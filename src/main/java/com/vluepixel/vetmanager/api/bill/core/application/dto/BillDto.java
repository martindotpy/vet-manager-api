package com.vluepixel.vetmanager.api.bill.core.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.vluepixel.vetmanager.api.client.core.application.dto.ClientDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Bill DTO.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class BillDto {
    private Long id;

    private BigDecimal total;
    private Integer discount;
    private BigDecimal totalPaid;
    private LocalDateTime createdAt;

    private ClientDto client;
}
