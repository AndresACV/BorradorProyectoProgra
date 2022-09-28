package sucursales.presentation.sucursal;

import sucursales.Application;
import sucursales.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

    private JLabel selectedLabel;
    private Image sucursalSelectedImage;

    private JLabel unselectedLabel;
    private Image sucursalUnselectedImage;

    private JLabel currentSucursal;
    private int currentX = 0;
    private int currentY = 0;

    public View() throws IOException {
        guardarButton.addActionListener(e -> {
            clean();
            if (validate()) {
                Sucursal n;
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
        cancelarButton.addActionListener(e -> {
            controller.hide();
            cleanMap();
        }
        );
        mapLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cleanMap();

                currentX = e.getX();
                currentY = e.getY();
                currentSucursal.setLocation(currentX - 15, currentY - 31);
                mapLabel.add(currentSucursal);
            }
        });
    }

    public JPanel getPanel() { return panel; }

    public void setController(sucursales.presentation.sucursal.Controller controller) { this.controller = controller; }

    public void setModel(sucursales.presentation.sucursal.Model model) {
        this.model = model;
        model.addObserver(this);
    }

    private void cleanMap(){
        mapLabel.remove(currentSucursal);
        mapLabel.setIcon(new ImageIcon(mapImage));
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        Sucursal current = model.getCurrent();
            this.codigoField.setEnabled(model.getModo() == Application.MODO_AGREGAR);
            this.codigoField.setText(current.getCodigo());
            referenciaField.setText(current.getReferencia());
            direccionField.setText(current.getDireccion());
            if(String.valueOf(current.getPorcentajeZonaje()).equals("0.0")){
                zonajeField.setText("");
            }
            else{
                zonajeField.setText(String.valueOf(current.getPorcentajeZonaje()));
            }

            if(model.getModo() == 0) { cleanMap(); }
            if(model.getModo() == 1) {
                currentSucursal.setLocation(current.getX() - 15, current.getY() - 31);
                currentSucursal.setToolTipText("<html>" + current.getReferencia() + "<br/>" + current.getDireccion() +"</html>");
                mapLabel.add(currentSucursal);
                mapLabel.setIcon(new ImageIcon(mapImage));
            }
            this.panel.validate();
    }

    public Sucursal take() {
        Sucursal s = new Sucursal();
        s.setCodigo(codigoField.getText());
        s.setReferencia(referenciaField.getText());
        s.setDireccion(direccionField.getText());
        s.setPorcentajeZonaje(Double.parseDouble(zonajeField.getText()));
        s.setX(currentX);
        s.setY(currentY);
        currentX = 0; currentY = 0;
        return s;
    }

    public void clean(){
        codigoLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
        referenciaLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
        direccionLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
        zonajeLabel.setBorder(new EmptyBorder(0, 0, 2, 0));
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
        else {
            codigoField.setBorder(null);
        }

        if (referenciaField.getText().length() == 0) {
            valid = false;
            referenciaLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Referencia requerida. "; concatenaciones++;
        } else if(!referenciaField.getText().matches("^[a-z\\sA-Z]+$")){
            valid = false;
            referenciaLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Referencia no puede ser numerica. ";
        } else {
            referenciaField.setBorder(null);
        }
        if (direccionField.getText().isEmpty()) {
            valid = false;
            direccionLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Direccion requerida. "; concatenaciones++;
        } else {
            codigoLabel.setBorder(null);
        }

        if (zonajeField.getText().isEmpty()) {
            valid = false;
            zonajeLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Zonaje requerido. "; concatenaciones++;
        } else if(!zonajeField.getText().matches("^[0-9]+\\.?[0-9]*$")) {
            valid = false;
            zonajeLabel.setBorder(Application.BORDER_ERROR);
            mensajeError += "Zonaje debe ser numerico. ";
        } else {
            codigoLabel.setBorder(null);
        }

        if (currentX == 0 && currentY == 0) {
            valid = false;
            mapLabel.setBorder(Application.BORDER_ERROR);
            JOptionPane.showMessageDialog(panel, "Debe seleccionar un lugar en el mapa","ERROR",JOptionPane.ERROR_MESSAGE);
        } else {
            mapLabel.setBorder(null);
        }

        if(concatenaciones == 4){
            JOptionPane.showMessageDialog(panel, "Todos los campos son requeridos","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(!mensajeError.equals("")){
            JOptionPane.showMessageDialog(panel, mensajeError,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
        return valid;
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

            currentSucursal = new JLabel();
            currentSucursal.setIcon(new ImageIcon(sucursalUnselectedImage));
            currentSucursal.setSize(30, 30);
            currentSucursal.setVisible(true);
            currentSucursal.setToolTipText("Sucursal");
            mapLabel.add(currentSucursal);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}