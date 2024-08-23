package dev.naman.productservice.services;

import dev.naman.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductServiceImpl")
public class SelfProductServiceImpl implements ProductService{

    @Override
    public List<GenericProductDto> getAllProducts(){
        return null;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        GenericProductDto product = new GenericProductDto();

        return product;
//        return "(\"selfProductServiceImpl\")Here is a product with id: " + id;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product){
        return null;
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id) {
        return null;
    }
}
