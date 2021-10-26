/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Daniel
 */
public class BaseDeDatos {
    public Connection conexion (String Location)
    {
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection result = DriverManager.getConnection("jdbc:ucanaccess://"+Location);
            System.out.println("SE CONECTO");
            return result;
        }
        catch(Exception e)
        {
            System.out.println("La excepcion fue: "+e);
            return null; 
        }    
        
    }
    
     //Funcion para añadir, editar o eliminar de la base de datos a través de UCanAccess
     public void add_edit_delete (Connection x, String queries)
    {   Statement st;
        
        try
        {
            st=x.createStatement();
            st.executeUpdate(queries);
            
        }
        catch (Exception e)
        {
            System.out.println(e);        
        }        
    }
    public DefaultTableModel SEARCH(Connection x, String query,String results[],DefaultTableModel m)
    { ResultSet s=null;
      Statement st;
      
      
      try{
          st=x.createStatement();
          s=st.executeQuery(query);
          
          while(s.next())
          {
              for(int i=0; i<results.length; i++)
              {
                  results[i]=s.getString(i+1);
              }
              m.addRow(results);
          }    
          
      }
      catch(Exception e)
      {
          System.out.println(e);
      }
      return m;
    } 

    
}
