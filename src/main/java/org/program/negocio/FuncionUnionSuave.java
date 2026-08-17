package org.program.negocio;

import org.program.utils.Resultado;

public class FuncionUnionSuave {

    private double fronteraDer;
    private double fronteraIzq;

    private FuncionF fx;
    private FuncionG gx;

    public FuncionUnionSuave(double fronteraIzq, double fronteraDer, FuncionF fx, FuncionG gx) {
        this.fronteraIzq = fronteraIzq;
        this.fronteraDer = fronteraDer;
        this.fx = fx;
        this.gx = gx;
    }

    public Resultado getValorFuncionUnion(double x) {
        double resultado;
        double resultadoFuncionOmega;

        resultadoFuncionOmega = funcionOmegaAjustada(x, fronteraIzq, fronteraDer);
        //Cuidado por ejemplo si la funcion g fuera log x, ya que explota si x toma valores negativos
        resultado = (1 - resultadoFuncionOmega ) * fx.computarFuncionF(x) + resultadoFuncionOmega * gx.computarFuncionG(x);
        return new Resultado(resultado, true);
    }

    private double funcionOmegaAjustada(double x, double fronteraIzq, double fronteraDer) {
        return funcionOmega((x-fronteraIzq)/(fronteraDer - fronteraIzq) );
    }

    private double funcionOmega(double x) {
        double resultado;
        if( (0 < x ) &&  (x < 1) ){
            double valorFuncionAlfa = funcionAlfa(x);
            resultado = valorFuncionAlfa / (valorFuncionAlfa + funcionAlfa(1-x));
            return resultado;
        } else if (x <= 0) {
            resultado = 0;
        }else{
            resultado = 1;
        }
        return resultado;
    }

    private double funcionAlfa(double x) {
        double resultado;
        if(x > 0){
            resultado = Math.exp( (double) (-1)/x );
        }else{
            resultado = 0;
        }
        return resultado;
    }

    public void setFronteraIzq(double fronteraIzq) {
        this.fronteraIzq = fronteraIzq;
    }

    public void setFronteraDer(double fronteraDer) {
        this.fronteraDer = fronteraDer;
    }

}
