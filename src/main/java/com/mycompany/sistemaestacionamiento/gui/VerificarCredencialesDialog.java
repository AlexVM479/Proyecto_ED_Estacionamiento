
package com.mycompany.sistemaestacionamiento.gui;

import java.awt.Image;
import java.net.URL;
import javax.swing.*;
import java.awt.*;
import codigos_jos.LoginController;
import codigos_jos.Sesion;
import codigos_jos.Trabajador;
import codigos_jos.VerificacionAcceso;


public class VerificarCredencialesDialog extends javax.swing.JDialog  {
    private boolean accesoPermitido = false;
    
    private LoginController loginControl;


public VerificarCredencialesDialog(JFrame parent) {
    super(parent, true);
    initComponents();
    loginControl = new LoginController();
    SetImageLabel(login_fondo, "imagenes/login_fondo.png");
    pack();
    setLocationRelativeTo(parent); 
}

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        login_title = new javax.swing.JLabel();
        usuario_title = new javax.swing.JLabel();
        contrasena_title = new javax.swing.JLabel();
        usuario_txt = new javax.swing.JTextField();
        contrasena_txt = new javax.swing.JPasswordField();
        aviso_contrasena = new javax.swing.JLabel();
        aviso_usuario = new javax.swing.JLabel();
        login_fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        login_title.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        login_title.setForeground(new java.awt.Color(40, 40, 146));
        login_title.setText("Confirmar Usuario");
        jPanel2.add(login_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 220, 30));

        usuario_title.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        usuario_title.setForeground(new java.awt.Color(95, 95, 158));
        usuario_title.setText("Usuario:");
        jPanel2.add(usuario_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, -1, -1));

        contrasena_title.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        contrasena_title.setForeground(new java.awt.Color(95, 95, 158));
        contrasena_title.setText("Contraseña:");
        jPanel2.add(contrasena_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        usuario_txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        usuario_txt.setText("Ingrese su nombre de usuario");
        usuario_txt.setBorder(null);
        usuario_txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usuario_txtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usuario_txtFocusLost(evt);
            }
        });
        jPanel2.add(usuario_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 200, 40));

        contrasena_txt.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        contrasena_txt.setText("••••••••••••");
        contrasena_txt.setBorder(null);
        contrasena_txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contrasena_txtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                contrasena_txtFocusLost(evt);
            }
        });
        contrasena_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contrasena_txtActionPerformed(evt);
            }
        });
        jPanel2.add(contrasena_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 200, 40));

        aviso_contrasena.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        aviso_contrasena.setForeground(new java.awt.Color(255, 102, 102));
        jPanel2.add(aviso_contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 200, 20));

        aviso_usuario.setFont(new java.awt.Font("Century Gothic", 0, 11)); // NOI18N
        aviso_usuario.setForeground(new java.awt.Color(255, 102, 102));
        jPanel2.add(aviso_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 200, 20));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 490));

        login_fondo.setText("jLabel1");
        jPanel1.add(login_fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 490));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contrasena_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contrasena_txtActionPerformed
                                                 
    verificarAcceso();  

    }//GEN-LAST:event_contrasena_txtActionPerformed

    private void usuario_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuario_txtFocusGained
        if(this.usuario_txt.getText().equals("Ingrese su nombre de usuario")){
            this.usuario_txt.setText("");
            this.usuario_txt.setForeground(Color.black);
        }
    }//GEN-LAST:event_usuario_txtFocusGained

    private void usuario_txtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuario_txtFocusLost
        
        if(this.usuario_txt.getText().isEmpty()){
            this.usuario_txt.setText("Ingrese su nombre de usuario");
            this.usuario_txt.setForeground(Color.LIGHT_GRAY);
        }
        
    }//GEN-LAST:event_usuario_txtFocusLost

    private void contrasena_txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contrasena_txtFocusGained
        
        if(String.valueOf(contrasena_txt.getPassword()).equals("••••••••••••")){
            this.contrasena_txt.setText("");
            this.contrasena_txt.setForeground(Color.black);
        }
        
    }//GEN-LAST:event_contrasena_txtFocusGained

    private void contrasena_txtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contrasena_txtFocusLost
        
        if(String.valueOf(contrasena_txt.getPassword()).isEmpty()){
            this.contrasena_txt.setText("••••••••••••");
            this.contrasena_txt.setForeground(Color.LIGHT_GRAY);
        }
        
    }//GEN-LAST:event_contrasena_txtFocusLost

    private void SetImageLabel(JLabel labelName, String root) {
        URL imageURL = getClass().getClassLoader().getResource(root);
        if (imageURL != null) {
            ImageIcon image = new ImageIcon(imageURL);
            Icon icon = new ImageIcon(
                image.getImage().getScaledInstance(
                    labelName.getWidth(),
                    labelName.getHeight(),
                    Image.SCALE_SMOOTH
                )
            );
            labelName.setIcon(icon);
            this.repaint();
        } else {
            System.err.println("No se pudo encontrar la imagen: " + root);
    }
}

private void verificarAcceso() {
    String usuarioIngresado = usuario_txt.getText().trim();
    String contraseñaIngresada = new String(contrasena_txt.getPassword()).trim();

    if (VerificacionAcceso.verificarCredenciales(usuarioIngresado, contraseñaIngresada)) {
        accesoPermitido = true;
        dispose(); // Cierra el diálogo
    } else {
        aviso_usuario.setText("Usuario o contraseña incorrectos.");
    }
}


public boolean isAccesoPermitido() {
    return accesoPermitido;
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aviso_contrasena;
    private javax.swing.JLabel aviso_usuario;
    private javax.swing.JLabel contrasena_title;
    private javax.swing.JPasswordField contrasena_txt;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel login_fondo;
    private javax.swing.JLabel login_title;
    private javax.swing.JLabel usuario_title;
    private javax.swing.JTextField usuario_txt;
    // End of variables declaration//GEN-END:variables

}
