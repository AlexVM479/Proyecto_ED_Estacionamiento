package Registro_Alex;

import com.mycompany.sistemaestacionamiento.Conexion;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EstacionamientoController {

    // Registrar el ingreso de un vehículo
    public boolean registrarIngreso(String placa, int codigoConductor, String tipoVehiculo, String zona, String lugar) {
        boolean exito = false;
        try (Connection conn = Conexion.conectar()) {
            // Buscar el ID del estacionamiento a partir del lugar exacto
            String sql = "SELECT * FROM ESTACIONAMIENTO WHERE lugar = ? AND estado = 0";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, lugar);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idEstacionamiento = rs.getInt("ID_Estacionamiento");
                String insertSql = "INSERT INTO HISTORIAL_ESTACIONAMIENTO (Placa, Codigo_Conductor, ID_Estacionamiento, fecha, hora_entrada) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setString(1, placa);
                insertStmt.setInt(2, codigoConductor);
                insertStmt.setInt(3, idEstacionamiento);
                insertStmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
                insertStmt.setTime(5, java.sql.Time.valueOf(LocalTime.now()));
                int rows = insertStmt.executeUpdate();
                
                if (rows > 0) {
                    // Actualizar el estado del estacionamiento
                    String updateSql = "UPDATE ESTACIONAMIENTO SET estado = 1 WHERE ID_Estacionamiento = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                    updateStmt.setInt(1, idEstacionamiento);
                    updateStmt.executeUpdate();
                    
                    exito = true; // Ingreso exitoso
                }
            }
        } catch (Exception e) {
            System.out.println("Error al registrar el ingreso: " + e.getMessage());
        }
        return exito;
    }

    // Registrar la salida de un vehículo
    public boolean registrarSalida(String placa) {
        boolean exito = false;
        try (Connection conn = Conexion.conectar()) {
            // Obtener el historial de ingreso
            String sql = "SELECT * FROM HISTORIAL_ESTACIONAMIENTO WHERE Placa = ? AND hora_salida IS NULL";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                int idHistorial = rs.getInt("ID_Historial");
                int idEstacionamiento = rs.getInt("ID_Estacionamiento");
                Time horaIngreso = rs.getTime("hora_entrada");
                Time horaSalida = java.sql.Time.valueOf(LocalTime.now());
                
                // Actualizar la hora de salida
                String updateSql = "UPDATE HISTORIAL_ESTACIONAMIENTO SET hora_salida = ? WHERE ID_Historial = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setTime(1, horaSalida);
                updateStmt.setInt(2, idHistorial);
                updateStmt.executeUpdate();
                
                // Actualizar el estado del estacionamiento
                String updateEstacionamientoSql = "UPDATE ESTACIONAMIENTO SET estado = 0 WHERE ID_Estacionamiento = ?";
                PreparedStatement updateEstacionamientoStmt = conn.prepareStatement(updateEstacionamientoSql);
                updateEstacionamientoStmt.setInt(1, idEstacionamiento);
                updateEstacionamientoStmt.executeUpdate();
                
                // Calcular el tiempo total usando Duration
                LocalTime horaIngresoLocal = horaIngreso.toLocalTime();
                LocalTime horaSalidaLocal = horaSalida.toLocalTime();
                long diffMinutes = Duration.between(horaIngresoLocal, horaSalidaLocal).toMinutes();
                
                System.out.println("Tiempo total de estacionamiento: " + diffMinutes + " minutos.");
                exito = true;
            }
        } catch (Exception e) {
            System.out.println("Error al registrar la salida: " + e.getMessage());
        }
        return exito;
    }
    
    // Método para buscar un estacionamiento disponible
    public Estacionamiento buscarEstacionamientoDisponible(String lugar) {
        Estacionamiento estacionamiento = null;
        
        try (Connection conn = Conexion.conectar()) {
            // Buscar un estacionamiento disponible en el lugar especificado (Odontología o Comedor)
            String sql = "SELECT * FROM ESTACIONAMIENTO WHERE estado = 0 AND lugar LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, lugar + "%"); // Se asume que 'lugar' contiene la palabra clave (Odontología o Comedor)
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                // Si hay un estacionamiento libre, lo creamos y lo devolvemos
                int idEstacionamiento = rs.getInt("ID_Estacionamiento");
                String lugarEstacionamiento = rs.getString("lugar");
                int estado = rs.getInt("estado");
                
                // Crear un objeto Estacionamiento con los datos obtenidos
                estacionamiento = new Estacionamiento(idEstacionamiento, lugarEstacionamiento, estado);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar estacionamiento disponible: " + e.getMessage());
        }
        
        return estacionamiento; // Devuelve el estacionamiento disponible o null si no se encontró
    }
    
    // Obtener los estados de todos los espacios de odontología
    public List<Estacionamiento> obtenerEstacionamientosOdontologia() {
        List<Estacionamiento> estacionamientos = new ArrayList<>();
        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT * FROM ESTACIONAMIENTO WHERE lugar LIKE 'Odontologia%'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Estacionamiento espacio = new Estacionamiento();
                espacio.setId(rs.getInt("id"));
                espacio.setEstado(rs.getInt("estado"));
                espacio.setLugar(rs.getString("lugar"));
                estacionamientos.add(espacio);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener estacionamientos: " + e.getMessage());
        }
        return estacionamientos;
    }
}