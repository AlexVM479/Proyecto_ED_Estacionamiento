package control_busqueda;

import com.mycompany.sistemaestacionamiento.Conexion;
import java.sql.Connection;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HistorialController {

    public Conductor buscarConductorPorCodigo(int codigo) {
        Conductor conductor = null;
        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT * FROM CONDUCTOR WHERE Codigo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                conductor = new Conductor(
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

    public List<HistorialEstacionamiento> buscarHistorialPorCodigo(int codigo) {
        List<HistorialEstacionamiento> historial = new ArrayList<>();
        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT h.Placa, v.tipo, e.lugar, h.fecha, h.hora_entrada, h.hora_salida, " +
                         "c.Codigo, c.nombre, c.apellidos " +
                         "FROM HISTORIAL_ESTACIONAMIENTO h " +
                         "JOIN VEHICULO v ON h.Placa = v.Placa " +
                         "JOIN ESTACIONAMIENTO e ON h.ID_Estacionamiento = e.ID_Estacionamiento " +
                         "JOIN CONDUCTOR c ON h.Codigo_Conductor = c.Codigo " +
                         "WHERE h.Codigo_Conductor = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HistorialEstacionamiento h = new HistorialEstacionamiento();
                h.setCodigoConductor(rs.getInt("Codigo"));
                h.setPlaca(rs.getString("Placa"));
                h.setTipoVehiculo(rs.getString("tipo"));
                h.setZona(rs.getString("lugar"));
                h.setFecha(rs.getDate("fecha").toLocalDate());
                h.setHoraEntrada(rs.getTime("hora_entrada").toLocalTime());
                h.setHoraSalida(rs.getTime("hora_salida") != null ? rs.getTime("hora_salida").toLocalTime() : null);
                h.setCodigoConductor(rs.getInt("Codigo"));
                h.setNombreConductor(rs.getString("nombre"));
                h.setApellidosConductor(rs.getString("apellidos"));
                historial.add(h);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar historial por código: " + e.getMessage());
        }
        return historial;
    }

    public List<HistorialEstacionamiento> buscarHistorialPorFecha(LocalDate fecha) {
        List<HistorialEstacionamiento> historial = new ArrayList<>();
        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT h.Placa, v.tipo, e.lugar, h.fecha, h.hora_entrada, h.hora_salida, " +
                         "c.Codigo, c.nombre, c.apellidos " +
                         "FROM HISTORIAL_ESTACIONAMIENTO h " +
                         "JOIN VEHICULO v ON h.Placa = v.Placa " +
                         "JOIN ESTACIONAMIENTO e ON h.ID_Estacionamiento = e.ID_Estacionamiento " +
                         "JOIN CONDUCTOR c ON h.Codigo_Conductor = c.Codigo " +
                         "WHERE h.fecha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(fecha));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HistorialEstacionamiento h = new HistorialEstacionamiento();
                h.setPlaca(rs.getString("Placa"));
                h.setTipoVehiculo(rs.getString("tipo"));
                h.setZona(rs.getString("lugar"));
                h.setFecha(rs.getDate("fecha").toLocalDate());
                h.setHoraEntrada(rs.getTime("hora_entrada").toLocalTime());
                h.setHoraSalida(rs.getTime("hora_salida") != null ? rs.getTime("hora_salida").toLocalTime() : null);
                h.setCodigoConductor(rs.getInt("Codigo"));
                h.setNombreConductor(rs.getString("nombre"));
                h.setApellidosConductor(rs.getString("apellidos"));
                historial.add(h);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al buscar historial por fecha: " + e.getMessage());
        }
        return historial;
    }
    
    public List<HistorialEstacionamiento> buscarHistorialPorPlaca(String placa) {
    List<HistorialEstacionamiento> historial = new ArrayList<>();
    try (Connection conn = Conexion.conectar()) {
        String sql = "SELECT h.Placa, v.tipo, e.lugar, h.fecha, h.hora_entrada, h.hora_salida, " +
                     "c.nombre, c.apellidos, c.Codigo " +
                     "FROM HISTORIAL_ESTACIONAMIENTO h " +
                     "JOIN VEHICULO v ON h.Placa = v.Placa " +
                     "JOIN ESTACIONAMIENTO e ON h.ID_Estacionamiento = e.ID_Estacionamiento " +
                     "JOIN CONDUCTOR c ON h.Codigo_Conductor = c.Codigo " +
                     "WHERE h.Placa = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, placa);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            HistorialEstacionamiento h = new HistorialEstacionamiento();
            h.setPlaca(rs.getString("Placa"));
            h.setTipoVehiculo(rs.getString("tipo"));
            h.setZona(rs.getString("lugar"));
            h.setFecha(rs.getDate("fecha").toLocalDate());
            h.setHoraEntrada(rs.getTime("hora_entrada").toLocalTime());
            h.setHoraSalida(rs.getTime("hora_salida") != null ? rs.getTime("hora_salida").toLocalTime() : null);
            h.setNombreConductor(rs.getString("nombre"));
            h.setApellidosConductor(rs.getString("apellidos"));
            h.setCodigoConductor(rs.getInt("Codigo"));
            historial.add(h);
        }
    } catch (Exception e) {
        System.out.println("❌ Error al buscar historial por placa: " + e.getMessage());
    }
    return historial;
    }

}

