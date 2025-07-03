package com.mycompany.sistemaestacionamiento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/gestion_estacionamiento"; // cambia si tu BD tiene otro nombre
    private static final String USUARIO = "root"; // tu usuario de MySQL
    private static final String CLAVE = "root"; // tu contraseña de MySQL

    public static Connection conectar() {
        try {
            // Desde JDBC 4.0 no es obligatorio, pero puedes dejarlo
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
            System.out.println("✅ Conexión exitosa a la base de datos");
            return conexion;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
