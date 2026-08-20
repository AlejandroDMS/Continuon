package org.program.negocio.funcionesUnion;

import org.program.negocio.AbstractFuncionTransicion;

public class Smootherstep extends AbstractFuncionTransicion {

    public Smootherstep() {}

    public double funcionAlfa(double x) {
        return ( 6*Math.pow(x, 5) - 15*Math.pow(x, 4) + 10*Math.pow(x,3) );
    }
}
