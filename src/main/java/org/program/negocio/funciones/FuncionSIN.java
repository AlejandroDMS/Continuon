package org.program.negocio.funciones;

import org.program.negocio.AbstractFuncion;
import org.program.utils.Lado;

public class FuncionSIN extends AbstractFuncion {

    public FuncionSIN(double frontera, Lado ladoFrontera) {
        super(frontera, ladoFrontera);
    }

    @Override
    public double computarFuncion(double x) {
        double resultado = 0;
        resultado = Math.sin(x);
        return resultado;
    }

}
