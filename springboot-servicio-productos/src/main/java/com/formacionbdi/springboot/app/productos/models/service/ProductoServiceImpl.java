package com.formacionbdi.springboot.app.productos.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.repository.ProductoRepository;

@Service 

public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private ProductoRepository productoRepo;

	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {
		return (List<Producto>)productoRepo.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Producto findById(Long id) {
		return productoRepo.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		return productoRepo.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		productoRepo.deleteById(id);
		
	}
	

}
