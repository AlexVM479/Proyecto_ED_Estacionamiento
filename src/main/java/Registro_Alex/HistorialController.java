package Registro_Alex;

import com.mycompany.sistemaestacionamiento.Conexion;
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistorialController {

    public Registro_Alex.Conductor buscarConductorPorCodigo(int codigo) {
        Registro_Alex.Conductor conductor = null;
        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT * FROM CONDUCTOR WHERE Codigo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                conductor = new Registro_Alex.Conductor(
                    rs.getInt("Codigo"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("correo"),
                    rs.getString("celular")
                );
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar conductor: " + e.getMessage());
        }
        return conductor;
    }
    
    public Registro_Alex.HistorialEstacionamiento buscarHistorialPorPlaca(String placa) {
    Registro_Alex.HistorialEstacionamiento historial = null;
    try (Connection conn = Conexion.conectar()) {
        String sql = "SELECT h.Placa, v.tipo, e.lugar, h.fecha, h.hora_entrada, h.hora_salida, " +
                     "c.nombre, c.apellidos, c.Codigo " +
                     "FROM HISTORIAL_ESTACIONAMIENTO h " +
                     "JOIN VEHICULO v ON h.Placa = v.Placa " +
                     "JOIN ESTACIONAMIENTO e ON h.ID_Estacionamiento = e.ID_Estacionamiento " +
                     "JOIN CONDUCTOR c ON h.Codigo_Conductor = c.Codigo " +
                     "WHERE h.Placa = ? AND h.hora_salida IS NULL"; // Solo el último ingreso
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, placa);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            historial = new Registro_Alex.HistorialEstacionamiento();
            historial.setPlaca(rs.getString("Placa"));
            historial.setTipoVehiculo(rs.getString("tipo"));
            historial.setZona(rs.getString("lugar"));
            historial.setFecha(rs.getDate("fecha").toLocalDate());
            historial.setHoraEntrada(rs.getTime("hora_entrada").toLocalTime());
            historial.setHoraSalida(rs.getTime("hora_salida") != null ? rs.getTime("hora_salida").toLocalTime() : null);
            historial.setNombreConductor(rs.getString("nombre"));
            historial.setApellidosConductor(rs.getString("apellidos"));
            historial.setCodigoConductor(rs.getInt("Codigo"));
        }
    } catch (Exception e) {
        System.out.println("❌ Error al buscar historial por placa: " + e.getMessage());
    }
    return historial;
}
    
    public boolean tieneSalidaPendiente(String placa) {
    boolean tieneSalidaPendiente = false;
    try (Connection conn = Conexion.conectar()) {
        String sql = "SELECT * FROM HISTORIAL_ESTACIONAMIENTO WHERE Placa = ? AND hora_salida IS NULL";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, placa);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            tieneSalidaPendiente = true; // Si encontramos un historial sin hora de salida, está pendiente
        }
    } catch (Exception e) {
        System.out.println("❌ Error al verificar la salida pendiente: " + e.getMessage());
    }
    return tieneSalidaPendiente;
}

    public Vehiculo buscarVehiculoPorPlaca(String placa) {
    Vehiculo vehiculo = null;
    try (Connection conn = Conexion.conectar()) {
        String sql = "SELECT * FROM VEHICULO WHERE Placa = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, placa);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            vehiculo = new Vehiculo();
            vehiculo.setPlaca(rs.getString("Placa"));
            vehiculo.setTipo(rs.getString("tipo"));
            // Aquí podrías añadir más detalles si necesitas.
        }
    } catch (Exception e) {
        System.out.println("❌ Error al buscar vehículo: " + e.getMessage());
    }
    return vehiculo;
}

}