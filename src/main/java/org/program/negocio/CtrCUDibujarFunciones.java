package org.program.negocio;

import org.program.negocio.funciones.FuncionCOS;
import org.program.negocio.funciones.FuncionExpX;
import org.program.negocio.funciones.FuncionSIN;
import org.program.negocio.funciones.FuncionX;
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

    public void procesarEventoCambiarFuncion(int idFuncion, Lado lado){
        AbstractFuncion nuevaFuncion;
        double frontera = 0;

        if(lado == Lado.IZQ){
            frontera = fx.getFrontera();
        } else if (lado == Lado.DER) {
            frontera = gx.getFrontera();
        }

        switch(idFuncion){
            case 0:
                nuevaFuncion = new FuncionX(frontera, lado);
                break;
            case 1:
                nuevaFuncion = new FuncionCOS(frontera, lado);
                break;
            case 2:
                nuevaFuncion = new FuncionSIN(frontera, lado);
                break;
            case 3:
                nuevaFuncion = new FuncionExpX(frontera, lado);
                break;
            default:
                nuevaFuncion = null;
        }

        if(lado == Lado.IZQ){
            cambiarFuncionF(nuevaFuncion);
        } else if (lado == Lado.DER) {
            cambiarFuncionG(nuevaFuncion);
        }

    }

    public void procesarEventoCambiarFuncionF(int idFuncion) {
        AbstractFuncion nuevaFuncion;

        switch(idFuncion){
            case 0:
                nuevaFuncion = new FuncionX(fx.getFrontera(), Lado.IZQ);
                break;
            case 1:
                nuevaFuncion = new FuncionCOS(fx.getFrontera(), Lado.IZQ);
                break;
            default:
                nuevaFuncion = null;
        }

        cambiarFuncionF(nuevaFuncion);
    }

    public void procesarEventoCambiarFuncionG(int idFuncion) {
        AbstractFuncion nuevaFuncion;

        switch(idFuncion){
            case 0:
                nuevaFuncion = new FuncionX(gx.getFrontera(), Lado.DER);
                break;
            case 1:
                nuevaFuncion = new FuncionCOS(gx.getFrontera(), Lado.DER);
                break;
            default:
                nuevaFuncion = null;
        }

        cambiarFuncionG(nuevaFuncion);
    }

    private void cambiarFuncionF(AbstractFuncion nuevaFuncion){
        fx = nuevaFuncion;
        hx.setFx(fx);
    }

    private void cambiarFuncionG(AbstractFuncion nuevaFuncion){
        gx = nuevaFuncion;
        hx.setGx(gx);
    }

}
