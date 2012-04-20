package com.certicamara.prueba2.controller;

import com.certicamara.prueba2.domain.Proveedor;
import com.certicamara.prueba2.dto.ProveedorListDto;
import com.certicamara.prueba2.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService service;

    @RequestMapping
    public String getBodegasPage() {
        return "proveedores";
    }

    @RequestMapping(value = "/records")
    public
    @ResponseBody
    ProveedorListDto getProveedores() {

        ProveedorListDto userListDto = new ProveedorListDto();
        userListDto.setProveedores(service.readAll());
        return userListDto;
    }

    @RequestMapping(value = "/get")
    public
    @ResponseBody
    Proveedor get(@RequestBody Proveedor proveedor) {
        return service.read(proveedor);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    Proveedor create(
            @RequestParam String nombre,
            @RequestParam String direccion,
            @RequestParam String telefono,
            @RequestParam String mail) {
        Proveedor newProveedor = new Proveedor();
        newProveedor.setNombre(nombre);
        newProveedor.setDireccion(direccion);
        newProveedor.setTelefono(telefono);
        newProveedor.setMail(mail);

        return service.create(newProveedor);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Proveedor update(
            @RequestParam String nombre,
            @RequestParam String direccion,
            @RequestParam String telefono,
            @RequestParam String mail,
            @RequestParam String nombreAnterior) {

        Proveedor proveedor = new Proveedor();

        proveedor.setNombre(nombre);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setMail(mail);

        return service.update(proveedor,nombreAnterior);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean delete(
            @RequestParam String nombre) {

        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombre);

        return service.delete(proveedor);
    }
}
