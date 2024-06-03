
package modelo;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class Operador {
    
    PreparedStatement ps;
    ResultSet rs;
    
    
    public boolean iniciarSesion(String usuario, String contrasena){
        Conexion con = new Conexion();
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
    
    public boolean comprobarExistencia(String correo,Cliente cliente){
        boolean existe = false;
         Conexion con = new Conexion();
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select id_cliente from cliente where correo = ?");
            ps.setString(1, correo);
            rs = ps.executeQuery();
            
            if(rs.next()){
                existe = true;
                cliente.setId(rs.getInt(1));
            }
            return existe;
        } catch (Exception e) {
            System.err.println(e);
            return existe;
            
        }
    }
    
    public boolean codigoRepetido(String codigo){
         boolean repetido = false;
         Conexion con = new Conexion();
         
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select codigo from codigo where codigo = ?");
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            
            if(rs.next()){
                repetido = true;
                
            }
            return repetido;
        } catch (Exception e) {
            System.err.println(e);
            return repetido;
            
        }
    }
    
    public void generarCodigoAntiguo(String codigo,Cliente cliente){
         Conexion con = new Conexion();
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("update cliente set codigo = ? where id_cliente = ?");
            ps.setString(1, codigo);
            ps.setInt(2, cliente.getId());
            ps.executeUpdate();
            
            ps = conexion.prepareStatement("update cliente set visitas = visitas + ? where id_cliente = ?");
            ps.setInt(1, 1);
            ps.setInt(2, cliente.getId());
            ps.executeUpdate();
            
            ps = conexion.prepareStatement("update cliente set disponible = true where id_cliente = ?");
            ps.setInt(1, cliente.getId());
            ps.executeUpdate();
            
            ps = conexion.prepareStatement("insert into codigo(codigo) values(?)");
            ps.setString(1, codigo);
            ps.executeUpdate();
            
            ps = conexion.prepareStatement("update cliente set acompanantes = ? where id_cliente = ?");
            ps.setInt(1, cliente.getAcompanantes());
            ps.setInt(2, cliente.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void generarCodigoPrimer(Cliente cliente){
         Conexion con = new Conexion();
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("insert into cliente(nombre,correo,telefono,acompanantes,codigo) values(?,?,?,?,?)");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCorreo());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getAcompanantes());
            ps.setString(5, cliente.getCodigo());
            ps.executeUpdate();
            
            conexion.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public boolean codgioYagenerado(Cliente cliente){
        boolean disponible = false;
        Conexion con = new Conexion();
        
        try {
            Connection conexion = con.getConnection();
            ps = conexion.prepareStatement("select disponible from cliente where id_cliente = ?");
            ps.setInt(1, cliente.getId());
            rs = ps.executeQuery();
            
            if(rs.next()){
                disponible = rs.getBoolean(1);
            }
            return disponible;
        } catch (Exception e) {
            System.err.println(e);
            return disponible;
        }
    }
}
