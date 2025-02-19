package com.vluepixel.vetmanager.api.bill.core.adapter.in.response;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.PaginatedResponse;

import lombok.experimental.SuperBuilder;

/**
 * Paginated bill response.
 */
@SuperBuilder
public final class PaginatedBillResponse extends PaginatedResponse<BillDto> {
}
