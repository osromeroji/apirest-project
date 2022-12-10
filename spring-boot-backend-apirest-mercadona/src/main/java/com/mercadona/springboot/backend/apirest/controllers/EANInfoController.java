package com.mercadona.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mercadona.springboot.backend.apirest.models.entities.EanCode;
import com.mercadona.springboot.backend.apirest.models.entities.Product;
import com.mercadona.springboot.backend.apirest.models.entities.Supplier;
import com.mercadona.springboot.backend.apirest.models.services.IProductService;
import com.mercadona.springboot.backend.apirest.models.services.ISupplierService;

@Controller
@RequestMapping("/api")
public class EANInfoController {

	private static final String EXAMPLE2_VIEW = "example2";
	@Autowired
	private IProductService productService;

	@Autowired
	private ISupplierService supplierService;

	// localhost:8080/example2/request2/OSCAR
	@GetMapping("/search/{ean}")
	public ResponseEntity<?> request2(@PathVariable String ean) {
		Map<String, Object> response = new HashMap<>();

		if (ean.length() != 13) {
			response.put("message", "Los código EAN deben estar formados por 13 dígitos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		String supplierCode = "";
		String productCode = "";
		int destination = 0;

		EanCode eanCode = new EanCode();

		try {
			Long.valueOf(ean);
		} catch (Exception e) {
			response.put("message", "Los código EAN deben ser numéricos.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		supplierCode = ean.substring(0, 7);
		productCode = ean.substring(7, 12);
		destination = Integer.valueOf(ean.substring(ean.length() - 1));
		
		List<Supplier> filteredSuppliers = supplierService.findByCode(supplierCode);
		List<Product> filteredProducts = productService.findByCode(productCode);

		if (filteredSuppliers.size() == 0) {
			response.put("message", "El proveedor del EAN especificado no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (filteredProducts.size() == 0) {
			response.put("message", "El producto del EAN especificado no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		eanCode.setSupplier(filteredSuppliers.get(0));
		eanCode.setProduct(filteredProducts.get(0));
		eanCode.setDestination(getDestinationFromId(destination));

		response.put("message", "El producto del EAN especificado no existe en la base de datos.");
		return new ResponseEntity<EanCode>(eanCode, HttpStatus.OK);
	}

	private String getDestinationFromId(int destinationId) {
		String destination = "";

		switch (destinationId) {
		case 0: {
			destination = "Colmenas";
			break;
		}
		case 1, 2, 3, 4, 5: {
			destination = "Tiendas Mercadona España";
			break;
		}
		case 6: {
			destination = "Tiendas Mercadona Portugal";
			break;
		}
		case 7: {
			break;
		}
		case 8: {
			destination = "Almacenes";
			break;
		}
		case 9: {
			destination = "Oficinas";
			break;
		}
		}
		
		return destination;

	}
}
