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
import org.springframework.web.bind.annotation.RestController;

import com.mercadona.springboot.backend.apirest.models.entities.Supplier;
import com.mercadona.springboot.backend.apirest.models.services.ISupplierService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class SupplierRestController {
	
	@Autowired
	private ISupplierService supplierService;
	
	@GetMapping("/suppliers")
	public List<Supplier> index() {
		return supplierService.findAll();
	}
	
	@GetMapping("/suppliers/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		Supplier supplier = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			supplier = supplierService.findById(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar la consulta en la base de datos.");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		if (supplier == null) {
			response.put("message", "El proveedor con ID " .concat(id.toString().concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);
	}
	
	@PostMapping("/suppliers")
	public ResponseEntity<?> create(@RequestBody Supplier supplier){ //RequestBody because it is in a JSON format.
		Supplier newSupplier = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			newSupplier = supplierService.save(supplier);
		} catch (DataAccessException e) {
			response.put("message", "Error al realizar el insert en la base de datos.");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El proveedor ha sido creado con éxito!");
		response.put("supplier", newSupplier);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/suppliers/{id}")
	public ResponseEntity<?> update(@RequestBody Supplier supplier, @PathVariable Long id){ //RequestBody because it is in a JSON format.
		Supplier currentSupplier = supplierService.findById(id);
		Supplier updatedSupplier = null;
		Map<String, Object> response = new HashMap<>();
		
		if (currentSupplier == null) {
			response.put("message", "Error: No se pudo editar el proveedor porque el proveedor con ID " .concat(id.toString().concat(" no existe en la base de datos.")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentSupplier.setCode(supplier.getCode());
			currentSupplier.setName(supplier.getName());
			//currentSupplier.setProducts(supplier.getProducts());
			updatedSupplier = supplierService.save(currentSupplier);
		} catch (DataAccessException e) {
			response.put("message", "Error al actualizar el proveedor en la base de datos.");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("message", "El proveedor ha sido actualizado con éxito!");
		response.put("supplier", updatedSupplier);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/suppliers/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){ 
		Map<String, Object> response = new HashMap<>();
		
		try {
			supplierService.delete(id);
		} catch (DataAccessException e) {
			response.put("message", "Error al eliminar el proveedor de la base de datos. ");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El proveedor ha sido eliminado con éxito");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
