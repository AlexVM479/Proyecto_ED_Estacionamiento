
package com.mycompany.sistemaestacionamiento.gui;

import java.awt.*;
import java.net.URL;
import javax.swing.*;
import codigos_jos.Trabajador;
import codigos_jos.TrabajadorDAO;
import java.util.List;

public class AdministrarUser extends javax.swing.JPanel {

    private boolean mostrarContraseña = false;

    public AdministrarUser() {
        try {
initComponents();
SetImageLabel(jLabel9, "imagenes/usuario_icon.png");
SetImageLabel(jLabel12, "imagenes/usuario_icon.png");
SetImageLabel(jLabel14, "imagenes/usuario_icon.png");
SetImageLabel(jLabel43, "imagenes/usuario_icon.png");
SetImageLabel(jLabel45, "imagenes/usuario_icon.png");
SetImageLabel(jLabel47, "imagenes/usuario_icon.png");
SetImageLabel(jLabel41, "imagenes/ojo_icon.png");

jLabel41.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
jLabel41.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        mostrarContraseña = !mostrarContraseña;
        if (mostrarContraseña) {
            jPasswordField3.setEchoChar((char) 0); // Mostrar texto
        } else {
            jPasswordField3.setEchoChar('•'); // Ocultar con puntos o usa '*'
        }
    }
});


cargarPanelesTrabajadores(); // nuevo método que vamos a crear

