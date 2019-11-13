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
import javax.swing.UIManager;

public class Barras_ComprasProveedor extends ApplicationFrame {

    public ChartPanel panel;
    public JFreeChart chart;
    int proveedor1=0,proveedor2,proveedor3,proveedor4,proveedor5,proveedor6,
                proveedor7,proveedor8,proveedor9,proveedor10;

    public Barras_ComprasProveedor(String title) throws SQLException {
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
            ResultSet rs = con.consulta("SELECT nit_proveedor FROM compra_proveedor");
            while(rs.next()){
            if(rs.getInt(1)==(123001)){
                proveedor1++;
            }
            if(rs.getInt(1)==(123002)){
                proveedor2++;
            }
            if(rs.getInt(1)==(123003)){
                proveedor3++;
            }
            if(rs.getInt(1)==(123004)){
                proveedor4++;
            }
            if(rs.getInt(1)==(123005)){
                proveedor5++;
            }
            if(rs.getInt(1)==(123006)){
                proveedor6++;
            }
            if(rs.getInt(1)==(123007)){
                proveedor7++;
            }
            if(rs.getInt(1)==(123008)){
                proveedor8++;
            }
            if(rs.getInt(1)==(123009)){
                proveedor9++;
            }
            if(rs.getInt(1)==(123010)){
                proveedor10++;
            }
            }
        }
        con.cerrarConsulta();
        con.desconectar();
        dataset.setValue(proveedor1, "Compras", "Pichichi");
        dataset.setValue(proveedor2, "Compras", "Aceroscol");
        dataset.setValue(proveedor3, "Compras", "Agricola Himalaya");
        dataset.setValue(proveedor4, "Compras", "RH SAS");
        dataset.setValue(proveedor5, "Compras", "Riopaila Castilla");
        dataset.setValue(proveedor6, "Compras", "SIESA");
        dataset.setValue(proveedor7, "Compras", "Manuelita");
        dataset.setValue(proveedor8, "Compras", "Gases de occidente");
        dataset.setValue(proveedor9, "Compras", "Electrojaponesa");
        dataset.setValue(proveedor10, "Compras", "Cementos San Marcos");

        return dataset;
    }

    private JFreeChart crearChart(CategoryDataset data) {
        JFreeChart chart = ChartFactory.createBarChart(
                "", //Nombre de la gr�fica
                "Cantidad de Compras", //Nombre del eje Horizontal
                "Proveedores", //Nombre del eje vertical
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
