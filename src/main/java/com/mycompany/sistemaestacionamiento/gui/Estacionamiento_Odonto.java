package com.mycompany.sistemaestacionamiento.gui;

import Registro_Alex.Estacionamiento;
import Registro_Alex.EstacionamientoController;
import com.mycompany.sistemaestacionamiento.Conexion;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Estacionamiento_Odonto extends javax.swing.JPanel {
    
    private List<Estacionamiento> estacionamientos = new ArrayList<>();
    private EstacionamientoController estacionamientoController = new EstacionamientoController();

    public Estacionamiento_Odonto() {
        initComponents();
        cargarEstacionamientos(); // Cargar los estacionamientos al iniciar
        actualizarEstadoEspacios(); // Actualizar el estado de los espacios
    }

    private void cargarEstacionamientos() {
        try (Connection conn = Conexion.conectar()) {
            // Consulta SQL para obtener estacionamientos con estado = 0 (vacío)
            String sql = "SELECT * FROM ESTACIONAMIENTO WHERE lugar LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String lugar = "Odontologia";  // O el lugar que corresponda, por ejemplo "Comedor"
            stmt.setString(1, lugar + "%");  // Concatenar "%" para hacer la búsqueda por lugar
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Asegúrate de usar el nombre correcto de la columna: 'ID_Estacionamiento'
                int idEstacionamiento = rs.getInt("ID_Estacionamiento");  // Acceso correcto
                String lugarEstacionamiento = rs.getString("lugar");  // Acceso correcto
                int estado = rs.getInt("estado");  // Estado del estacionamiento (0 para vacío, 1 para ocupado)

                // Crear un nuevo objeto Estacionamiento y agregarlo a la lista
                Estacionamiento estacionamiento = new Estacionamiento(idEstacionamiento, lugarEstacionamiento, estado);
                estacionamientos.add(estacionamiento);  // Agregar a la lista de estacionamientos
            }
        } catch (Exception e) {
            System.out.println("Error al obtener estacionamientos: " + e.getMessage());
        }
    }
    
    // Actualizar los colores de los paneles según el estado de cada espacio
    private void actualizarEstadoEspacios() {

        for (int i = 0; i < estacionamientos.size(); i++) {
            Estacionamiento espacio = estacionamientos.get(i);
            JPanel panel = obtenerPanelPorId(espacio.getId());

            if (panel != null) {
                panel.setOpaque(true); // MUY IMPORTANTE: asegura que se pinte el fondo

                if (espacio.getEstado() == 1) {
                    panel.setBackground(new Color(220, 20, 60)); // Rojo Carmesí (ocupado)
                } else {
                    panel.setBackground(new Color(34, 139, 34)); // Verde Bosque (libre)
                }
                panel.setOpaque(true); // Asegura que el fondo se pinte
                panel.repaint();
        }
    }
}    
    // Obtener el panel correspondiente según el ID del espacio
    private JPanel obtenerPanelPorId(int id) {
        switch (id) {
            case 1: return A1;
            case 2: return A2;
            case 3: return A3;
            case 4: return A4;
            case 5: return A5;
            case 6: return A6;
            case 7: return A7;
            case 8: return A8;
            case 9: return A9;
            case 10: return A10;
            case 11: return A11;
            case 12: return A12;
            case 13: return A13;
            case 14: return A14;
            case 15: return A15;
            case 16: return A16;
            case 17: return A17;
            case 18: return A18;
            case 19: return A19;
            case 20: return A20;
            case 21: return A21;
            case 22: return A22;
            case 23: return A23;
            case 24: return A24;
            case 25: return A25;
            case 26: return A26;
            case 27: return A27;
            case 28: return A28;
            case 29: return A29;
            case 30: return A30;
            case 31: return A31;
            case 32: return A32;
            case 33: return A33;
            case 34: return A34;
            case 35: return A35;
            case 36: return A36;
            case 37: return A37;
            case 38: return A38;
            case 39: return A39;
            case 40: return A40;
            case 41: return A41;
            case 42: return A42;
            case 43: return A43;
            case 44: return A44;
            case 45: return A45;
            case 46: return A46;
            case 47: return A47;
            case 48: return A48;
            case 49: return A49;
            case 50: return A50;
            case 51: return A51;
            case 52: return A52;
            case 53: return A53;
            case 54: return A54;
            case 55: return A55;
            case 56: return A56;
            case 57: return A57;
            case 58: return A58;
            case 59: return A59;
            case 60: return A60;
            case 61: return A61;
            case 62: return A62;
            case 63: return A63;
            case 64: return A64;
            case 65: return A65;
            case 66: return A66;
            default: return null;
        }
    }
    
    // Método para que se actualice el estado de los espacios al ingresar o salir un vehículo
    public void actualizarEstadoDeEspacio(int id, int estado) {
        Estacionamiento espacio = estacionamientos.get(id - 1); // Asegúrate de que el ID empieza en 1
        espacio.setEstado(estado);
        actualizarEstadoEspacios();
        }

    public boolean registrarSalida(String placa) {
    try (Connection conn = Conexion.conectar()) {
        // Obtener el historial de ingreso
        String sql = "SELECT * FROM HISTORIAL_ESTACIONAMIENTO WHERE Placa = ? AND hora_salida IS NULL";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, placa);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int idEstacionamiento = rs.getInt("ID_Estacionamiento");

            // Actualizar la hora de salida
            String updateSql = "UPDATE HISTORIAL_ESTACIONAMIENTO SET hora_salida = ? WHERE Placa = ? AND hora_salida IS NULL";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setTime(1, java.sql.Time.valueOf(LocalTime.now()));
            updateStmt.setString(2, placa);
            updateStmt.executeUpdate();

            // Actualizar el estado del estacionamiento a libre (estado = 0)
            String updateEstacionamientoSql = "UPDATE ESTACIONAMIENTO SET estado = 0 WHERE ID_Estacionamiento = ?";
            PreparedStatement updateEstacionamientoStmt = conn.prepareStatement(updateEstacionamientoSql);
            updateEstacionamientoStmt.setInt(1, idEstacionamiento);
            updateEstacionamientoStmt.executeUpdate();
            
            return true;
        }
    } catch (Exception e) {
        System.out.println("Error al registrar salida: " + e.getMessage());
    }
    return false;
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        A2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        A1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        A4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        A3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        A6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        A5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        A8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        A7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        A9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        A10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        A15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        A16 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        A19 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        A13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        A14 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        A12 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        A11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        A17 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        A18 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        A24 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        A25 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        A28 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        A22 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        A23 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        A21 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        A20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        A26 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        A27 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        A33 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        A34 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        A37 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        A31 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        A32 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        A30 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        A29 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        A35 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        A36 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        A42 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        A43 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        A46 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        A40 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        A41 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        A39 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        A38 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        A44 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        A45 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        A51 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        A52 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();
        A55 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        A49 = new javax.swing.JPanel();
        jLabel49 = new javax.swing.JLabel();
        A50 = new javax.swing.JPanel();
        jLabel50 = new javax.swing.JLabel();
        A56 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        A48 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        A47 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        A53 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        A54 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        A58 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        A57 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        A60 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        A59 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        A62 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        A61 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        A64 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        A63 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        A66 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        A65 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        A2.setBackground(new java.awt.Color(0, 102, 0));
        A2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A2.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("A2");

        javax.swing.GroupLayout A2Layout = new javax.swing.GroupLayout(A2);
        A2.setLayout(A2Layout);
        A2Layout.setHorizontalGroup(
            A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A2Layout.setVerticalGroup(
            A2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 60, 20));

        A1.setBackground(new java.awt.Color(0, 102, 0));
        A1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A1.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("A1");

        javax.swing.GroupLayout A1Layout = new javax.swing.GroupLayout(A1);
        A1.setLayout(A1Layout);
        A1Layout.setHorizontalGroup(
            A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A1Layout.setVerticalGroup(
            A1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 60, 20));

        A4.setBackground(new java.awt.Color(0, 102, 0));
        A4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A4.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("A4");

        javax.swing.GroupLayout A4Layout = new javax.swing.GroupLayout(A4);
        A4.setLayout(A4Layout);
        A4Layout.setHorizontalGroup(
            A4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A4Layout.setVerticalGroup(
            A4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 60, 20));

        A3.setBackground(new java.awt.Color(0, 102, 0));
        A3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A3.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("A3");

        javax.swing.GroupLayout A3Layout = new javax.swing.GroupLayout(A3);
        A3.setLayout(A3Layout);
        A3Layout.setHorizontalGroup(
            A3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A3Layout.setVerticalGroup(
            A3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A3Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 60, 20));

        A6.setBackground(new java.awt.Color(0, 102, 0));
        A6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A6.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("A6");

        javax.swing.GroupLayout A6Layout = new javax.swing.GroupLayout(A6);
        A6.setLayout(A6Layout);
        A6Layout.setHorizontalGroup(
            A6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A6Layout.setVerticalGroup(
            A6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A6Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 60, 20));

        A5.setBackground(new java.awt.Color(0, 102, 0));
        A5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A5.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("A5");

        javax.swing.GroupLayout A5Layout = new javax.swing.GroupLayout(A5);
        A5.setLayout(A5Layout);
        A5Layout.setHorizontalGroup(
            A5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A5Layout.setVerticalGroup(
            A5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A5Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 60, 20));

        A8.setBackground(new java.awt.Color(0, 102, 0));
        A8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A8.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("A8");

        javax.swing.GroupLayout A8Layout = new javax.swing.GroupLayout(A8);
        A8.setLayout(A8Layout);
        A8Layout.setHorizontalGroup(
            A8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A8Layout.setVerticalGroup(
            A8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A8Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 60, 20));

        A7.setBackground(new java.awt.Color(0, 102, 0));
        A7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A7.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("A7");

        javax.swing.GroupLayout A7Layout = new javax.swing.GroupLayout(A7);
        A7.setLayout(A7Layout);
        A7Layout.setHorizontalGroup(
            A7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A7Layout.setVerticalGroup(
            A7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A7Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 60, 20));

        A9.setBackground(new java.awt.Color(0, 102, 0));
        A9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A9.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("A9");

        javax.swing.GroupLayout A9Layout = new javax.swing.GroupLayout(A9);
        A9.setLayout(A9Layout);
        A9Layout.setHorizontalGroup(
            A9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A9Layout.setVerticalGroup(
            A9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel9))
        );

        add(A9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 60, 20));

        A10.setBackground(new java.awt.Color(0, 102, 0));
        A10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A10.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("A10");

        javax.swing.GroupLayout A10Layout = new javax.swing.GroupLayout(A10);
        A10.setLayout(A10Layout);
        A10Layout.setHorizontalGroup(
            A10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A10Layout.setVerticalGroup(
            A10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10))
        );

        add(A10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 60, 20));

        A15.setBackground(new java.awt.Color(0, 102, 0));
        A15.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A15.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("A15");

        javax.swing.GroupLayout A15Layout = new javax.swing.GroupLayout(A15);
        A15.setLayout(A15Layout);
        A15Layout.setHorizontalGroup(
            A15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A15Layout.setVerticalGroup(
            A15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A15Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 60, 20));

        A16.setBackground(new java.awt.Color(0, 102, 0));
        A16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A16.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("A16");

        javax.swing.GroupLayout A16Layout = new javax.swing.GroupLayout(A16);
        A16.setLayout(A16Layout);
        A16Layout.setHorizontalGroup(
            A16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A16Layout.setVerticalGroup(
            A16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A16Layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, 60, 20));

        A19.setBackground(new java.awt.Color(0, 102, 0));
        A19.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A19.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel19.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("A19");

        javax.swing.GroupLayout A19Layout = new javax.swing.GroupLayout(A19);
        A19.setLayout(A19Layout);
        A19Layout.setHorizontalGroup(
            A19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A19Layout.setVerticalGroup(
            A19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A19Layout.createSequentialGroup()
                .addComponent(jLabel19)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A19, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 60, 20));

        A13.setBackground(new java.awt.Color(0, 102, 0));
        A13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A13.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("A13");

        javax.swing.GroupLayout A13Layout = new javax.swing.GroupLayout(A13);
        A13.setLayout(A13Layout);
        A13Layout.setHorizontalGroup(
            A13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A13Layout.setVerticalGroup(
            A13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A13Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A13, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 60, 20));

        A14.setBackground(new java.awt.Color(0, 102, 0));
        A14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A14.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("A14");

        javax.swing.GroupLayout A14Layout = new javax.swing.GroupLayout(A14);
        A14.setLayout(A14Layout);
        A14Layout.setHorizontalGroup(
            A14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A14Layout.setVerticalGroup(
            A14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A14Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 60, 20));

        A12.setBackground(new java.awt.Color(0, 102, 0));
        A12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A12.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("A12");

        javax.swing.GroupLayout A12Layout = new javax.swing.GroupLayout(A12);
        A12.setLayout(A12Layout);
        A12Layout.setHorizontalGroup(
            A12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A12Layout.setVerticalGroup(
            A12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A12Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 60, 20));

        A11.setBackground(new java.awt.Color(0, 102, 0));
        A11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A11.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("A11");

        javax.swing.GroupLayout A11Layout = new javax.swing.GroupLayout(A11);
        A11.setLayout(A11Layout);
        A11Layout.setHorizontalGroup(
            A11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A11Layout.setVerticalGroup(
            A11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A11Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 60, 20));

        A17.setBackground(new java.awt.Color(0, 102, 0));
        A17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A17.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("A17");

        javax.swing.GroupLayout A17Layout = new javax.swing.GroupLayout(A17);
        A17.setLayout(A17Layout);
        A17Layout.setHorizontalGroup(
            A17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A17Layout.setVerticalGroup(
            A17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A17Layout.createSequentialGroup()
                .addComponent(jLabel17)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 60, 20));

        A18.setBackground(new java.awt.Color(0, 102, 0));
        A18.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A18.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("A18");

        javax.swing.GroupLayout A18Layout = new javax.swing.GroupLayout(A18);
        A18.setLayout(A18Layout);
        A18Layout.setHorizontalGroup(
            A18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A18Layout.setVerticalGroup(
            A18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A18Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A18, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 60, 20));

        A24.setBackground(new java.awt.Color(0, 102, 0));
        A24.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A24.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel24.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("A24");

        javax.swing.GroupLayout A24Layout = new javax.swing.GroupLayout(A24);
        A24.setLayout(A24Layout);
        A24Layout.setHorizontalGroup(
            A24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A24Layout.setVerticalGroup(
            A24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A24Layout.createSequentialGroup()
                .addComponent(jLabel24)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A24, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 60, 20));

        A25.setBackground(new java.awt.Color(0, 102, 0));
        A25.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A25.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel25.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("A25");

        javax.swing.GroupLayout A25Layout = new javax.swing.GroupLayout(A25);
        A25.setLayout(A25Layout);
        A25Layout.setHorizontalGroup(
            A25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A25Layout.setVerticalGroup(
            A25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A25Layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A25, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 230, 60, 20));

        A28.setBackground(new java.awt.Color(0, 102, 0));
        A28.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A28.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel28.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("A28");

        javax.swing.GroupLayout A28Layout = new javax.swing.GroupLayout(A28);
        A28.setLayout(A28Layout);
        A28Layout.setHorizontalGroup(
            A28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A28Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A28Layout.setVerticalGroup(
            A28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A28Layout.createSequentialGroup()
                .addComponent(jLabel28)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A28, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 320, 60, 20));

        A22.setBackground(new java.awt.Color(0, 102, 0));
        A22.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A22.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel22.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("A22");

        javax.swing.GroupLayout A22Layout = new javax.swing.GroupLayout(A22);
        A22.setLayout(A22Layout);
        A22Layout.setHorizontalGroup(
            A22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A22Layout.setVerticalGroup(
            A22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A22Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A22, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 60, 20));

        A23.setBackground(new java.awt.Color(0, 102, 0));
        A23.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A23.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel23.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("A23");

        javax.swing.GroupLayout A23Layout = new javax.swing.GroupLayout(A23);
        A23.setLayout(A23Layout);
        A23Layout.setHorizontalGroup(
            A23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A23Layout.setVerticalGroup(
            A23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A23Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A23, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 170, 60, 20));

        A21.setBackground(new java.awt.Color(0, 102, 0));
        A21.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A21.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel21.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("A21");

        javax.swing.GroupLayout A21Layout = new javax.swing.GroupLayout(A21);
        A21.setLayout(A21Layout);
        A21Layout.setHorizontalGroup(
            A21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A21Layout.setVerticalGroup(
            A21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A21Layout.createSequentialGroup()
                .addComponent(jLabel21)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A21, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 60, 20));

        A20.setBackground(new java.awt.Color(0, 102, 0));
        A20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A20.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel20.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("A20");

        javax.swing.GroupLayout A20Layout = new javax.swing.GroupLayout(A20);
        A20.setLayout(A20Layout);
        A20Layout.setHorizontalGroup(
            A20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A20Layout.setVerticalGroup(
            A20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A20Layout.createSequentialGroup()
                .addComponent(jLabel20)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A20, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 60, 20));

        A26.setBackground(new java.awt.Color(0, 102, 0));
        A26.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A26.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel26.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("A26");

        javax.swing.GroupLayout A26Layout = new javax.swing.GroupLayout(A26);
        A26.setLayout(A26Layout);
        A26Layout.setHorizontalGroup(
            A26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A26Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A26Layout.setVerticalGroup(
            A26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A26Layout.createSequentialGroup()
                .addComponent(jLabel26)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A26, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 60, 20));

        A27.setBackground(new java.awt.Color(0, 102, 0));
        A27.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A27.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel27.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("A27");

        javax.swing.GroupLayout A27Layout = new javax.swing.GroupLayout(A27);
        A27.setLayout(A27Layout);
        A27Layout.setHorizontalGroup(
            A27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A27Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A27Layout.setVerticalGroup(
            A27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A27Layout.createSequentialGroup()
                .addComponent(jLabel27)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A27, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, 60, 20));

        A33.setBackground(new java.awt.Color(0, 102, 0));
        A33.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A33.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel33.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("A33");

        javax.swing.GroupLayout A33Layout = new javax.swing.GroupLayout(A33);
        A33.setLayout(A33Layout);
        A33Layout.setHorizontalGroup(
            A33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A33Layout.setVerticalGroup(
            A33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A33Layout.createSequentialGroup()
                .addComponent(jLabel33)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A33, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 60, 20));

        A34.setBackground(new java.awt.Color(0, 102, 0));
        A34.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A34.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel34.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("A34");

        javax.swing.GroupLayout A34Layout = new javax.swing.GroupLayout(A34);
        A34.setLayout(A34Layout);
        A34Layout.setHorizontalGroup(
            A34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A34Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A34Layout.setVerticalGroup(
            A34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A34Layout.createSequentialGroup()
                .addComponent(jLabel34)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A34, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 60, 20));

        A37.setBackground(new java.awt.Color(0, 102, 0));
        A37.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A37.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel37.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("A37");

        javax.swing.GroupLayout A37Layout = new javax.swing.GroupLayout(A37);
        A37.setLayout(A37Layout);
        A37Layout.setHorizontalGroup(
            A37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A37Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A37Layout.setVerticalGroup(
            A37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A37Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel37))
        );

        add(A37, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, 60, 20));

        A31.setBackground(new java.awt.Color(0, 102, 0));
        A31.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A31.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel31.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("A31");

        javax.swing.GroupLayout A31Layout = new javax.swing.GroupLayout(A31);
        A31.setLayout(A31Layout);
        A31Layout.setHorizontalGroup(
            A31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A31Layout.setVerticalGroup(
            A31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A31Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A31, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 60, 20));

        A32.setBackground(new java.awt.Color(0, 102, 0));
        A32.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A32.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel32.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("A32");

        javax.swing.GroupLayout A32Layout = new javax.swing.GroupLayout(A32);
        A32.setLayout(A32Layout);
        A32Layout.setHorizontalGroup(
            A32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A32Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A32Layout.setVerticalGroup(
            A32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A32Layout.createSequentialGroup()
                .addComponent(jLabel32)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A32, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 60, 20));

        A30.setBackground(new java.awt.Color(0, 102, 0));
        A30.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A30.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel30.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("A30");

        javax.swing.GroupLayout A30Layout = new javax.swing.GroupLayout(A30);
        A30.setLayout(A30Layout);
        A30Layout.setHorizontalGroup(
            A30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A30Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A30Layout.setVerticalGroup(
            A30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A30Layout.createSequentialGroup()
                .addComponent(jLabel30)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A30, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 60, 20));

        A29.setBackground(new java.awt.Color(0, 102, 0));
        A29.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A29.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel29.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("A29");

        javax.swing.GroupLayout A29Layout = new javax.swing.GroupLayout(A29);
        A29.setLayout(A29Layout);
        A29Layout.setHorizontalGroup(
            A29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A29Layout.setVerticalGroup(
            A29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A29Layout.createSequentialGroup()
                .addComponent(jLabel29)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A29, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 60, 20));

        A35.setBackground(new java.awt.Color(0, 102, 0));
        A35.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A35.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel35.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("A35");

        javax.swing.GroupLayout A35Layout = new javax.swing.GroupLayout(A35);
        A35.setLayout(A35Layout);
        A35Layout.setHorizontalGroup(
            A35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A35Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A35Layout.setVerticalGroup(
            A35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A35Layout.createSequentialGroup()
                .addComponent(jLabel35)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A35, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 60, 20));

        A36.setBackground(new java.awt.Color(0, 102, 0));
        A36.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A36.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel36.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("A36");

        javax.swing.GroupLayout A36Layout = new javax.swing.GroupLayout(A36);
        A36.setLayout(A36Layout);
        A36Layout.setHorizontalGroup(
            A36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A36Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A36Layout.setVerticalGroup(
            A36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A36Layout.createSequentialGroup()
                .addComponent(jLabel36)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A36, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 60, 20));

        A42.setBackground(new java.awt.Color(0, 102, 0));
        A42.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A42.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel42.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("A42");

        javax.swing.GroupLayout A42Layout = new javax.swing.GroupLayout(A42);
        A42.setLayout(A42Layout);
        A42Layout.setHorizontalGroup(
            A42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A42Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A42Layout.setVerticalGroup(
            A42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A42Layout.createSequentialGroup()
                .addComponent(jLabel42)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A42, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 60, 20));

        A43.setBackground(new java.awt.Color(0, 102, 0));
        A43.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A43.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel43.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("A43");

        javax.swing.GroupLayout A43Layout = new javax.swing.GroupLayout(A43);
        A43.setLayout(A43Layout);
        A43Layout.setHorizontalGroup(
            A43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A43Layout.setVerticalGroup(
            A43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A43Layout.createSequentialGroup()
                .addComponent(jLabel43)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A43, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 230, 60, 20));

        A46.setBackground(new java.awt.Color(0, 102, 0));
        A46.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A46.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel46.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("A46");

        javax.swing.GroupLayout A46Layout = new javax.swing.GroupLayout(A46);
        A46.setLayout(A46Layout);
        A46Layout.setHorizontalGroup(
            A46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A46Layout.setVerticalGroup(
            A46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A46Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel46))
        );

        add(A46, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 60, 20));

        A40.setBackground(new java.awt.Color(0, 102, 0));
        A40.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A40.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel40.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("A40");

        javax.swing.GroupLayout A40Layout = new javax.swing.GroupLayout(A40);
        A40.setLayout(A40Layout);
        A40Layout.setHorizontalGroup(
            A40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A40Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A40Layout.setVerticalGroup(
            A40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A40Layout.createSequentialGroup()
                .addComponent(jLabel40)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A40, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, 60, 20));

        A41.setBackground(new java.awt.Color(0, 102, 0));
        A41.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A41.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel41.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("A41");

        javax.swing.GroupLayout A41Layout = new javax.swing.GroupLayout(A41);
        A41.setLayout(A41Layout);
        A41Layout.setHorizontalGroup(
            A41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A41Layout.setVerticalGroup(
            A41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A41Layout.createSequentialGroup()
                .addComponent(jLabel41)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A41, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 60, 20));

        A39.setBackground(new java.awt.Color(0, 102, 0));
        A39.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A39.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel39.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("A39");

        javax.swing.GroupLayout A39Layout = new javax.swing.GroupLayout(A39);
        A39.setLayout(A39Layout);
        A39Layout.setHorizontalGroup(
            A39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A39Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A39Layout.setVerticalGroup(
            A39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A39Layout.createSequentialGroup()
                .addComponent(jLabel39)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A39, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 60, 20));

        A38.setBackground(new java.awt.Color(0, 102, 0));
        A38.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A38.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel38.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("A38");

        javax.swing.GroupLayout A38Layout = new javax.swing.GroupLayout(A38);
        A38.setLayout(A38Layout);
        A38Layout.setHorizontalGroup(
            A38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A38Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A38Layout.setVerticalGroup(
            A38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A38Layout.createSequentialGroup()
                .addComponent(jLabel38)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A38, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 60, 20));

        A44.setBackground(new java.awt.Color(0, 102, 0));
        A44.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A44.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel44.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("A44");

        javax.swing.GroupLayout A44Layout = new javax.swing.GroupLayout(A44);
        A44.setLayout(A44Layout);
        A44Layout.setHorizontalGroup(
            A44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A44Layout.setVerticalGroup(
            A44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A44Layout.createSequentialGroup()
                .addComponent(jLabel44)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A44, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 60, 20));

        A45.setBackground(new java.awt.Color(0, 102, 0));
        A45.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A45.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel45.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("A45");

        javax.swing.GroupLayout A45Layout = new javax.swing.GroupLayout(A45);
        A45.setLayout(A45Layout);
        A45Layout.setHorizontalGroup(
            A45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A45Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A45Layout.setVerticalGroup(
            A45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A45Layout.createSequentialGroup()
                .addComponent(jLabel45)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A45, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 60, 20));

        A51.setBackground(new java.awt.Color(0, 102, 0));
        A51.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A51.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel51.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("A51");

        javax.swing.GroupLayout A51Layout = new javax.swing.GroupLayout(A51);
        A51.setLayout(A51Layout);
        A51Layout.setHorizontalGroup(
            A51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A51Layout.setVerticalGroup(
            A51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A51Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel51))
        );

        add(A51, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 60, 20));

        A52.setBackground(new java.awt.Color(0, 102, 0));
        A52.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A52.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel52.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("A52");

        javax.swing.GroupLayout A52Layout = new javax.swing.GroupLayout(A52);
        A52.setLayout(A52Layout);
        A52Layout.setHorizontalGroup(
            A52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A52Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A52Layout.setVerticalGroup(
            A52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A52Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel52))
        );

        add(A52, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 60, 20));

        A55.setBackground(new java.awt.Color(0, 102, 0));
        A55.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A55.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel55.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("A55");

        javax.swing.GroupLayout A55Layout = new javax.swing.GroupLayout(A55);
        A55.setLayout(A55Layout);
        A55Layout.setHorizontalGroup(
            A55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A55Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A55Layout.setVerticalGroup(
            A55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A55Layout.createSequentialGroup()
                .addComponent(jLabel55)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A55, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 320, 60, 20));

        A49.setBackground(new java.awt.Color(0, 102, 0));
        A49.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A49.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel49.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("A49");

        javax.swing.GroupLayout A49Layout = new javax.swing.GroupLayout(A49);
        A49.setLayout(A49Layout);
        A49Layout.setHorizontalGroup(
            A49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A49Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A49Layout.setVerticalGroup(
            A49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A49Layout.createSequentialGroup()
                .addComponent(jLabel49)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A49, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 140, 60, 20));

        A50.setBackground(new java.awt.Color(0, 102, 0));
        A50.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A50.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel50.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("A50");

        javax.swing.GroupLayout A50Layout = new javax.swing.GroupLayout(A50);
        A50.setLayout(A50Layout);
        A50Layout.setHorizontalGroup(
            A50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A50Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A50Layout.setVerticalGroup(
            A50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A50Layout.createSequentialGroup()
                .addComponent(jLabel50)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A50, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 170, 60, 20));

        A56.setBackground(new java.awt.Color(0, 102, 0));
        A56.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A56.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel56.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 255, 255));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("A56");

        javax.swing.GroupLayout A56Layout = new javax.swing.GroupLayout(A56);
        A56.setLayout(A56Layout);
        A56Layout.setHorizontalGroup(
            A56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A56Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A56Layout.setVerticalGroup(
            A56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A56Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel56))
        );

        add(A56, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 60, 20));

        A48.setBackground(new java.awt.Color(0, 102, 0));
        A48.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A48.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel48.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("A48");

        javax.swing.GroupLayout A48Layout = new javax.swing.GroupLayout(A48);
        A48.setLayout(A48Layout);
        A48Layout.setHorizontalGroup(
            A48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A48Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A48Layout.setVerticalGroup(
            A48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A48Layout.createSequentialGroup()
                .addComponent(jLabel48)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A48, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 60, 20));

        A47.setBackground(new java.awt.Color(0, 102, 0));
        A47.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A47.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel47.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("A47");

        javax.swing.GroupLayout A47Layout = new javax.swing.GroupLayout(A47);
        A47.setLayout(A47Layout);
        A47Layout.setHorizontalGroup(
            A47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A47Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        A47Layout.setVerticalGroup(
            A47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A47Layout.createSequentialGroup()
                .addComponent(jLabel47)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        add(A47, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 60, 20));

        A53.setBackground(new java.awt.Color(0, 102, 0));
        A53.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A53.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel53.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("A53");

        javax.swing.GroupLayout A53Layout = new javax.swing.GroupLayout(A53);
        A53.setLayout(A53Layout);
        A53Layout.setHorizontalGroup(
            A53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A53Layout.setVerticalGroup(
            A53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(A53, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 60, 20));

        A54.setBackground(new java.awt.Color(0, 102, 0));
        A54.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A54.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel54.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("A54");

        javax.swing.GroupLayout A54Layout = new javax.swing.GroupLayout(A54);
        A54.setLayout(A54Layout);
        A54Layout.setHorizontalGroup(
            A54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
        );
        A54Layout.setVerticalGroup(
            A54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A54Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel54))
        );

        add(A54, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 60, 20));

        A58.setBackground(new java.awt.Color(0, 102, 0));
        A58.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A58.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel58.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("A58");

        javax.swing.GroupLayout A58Layout = new javax.swing.GroupLayout(A58);
        A58.setLayout(A58Layout);
        A58Layout.setHorizontalGroup(
            A58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A58Layout.createSequentialGroup()
                .addComponent(jLabel58)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        A58Layout.setVerticalGroup(
            A58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A58Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A58, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 380, 30, 60));

        A57.setBackground(new java.awt.Color(0, 102, 0));
        A57.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A57.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel57.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("A57");

        javax.swing.GroupLayout A57Layout = new javax.swing.GroupLayout(A57);
        A57.setLayout(A57Layout);
        A57Layout.setHorizontalGroup(
            A57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A57Layout.createSequentialGroup()
                .addComponent(jLabel57)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        A57Layout.setVerticalGroup(
            A57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A57Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A57, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 30, 60));

        A60.setBackground(new java.awt.Color(0, 102, 0));
        A60.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A60.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel60.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("A60");

        javax.swing.GroupLayout A60Layout = new javax.swing.GroupLayout(A60);
        A60.setLayout(A60Layout);
        A60Layout.setHorizontalGroup(
            A60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A60Layout.createSequentialGroup()
                .addComponent(jLabel60)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        A60Layout.setVerticalGroup(
            A60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A60Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A60, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 30, 60));

        A59.setBackground(new java.awt.Color(0, 102, 0));
        A59.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A59.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel59.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel59.setText("A59");

        javax.swing.GroupLayout A59Layout = new javax.swing.GroupLayout(A59);
        A59.setLayout(A59Layout);
        A59Layout.setHorizontalGroup(
            A59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A59Layout.createSequentialGroup()
                .addComponent(jLabel59)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        A59Layout.setVerticalGroup(
            A59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A59Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A59, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, 30, 60));

        A62.setBackground(new java.awt.Color(0, 102, 0));
        A62.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A62.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel62.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("A62");

        javax.swing.GroupLayout A62Layout = new javax.swing.GroupLayout(A62);
        A62.setLayout(A62Layout);
        A62Layout.setHorizontalGroup(
            A62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A62Layout.createSequentialGroup()
                .addComponent(jLabel62)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        A62Layout.setVerticalGroup(
            A62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A62Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A62, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 30, 60));

        A61.setBackground(new java.awt.Color(0, 102, 0));
        A61.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A61.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel61.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("A61");

        javax.swing.GroupLayout A61Layout = new javax.swing.GroupLayout(A61);
        A61.setLayout(A61Layout);
        A61Layout.setHorizontalGroup(
            A61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A61Layout.createSequentialGroup()
                .addComponent(jLabel61)
                .addGap(0, 26, Short.MAX_VALUE))
        );
        A61Layout.setVerticalGroup(
            A61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A61Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A61, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 30, 60));

        A64.setBackground(new java.awt.Color(0, 102, 0));
        A64.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A64.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel64.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("A64");

        javax.swing.GroupLayout A64Layout = new javax.swing.GroupLayout(A64);
        A64.setLayout(A64Layout);
        A64Layout.setHorizontalGroup(
            A64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A64Layout.createSequentialGroup()
                .addComponent(jLabel64)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        A64Layout.setVerticalGroup(
            A64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A64Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A64, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, 30, 60));

        A63.setBackground(new java.awt.Color(0, 102, 0));
        A63.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A63.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel63.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("A63");

        javax.swing.GroupLayout A63Layout = new javax.swing.GroupLayout(A63);
        A63.setLayout(A63Layout);
        A63Layout.setHorizontalGroup(
            A63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A63Layout.createSequentialGroup()
                .addComponent(jLabel63)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        A63Layout.setVerticalGroup(
            A63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A63Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A63, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 30, 60));

        A66.setBackground(new java.awt.Color(0, 102, 0));
        A66.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A66.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel66.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel66.setText("A66");

        javax.swing.GroupLayout A66Layout = new javax.swing.GroupLayout(A66);
        A66.setLayout(A66Layout);
        A66Layout.setHorizontalGroup(
            A66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A66Layout.createSequentialGroup()
                .addComponent(jLabel66)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        A66Layout.setVerticalGroup(
            A66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A66Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A66, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 30, 60));

        A65.setBackground(new java.awt.Color(0, 102, 0));
        A65.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        A65.setMinimumSize(new java.awt.Dimension(55, 22));

        jLabel65.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel65.setText("A65");

        javax.swing.GroupLayout A65Layout = new javax.swing.GroupLayout(A65);
        A65.setLayout(A65Layout);
        A65Layout.setHorizontalGroup(
            A65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(A65Layout.createSequentialGroup()
                .addComponent(jLabel65)
                .addGap(0, 25, Short.MAX_VALUE))
        );
        A65Layout.setVerticalGroup(
            A65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, A65Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(A65, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 380, 30, 60));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel A1;
    private javax.swing.JPanel A10;
    private javax.swing.JPanel A11;
    private javax.swing.JPanel A12;
    private javax.swing.JPanel A13;
    private javax.swing.JPanel A14;
    private javax.swing.JPanel A15;
    private javax.swing.JPanel A16;
    private javax.swing.JPanel A17;
    private javax.swing.JPanel A18;
    private javax.swing.JPanel A19;
    private javax.swing.JPanel A2;
    private javax.swing.JPanel A20;
    private javax.swing.JPanel A21;
    private javax.swing.JPanel A22;
    private javax.swing.JPanel A23;
    private javax.swing.JPanel A24;
    private javax.swing.JPanel A25;
    private javax.swing.JPanel A26;
    private javax.swing.JPanel A27;
    private javax.swing.JPanel A28;
    private javax.swing.JPanel A29;
    private javax.swing.JPanel A3;
    private javax.swing.JPanel A30;
    private javax.swing.JPanel A31;
    private javax.swing.JPanel A32;
    private javax.swing.JPanel A33;
    private javax.swing.JPanel A34;
    private javax.swing.JPanel A35;
    private javax.swing.JPanel A36;
    private javax.swing.JPanel A37;
    private javax.swing.JPanel A38;
    private javax.swing.JPanel A39;
    private javax.swing.JPanel A4;
    private javax.swing.JPanel A40;
    private javax.swing.JPanel A41;
    private javax.swing.JPanel A42;
    private javax.swing.JPanel A43;
    private javax.swing.JPanel A44;
    private javax.swing.JPanel A45;
    private javax.swing.JPanel A46;
    private javax.swing.JPanel A47;
    private javax.swing.JPanel A48;
    private javax.swing.JPanel A49;
    private javax.swing.JPanel A5;
    private javax.swing.JPanel A50;
    private javax.swing.JPanel A51;
    private javax.swing.JPanel A52;
    private javax.swing.JPanel A53;
    private javax.swing.JPanel A54;
    private javax.swing.JPanel A55;
    private javax.swing.JPanel A56;
    private javax.swing.JPanel A57;
    private javax.swing.JPanel A58;
    private javax.swing.JPanel A59;
    private javax.swing.JPanel A6;
    private javax.swing.JPanel A60;
    private javax.swing.JPanel A61;
    private javax.swing.JPanel A62;
    private javax.swing.JPanel A63;
    private javax.swing.JPanel A64;
    private javax.swing.JPanel A65;
    private javax.swing.JPanel A66;
    private javax.swing.JPanel A7;
    private javax.swing.JPanel A8;
    private javax.swing.JPanel A9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}