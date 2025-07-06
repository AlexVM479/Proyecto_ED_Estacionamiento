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

public class Estacionamiento_Comedor extends javax.swing.JPanel {

    private List<Estacionamiento> estacionamientos = new ArrayList<>();
    private EstacionamientoController estacionamientoController = new EstacionamientoController();

    public Estacionamiento_Comedor() {
        initComponents();
        cargarEstacionamientos();
        actualizarEstadoEspacios();
    }

    private void cargarEstacionamientos() {
        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT * FROM ESTACIONAMIENTO WHERE lugar LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            String lugar = "Comedor";
            stmt.setString(1, lugar + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int idEstacionamiento = rs.getInt("ID_Estacionamiento");
                String lugarEstacionamiento = rs.getString("lugar");
                int estado = rs.getInt("estado");
                Estacionamiento estacionamiento = new Estacionamiento(idEstacionamiento, lugarEstacionamiento, estado);
                estacionamientos.add(estacionamiento);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener estacionamientos: " + e.getMessage());
        }
    }

    private void actualizarEstadoEspacios() {
        for (int i = 0; i < estacionamientos.size(); i++) {
            Estacionamiento espacio = estacionamientos.get(i);
            JPanel panel = obtenerPanelPorId(espacio.getId());

            if (panel != null) {
                panel.setOpaque(true);
                if (espacio.getEstado() == 1) {
                    panel.setBackground(new Color(220, 20, 60)); // Rojo Carmesí
                } else {
                    panel.setBackground(new Color(34, 139, 34)); // Verde Bosque
                }
                panel.repaint();
            }
        }
    }

    private JPanel obtenerPanelPorId(int id) {
        switch (id) {
            case 67: return B1;
            case 68: return B2;
            case 69: return B3;
            case 70: return B4;
            case 71: return B5;
            case 72: return B6;
            case 73: return B7;
            case 74: return B8;
            case 75: return B9;
            case 76: return B10;
            case 77: return B11;
            case 78: return B12;
            case 79: return B13;
            case 80: return B14;
            case 81: return B15;
            case 82: return B16;
            case 83: return B17;
            case 84: return B18;
            case 85: return B19;
            case 86: return B20;
            case 87: return B21;
            case 88: return B22;
            case 89: return B23;
            case 90: return B24;
            case 91: return B25;
            case 92: return B26;
            case 93: return B27;
            case 94: return B28;
            case 95: return B29;
            case 96: return B30;
            case 97: return B31;
            case 98: return B32;
            case 99: return B33;
            case 100: return B34;
            case 101: return B35;
            case 102: return B36;
            case 103: return B37;
            case 104: return B38;
            case 105: return B39;
            case 106: return B40;
            case 107: return B41;
            case 108: return B42;
            case 109: return B43;
            case 110: return B44;
            case 111: return B45;
            case 112: return B46;
            case 113: return B47;
            case 114: return B48;
            case 115: return B49;
            case 116: return B50;
            case 117: return B51;
            case 118: return B52;
            case 119: return B53;
            case 120: return B54;
            case 121: return B55;
            case 122: return B56;
            case 123: return B57;
            default: return null;
        }
    }

    public void actualizarEstadoDeEspacio(int id, int estado) {
        Estacionamiento espacio = estacionamientos.get(id - 67); // Asumiendo que los IDs del comedor empiezan en 67
        espacio.setEstado(estado);
        actualizarEstadoEspacios();
    }

