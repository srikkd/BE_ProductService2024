package dev.naman.productservice;

import dev.naman.productservice.models.Category;
import dev.naman.productservice.models.Price;
import dev.naman.productservice.models.Product;
import dev.naman.productservice.repositories.CategoryRepository;
import dev.naman.productservice.repositories.PriceRepository;
import dev.naman.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@SpringBootApplication
public class ProductserviceApplication implements CommandLineRunner {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	public ProductserviceApplication(ProductRepository productRepository,
									 CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductserviceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner demo(ProductRepository productRepository, CategoryRepository categoryRepository,
//								  PriceRepository priceRepository) {
//		return (args) -> {
//			Category category = new Category();
//			category.setName("Apple Devices");
//			Category savedCategory = categoryRepository.save(category);
//
//			Price price = new Price("INR", 14000);
////			priceRepository.save(price);
//
//			Product product = new Product();
//			product.setTitle("iPhone 15 Pro");
//			product.setDescription("The Best till now!!");
//			product.setCategory(savedCategory);
//			product.setPrice(price);
//			productRepository.save(product);
//
//			Product product1 = productRepository.findDistinctByTitle("iPhone 15 Pro");
//			List<Product> products = productRepository.findAllByPrice_Currency("INR");
//
////			productRepository.deleteById(UUID.fromString("622d65e4-2ed4-4a1c-9937-e31045b1338c"));
//
////			Optional<Category> category1 = categoryRepository.findById(UUID.fromString("622d65e4-2ed4-4a1c-9937-e31045b1338c"));
////			System.out.println("Category name is: " + category1.get().getName());
////			System.out.println("Printing all products in the category: ");
//
////			for (Product product1: category1.get().getProductList()) {
////				System.out.println(product1.getTitle());
////			}
//		};
//	}

	@Override
	public void run(String... args) throws Exception{

		Category category = new Category();
		category.setName("Apple Devices");
		Category savedCategory = categoryRepository.save(category);

		Price price = new Price("INR", 15000);

		Product product = new Product();
		product.setTitle("iPhone 15 Pro");
		product.setDescription("The Best till now!!");
		product.setCategory(savedCategory);
		product.setPrice(price);
		productRepository.save(product);

//		Optional<Category> category1 = categoryRepository.findById(UUID.fromString("9214dfb9-db3f-4944-80d5-b55fb245a5e0"));
//		System.out.println("Category name is: " + category1.get().getName());
//		System.out.println("Printing all products in the category: ");
//
//		for (Product product1: category1.get().getProductList()){
//			System.out.println(product1.getTitle());
//		}

		Optional<Category> categoryOptional =
				categoryRepository.findById(UUID.fromString("ae65fda1-7ecc-4798-8964-86ae5e4eaa21"));
		Category category1 = categoryOptional.get();

		System.out.println("found category object [Lazy fetch: without associated productList], with Name: " +
							category1.getName());

		List<Product> productList = category1.getProductList();
		for(Product product1: productList){
			System.out.println("Product Name: " + product1.getDescription());
		}
	}
}
