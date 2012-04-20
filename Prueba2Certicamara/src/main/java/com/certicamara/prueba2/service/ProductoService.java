package com.certicamara.prueba2.service;

import java.util.List;
import java.util.UUID;

import com.certicamara.prueba2.domain.Producto;
import com.certicamara.prueba2.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository bodegaRepository;
	
	
	public Producto create(Producto bodega) {
		bodega.setId(UUID.randomUUID().toString());
		
		return bodegaRepository.save(bodega);
	}
	
	public Producto read(Producto bodega) {
		return bodega;
	}
	
	public List<Producto> readAll() {
		return bodegaRepository.findAll();
	}
	
	public Producto update(Producto producto) {
		Producto productoActualizar = bodegaRepository.findByNombre(producto.getNombre());

		if (productoActualizar == null) {
			return null;
		}

		productoActualizar.setNombre(producto.getNombre());
		productoActualizar.setPrecio(producto.getPrecio());
                productoActualizar.setCantidad(producto.getCantidad());

		return bodegaRepository.save(productoActualizar);
	}

	public Producto update(Producto poducto, String nombreAnterior) {
		Producto productoActualizar = bodegaRepository.findByNombre(nombreAnterior);

		if (productoActualizar == null) {
			return null;
		}

		productoActualizar.setNombre(poducto.getNombre());
		productoActualizar.setPrecio(poducto.getPrecio());
                productoActualizar.setCantidad(poducto.getCantidad());

		return bodegaRepository.save(productoActualizar);
	}
	
	public Boolean delete(Producto producto) {
		Producto productoBorrar = bodegaRepository.findByNombre(producto.getNombre());
		
		if (productoBorrar == null) {
			return false;
		}
		
		bodegaRepository.delete(productoBorrar);
		return true;
	}
}
