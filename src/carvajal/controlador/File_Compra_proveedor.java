/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.controlador;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Santiago Romero
 */
public class File_Compra_proveedor {

    FileWriter fw = null;
    public boolean error = false;

    public void crear() {
        try {
            fw = new FileWriter("Compra_proveedor.csv");
        } catch (IOException ioe) {
            error = true;
            System.out.println("Error el tratar de crear el archivo 'Compra_proveedor.csv'");
        }
    }

    public void guardar(String registro) {
        try {
            fw.write(registro + "\r\n");
        } catch (IOException ioe) {
            System.out.println("Error el tratar de guardar en el archivo 'Compra_proveedor.csv'");
        }
    }

    public void cerrar() {
        try {
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Error el tratar de cerrar el archivo 'Compra_proveedor.csv'");
        }
    }
}
