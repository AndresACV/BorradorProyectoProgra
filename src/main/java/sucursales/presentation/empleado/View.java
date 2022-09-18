package sucursales.presentation.empleado;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;


public class View implements Observer {

    private JPanel panel;
    private JTextField cedulaFld;
    private JTextField nombreFld;
    private JTextField telefonoField;
    private JTextField salarioField;
    private JTextField sucursalField;
    private JButton guardarFld;
    private JButton cancelarFld;
    private JLabel cedulaLbl;
    private JLabel nombreLbl;
    private JLabel mapaLabel;
    private JLabel telefonoL;
    private JLabel salarioL;
    private JLabel sucursalL;
    private JFrame window;

    public View() {
        guardarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
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
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
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
    public static boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }

    public Empleado take() throws Exception {
        Empleado e = new Empleado();
            e.setCedula(cedulaFld.getText());
            e.setNombre(nombreFld.getText());
            e.setTelefono(telefonoField.getText());
            e.setSalarioBase(Double.parseDouble(salarioField.getText()));
            e.setSucursal(Service.instance().sucursalGet(sucursalField.getText()));
            return e;
    }

    private boolean validate() throws Exception {
        boolean valid = true;
        if (cedulaFld.getText().isEmpty()) {
            valid = false;
            cedulaLbl.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Cedula requerida","ERROR",JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(panel, "Nombre requerido","ERROR",JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(panel, "Telefono requerido","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(!telefonoField.getText().matches("[0-9]+")) {
            valid = false;
            telefonoL.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Telefono debe ser numerico","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(telefonoField.getText().length() != 8) {
            valid = false;
            telefonoL.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Telefono debe tener 8 digitos","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else {
            telefonoL.setBorder(null);
            telefonoL.setToolTipText(null);
        }

        if (salarioField.getText().length() == 0) {
            valid = false;
            salarioL.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Salario requerido","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(!salarioField.getText().matches("[0-9]+")) {
            valid = false;
            salarioL.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Salario debe ser numerico","ERROR",JOptionPane.ERROR_MESSAGE);
        } else {
            salarioL.setBorder(null);
            salarioL.setToolTipText(null);
        }

        if (sucursalField.getText().length() == 0) {
            valid = false;
            sucursalL.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Sucursal requerida","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(Service.instance().sucursalGet(sucursalField.getText()) == null) {
            valid = false;
            sucursalL.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Sucursal no existe","ERROR",JOptionPane.ERROR_MESSAGE);
        } else {
            sucursalL.setBorder(null);
            sucursalL.setToolTipText(null);
        }
        return valid;
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
        mapaLabel = new JLabel();
        ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("src/main/resources/MapCR.png").getImage().getScaledInstance(400, 400, Image.SCALE_DEFAULT));
        mapaLabel.setIcon(imageIcon2);
    }
}
