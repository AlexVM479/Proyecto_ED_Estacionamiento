package com.mycompany.sistemaestacionamiento.gui;

import control_busqueda.HistorialController;
import control_busqueda.HistorialEstacionamiento;
import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class fechaBusqueda extends javax.swing.JPanel {

    public fechaBusqueda() {
        initComponents();
        configurarEventos(); // ← Aquí enlazamos el botón BUSCAR
        jTextField1.setText("texto busqueda fecha (YYYY-MM-DD)");
        jTextField1.setForeground(java.awt.Color.GRAY);  
    }
    private void configurarEventos() {
    jToggleButton1.addActionListener(e -> buscarPorFecha());
    }
    
    private void buscarPorFecha() {
    String fechaTexto = jTextField1.getText().trim();
    try {
        LocalDate fecha = LocalDate.parse(fechaTexto);
        HistorialController controller = new HistorialController();
        List<HistorialEstacionamiento> resultados = controller.buscarHistorialPorFecha(fecha);

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new String[]{"Codigo","Nombre", "Apellidos", "Placa", "Tipo", "Zona", "Entrada", "Salida"});
        for (HistorialEstacionamiento h : resultados) {
            modelo.addRow(new Object[]{
                h.getCodigoConductor(),
                h.getNombreConductor(),
                h.getApellidosConductor(),
                h.getPlaca(),
                h.getTipoVehiculo(),
                h.getZona(),
                
                h.getHoraEntrada(),
                h.getHoraSalida()
            });
        }
        jTable1.setModel(modelo);
    } catch (DateTimeParseException ex) {
        JOptionPane.showMessageDialog(this, "Fecha inválida. Usa el formato YYYY-MM-DD.");
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setText("texto busqueda fecha (YYYY-MM-DD)");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 300, -1));

        jToggleButton1.setBackground(new java.awt.Color(31, 39, 115));
        jToggleButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("BUSCAR");
        jToggleButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 110, -1));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 550, 420));
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        if (jTextField1.getText().equals("texto busqueda fecha (YYYY-MM-DD)")) {
            jTextField1.setText("");
            jTextField1.setForeground(Color.BLACK);}
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        if (jTextField1.getText().isEmpty()) {
            jTextField1.setText("texto busqueda fecha (YYYY-MM-DD)");
            jTextField1.setForeground(Color.GRAY);
}
    }//GEN-LAST:event_jTextField1FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