jTextField18.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        confirmarYAgregarUsuario();
    }
});
jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        eliminarUsuarioSeleccionado();
    }
});
jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        habilitarEdicionYConfirmar();
    }
});
jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
        // Validar que esté habilitada la edición
        if (!jTextField14.isEditable()) {
            JOptionPane.showMessageDialog(null, "ℹ️ Primero presiona el botón EDIT para habilitar la edición.");
            return;
        }

        int confirmarGuardar = JOptionPane.showConfirmDialog(null,
            "¿Deseas guardar los cambios?",
            "Confirmar edición",
            JOptionPane.YES_NO_OPTION);

        if (confirmarGuardar == JOptionPane.YES_OPTION) {
            String usuario = jTextField13.getText(); // no se edita
            String nuevoUserName = jTextField14.getText();
            String nuevaContraseña = new String(jPasswordField3.getPassword());
            String nuevoNombre = jTextField15.getText();
            String nuevosApellidos = jTextField16.getText();
            String nuevoDni = jTextField1.getText();
            String nuevaDireccion = jTextField17.getText();
            String nuevoCelular = jTextField18.getText();

            if (nuevoUserName.isEmpty() || nuevaContraseña.isEmpty() || nuevoNombre.isEmpty()
                || nuevosApellidos.isEmpty() || nuevoDni.isEmpty()
                || nuevaDireccion.isEmpty() || nuevoCelular.isEmpty()) {
                JOptionPane.showMessageDialog(null, "❌ Todos los campos deben estar llenos.");
                return;
            }

            Trabajador nuevo = new Trabajador(usuario, nuevaContraseña, nuevoNombre, nuevosApellidos, nuevoDni, nuevaDireccion, nuevoCelular);

            boolean actualizado = TrabajadorDAO.actualizarTrabajador(nuevo);

            if (actualizado) {
                JOptionPane.showMessageDialog(null, "✅ Datos actualizados correctamente.");
                cargarPanelesTrabajadores();
            } else {
                JOptionPane.showMessageDialog(null, "❌ No se pudo actualizar el trabajador.");
            }

            // Bloquear los campos después de editar
            jPasswordField3.setEditable(false);
            jTextField14.setEditable(false);
            jTextField15.setEditable(false);
            jTextField16.setEditable(false);
            jTextField17.setEditable(false);
            jTextField18.setEditable(false);
            jTextField1.setEditable(false);
        }
    }
});


        } catch (Exception e) {
            e.printStackTrace(); // esto mostrará la línea exacta donde falla
            JOptionPane.showMessageDialog(null, "Error en AdminUser: " + e.getMessage());
        }
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jToggleButton4 = new javax.swing.JToggleButton();

        setBackground(new java.awt.Color(248, 248, 248));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(204, 204, 204));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(31, 39, 115));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(31, 39, 115));
        jLabel7.setText("DATOS DEL USUARIO");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(31, 39, 115));
        jLabel8.setText("DATOS PERSONALES");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, -1, -1));

        jSeparator3.setBackground(new java.awt.Color(204, 204, 204));
        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 178, 373, 10));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(82, 82, 82));
        jLabel33.setText("User ID :");
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 47, -1, -1));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(82, 82, 82));
        jLabel34.setText("User Name :");
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 90, -1, -1));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(82, 82, 82));
        jLabel35.setText("Password :");
        jPanel6.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 136, -1, -1));

        jPasswordField3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jPasswordField3.setText("jPasswordField1");
        jPasswordField3.setBorder(null);
        jPasswordField3.setCaretColor(new java.awt.Color(204, 204, 204));
        jPasswordField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField3ActionPerformed(evt);
            }
        });
        jPanel6.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 129, 140, 20));

        jTextField13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField13.setBorder(null);
        jTextField13.setCaretColor(new java.awt.Color(204, 204, 204));
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 140, 20));

        jTextField14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField14.setBorder(null);
        jPanel6.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 83, 140, 20));

        jLabel36.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(82, 82, 82));
        jLabel36.setText("Nombres :");
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 243, -1, -1));

        jLabel37.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(82, 82, 82));
        jLabel37.setText("Apellidos :");
        jPanel6.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 283, -1, -1));

        jLabel38.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(82, 82, 82));
        jLabel38.setText("DNI : ");
        jPanel6.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, -1, -1));

        jLabel39.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(82, 82, 82));
        jLabel39.setText("Direccion :");
        jPanel6.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(49, 369, -1, -1));

        jLabel40.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(82, 82, 82));
        jLabel40.setText("Celular :");
        jPanel6.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        jTextField15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField15.setBorder(null);
        jTextField15.setCaretColor(new java.awt.Color(204, 204, 204));
        jPanel6.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 236, 129, 20));

        jTextField16.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField16.setBorder(null);
        jTextField16.setCaretColor(new java.awt.Color(204, 204, 204));
        jPanel6.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 276, 129, 20));

        jTextField17.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField17.setBorder(null);
        jTextField17.setCaretColor(new java.awt.Color(204, 204, 204));
        jPanel6.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 362, 129, 20));

        jTextField18.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField18.setBorder(null);
        jTextField18.setCaretColor(new java.awt.Color(204, 204, 204));
        jPanel6.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(171, 405, 129, 20));
        jPanel6.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 20, 20));

        jTextField1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.setCaretColor(new java.awt.Color(204, 204, 204));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel6.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 129, 20));

        add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, 460));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel7.setBackground(new java.awt.Color(153, 153, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(31, 39, 115));
        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 1, 30, 50));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Usuario");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 3, -1, 48));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 149, -1));

        jPanel9.setBackground(new java.awt.Color(31, 39, 115));
        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setPreferredSize(new java.awt.Dimension(156, 51));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Usuario");
        jPanel9.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 3, -1, 45));
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 3, 30, 45));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 66, 149, -1));

        jPanel10.setBackground(new java.awt.Color(31, 39, 115));
        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.setPreferredSize(new java.awt.Dimension(156, 51));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Usuario");
        jPanel10.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 3, -1, 45));
        jPanel10.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 3, 30, 45));

        jPanel7.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 123, 149, -1));

        jPanel11.setBackground(new java.awt.Color(31, 39, 115));
        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel11.setPreferredSize(new java.awt.Dimension(156, 51));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Usuario");
        jPanel11.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 3, -1, 45));
        jPanel11.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 3, 30, 45));

        jPanel7.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 180, 149, -1));

        jPanel12.setBackground(new java.awt.Color(31, 39, 115));
        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel12.setPreferredSize(new java.awt.Dimension(156, 51));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel44.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Usuario");
        jPanel12.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 3, -1, 45));
        jPanel12.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 3, 30, 45));

        jPanel7.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 237, 149, -1));

        jPanel13.setBackground(new java.awt.Color(31, 39, 115));
        jPanel13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel13.setPreferredSize(new java.awt.Dimension(145, 51));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel46.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("Usuario");
        jPanel13.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(67, 3, -1, 45));
        jPanel13.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 3, 30, 45));

        jPanel7.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 294, 149, -1));

        jPanel14.setBackground(new java.awt.Color(31, 39, 115));
        jPanel14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel14.setPreferredSize(new java.awt.Dimension(145, 51));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel7.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 351, -1, -1));

        jScrollPane2.setViewportView(jPanel7);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 180, 380));

        jToggleButton1.setBackground(new java.awt.Color(31, 39, 115));
        jToggleButton1.setFont(new java.awt.Font("Segoe UI Black", 1, 10)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("NEW");
        jToggleButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        add(jToggleButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 50, 30));

        jToggleButton2.setBackground(new java.awt.Color(31, 39, 115));
        jToggleButton2.setFont(new java.awt.Font("Segoe UI Black", 1, 10)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton2.setText("DELETE");
        jToggleButton2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(jToggleButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 60, 30));

        jToggleButton3.setBackground(new java.awt.Color(31, 39, 115));
        jToggleButton3.setFont(new java.awt.Font("Segoe UI Black", 1, 10)); // NOI18N
        jToggleButton3.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton3.setText("EDIT");
        jToggleButton3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(jToggleButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 50, 30));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(58, 65, 133));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Administrar Usuarios");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 590, 30));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 190, 20));

        jToggleButton4.setBackground(new java.awt.Color(31, 39, 115));
        jToggleButton4.setFont(new java.awt.Font("Segoe UI Black", 1, 10)); // NOI18N
        jToggleButton4.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton4.setText("SAVE");
        jToggleButton4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToggleButton4.setMaximumSize(new java.awt.Dimension(45, 20));
        jToggleButton4.setMinimumSize(new java.awt.Dimension(45, 20));
        jToggleButton4.setPreferredSize(new java.awt.Dimension(45, 20));
        add(jToggleButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, 60, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
    // Limpiar todos los campos
    jTextField13.setText("");
    jTextField14.setText("");
    jPasswordField3.setText("");
    jTextField15.setText("");
    jTextField16.setText("");
    jTextField17.setText("");
    jTextField18.setText("");
    jTextField1.setText("");
  // Habilitar campos para edición
    jTextField13.setEditable(true);
    jTextField14.setEditable(true);
    jPasswordField3.setEditable(true);
    jTextField15.setEditable(true);
    jTextField16.setEditable(true);
    jTextField17.setEditable(true);
    jTextField18.setEditable(true);
    jTextField1.setEditable(true);
    // Colocar el foco en el primer campo
    jTextField13.requestFocus();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jPasswordField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordField3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed
    
    
    private void SetImageLabel(JLabel labelName, String root) {
        URL imageURL = getClass().getClassLoader().getResource(root);
        if (imageURL != null) {
            ImageIcon image = new ImageIcon(imageURL);

            int w = labelName.getWidth();
            int h = labelName.getHeight();

            if (w > 0 && h > 0) {
                Icon icon = new ImageIcon(
                    image.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)
                );
                labelName.setIcon(icon);
            } else {
                
                labelName.setIcon(image);
            }
            this.repaint();
        } else {
            System.err.println("No se pudo encontrar la imagen: " + root);
        }
    }

