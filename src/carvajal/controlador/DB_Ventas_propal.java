/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago Romero
 */
public class DB_Ventas_propal {

    FileReader fr;
    String nomFile;
    boolean error = false;

    public DB_Ventas_propal(String nomFile) {
        this.nomFile = nomFile;
        try {
            fr = new FileReader(nomFile);
        } catch (IOException ioe) {
            error = true;
            JOptionPane.showMessageDialog(null,
                    "Error al tratar de abrir el documento '" + nomFile + "' para lectura.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardar() {
        if (!error) {
            String registro = "";
            BufferedReader br = new BufferedReader(fr);
            int linea = 1;
            Conexion con = new Conexion();
            boolean sw = con.conectarMySQL("carvajal", "root", "", "localhost");
            sw = con.actualizar("DELETE FROM ventas_propal;");
            if (!sw) {
                try {
                    while ((registro = br.readLine()) != null) {
                        String tokens[] = registro.split(";");
                        sw = con.actualizar("INSERT INTO ventas_propal values('"
                                + tokens[0] + "', '"
                                + tokens[1] + "', '"
                                + tokens[2] + "', '"
                                + tokens[3] + "', '"
                                + tokens[4] + "', '"
                                + tokens[5] + "', '"
                                + tokens[6] + "');");
                        if (sw) {
                            break;
                        }
                        linea++;
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error al tratar de leer el documento '" + nomFile + "' en la linea " + linea,
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

                con.desconectar();

            }//fin del if(!sw)
            JOptionPane.showMessageDialog(null, "Datos Cargados");
        }
    }
}
