package org.program.negocio;

import org.program.utils.Lado;
import org.program.utils.Resultado;

import java.util.ArrayList;

public class CtrCUDibujarFunciones {

    private static CtrCUDibujarFunciones instancia;

    private AbstractFuncion fx;
    private AbstractFuncion gx;
    private FuncionUnionSuave hx;

    public CtrCUDibujarFunciones() {
        fx = new FuncionX(1, Lado.IZQ);
        gx = new FuncionCOS(3, Lado.DER);
        hx = new FuncionUnionSuave(1,3, fx, gx);
    }
    public static CtrCUDibujarFunciones getInstancia() {
        if (instancia == null) {
            instancia = new CtrCUDibujarFunciones();
        }
        return instancia;
    }

    public void setFronteraIzq(double fronteraIzq) {
        fx.setFrontera(fronteraIzq); //Hay que poner tambien setLado ??
        hx.setFronteraIzq(fronteraIzq);
    }

    public void setFronteraDer(double fronteraDer) {
        gx.setFrontera(fronteraDer);
        hx.setFronteraDer(fronteraDer);
    }

    public Punto2D[] procesarEventoObtenerResultadoFuncionF(double resolucion, double amplitudIzq, double amplitudDer) {
        ArrayList<Punto2D> puntosF = new ArrayList<Punto2D>();
        Resultado resultado;
        for(double x = amplitudIzq; x <= amplitudDer; x = x + resolucion){
            resultado = fx.getResultadoFuncion(x);

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
            resultado = gx.getResultadoFuncion(x);

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
            resultado = hx.getResultadoFuncionUnion(x);

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

    public void procesarEventoCambiarCambiarFuncionF(int idFuncion) {
        AbstractFuncion nuevaFuncion;

        switch(idFuncion){
            case 1:
                nuevaFuncion = new FuncionX(1, Lado.IZQ);
            case 2:
                nuevaFuncion = new FuncionCOS(3, Lado.IZQ);
            default:
                nuevaFuncion = null;
        }

        cambiarFuncionF(nuevaFuncion);
    }

    public void procesarEventoCambiarCambiarFuncionG(int idFuncion) {
        AbstractFuncion nuevaFuncion;

        switch(idFuncion){
            case 1:
                nuevaFuncion = new FuncionX(1, Lado.IZQ);
                break;
            case 2:
                nuevaFuncion = new FuncionCOS(3, Lado.IZQ);
                break;
            default:
                nuevaFuncion = null;
        }

        cambiarFuncionG(nuevaFuncion);
    }

    private void cambiarFuncionF(AbstractFuncion nuevaFuncion){
        fx = nuevaFuncion;
    }

    private void cambiarFuncionG(AbstractFuncion nuevaFuncion){
        gx = nuevaFuncion;
    }

}
