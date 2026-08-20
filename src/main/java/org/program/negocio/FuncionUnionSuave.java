package org.program.negocio;

import org.program.negocio.funcionesUnion.ExpNegativo;
import org.program.utils.Resultado;

public class FuncionUnionSuave {

    private double fronteraDer;
    private double fronteraIzq;

    private AbstractFuncion fx;
    private AbstractFuncion gx;
    private AbstractFuncionTransicion hx;

    public FuncionUnionSuave(double fronteraIzq, double fronteraDer, AbstractFuncion fx, AbstractFuncion gx) {
        this.fronteraIzq = fronteraIzq;
        this.fronteraDer = fronteraDer;
        this.fx = fx;
        this.gx = gx;
        hx = new ExpNegativo();
    }

    public Resultado getResultadoFuncionUnion(double x) {
        double resultado;
        double resultadoFuncionOmega;

        resultadoFuncionOmega = funcionOmegaAjustada(x, fronteraIzq, fronteraDer);
        //Cuidado por ejemplo si la funcion g fuera log x, ya que explota si x toma valores negativos
        resultado = (1 - resultadoFuncionOmega ) * fx.computarFuncion(x) + resultadoFuncionOmega * gx.computarFuncion(x);
        return new Resultado(resultado, true);
    }

    private double funcionOmegaAjustada(double x, double fronteraIzq, double fronteraDer) {
        return hx.funcionOmega((x-fronteraIzq)/(fronteraDer - fronteraIzq) );
    }


    public void setFronteraIzq(double fronteraIzq) {
        this.fronteraIzq = fronteraIzq;
    }

    public void setFronteraDer(double fronteraDer) {
        this.fronteraDer = fronteraDer;
    }

    public void setFx(AbstractFuncion fx) {
        this.fx = fx;
    }
    public void setGx(AbstractFuncion gx) {
        this.gx = gx;
    }

    public void setFuncionTransicion(AbstractFuncionTransicion hx) {
        this.hx = hx;
    }

    private AbstractFuncionTransicion getHx() {
        return hx;
    }

}
