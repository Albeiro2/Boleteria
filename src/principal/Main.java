
package principal;

import modelo.Cliente;
import modelo.Operador;
import vista.Login;
import controlador.Control;

public class Main {
    
    
    public static void main(String[] args) {
     Cliente cliente = new Cliente();
    Operador opera = new Operador();
     Login login = new Login();
    
    Control controlador = new Control(cliente,opera,login);
    controlador.start();
    }
    
   
   
}
