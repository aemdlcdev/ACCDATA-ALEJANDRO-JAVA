/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.aem.clasefile.vista;

import com.aem.clasefile.controlador.ControlArchivo;
import com.aem.clasefile.controlador.ControlCarpeta;
import javax.swing.JTextArea;

/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 */
public class VistaGrafica extends javax.swing.JFrame implements InterfazVista{
    
    /**
     * Creates new form VistaGrafica
     */
    public VistaGrafica() {
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

        jPanel1 = new javax.swing.JPanel();
        btnCarpetas = new javax.swing.JButton();
        btnArchivos = new javax.swing.JButton();
        txtBienvenida = new java.awt.Label();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(137, 137, 137));

        btnCarpetas.setFont(new java.awt.Font("Source Code Pro Medium", 0, 18)); // NOI18N
        btnCarpetas.setText("Carpetas");
        btnCarpetas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCarpetas.setBorderPainted(false);
        btnCarpetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCarpetasActionPerformed(evt);
            }
        });

        btnArchivos.setFont(new java.awt.Font("Source Code Pro Medium", 0, 18)); // NOI18N
        btnArchivos.setText("Archivos");
        btnArchivos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnArchivos.setBorderPainted(false);

        txtBienvenida.setFont(new java.awt.Font("Source Code Pro Medium", 0, 36)); // NOI18N
        txtBienvenida.setForeground(new java.awt.Color(255, 255, 255));
        txtBienvenida.setText("Bienvenido! ¿Con que desea trabajar?");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(454, 454, 454)
                .addComponent(btnSalir)
                .addContainerGap(484, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 95, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addComponent(btnCarpetas, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(190, 190, 190)
                            .addComponent(btnArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 95, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(459, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(48, 48, 48))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 140, Short.MAX_VALUE)
                    .addComponent(txtBienvenida, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(60, 60, 60)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnCarpetas, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnArchivos, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 140, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCarpetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCarpetasActionPerformed
        // TODO add your handling code here:
        VistaCarpeta vc = new VistaCarpeta();
        vc.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCarpetasActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaGrafica().setVisible(true);
            }
        });
    }

    @Override
    public void setControlador(ControlCarpeta c) {
        
    }

    @Override
    public void setControlador(ControlArchivo ca) {
        
    }

    @Override //No pongo el pack porque lo pone por defecto
    public void arranca() { 
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public String getRuta() {
        return "";
    }

    @Override
    public String getNombre() {
        return "";
    }

    @Override
    public String getNombreNuevo() {
        return "";
    }

    @Override
    public String getRutaNueva() {
        return "";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArchivos;
    private javax.swing.JButton btnCarpetas;
    private javax.swing.JButton btnSalir;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label txtBienvenida;
    // End of variables declaration//GEN-END:variables

    @Override
    public JTextArea getTextArea() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void recorreYMuestra(String[] s) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
