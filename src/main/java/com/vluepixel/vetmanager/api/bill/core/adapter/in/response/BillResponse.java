package com.vluepixel.vetmanager.api.bill.core.adapter.in.response;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.shared.adapter.in.response.ContentResponse;

import lombok.experimental.SuperBuilder;

/**
 * Bill response.
 */
@SuperBuilder
public final class BillResponse extends ContentResponse<BillDto> {
}
