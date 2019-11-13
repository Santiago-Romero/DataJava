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

public class Barras_Categorias_VentasEmpaques extends ApplicationFrame {

    public ChartPanel panel;
    public JFreeChart chart;
    int categoria1, categoria2, categoria3, categoria4, categoria5, categoria6;

    public Barras_Categorias_VentasEmpaques(String title) throws SQLException {
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
            ResultSet rs = con.consulta("SELECT * FROM ventas_empaques");
            while(rs.next()){
            if(rs.getInt(3)==(1)) categoria1+=rs.getInt(4);
            if(rs.getInt(3)==(2)) categoria1+=rs.getInt(4);
            if(rs.getInt(3)==(3)) categoria1+=rs.getInt(4);
            if(rs.getInt(3)==(4)) categoria2+=rs.getInt(4);
            if(rs.getInt(3)==(5)) categoria3+=rs.getInt(4);
            if(rs.getInt(3)==(6)) categoria3+=rs.getInt(4);
            if(rs.getInt(3)==(7)) categoria2+=rs.getInt(4);
            if(rs.getInt(3)==(8)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(9)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(10)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(11)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(12)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(13)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(14)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(15)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(16)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(17)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(18)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(19)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(20)) categoria4+=rs.getInt(4);
            if(rs.getInt(3)==(21)) categoria5+=rs.getInt(4);
            if(rs.getInt(3)==(22)) categoria5+=rs.getInt(4);
            if(rs.getInt(3)==(23)) categoria5+=rs.getInt(4);
            if(rs.getInt(3)==(24)) categoria5+=rs.getInt(4);
            if(rs.getInt(3)==(25)) categoria5+=rs.getInt(4);
            if(rs.getInt(3)==(26)) categoria5+=rs.getInt(4);
            if(rs.getInt(3)==(27)) categoria2+=rs.getInt(4);
            if(rs.getInt(3)==(28)) categoria5+=rs.getInt(4);
            if(rs.getInt(3)==(29)) categoria6+=rs.getInt(4);
            if(rs.getInt(3)==(30)) categoria6+=rs.getInt(4);
            }
        }
        con.cerrarConsulta();
        con.desconectar();
        dataset.setValue(categoria1, "Ventas", "Lácteos");
        dataset.setValue(categoria2, "Ventas", "Frutas");
        dataset.setValue(categoria3, "Ventas", "Farmacos");
        dataset.setValue(categoria4, "Ventas", "Utensilios");
        dataset.setValue(categoria5, "Ventas", "Contenedor");
        dataset.setValue(categoria6, "Ventas", "Carnicos");

        return dataset;
    }

    private JFreeChart crearChart(CategoryDataset data) {
        JFreeChart chart = ChartFactory.createBarChart(
                "", //Nombre de la gr�fica
                "Categorias", //Nombre del eje Horizontal
                "Cantidad de Vendidos", //Nombre del eje vertical
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
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.orange, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

        return chart;
    }
}
