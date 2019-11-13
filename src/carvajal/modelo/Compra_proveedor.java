package carvajal.modelo;

import carvajal.controlador.Conexion;
import carvajal.controlador.File_Compra_proveedor;
import java.sql.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Santiago Romero
 */
public class Compra_proveedor {

    //id,(nit_proveedor),ref,descripcion,cantidad,precio_unidad;
    public boolean error;
    String productos_prove[] = {
        "Azucar", "Acero", "Abono", "Plastico", "Caña", "Licencia", "Bagazo", "Gas", "Electrico", "Cemento"
    };

    public Compra_proveedor(int cantRegistros) {
        cargarProveedores(cantRegistros);
    }

    public void cargarProveedores(int cantRegistros) {
        String proveedores[] = null;
        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        if (!error) {
            ResultSet rs = con.consulta("SELECT * FROM proveedor");
            int cant = con.getSizeQuery(rs);
            if (cant > 0) {
                proveedores = new String[cant];
                int i = 0;
                try {
                    while (rs.next()) {
                        proveedores[i] = rs.getString(1);//id
                        i++;
                    }
                } catch (SQLException sql) {
                    JOptionPane.showMessageDialog(null,
                            "Error al tratar de obtener la informacion de los planes");
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "No existen planes academicos para ser asignados al estudiante");
            }
        }

        con.cerrarConsulta();
        con.desconectar();
        generar(proveedores, cantRegistros);
    }

    public void generar(String[] proveedores, int cantRegistros) {
        int ale = 0;
        String registro = "";
        int id = 1132;
        File_Compra_proveedor fcp = new File_Compra_proveedor();
        fcp.crear();

        if (!fcp.error) {

            for (int i = 0; i < cantRegistros; i++) {
                registro = id + ";";//agrega id

                ale = (int) (Math.random() * 10/*cambiar cantidad de provee*/);//selecciona un proveedor
                registro += proveedores[ale] + ";";
                int elprodu = ale;
                registro += ale + 1 + ";";
                registro += productos_prove[elprodu] + ";";//agrega descripcion
                ale = (int) (Math.random() * 50) + 1;
                registro += ale + ";";
                ale = (int) (Math.random() * 100000) + 1;//agrega un precio por unidad
                registro += ale + ";";

                System.out.println(registro);
                fcp.guardar(registro);
                id++;
            }//fin del for

            fcp.cerrar();
        }

        //File_Compra_proveedor_DB lfcdb = new DB_Compra_proveedor("Compra_proveedor.csv");
        //lfcdb.guardar();
    }

}
