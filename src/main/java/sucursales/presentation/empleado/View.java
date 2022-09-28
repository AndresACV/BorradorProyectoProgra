package sucursales.presentation.empleado;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;


public class View implements Observer {

    private JPanel panel;

    private JLabel cedulaLabel;
    private JLabel nombreLabel;
    private JLabel telefonoLabel;
    private JLabel salarioLabel;
    private JLabel sucursalLabel;

    private JTextField cedulaField;
    private JTextField nombreField;
    private JTextField telefonoField;
    private JTextField salarioField;
    private JTextField sucursalField;

    private JButton guardarButton;
    private JButton cancelarButton;

    private JLabel mapLabel;
    private JLabel selectedLabel;
    private JLabel unselectedLabel;

    private Image mapImage;
    private Image sucursalSelectedImage;
    private Image sucursalUnselectedImage;

    Controller controller;
    Model model;

    public View() {
        guardarButton.addActionListener(e -> {
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
        });
        cancelarButton.addActionListener(e -> controller.hide());
    }

    public JPanel getPanel() {
        return panel;
    }

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
            this.cedulaField.setEnabled(model.getModo() == Application.MODO_AGREGAR);
            this.cedulaField.setText(current.getCedula());
            nombreField.setText(current.getNombre());
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

            actualizarMapa();

        this.panel.validate();
    }

    public Empleado take() throws Exception {
        Empleado e = new Empleado();
            e.setCedula(cedulaField.getText());
            e.setNombre(nombreField.getText());
            e.setTelefono(telefonoField.getText());
            e.setSalarioBase(Double.parseDouble(salarioField.getText()));
            e.setSucursal(Service.instance().sucursalGet(sucursalField.getText()));
            e.setSalarioTotal(e.getSalarioBase() + (e.getSalarioBase() / 100));
            return e;
    }

    public void clean() {
        cedulaLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
        nombreLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
        telefonoLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
        salarioLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
        sucursalLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
    }

    private boolean validate() {
        boolean valid = true;
        String mensajeError = "";
        int concatenaciones = 0;

        if (cedulaField.getText().isEmpty()) {
            valid = false;
            cedulaLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Cedula requerida. "; concatenaciones++;
        } else if(!cedulaField.getText().matches("[0-9]+")){
            valid = false;
            cedulaLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Cedula debe ser numerico. ";
        }
        else {
            cedulaLabel.setBorder(null);
            cedulaLabel.setToolTipText(null);
        }

        if (nombreField.getText().length() == 0) {
            valid = false;
            nombreLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Nombre requerido. "; concatenaciones++;
        } else if(!nombreField.getText().matches("^[a-z\\sA-Z]+$")){
            valid = false;
            nombreLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "El nombre no puede ser numerico. ";
        }
        else {
            nombreLabel.setBorder(null);
            nombreLabel.setToolTipText(null);
        }

        if (telefonoField.getText().length() == 0) {
            valid = false;
            telefonoLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Telefono requerido. "; concatenaciones++;
        } else if(!telefonoField.getText().matches("[0-9]+")) {
            valid = false;
            telefonoLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "El telefono debe llenarse con numeros enteros. ";
        } else if(telefonoField.getText().length() != 8) {
            valid = false;
            telefonoLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "El telefono debe tener 8 digitos. ";
        }
        else {
            telefonoLabel.setBorder(null);
            telefonoLabel.setToolTipText(null);
        }

        if (salarioField.getText().length() == 0) {
            valid = false;
            salarioLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Salario requerido. "; concatenaciones++;
        } else if(!salarioField.getText().matches("^[0-9]+\\.?[0-9]*$")) {
            valid = false;
            salarioLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "El salario debe ser numerico. ";
        } else {
            salarioLabel.setBorder(null);
            salarioLabel.setToolTipText(null);
        }

        if (sucursalField.getText().length() == 0) {
            valid = false;
            sucursalLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Sucursal requerida. "; concatenaciones++;
        } else if(Service.instance().sucursalGet(sucursalField.getText()) == null) {
            valid = false;
            sucursalLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "La sucursal no existe. ";
        } else {
            sucursalLabel.setBorder(null);
            sucursalLabel.setToolTipText(null);
        }
        if(concatenaciones == 5){
            JOptionPane.showMessageDialog(panel, "Todos los campos son requeridos","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(!mensajeError.equals("")){
            JOptionPane.showMessageDialog(panel, mensajeError,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return valid;

    }

    public void actualizarMapa(){
        mapLabel.removeAll();
        llenarMapa();
        panel.updateUI();
    }

    private void llenarMapa() {
        for (int j = 0; j < model.getSucursales().size(); j++) {
            JLabel temp = new JLabel();
            Sucursal s = model.getSucursales().get(j);
            temp.setSize(30, 30);
            temp.setLocation(s.getX() - 15, s.getY() - 31);
            temp.setToolTipText("<html>" + s.getReferencia()  + "<br/>" + s.getDireccion() +"</html>");
            temp.setIcon(new ImageIcon(sucursalUnselectedImage));
            if(sucursalField.getText().equals(s.getReferencia())){
                temp.setIcon(new ImageIcon(sucursalSelectedImage));
            }
            temp.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    temp.setIcon(new ImageIcon(sucursalSelectedImage));
                    sucursalField.setText(s.getReferencia());
                    sucursalField.setForeground(Color.RED);
                    actualizarMapa();
                }
            });
            temp.setVisible(true);
            mapLabel.add(temp);
        }
    }


    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        init();
    }

    public void init() {
        try {
            mapLabel = new JLabel(); mapLabel.removeAll();
            selectedLabel = new JLabel();
            unselectedLabel = new JLabel();

            sucursalSelectedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SucursalSel.png")));
            sucursalUnselectedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sucursal.png")));
            mapImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/MapCR.png")));

            sucursalSelectedImage = sucursalSelectedImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            sucursalUnselectedImage = sucursalUnselectedImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            mapImage = mapImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);

            selectedLabel.setIcon(new ImageIcon(sucursalSelectedImage));
            unselectedLabel.setIcon(new ImageIcon(sucursalUnselectedImage));
            mapLabel.setIcon(new ImageIcon(mapImage));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
