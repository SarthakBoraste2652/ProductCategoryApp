package com.sarthak.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarthak.demo.model.Product;
import com.sarthak.demo.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@GetMapping("")						
	public ResponseEntity<List<Product>> getProducts(
			@RequestParam(value="page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
			){
		return service.getProducts(page, pageSize);
	}
	
	@GetMapping("{di}")						
	public ResponseEntity<Product> getProduct(@PathVariable("di") int id) {
		return service.getProduct(id);
	}
	
	@PostMapping("")							
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		return service.createProduct(product);
	}
	
	@PutMapping("/{di}")							
	public ResponseEntity<Product> updateProduct(@PathVariable("di") int id ,@RequestBody Product product) {
		return service.updateProduct(id, product);
	}
	
	@DeleteMapping("/{di}")							//deleting product by id
	public ResponseEntity<Void> deleteProduct(@PathVariable("di") int id) {
		return service.deleteProduct(id);
	}
}