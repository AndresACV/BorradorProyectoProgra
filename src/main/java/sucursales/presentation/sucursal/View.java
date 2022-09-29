package sucursales.presentation.sucursal;

import sucursales.Application;
import sucursales.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    sucursales.presentation.sucursal.Controller controller;
    sucursales.presentation.sucursal.Model model;

    private JPanel panel;

    private JLabel codigoLabel;
    private JTextField codigoField;

    private JLabel referenciaLabel;
    private JTextField referenciaField;

    private JButton guardarButton;
    private JButton cancelarButton;

    private JLabel direccionLabel;
    private JTextField direccionField;

    private JLabel zonajeLabel;
    private JTextField zonajeField;

    private JLabel mapLabel;
    private Image mapImage;

    private JLabel chirulitoLabel;
    private Image chirulitoImage;

    Robot robot;
    Color outOfRangeColor;

    public View() throws IOException {
        guardarButton.addActionListener(e -> {
            cleanBorders();
            if (validate()) {
                try {
                    controller.guardar(take());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "El codigo debe ser unico","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                mapLabel.remove(chirulitoLabel);
                mapLabel.setIcon(new ImageIcon(mapImage));
            }
        });
        cancelarButton.addActionListener(e -> {
            controller.hide();
            mapLabel.remove(chirulitoLabel);
            mapLabel.setIcon(new ImageIcon(mapImage));
        }
        );
        mapLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!outOfRangeColor.equals(robot.getPixelColor(e.getXOnScreen(), e.getYOnScreen()))) {
                    Sucursal current = model.getCurrent();
                    current.setX(e.getX() - 15);
                    current.setY(e.getY() - 31);
                    chirulitoLabel.setLocation(current.getX(), current.getY());
                    mapLabel.add(chirulitoLabel);
                }
            }
        });
    }

    public JPanel getPanel() { return panel; }

    public void setController(sucursales.presentation.sucursal.Controller controller) { this.controller = controller; }

    public void setModel(sucursales.presentation.sucursal.Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        Sucursal current = model.getCurrent();
            this.codigoField.setEnabled(model.getModo() == Application.MODO_AGREGAR);
            this.codigoField.setText(current.getCodigo());
            referenciaField.setText(current.getReferencia());
            direccionField.setText(current.getDireccion());
            if(String.valueOf(current.getPorcentajeZonaje()).equals("0.0")){ zonajeField.setText(""); }
            else { zonajeField.setText(String.valueOf(current.getPorcentajeZonaje())); }

            if(model.getModo() == 1) {
                chirulitoLabel.setLocation(current.getX(), current.getY());
                chirulitoLabel.setToolTipText("<html>" + current.getReferencia() + "<br/>" + current.getDireccion() +"</html>");
                mapLabel.add(chirulitoLabel);
                mapLabel.setIcon(new ImageIcon(mapImage));
            }
            this.panel.validate();
    }

    public Sucursal take() {
        Sucursal s = new Sucursal();
        Sucursal current = model.getCurrent();

        s.setCodigo(codigoField.getText());
        s.setReferencia(referenciaField.getText());
        s.setDireccion(direccionField.getText());
        s.setPorcentajeZonaje(Double.parseDouble(zonajeField.getText()));

        s.setX(current.getX());   current.setX(0);
        s.setY(current.getY());   current.setY(0);
        return s;
    }

    public void cleanBorders(){
        codigoLabel.setBorder(null);
        referenciaLabel.setBorder(null);
        direccionLabel.setBorder(null);
        zonajeLabel.setBorder(null);
        mapLabel.setBorder(null);
    }
    private boolean validate() {
        boolean valid = true;
        String mensajeError = "";
        int concatenaciones = 0;

        if (codigoField.getText().isEmpty()) {
            valid = false;
            codigoLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Codigo requerido. "; concatenaciones++;
        } else if(!codigoField.getText().matches("[0-9]+")){
            valid = false;
            codigoLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Codigo debe ser numerico. ";        }

        if (referenciaField.getText().length() == 0) {
            valid = false;
            referenciaLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Referencia requerida. "; concatenaciones++;
        } else if(!referenciaField.getText().matches("^[a-z\\sA-Z]+$")){
            valid = false;
            referenciaLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Referencia no puede ser numerica. ";
        }
        if (direccionField.getText().isEmpty()) {
            valid = false;
            direccionLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Direccion requerida. "; concatenaciones++;
        }
        if (zonajeField.getText().isEmpty()) {
            valid = false;
            zonajeLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Zonaje requerido. "; concatenaciones++;
        } else if(!zonajeField.getText().matches("^[0-9]+\\.?[0-9]*$")) {
            valid = false;
            zonajeLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Zonaje debe ser numerico. ";
        }
        if (model.getCurrent().getX() == 0 && model.getCurrent().getY() == 0) {
            valid = false;
            mapLabel.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Debe seleccionar un lugar en el mapa","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        if(concatenaciones == 4){
            JOptionPane.showMessageDialog(panel, "Todos los campos son requeridos","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(!mensajeError.equals("")){
            JOptionPane.showMessageDialog(panel, mensajeError,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return valid;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            robot = new Robot();
            outOfRangeColor = new Color(236, 219, 194);

            mapLabel = new JLabel();
            mapImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/MapCR.png")));
            mapImage = mapImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            mapLabel.setIcon(new ImageIcon(mapImage));

            chirulitoLabel = new JLabel();
            chirulitoImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SucursalSel.png")));
            chirulitoImage = chirulitoImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            chirulitoLabel.setIcon(new ImageIcon(chirulitoImage));
            chirulitoLabel.setVisible(true);
            chirulitoLabel.setSize(30, 30);
            chirulitoLabel.setToolTipText("Sucursal");

        } catch (IOException | AWTException e) {
            throw new RuntimeException(e);
        }
    }
}