/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.vista;

import carvajal.controlador.Conexion;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Barras_Producto_Fechas_Ventas extends ApplicationFrame {

    public ChartPanel panel;
    public JFreeChart chart;
    public String empresa, producto, mes, anio;
    public int tam;
    public int dinero;

    public Barras_Producto_Fechas_Ventas(String empresa, String producto, int dinero) throws SQLException {

        super(empresa);
        this.dinero = dinero;
        this.empresa = empresa;
        this.producto = producto;
        CategoryDataset dataset = crearDataset();
        chart = crearChart(dataset);
        panel = new ChartPanel(chart, false);
        panel.setPreferredSize(new Dimension(700, 500));
        setContentPane(panel);
        pack();
    }

    private CategoryDataset crearDataset() throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        Conexion con1 = new Conexion();
        boolean error2 = con1.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        for (int a = 2010; a <= 2017; a++) {
            for (int m = 1; m <= 12; m++) {
                if (!error) {
                    ResultSet rs = con.consulta("SELECT * FROM ventas_" + empresa + " WHERE YEAR(fecha)=" + a + " AND MONTH(fecha)=" + m + " AND ref_producto=" + producto);

                    if (dinero == 0) {
                        while (rs.next()) {
                            tam += rs.getInt(4);
                        }
                    }

                    if (dinero == 1) {
                        ResultSet rs1 = con1.consulta("SELECT precio FROM productos_" + empresa + " WHERE ref=" + producto);
                        while (rs.next()) {
                            while (rs1.next()) {
                                tam += rs.getInt(4) * rs1.getInt(1);
                            }
                        }
                    }
                    if (dinero == 2) {
                        ResultSet rs1 = con1.consulta("SELECT costo FROM productos_" + empresa + " WHERE ref=" + producto);
                        while (rs.next()) {
                            while (rs1.next()) {
                                tam += rs.getInt(4) * rs1.getInt(1);
                            }
                        }

                    }
                }
                if (m == 1) {
                    dataset.addValue(tam, "Enero", a + "");
                    tam = 0;
                }
                if (m == 2) {
                    dataset.addValue(tam, "Febrero", a + "");
                    tam = 0;
                }
                if (m == 3) {
                    dataset.addValue(tam, "Marzo", a + "");
                    tam = 0;
                }
                if (m == 4) {
                    dataset.addValue(tam, "Abril", a + "");
                    tam = 0;
                }
                if (m == 5) {
                    dataset.addValue(tam, "Mayo", a + "");
                    tam = 0;
                }
                if (m == 6) {
                    dataset.addValue(tam, "Junio", a + "");
                    tam = 0;
                }
                if (m == 7) {
                    dataset.addValue(tam, "Julio", a + "");
                    tam = 0;
                }
                if (m == 8) {
                    dataset.addValue(tam, "Agosto", a + "");
                    tam = 0;
                }
                if (m == 9) {
                    dataset.addValue(tam, "Septiempre", a + "");
                    tam = 0;
                }
                if (m == 10) {
                    dataset.addValue(tam, "Octubre", a + "");
                    tam = 0;
                }
                if (m == 11) {
                    dataset.addValue(tam, "Noviembre", a + "");
                    tam = 0;
                }
                if (m == 12) {
                    dataset.addValue(tam, "Diciembre", a + "");
                    tam = 0;
                }
            }
        }

        con.cerrarConsulta();
        con.desconectar();
        if (dinero != 0) {
            con1.cerrarConsulta();
            con1.desconectar();
        }

        return dataset;
    }

    private JFreeChart crearChart(CategoryDataset data) {
        String nombrePro = null;
        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        if (!error) {
            ResultSet rs = con.consulta("SELECT descripcion FROM productos_" + empresa + " WHERE ref=" + producto);

            try {
                while (rs.next()) {
                    nombrePro = rs.getString(1);
                }
            } catch (SQLException sql) {
                System.out.println(sql);
            }
        }

        con.cerrarConsulta();
        con.desconectar();

        JFreeChart chart = ChartFactory.createBarChart(
                nombrePro + " Vendidos", //Nombre de la gr�fica
                "Productos", //Nombre del eje Horizontal
                "Camtidad de Vendidos", //Nombre del eje vertical
                data, //Data
                PlotOrientation.VERTICAL, //Orientaci�n HORIZONTAL o VERTICAL
                true, //Incluir leyenda
                true, //Informaci�n al pasar el mouse
                true);                      //URls

        Color c = UIManager.getLookAndFeel().getDefaults().getColor("Panel.background");
        chart.setBackgroundPaint(new Color(c.getRed(), c.getGreen(), c.getBlue()));//Dar color al fondo del panel
        chart.getTitle().setPaint(Color.BLACK);//Dar color al titulo 

        //CategoryPlot plot =(CategoryPlot) chart.getPlot();	    	    	    
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);//Color del fondo del gr�fico

        plot.setDomainGridlinesVisible(true);//Lineas divisorias
        plot.setRangeGridlinePaint(Color.BLACK);//Color de lineas divisorias	    

        //Calculo de los valores en el eje x
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(true);//Asignar color de linea a las barras

        //Dar color a las barras
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.orange, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

        return chart;
    }
}
