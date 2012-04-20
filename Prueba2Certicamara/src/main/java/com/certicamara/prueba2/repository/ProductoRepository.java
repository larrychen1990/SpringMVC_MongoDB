package com.certicamara.prueba2.repository;

import com.certicamara.prueba2.domain.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepository extends MongoRepository<Producto, String> {
	
	Producto findByNombre(String nombre);
}
