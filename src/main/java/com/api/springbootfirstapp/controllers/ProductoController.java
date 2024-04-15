package com.api.springbootfirstapp.controllers;


import com.api.springbootfirstapp.models.Producto;
import com.api.springbootfirstapp.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @PostMapping(value = "/get/producto")
    @ResponseBody
    public List<Producto> getProductoList(@RequestBody Producto producto){
        List<Producto> productoList = productoService.getProductoList(producto);

        return productoList;
    }


}
