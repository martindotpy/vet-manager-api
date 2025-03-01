package com.vluepixel.vetmanager.api.bill.sale.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.vluepixel.vetmanager.api.bill.core.domain.model.Bill;
import com.vluepixel.vetmanager.api.bill.sale.application.dto.ProductSaleDto;
import com.vluepixel.vetmanager.api.bill.sale.domain.model.ProductSale;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.CreateProductSaleRequest;
import com.vluepixel.vetmanager.api.bill.sale.domain.request.UpdateProductSaleRequest;
import com.vluepixel.vetmanager.api.product.core.domain.model.Product;
import com.vluepixel.vetmanager.api.shared.application.mapper.CrudMapper;
import com.vluepixel.vetmanager.api.shared.application.mapper.StringUtilsMapper;

/**
 * Product sale mapper.
 */
@Mapper(componentModel = "spring", uses = { StringUtilsMapper.class })
public interface ProductSaleMapper
        extends CrudMapper<ProductSale, ProductSaleDto, ProductSale> {
    /**
     * Create product sale from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>id</code></li>
     * <li><code>seller</code></li>
     * <li><code>price</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the create product sale request.
     * @return the product sale builder
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "bill", source = "billId")
    @Mapping(target = "product", source = "productId")
    ProductSale fromRequest(CreateProductSaleRequest request);

    /**
     * Update product sale from request.
     *
     * <ul>
     * <li><strong>Ignores:</strong>
     * <ul>
     * <li><code>bill</code></li>
     * <li><code>seller</code></li>
     * <li><code>price</code></li>
     * </ul>
     * </li>
     * </ul>
     *
     * @param request the update product sale request.
     * @return the product sale builder
     */
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "bill", ignore = true)
    @Mapping(target = "product", source = "productId")
    ProductSale fromRequest(UpdateProductSaleRequest request);

    /**
     * Maps the product id to an product.
     *
     * @param productId the product id.
     * @return the product
     */
    default Product mapProductIdToProduct(Long productId) {
        return Product.builder().id(productId).build();
    }

    /**
     * Maps the bill id to an bill.
     *
     * @param billId the bill id.
     * @return the bill
     */
    default Bill mapBillIdToBill(Long billId) {
        return Bill.builder().id(billId).build();
    }
}
