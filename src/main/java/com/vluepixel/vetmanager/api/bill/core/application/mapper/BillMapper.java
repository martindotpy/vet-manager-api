package com.vluepixel.vetmanager.api.bill.core.application.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ObjectFactory;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.core.domain.request.CreateBillRequest;
import com.vluepixel.vetmanager.api.bill.core.domain.request.UpdateBillRequest;
import com.vluepixel.vetmanager.api.bill.sale.application.mapper.SaleMapper;
import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.shared.application.mapper.CrudMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

/**
 * Bill mapper.
 */
@Mapper(componentModel = "spring", uses = { StringUtilsMapper.class, SaleMapper.class })
public interface BillMapper
        extends CrudMapper<Bill, BillDto, Bill.BillBuilder> {
    /**
     * Creates a new {@link Bill} builder.
     *
     * @return the builder
     */
    @ObjectFactory
    default Bill.BillBuilder createBillBuilder() {
        return Bill.builder();
    }

    /**
     * Create bill from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * <li><code>createdAt</code></li>
     * <li><code>deleted</code></li>
     * <li><code>paid</code></li>
     * <li><code>total</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the create bill request.
     * @return the bill builder
     */
    @Mapping(target = "total", constant = "0")
    @Mapping(target = "totalPaid", constant = "0")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "sales", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastPaidAt", ignore = true)
    @Mapping(target = "client", source = "clientId")
    Bill.BillBuilder fromRequest(CreateBillRequest request);

    /**
     * Update bill from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>createdAt</code></li>
     * <li><code>deleted</code></li>
     * <li><code>paid</code></li>
     * <li><code>total</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the update bill request.
     * @return the bill builder
     */
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "paid", ignore = true)
    @Mapping(target = "total", ignore = true)
    @Mapping(target = "sales", ignore = true)
    @Mapping(target = "lastPaidAt", expression = "java(updateLastPaidAtByTotalPaid(request.getTotalPaid()))")
    @Mapping(target = "client", source = "clientId")
    Bill.BillBuilder fromRequest(UpdateBillRequest request);

    /**
     * Maps the client id to a client.
     *
     * @param clientId the client id
     * @return the client
     */
    default Client mapClientIdToClient(Long clientId) {
        return Client.builder().id(clientId).build();
    }

    /**
     * Updates the last paid at by total paid.
     *
     * @param totalPaid the total paid.
     * @return the last paid at
     */
    default LocalDateTime updateLastPaidAtByTotalPaid(BigDecimal totalPaid) {
        return totalPaid.compareTo(BigDecimal.ZERO) > 0 ? LocalDateTime.now() : null;
    }
}
