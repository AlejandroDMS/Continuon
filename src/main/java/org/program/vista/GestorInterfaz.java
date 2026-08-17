package org.program.vista;

import javax.swing.JFrame;

public class GestorInterfaz {

    private static GestorInterfaz instancia = null;
    private JFrame vistaActual;


    private GestorInterfaz() {}

    public static GestorInterfaz getInstance(){
        if(instancia == null){
            instancia = new GestorInterfaz();
        }
        return instancia;
    }

    public void iniciarInterfaz(){
        vistaActual = new VistaVisualizacionFunciones();
        vistaActual.setVisible(true);
    }

}
