package com.vluepixel.vetmanager.api.bill.sale.adapter.in.response;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.PaginatedResponse;

import lombok.experimental.SuperBuilder;

/**
 * Paginated sale response.
 */
@SuperBuilder
public final class PaginatedSaleResponse extends PaginatedResponse<SaleDto> {
}
