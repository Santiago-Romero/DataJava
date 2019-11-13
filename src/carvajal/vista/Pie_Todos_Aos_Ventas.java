/*
 * Codigo por Santiago Romero Andrade
 */
package carvajal.vista;

import carvajal.controlador.Conexion;
import java.awt.Color;
import java.sql.ResultSet;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;

public class Pie_Todos_Aos_Ventas extends ApplicationFrame {

    public ChartPanel chartPanel;
    public String empresa;

    public Pie_Todos_Aos_Ventas(final String empresa) {

        super(empresa);
        this.empresa=empresa;
        JFreeChart chart = crearChart(crearDataSet());

        // add the chart to a panel...
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(758,510));
        setContentPane(chartPanel);
        pack();
    }

    private PieDataset crearDataSet() {
        
        DefaultPieDataset data = new DefaultPieDataset();
        
        Conexion con = new Conexion();
            boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
            int cant=0;
            for (int a = 2000; a <= 2017; a++) {
                    if (!error) {
                            ResultSet rs = con.consulta("SELECT * FROM ventas_" +empresa +" WHERE YEAR(fecha)="+a);
                            cant=con.getSizeQuery(rs);
                        }
                    if(a==2000) data.setValue("2000", new Double(cant));
                    if(a==2001) data.setValue("2001", new Double(cant));
                    if(a==2002) data.setValue("2002", new Double(cant));
                    if(a==2003) data.setValue("2003", new Double(cant));
                    if(a==2004) data.setValue("2004", new Double(cant));
                    if(a==2005) data.setValue("2005", new Double(cant));
                    if(a==2006) data.setValue("2006", new Double(cant));
                    if(a==2007) data.setValue("2007", new Double(cant));
                    if(a==2008) data.setValue("2008", new Double(cant));
                    if(a==2009) data.setValue("2009", new Double(cant));
                    if(a==2010) data.setValue("2010", new Double(cant));
                    if(a==2011) data.setValue("2011", new Double(cant));
                    if(a==2012) data.setValue("2012", new Double(cant));
                    if(a==2013) data.setValue("2013", new Double(cant));
                    if(a==2014) data.setValue("2014", new Double(cant));
                    if(a==2015) data.setValue("2015", new Double(cant));
                    if(a==2016) data.setValue("2016", new Double(cant));
                    if(a==2017) data.setValue("2017", new Double(cant));
                    
                        
            }

            con.cerrarConsulta();
            con.desconectar();

        return data;
    }

    private JFreeChart crearChart(PieDataset data) {
        // create the chart...
        JFreeChart chart = ChartFactory.createPieChart3D(
                "", // chart title
                data, // data
                true, // include legend
                true,
                false
        );
        Color c = UIManager.getLookAndFeel().getDefaults().getColor("Panel.background");

        chart.setBackgroundPaint(new Color(c.getRed(), c.getGreen(), c.getBlue()));//Color de fonde de la ventana
        chart.getTitle().setPaint(Color.blue); //Dar color al titulo

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelBackgroundPaint(Color.ORANGE);//Color de las etiquetas
        plot.setForegroundAlpha(0.60f);
        plot.setNoDataMessage("No hay data");

        Rotator rotator = new Rotator(plot);
        rotator.start();

        return chart;
    }
}
