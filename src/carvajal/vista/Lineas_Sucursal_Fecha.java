package carvajal.vista;

import carvajal.controlador.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Lineas_Sucursal_Fecha {

    public ChartPanel frame;
    int tam;
    public Lineas_Sucursal_Fecha(String empresa, String sucursal) throws SQLException {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Conexion con = new Conexion();
        boolean error = con.conectarMySQL("carvajal", "root", "", "localhost");//"127.0.0.1"
        for (int a = 2010; a <= 2017; a++) {
                for (int m = 1; m <= 12; m++) {
                    if (!error) {
                            ResultSet rs = con.consulta("SELECT * FROM ventas_" +empresa +" WHERE sucursal='"+sucursal+"' AND YEAR(fecha)="+a+" AND MONTH(fecha)="+m);
                            tam =con.getSizeQuery(rs);
                            
                        }
                    if(m==1){
                    dataset.addValue(tam, a+"", "Enero");
                    tam=0;
                    }
                    if(m==2){
                    dataset.addValue(tam, a+"", "Febrero");
                    tam=0;
                    }
                    if(m==3){
                    dataset.addValue(tam, a+"", "Marzo");
                    tam=0;
                    }
                    if(m==4){
                    dataset.addValue(tam, a+"", "Abril");
                    tam=0;
                    }
                    if(m==5){
                    dataset.addValue(tam, a+"", "Mayo");
                    tam=0;
                    }
                    if(m==6){
                    dataset.addValue(tam, a+"", "Junio");
                    tam=0;
                    }
                    if(m==7){
                    dataset.addValue(tam, a+"", "Julio");
                    tam=0;
                    }
                    if(m==8){
                    dataset.addValue(tam, a+"", "Agosto");
                    tam=0;
                    }
                    if(m==9){
                    dataset.addValue(tam, a+"", "Septiempre");
                    tam=0;
                    }
                    if(m==10){
                    dataset.addValue(tam, a+"", "Octubre");
                    tam=0;
                    }
                    if(m==11){
                    dataset.addValue(tam, a+"", "Noviembre");
                    tam=0;
                    }
                    if(m==12){
                    dataset.addValue(tam, a+"", "Diciembre");
                    tam=0;
                    }
                    
                }
            }
        JFreeChart chart = ChartFactory.createLineChart3D(
                "", // Titulo
                "Referencia producto", // Etiqueta Coordenada X
                "Cantidad de productos vendidos", // Etiqueta Coordenada Y
                dataset, // Datos
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