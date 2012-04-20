package com.certicamara.prueba2.dto;

import java.util.List;

import com.certicamara.prueba2.domain.Proveedor;

public class ProveedorListDto {

	private List<Proveedor> proveedores;

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<Proveedor> proveedores) {
        this.proveedores = proveedores;
    }

}
