package codigos_jos;

import com.mycompany.sistemaestacionamiento.Conexion;
import java.sql.*;
import java.util.*;

public class TrabajadorDAO {

    public static List<Trabajador> obtenerTrabajadores() {
        List<Trabajador> lista = new ArrayList<>();

        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT usuario, contraseña, nombre, apellidos, dni, direccion, celular FROM trabajador";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String usuario = rs.getString("usuario");
                String contraseña = rs.getString("contraseña");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String dni = rs.getString("dni");
                String direccion = rs.getString("direccion");
                String celular = rs.getString("celular");

                // rol por defecto (si aún no se guarda en la BD)
                Trabajador t = new Trabajador(usuario, contraseña, nombre, apellidos, dni, direccion, celular);
                t.setRol("Empleado"); // puedes cambiar esto si en el futuro lo lees desde la base de datos

                lista.add(t);
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener trabajadores: " + e.getMessage());
        }

        return lista;
    }
    
public static boolean eliminarTrabajador(String usuario) {
    String sql = "DELETE FROM trabajador WHERE usuario = ?";
    try (Connection conn = Conexion.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, usuario);
        int filas = stmt.executeUpdate();
        return filas > 0;
    } catch (Exception e) {
        System.out.println("❌ Error al eliminar trabajador: " + e.getMessage());
        return false;
    }
}

public static boolean actualizarTrabajador(Trabajador t) {
    String sql = "UPDATE trabajador SET contraseña=?, nombre=?, apellidos=?, dni=?, direccion=?, celular=? WHERE usuario=?";
    try (Connection conn = Conexion.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, t.getContraseña());
        ps.setString(2, t.getNombre());
        ps.setString(3, t.getApellidos());
        ps.setString(4, t.getDni());
        ps.setString(5, t.getDireccion());
        ps.setString(6, t.getCelular());
        ps.setString(7, t.getUsuario());

        return ps.executeUpdate() > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
}

public static boolean insertarTrabajador(Trabajador t) {
String sql = "INSERT INTO trabajador (usuario, contraseña, nombre, apellidos, dni, direccion, celular) VALUES (?, ?, ?, ?, ?, ?, ?)";
try (Connection conn = Conexion.conectar();
PreparedStatement stmt = conn.prepareStatement(sql)) {
stmt.setString(1, t.getUsuario());
stmt.setString(2, t.getContraseña());
stmt.setString(3, t.getNombre());
stmt.setString(4, t.getApellidos());
stmt.setString(5, t.getDni());
stmt.setString(6, t.getDireccion());
stmt.setString(7, t.getCelular());
int filas = stmt.executeUpdate();
    return filas > 0;
} catch (Exception e) {
    System.out.println("❌ Error al insertar trabajador: " + e.getMessage());
    return false;
}
}
}
