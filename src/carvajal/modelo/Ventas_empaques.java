/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carvajal.modelo;

import carvajal.controlador.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago Romero
 */
public class Ventas_empaques {

    public boolean error = false;
    ArrayList<String> clientes = new ArrayList<String>();
    int cantidadClientes;
    String productos[];
    String sucursal[] = {"Bogota", "Tocancipa", "Cali", "Ginebra", "Medellin"};

    public Ventas_empaques(int cantRegistros) {
        cargarClientes();
        cargarProductos(cantRegistros);
    }

    public void cargarClientes() {

        FileReader fr = null;
        String nomFile = "Clientes.csv";
        try {
            fr = new FileReader(nomFile);
        } catch (IOException ioe) {
            error = true;
            JOptionPane.showMessageDialog(null,
                    "Error al tratar de abrir el documento '" + nomFile + "' para lectura.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (!error) {
            String registro = "";
            BufferedReader br = new BufferedReader(fr);
            int linea = 1;
            try {
                int i = 0;
                while ((registro = br.readLine()) != null) {
                    String tokens[] = registro.split(";");
                    clientes.add(tokens[0]);
                    cantidadClientes++;
                    i++;
                    linea++;
                }
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, linea,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            //fin del if(!sw)
            JOptionPane.showMessageDialog(null, "Datos Generados");
        } else {
            JOptionPane.showMessageDialog(null, "Cree primero los clientes");
        }
    }

    public void cargarProductos(int cantRegistros) {
        productos = null;
        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        if (!error) {
            ResultSet rs = con.consulta("SELECT * FROM productos_empaques");
            int cant = con.getSizeQuery(rs);
            if (cant > 0) {
                productos = new String[cant];
                int i = 0;
                try {
                    while (rs.next()) {
                        productos[i] = rs.getString(1);//id
                        i++;
                    }
                } catch (SQLException sql) {
                    JOptionPane.showMessageDialog(null,
                            "Error al tratar de obtener la informacion");
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "No existe");
            }
        }

        con.cerrarConsulta();
        con.desconectar();
        generar(cantRegistros);
    }

    public void generar(int cantRegistros) {
        if (!error) {
            int ale = 0;
            String registro = "";
            int dia, mes, anio;
            int id = 1132;
            File_Ventas_empaques fve = new File_Ventas_empaques();
            fve.crear();

            if (!fve.error) {

                for (int i = 0; i < cantRegistros; i++) {
                    registro = id + ";";//agrega id

                    ale = (int) (Math.random() * cantidadClientes);//selecciona un cliente
                    registro += clientes.get(ale) + ";";

                    ale = (int) (Math.random() * 30);//selecciona un producto
                    registro += productos[ale] + ";";

                    ale = 100 + (int) (Math.random() * (10000 - 100));
                    registro += ale + ";";//agrega una cantidad

                    do {
                        dia = 1 + (int) (Math.random() * 31);//genera el dia
                        mes = 1 + (int) (Math.random() * 12);//genera el mes
                        anio = 2010 + (int) (Math.random() * (2018 - 2010));//genera el año
                    } while (!isFechaValida(dia, mes, anio));
                    registro += anio + "-" + mes + "-" + dia + ";";

                    int hora = (int) (Math.random() * 23);
                    int minuto = (int) (Math.random() * 59);
                    String time = hora + ":" + minuto;
                    registro += time + ";";

                    ale = (int) (Math.random() * 5);//selecciona una sucursal
                    registro += sucursal[ale] + ";";

                    System.out.println(registro);
                    fve.guardar(registro);
                    id++;
                }//fin del for

                fve.cerrar();
            }
        }

        //File_Ventas_empaques_DB lfcdb = new File_Ventas_empaques_DB("Ventas_empaques.csv");
        //lfcdb.guardar();
    }

    public static boolean isFechaValida(int dd, int mm, int yyyy) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.parse(dd + "/" + mm + "/" + yyyy);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
