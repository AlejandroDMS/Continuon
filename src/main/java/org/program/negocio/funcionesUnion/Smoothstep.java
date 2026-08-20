package org.program.negocio.funcionesUnion;

import org.program.negocio.AbstractFuncionTransicion;

public class Smoothstep extends AbstractFuncionTransicion {

    public Smoothstep() {}

    public double funcionAlfa(double x){
            return ( 3*Math.pow(x, 2) - 2*Math.pow(x, 3) );
    }
}
