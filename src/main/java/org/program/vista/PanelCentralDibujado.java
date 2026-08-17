package org.program.vista;

import org.program.negocio.FuncionF;
import org.program.negocio.FuncionG;
import org.program.negocio.FuncionUnionSuave;
import org.program.negocio.Punto2D;
import org.program.utils.Resultado;
import org.program.utils.TransformadorCoordenadas;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

public class PanelCentralDibujado extends javax.swing.JPanel {

    private CtrPanelCentralDibujado controlador;

    private final double RESOLUCION = 0.02;

    private final float AMPLITUD_DER = 5;
    private final float AMPLITUD_IZQ = -5;
    private final float AMPLITUD_SUP = 2.5f;
    private final float AMPLITUD_INF = -2.5f;

    private final double MARGEN = 1.0;

    private final double ZOOM = 60;


    private FuncionF fx;
    private FuncionG gx;
    private FuncionUnionSuave hx;

    public PanelCentralDibujado() {
        super();
        controlador = new CtrPanelCentralDibujado(this);

        fx = new FuncionF(1);
        gx = new FuncionG(3);
        hx = new FuncionUnionSuave(1,3, fx, gx);

        //TransformadorCoordenadas t = new TransformadorCoordenadas(7,6);
    }

    public int getNumPuntos(){
        return (int) ( (AMPLITUD_DER - AMPLITUD_IZQ) / RESOLUCION );
    }

    public double getResolucion(){
        return RESOLUCION;
    }

    public double getAmplitudDer(){
        return AMPLITUD_DER;
    }

    public double getAmplitudIzq(){
        return AMPLITUD_IZQ;
    }

    public void pintarFuncionF(Graphics2D g2d, int ancho, int alto, double escalaX, double escalaY) {
        Resultado yf;

        Path2D puntosF = new Path2D.Double();

        for(double x = AMPLITUD_IZQ; x < AMPLITUD_DER; x = x + RESOLUCION ){
            yf = fx.getValorFuncionF(x);

            if(yf.valido()){
                Punto2D pTf = TransformadorCoordenadas.transformarCoordenadasASwing(
                        new Punto2D(x, yf.valor()),
                        ancho, alto,
                        escalaX, escalaY);
                Ellipse2D cf = new Ellipse2D.Double(pTf.getX(), pTf.getY(), 2, 2);
                puntosF.append(cf, false);
            }


        }

        g2d.setColor(Color.blue);
        g2d.draw(puntosF);
    }

    public void pintarFuncionF(Graphics2D g2d, Punto2D[] puntos, int ancho, int alto, double escalaX, double escalaY) {

        Path2D puntosF = new Path2D.Double();
        for(Punto2D p : puntos){
            Punto2D pTf = TransformadorCoordenadas.transformarCoordenadasASwing(
                    p,
                    ancho, alto,
                    escalaX, escalaY);
            Ellipse2D cf = new Ellipse2D.Double(pTf.getX(), pTf.getY(), 2, 2);
            puntosF.append(cf, false);
        }
        g2d.setColor(Color.blue);
        g2d.draw(puntosF);
    }


    public void pintarFuncionG(Graphics2D g2d, int ancho, int alto, double escalaX, double escalaY) {
        Resultado yg;
        Path2D puntosG = new Path2D.Double();

        for(double x = AMPLITUD_IZQ; x < AMPLITUD_DER; x = x + RESOLUCION ){
            yg = gx.getValorFuncionG(x);

            if(yg.valido()){
                Punto2D pTf = TransformadorCoordenadas.transformarCoordenadasASwing(
                        new Punto2D(x, yg.valor()),
                        ancho, alto,
                        escalaX, escalaY);
                Ellipse2D cf = new Ellipse2D.Double(pTf.getX(), pTf.getY(), 2, 2);
                puntosG.append(cf, false);
            }


        }

        g2d.setColor(Color.red);
        g2d.draw(puntosG);
    }

    public void pintarFuncionG(Graphics2D g2d, Punto2D[] puntos, int ancho, int alto, double escalaX, double escalaY) {

        Path2D puntosG = new Path2D.Double();
        for(Punto2D p : puntos){
            Punto2D pTf = TransformadorCoordenadas.transformarCoordenadasASwing(
                    p,
                    ancho, alto,
                    escalaX, escalaY);
            Ellipse2D cf = new Ellipse2D.Double(pTf.getX(), pTf.getY(), 2, 2);
            puntosG.append(cf, false);
        }
        g2d.setColor(Color.red);
        g2d.draw(puntosG);
    }
    //PintarFuncion( ... , Funcion f) {}


