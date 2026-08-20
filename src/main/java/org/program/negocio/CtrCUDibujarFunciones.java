package org.program.negocio;

import org.program.negocio.funciones.FuncionCOS;
import org.program.negocio.funciones.FuncionExpX;
import org.program.negocio.funciones.FuncionSIN;
import org.program.negocio.funciones.FuncionX;
import org.program.negocio.funcionesUnion.ExpNegativo;
import org.program.negocio.funcionesUnion.Smootherstep;
import org.program.negocio.funcionesUnion.Smoothstep;
import org.program.negocio.funcionesUnion.TanH;
import org.program.utils.Lado;
import org.program.utils.Resultado;

import java.util.ArrayList;

public class CtrCUDibujarFunciones {

    private static CtrCUDibujarFunciones instancia;

    private AbstractFuncion fx;
    private AbstractFuncion gx;
    private FuncionUnionSuave hx;

    private double desplazamientoX = 0;
    private double desplazamientoY = 0;

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
                puntosF.add(new Punto2D(x - getDesplazamientoX(),resultado.valor() - getDesplazamientoY()));
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
                puntosG.add(new Punto2D(x - getDesplazamientoX(),resultado.valor() - getDesplazamientoY()));
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
                puntosH.add(new Punto2D(x - getDesplazamientoX(),resultado.valor() - getDesplazamientoY()));
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

        nuevaFuncion = switch (idFuncion) {
            case 0 -> new FuncionX(frontera, lado);
            case 1 -> new FuncionCOS(frontera, lado);
            case 2 -> new FuncionSIN(frontera, lado);
            case 3 -> new FuncionExpX(frontera, lado);
            default -> null;
        };

        if(lado == Lado.IZQ){
            cambiarFuncionF(nuevaFuncion);
        } else if (lado == Lado.DER) {
            cambiarFuncionG(nuevaFuncion);
        }

    }

    public void procesarEventoCambiarFuncionTransicion(int idFuncion){
        AbstractFuncionTransicion nuevaTransicion = switch (idFuncion) {
            case 0 -> new Smoothstep();
            case 1 -> new Smootherstep();
            case 2 -> new ExpNegativo();
            case 3 -> new TanH();
            default -> null;
        };

        cambiarFuncionTransicion(nuevaTransicion);

    }

    private void cambiarFuncionF(AbstractFuncion nuevaFuncion){
        fx = nuevaFuncion;
        hx.setFx(fx);
    }

    private void cambiarFuncionG(AbstractFuncion nuevaFuncion){
        gx = nuevaFuncion;
        hx.setGx(gx);
    }

    private void cambiarFuncionTransicion(AbstractFuncionTransicion nuevaTransicion){
        hx.setFuncionTransicion(nuevaTransicion);
    }

    public void setDesplazamientoX(double nuevoDesplazamientoX){
        desplazamientoX = nuevoDesplazamientoX;
    }

    public void setDesplazamientoY(double nuevoDesplazamientoY){
        desplazamientoY = nuevoDesplazamientoY;
    }

    public double getDesplazamientoX(){
        return desplazamientoX;
    }

    public double getDesplazamientoY(){
        return desplazamientoY;
    }

    public void procesarEventoDesplazarPantalla(double cambioX, double cambioY){
        setDesplazamientoX( getDesplazamientoX() +  (cambioX/100) );
        setDesplazamientoY( getDesplazamientoY() +  (cambioY/100) );
    }

}
