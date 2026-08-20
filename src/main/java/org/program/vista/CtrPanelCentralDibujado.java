package org.program.vista;

import org.program.negocio.CtrCUDibujarFunciones;
import org.program.negocio.Punto2D;
import org.program.utils.Resultado;

import java.awt.*;

public class CtrPanelCentralDibujado {

    private PanelCentralDibujado vista;
    private CtrCUDibujarFunciones controladorCU;


    public CtrPanelCentralDibujado(PanelCentralDibujado vista) {
        this.vista = vista;
        controladorCU = CtrCUDibujarFunciones.getInstancia();
    }

    public void eventoDesplazarPantalla(double cambioX, double cambioY){
        controladorCU.procesarEventoDesplazarPantalla(cambioX, cambioY);
        vista.repaint();
    }

    public void eventoPintarFunciones(Graphics2D g2d, int ancho, int alto, double escalaX, double escalaY) {
        double resolucion = vista.getResolucion();

        double amplitudIzq = vista.getAmplitudIzq();
        double amplitudDer = vista.getAmplitudDer();

        Punto2D[] puntosF = this.controladorCU.procesarEventoObtenerResultadoFuncionF(resolucion, amplitudIzq, amplitudDer );
        Punto2D[] puntosG = this.controladorCU.procesarEventoObtenerResultadoFuncionG(resolucion, amplitudIzq, amplitudDer);
        Punto2D[] puntosH = this.controladorCU.procesarEventoObtenerResultadoFuncionUnionSuave(resolucion, amplitudIzq, amplitudDer);

        vista.pintarFuncionUnion(g2d, puntosH,  ancho, alto, escalaX, escalaY);
        vista.pintarFuncionF(g2d, puntosF, ancho, alto, escalaX, escalaY);
        vista.pintarFuncionG(g2d, puntosG,  ancho, alto, escalaX, escalaY);

    }

    public void eventoCambiarFronteraIzq(double valor){
        this.controladorCU.procesarEventoCambiarFronteraIzq(valor);
    }

    public void eventoCambiarFronteraDer(double valor){
        this.controladorCU.procesarEventoCambiarFronteraDer(valor);
    }

}
