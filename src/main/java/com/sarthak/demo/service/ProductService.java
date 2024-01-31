package com.sarthak.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sarthak.demo.model.Category;
import com.sarthak.demo.model.Product;
import com.sarthak.demo.repository.CategoryRepository;
import com.sarthak.demo.repository.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public ResponseEntity<List<Product>> getProducts(int page, int pageSize) {
		Pageable p = PageRequest.of(page, pageSize);
		Page<Product>pageProduct = productRepo.findAll(p);
		
		List<Product> products = pageProduct.getContent();		
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	public ResponseEntity<Product> getProduct(int id) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			return new ResponseEntity<>(product, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Product> createProduct(Product product) {
		Category category = product.getCategory();
        if (category != null) {
            category = categoryRepo.findById(category.getcId()).orElse(category);
        }

        category = categoryRepo.save(category);
        product.setCategory(category);
		
		product = productRepo.save(product);
		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	public ResponseEntity<Product> updateProduct(int id, Product updatedProduct) {
	    Optional<Product> optionalProduct = productRepo.findById(id);
	    if (optionalProduct.isEmpty()) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }

	    Product existingProduct = optionalProduct.get();

	    existingProduct.setpName(updatedProduct.getpName());
	    existingProduct.setPrice(updatedProduct.getPrice());
	    
	    Category updatedCategory = updatedProduct.getCategory();
	    if (updatedCategory != null) {
	        if (!entityManager.contains(updatedCategory)) {
	            updatedCategory = entityManager.merge(updatedCategory);
	        }
	        existingProduct.setCategory(updatedCategory);
	    }
	    productRepo.save(existingProduct);
	    return new ResponseEntity<>(existingProduct,HttpStatus.OK);
	}

	public ResponseEntity<Void> deleteProduct(int id) {
				
		Optional<Product> optionalProduct = productRepo.findById(id);
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			productRepo.deleteById(product.getpId());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}