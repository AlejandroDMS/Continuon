package org.program;

import org.program.vista.GestorInterfaz;

import javax.swing.*;

public class App {

    private static final String LOOK_AND_FEEL_NOMBRE = "Nimbus";

    public static void main(String[] args) {
        try{
            for( javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()){
                if(LOOK_AND_FEEL_NOMBRE.equals(info.getName())){
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater( () -> GestorInterfaz.getInstance().iniciarInterfaz() );
    }
}