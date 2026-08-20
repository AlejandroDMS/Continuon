package org.program.negocio.funcionesUnion;

import org.program.negocio.AbstractFuncionTransicion;

public class ExpNegativo extends AbstractFuncionTransicion {

    public ExpNegativo() {}

    public double funcionAlfa(double x) {
        return ( funcionAux(x) / (funcionAux(x) + funcionAux(1-x)) );
    }

    private double funcionAux(double x){
        double resultado;
        if(x > 0){
            resultado = Math.exp( (double) (-1)/x );
        }else{
            resultado = 0;
        }
        return resultado;
    }

}
