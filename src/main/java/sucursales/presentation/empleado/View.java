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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private JButton cancelarFld;
    private JLabel cedulaLbl;
    private JLabel nombreLbl;
    private JLabel telefonoL;
    private JLabel salarioL;
    private JLabel sucursalL;
    private JButton gurdarFld;
    private JFrame window;

    private JLabel mapLabel;
    private Image mapImage;
    private Graphics graphics;
    private BufferedImage result;
    private JLabel selectedLabel;
    private Image sucursalSelectedImage;

    private JLabel unselectedLabel;
    private Image sucursalUnselectedImage;

    private String referenciaTemporal;


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

            actualizarMapa();

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
        String mensajeError = "";
        int concatenaciones = 0;

        if (cedulaFld.getText().isEmpty()) {
            valid = false;
            cedulaLbl.setBorder(Application.BORDER_ERROR);
            mensajeError += "Cedula requerida. "; concatenaciones++;
        } else if(!cedulaFld.getText().matches("[0-9]+")){
            valid = false;
            cedulaLbl.setBorder(Application.BORDER_ERROR);
            mensajeError += "Cedula debe ser numerico. ";
        }
        else {
            cedulaLbl.setBorder(null);
            cedulaLbl.setToolTipText(null);
        }

        if (nombreFld.getText().length() == 0) {
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            mensajeError += "Nombre requerido. "; concatenaciones++;
        } else if(!nombreFld.getText().matches("^[a-z\\sA-Z]+$")){
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            mensajeError += "El nombre no puede ser numerico. ";
        }
        else {
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
        }

        if (telefonoField.getText().length() == 0) {
            valid = false;
            telefonoL.setBorder(Application.BORDER_ERROR);
            mensajeError += "Telefono requerido. "; concatenaciones++;
        } else if(!telefonoField.getText().matches("[0-9]+")) {
            valid = false;
            telefonoL.setBorder(Application.BORDER_ERROR);
            mensajeError += "El telefono debe llenarse con numeros enteros. ";
        } else if(telefonoField.getText().length() != 8) {
            valid = false;
            telefonoL.setBorder(Application.BORDER_ERROR);
            mensajeError += "El telefono debe tener 8 digitos. ";
        }
        else {
            telefonoL.setBorder(null);
            telefonoL.setToolTipText(null);
        }

        if (salarioField.getText().length() == 0) {
            valid = false;
            salarioL.setBorder(Application.BORDER_ERROR);
            mensajeError += "Salario requerido. "; concatenaciones++;
        } else if(!salarioField.getText().matches("^[0-9]+\\.?[0-9]*$")) {
            valid = false;
            salarioL.setBorder(Application.BORDER_ERROR);
            mensajeError += "El salario debe ser numerico. ";
        } else {
            salarioL.setBorder(null);
            salarioL.setToolTipText(null);
        }

        if (sucursalField.getText().length() == 0) {
            valid = false;
            sucursalL.setBorder(Application.BORDER_ERROR);
            mensajeError += "Sucursal requerida. "; concatenaciones++;
        } else if(Service.instance().sucursalGet(sucursalField.getText()) == null) {
            valid = false;
            sucursalL.setBorder(Application.BORDER_ERROR);
            mensajeError += "La sucursal no existe. ";
        } else {
            sucursalL.setBorder(null);
            sucursalL.setToolTipText(null);
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
        for (int j = 0; j < Service.instance().getData().getSucursales().size(); j++) {
            JLabel temp = new JLabel();
            Sucursal s = Service.instance().getData().getSucursales().get(j);
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
        selectedLabel = new JLabel();
        sucursalSelectedImage = ImageIO.read(new File("src/main/resources/SucursalSel.png"));
        selectedLabel.setIcon(new ImageIcon(sucursalSelectedImage));

        unselectedLabel = new JLabel();
        sucursalUnselectedImage = ImageIO.read(new File("src/main/resources/Sucursal.png"));
        unselectedLabel.setIcon(new ImageIcon(sucursalUnselectedImage));

        mapLabel = new JLabel();
        mapLabel.removeAll();
        mapImage = ImageIO.read(new File("src/main/resources/MapCR.png"));
        mapImage = mapImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        result = new BufferedImage(400,400, BufferedImage.TYPE_INT_ARGB);
        graphics = result.getGraphics();
        graphics.drawImage(mapImage, 10, 10, mapLabel);
        mapLabel.setIcon(new ImageIcon(result));
    }
}
