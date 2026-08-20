package org.program.negocio;

public abstract class AbstractFuncionTransicion {

    //metodo plantilla creo o hoock method
    double funcionOmega(double x){
        double resultado;
        if( (0 < x ) &&  (x < 1) ){
            return funcionAlfa(x);
        } else if (x <= 0) {
            resultado = 0;
        }else{
            resultado = 1;
        }
        return resultado;
    }

    public abstract double funcionAlfa(double x);


}
