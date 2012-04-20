package com.certicamara.prueba2.controller;

import com.certicamara.prueba2.domain.Bodega;
import com.certicamara.prueba2.dto.BodegaListDto;
import com.certicamara.prueba2.service.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/bodegas")
public class BodegaController {

    @Autowired
    private BodegaService service;

    @RequestMapping
    public String getBodegasPage() {
        return "bodegas";
    }

    @RequestMapping(value = "/records")
    public
    @ResponseBody
    BodegaListDto getBodegas() {

        BodegaListDto userListDto = new BodegaListDto();
        userListDto.setBodegas(service.readAll());
        return userListDto;
    }

    @RequestMapping(value = "/get")
    public
    @ResponseBody
    Bodega get(@RequestBody Bodega bodega) {
        return service.read(bodega);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    Bodega create(
            @RequestParam String codigo,
            @RequestParam String direccion) {
        Bodega newBodega = new Bodega();
        newBodega.setCodigo(codigo);
        newBodega.setDireccion(direccion);

        return service.create(newBodega);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    Bodega update(
            @RequestParam String codigo,
            @RequestParam String direccion,
            @RequestParam String codigoAnterior) {

        Bodega bodega = new Bodega();

        bodega.setCodigo(codigo);
        bodega.setDireccion(direccion);

        return service.update(bodega,codigoAnterior);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean delete(
            @RequestParam String codigo) {

        Bodega bodega = new Bodega();
        bodega.setCodigo(codigo);

        return service.delete(bodega);
    }
}
