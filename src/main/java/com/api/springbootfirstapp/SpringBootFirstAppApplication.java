package com.api.springbootfirstapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class SpringBootFirstAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFirstAppApplication.class, args);

		String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/orcl";
		String usuario = "marco";
		String contraseña = "12345";

		try (Connection connection = DriverManager.getConnection(jdbcUrl, usuario, contraseña)) {
			System.out.println("¡Conexión exitosa!");
		} catch (SQLException e) {
			System.err.println("Error al conectar a la base de datos: " + e.getMessage());
		}


	}

}
