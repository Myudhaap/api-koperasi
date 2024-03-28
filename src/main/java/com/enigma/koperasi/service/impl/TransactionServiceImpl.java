package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.constant.EApprovalStatus;
import com.enigma.koperasi.constant.ELoanStatus;
import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.transaction_loan.TransactionApproveReq;
import com.enigma.koperasi.model.dto.request.transaction_loan.TransactionPayReq;
import com.enigma.koperasi.model.dto.request.transaction_loan.TransactionReq;
import com.enigma.koperasi.model.dto.response.transaction_loan.TransactionRes;
import com.enigma.koperasi.model.dto.response.user_credential.UserCredentialRes;
import com.enigma.koperasi.model.entity.*;
import com.enigma.koperasi.model.mapper.*;
import com.enigma.koperasi.repository.TransactionRepository;
import com.enigma.koperasi.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;
  private final TransactionDetailService transactionDetailService;
  private final LoanTypeService loanTypeService;
  private final MemberService memberService;
  private final InstalmentTypeService instalmentTypeService;
  private final UserService userService;
  private final EmployeeService employeeService;

  private final LoanTypeMapper loanTypeMapper;
  private final InstalmentTypeMapper instalmentTypeMapper;
  private final MemberMapper memberMapper;
  private final TransactionMapper transactionMapper;
  private final EmployeeMapper employeeMapper;

  @Override
  public TransactionRes create(TransactionReq req) {
    Member member = memberMapper.convertToEntity(memberService.findById(req.getMemberId()));
    LoanType loanType = loanTypeMapper.convertToEntity(loanTypeService.findById(req.getLoanTypeId()));
    InstalmentType instalmentType = instalmentTypeMapper.convertToEntity(
        instalmentTypeService.findById(req.getInstalmentTypeId())
    );

    if(req.getNominal() > loanType.getMaxLoan()) throw new ApplicationException(
        HttpStatus.BAD_REQUEST.name(),
        "Nominal has to much from max nominal loan",
        HttpStatus.BAD_REQUEST
    );

    LoanTransaction transaction = LoanTransaction.builder()
        .loanType(loanType)
        .instalmentType(instalmentType)
        .member(member)
        .nominal(req.getNominal())
        .createdAt(Instant.now().toEpochMilli())
        .build();
    transactionRepository.save(transaction);

    return transactionMapper.convertToDto(transaction);
  }

  @Override
  public List<TransactionRes> findAll() {
    List<LoanTransaction> loanTransactions = transactionRepository.findAll();

    return loanTransactions.stream()
        .map(transactionMapper::convertToDto)
        .toList();
  }

  @Override
  public TransactionRes findById(String id) {
    LoanTransaction loanTransaction = transactionRepository.findById(id)
        .orElseThrow(() -> new ApplicationException(
            HttpStatus.NOT_FOUND.name(),
            "Transaction not found",
            HttpStatus.NOT_FOUND
        ));

    return transactionMapper.convertToDto(loanTransaction);
  }

  @Transactional(rollbackOn = Exception.class)
  @Override
  public TransactionRes approve(String id) {
    TransactionRes existTrans = findById(id);

    if(existTrans.getApprovalStatus() != null)
      throw new ApplicationException(
          HttpStatus.CONFLICT.name(),
          "Transaction cant be approved, because this transaction was have action",
          HttpStatus.CONFLICT
      );

    SecurityContext securityContext = SecurityContextHolder.getContext();
    AppUser appUser = (AppUser) securityContext.getAuthentication().getPrincipal();

    Employee employee = employeeMapper.convertToEntity(employeeService.findByUserCredentialId(appUser.getId()));
    Member member = memberMapper.convertToEntity(memberService.findById(existTrans.getMemberId()));
    LoanType loanType = loanTypeMapper.convertToEntity(loanTypeService.findById(existTrans.getLoanTypeId()));
    InstalmentType instalmentType = instalmentTypeMapper.convertToEntity(
        instalmentTypeService.findById(existTrans.getInstalmentTypeId())
    );

    LoanTransaction transaction = transactionMapper.convertToEntity(
        existTrans,
        member,
        loanType,
        instalmentType,
        employee
    );

    transaction.setApprovalStatus(EApprovalStatus.APPROVED);
    transaction.setEmployee(employee);
    transaction.setApprovedAt(Instant.now().toEpochMilli());
    transaction.setUpdatedAt(Instant.now().toEpochMilli());

    List<LoanTransactionDetail> loanTransactionDetails = makeTransactionDetail(
        id, instalmentType, transaction, loanType
    );

    loanTransactionDetails = loanTransactionDetails.stream()
            .map(transactionDetail -> {
              transactionDetail.setLoanTransaction(transaction);
              transactionDetailService.save(transactionDetail);
              return transactionDetail;
            }).toList();

    transaction.setLoanTransactionDetails(loanTransactionDetails);
    transactionRepository.saveAndFlush(transaction);

    return transactionMapper.convertToDto(transaction);
  }

  @Override
  public TransactionRes reject(String id) {
    TransactionRes existTrans = findById(id);

    if(existTrans.getApprovalStatus() != null)
      throw new ApplicationException(
          HttpStatus.CONFLICT.name(),
          "Transaction cant be rejected, because this transaction was have action",
          HttpStatus.CONFLICT
      );
    SecurityContext securityContext = SecurityContextHolder.getContext();
    AppUser appUser = (AppUser) securityContext.getAuthentication().getPrincipal();

    Employee employee = employeeMapper.convertToEntity(employeeService.findByUserCredentialId(appUser.getId()));
    Member member = memberMapper.convertToEntity(memberService.findById(existTrans.getMemberId()));
    LoanType loanType = loanTypeMapper.convertToEntity(loanTypeService.findById(existTrans.getLoanTypeId()));
    InstalmentType instalmentType = instalmentTypeMapper.convertToEntity(
        instalmentTypeService.findById(existTrans.getInstalmentTypeId())
    );

    LoanTransaction transaction = transactionMapper.convertToEntity(
        existTrans,
        member,
        loanType,
        instalmentType,
        employee
    );

    transaction.setApprovalStatus(EApprovalStatus.REJECTED);
    transaction.setEmployee(employee);
    transaction.setApprovedAt(Instant.now().toEpochMilli());
    transaction.setUpdatedAt(Instant.now().toEpochMilli());

    transactionRepository.saveAndFlush(transaction);

    return transactionMapper.convertToDto(transaction);
  }

  @Override
  @Transactional(rollbackOn = Exception.class)
  public TransactionRes pay(TransactionPayReq req, String trxId) {
    LoanTransactionDetail loanTransactionDetail = transactionDetailService.findById(req.getLoanTransactionDetailId());

    if(loanTransactionDetail.getLoanTransaction().getApprovalStatus().equals(EApprovalStatus.REJECTED))
      throw new ApplicationException(
          HttpStatus.CONFLICT.name(),
          "Cant pay this transaction, because transaction was rejected",
          HttpStatus.CONFLICT
      );

    if(loanTransactionDetail.getLoanStatus().equals(ELoanStatus.PAID))
      throw new ApplicationException(
          HttpStatus.CONFLICT.name(),
          "Cant pay this transaction, because transaction was paid",
          HttpStatus.CONFLICT
      );

    loanTransactionDetail.setTransactionDate(Instant.now().toEpochMilli());
    loanTransactionDetail.setUpdatedAt(Instant.now().toEpochMilli());
    loanTransactionDetail.setLoanStatus(ELoanStatus.PAID);
    transactionDetailService.save(loanTransactionDetail);

    return findById(trxId);
  }

  public List<LoanTransactionDetail> makeTransactionDetail(
      String id,
      InstalmentType instalmentType,
      LoanTransaction transaction,
      LoanType loanType
  ){
    List<LoanTransactionDetail> loanTransactionDetails = new ArrayList<>();
    double interestRate = (double) loanType.getInterest() /100;
    int instalmentPeriod = 0;

    switch (instalmentType.getInstalmentType()){
      case ONE_MONTH -> instalmentPeriod = 1;
      case THREE_MONTHS -> instalmentPeriod = 3;
      case SIXTH_MONTHS -> instalmentPeriod = 6;
      case NINE_MONTHS -> instalmentPeriod = 9;
      case TWELVE_MONTHS -> instalmentPeriod = 12;
    }

    Double interestTotal = ((interestRate/instalmentPeriod) * transaction.getNominal()) + (transaction.getNominal()/instalmentPeriod);
    for (int i = 0; i < instalmentPeriod; i++){
      loanTransactionDetails.add(
          LoanTransactionDetail.builder()
              .nominal(interestTotal)
              .loanStatus(ELoanStatus.UNPAID)
              .createdAt(Instant.now().toEpochMilli())
              .build()
      );
    }

    return loanTransactionDetails;
  }
}
