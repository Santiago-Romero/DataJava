package carvajal.controlador;

import java.io.FileWriter;
import java.io.IOException;

public class File_Clientes {

    FileWriter fw = null;
    public boolean error = false;

    public void crear() {
        try {//C:\Users\Docente.LI1-PC-01\Desktop\Nueva carpeta
            fw = new FileWriter("Clientes.csv");
        } catch (IOException ioe) {
            error = true;
            System.out.println("Error el tratar de crear el archivo 'Clientes.csv'");
        }
    }

    public void guardar(String registro) {
        try {
            fw.write(registro + "\r\n");
        } catch (IOException ioe) {
            System.out.println("Error el tratar de guardar en el archivo 'Clientes.csv'");
        }
    }

    public void cerrar() {
        try {
            fw.close();
        } catch (IOException ioe) {
            System.out.println("Error el tratar de cerrar el archivo 'Clientes.csv'");
        }
    }
}
