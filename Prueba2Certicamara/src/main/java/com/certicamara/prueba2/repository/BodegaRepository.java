package com.certicamara.prueba2.repository;

import com.certicamara.prueba2.domain.Bodega;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BodegaRepository extends MongoRepository<Bodega, String> {
	
	Bodega findByCodigo(String codigo);
}
