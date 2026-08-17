package org.program.negocio;

import org.program.utils.Lado;
import org.program.utils.Resultado;

public abstract class AbstractFuncion {

    private double frontera;
    private Lado posicionFrontera;

    protected AbstractFuncion(double frontera, Lado posicionFrontera) {
        this.frontera = frontera;
        this.posicionFrontera = posicionFrontera;
    }

    public void setLadoFrontera(Lado posicionFrontera) {
        this.posicionFrontera = posicionFrontera;
    }

    public Lado getLadoFrontera() {
        return posicionFrontera;
    }

    public void setFrontera(double frontera) {
        this.frontera = frontera;
    }

    public double getFrontera() {
        return frontera;
    }


    public Resultado getResultadoFuncion(double x){
        double resultado = Double.NaN;
        boolean valorValido = true;

        if(getLadoFrontera() == Lado.IZQ){
            if(x < getFrontera()){
                resultado = computarFuncion(x);
            }else{
                resultado = 0;
                valorValido = false;
            }
        } else if (getLadoFrontera() == Lado.DER) {
            if(x > getFrontera()){
                resultado = computarFuncion(x);
            }else{
                resultado = 0;
                valorValido = false;
            }
        }

        return new Resultado(resultado, valorValido);
    }


    public abstract double computarFuncion(double x);


}
