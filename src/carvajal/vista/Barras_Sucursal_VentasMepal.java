/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.vista;

/**
 *
 * @author Santiago Romero
 */
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

public class Barras_Sucursal_VentasMepal extends ApplicationFrame {

    public ChartPanel panel;
    public JFreeChart chart;
    int sucursal1, sucursal2, sucursal3, sucursal4, sucursal5, sucursal6;

    public Barras_Sucursal_VentasMepal(String title) throws SQLException {
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
            ResultSet rs = con.consulta("SELECT sucursal FROM ventas_mepal");
            while(rs.next()){
            if(rs.getString(1).equals("Bogota")) sucursal1++;
            if(rs.getString(1).equals("Medellin")) sucursal2++;
            if(rs.getString(1).equals("Barranquilla")) sucursal3++;
            if(rs.getString(1).equals("Cali")) sucursal4++;
            if(rs.getString(1).equals("Cartagena")) sucursal5++;
            if(rs.getString(1).equals("Eje Cafetero")) sucursal6++;
            }
        }
        con.cerrarConsulta();
        con.desconectar();
        dataset.setValue(sucursal1, "Ventas", "Bogota");
        dataset.setValue(sucursal2, "Ventas", "Medellin");
        dataset.setValue(sucursal3, "Ventas", "Cali");
        dataset.setValue(sucursal4, "Ventas", "Cartagena");
        dataset.setValue(sucursal5, "Ventas", "Medellin");
        dataset.setValue(sucursal6, "Ventas", "Eje Cafetero");

        return dataset;
    }

    private JFreeChart crearChart(CategoryDataset data) {
        JFreeChart chart = ChartFactory.createBarChart(
                "", //Nombre de la gr�fica
                "Cantidad de Ventas", //Nombre del eje Horizontal
                "Sucursal", //Nombre del eje vertical
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
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.yellow, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

        return chart;
    }
}


