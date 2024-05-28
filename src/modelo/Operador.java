
package modelo;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class Operador {
    
    PreparedStatement ps;
    ResultSet rs;
    Conexion con = new Conexion();
    
    public boolean iniciarSesion(String usuario, String contrasena){
        
       boolean correcto = false;
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select * from usuario");
            rs = ps.executeQuery();
            
            while(rs.next()){
                if(rs.getString(2).equals(usuario) && rs.getString(3).equals(contrasena)){
                    correcto = true;
                }
            }
            return correcto;
        } catch (Exception e) {
            return correcto;
        }
    }
}
