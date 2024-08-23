package dev.naman.productservice.thirdpartyclients.productservice.fakestore;

import dev.naman.productservice.dtos.FakeStoreProductDto;
import dev.naman.productservice.dtos.GenericProductDto;
import dev.naman.productservice.exceptions.NotFoundException;
import dev.naman.productservice.thirdpartyclients.productservice.ThirdPartyProductServiceClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreProductServiceClient implements ThirdPartyProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;

//    @Value("${fakestore.api.url}")
//    private String fakeStoreApiUrl;
//
//    @Value("${fakestore.api.paths.product}")
//    private String fakeStoreProductsApiPath;

    private String specificProductRequestUrl;
    private String productRequestBaseUrl;

    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,
                                         @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                         @Value("${fakestore.api.paths.product}") String fakeStoreProductsApiPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.specificProductRequestUrl = fakeStoreApiUrl + fakeStoreProductsApiPath + "/{id}";
        this.productRequestBaseUrl = fakeStoreApiUrl + fakeStoreProductsApiPath;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product = new GenericProductDto();    //the type of return in our system, should be different
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());

        return product;
    }

    public FakeStoreProductDto[] getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();

//        ResponseEntity< ArrayList<GenericProductDto> > response =
//                restTemplate.getForEntity(productRequestBaseUrl, ArrayList<GenericProductDto>.class);

        //Following will need masking later and still the internal casting will not be possible:
        //because there are not objects inside the AL, but its just like a HashMap or something else
//        ResponseEntity< ArrayList > response =
//                restTemplate.getForEntity(productRequestBaseUrl, ArrayList.class);

        ResponseEntity< FakeStoreProductDto[] > response =
                restTemplate.getForEntity(productRequestBaseUrl, FakeStoreProductDto[].class);

        return response.getBody();
    }

    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<GenericProductDto> response =
//                restTemplate.getForEntity(specificProductRequestUrl, GenericProductDto.class, id);

        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);

//        response.getStatusCode();
        FakeStoreProductDto fakeStoreProductDto = response.getBody();   //the type of response from 3-P should be FakeStoreProductDto

        if(fakeStoreProductDto == null){
            throw new NotFoundException("Product with the id: " + id + " doesn't exist.");
        }
        return fakeStoreProductDto;
//        return "(\"fakeStoreProductServiceImpl\")Here is a product with id: " + id;
        //return null;
    }

    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();

//        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(
//                productRequestBaseUrl, product, GenericProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                productRequestBaseUrl, product, FakeStoreProductDto.class);

//        FakeStoreProductDto fakeStoreProductDto = response.getBody();   //the type of response from 3-P should be FakeStoreProductDto
        return response.getBody();
    }

    public FakeStoreProductDto deleteProductById(Long id) {
//        HttpEntity<Product> request =
//                new HttpEntity<Product>(new Product());
        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.delete(specificProductRequestUrl, id);   //when the delete API returns void, but here it returns JSON
//        restTemplate.delete(specificProductRequestUrl, request, GenericProductDto.class, id);    //wrong param-list
//        restTemplate.exchange(specificProductRequestUrl, HttpMethod.DELETE, request, void.class, id);

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.execute(specificProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);

//        FakeStoreProductDto fakeStoreProductDto = response.getBody();   //the type of response from 3-P should be FakeStoreProductDto
        return response.getBody();
    }

    public FakeStoreProductDto updateProductById(Long id) {

        RestTemplate restTemplate = new RestTemplate();

//        restTemplate.put(specificProductRequestUrl, requestBody, id);
//        restTemplate.exchange(specificProductRequestUrl, HttpMethod.PUT, requestBody, void.class, id);

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.execute(specificProductRequestUrl, HttpMethod.PUT, requestCallback, responseExtractor, id);

//        FakeStoreProductDto fakeStoreProductDto = response.getBody();   //the type of response from 3-P should be FakeStoreProductDto
        return response.getBody();
    }
}
