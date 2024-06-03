
package controlador;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Operador;
import vista.General;
import vista.GenerarBoleta;
import vista.Login;

public class Control implements ActionListener {
    
    private Cliente cliente;
    private Operador opera;
    private Login login;
    private General general;
    private GenerarBoleta generarBoleta;
    public Control(Cliente cliente, Operador opera, Login login,General general,GenerarBoleta generarBoleta) {
        this.cliente = cliente;
        this.opera = opera;
        this.login = login;
        this.general = general;
        this.generarBoleta = generarBoleta;
        login.botonIniciar.addActionListener(this);
        generarBoleta.generarBoletoAntiguo.addActionListener(this);
        general.botonGenerarBoleta.addActionListener(this);
        generarBoleta.generarBoletoPrimer.addActionListener(this);
        generarBoleta.limpiarAntiguo.addActionListener(this);
        generarBoleta.limpiarNuevo.addActionListener(this);
    }
    
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == login.botonIniciar){
            iniciarSesion();
        }
        
        if(e.getSource() == generarBoleta.generarBoletoAntiguo){
            generarBoletoAntiguo();
        }
        
        if(e.getSource() == general.botonGenerarBoleta){
            general.setVisible(false);
            refrescarAntiguo();
            refrescarNuevo();
            generarBoleta.setVisible(true);
        }
        
        if(e.getSource() == generarBoleta.generarBoletoPrimer){
            generarBoletoPrimer();
        }
        
        if(e.getSource() == generarBoleta.limpiarAntiguo){
            refrescarAntiguo();
        }
        
        if(e.getSource() == generarBoleta.limpiarNuevo){
            refrescarNuevo();
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
        generarBoleta.setLocationRelativeTo(null);
    }
    
    private void generarBoletoAntiguo(){
        String codigo;
        try {
        String correo = generarBoleta.cajaCorreoAntiguo.getText();
        int acompanantes = Integer.parseInt(generarBoleta.cajaAcompaAntiguo.getText());
        String campo2 = generarBoleta.cajaAcompaAntiguo.getText();
        
        if(!correo.equals("") && !campo2.equals("")){
            Cliente cliente = new Cliente();
            if(opera.comprobarExistencia(correo,cliente)){
                if(!opera.codgioYagenerado(cliente)){
                    do{
                    codigo = generarCodigo();
                }while(opera.codigoRepetido(codigo));
                cliente.setAcompanantes(acompanantes);
                opera.generarCodigoAntiguo(codigo, cliente);
                generarBoleta.boletoAntiguo.setVisible(true);
                generarBoleta.boletoAntiguo.setText("Boleta: "+codigo);
                }else{
                    JOptionPane.showMessageDialog(null, "Este participante ya tiene una boleta disponible");
                }
            }else{
                JOptionPane.showMessageDialog(null, "Este parcipante no esta registrado en eventos anteriores");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Revisa que los campos esten llenos");
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Puede que algun valor en los campos este incorrecto o vacio");
        }   
    }
    
    private void generarBoletoPrimer(){
        Cliente cliente;
        String codigo;
        try {
            cliente = new Cliente();
            cliente.setNombre(generarBoleta.cajaNombrePrimer.getText());
            cliente.setCorreo(generarBoleta.cajaCorreoPrimer.getText());
            cliente.setTelefono(generarBoleta.cajaTelefonoPrimer.getText());
            cliente.setAcompanantes(Integer.parseInt(generarBoleta.cajaAcompaNuevo.getText()));
            
            if(cliente.getNombre().equals("") || cliente.getCorreo().equals("") || cliente.getTelefono().equals("")){
                JOptionPane.showMessageDialog(null, "Asegurese de llenar todos los campos");
            }else{
                if(opera.comprobarExistencia(cliente.getCorreo(), cliente)){
                    JOptionPane.showMessageDialog(null, "Este participante ya esta registrado en eventos anteriores");
                }else{
                    do{
                        codigo = generarCodigo();
                    }while(opera.codigoRepetido(codigo));
                    cliente.setCodigo(codigo);
                    opera.generarCodigoPrimer(cliente);
                    generarBoleta.boletoNuevo.setVisible(true);
                    generarBoleta.boletoNuevo.setText("Booleta: "+codigo);
                }
            }
            
            
        } catch (Exception e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "¡Cantidad de acompañantes no valido!");
        }
        
        
    }
    
     public String generarCodigo() {
        String caracteres = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder codigo = new StringBuilder(5);

        for (int i = 0; i < 5; i++) {
            int indice = random.nextInt(caracteres.length());
            codigo.append(caracteres.charAt(indice));
        }

        return codigo.toString();
    }
     
   public void refrescarAntiguo(){
       generarBoleta.boletoAntiguo.setVisible(false);
       generarBoleta.boletoAntiguo.setText("");
       generarBoleta.cajaCorreoAntiguo.setText(null);
       generarBoleta.cajaAcompaAntiguo.setText(null);
   }
   
   public void refrescarNuevo(){
       generarBoleta.boletoNuevo.setVisible(false);
       generarBoleta.boletoNuevo.setText("");
       generarBoleta.cajaCorreoPrimer.setText(null);
       generarBoleta.cajaAcompaNuevo.setText(null);
       generarBoleta.cajaNombrePrimer.setText(null);
       generarBoleta.cajaTelefonoPrimer.setText(null);
   }
    
}
