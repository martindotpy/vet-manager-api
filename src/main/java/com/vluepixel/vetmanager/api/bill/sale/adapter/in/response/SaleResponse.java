package com.vluepixel.vetmanager.api.bill.sale.adapter.in.response;

import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.ContentResponse;

import lombok.experimental.SuperBuilder;

/**
 * Sale response.
 */
@SuperBuilder
public final class SaleResponse extends ContentResponse<SaleDto> {
}