private void mostrarDatosTrabajador(Trabajador t) {
    jTextField13.setText(t.getUsuario());
    jTextField14.setText(t.getUsuario());
    jPasswordField3.setText(t.getContraseña());
    jTextField15.setText(t.getNombre());
    jTextField16.setText(t.getApellidos());
    jTextField17.setText(t.getDireccion());
    jTextField18.setText(t.getCelular());
    jTextField1.setText(t.getDni());
    //bloquea
    jTextField13.setEditable(false);
    jTextField14.setEditable(false);
    jPasswordField3.setEditable(false);
    jTextField15.setEditable(false);
    jTextField16.setEditable(false);
    jTextField17.setEditable(false);
    jTextField18.setEditable(false);
    jTextField1.setEditable(false);
    
}

    
private void cargarPanelesTrabajadores() {
    try {
        List<Trabajador> trabajadores = TrabajadorDAO.obtenerTrabajadores();

     jPanel7.removeAll();  // limpia la lista lateral
     jPanel7.setLayout(new BoxLayout(jPanel7, BoxLayout.Y_AXIS)); // para scroll correcto

        for (Trabajador t : trabajadores) {
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    panel.setBackground(new java.awt.Color(31, 39, 115));
    panel.setPreferredSize(new Dimension(150, 50)); // alto fijo

    JLabel labelIcono = new JLabel();
    labelIcono.setPreferredSize(new Dimension(30, 30));
    labelIcono.setIcon(new ImageIcon(getClass().getClassLoader().getResource("imagenes/usuario_icon.png")));

    JLabel labelTexto = new JLabel(t.getNombre() + " " + t.getApellidos());
    labelTexto.setFont(new java.awt.Font("Century Gothic", 1, 12));
    labelTexto.setForeground(Color.WHITE);

    panel.add(labelIcono);
    panel.add(labelTexto);

    // ✅ evento al hacer clic
    panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    panel.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            mostrarDatosTrabajador(t);
        }
    });

    jPanel7.add(panel);
        }

        jPanel7.revalidate();
        jPanel7.repaint();
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "❌ Error al cargar trabajadores: " + e.getMessage());
    }
}

