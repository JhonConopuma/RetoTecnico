package com.api.springbootfirstapp.services;

import com.api.springbootfirstapp.models.Producto;
import com.api.springbootfirstapp.repositories.ProductoRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    ProductoRepositories productoRepositories;


    public List<Producto> getProductoList(Producto producto){

        List<Producto> productoList = productoRepositories.getProductoList(producto);

        return productoList;
    }
}
