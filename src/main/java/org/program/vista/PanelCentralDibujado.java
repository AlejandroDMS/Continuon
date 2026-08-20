package org.program.vista;

import org.program.negocio.*;
import org.program.utils.Resultado;
import org.program.utils.TransformadorCoordenadas;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    private Point puntoInicial;

    public PanelCentralDibujado() {
        super();
        controlador = new CtrPanelCentralDibujado(this);


        MouseAdapter ratonListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                puntoInicial = e.getPoint();
            }
            @Override
            public void mouseDragged(MouseEvent e){
                if(puntoInicial == null) return;

                double cambioX = e.getX() - puntoInicial.x;
                double cambioY = e.getY() - puntoInicial.y;

                controlador.eventoDesplazarPantalla(-cambioX, cambioY);
                puntoInicial = e.getPoint();

            }
        };

        addMouseListener(ratonListener);
        addMouseMotionListener(ratonListener);

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

    public void pintarGrid(Graphics2D g2d, int ancho, int alto){
        Line2D ejeY = new Line2D.Double(ancho/2, 0, ancho/2, alto);
        Line2D ejeX = new Line2D.Double(0, alto/2, ancho, alto/2);
        g2d.setColor(Color.gray);
        g2d.draw(ejeX);
        g2d.draw(ejeY);
    }



    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
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
