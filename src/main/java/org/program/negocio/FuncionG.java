package org.program.negocio;

import org.program.utils.Resultado;

public class FuncionG {

    private double fronteraDer;

    public FuncionG(double fronteraDer) {
        this.fronteraDer = fronteraDer;
    }

    public void setFronteraDer(double fronteraDer) { this.fronteraDer = fronteraDer; }
    public double getFronteraDer() { return fronteraDer; }

    public Resultado getValorFuncionG(double x) {
        double resultado;
        boolean valorValido = true;

        if(x > fronteraDer){
            resultado = computarFuncionG(x);
        }else{
            resultado = 0;
            valorValido = false;
        }

        return new Resultado(resultado, valorValido);
    }


    public double computarFuncionG(double x) {
        double resultado = 0;
        resultado = Math.sin(x);

        return resultado;
    }

}