private void confirmarYAgregarUsuario() {
    String usuario = jTextField13.getText();
    String contraseña = new String(jPasswordField3.getPassword());
    String nombre = jTextField15.getText();
    String apellidos = jTextField16.getText();
    String direccion = jTextField17.getText();
    String celular = jTextField18.getText();
    String dni = jTextField1.getText();  // ✅ aquí el cambio importante

    if (usuario.isEmpty() || contraseña.isEmpty() || nombre.isEmpty() ||
        apellidos.isEmpty() || direccion.isEmpty() || celular.isEmpty() || dni.isEmpty()) {
        JOptionPane.showMessageDialog(this, "❌ Por favor, completa todos los campos.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this,
        "¿Deseas agregar a este usuario?",
        "Confirmar registro",
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        try {
            Trabajador t = new Trabajador(usuario, contraseña, nombre, apellidos, dni, direccion, celular);
            // t.setRol(rol);  // ❌ ya no es necesario
            boolean insertado = TrabajadorDAO.insertarTrabajador(t);

            if (insertado) {
                JOptionPane.showMessageDialog(this, "✅ Trabajador insertado correctamente.");
                cargarPanelesTrabajadores();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ No se pudo insertar el trabajador.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage());
        }
    }
}

private void eliminarUsuarioSeleccionado() {
    String usuario = jTextField13.getText();

    if (usuario.isEmpty()) {
        JOptionPane.showMessageDialog(this, "❌ Selecciona un trabajador para eliminar.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this,
        "¿Estás seguro de que deseas eliminar al usuario: " + usuario + "?",
        "Confirmar eliminación",
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        boolean eliminado = TrabajadorDAO.eliminarTrabajador(usuario);
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "✅ Trabajador eliminado correctamente.");
            cargarPanelesTrabajadores();
            jToggleButton1.doClick(); // Limpia los campos como si se presionara el botón NEW
        } else {
            JOptionPane.showMessageDialog(this, "⚠️ No se pudo eliminar el trabajador.");
        }
    }
}

private void habilitarEdicionYConfirmar() {
    String usuario = jTextField13.getText();

    if (usuario.isEmpty()) {
        JOptionPane.showMessageDialog(this, "❌ Selecciona un trabajador primero.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this,
        "¿Desea editar este usuario: " + usuario + "?",
        "Confirmar edición",
        JOptionPane.YES_NO_OPTION);

    if (confirm == JOptionPane.YES_OPTION) {
        // Habilitar los campos para edición (menos el usuario)
        jPasswordField3.setEditable(true);
        jTextField14.setEditable(true);
        jTextField15.setEditable(true);
        jTextField16.setEditable(true);
        jTextField17.setEditable(true);
        jTextField18.setEditable(true);
        jTextField1.setEditable(true);

        // Eliminar listeners anteriores para evitar duplicación
        for (java.awt.event.ActionListener al : jTextField18.getActionListeners()) {
            jTextField18.removeActionListener(al);
        }

        // Esperar ENTER en jTextField18 para guardar cambios
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int confirmarGuardar = JOptionPane.showConfirmDialog(null,
                    "¿Deseas guardar los cambios?",
                    "Confirmar edición",
                    JOptionPane.YES_NO_OPTION);

                if (confirmarGuardar == JOptionPane.YES_OPTION) {
                    String nuevoUserName = jTextField14.getText();
                    String nuevaContraseña = new String(jPasswordField3.getPassword());
                    String nuevoNombre = jTextField15.getText();
                    String nuevosApellidos = jTextField16.getText();
                    String nuevoDni = jTextField1.getText();
                    String nuevaDireccion = jTextField17.getText();
                    String nuevoCelular = jTextField18.getText();

                    Trabajador nuevo = new Trabajador(usuario, nuevaContraseña, nuevoNombre, nuevosApellidos, nuevoDni, nuevaDireccion, nuevoCelular);

                    boolean actualizado = TrabajadorDAO.actualizarTrabajador(nuevo);

                    if (actualizado) {
                        JOptionPane.showMessageDialog(null, "✅ Datos actualizados correctamente.");
                        cargarPanelesTrabajadores();
                    } else {
                        JOptionPane.showMessageDialog(null, "❌ No se pudo actualizar el trabajador.");
                    }

                    // Bloquear nuevamente los campos
                    jPasswordField3.setEditable(false);
                    jTextField14.setEditable(false);
                    jTextField15.setEditable(false);
                    jTextField16.setEditable(false);
                    jTextField17.setEditable(false);
                    jTextField18.setEditable(false);
                    jTextField1.setEditable(false);
                }
            }
        });
    }
}



                    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    // End of variables declaration//GEN-END:variables
}
