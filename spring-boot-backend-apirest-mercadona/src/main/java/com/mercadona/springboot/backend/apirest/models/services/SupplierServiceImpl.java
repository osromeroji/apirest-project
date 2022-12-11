package com.mercadona.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mercadona.springboot.backend.apirest.models.dao.ISupplierDao;
import com.mercadona.springboot.backend.apirest.models.entities.Supplier;

@Service
public class SupplierServiceImpl implements ISupplierService{
	
	@Autowired
	private ISupplierDao supplierDao;

	@Override
	@Transactional(readOnly = true)
	public List<Supplier> findAll() {
		return (List<Supplier>) supplierDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Supplier findById(Long id) {
		return supplierDao.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Supplier findByCode(String code) {
		return supplierDao.findTopByCode(code);
	}

	@Override
	@Transactional
	public Supplier save(Supplier supplier) {
		return supplierDao.save(supplier);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		supplierDao.deleteById(id);
	}

}
