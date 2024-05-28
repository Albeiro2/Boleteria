
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Operador;
import vista.General;
import vista.Login;

public class Control implements ActionListener {
    
    private Cliente cliente;
    private Operador opera;
    private Login login;
    private General general;
    public Control(Cliente cliente, Operador opera, Login login,General general) {
        this.cliente = cliente;
        this.opera = opera;
        this.login = login;
        this.general = general;
        login.botonIniciar.addActionListener(this);
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == login.botonIniciar){
            iniciarSesion();
        }
    }
    
    private void iniciarSesion(){
        char [] pass1 = login.campoPass.getPassword();
        String user = login.campoUser.getText();
        String pass = new String(pass1);
        
        if((!user.equals("")) && !pass.equals("")){
            if(opera.iniciarSesion(user, pass)){
                JOptionPane.showMessageDialog(null,"Sesion Iniciada");
                login.dispose();
                general.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null,"Usuario o contraseña incorrecta");
            }
        }
    }
    
    public void start(){
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        general.setLocationRelativeTo(null);
    }
    
}
