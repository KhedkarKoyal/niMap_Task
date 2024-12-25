package com.tech.service;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.tech.model.Category;
import com.tech.model.Product;
import com.tech.repo.CatRepo;
import com.tech.repo.ProductRepo;

@Service("catService")
public class CatService {
	private long sid;
	@Autowired
	CatRepo catRepo;
	 @Autowired
	    private ProductRepo proRepo;

	 // Category crud operations 
	 
	 // get all Category with pagination
	 public Page<Category> getAllCategory(Pageable pageable) {
	        return catRepo.findAll(pageable); 
	 }
	
	//add new category 
	public Category saveCat(Category category)
	{
		return catRepo.save(category);
	}
	
	//get category by id
	public Category getCategoryById(Long id) {
        return catRepo.findById(id).orElseThrow();
        }
	 
	//update category
	public Category updateCategory(Category category) {
	 return catRepo.save(category);
	    }
	
	//delete category by id
	public String deleteById(long id) {
	    java.util.Optional<Category> categoryOptional = catRepo.findById(id);
	    if (categoryOptional.isPresent()) {
	        catRepo.deleteById(id);
	        return "Category with ID " + id + " has been deleted successfully.";
	    } else {
	        return "Category with ID " + id + " not found.";
	    }
	}
	
	
	
//=============================================================================================
	
	//Product crud
	
	//get all product with pagination
	 public Page<Product> getAllProducts(Pageable pageable) {
		 return proRepo.findAll(pageable);
	  }
	 
	 //add new product
	 public Product savePro(Product product) {
		return proRepo.save(product);
    	}

	 //get product by id
	 public Product getProductById(Long id) {
        return proRepo.findById(id).orElseThrow();
        }

	 // update product
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		 return proRepo.save(product);
	}
	
	//delete product by id
	public String deleteProductById(long id) {
	    java.util.Optional<Category> categoryOptional = catRepo.findById(id);
	    if (categoryOptional.isPresent()) {
	        catRepo.deleteById(id);
	        return "Category with ID " + id + " has been deleted successfully.";
	    } else {
	        return "Category with ID " + id + " not found.";
	    }
	}
	 	
	
	
	public Product getProductDetails(Long productId) {
        return proRepo.findByIdWithCategory(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
