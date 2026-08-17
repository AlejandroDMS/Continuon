package org.program.utils;

import org.program.negocio.Punto2D;

public class TransformadorCoordenadas {

    private static double ancho;
    private static double alto;

    public static Punto2D transformarCoordenadasASwing(Punto2D p, int ancho, int alto,double escalaX, double escalaY) {


        Punto2D pT = new Punto2D(p.getX() * escalaX + ancho/2 , (-1) * p.getY() * escalaY + alto/2 );



        return pT;
    }

    public static Punto2D transformarCoordenadasASwing(Punto2D p, int ancho, int alto,double escala) {


        Punto2D pT = new Punto2D(p.getX() * escala + ancho/2 , (-1) * p.getY() * escala + alto/2 );



        return pT;
    }


    public TransformadorCoordenadas(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
    }
}
