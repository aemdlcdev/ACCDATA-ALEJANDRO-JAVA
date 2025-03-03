package com.aem.mvc.vista;

import com.aem.mvc.controlador.ControlConversor;
import com.aem.mvc.vista.InterfazVista;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




/**
 *
 * @author AEM by Alejandro Esteban Martinez de la Casa
 * @version 1.0
 * Created on 15 sept 2024
 */
public class VentanaConversor extends JFrame implements InterfazVista{
    private final JButton convertirApesetas;
    private final JButton convertirAeuros;
    private final JTextField cantidad;
    private final JTextField comision;
    private final JLabel resultado;

    /**
     * Genera la interfaz gráfica
     */
    public VentanaConversor() {
        super("Conversor de Euros y Pesetas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(10, 10));
        cantidad = new JTextField(8);
        JPanel panelaux = new JPanel();
        panelaux.add(cantidad);
        panelPrincipal.add(panelaux, BorderLayout.NORTH);
        
        comision = new JTextField(8);
        JPanel panelaux3 = new JPanel();
        panelaux3.add(comision);
        panelPrincipal.add(panelaux3, BorderLayout.EAST);
        
        resultado = new JLabel("Indique una cantidad y pulse uno de los botones");
        JPanel panelaux2 = new JPanel();
        panelaux2.add(resultado);
        panelPrincipal.add(panelaux2, BorderLayout.CENTER);
        
        convertirApesetas = new JButton("A pesetas");
        // Le indocamos el ActionCommand para el botón y así luego saber desde que botón se ha llamado
        convertirApesetas.setActionCommand(APESETAS);
        // Le indocamos el ActionCommand para el botón y así luego saber desde que botón se ha llamado
        convertirAeuros = new JButton("A euros");
        convertirAeuros.setActionCommand(AEUROS);
        
     
        JPanel botonera = new JPanel();
        botonera.add(convertirApesetas);
        botonera.add(convertirAeuros);
        panelPrincipal.add(botonera, BorderLayout.SOUTH);
        getContentPane().add(panelPrincipal);
    
    }

    @Override
    public void setControlador(ControlConversor c) {
        convertirApesetas.addActionListener(c);
        convertirAeuros.addActionListener(c);
    }

    @Override
    public void arranca() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public double getCantidad() {
        try{
            return Double.parseDouble(cantidad.getText());
        } catch (NumberFormatException e){
            return 0.0D;
        }
    }

    @Override
    public void escribeCambio(String s) {
        this.resultado.setText(s);
    }

    @Override
    public int getComision() {
        try{
            return Integer.parseInt(comision.getText());
        }
        catch(NumberFormatException e){
            return 0;
        }
    }
   
}