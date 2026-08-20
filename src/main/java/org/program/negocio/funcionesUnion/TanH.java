package org.program.negocio.funcionesUnion;

import org.program.negocio.AbstractFuncionTransicion;

public class TanH extends AbstractFuncionTransicion {

    public TanH() {}

    public double funcionAlfa(double x) {
        return ( 0.5 * (1 + Math.tanh( (2*x - 1) / ( x * (1-x) ) )) );
    }
}
