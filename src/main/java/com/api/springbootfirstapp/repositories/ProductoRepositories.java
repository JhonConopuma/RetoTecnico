package com.api.springbootfirstapp.repositories;

import com.api.springbootfirstapp.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class ProductoRepositories {

    @Autowired
    JdbcTemplate jdbcTemplate;



    public List<Producto> getProductoList(Producto producto) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("marco")
                .withCatalogName("MI_PAQUETE")  // Especifica el nombre del paquete
                .withProcedureName("SP_INSERTANDLISTPRODUCTS")  // Especifica el nombre del procedimiento almacenado
                .declareParameters(new SqlParameter[]{
                        new SqlParameter("idProducto", Types.INTEGER),
                        new SqlParameter("nombre", Types.VARCHAR),
                        new SqlParameter("fecRegistro", Types.DATE),

                        new SqlOutParameter("cursor", Types.REF_CURSOR),
                        new SqlOutParameter("codigoRespuesta", Types.INTEGER),
                        new SqlOutParameter("mensajeRespuesta", Types.VARCHAR)
                })
                .returningResultSet("cursor", new RowMapper<Producto>() {
                    @Override
                    public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Producto producto = new Producto();
                        producto.setIdProducto(rs.getInt("idProducto"));
                        producto.setNombre(rs.getString("nombre"));
                        producto.setFecRegistro(rs.getDate("fecRegistro"));

                        return producto;
                    }
                });
        // Obtener parámetros del objeto Producto
        int idProducto = producto.getIdProducto();
        String nombre = producto.getNombre();
        LocalDate fechaActual = LocalDate.now();

        // Crear un MapSqlParameterSource para pasar los parámetros al procedimiento almacenado
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
                .addValue("idProducto", idProducto)
                .addValue("nombre", nombre)
                .addValue("fecRegistro", fechaActual);

        // Ejecutar el procedimiento almacenado y obtener el resultado
        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);

        // Obtener la lista de productos del resultado
        List<Producto> productos = (List<Producto>) results.get("cursor");

        return productos;
    }
}
