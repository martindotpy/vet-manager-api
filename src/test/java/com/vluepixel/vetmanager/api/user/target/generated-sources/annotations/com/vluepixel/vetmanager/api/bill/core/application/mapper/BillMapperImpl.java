package com.vluepixel.vetmanager.api.bill.core.application.mapper;

import com.vluepixel.vetmanager.api.bill.core.application.dto.BillDto;
import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.core.domain.request.CreateBillRequest;
import com.vluepixel.vetmanager.api.bill.core.domain.request.UpdateBillRequest;
import com.vluepixel.vetmanager.api.bill.sale.application.dto.SaleDto;
import com.vluepixel.vetmanager.api.bill.sale.application.mapper.SaleMapper;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.Sale;
import com.vluepixel.vetmanager.api.client.core.application.dto.ClientDto;
import com.vluepixel.vetmanager.api.client.core.domain.model.Client;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-28T20:38:37-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.41.0.z20250213-2037, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class BillMapperImpl implements BillMapper {

    @Autowired
    private SaleMapper saleMapper;

    @Override
    public Bill.BillBuilder toBuilder(Bill t) {
        if ( t == null ) {
            return null;
        }

        Bill.BillBuilder billBuilder = createBillBuilder();

        billBuilder.client( t.getClient() );
        billBuilder.createdAt( t.getCreatedAt() );
        billBuilder.deleted( t.isDeleted() );
        billBuilder.discount( t.getDiscount() );
        billBuilder.id( t.getId() );
        billBuilder.lastPaidAt( t.getLastPaidAt() );
        billBuilder.paid( t.isPaid() );
        List<Sale> list = t.getSales();
        if ( list != null ) {
            billBuilder.sales( new ArrayList<Sale>( list ) );
        }
        billBuilder.total( t.getTotal() );
        billBuilder.totalPaid( t.getTotalPaid() );

        return billBuilder;
    }

    @Override
    public BillDto toDto(Bill domain) {
        if ( domain == null ) {
            return null;
        }

        BillDto.BillDtoBuilder billDto = BillDto.builder();

        billDto.client( clientToClientDto( domain.getClient() ) );
        billDto.createdAt( domain.getCreatedAt() );
        billDto.discount( domain.getDiscount() );
        billDto.id( domain.getId() );
        billDto.lastPaidAt( domain.getLastPaidAt() );
        billDto.sales( saleListToSaleDtoList( domain.getSales() ) );
        billDto.total( domain.getTotal() );
        billDto.totalPaid( domain.getTotalPaid() );

        return billDto.build();
    }

    @Override
    public Bill.BillBuilder fromRequest(CreateBillRequest request) {
        if ( request == null ) {
            return null;
        }

        Bill.BillBuilder billBuilder = createBillBuilder();

        billBuilder.client( mapClientIdToClient( request.getClientId() ) );
        billBuilder.discount( request.getDiscount() );

        billBuilder.total( new BigDecimal( "0" ) );
        billBuilder.totalPaid( new BigDecimal( "0" ) );

        return billBuilder;
    }

    @Override
    public Bill.BillBuilder fromRequest(UpdateBillRequest request) {
        if ( request == null ) {
            return null;
        }

        Bill.BillBuilder billBuilder = createBillBuilder();

        billBuilder.client( mapClientIdToClient( request.getClientId() ) );
        billBuilder.discount( request.getDiscount() );
        billBuilder.id( request.getId() );
        billBuilder.totalPaid( request.getTotalPaid() );

        billBuilder.lastPaidAt( updateLastPaidAtByTotalPaid(request.getTotalPaid()) );

        return billBuilder;
    }

    protected ClientDto clientToClientDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto.ClientDtoBuilder clientDto = ClientDto.builder();

        clientDto.address( StringUtilsMapper.trimString( client.getAddress() ) );
        List<String> list = client.getEmails();
        if ( list != null ) {
            clientDto.emails( new ArrayList<String>( list ) );
        }
        clientDto.firstName( StringUtilsMapper.trimString( client.getFirstName() ) );
        clientDto.id( client.getId() );
        clientDto.identification( StringUtilsMapper.trimString( client.getIdentification() ) );
        clientDto.identificationType( client.getIdentificationType() );
        clientDto.lastName( StringUtilsMapper.trimString( client.getLastName() ) );
        List<String> list1 = client.getPhones();
        if ( list1 != null ) {
            clientDto.phones( new ArrayList<String>( list1 ) );
        }

        return clientDto.build();
    }

    protected List<SaleDto> saleListToSaleDtoList(List<Sale> list) {
        if ( list == null ) {
            return null;
        }

        List<SaleDto> list1 = new ArrayList<SaleDto>( list.size() );
        for ( Sale sale : list ) {
            list1.add( saleMapper.toDto( sale ) );
        }

        return list1;
    }
}
