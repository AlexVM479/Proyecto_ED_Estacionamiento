package codigos_jos;

public class VerificacionAcceso {
    public static boolean verificarCredenciales(String usuario, String contraseña) {
        return usuario.equals("admin") && contraseña.equals("admin123");
    }
}