    public void pintarFuncionUnion(Graphics2D g2d, int ancho, int alto, double escalaX, double escalaY) {
        Resultado yh;
        Path2D puntosH = new Path2D.Double();


        for(double x = AMPLITUD_IZQ; x < AMPLITUD_DER; x = x + RESOLUCION ){
            yh = hx.getValorFuncionUnion(x);

            if(yh.valido()){
                Punto2D pTf = TransformadorCoordenadas.transformarCoordenadasASwing(
                        new Punto2D(x, yh.valor()),
                        ancho, alto,
                        escalaX, escalaY);
                Ellipse2D cf = new Ellipse2D.Double(pTf.getX(), pTf.getY(), 2, 2);
                puntosH.append(cf, false);
            }


        }

        g2d.setColor(Color.black);
        g2d.draw(puntosH);
    }

    public void pintarFuncionUnion(Graphics2D g2d, Punto2D[] puntos, int ancho, int alto, double escalaX, double escalaY) {

        Path2D puntosH = new Path2D.Double();
        for(Punto2D p : puntos){
            Punto2D pTf = TransformadorCoordenadas.transformarCoordenadasASwing(
                    p,
                    ancho, alto,
                    escalaX, escalaY);
            Ellipse2D cf = new Ellipse2D.Double(pTf.getX(), pTf.getY(), 2, 2);
            puntosH.append(cf, false);
        }
        g2d.setColor(Color.black);
        g2d.draw(puntosH);
    }

    private void pintarFuncion(Graphics2D g2d, int ancho, int alto) {
        Resultado yf;
        Resultado yg;

        Path2D puntosF = new Path2D.Double();
        Path2D puntosG = new Path2D.Double();


        double escalaX = (double) ancho / (AMPLITUD_DER - AMPLITUD_IZQ);
        double escalaY = (double) alto / (AMPLITUD_SUP - AMPLITUD_INF);
        double escala = Math.min(escalaX, escalaY) * MARGEN; // Fuerza a que el eje x e y sean proporcionados (no aplastados) PONERLO COMO OPCION EN OPCIONES

        for(double x = AMPLITUD_IZQ; x < AMPLITUD_DER; x = x + RESOLUCION ){
            yf = fx.getValorFuncionF(x);
            yg = gx.getValorFuncionG(x);


            if(yf.valido()){
                Punto2D pTf = TransformadorCoordenadas.transformarCoordenadasASwing(
                        new Punto2D(x, yf.valor()),
                        ancho, alto,
                        escalaX, escalaY);
                Ellipse2D cf = new Ellipse2D.Double(pTf.getX(), pTf.getY(), 2, 2);
                puntosF.append(cf, false);
            }

            if(yg.valido()){
                Punto2D pTg = TransformadorCoordenadas.transformarCoordenadasASwing(
                        new Punto2D(x, yg.valor()),
                        ancho, alto,
                        escalaX, escalaY);
                Ellipse2D cg = new Ellipse2D.Double(pTg.getX(), pTg.getY(), 2, 2);
                puntosG.append(cg, false);
            }


        }

        g2d.setColor(Color.blue);
        g2d.draw(puntosF);

        g2d.setColor(Color.red);
        g2d.draw(puntosG);
    }

    private void pintarGrid(Graphics2D g2d, int ancho, int alto){
        Line2D ejeY = new Line2D.Double(ancho/2, 0, ancho/2, alto);
        Line2D ejeX = new Line2D.Double(0, alto/2, ancho, alto/2);
        g2d.setColor(Color.gray);
        g2d.draw(ejeX);
        g2d.draw(ejeY);
    }


    @Override
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        int ancho = getWidth();
        int alto = getHeight();
        //Rectangle2D rect = new Rectangle2D.Double(0, 0, width, height);
        pintarGrid(g2d, ancho, alto);

        //Calcular escala
        double escalaX = (double) ancho / (AMPLITUD_DER - AMPLITUD_IZQ);
        double escalaY = (double) alto / (AMPLITUD_SUP - AMPLITUD_INF);
        double escala = Math.min(escalaX, escalaY) * MARGEN; // Fuerza a que el eje x e y sean proporcionados (no aplastados) PONERLO COMO OPCION EN OPCIONES


        //pintarFuncionUnion(g2d, ancho, alto, escalaX, escalaY);
        //pintarFuncionF(g2d, ancho, alto, escalaX, escalaY);
        //pintarFuncionG(g2d, ancho, alto, escalaX, escalaY);

        controlador.eventoPintarFunciones(g2d, ancho, alto, escalaX,escalaY);

    }
}
