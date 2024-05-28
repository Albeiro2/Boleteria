
package principal;

import modelo.Cliente;
import modelo.Operador;
import vista.Login;
import controlador.Control;
import vista.General;
import vista.GenerarBoleta;

public class Main {
    
    
    public static void main(String[] args) {
     Cliente cliente = new Cliente();
    Operador opera = new Operador();
     Login login = new Login();
    General general = new General();
    GenerarBoleta generarBoleta = new GenerarBoleta();
    Control controlador = new Control(cliente,opera,login,general,generarBoleta);
    controlador.start();
    }
    
   
   
}
