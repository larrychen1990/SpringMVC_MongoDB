package com.certicamara.prueba2.controller;

import com.certicamara.prueba2.domain.Producto;
import com.certicamara.prueba2.dto.BodegaListDto;
import com.certicamara.prueba2.dto.ProductoListDto;
import com.certicamara.prueba2.dto.ProveedorListDto;
import com.certicamara.prueba2.service.BodegaService;
import com.certicamara.prueba2.service.ProductoService;
import com.certicamara.prueba2.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @RequestMapping
    public String getBodegasPage() {
        return "productos";
    }

    @Autowired
    private BodegaService serviceBodega;

    @Autowired
    private ProveedorService serviceProveedor;

    @RequestMapping(value = "/records")
    public
    @ResponseBody
    ProductoListDto getProductos() {

        ProductoListDto productosListDto = new ProductoListDto();
        productosListDto.setProductos(service.readAll());
        return productosListDto;
    }

    @RequestMapping(value = "/bodegas")
    public
    @ResponseBody
    BodegaListDto getBodegas() {

        BodegaListDto userListDto = new BodegaListDto();
        userListDto.setBodegas(serviceBodega.readAll());
        return userListDto;
    }

    @RequestMapping(value = "/proveedores")
    public
    @ResponseBody
    ProveedorListDto getProveedores() {

        ProveedorListDto userListDto = new ProveedorListDto();
        userListDto.setProveedores(serviceProveedor.readAll());
        return userListDto;
    }

    @RequestMapping(value = "/get")
    public
    @ResponseBody
    Producto get(@RequestBody Producto productos) {
        return service.read(productos);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    Producto create(
            @RequestParam String nombre,
            @RequestParam Float precio,
            @RequestParam Integer cantidad,
            @RequestParam String nombreProveedor,
            @RequestParam String codigoBodega
            ) {
        Producto newProducto = new Producto();
        newProducto.setNombre(nombre);
        newProducto.setPrecio(precio);
        newProducto.setCantidad(cantidad);
        newProducto.setProveedor(serviceProveedor.findByNombre(nombreProveedor));
        newProducto.setBodega( serviceBodega.findByCodigo(codigoBodega) );

        return service.create(newProducto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Producto update(
            @RequestParam String nombre,
            @RequestParam Float precio,
            @RequestParam Integer cantidad,
            @RequestParam String nombreProveedor,
            @RequestParam String codigoBodega,
            @RequestParam String nombreAnterior) {

        Producto producto = new Producto();

        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);
        producto.setProveedor(serviceProveedor.findByNombre(nombreProveedor));
        producto.setBodega( serviceBodega.findByCodigo(codigoBodega) );

        return service.update(producto,nombreAnterior);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean delete(
            @RequestParam String nombre) {

        Producto producto = new Producto();
        producto.setNombre(nombre);

        return service.delete(producto);
    }
}
