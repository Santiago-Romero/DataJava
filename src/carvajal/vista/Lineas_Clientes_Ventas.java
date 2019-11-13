package carvajal.vista;

import carvajal.controlador.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Lineas_Clientes_Ventas {

    public ChartPanel frame;
    int sucursal1, sucursal2, sucursal3, sucursal4, sucursal5, sucursal6,
            sucursal7, sucursal8, sucursal9, sucursal10, sucursal11, sucursal12,
            sucursal13, sucursal14, sucursal15, sucursal16,sucursal17;

    public Lineas_Clientes_Ventas(String empresa, String cliente) throws SQLException {

        DefaultCategoryDataset cat_dataset = new DefaultCategoryDataset();

        if (empresa.equals("empaques")) {
            for (int i = 1; i <= 30; i++) {
                Conexion con = new Conexion();
                boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
                if (!error) {
                    ResultSet rs = con.consulta("SELECT * FROM ventas_empaques WHERE nit_cliente=" + cliente + " AND ref_producto=" + i);
                    while (rs.next()) {
                        if (rs.getString(7).equals("Bogota")) {
                            sucursal1 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Tocancipa")) {
                            sucursal2 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Cali")) {
                            sucursal3 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Ginebra")) {
                            sucursal4 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Medellin")) {
                            sucursal5 += rs.getInt(4);
                        }
                    }
                }
                con.cerrarConsulta();
                con.desconectar();
                cat_dataset.setValue(sucursal1, "Bogota", i + "");
                cat_dataset.setValue(sucursal2, "Tocancipa", i + "");
                cat_dataset.setValue(sucursal3, "Cali", i + "");
                cat_dataset.setValue(sucursal4, "Ginebra", i + "");
                cat_dataset.setValue(sucursal5, "Medellin", i + "");
                sucursal1 = 0;
                sucursal2 = 0;
                sucursal3 = 0;
                sucursal4 = 0;
                sucursal5 = 0;
                sucursal6 = 0;
                sucursal7 = 0;
            }
        }
        if (empresa.equals("propal")) {
            for (int i = 1; i <= 5; i++) {
                Conexion con = new Conexion();
                boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
                if (!error) {
                    ResultSet rs = con.consulta("SELECT * FROM ventas_propal WHERE nit_cliente=" + cliente + " AND ref_producto=" + i);
                    while (rs.next()) {
                        if (rs.getString(7).equals("Yumbo")) {
                            sucursal1 += rs.getInt(4);
                        }
                    }
                }
                con.cerrarConsulta();
                con.desconectar();
                cat_dataset.setValue(sucursal1, "Yumbo", i + "");
                sucursal1 = 0;
                sucursal2 = 0;
                sucursal3 = 0;
                sucursal4 = 0;
                sucursal5 = 0;
                sucursal6 = 0;
                sucursal7 = 0;
            }
        }
        if (empresa.equals("mepal")) {
            for (int i = 1; i <= 25; i++) {
                Conexion con = new Conexion();
                boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
                if (!error) {
                    ResultSet rs = con.consulta("SELECT * FROM ventas_mepal WHERE nit_cliente=" + cliente + " AND ref_producto=" + i);
                    while (rs.next()) {
                        if (rs.getString(7).equals("Bogota")) {
                            sucursal1 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Medellin")) {
                            sucursal2 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Barranquilla")) {
                            sucursal3 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Cali")) {
                            sucursal4 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Cartagena")) {
                            sucursal5 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Eje Cafetero")) {
                            sucursal6 += rs.getInt(4);
                        }
                    }
                }
                con.cerrarConsulta();
                con.desconectar();
                cat_dataset.setValue(sucursal1, "Bogota", i + "");
                cat_dataset.setValue(sucursal2, "Medellin", i + "");
                cat_dataset.setValue(sucursal3, "Barranquilla", i + "");
                cat_dataset.setValue(sucursal4, "Cali", i + "");
                cat_dataset.setValue(sucursal5, "Cartagena", i + "");
                cat_dataset.setValue(sucursal6, "Eje Cafetero", i + "");
                sucursal1 = 0;
                sucursal2 = 0;
                sucursal3 = 0;
                sucursal4 = 0;
                sucursal5 = 0;
                sucursal6 = 0;
                sucursal7 = 0;
            }
        }
        if (empresa.equals("sol_com")) {
            for (int i = 1; i <= 17; i++) {
                Conexion con = new Conexion();
                boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
                if (!error) {
                    ResultSet rs = con.consulta("SELECT * FROM ventas_sol_com WHERE nit_cliente=" + cliente + " AND ref_producto=" + i);
                    while (rs.next()) {
                        if (rs.getString(7).equals("Bogota")) {
                            sucursal1 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Medellin")) {
                            sucursal2 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Bucaramanga")) {
                            sucursal3 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Manizales")) {
                            sucursal4 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Cali")) {
                            sucursal5 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("Barranquilla")) {
                            sucursal6 += rs.getInt(4);
                        }
                        if (rs.getString(7).equals("")) {
                            sucursal5 += rs.getInt(4);
                        }
                    }
                }
                con.cerrarConsulta();
                con.desconectar();
                cat_dataset.setValue(sucursal1, "Bogota", i + "");
                cat_dataset.setValue(sucursal2, "Medellin", i + "");
                cat_dataset.setValue(sucursal3, "Bucaramanga", i + "");
                cat_dataset.setValue(sucursal4, "Manizales", i + "");
                cat_dataset.setValue(sucursal5, "Cali", i + "");
                cat_dataset.setValue(sucursal6, "Barranquilla", i + "");
                cat_dataset.setValue(sucursal7, "Pereira", i + "");
                sucursal1 = 0;
                sucursal2 = 0;
                sucursal3 = 0;
                sucursal4 = 0;
                sucursal5 = 0;
                sucursal6 = 0;
                sucursal7 = 0;
            }
        }
        JFreeChart chart = ChartFactory.createLineChart(
                "", // Titulo
                "Referencia producto", // Etiqueta Coordenada X
                "Cantidad de productos vendidos", // Etiqueta Coordenada Y
                cat_dataset, // Datos
                PlotOrientation.VERTICAL,
                true, // Muestra la leyenda de los sucursals en el eje de la X
                true,// mostrar la leyenda en cada punto
                false
        );

        // Mostramos la grafica en pantalla
        frame = new ChartPanel(chart);
        frame.setSize(700, 500);
    }

}
