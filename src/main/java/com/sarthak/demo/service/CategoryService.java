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
import com.sarthak.demo.repository.CategoryRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	public ResponseEntity<List<Category>> getCategories(int page, int pageSize) {		
		Pageable p = PageRequest.of(page, pageSize);
		
		Page<Category> pageCategory = categoryRepo.findAll(p);
		
		List<Category> categories = pageCategory.getContent();
		
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	public ResponseEntity<Category>  createCategory(Category category) {
		category = categoryRepo.save(category);
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}

	public ResponseEntity<Category> getCategory(int id) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	public ResponseEntity<Category> updateCategory(int id, Category updatedCategory) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);

        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            
            existingCategory.setcName(updatedCategory.getcName());
            existingCategory.setProducts(updatedCategory.getProducts());
            
            return new ResponseEntity<>(categoryRepo.save(existingCategory), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	public ResponseEntity<Void> deleteCategory(int id) {
		Optional<Category> optionalCategory = categoryRepo.findById(id);

        if (optionalCategory.isPresent()) {
            categoryRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
	}
}