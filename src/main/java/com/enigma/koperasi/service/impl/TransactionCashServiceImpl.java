package com.enigma.koperasi.service.impl;

import com.enigma.koperasi.constant.ETypeSaving;
import com.enigma.koperasi.exception.ApplicationException;
import com.enigma.koperasi.model.dto.request.cash.CashReq;
import com.enigma.koperasi.model.dto.request.transaction_cash.TransactionCashReq;
import com.enigma.koperasi.model.dto.request.type_cash.TypeCashReq;
import com.enigma.koperasi.model.dto.response.cash.CashRes;
import com.enigma.koperasi.model.dto.response.employee.EmployeeRes;
import com.enigma.koperasi.model.dto.response.member.MemberRes;
import com.enigma.koperasi.model.dto.response.transaction_cash.TransactionCashRes;
import com.enigma.koperasi.model.dto.response.type_cash.TypeCashRes;
import com.enigma.koperasi.model.entity.*;
import com.enigma.koperasi.model.mapper.*;
import com.enigma.koperasi.repository.TransactionCashRepository;
import com.enigma.koperasi.service.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionCashServiceImpl implements TransactionCashService {
  private final TransactionCashRepository transactionCashRepository;
  private final TypeCashService typeCashService;
  private final MemberService memberService;
  private final EmployeeService employeeService;
  private final CashService cashService;

  private final TypeCashMapper typeCashMapper;
  private final MemberMapper memberMapper;
  private final EmployeeMapper employeeMapper;
  private final CashMapper cashMapper;
  private final TransactionCashMapper transactionCashMapper;

  @Transactional(rollbackFor = Exception.class)
  @Override
  public TransactionCashRes transaction(TransactionCashReq req) {
    SecurityContext authentication = SecurityContextHolder.getContext();
    AppUser user = (AppUser) authentication.getAuthentication().getPrincipal();
    TypeCashReq typeCashReq = TypeCashReq.builder()
        .typeCashName(req.getTypeCash())
        .build();

    MemberRes memberRes = memberService.findById(req.getMemberId());
    EmployeeRes employeeRes = employeeService.findByUserCredentialId(user.getId());
    TypeCashRes typeCashRes = typeCashService.getOrSave(typeCashReq);
    CashRes cashRes = cashService.findById(memberRes.getCash().getId());
    CashReq cashReq = cashMapper.convertToReq(cashRes);
    if(typeCashRes.getTypeCashName().equals(ETypeSaving.IN)){
      cashReq.setTotalCash(cashReq.getTotalCash() + req.getAmount());
    }else{
      cashReq.setTotalCash(cashReq.getTotalCash() - req.getAmount());
      if(cashReq.getTotalCash() < 0) throw new ApplicationException(
          HttpStatus.BAD_REQUEST.name(),
          "Saldo tidak cukup",
          HttpStatus.BAD_REQUEST
      );
    }
    cashService.save(cashReq);

    TransactionSaving transactionSaving = TransactionSaving.builder()
        .employee(employeeMapper.convertToEntity(employeeRes))
        .member(memberMapper.convertToEntity(memberRes))
        .typeCash(typeCashMapper.convertToEntity(typeCashRes))
        .trxDate(req.getTrxDate())
        .amount(req.getAmount())
        .description(req.getDescription())
        .build();

    transactionCashRepository.store(transactionSaving);

    return transactionCashMapper.convertToDto(transactionSaving, cashReq);
  }

  @Override
  public Page<TransactionCashRes> findAll(String employeeName, String memberName, Integer amount, ETypeSaving typeSaving, Date startDate, Date endDate, int page, int size) {
    Specification<TransactionSaving> specification = (root, query, criteriaBuilder) -> {
      Join<TransactionSaving, Employee> employeeJoin = root.join("employee");
      Join<TransactionSaving, Member> memberJoin = root.join("member");
      Join<TransactionSaving, TypeCash> typeCashJoin = root.join("typeCash");

      List<Predicate> predicates = new ArrayList<>();

      if(employeeName != null){
        predicates.add(
            criteriaBuilder.like(
              criteriaBuilder.lower(employeeJoin.get("name")),
              "%" + employeeName.toLowerCase() + "%"
            )
        );
      }

      if(memberName != null){
        predicates.add(
            criteriaBuilder.like(
                criteriaBuilder.lower(memberJoin.get("name")),
                "%" + memberName + "%"
            )
        );
      }

      if(amount != null){
        predicates.add(
            criteriaBuilder.equal(
                root.get("amount"),
                amount
            )
        );
      }

      if(typeSaving != null){
        predicates.add(
            criteriaBuilder.equal(
                typeCashJoin.get("typeCashName"),
                typeSaving.name()
            )
        );
      }

      if(startDate != null || endDate != null){
        if(startDate != null && endDate != null){
          predicates.add(
              criteriaBuilder.between(
                  root.get("trxDate"),
                  startDate, endDate
              )
          );
        }else if(startDate != null){
          predicates.add(
              criteriaBuilder.equal(
                  root.get("trxDate"),
                  startDate
              )
          );
        }
      }

      return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
    };

    Pageable pageable = PageRequest.of(page - 1, size);
    Page<TransactionSaving> transactionSavings = transactionCashRepository.findAll(specification, pageable);

    List<TransactionCashRes> transactionCashResList = transactionSavings.getContent().stream()
        .map(transactionCashMapper::convertToDto).toList();

    return new PageImpl<>(transactionCashResList, pageable, transactionSavings.getTotalElements());
  }

  @Override
  public Page<TransactionCashRes> findByMember(String memberId, int page, int size) {
    Pageable pageable = PageRequest.of(page - 1, size);

    Page<TransactionSaving> transactionSavings = transactionCashRepository.findByMemberId(memberId, pageable);

    List<TransactionCashRes> transactionCashRes = transactionSavings.getContent().stream()
        .map(transactionCashMapper::convertToDto).toList();

    return new PageImpl<>(transactionCashRes, pageable, transactionSavings.getTotalElements());
  }
}
