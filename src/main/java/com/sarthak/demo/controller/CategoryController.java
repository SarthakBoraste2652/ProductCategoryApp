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

import com.sarthak.demo.model.Category;
import com.sarthak.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping("")
	public ResponseEntity<List<Category>> getCategories(
			@RequestParam(value="page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
			) {
		return service.getCategories(page, pageSize);
	}
	
	@GetMapping("/{di}")
	public ResponseEntity<Category> getCategory(@PathVariable("di") int id) {
		return service.getCategory(id);
	}
	
	@PostMapping("")
	public ResponseEntity<Category> createCategory(@RequestBody Category category) {
		return service.createCategory(category);
	}	
	
	@PutMapping("/{di}")
	public ResponseEntity<Category> updateCategory(@PathVariable("di") int id, @RequestBody Category category) {
		return service.updateCategory(id, category);
	}
	
	@DeleteMapping("/{di}")
	public ResponseEntity<Void> deleteCategory(@PathVariable("di") int id) {
		return service.deleteCategory(id);
	}
}