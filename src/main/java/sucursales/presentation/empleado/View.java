package sucursales.presentation.empleado;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


public class View implements Observer {

    private JPanel panel;
    private JTextField cedulaFld;
    private JTextField nombreFld;
    private JTextField telefonoField;
    private JTextField salarioField;
    private JTextField sucursalField;
    private JButton cancelarFld;
    private JLabel cedulaLbl;
    private JLabel nombreLbl;
    private JLabel mapaLabel;
    private JLabel telefonoL;
    private JLabel salarioL;
    private JLabel sucursalL;
    private JButton gurdarFld;
    private JFrame window;
    private Image mapa;

    public View() {
        gurdarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    clean();
                    if (validate()) {
                        Empleado n = null;
                        try {
                            n = take();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                        try {
                            controller.guardar(n);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(panel, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                        }
                    }
            }
        });
        cancelarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
            }
        }
        );
    }

    public JPanel getPanel() {
        return panel;
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        Empleado current = model.getCurrent();
            this.cedulaFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
            this.cedulaFld.setText(current.getCedula());
            nombreFld.setText(current.getNombre());
            telefonoField.setText(current.getTelefono());
            if(String.valueOf(current.getSalarioBase()).equals("0.0")){
                salarioField.setText("");
            }
            else{
                salarioField.setText(String.valueOf(current.getSalarioBase()));
            }
            if(current.getSucursal() != null){
                sucursalField.setText(current.getSucursal().getReferencia());
            }
            else{
                sucursalField.setText("");
            }

        this.panel.validate();
    }

    public Empleado take() throws Exception {
        Empleado e = new Empleado();
            e.setCedula(cedulaFld.getText());
            e.setNombre(nombreFld.getText());
            e.setTelefono(telefonoField.getText());
            e.setSalarioBase(Double.parseDouble(salarioField.getText()));
            e.setSucursal(Service.instance().sucursalGet(sucursalField.getText()));
            e.setSalarioTotal(e.getSalarioBase() + (e.getSalarioBase() / 100));
            return e;
    }

    public void clean() {
        cedulaLbl.setBorder(new EmptyBorder(0, 0, 2, 0));
        nombreLbl.setBorder(new EmptyBorder(0, 0, 2, 0));
        telefonoL.setBorder(new EmptyBorder(0, 0, 2, 0));
        salarioL.setBorder(new EmptyBorder(0, 0, 2, 0));
        sucursalL.setBorder(new EmptyBorder(0, 0, 2, 0));
    }

    private boolean validate() {
        boolean valid = true;
        if (cedulaFld.getText().isEmpty()) {
            valid = false;
            cedulaLbl.setBorder(Application.BORDER_ERROR);
        } else if(!cedulaFld.getText().matches("[0-9]+")){
            valid = false;
            cedulaLbl.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Cedula debe ser numerico","ERROR",JOptionPane.ERROR_MESSAGE);

        }
        else {
            cedulaLbl.setBorder(null);
            cedulaLbl.setToolTipText(null);
        }

        if (nombreFld.getText().length() == 0) {
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            nombreLbl.setToolTipText("Nombre requerido");
        } else if(!nombreFld.getText().matches("^[a-zA-Z]+$")){
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "El nombre no puede ser numerico","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else {
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
        }

        if (telefonoField.getText().length() == 0) {
            valid = false;
            telefonoL.setBorder(Application.BORDER_ERROR);
        } else if(!telefonoField.getText().matches("[0-9]+")) {
            valid = false;
            telefonoL.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Telefono debe llenarse con numeros enteros","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else {
            telefonoL.setBorder(null);
            telefonoL.setToolTipText(null);
        }
        if (salarioField.getText().length() == 0) {
            valid = false;
            salarioL.setBorder(Application.BORDER_ERROR);
        } else if(!salarioField.getText().matches("^[0-9]+\\.?[0-9]*$")) {
            valid = false;
            salarioL.setBorder(Application.BORDER_ERROR);
        } else {
            salarioL.setBorder(null);
            salarioL.setToolTipText(null);
        }
        if (sucursalField.getText().length() == 0) {
            valid = false;
            sucursalL.setBorder(Application.BORDER_ERROR);
        } else if(Service.instance().sucursalGet(sucursalField.getText()) == null) {
            valid = false;
            sucursalL.setBorder(Application.BORDER_ERROR);
        } else {
            sucursalL.setBorder(null);
            sucursalL.setToolTipText(null);
        }
        if(cedulaFld.getText().isEmpty() && nombreFld.getText().isEmpty() && salarioField.getText().isEmpty() && telefonoField.getText().isEmpty() && sucursalField.getText().isEmpty() ){
            JOptionPane.showMessageDialog(panel, "Todos los espacios estan vacios!!!!","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if( nombreFld.getText().isEmpty() &&  telefonoField.getText().isEmpty() && salarioField.getText().isEmpty() && sucursalField.getText().isEmpty() ){
            JOptionPane.showMessageDialog(panel, "Falto el nombre, el telefono,el salario, y la sucursal!!!!","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(  telefonoField.getText().isEmpty() && salarioField.getText().isEmpty()  && sucursalField.getText().isEmpty() ){
            JOptionPane.showMessageDialog(panel, "Falto  el telefono, el salario, y la sucursal!!!!","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if( salarioField.getText().isEmpty() && sucursalField.getText().isEmpty() ){
            JOptionPane.showMessageDialog(panel, "Falto el telefono y la sucursal!!!!","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if( sucursalField.getText().isEmpty() ){
            JOptionPane.showMessageDialog(panel, "Falto la sucursal!!!!","ERROR",JOptionPane.ERROR_MESSAGE);
        }


        return valid;
    }

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here

        mapaLabel = new JLabel();
        mapa = ImageIO.read(new File("src/main/resources/MapCR.png"));
        mapa = mapa.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        BufferedImage result = new BufferedImage(400,400, BufferedImage.TYPE_INT_ARGB);
        Graphics g = result.getGraphics();
        g.drawImage(mapa, 10, 10, mapaLabel);
        mapaLabel.setIcon(new ImageIcon(result));
//        g.drawImage(mapa, 30, 40,mapaLabel);
//        mapaLabel.setIcon(new ImageIcon(result));
    }
}
