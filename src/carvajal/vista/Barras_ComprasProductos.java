/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.vista;

import carvajal.controlador.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.UIManager;
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

/**
 *
 * @author Santiago Romero
 */
public class Barras_ComprasProductos extends ApplicationFrame {

    public ChartPanel panel;
    public JFreeChart chart;
    int producto1 = 0, producto2, producto3, producto4, producto5, producto6,
            producto7, producto8, producto9, producto10;

    public Barras_ComprasProductos(String title) throws SQLException {
        super(title);

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
        if (!error) {
            ResultSet rs = con.consulta("SELECT * FROM compra_proveedor");
            while (rs.next()) {
                if (rs.getInt(3) == (1)) {
                    producto1 += rs.getInt(5);
                }
                if (rs.getInt(3) == (2)) {
                    producto2 += rs.getInt(5);
                }
                if (rs.getInt(3) == (3)) {
                    producto3 += rs.getInt(5);
                }
                if (rs.getInt(3) == (4)) {
                    producto4 += rs.getInt(5);
                }
                if (rs.getInt(3) == (5)) {
                    producto5 += rs.getInt(5);
                }
                if (rs.getInt(3) == (6)) {
                    producto6 += rs.getInt(5);
                }
                if (rs.getInt(3) == (7)) {
                    producto7 += rs.getInt(5);
                }
                if (rs.getInt(3) == (8)) {
                    producto8 += rs.getInt(5);
                }
                if (rs.getInt(3) == (9)) {
                    producto9 += rs.getInt(5);
                }
                if (rs.getInt(3) == (10)) {
                    producto10 += rs.getInt(5);
                }
            }
        }
        con.cerrarConsulta();
        con.desconectar();
        dataset.setValue(producto1, "Compras", "Azucar");
        dataset.setValue(producto2, "Compras", "Acero");
        dataset.setValue(producto3, "Compras", "Abono");
        dataset.setValue(producto4, "Compras", "Plastico");
        dataset.setValue(producto5, "Compras", "Caña");
        dataset.setValue(producto6, "Compras", "Licencia");
        dataset.setValue(producto7, "Compras", "Bagazo");
        dataset.setValue(producto8, "Compras", "Gas");
        dataset.setValue(producto9, "Compras", "Electrico");
        dataset.setValue(producto10, "Compras", "Cemento");

        return dataset;
    }

    private JFreeChart crearChart(CategoryDataset data) {
        JFreeChart chart = ChartFactory.createBarChart(
                "", //Nombre de la gr�fica
                "Productos", //Nombre del eje Horizontal
                "Cantidad de Comprados", //Nombre del eje vertical
                data, //Data
                PlotOrientation.VERTICAL, //Orientaci�n HORIZONTAL o VERTICAL
                true, //Incluir leyenda
                true, //Informaci�n al pasar el mouse
                true);                      //URls

        Color c = UIManager.getLookAndFeel().getDefaults().getColor("Panel.background");
        chart.setBackgroundPaint(new Color(c.getRed(), c.getGreen(), c.getBlue()));//Dar color al fondo del panel
        chart.getTitle().setPaint(Color.WHITE);//Dar color al titulo 

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
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.MAGENTA, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

        return chart;
    }
}
