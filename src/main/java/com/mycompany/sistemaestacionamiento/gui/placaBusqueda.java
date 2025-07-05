package com.mycompany.sistemaestacionamiento.gui;

import control_busqueda.HistorialController;
import control_busqueda.HistorialEstacionamiento;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class placaBusqueda extends javax.swing.JPanel {

    public placaBusqueda() {
        initComponents();
        configurarEventos(); // ← Aquí enlazamos el botón BUSCAR
    }
    private void configurarEventos() {
    jToggleButton1.addActionListener(e -> buscarPorPlaca());
    }
    
    private void buscarPorPlaca() {
    String placaTexto = jTextField1.getText().trim();
    if (placaTexto.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor ingresa una placa.");
        return;
    }

    HistorialController controller = new HistorialController();
    List<HistorialEstacionamiento> resultados = controller.buscarHistorialPorPlaca(placaTexto);

    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new String[]{
        "Código", "Nombre", "Apellidos", "Tipo", "Zona", "Fecha", "Entrada", "Salida"
    });

    for (HistorialEstacionamiento h : resultados) {
        modelo.addRow(new Object[]{
            h.getCodigoConductor(),
            h.getNombreConductor(),
            h.getApellidosConductor(),
            h.getTipoVehiculo(),
            h.getZona(),
            h.getFecha(),
            h.getHoraEntrada(),
            h.getHoraSalida()
        });
    }

    jTable1.setModel(modelo);
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 550, 390));

        jTextField1.setText("texto busqueda placa");
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 300, -1));

        jToggleButton1.setBackground(new java.awt.Color(31, 39, 115));
        jToggleButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("BUSCAR");
        jToggleButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 110, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
