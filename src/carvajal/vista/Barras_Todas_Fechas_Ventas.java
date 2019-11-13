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


public class Barras_Todas_Fechas_Ventas extends ApplicationFrame
{	
    public ChartPanel panel;
    public String empresa;
    public int tam;
    public Barras_Todas_Fechas_Ventas(String empresa) 
    {
        
    	super(empresa);
        this.empresa=empresa;
	    CategoryDataset dataset = crearDataset();
	    JFreeChart chart = crearChart(dataset);
	    panel = new ChartPanel(chart,false);
	    panel.setPreferredSize(new Dimension(758,510));
	    setContentPane(panel);
	    pack();
    }    
     
                
    private CategoryDataset crearDataset()
	{	    
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            
            Conexion con = new Conexion();
            boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
            for (int a = 2010; a <= 2017; a++) {
                for (int m = 1; m <= 12; m++) {
                    if (!error) {
                            ResultSet rs = con.consulta("SELECT * FROM ventas_" +empresa +" WHERE YEAR(fecha)="+a+" AND MONTH(fecha)="+m);
                            tam =con.getSizeQuery(rs);
                            
                        }
                    if(m==1){
                    dataset.addValue(tam, "Enero", a+"");
                    tam=0;
                    }
                    if(m==2){
                    dataset.addValue(tam, "Febrero", a+"");
                    tam=0;
                    }
                    if(m==3){
                    dataset.addValue(tam, "Marzo", a+"");
                    tam=0;
                    }
                    if(m==4){
                    dataset.addValue(tam, "Abril", a+"");
                    tam=0;
                    }
                    if(m==5){
                    dataset.addValue(tam, "Mayo", a+"");
                    tam=0;
                    }
                    if(m==6){
                    dataset.addValue(tam, "Junio", a+"");
                    tam=0;
                    }
                    if(m==7){
                    dataset.addValue(tam, "Julio", a+"");
                    tam=0;
                    }
                    if(m==8){
                    dataset.addValue(tam, "Agosto", a+"");
                    tam=0;
                    }
                    if(m==9){
                    dataset.addValue(tam, "Septiempre", a+"");
                    tam=0;
                    }
                    if(m==10){
                    dataset.addValue(tam, "Octubre", a+"");
                    tam=0;
                    }
                    if(m==11){
                    dataset.addValue(tam, "Noviembre", a+"");
                    tam=0;
                    }
                    if(m==12){
                    dataset.addValue(tam, "Diciembre", a+"");
                    tam=0;
                    }
                    
                }
            }
            
            con.cerrarConsulta();
            con.desconectar();

	    return dataset;
	}
    
    
    private JFreeChart crearChart(CategoryDataset data)
	{		
	    JFreeChart chart= ChartFactory.createBarChart(
	            "",        //Nombre de la grafica
	            "Años",               //Nombre del eje Horizontal
	            "Ventas",                  //Nombre del eje vertical
	            data,                       //Data
	            PlotOrientation.VERTICAL, //Orientacion HORIZONTAL o VERTICAL
	            true,                       //Incluir leyenda
	            true,                       //Informacion al pasar el mouse
	            true);                      //URls
	            
	    
	    chart.setBackgroundPaint(Color.ORANGE);//Dar color al fondo del panel
	    
	    CategoryPlot plot =(CategoryPlot) chart.getPlot();//Dar color a cada categoria
	    	    	    
	    plot.setBackgroundPaint(Color.WHITE);//Color del fondo del gr�fico
	    
	    plot.setDomainGridlinesVisible(true);//Lineas divisorias
	    plot.setRangeGridlinePaint(Color.BLACK);
	    
	    //Calculo de los valores en el eje x
	    NumberAxis rangeAxis=(NumberAxis) plot.getRangeAxis();
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	 
	    BarRenderer renderer = (BarRenderer) plot.getRenderer();
	    renderer.setDrawBarOutline(false);
	    
	    //Dar color a cada barra
	    GradientPaint gp0= new GradientPaint(0.0f,0.0f,Color.blue,0.0f,0.0f,new Color(0,0,64));
	    GradientPaint gp1= new GradientPaint(0.0f,0.0f,Color.green,0.0f,0.0f,new Color(0,64,0));
	    GradientPaint gp2= new GradientPaint(0.0f,0.0f,Color.red,0.0f,0.0f,new Color(64,0,0));
	    renderer.setSeriesPaint(0,gp0);
	    renderer.setSeriesPaint(1,gp1);
	    renderer.setSeriesPaint(2,gp2);
	 
	    CategoryAxis domainAxis = plot.getDomainAxis();
	    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI/6.0));
	    
	    return chart;
	}
}