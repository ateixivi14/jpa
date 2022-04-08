package com.training.jpa.dto;

import com.training.jpa.entity.Contract;
import com.training.jpa.entity.Receipt;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@RequiredArgsConstructor
public class ContractDto {
    private final Long id;
    private final List<Integer> receiptDtoAmountList;

    public static ContractDto toDto(Contract contract) {
        return ContractDto.builder()
                .id(contract.getId())
                .receiptDtoAmountList(mapReceipts(contract.getReceipts()))
                .build();
    }

    private static List<Integer> mapReceipts(List<Receipt> receiptList) {
        return receiptList.stream().map(Receipt::getAmount).collect(Collectors.toList());
    }
}
