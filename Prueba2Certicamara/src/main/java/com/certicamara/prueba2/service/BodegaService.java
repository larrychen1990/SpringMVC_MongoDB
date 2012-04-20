package com.certicamara.prueba2.service;

import java.util.List;
import java.util.UUID;

import com.certicamara.prueba2.domain.Bodega;
import com.certicamara.prueba2.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BodegaService {

	@Autowired
	private BodegaRepository bodegaRepository;
	

        public Bodega findByCodigo(String codigo){
            return bodegaRepository.findByCodigo(codigo);
        }

	public Bodega create(Bodega bodega) {
		bodega.setId(UUID.randomUUID().toString());
		
		return bodegaRepository.save(bodega);
	}
	
	public Bodega read(Bodega bodega) {
		return bodega;
	}
	
	public List<Bodega> readAll() {
		return bodegaRepository.findAll();
	}
	
	public Bodega update(Bodega bodega) {
		Bodega bodegaActualizar = bodegaRepository.findByCodigo(bodega.getCodigo());

		if (bodegaActualizar == null) {
			return null;
		}

		bodegaActualizar.setCodigo(bodega.getCodigo());
		bodegaActualizar.setDireccion(bodega.getDireccion());

		return bodegaRepository.save(bodegaActualizar);
	}

	public Bodega update(Bodega bodega, String codigoAnterior) {
		Bodega bodegaActualizar = bodegaRepository.findByCodigo(codigoAnterior);
		
		if (bodegaActualizar == null) {
			return null;
		}
		
		bodegaActualizar.setCodigo(bodega.getCodigo());
		bodegaActualizar.setDireccion(bodega.getDireccion());
		
		return bodegaRepository.save(bodegaActualizar);
	}
	
	public Boolean delete(Bodega bodega) {
		Bodega bodegaBorrar = bodegaRepository.findByCodigo(bodega.getCodigo());
		
		if (bodegaBorrar == null) {
			return false;
		}
		
		bodegaRepository.delete(bodegaBorrar);
		return true;
	}
}
