
package com.mycompany.sistemaestacionamiento.gui;

import com.mycompany.sistemaestacionamiento.gui.ControlBusqueda;
import com.mycompany.sistemaestacionamiento.gui.ControlEstacionamiento;
import com.mycompany.sistemaestacionamiento.gui.VerificarCredencialesDialog;
import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class Menu extends javax.swing.JFrame {

   
    public Menu() {
        initComponents();
        SetImageLabel(controlestacionamiento_button, "imagenes/controlestacionamiento_button_default.png");
        SetImageLabel(administrarusuarios_button, "imagenes/administrarusuarios_button_default.png");
        SetImageLabel(cerrarsesion_button, "imagenes/cerrarsesion_button_default.png");

    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel_menu = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        controlestacionamiento_button = new javax.swing.JLabel();
        administrarusuarios_button = new javax.swing.JLabel();
        cerrarsesion_button = new javax.swing.JLabel();
        control_busqueda_button = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Estacionamiento");

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_menu.setBackground(new java.awt.Color(255, 255, 255));
        panel_menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(panel_menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 610, 600));

        menu.setBackground(new java.awt.Color(204, 204, 204));
        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        controlestacionamiento_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        controlestacionamiento_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                controlestacionamiento_buttonMouseClicked(evt);
            }
        });
        menu.add(controlestacionamiento_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 150, 50));

        administrarusuarios_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        administrarusuarios_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                administrarusuarios_buttonMouseClicked(evt);
            }
        });
        menu.add(administrarusuarios_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 150, 50));

        cerrarsesion_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu.add(cerrarsesion_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 150, 50));

        control_busqueda_button.setBackground(new java.awt.Color(0, 0, 102));
        control_busqueda_button.setForeground(new java.awt.Color(255, 255, 255));
        control_busqueda_button.setText("Control Búsqueda");
        control_busqueda_button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        control_busqueda_button.setMaximumSize(new java.awt.Dimension(37, 16));
        control_busqueda_button.setMinimumSize(new java.awt.Dimension(37, 16));
        control_busqueda_button.setName(""); // NOI18N
        control_busqueda_button.setPreferredSize(new java.awt.Dimension(37, 16));
        control_busqueda_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                control_busqueda_buttonMouseClicked(evt);
            }
        });
        menu.add(control_busqueda_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 150, 50));

        jPanel1.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void controlestacionamiento_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_controlestacionamiento_buttonMouseClicked
        
        ControlEstacionamiento ctrest = new ControlEstacionamiento();
        cambiarPanel(ctrest);
    }//GEN-LAST:event_controlestacionamiento_buttonMouseClicked

    private void administrarusuarios_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_administrarusuarios_buttonMouseClicked
    System.out.println("Click detectado en Administrar Usuarios");

    try {
        System.out.println("Abriendo diálogo de verificación...");
        VerificarCredencialesDialog verificacion = new VerificarCredencialesDialog(this);
        System.out.println("Diálogo creado");
        verificacion.setLocationRelativeTo(this);
        verificacion.setVisible(true); // bloqueante si es modal
        System.out.println("Diálogo cerrado");

        if (verificacion.isAccesoPermitido()) {
            System.out.println("Acceso permitido. Cargando panel AdministrarUser...");
            AdministrarUser admuser = new AdministrarUser();
            cambiarPanel(admuser);
        } else {
            System.out.println("Acceso denegado: Credenciales no válidas.");
        }
    } catch (Exception e) {
        System.out.println("Error al abrir el diálogo de verificación:");
        e.printStackTrace();
    }
    }//GEN-LAST:event_administrarusuarios_buttonMouseClicked

    private void control_busqueda_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_control_busqueda_buttonMouseClicked
        ControlBusqueda ctrest = new ControlBusqueda();
        cambiarPanel(ctrest);
    }//GEN-LAST:event_control_busqueda_buttonMouseClicked
    
    private void cambiarPanel(JPanel p){
        
        p.setSize(this.panel_menu.getWidth(), this.panel_menu.getHeight());
        p.setLocation(0, 0);
        
        this.panel_menu.setLayout(new BorderLayout());
        
        this.panel_menu.removeAll();
        
        this.panel_menu.add(p, BorderLayout.CENTER);
        this.panel_menu.revalidate();
        this.panel_menu.repaint();
        
    }
    
    private void SetImageLabel(JLabel label, String path) {
    URL imageURL = getClass().getClassLoader().getResource(path);
    if (imageURL != null) {
        ImageIcon image = new ImageIcon(imageURL);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        label.setIcon(icon);
        this.repaint();
    } else {
        System.err.println("No se pudo encontrar la imagen: " + path);
    }
}
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel administrarusuarios_button;
    private javax.swing.JLabel cerrarsesion_button;
    private javax.swing.JLabel control_busqueda_button;
    private javax.swing.JLabel controlestacionamiento_button;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel panel_menu;
    // End of variables declaration//GEN-END:variables

}
