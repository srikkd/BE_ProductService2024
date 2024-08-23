package dev.naman.productservice.thirdpartyclients.productservice;

import dev.naman.productservice.dtos.FakeStoreProductDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;

public interface ThirdPartyProductServiceClient {
    FakeStoreProductDto[] getAllProducts();

    FakeStoreProductDto getProductById(Long id) throws NotFoundException;

    FakeStoreProductDto createProduct(GenericProductDto product);

    FakeStoreProductDto deleteProductById(Long id);

    FakeStoreProductDto updateProductById(Long id);
}
