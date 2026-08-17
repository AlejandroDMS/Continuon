package org.program.negocio;

import org.program.utils.Lado;

public class FuncionCOS extends AbstractFuncion{

    public FuncionCOS(double frontera, Lado ladoFrontera) {
        super(frontera, ladoFrontera);
    }

    @Override
    public double computarFuncion(double x) {
        double resultado = 0;
        resultado = Math.cos(x);
        return resultado;
    }

}
