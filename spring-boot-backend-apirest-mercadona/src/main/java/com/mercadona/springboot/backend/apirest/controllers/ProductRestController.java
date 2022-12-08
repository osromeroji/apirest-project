package com.mercadona.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mercadona.springboot.backend.apirest.models.entities.Product;
import com.mercadona.springboot.backend.apirest.models.services.IProductService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ProductRestController {
	
	@Autowired
	private IProductService productService;

	@GetMapping("/products")
	public List<Product> index() {
		return productService.findAll();
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Product product = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			product = productService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos.");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		if (product == null) {
			response.put("message", "El producto con ID " .concat(id.toString().concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("/products")
	public ResponseEntity<?> create(@RequestBody Product product){ //RequestBody because it is in a JSON format.
		Product newProduct = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			newProduct = productService.save(product);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos.");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El producto ha sido creado con éxito!");
		response.put("product", newProduct);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id){ //RequestBody because it is in a JSON format.
		Product currentProduct = productService.findById(id);
		Product updatedProduct = null;
		Map<String, Object> response = new HashMap<>();
		
		if (currentProduct == null) {
			response.put("message", "Error: No se pudo editar el producto porque el rpoducto con ID " .concat(id.toString().concat(" no existe en la base de datos.")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentProduct.setEan(product.getEan());
			updatedProduct = productService.save(currentProduct);
		} catch (DataAccessException e) {
			response.put("message", "Error al actualizar el producto en la base de datos.");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El producto ha sido actualizado con éxito!");
		response.put("product", updatedProduct);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){ 
		Map<String, Object> response = new HashMap<>();
		
		try {
			productService.delete(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar el producto de la base de datos. ");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El producto ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
