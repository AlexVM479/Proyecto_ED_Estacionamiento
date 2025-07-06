package codigos_jos;

public class Trabajador {
    private String usuario;
    private String contraseña;
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String celular;
    private String rol; // este se puede setear luego
    
    public Trabajador() {
}
    // Constructor que coincide con lo que usa tu DAO
    public Trabajador(String usuario, String contraseña, String nombre, String apellidos, String dni, String direccion, String celular) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.celular = celular;
    }

    // Getters
    public String getUsuario() { return usuario; }
    public String getContraseña() { return contraseña; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String getDni() { return dni; }
    public String getDireccion() { return direccion; }
    public String getCelular() { return celular; }
    public String getRol() { return rol; }

    // Setters
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public void setDni(String dni) { this.dni = dni; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setCelular(String celular) { this.celular = celular; }
    public void setRol(String rol) { this.rol = rol; }
}