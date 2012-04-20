package com.certicamara.prueba2.dto;

import java.util.List;

import com.certicamara.prueba2.domain.Producto;

public class ProductoListDto {

    private List<Producto> productos;

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

   

}
