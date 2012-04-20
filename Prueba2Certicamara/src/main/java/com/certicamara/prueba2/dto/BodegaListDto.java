package com.certicamara.prueba2.dto;

import java.util.List;

import com.certicamara.prueba2.domain.Bodega;

public class BodegaListDto {

	private List<Bodega> bodegas;

	public List<Bodega> getBodegas() {
		return bodegas;
	}

	public void setBodegas(List<Bodega> users) {
		this.bodegas = users;
	}
}
