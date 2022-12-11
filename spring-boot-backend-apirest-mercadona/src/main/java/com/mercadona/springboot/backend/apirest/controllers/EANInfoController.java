package com.mercadona.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mercadona.springboot.backend.apirest.models.entities.EanCode;
import com.mercadona.springboot.backend.apirest.models.entities.Product;
import com.mercadona.springboot.backend.apirest.models.entities.Supplier;
import com.mercadona.springboot.backend.apirest.models.services.IProductService;
import com.mercadona.springboot.backend.apirest.models.services.ISupplierService;

@Controller
@RequestMapping("/api")
public class EANInfoController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ISupplierService supplierService;

	@GetMapping("/search/{ean}")
	public ResponseEntity<?> getEanInfo(@PathVariable String ean) {
		Map<String, Object> response = new HashMap<>();

		if (ean.length() != 13) {
			response.put("message", "Los código EAN deben estar formados por 13 dígitos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		try {
			Long.valueOf(ean);
		} catch (Exception e) {
			response.put("message", "Los código EAN deben ser numéricos.");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		String supplierCode = ean.substring(0, 7);
		String productCode = ean.substring(7, 12);

		Supplier filteredSupplier = supplierService.findByCode(supplierCode);
		Product filteredProduct = productService.findByCode(productCode);

		if (filteredSupplier == null) {
			response.put("message", "El proveedor del EAN especificado no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (filteredProduct == null) {
			response.put("message", "El producto del EAN especificado no existe en la base de datos.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		EanCode eanCode = new EanCode();
		int destination = Integer.valueOf(ean.substring(ean.length() - 1));
		
		eanCode.setSupplier(filteredSupplier);
		eanCode.setProduct(filteredProduct);
		eanCode.setDestination(getDestinationFromId(destination));

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
				destination = "No existe ningún destino con el código 7.";
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
