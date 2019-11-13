package carvajal.controlador;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {

    Connection conn = null; //Objeto para la conexion
    Statement stmt = null;//Objeto para ejecutar la consulta
    ResultSet rs = null;//Objeto para recuperar los resultados de la consulta

    public Conexion() {
        conn = null;
        stmt = null;
        rs = null;
    }

    //----------------------------------------------- MySQL -------------------------------------------------------
    public boolean conectarMySQL(String bd, String login, String password, String host) {
        boolean error = false;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception e) {
            error = true;
            JOptionPane.showMessageDialog(null,
                    "No se encuentra la referencia del conector de MySQL.\n" + e.getMessage(),
                    "Error de Conexi�n",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (!error) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + bd, login, password);
            } catch (SQLException ex) {
                error = true;
                JOptionPane.showMessageDialog(null,
                        "Error al tratar de conectar con la base de datos '" + bd + "'.\n\n"
                        + "MySQL dice: " + ex.getMessage(),
                        "Error de Conexi�n",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return error;
    }

    //----------------------------------------------- PostgreSQL -------------------------------------------------------
    public boolean conectarPostgres(String bd, String login, String password, String host) {
        boolean error = false;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception ex) {
            error = true;

            JOptionPane.showMessageDialog(null,
                    "No se encuentra la referencia del conector de PostgreSQL.\n" + ex.getMessage(),
                    "Error de Conexi�n",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (!error) {
            try {
                conn = DriverManager.getConnection("jdbc:postgresql://" + host + ":5432/" + bd, login, password);
            } catch (SQLException ex) {
                error = true;
                JOptionPane.showMessageDialog(null,
                        "Error al tratar de conectar con la base de datos '" + bd + "'.\n\n"
                        + "PostgreSQL dice: " + ex.getMessage(),
                        "Error de Conexi�n",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return error;
    }

    //----------------------------------------------- FireBird -------------------------------------------------------
    public boolean conectarFB(String bd, String login, String password, String host) {
        boolean error = false;

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");
        } catch (Exception ex) {
            error = true;

            JOptionPane.showMessageDialog(null,
                    "No se encuentra la referencia del conector de FireBird.\n" + ex.getMessage(),
                    "Error de Conexi�n",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (!error) {
            try {
                conn = DriverManager.getConnection("jdbc:firebirdsql:" + host + "/3050:" + bd, login, password);
            } catch (SQLException ex) {
                error = true;
                JOptionPane.showMessageDialog(null,
                        "Error al tratar de conectar con la base de datos '" + bd + "'.\n\n"
                        + "FireBird dice: " + ex.getMessage(),
                        "Error de Conexi�n",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return error;
    }

    //----------------------------------------------- Oracle -------------------------------------------------------
    public boolean conectarOracle(String login, String password, String host) {
        boolean error = false;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception ex) {
            error = true;

            JOptionPane.showMessageDialog(null,
                    "No se encuentra la referencia del conector de Oracle.\n" + ex.getMessage(),
                    "Error de Conexi�n",
                    JOptionPane.ERROR_MESSAGE);
        }

        if (!error) {
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":1521",
                        login,
                        password);
                /*conn = DriverManager.getConnection("jdbc:oracle:oci8:@", 
										login,
										password);*/
            } catch (SQLException ex) {
                error = true;
                JOptionPane.showMessageDialog(null,
                        "Error al tratar de conectar con la base de datos '" + login + "'.\n\n"
                        + "Oracle dice: " + ex.getMessage(),
                        "Error de Conexi�n",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return error;
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null,
                    "Error al tratar de cerrar la conexi�n con la base de datos.\n\n"
                    + "SQL Error: " + sqle.getMessage(),
                    "Error de Conexi�n",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean actualizar(String sql) {
        int resultado = 0;
        boolean error = false;
        stmt = null;//Objeto para ejecutar la consulta

        if (conn != null) {
            try {
                stmt = conn.createStatement();
                resultado = stmt.executeUpdate(sql);
                stmt.close();
            } catch (SQLException sqle) {
                error = true;
                JOptionPane.showMessageDialog(null,
                        "Error al tratar de actualizar la tabla.\n\n"
                        + "SQL Error: " + sqle.getMessage(),
                        "Error de actualizaci�n",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return error;
    }

    public ResultSet consulta(String sql) {
        boolean existe = false;
        stmt = null;//Objeto para ejecutar la consulta
        rs = null;//Objeto para recuperar los resultados de la consulta

        if (conn != null) {
            try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = stmt.executeQuery(sql);
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null,
                        "Error al tratar de consultar la tabla.\n\n"
                        + "SQL Error: " + sqle.getMessage(),
                        "Error de consulta",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        return rs;
    }

    public int getSizeQuery(ResultSet rs) {
        int cantFilas = -1;
        try {
            rs.last(); //Desplazar el puntero de lectura a la ultima fila (registro)
            cantFilas = rs.getRow(); //Calcular la cantidad de filas (registros) que arroja la consulta
            rs.beforeFirst(); //Desplazar el puntero de lectura a la primera fila (registro)
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null,
                    "Error al tratar de obtener la cantidad de registros resultantes de la consulta.\n\n"
                    + "SQL Error: " + sqle.getMessage(),
                    "Error de consulta",
                    JOptionPane.ERROR_MESSAGE);
        }

        return cantFilas;
    }

    public void cerrarConsulta() {
        try {
            rs.close(); //Cerrar el objeto que recupero los resultados de la consulta				
            stmt.close();//Cerrar el objeto que ejecuto la consulta
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null,
                    "Error al tratar de cerrar la consulta en la base de datos.\n\n"
                    + "SQL Error: " + sqle.getMessage(),
                    "Error de Consulta",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
