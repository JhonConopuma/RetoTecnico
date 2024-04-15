package com.api.springbootfirstapp.models;

import lombok.Data;

import java.util.Date;


@Data
public class Producto {
    private Integer idProducto;
    private String nombre;
    private Date fecRegistro;

}
