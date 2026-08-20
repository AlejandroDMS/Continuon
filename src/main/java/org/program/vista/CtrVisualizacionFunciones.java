package org.program.vista;

import org.program.negocio.CtrCUDibujarFunciones;
import org.program.utils.Lado;

public class CtrVisualizacionFunciones {

    private VistaVisualizacionFunciones vista;
    private CtrCUDibujarFunciones controladorCU;

    public CtrVisualizacionFunciones(VistaVisualizacionFunciones vista) {
        this.vista = vista;
        controladorCU = CtrCUDibujarFunciones.getInstancia();
    }

    public void eventoCambiarFronteraIzq(javax.swing.event.ChangeEvent evt){
        javax.swing.JSlider slider = (javax.swing.JSlider) evt.getSource();
        int valor = slider.getValue();
        this.controladorCU.procesarEventoCambiarFronteraIzq((double) valor / 10);
        vista.repaint();
    }

    public void eventoCambiarFronteraDer(javax.swing.event.ChangeEvent evt){
        javax.swing.JSlider slider = (javax.swing.JSlider) evt.getSource();
        int valor = slider.getValue();
        this.controladorCU.procesarEventoCambiarFronteraDer((double) valor / 10);
        vista.repaint();
    }

    public void eventoCambiarFuncionF(java.awt.event.ActionEvent evt){
        javax.swing.JComboBox comboBox = (javax.swing.JComboBox) evt.getSource();
        int valor = comboBox.getSelectedIndex();
        this.controladorCU.procesarEventoCambiarFuncion(valor, Lado.IZQ);
        vista.repaint();
    }

    public void eventoCambiarFuncionG(java.awt.event.ActionEvent evt){
        javax.swing.JComboBox comboBox = (javax.swing.JComboBox) evt.getSource();
        int valor = comboBox.getSelectedIndex();
        this.controladorCU.procesarEventoCambiarFuncion(valor, Lado.DER);
        vista.repaint();
    }

    public void eventoCambiarFuncionUnion(java.awt.event.ActionEvent evt){
        javax.swing.JComboBox comboBox = (javax.swing.JComboBox) evt.getSource();
        int valor = comboBox.getSelectedIndex();
        this.controladorCU.procesarEventoCambiarFuncionTransicion(valor);
        vista.repaint();
    }

}
