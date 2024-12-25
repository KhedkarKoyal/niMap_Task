package com.tech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.model.Category;
import com.tech.model.Product;
import com.tech.service.CatService;

@RestController
@RequestMapping("/category")
public class Controller {
	@Autowired
	CatService catService;

	 // Get all products with pagination
	@GetMapping("/allCategory")
	public ResponseEntity<Page<Category>> getAllCategory(Pageable pageable) {
			Page<Category> category = catService.getAllCategory(pageable);
			return ResponseEntity.ok(category);
		}
	
	// get by id
	@GetMapping(value="/getCat/{id}")
	public Category getById(@PathVariable("id") Long id)
	{
		return catService.getCategoryById(id);
	}
	
	//add category
	@PostMapping(value="/addCategory")
	public Category saveNotice(@RequestBody Category model)
	{
		return catService.saveCat(model);

	}
	
	//update category
	@PutMapping(value="/update/{id}")
	 public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category existingCategory = catService.getCategoryById(id);
        existingCategory.setName(category.getName());
        return catService.updateCategory(existingCategory);
    }
	

	//delete category
	@DeleteMapping(value="/delete/{id}")
	public String deleteById(@PathVariable("id") Integer id) {
	   return  catService.deleteById(id);
	   
	}

	
	
	

}

