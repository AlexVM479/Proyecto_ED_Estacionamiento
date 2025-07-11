/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.sistemaestacionamiento.gui;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author USER
 */
public class ControlEstacionamiento extends javax.swing.JPanel {

    /**
     * Creates new form ControlEstacionamiento
     */
    public ControlEstacionamiento() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escenariofondo_panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        registraringreso_button = new javax.swing.JButton();
        mostrarparqueo_button = new javax.swing.JButton();

        setForeground(new java.awt.Color(51, 51, 51));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        escenariofondo_panel.setBackground(new java.awt.Color(248, 248, 248));
        escenariofondo_panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(escenariofondo_panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 610, 560));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, -1));

        registraringreso_button.setBackground(new java.awt.Color(79, 79, 79));
        registraringreso_button.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        registraringreso_button.setForeground(new java.awt.Color(204, 204, 204));
        registraringreso_button.setText("Registrar Ingreso");
        registraringreso_button.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        registraringreso_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        registraringreso_button.setDefaultCapable(false);
        registraringreso_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registraringreso_buttonActionPerformed(evt);
            }
        });
        add(registraringreso_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 200, 40));

        mostrarparqueo_button.setBackground(new java.awt.Color(79, 79, 79));
        mostrarparqueo_button.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        mostrarparqueo_button.setForeground(new java.awt.Color(204, 204, 204));
        mostrarparqueo_button.setText("Mostrar Parqueo");
        mostrarparqueo_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mostrarparqueo_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarparqueo_buttonMouseClicked(evt);
            }
        });
        add(mostrarparqueo_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 190, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void registraringreso_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registraringreso_buttonActionPerformed
        CE_Registro_IngresoSalida reging = new CE_Registro_IngresoSalida();
        cambiarPanel(reging);
    }//GEN-LAST:event_registraringreso_buttonActionPerformed

    private void mostrarparqueo_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mostrarparqueo_buttonMouseClicked
        CE_MostrarParqueo parqueo = new CE_MostrarParqueo();
        cambiarPanel(parqueo);
    }//GEN-LAST:event_mostrarparqueo_buttonMouseClicked

    private void cambiarPanel(JPanel p){
        
        p.setSize(this.escenariofondo_panel.getWidth(), this.escenariofondo_panel.getHeight());
        p.setLocation(0, 0);
        
        this.escenariofondo_panel.setLayout(new BorderLayout());
        
        this.escenariofondo_panel.removeAll();
        
        this.escenariofondo_panel.add(p, BorderLayout.CENTER);
        this.escenariofondo_panel.revalidate();
        this.escenariofondo_panel.repaint();
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel escenariofondo_panel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton mostrarparqueo_button;
    private javax.swing.JButton registraringreso_button;
    // End of variables declaration//GEN-END:variables
}
