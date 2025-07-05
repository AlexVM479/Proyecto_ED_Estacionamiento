package codigos_jos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.mycompany.sistemaestacionamiento.Conexion;

public class LoginController {

    private HashMap<String, String> credenciales;

    public LoginController() {
        credenciales = new HashMap<>();
        cargarCredencialesDesdeBD();
    }

    private void cargarCredencialesDesdeBD() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.conectar();
            String sql = "SELECT usuario, contraseña FROM TRABAJADOR";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contraseña");
                credenciales.put(usuario, contrasena);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al cargar credenciales: " + e.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

    public boolean validarLogin(String usuario, String contrasena) {
        return credenciales.containsKey(usuario) && credenciales.get(usuario).equals(contrasena);
    }
    
    public boolean iniciarSesion(String usuario, String contrasena) {
    if (validarLogin(usuario, contrasena)) {
        // Creamos el Trabajador logueado (puedes extender esto para traer más datos si gustas)
        Trabajador trabajador = new Trabajador();
        trabajador.setUsuario(usuario);
        trabajador.setContraseña(contrasena);
        Sesion.usuarioLogueado = trabajador;
        return true;
    }
    return false;
}
}
