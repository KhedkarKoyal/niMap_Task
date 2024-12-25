package com.tech.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import response.CategoryResponse;
import response.ProductResponse;

@RestController
@RequestMapping("/product")
public class ProController {
    @Autowired
    private CatService catService;

    // Get all products with pagination
    @GetMapping("/allProducts")
    public ResponseEntity<Page<Product>> getAllProducts(Pageable pageable) {
        Page<Product> products = catService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

    // get product by id 
    @GetMapping(value="/getPro/{id}")
	public Product getById(@PathVariable("id") Long id)
	{
		return catService.getProductById(id);
	}
    
    // Add a new product
    @PostMapping("/addProduct")
    public Product saveProduct(@RequestBody Product product) {
        return catService.savePro(product);
    }
    
  //update category
  	@PutMapping(value="/update/{id}")
  	 public Product updateCategory(@PathVariable Long id, @RequestBody Product product) {
  		Product existingProduct = catService.getProductById(id);
          existingProduct.setName(product.getName());
          return catService.updateProduct(existingProduct);
      }
  	

  	//delete category
  	@DeleteMapping(value="/delete/{id}")
  	public String deleteProductById(@PathVariable("id") Integer id) {
  	   return  catService.deleteProductById(id);
  	   
  	}
  	

    @GetMapping("kk/{id}")
    public ResponseEntity<ProductResponse> getProductDetails(@PathVariable Long id) {
        Product product = catService.getProductDetails(id);

        // Convert to DTO to avoid exposing entity details directly
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setPrice(product.getPrice());

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(product.getCategory().getId());
        categoryResponse.setName(product.getCategory().getName());

        response.setCategory(categoryResponse);

        return ResponseEntity.ok(response);
    }
}