    public boolean registrarSalida(String placa) {
        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT * FROM HISTORIAL_ESTACIONAMIENTO WHERE Placa = ? AND hora_salida IS NULL";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idEstacionamiento = rs.getInt("ID_Estacionamiento");
                String updateSql = "UPDATE HISTORIAL_ESTACIONAMIENTO SET hora_salida = ? WHERE Placa = ? AND hora_salida IS NULL";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setTime(1, java.sql.Time.valueOf(LocalTime.now()));
                updateStmt.setString(2, placa);
                updateStmt.executeUpdate();

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

        B2 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        B1 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        B4 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        B3 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        B6 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        B5 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        B8 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        B7 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        B9 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        B11 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        B10 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        B13 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        B12 = new javax.swing.JPanel();
        jLabel78 = new javax.swing.JLabel();
        B14 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        B16 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        B15 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        B18 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        B17 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        B20 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        B19 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        B21 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        B23 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        B22 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        B24 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        B25 = new javax.swing.JPanel();
        jLabel91 = new javax.swing.JLabel();
        B27 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        B26 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        B29 = new javax.swing.JPanel();
        jLabel95 = new javax.swing.JLabel();
        B28 = new javax.swing.JPanel();
        jLabel94 = new javax.swing.JLabel();
        B31 = new javax.swing.JPanel();
        jLabel97 = new javax.swing.JLabel();
        B30 = new javax.swing.JPanel();
        jLabel96 = new javax.swing.JLabel();
        B32 = new javax.swing.JPanel();
        jLabel98 = new javax.swing.JLabel();
        B34 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        B33 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        B35 = new javax.swing.JPanel();
        jLabel101 = new javax.swing.JLabel();
        B36 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        B38 = new javax.swing.JPanel();
        jLabel104 = new javax.swing.JLabel();
        B37 = new javax.swing.JPanel();
        jLabel103 = new javax.swing.JLabel();
        B40 = new javax.swing.JPanel();
        jLabel106 = new javax.swing.JLabel();
        B39 = new javax.swing.JPanel();
        jLabel105 = new javax.swing.JLabel();
        B42 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        B41 = new javax.swing.JPanel();
        jLabel107 = new javax.swing.JLabel();
        B43 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        B45 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        B44 = new javax.swing.JPanel();
        jLabel110 = new javax.swing.JLabel();
        B46 = new javax.swing.JPanel();
        jLabel112 = new javax.swing.JLabel();
        B47 = new javax.swing.JPanel();
        jLabel113 = new javax.swing.JLabel();
        B49 = new javax.swing.JPanel();
        jLabel115 = new javax.swing.JLabel();
        B48 = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        B51 = new javax.swing.JPanel();
        jLabel117 = new javax.swing.JLabel();
        B50 = new javax.swing.JPanel();
        jLabel116 = new javax.swing.JLabel();
        B53 = new javax.swing.JPanel();
        jLabel119 = new javax.swing.JLabel();
        B52 = new javax.swing.JPanel();
        jLabel118 = new javax.swing.JLabel();
        B54 = new javax.swing.JPanel();
        jLabel120 = new javax.swing.JLabel();
        B56 = new javax.swing.JPanel();
        jLabel122 = new javax.swing.JLabel();
        B55 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        B57 = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        B2.setBackground(new java.awt.Color(0, 102, 0));
        B2.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel68.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel68.setText("B2");

        javax.swing.GroupLayout B2Layout = new javax.swing.GroupLayout(B2);
        B2.setLayout(B2Layout);
        B2Layout.setHorizontalGroup(
            B2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B2Layout.setVerticalGroup(
            B2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 30, -1));

        B1.setBackground(new java.awt.Color(0, 102, 0));
        B1.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel67.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel67.setText("B1");

        javax.swing.GroupLayout B1Layout = new javax.swing.GroupLayout(B1);
        B1.setLayout(B1Layout);
        B1Layout.setHorizontalGroup(
            B1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B1Layout.setVerticalGroup(
            B1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 30, -1));

        B4.setBackground(new java.awt.Color(0, 102, 0));
        B4.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel70.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel70.setText("B4");

        javax.swing.GroupLayout B4Layout = new javax.swing.GroupLayout(B4);
        B4.setLayout(B4Layout);
        B4Layout.setHorizontalGroup(
            B4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B4Layout.setVerticalGroup(
            B4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 30, -1));

        B3.setBackground(new java.awt.Color(0, 102, 0));
        B3.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel69.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel69.setText("B3");

        javax.swing.GroupLayout B3Layout = new javax.swing.GroupLayout(B3);
        B3.setLayout(B3Layout);
        B3Layout.setHorizontalGroup(
            B3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B3Layout.setVerticalGroup(
            B3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 30, -1));

        B6.setBackground(new java.awt.Color(0, 102, 0));
        B6.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel72.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 255, 255));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("B6");

        javax.swing.GroupLayout B6Layout = new javax.swing.GroupLayout(B6);
        B6.setLayout(B6Layout);
        B6Layout.setHorizontalGroup(
            B6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B6Layout.setVerticalGroup(
            B6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 80, 30, -1));

        B5.setBackground(new java.awt.Color(0, 102, 0));
        B5.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel71.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("B5");

        javax.swing.GroupLayout B5Layout = new javax.swing.GroupLayout(B5);
        B5.setLayout(B5Layout);
        B5Layout.setHorizontalGroup(
            B5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B5Layout.setVerticalGroup(
            B5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 30, -1));

        B8.setBackground(new java.awt.Color(0, 102, 0));
        B8.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel74.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("B8");

        javax.swing.GroupLayout B8Layout = new javax.swing.GroupLayout(B8);
        B8.setLayout(B8Layout);
        B8Layout.setHorizontalGroup(
            B8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B8Layout.setVerticalGroup(
            B8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 30, -1));

        B7.setBackground(new java.awt.Color(0, 102, 0));
        B7.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel73.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("B7");

        javax.swing.GroupLayout B7Layout = new javax.swing.GroupLayout(B7);
        B7.setLayout(B7Layout);
        B7Layout.setHorizontalGroup(
            B7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B7Layout.setVerticalGroup(
            B7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B7, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 30, -1));

        B9.setBackground(new java.awt.Color(0, 102, 0));
        B9.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel75.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("B9");

        javax.swing.GroupLayout B9Layout = new javax.swing.GroupLayout(B9);
        B9.setLayout(B9Layout);
        B9Layout.setHorizontalGroup(
            B9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B9Layout.setVerticalGroup(
            B9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 30, -1));

        B11.setBackground(new java.awt.Color(0, 102, 0));
        B11.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel77.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel77.setText("B11");

        javax.swing.GroupLayout B11Layout = new javax.swing.GroupLayout(B11);
        B11.setLayout(B11Layout);
        B11Layout.setHorizontalGroup(
            B11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B11Layout.setVerticalGroup(
            B11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 30, -1));

        B10.setBackground(new java.awt.Color(0, 102, 0));
        B10.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel76.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel76.setText("B10");

        javax.swing.GroupLayout B10Layout = new javax.swing.GroupLayout(B10);
        B10.setLayout(B10Layout);
        B10Layout.setHorizontalGroup(
            B10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B10Layout.setVerticalGroup(
            B10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 30, -1));

        B13.setBackground(new java.awt.Color(0, 102, 0));
        B13.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel79.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel79.setText("B13");

        javax.swing.GroupLayout B13Layout = new javax.swing.GroupLayout(B13);
        B13.setLayout(B13Layout);
        B13Layout.setHorizontalGroup(
            B13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B13Layout.setVerticalGroup(
            B13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 80, 30, -1));

        B12.setBackground(new java.awt.Color(0, 102, 0));
        B12.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel78.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel78.setText("B12");

        javax.swing.GroupLayout B12Layout = new javax.swing.GroupLayout(B12);
        B12.setLayout(B12Layout);
        B12Layout.setHorizontalGroup(
            B12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B12Layout.setVerticalGroup(
            B12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 80, 30, -1));

        B14.setBackground(new java.awt.Color(0, 102, 0));
        B14.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel80.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel80.setText("B14");

        javax.swing.GroupLayout B14Layout = new javax.swing.GroupLayout(B14);
        B14.setLayout(B14Layout);
        B14Layout.setHorizontalGroup(
            B14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B14Layout.setVerticalGroup(
            B14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B14Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 30, -1));

        B16.setBackground(new java.awt.Color(0, 102, 0));
        B16.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel82.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("B16");

        javax.swing.GroupLayout B16Layout = new javax.swing.GroupLayout(B16);
        B16.setLayout(B16Layout);
        B16Layout.setHorizontalGroup(
            B16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B16Layout.setVerticalGroup(
            B16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B16, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 30, -1));

        B15.setBackground(new java.awt.Color(0, 102, 0));
        B15.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel81.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel81.setText("B15");

        javax.swing.GroupLayout B15Layout = new javax.swing.GroupLayout(B15);
        B15.setLayout(B15Layout);
        B15Layout.setHorizontalGroup(
            B15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B15Layout.setVerticalGroup(
            B15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 30, -1));

        B18.setBackground(new java.awt.Color(0, 102, 0));
        B18.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel84.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("B18");

        javax.swing.GroupLayout B18Layout = new javax.swing.GroupLayout(B18);
        B18.setLayout(B18Layout);
        B18Layout.setHorizontalGroup(
            B18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B18Layout.setVerticalGroup(
            B18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B18, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, 30, -1));

        B17.setBackground(new java.awt.Color(0, 102, 0));
        B17.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel83.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("B17");

        javax.swing.GroupLayout B17Layout = new javax.swing.GroupLayout(B17);
        B17.setLayout(B17Layout);
        B17Layout.setHorizontalGroup(
            B17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B17Layout.setVerticalGroup(
            B17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B17Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B17, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 30, -1));

        B20.setBackground(new java.awt.Color(0, 102, 0));
        B20.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel86.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel86.setText("B20");

        javax.swing.GroupLayout B20Layout = new javax.swing.GroupLayout(B20);
        B20.setLayout(B20Layout);
        B20Layout.setHorizontalGroup(
            B20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B20Layout.setVerticalGroup(
            B20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B20, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 30, -1));

        B19.setBackground(new java.awt.Color(0, 102, 0));
        B19.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel85.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(255, 255, 255));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel85.setText("B19");

        javax.swing.GroupLayout B19Layout = new javax.swing.GroupLayout(B19);
        B19.setLayout(B19Layout);
        B19Layout.setHorizontalGroup(
            B19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B19Layout.setVerticalGroup(
            B19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B19Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 30, -1));

        B21.setBackground(new java.awt.Color(0, 102, 0));
        B21.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel87.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("B21");

        javax.swing.GroupLayout B21Layout = new javax.swing.GroupLayout(B21);
        B21.setLayout(B21Layout);
        B21Layout.setHorizontalGroup(
            B21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B21Layout.setVerticalGroup(
            B21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B21Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B21, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 30, -1));

        B23.setBackground(new java.awt.Color(0, 102, 0));
        B23.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel89.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("B23");

        javax.swing.GroupLayout B23Layout = new javax.swing.GroupLayout(B23);
        B23.setLayout(B23Layout);
        B23Layout.setHorizontalGroup(
            B23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B23Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B23Layout.setVerticalGroup(
            B23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B23Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B23, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 30, -1));

        B22.setBackground(new java.awt.Color(0, 102, 0));
        B22.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel88.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("B22");

        javax.swing.GroupLayout B22Layout = new javax.swing.GroupLayout(B22);
        B22.setLayout(B22Layout);
        B22Layout.setHorizontalGroup(
            B22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B22Layout.setVerticalGroup(
            B22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B22, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 30, -1));

        B24.setBackground(new java.awt.Color(0, 102, 0));
        B24.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel90.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel90.setText("B24");

        javax.swing.GroupLayout B24Layout = new javax.swing.GroupLayout(B24);
        B24.setLayout(B24Layout);
        B24Layout.setHorizontalGroup(
            B24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B24Layout.setVerticalGroup(
            B24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B24, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 30, -1));

        B25.setBackground(new java.awt.Color(0, 102, 0));
        B25.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel91.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel91.setText("B25");

        javax.swing.GroupLayout B25Layout = new javax.swing.GroupLayout(B25);
        B25.setLayout(B25Layout);
        B25Layout.setHorizontalGroup(
            B25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B25Layout.setVerticalGroup(
            B25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B25Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B25, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 30, -1));

        B27.setBackground(new java.awt.Color(0, 102, 0));
        B27.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel93.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel93.setText("B27");

        javax.swing.GroupLayout B27Layout = new javax.swing.GroupLayout(B27);
        B27.setLayout(B27Layout);
        B27Layout.setHorizontalGroup(
            B27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B27Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B27Layout.setVerticalGroup(
            B27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B27Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B27, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 30, -1));

        B26.setBackground(new java.awt.Color(0, 102, 0));
        B26.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel92.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel92.setText("B26");

        javax.swing.GroupLayout B26Layout = new javax.swing.GroupLayout(B26);
        B26.setLayout(B26Layout);
        B26Layout.setHorizontalGroup(
            B26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B26Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B26Layout.setVerticalGroup(
            B26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B26Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B26, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 30, -1));

        B29.setBackground(new java.awt.Color(0, 102, 0));
        B29.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel95.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel95.setText("B29");

        javax.swing.GroupLayout B29Layout = new javax.swing.GroupLayout(B29);
        B29.setLayout(B29Layout);
        B29Layout.setHorizontalGroup(
            B29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B29Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B29Layout.setVerticalGroup(
            B29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B29Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B29, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 30, -1));

        B28.setBackground(new java.awt.Color(0, 102, 0));
        B28.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel94.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setText("B28");

        javax.swing.GroupLayout B28Layout = new javax.swing.GroupLayout(B28);
        B28.setLayout(B28Layout);
        B28Layout.setHorizontalGroup(
            B28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B28Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B28Layout.setVerticalGroup(
            B28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B28Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B28, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 30, -1));

        B31.setBackground(new java.awt.Color(0, 102, 0));
        B31.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel97.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel97.setText("B31");

        javax.swing.GroupLayout B31Layout = new javax.swing.GroupLayout(B31);
        B31.setLayout(B31Layout);
        B31Layout.setHorizontalGroup(
            B31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B31Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B31Layout.setVerticalGroup(
            B31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B31Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B31, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 30, -1));

        B30.setBackground(new java.awt.Color(0, 102, 0));
        B30.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel96.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel96.setText("B30");

        javax.swing.GroupLayout B30Layout = new javax.swing.GroupLayout(B30);
        B30.setLayout(B30Layout);
        B30Layout.setHorizontalGroup(
            B30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B30Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B30Layout.setVerticalGroup(
            B30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B30Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B30, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 30, -1));

        B32.setBackground(new java.awt.Color(0, 102, 0));
        B32.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel98.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(255, 255, 255));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel98.setText("B32");

        javax.swing.GroupLayout B32Layout = new javax.swing.GroupLayout(B32);
        B32.setLayout(B32Layout);
        B32Layout.setHorizontalGroup(
            B32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B32Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B32Layout.setVerticalGroup(
            B32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B32Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B32, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 30, -1));

        B34.setBackground(new java.awt.Color(0, 102, 0));
        B34.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel100.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(255, 255, 255));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel100.setText("B34");

        javax.swing.GroupLayout B34Layout = new javax.swing.GroupLayout(B34);
        B34.setLayout(B34Layout);
        B34Layout.setHorizontalGroup(
            B34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B34Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B34Layout.setVerticalGroup(
            B34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B34Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B34, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 30, -1));

        B33.setBackground(new java.awt.Color(0, 102, 0));
        B33.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel99.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel99.setText("B33");

        javax.swing.GroupLayout B33Layout = new javax.swing.GroupLayout(B33);
        B33.setLayout(B33Layout);
        B33Layout.setHorizontalGroup(
            B33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B33Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B33Layout.setVerticalGroup(
            B33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B33Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B33, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 30, -1));

        B35.setBackground(new java.awt.Color(0, 102, 0));
        B35.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel101.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel101.setText("B35");

        javax.swing.GroupLayout B35Layout = new javax.swing.GroupLayout(B35);
        B35.setLayout(B35Layout);
        B35Layout.setHorizontalGroup(
            B35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B35Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B35Layout.setVerticalGroup(
            B35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B35Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B35, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 30, -1));

        B36.setBackground(new java.awt.Color(0, 102, 0));
        B36.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel102.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel102.setText("B36");

        javax.swing.GroupLayout B36Layout = new javax.swing.GroupLayout(B36);
        B36.setLayout(B36Layout);
        B36Layout.setHorizontalGroup(
            B36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B36Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B36Layout.setVerticalGroup(
            B36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B36Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B36, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 30, -1));

        B38.setBackground(new java.awt.Color(0, 102, 0));
        B38.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel104.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 255, 255));
        jLabel104.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel104.setText("B38");

        javax.swing.GroupLayout B38Layout = new javax.swing.GroupLayout(B38);
        B38.setLayout(B38Layout);
        B38Layout.setHorizontalGroup(
            B38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B38Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B38Layout.setVerticalGroup(
            B38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B38Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B38, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 320, 30, -1));

        B37.setBackground(new java.awt.Color(0, 102, 0));
        B37.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel103.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel103.setText("B37");

        javax.swing.GroupLayout B37Layout = new javax.swing.GroupLayout(B37);
        B37.setLayout(B37Layout);
        B37Layout.setHorizontalGroup(
            B37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B37Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B37Layout.setVerticalGroup(
            B37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B37Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B37, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 30, -1));

        B40.setBackground(new java.awt.Color(0, 102, 0));
        B40.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel106.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel106.setForeground(new java.awt.Color(255, 255, 255));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel106.setText("B40");

        javax.swing.GroupLayout B40Layout = new javax.swing.GroupLayout(B40);
        B40.setLayout(B40Layout);
        B40Layout.setHorizontalGroup(
            B40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B40Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B40Layout.setVerticalGroup(
            B40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B40Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B40, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 320, 30, -1));

        B39.setBackground(new java.awt.Color(0, 102, 0));
        B39.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel105.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(255, 255, 255));
        jLabel105.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel105.setText("B39");

        javax.swing.GroupLayout B39Layout = new javax.swing.GroupLayout(B39);
        B39.setLayout(B39Layout);
        B39Layout.setHorizontalGroup(
            B39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B39Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B39Layout.setVerticalGroup(
            B39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B39Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B39, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 30, -1));

        B42.setBackground(new java.awt.Color(0, 102, 0));
        B42.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel108.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(255, 255, 255));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel108.setText("B42");

        javax.swing.GroupLayout B42Layout = new javax.swing.GroupLayout(B42);
        B42.setLayout(B42Layout);
        B42Layout.setHorizontalGroup(
            B42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B42Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B42Layout.setVerticalGroup(
            B42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B42Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B42, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, 30, -1));

        B41.setBackground(new java.awt.Color(0, 102, 0));
        B41.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel107.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(255, 255, 255));
        jLabel107.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel107.setText("B41");

        javax.swing.GroupLayout B41Layout = new javax.swing.GroupLayout(B41);
        B41.setLayout(B41Layout);
        B41Layout.setHorizontalGroup(
            B41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B41Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B41Layout.setVerticalGroup(
            B41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B41Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B41, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 30, -1));

        B43.setBackground(new java.awt.Color(0, 102, 0));
        B43.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel109.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 255, 255));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel109.setText("B43");

        javax.swing.GroupLayout B43Layout = new javax.swing.GroupLayout(B43);
        B43.setLayout(B43Layout);
        B43Layout.setHorizontalGroup(
            B43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B43Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B43Layout.setVerticalGroup(
            B43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B43Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B43, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 320, 30, -1));

        B45.setBackground(new java.awt.Color(0, 102, 0));
        B45.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel111.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel111.setText("B45");

        javax.swing.GroupLayout B45Layout = new javax.swing.GroupLayout(B45);
        B45.setLayout(B45Layout);
        B45Layout.setHorizontalGroup(
            B45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B45Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B45Layout.setVerticalGroup(
            B45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B45Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel111, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B45, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 30, -1));

        B44.setBackground(new java.awt.Color(0, 102, 0));
        B44.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel110.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 255, 255));
        jLabel110.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel110.setText("B44");

        javax.swing.GroupLayout B44Layout = new javax.swing.GroupLayout(B44);
        B44.setLayout(B44Layout);
        B44Layout.setHorizontalGroup(
            B44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B44Layout.setVerticalGroup(
            B44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B44Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B44, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, 30, -1));

        B46.setBackground(new java.awt.Color(0, 102, 0));
        B46.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel112.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel112.setForeground(new java.awt.Color(255, 255, 255));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel112.setText("B46");

        javax.swing.GroupLayout B46Layout = new javax.swing.GroupLayout(B46);
        B46.setLayout(B46Layout);
        B46Layout.setHorizontalGroup(
            B46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B46Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B46Layout.setVerticalGroup(
            B46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B46Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B46, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, 30, -1));

        B47.setBackground(new java.awt.Color(0, 102, 0));
        B47.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel113.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel113.setForeground(new java.awt.Color(255, 255, 255));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel113.setText("B47");

        javax.swing.GroupLayout B47Layout = new javax.swing.GroupLayout(B47);
        B47.setLayout(B47Layout);
        B47Layout.setHorizontalGroup(
            B47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B47Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B47Layout.setVerticalGroup(
            B47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B47Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel113, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B47, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 380, 30, -1));

        B49.setBackground(new java.awt.Color(0, 102, 0));
        B49.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel115.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(255, 255, 255));
        jLabel115.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel115.setText("B49");

        javax.swing.GroupLayout B49Layout = new javax.swing.GroupLayout(B49);
        B49.setLayout(B49Layout);
        B49Layout.setHorizontalGroup(
            B49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B49Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B49Layout.setVerticalGroup(
            B49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B49Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B49, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 30, -1));

        B48.setBackground(new java.awt.Color(0, 102, 0));
        B48.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel114.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(255, 255, 255));
        jLabel114.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel114.setText("B48");

        javax.swing.GroupLayout B48Layout = new javax.swing.GroupLayout(B48);
        B48.setLayout(B48Layout);
        B48Layout.setHorizontalGroup(
            B48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B48Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B48Layout.setVerticalGroup(
            B48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B48Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B48, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 30, -1));

        B51.setBackground(new java.awt.Color(0, 102, 0));
        B51.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel117.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(255, 255, 255));
        jLabel117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel117.setText("B51");

        javax.swing.GroupLayout B51Layout = new javax.swing.GroupLayout(B51);
        B51.setLayout(B51Layout);
        B51Layout.setHorizontalGroup(
            B51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B51Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B51Layout.setVerticalGroup(
            B51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B51Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B51, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 30, -1));

        B50.setBackground(new java.awt.Color(0, 102, 0));
        B50.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel116.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(255, 255, 255));
        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel116.setText("B50");

        javax.swing.GroupLayout B50Layout = new javax.swing.GroupLayout(B50);
        B50.setLayout(B50Layout);
        B50Layout.setHorizontalGroup(
            B50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B50Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B50Layout.setVerticalGroup(
            B50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B50Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B50, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 380, 30, -1));

        B53.setBackground(new java.awt.Color(0, 102, 0));
        B53.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel119.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel119.setForeground(new java.awt.Color(255, 255, 255));
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel119.setText("B53");

        javax.swing.GroupLayout B53Layout = new javax.swing.GroupLayout(B53);
        B53.setLayout(B53Layout);
        B53Layout.setHorizontalGroup(
            B53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B53Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B53Layout.setVerticalGroup(
            B53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B53Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel119, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B53, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 30, -1));

        B52.setBackground(new java.awt.Color(0, 102, 0));
        B52.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel118.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel118.setForeground(new java.awt.Color(255, 255, 255));
        jLabel118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel118.setText("B52");

        javax.swing.GroupLayout B52Layout = new javax.swing.GroupLayout(B52);
        B52.setLayout(B52Layout);
        B52Layout.setHorizontalGroup(
            B52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B52Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B52Layout.setVerticalGroup(
            B52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B52Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B52, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 380, 30, -1));

        B54.setBackground(new java.awt.Color(0, 102, 0));
        B54.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel120.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel120.setForeground(new java.awt.Color(255, 255, 255));
        jLabel120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel120.setText("B54");

        javax.swing.GroupLayout B54Layout = new javax.swing.GroupLayout(B54);
        B54.setLayout(B54Layout);
        B54Layout.setHorizontalGroup(
            B54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B54Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B54Layout.setVerticalGroup(
            B54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B54Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel120, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B54, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 380, 30, -1));

        B56.setBackground(new java.awt.Color(0, 102, 0));
        B56.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel122.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(255, 255, 255));
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel122.setText("B56");

        javax.swing.GroupLayout B56Layout = new javax.swing.GroupLayout(B56);
        B56.setLayout(B56Layout);
        B56Layout.setHorizontalGroup(
            B56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B56Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B56Layout.setVerticalGroup(
            B56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B56Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B56, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 380, 30, -1));

        B55.setBackground(new java.awt.Color(0, 102, 0));
        B55.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel121.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel121.setForeground(new java.awt.Color(255, 255, 255));
        jLabel121.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel121.setText("B55");

        javax.swing.GroupLayout B55Layout = new javax.swing.GroupLayout(B55);
        B55.setLayout(B55Layout);
        B55Layout.setHorizontalGroup(
            B55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B55Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B55Layout.setVerticalGroup(
            B55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B55Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel121, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B55, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 30, -1));

        B57.setBackground(new java.awt.Color(0, 102, 0));
        B57.setPreferredSize(new java.awt.Dimension(22, 55));

        jLabel123.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(255, 255, 255));
        jLabel123.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel123.setText("B57");

        javax.swing.GroupLayout B57Layout = new javax.swing.GroupLayout(B57);
        B57.setLayout(B57Layout);
        B57Layout.setHorizontalGroup(
            B57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B57Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        B57Layout.setVerticalGroup(
            B57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, B57Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(B57, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 30, -1));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel B1;
    private javax.swing.JPanel B10;
    private javax.swing.JPanel B11;
    private javax.swing.JPanel B12;
    private javax.swing.JPanel B13;
    private javax.swing.JPanel B14;
    private javax.swing.JPanel B15;
    private javax.swing.JPanel B16;
    private javax.swing.JPanel B17;
    private javax.swing.JPanel B18;
    private javax.swing.JPanel B19;
    private javax.swing.JPanel B2;
    private javax.swing.JPanel B20;
    private javax.swing.JPanel B21;
    private javax.swing.JPanel B22;
    private javax.swing.JPanel B23;
    private javax.swing.JPanel B24;
    private javax.swing.JPanel B25;
    private javax.swing.JPanel B26;
    private javax.swing.JPanel B27;
    private javax.swing.JPanel B28;
    private javax.swing.JPanel B29;
    private javax.swing.JPanel B3;
    private javax.swing.JPanel B30;
    private javax.swing.JPanel B31;
    private javax.swing.JPanel B32;
    private javax.swing.JPanel B33;
    private javax.swing.JPanel B34;
    private javax.swing.JPanel B35;
    private javax.swing.JPanel B36;
    private javax.swing.JPanel B37;
    private javax.swing.JPanel B38;
    private javax.swing.JPanel B39;
    private javax.swing.JPanel B4;
    private javax.swing.JPanel B40;
    private javax.swing.JPanel B41;
    private javax.swing.JPanel B42;
    private javax.swing.JPanel B43;
    private javax.swing.JPanel B44;
    private javax.swing.JPanel B45;
    private javax.swing.JPanel B46;
    private javax.swing.JPanel B47;
    private javax.swing.JPanel B48;
    private javax.swing.JPanel B49;
    private javax.swing.JPanel B5;
    private javax.swing.JPanel B50;
    private javax.swing.JPanel B51;
    private javax.swing.JPanel B52;
    private javax.swing.JPanel B53;
    private javax.swing.JPanel B54;
    private javax.swing.JPanel B55;
    private javax.swing.JPanel B56;
    private javax.swing.JPanel B57;
    private javax.swing.JPanel B6;
    private javax.swing.JPanel B7;
    private javax.swing.JPanel B8;
    private javax.swing.JPanel B9;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    // End of variables declaration//GEN-END:variables
}