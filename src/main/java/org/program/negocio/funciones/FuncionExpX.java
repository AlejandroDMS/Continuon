package org.program.negocio.funciones;

import org.program.negocio.AbstractFuncion;
import org.program.utils.Lado;

public class FuncionExpX extends AbstractFuncion {

    public FuncionExpX(double frontera, Lado ladoFrontera) {
        super(frontera, ladoFrontera);
    }

    @Override
    public double computarFuncion(double x) {
        double resultado = 0;
        resultado = Math.exp(x);
        return resultado;
    }

}
