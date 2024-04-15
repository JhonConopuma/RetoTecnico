package com.api.springbootfirstapp;

import com.api.springbootfirstapp.models.Producto;
import com.api.springbootfirstapp.repositories.ProductoRepositories;
import com.api.springbootfirstapp.services.ProductoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SpringBootFirstAppApplicationTests {

	@Mock
	private ProductoRepositories productoRepositories;

	@InjectMocks
	private ProductoService productoService;


	@DisplayName("Prueba 1")
	@Test
	public void test1() throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		Date fecha = new Date(dateFormat.parse("2024-04-14T10:00:00").getTime());

		Producto producto1 = new Producto();
		producto1.setIdProducto(9);
		producto1.setNombre("PAD");
		producto1.setFecRegistro(fecha);


		// Crear una lista de productos ficticia para simular el resultado del repositorio
		List<Producto> listaProductoEsperado = new ArrayList<>();
		listaProductoEsperado.add(producto1);

		// Mockear el repositorio para que devuelva la lista ficticia
		when(productoRepositories.getProductoList(producto1)).thenReturn(listaProductoEsperado);

		// Llamar al método del servicio que queremos probar
		final List<Producto> resultado = productoService.getProductoList(producto1);

		// Verificar si el producto1 está presente en la lista de resultados
		assertTrue(resultado.contains(producto1), "El producto1 no está presente en la lista de resultados");

	}

	@DisplayName("Prueba 2")
	@Test
	public void test2() throws ParseException {

		// Configuración del comportamiento simulado del repositorio
		when(productoRepositories.getProductoList(any(Producto.class))).thenReturn(Collections.emptyList());

		// Ejecución de la prueba
		List<Producto> result = productoRepositories.getProductoList(new Producto());

		// Verificación
		assertTrue(result.isEmpty());
	}
}
