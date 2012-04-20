package com.certicamara.prueba2.repository;

import com.certicamara.prueba2.domain.Proveedor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProveedorRepository extends MongoRepository<Proveedor, String> {
	
	Proveedor findByNombre(String nombre);
}
