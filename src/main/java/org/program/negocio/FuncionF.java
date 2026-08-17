package org.program.negocio;

import org.program.utils.Resultado;

public class FuncionF {

    private double fronteraIzq;


    public FuncionF(double fronteraIzq) {
        this.fronteraIzq = fronteraIzq;
    }

    public void setFronteraIzq(double fronteraIzq) { this.fronteraIzq = fronteraIzq; }
    public double getFronteraIzq(){ return fronteraIzq; }

    public Resultado getValorFuncionF(double x) {
        double resultado;
        boolean valorValido = true;

        if(x < fronteraIzq){
            resultado = computarFuncionF(x);
        }else{
            resultado = 0;
            valorValido = false;
        }

        return new Resultado(resultado, valorValido);
    }

    public double computarFuncionF(double x) {
        double resultado = 0;

        resultado = -Math.exp(x);

        return resultado;
    }

}
