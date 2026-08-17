package org.program.negocio;

import org.program.utils.Resultado;
import org.program.vista.PanelCentralDibujado;

import java.util.ArrayList;

public class CtrCUDibujarFunciones {

    private static CtrCUDibujarFunciones instancia;

    private FuncionF fx;
    private FuncionG gx;
    private FuncionUnionSuave hx;

    public CtrCUDibujarFunciones() {
        fx = new FuncionF(1);
        gx = new FuncionG(3);
        hx = new FuncionUnionSuave(1,3, fx, gx);
    }
    public static CtrCUDibujarFunciones getInstancia() {
        if (instancia == null) {
            instancia = new CtrCUDibujarFunciones();
        }
        return instancia;
    }

    public void setFronteraIzq(double fronteraIzq) {
        fx.setFronteraIzq(fronteraIzq);
        hx.setFronteraIzq(fronteraIzq);
    }

    public void setFronteraDer(double fronteraDer) {
        gx.setFronteraDer(fronteraDer);
        hx.setFronteraDer(fronteraDer);
    }

    public Punto2D[] procesarEventoObtenerResultadoFuncionF(double resolucion, double amplitudIzq, double amplitudDer) {
        ArrayList<Punto2D> puntosF = new ArrayList<Punto2D>();
        Resultado resultado;
        for(double x = amplitudIzq; x <= amplitudDer; x = x + resolucion){
            resultado = fx.getValorFuncionF(x);

            if(resultado.valido()){
                puntosF.add(new Punto2D(x,resultado.valor()));
            }

        }
        return puntosF.toArray(new Punto2D[puntosF.size()]);
    }

    public Punto2D[] procesarEventoObtenerResultadoFuncionG(double resolucion, double amplitudIzq, double amplitudDer) {
        ArrayList<Punto2D> puntosG = new ArrayList<Punto2D>();
        Resultado resultado;
        for(double x = amplitudIzq; x <= amplitudDer; x = x + resolucion){
            resultado = gx.getValorFuncionG(x);

            if(resultado.valido()){
                puntosG.add(new Punto2D(x,resultado.valor()));
            }

        }
        return puntosG.toArray(new Punto2D[puntosG.size()]);
    }

    public Punto2D[] procesarEventoObtenerResultadoFuncionUnionSuave(double resolucion, double amplitudIzq, double amplitudDer) {
        ArrayList<Punto2D> puntosH = new ArrayList<Punto2D>();
        Resultado resultado;
        for(double x = amplitudIzq; x <= amplitudDer; x = x + resolucion){
            resultado = hx.getValorFuncionUnion(x);

            if(resultado.valido()){
                puntosH.add(new Punto2D(x,resultado.valor()));
            }

        }
        return puntosH.toArray(new Punto2D[puntosH.size()]);
    }

    public void procesarEventoCambiarFronteraIzq(double valor){
        setFronteraIzq(valor);
    }

    public void procesarEventoCambiarFronteraDer(double valor){
        setFronteraDer(valor);
    }

}
