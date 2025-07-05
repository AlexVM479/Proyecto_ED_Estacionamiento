package com.mycompany.sistemaestacionamiento.gui;

import Registro_Alex.Conductor;
import Registro_Alex.Vehiculo;
import Registro_Alex.VehiculoController;
import Registro_Alex.EstacionamientoController;
import Registro_Alex.Estacionamiento;
import Registro_Alex.HistorialEstacionamiento;
import Registro_Alex.HistorialController;
import java.time.LocalTime;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class CE_Registro_IngresoSalida extends javax.swing.JPanel {

    private final EstacionamientoController estacionamientoController = new EstacionamientoController();
    private final HistorialController historialController = new HistorialController();
    private final VehiculoController vehiculoController = new VehiculoController();  // Instancia del VehiculoController

    public CE_Registro_IngresoSalida() {
        initComponents();
        // Configurar los botones de acción
        registraringreso_button.addActionListener(e -> registrarIngreso());
        registrarsalida_button.addActionListener(e -> registrarSalida());
        restablecer_ingreso_button.addActionListener(e -> restablecerIngreso());
        restablecer_salida_button.addActionListener(e -> restablecerSalida());
        buscarvehiculo_button.addActionListener(e -> buscarVehiculo());
    }

    private void registrarIngreso() {
    try {
        // Validar el código del conductor
        int codigoConductor = Integer.parseInt(codigo_txt.getText().trim());

        // Buscar al conductor en la base de datos
        Conductor conductor = historialController.buscarConductorPorCodigo(codigoConductor);

        if (conductor == null) {
            hayespaciodisponible_txt.setText("Conductor no encontrado.");
            return;
        }

        // Validar la placa del vehículo
        String placa = Nplaca_ingreso_txt.getText().trim();

        // Buscar si la placa ya está registrada en la base de datos
        Vehiculo vehiculo = vehiculoController.buscarVehiculoPorPlaca(placa);

        // Si no se encuentra el vehículo, se trata de una nueva placa, así que la registramos
        if (vehiculo == null) {
            // Aquí registramos la nueva placa
            Vehiculo nuevoVehiculo = new Vehiculo();
            nuevoVehiculo.setPlaca(placa); // Asignamos la nueva placa
            nuevoVehiculo.setTipo(tipovehiculo_combobox.getSelectedItem().toString()); // Asignamos el tipo del vehículo

            // Llamamos al método que registra el vehículo en la base de datos
            vehiculoController.registrarVehiculoNuevo(nuevoVehiculo);

            // Opcionalmente, puedes agregar un mensaje de éxito de registro de vehículo
            System.out.println("Nuevo vehículo registrado: " + placa);
        } else {
            // Verificamos que la placa no tenga una salida pendiente
            boolean vehiculoConSalidaPendiente = historialController.tieneSalidaPendiente(placa);
            if (vehiculoConSalidaPendiente) {
                aviso_Nplaca.setText("El vehículo ya está en uso. No se puede registrar nuevamente.");
                return;
            }
        }

        // Asignar el estacionamiento dependiendo de la puerta
        int numeroPuerta = Integer.parseInt((String) puerta_combobox.getSelectedItem());
        String lugar = (numeroPuerta == 7) ? "Odontología" : "Comedor";

        Estacionamiento estacionamiento = estacionamientoController.buscarEstacionamientoDisponible(lugar);

        if (estacionamiento == null) {
            hayespaciodisponible_txt.setText("No hay espacio disponible.");
            return;
        }

        // Registrar el ingreso
        boolean exito = estacionamientoController.registrarIngreso(placa, codigoConductor, vehiculo.getTipo(), numeroPuerta);

        if (exito) {
            hayespaciodisponible_txt.setText("Espacio asignado: " + estacionamiento.getLugar());
            espacioasignado_txt.setText(estacionamiento.getLugar());
            encoladoposicion_txt.setText("Posición " + estacionamiento.getId());
            horaingreso_txt.setText(LocalTime.now().toString());
        } else {
            hayespaciodisponible_txt.setText("Error al registrar ingreso.");
        }
    } catch (NumberFormatException e) {
        System.out.println("❌ Error al registrar ingreso: " + e.getMessage());
    }
}
    
    private void buscarVehiculo() {
     try {
         // Obtener la placa del vehículo
         String placa = Nplaca_salida_txt.getText().trim();
         HistorialEstacionamiento historial = historialController.buscarHistorialPorPlaca(placa);

         if (historial == null) {
             aviso_busqueda.setText("Vehículo no encontrado.");
             return;
         }

         // Mostrar los datos del vehículo
         espacio_txt.setText(historial.getZona());
         horaingreso_salida_txt.setText(historial.getHoraEntrada().toString());
         tipovehiculo_txt.setText(historial.getTipoVehiculo());
         
         // Aquí no se realiza la salida aún
     } catch (Exception e) {
         System.out.println("❌ Error al buscar vehículo: " + e.getMessage());
     }
 }

    private void registrarSalida() {
     try {
         // Obtener la placa del vehículo para registrar la salida
         String placa = Nplaca_salida_txt.getText().trim();
         HistorialEstacionamiento historial = historialController.buscarHistorialPorPlaca(placa);

         if (historial == null) {
             aviso_busqueda.setText("Vehículo no encontrado.");
             return;
         }

         // Calcular el tiempo total de estacionamiento
         LocalTime horaIngreso = historial.getHoraEntrada();
         LocalTime horaSalida = LocalTime.now();
         long tiempoTotal = java.time.temporal.ChronoUnit.MINUTES.between(horaIngreso, horaSalida);
         tiempototal_txt.setText(tiempoTotal + " minutos");

         // Registrar la salida en la base de datos
         boolean exito = estacionamientoController.registrarSalida(placa);

         if (exito) {
             aviso_busqueda.setText("Salida registrada exitosamente.");
         } else {
             aviso_busqueda.setText("Error al registrar salida.");
         }
     } catch (Exception e) {
         System.out.println("❌ Error al registrar salida: " + e.getMessage());
     }
 }

    private void restablecerIngreso() {
        codigo_txt.setText("");
        Nplaca_ingreso_txt.setText("");
        hayespaciodisponible_txt.setText("");
        aviso_Nplaca.setText("");
        espacioasignado_txt.setText("");
        encoladoposicion_txt.setText("");
        horaingreso_txt.setText("");
    }

    private void restablecerSalida() {
        Nplaca_salida_txt.setText("");
        tiempototal_txt.setText("");
        aviso_busqueda.setText("");
        espacio_txt.setText("");
        horaingreso_salida_txt.setText("");
        tipovehiculo_txt.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Numpuerta_title = new javax.swing.JLabel();
        Nplaca_ingreso_title = new javax.swing.JLabel();
        Nplaca_ingreso_txt = new javax.swing.JTextField();
        puerta_combobox = new javax.swing.JComboBox<>();
        registraringreso_button = new javax.swing.JButton();
        restablecer_ingreso_button = new javax.swing.JButton();
        registraringreso_title = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        aviso_codigo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        encolado_title = new javax.swing.JLabel();
        horaingreso_title = new javax.swing.JLabel();
        encoladoposicion_txt = new javax.swing.JLabel();
        horaingreso_txt = new javax.swing.JLabel();
        espacioasignado_title = new javax.swing.JLabel();
        espacioasignado_txt = new javax.swing.JLabel();
        hayespaciodisponible_txt = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        codigo_title = new javax.swing.JLabel();
        codigo_txt = new javax.swing.JTextField();
        aviso_Nplaca = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        tipovehiculo_title = new javax.swing.JLabel();
        tipovehiculo_combobox = new javax.swing.JComboBox<>();
        registrarsalida_title = new javax.swing.JLabel();
        Nplaca_salida_title = new javax.swing.JLabel();
        Nplaca_salida_txt = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        restablecer_salida_button = new javax.swing.JButton();
        buscarvehiculo_button = new javax.swing.JButton();
        datosingreso_title = new javax.swing.JLabel();
        aviso_busqueda = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        espacio_title = new javax.swing.JLabel();
        espacio_txt = new javax.swing.JLabel();
        tipovehiculo_title1 = new javax.swing.JLabel();
        horaingreso_title1 = new javax.swing.JLabel();
        tipovehiculo_txt = new javax.swing.JLabel();
        horaingreso_salida_txt = new javax.swing.JLabel();
        tiempototal_title = new javax.swing.JLabel();
        tiempototal_txt = new javax.swing.JLabel();
        registrarsalida_button = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Numpuerta_title.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        Numpuerta_title.setForeground(new java.awt.Color(102, 102, 102));
        Numpuerta_title.setText("N° Puerta:");
        add(Numpuerta_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        Nplaca_ingreso_title.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        Nplaca_ingreso_title.setForeground(new java.awt.Color(102, 102, 102));
        Nplaca_ingreso_title.setText("N° Placa:");
        add(Nplaca_ingreso_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, -1));

        Nplaca_ingreso_txt.setBackground(new java.awt.Color(204, 204, 204));
        Nplaca_ingreso_txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Nplaca_ingreso_txt.setForeground(new java.awt.Color(102, 102, 102));
        Nplaca_ingreso_txt.setBorder(null);
        Nplaca_ingreso_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nplaca_ingreso_txtActionPerformed(evt);
            }
        });
        add(Nplaca_ingreso_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 110, 20));

        puerta_combobox.setBackground(new java.awt.Color(204, 204, 204));
        puerta_combobox.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        puerta_combobox.setForeground(new java.awt.Color(51, 51, 51));
        puerta_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7" }));
        puerta_combobox.setBorder(null);
        puerta_combobox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        puerta_combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puerta_comboboxActionPerformed(evt);
            }
        });
        add(puerta_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, 110, -1));

        registraringreso_button.setBackground(new java.awt.Color(31, 39, 115));
        registraringreso_button.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        registraringreso_button.setForeground(new java.awt.Color(225, 225, 225));
        registraringreso_button.setText("Registrar Ingreso");
        registraringreso_button.setBorder(null);
        registraringreso_button.setBorderPainted(false);
        registraringreso_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(registraringreso_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 130, 30));

        restablecer_ingreso_button.setBackground(new java.awt.Color(82, 82, 82));
        restablecer_ingreso_button.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        restablecer_ingreso_button.setForeground(new java.awt.Color(225, 225, 225));
        restablecer_ingreso_button.setText("Restablecer");
        restablecer_ingreso_button.setBorder(null);
        restablecer_ingreso_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restablecer_ingreso_buttonActionPerformed(evt);
            }
        });
        add(restablecer_ingreso_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 110, 20));

        registraringreso_title.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        registraringreso_title.setForeground(new java.awt.Color(58, 65, 133));
        registraringreso_title.setText("Registro de Ingreso de Vehículo");
        add(registraringreso_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 250, 20));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 102));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 153));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 550, 20));

        aviso_codigo.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        aviso_codigo.setForeground(new java.awt.Color(255, 102, 102));
        add(aviso_codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 170, 20));

        jPanel1.setBackground(new java.awt.Color(36, 36, 36));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        encolado_title.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        encolado_title.setForeground(new java.awt.Color(225, 225, 225));
        encolado_title.setText("ID_Estacionamiento:");
        jPanel1.add(encolado_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 140, 20));

        horaingreso_title.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        horaingreso_title.setForeground(new java.awt.Color(225, 225, 225));
        horaingreso_title.setText("Hora de Ingreso:");
        jPanel1.add(horaingreso_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 110, 20));

        encoladoposicion_txt.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        encoladoposicion_txt.setForeground(new java.awt.Color(225, 225, 225));
        jPanel1.add(encoladoposicion_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 90, 20));

        horaingreso_txt.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        horaingreso_txt.setForeground(new java.awt.Color(225, 225, 225));
        jPanel1.add(horaingreso_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 100, 20));

        espacioasignado_title.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        espacioasignado_title.setForeground(new java.awt.Color(225, 225, 225));
        espacioasignado_title.setText("Espacio Asignado:");
        jPanel1.add(espacioasignado_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, 20));

        espacioasignado_txt.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        espacioasignado_txt.setForeground(new java.awt.Color(225, 225, 225));
        jPanel1.add(espacioasignado_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 90, 20));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 500, 90));

        hayespaciodisponible_txt.setForeground(new java.awt.Color(51, 51, 51));
        add(hayespaciodisponible_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 460, 30));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 110, 10));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 110, 10));

        codigo_title.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        codigo_title.setForeground(new java.awt.Color(102, 102, 102));
        codigo_title.setText("Código:");
        add(codigo_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, -1));

        codigo_txt.setBackground(new java.awt.Color(204, 204, 204));
        codigo_txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        codigo_txt.setForeground(new java.awt.Color(102, 102, 102));
        codigo_txt.setBorder(null);
        codigo_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigo_txtActionPerformed(evt);
            }
        });
        add(codigo_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, 110, 20));

        aviso_Nplaca.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        aviso_Nplaca.setForeground(new java.awt.Color(255, 102, 102));
        add(aviso_Nplaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 170, 20));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, 110, 10));

        tipovehiculo_title.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        tipovehiculo_title.setForeground(new java.awt.Color(102, 102, 102));
        tipovehiculo_title.setText("Tipo de vehículo:");
        add(tipovehiculo_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, -1, -1));

        tipovehiculo_combobox.setBackground(new java.awt.Color(204, 204, 204));
        tipovehiculo_combobox.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        tipovehiculo_combobox.setForeground(new java.awt.Color(51, 51, 51));
        tipovehiculo_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Auto", "Camión", "Camioneta", "Motocicleta" }));
        tipovehiculo_combobox.setBorder(null);
        tipovehiculo_combobox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        add(tipovehiculo_combobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 110, -1));

        registrarsalida_title.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        registrarsalida_title.setForeground(new java.awt.Color(58, 65, 133));
        registrarsalida_title.setText("Registro de Salida de Vehículo");
        add(registrarsalida_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 250, 20));

        Nplaca_salida_title.setFont(new java.awt.Font("Century Gothic", 0, 13)); // NOI18N
        Nplaca_salida_title.setForeground(new java.awt.Color(102, 102, 102));
        Nplaca_salida_title.setText("N° Placa:");
        add(Nplaca_salida_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, -1, -1));

        Nplaca_salida_txt.setBackground(new java.awt.Color(204, 204, 204));
        Nplaca_salida_txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Nplaca_salida_txt.setForeground(new java.awt.Color(102, 102, 102));
        Nplaca_salida_txt.setBorder(null);
        Nplaca_salida_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nplaca_salida_txtActionPerformed(evt);
            }
        });
        add(Nplaca_salida_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 110, 20));
        add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 110, 10));
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 110, 10));

        restablecer_salida_button.setBackground(new java.awt.Color(82, 82, 82));
        restablecer_salida_button.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        restablecer_salida_button.setForeground(new java.awt.Color(225, 225, 225));
        restablecer_salida_button.setText("Restablecer");
        restablecer_salida_button.setBorder(null);
        restablecer_salida_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restablecer_salida_buttonActionPerformed(evt);
            }
        });
        add(restablecer_salida_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 110, 20));

        buscarvehiculo_button.setBackground(new java.awt.Color(31, 39, 115));
        buscarvehiculo_button.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        buscarvehiculo_button.setForeground(new java.awt.Color(225, 225, 225));
        buscarvehiculo_button.setText("Buscar Vehículo");
        buscarvehiculo_button.setBorder(null);
        buscarvehiculo_button.setBorderPainted(false);
        add(buscarvehiculo_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 130, 30));

        datosingreso_title.setBackground(new java.awt.Color(51, 51, 51));
        datosingreso_title.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        datosingreso_title.setForeground(new java.awt.Color(51, 51, 51));
        datosingreso_title.setText("Datos de Ingreso:");
        add(datosingreso_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 110, -1));

        aviso_busqueda.setBackground(new java.awt.Color(51, 51, 51));
        aviso_busqueda.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        aviso_busqueda.setForeground(new java.awt.Color(255, 102, 102));
        add(aviso_busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 380, 300, 30));

        jPanel2.setBackground(new java.awt.Color(36, 36, 36));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        espacio_title.setBackground(new java.awt.Color(204, 204, 204));
        espacio_title.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        espacio_title.setForeground(new java.awt.Color(225, 225, 225));
        espacio_title.setText("Espacio:");
        jPanel2.add(espacio_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 70, 30));

        espacio_txt.setBackground(new java.awt.Color(204, 204, 204));
        espacio_txt.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        espacio_txt.setForeground(new java.awt.Color(225, 225, 225));
        jPanel2.add(espacio_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 110, 30));

        tipovehiculo_title1.setBackground(new java.awt.Color(204, 204, 204));
        tipovehiculo_title1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        tipovehiculo_title1.setForeground(new java.awt.Color(225, 225, 225));
        tipovehiculo_title1.setText("Tipo de Vehículo:");
        jPanel2.add(tipovehiculo_title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 110, 30));

        horaingreso_title1.setBackground(new java.awt.Color(204, 204, 204));
        horaingreso_title1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        horaingreso_title1.setForeground(new java.awt.Color(225, 225, 225));
        horaingreso_title1.setText("Hora de ingreso:   ");
        jPanel2.add(horaingreso_title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 30));

        tipovehiculo_txt.setBackground(new java.awt.Color(204, 204, 204));
        tipovehiculo_txt.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        tipovehiculo_txt.setForeground(new java.awt.Color(225, 225, 225));
        jPanel2.add(tipovehiculo_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 110, 30));

        horaingreso_salida_txt.setBackground(new java.awt.Color(204, 204, 204));
        horaingreso_salida_txt.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        horaingreso_salida_txt.setForeground(new java.awt.Color(225, 225, 225));
        jPanel2.add(horaingreso_salida_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 110, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 430, 280, 120));

        tiempototal_title.setBackground(new java.awt.Color(51, 51, 51));
        tiempototal_title.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        tiempototal_title.setForeground(new java.awt.Color(51, 51, 51));
        tiempototal_title.setText("Tiempo Total:");
        add(tiempototal_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 440, 90, 30));

        tiempototal_txt.setBackground(new java.awt.Color(204, 204, 204));
        tiempototal_txt.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        tiempototal_txt.setForeground(new java.awt.Color(51, 51, 51));
        add(tiempototal_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 440, 100, 30));

        registrarsalida_button.setBackground(new java.awt.Color(31, 39, 115));
        registrarsalida_button.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        registrarsalida_button.setForeground(new java.awt.Color(225, 225, 225));
        registrarsalida_button.setText("Registrar Salida");
        registrarsalida_button.setBorder(null);
        registrarsalida_button.setBorderPainted(false);
        add(registrarsalida_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 490, 130, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void Nplaca_ingreso_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nplaca_ingreso_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nplaca_ingreso_txtActionPerformed

    private void codigo_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigo_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codigo_txtActionPerformed

    private void puerta_comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puerta_comboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puerta_comboboxActionPerformed

    private void Nplaca_salida_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nplaca_salida_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nplaca_salida_txtActionPerformed

    private void restablecer_ingreso_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restablecer_ingreso_buttonActionPerformed
        
        this.codigo_txt.setText("");
        this.Nplaca_ingreso_txt.setText("");
        
    }//GEN-LAST:event_restablecer_ingreso_buttonActionPerformed

    private void restablecer_salida_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restablecer_salida_buttonActionPerformed
        this.Nplaca_salida_txt.setText("");
    }//GEN-LAST:event_restablecer_salida_buttonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nplaca_ingreso_title;
    private javax.swing.JTextField Nplaca_ingreso_txt;
    private javax.swing.JLabel Nplaca_salida_title;
    private javax.swing.JTextField Nplaca_salida_txt;
    private javax.swing.JLabel Numpuerta_title;
    private javax.swing.JLabel aviso_Nplaca;
    private javax.swing.JLabel aviso_busqueda;
    private javax.swing.JLabel aviso_codigo;
    private javax.swing.JButton buscarvehiculo_button;
    private javax.swing.JLabel codigo_title;
    private javax.swing.JTextField codigo_txt;
    private javax.swing.JLabel datosingreso_title;
    private javax.swing.JLabel encolado_title;
    private javax.swing.JLabel encoladoposicion_txt;
    private javax.swing.JLabel espacio_title;
    private javax.swing.JLabel espacio_txt;
    private javax.swing.JLabel espacioasignado_title;
    private javax.swing.JLabel espacioasignado_txt;
    private javax.swing.JLabel hayespaciodisponible_txt;
    private javax.swing.JLabel horaingreso_salida_txt;
    private javax.swing.JLabel horaingreso_title;
    private javax.swing.JLabel horaingreso_title1;
    private javax.swing.JLabel horaingreso_txt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JComboBox<String> puerta_combobox;
    private javax.swing.JButton registraringreso_button;
    private javax.swing.JLabel registraringreso_title;
    private javax.swing.JButton registrarsalida_button;
    private javax.swing.JLabel registrarsalida_title;
    private javax.swing.JButton restablecer_ingreso_button;
    private javax.swing.JButton restablecer_salida_button;
    private javax.swing.JLabel tiempototal_title;
    private javax.swing.JLabel tiempototal_txt;
    private javax.swing.JComboBox<String> tipovehiculo_combobox;
    private javax.swing.JLabel tipovehiculo_title;
    private javax.swing.JLabel tipovehiculo_title1;
    private javax.swing.JLabel tipovehiculo_txt;
    // End of variables declaration//GEN-END:variables

}