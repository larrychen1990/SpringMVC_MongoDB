package com.certicamara.prueba2.service;

import java.util.List;
import java.util.UUID;

import com.certicamara.prueba2.domain.Proveedor;
import com.certicamara.prueba2.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository bodegaRepository;


	
	public Proveedor findByNombre(String nombreproveedor) {
		return bodegaRepository.findByNombre(nombreproveedor);
	}

	public Proveedor create(Proveedor proveedor) {
		proveedor.setId(UUID.randomUUID().toString());
		
		return bodegaRepository.save(proveedor);
	}
	
	public Proveedor read(Proveedor proveedor) {
		return proveedor;
	}
	
	public List<Proveedor> readAll() {
		return bodegaRepository.findAll();
	}
	
	public Proveedor update(Proveedor proveedor) {
		Proveedor proveedorActualizar = bodegaRepository.findByNombre(proveedor.getNombre());

		if (proveedorActualizar == null) {
			return null;
		}

		proveedorActualizar.setNombre(proveedor.getNombre());
		proveedorActualizar.setDireccion(proveedor.getDireccion());
                proveedorActualizar.setMail(proveedor.getMail());
                proveedorActualizar.setTelefono(proveedor.getTelefono());

		return bodegaRepository.save(proveedorActualizar);
	}

	public Proveedor update(Proveedor proveedor, String nombreAnterior) {
		Proveedor proveedorActualizar = bodegaRepository.findByNombre(nombreAnterior);

		if (proveedorActualizar == null) {
			return null;
		}

		proveedorActualizar.setNombre(proveedor.getNombre());
		proveedorActualizar.setDireccion(proveedor.getDireccion());
                proveedorActualizar.setMail(proveedor.getMail());
                proveedorActualizar.setTelefono(proveedor.getTelefono());

		return bodegaRepository.save(proveedorActualizar);
	}
	
	public Boolean delete(Proveedor proveedor) {
		Proveedor proveedorBorrar = bodegaRepository.findByNombre(proveedor.getNombre());
		
		if (proveedorBorrar == null) {
			return false;
		}
		
		bodegaRepository.delete(proveedorBorrar);
		return true;
	}
}
