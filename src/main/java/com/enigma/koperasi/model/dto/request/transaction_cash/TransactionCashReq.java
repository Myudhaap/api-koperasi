package com.enigma.koperasi.model.dto.request.transaction_cash;

import com.enigma.koperasi.constant.ETypeSaving;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionCashReq {
    private String memberId;
    private ETypeSaving typeCash;
    private Date trxDate;
    private Long amount;
    private String description;
}
