package Registro_Alex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.sistemaestacionamiento.Conexion;

public class VehiculoController {

    // Método para buscar un vehículo por placa en la base de datos
    public Vehiculo buscarVehiculoPorPlaca(String placa) {
        Vehiculo vehiculo = null;
        String sql = "SELECT * FROM VEHICULO WHERE Placa = ?";
        
        try (Connection conn = Conexion.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                vehiculo = new Vehiculo(
                        rs.getString("Placa"),
                        rs.getString("tipo")
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al buscar vehículo: " + e.getMessage());
        }
        
        return vehiculo;
    }

    // Método para registrar un nuevo vehículo en la base de datos
    public boolean registrarVehiculo(String placa, String tipo) {
        if (buscarVehiculoPorPlaca(placa) != null) {
            return false; // Si el vehículo ya existe, no lo registramos
        }
        
        String sql = "INSERT INTO VEHICULO (Placa, tipo) VALUES (?, ?)";
        
        try (Connection conn = Conexion.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, placa);
            stmt.setString(2, tipo);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Si se insertó correctamente, devuelve true
        } catch (SQLException e) {
            System.out.println("❌ Error al registrar vehículo: " + e.getMessage());
            return false;
        }
    }

    // Método para actualizar el tipo de un vehículo en la base de datos
    public boolean actualizarTipoVehiculo(String placa, String nuevoTipo) {
        String sql = "UPDATE VEHICULO SET tipo = ? WHERE Placa = ?";
        
        try (Connection conn = Conexion.conectar(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nuevoTipo);
            stmt.setString(2, placa);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Si se actualizó correctamente, devuelve true
        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar tipo de vehículo: " + e.getMessage());
            return false;
        }
    }

    // Método para obtener todos los vehículos en la base de datos
    public List<Vehiculo> obtenerTodosVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        String sql = "SELECT * FROM VEHICULO";
        
        try (Connection conn = Conexion.conectar(); 
             Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Vehiculo vehiculo = new Vehiculo(
                        rs.getString("Placa"),
                        rs.getString("tipo")
                );
                vehiculos.add(vehiculo);
            }
        } catch (SQLException e) {
            System.out.println("❌ Error al obtener todos los vehículos: " + e.getMessage());
        }
        
        return vehiculos;
    }
    
    public void registrarVehiculoNuevo(Vehiculo vehiculo) {
    try (Connection conn = Conexion.conectar()) {
        String sql = "INSERT INTO VEHICULO (Placa, tipo) VALUES (?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, vehiculo.getPlaca());
        stmt.setString(2, vehiculo.getTipo()); // Si necesitas registrar el tipo de vehículo
        stmt.executeUpdate();
    } catch (Exception e) {
        System.out.println("❌ Error al registrar vehículo: " + e.getMessage());
    }
}

}