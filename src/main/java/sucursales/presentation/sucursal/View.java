package sucursales.presentation.sucursal;

import sucursales.Application;
import sucursales.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
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
    private JTextField codigoFld;
    private JTextField referenciaFld;

    private JButton guardarFld;
    private JButton cancelarFld;

    private JLabel codigoLbl;
    private JLabel referenciaLbl;
    private JLabel direccionLbl;
    private JLabel zonajeLbl;
    private JTextField direccionFld;
    private JTextField zonajeFld;
    private JLabel mapaLabel;
    private Image mapa;

    private JFrame window;

    public View() {
        guardarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clean();
                if (validate()) {
                    Sucursal n = take();
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

    sucursales.presentation.sucursal.Controller controller;
    sucursales.presentation.sucursal.Model model;

    public void setController(sucursales.presentation.sucursal.Controller controller) {
        this.controller = controller;
    }

    public void setModel(sucursales.presentation.sucursal.Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        Sucursal current = model.getCurrent();
        if(current != null)      {
            this.codigoFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
            this.codigoFld.setText(current.getCodigo());
            referenciaFld.setText(current.getReferencia());
            direccionFld.setText(current.getDireccion());
            zonajeFld.setText("");
            this.panel.validate();
        }
    }

    public Sucursal take() {
        Sucursal s = new Sucursal();
        s.setCodigo(codigoFld.getText());
        s.setReferencia(referenciaFld.getText());
        s.setDireccion(direccionFld.getText());
        s.setPorcentajeZonaje(Double.parseDouble(zonajeFld.getText()));
        return s;
    }
    public void clean(){
        codigoLbl.setBorder(new EmptyBorder(0, 0, 2, 0));
        referenciaLbl.setBorder(new EmptyBorder(0, 0, 2, 0));
        direccionLbl.setBorder(new EmptyBorder(0, 0, 2, 0));
        zonajeLbl.setBorder(new EmptyBorder(0, 0, 2, 0));
    }
    private boolean validate() {
        boolean valid = true;
        if (codigoFld.getText().isEmpty()) {
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
            //JOptionPane.showMessageDialog(panel, "Codigo requerido","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(!codigoFld.getText().matches("[0-9]+")){
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
           // JOptionPane.showMessageDialog(panel, "Codigo debe ser numerico","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (referenciaFld.getText().length() == 0) {
            valid = false;
            referenciaLbl.setBorder(Application.BORDER_ERROR);
            //JOptionPane.showMessageDialog(panel, "Referencia requerida","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(!referenciaFld.getText().matches("^[a-zA-Z]+$")){
            valid = false;
            referenciaLbl.setBorder(Application.BORDER_ERROR);
           // JOptionPane.showMessageDialog(panel, "La referencia no puede ser numerica","ERROR",JOptionPane.ERROR_MESSAGE);
        } else {
            referenciaFld.setBorder(null);
            referenciaFld.setToolTipText(null);
        }
        if (direccionFld.getText().isEmpty()) {
            valid = false;
            direccionLbl.setBorder(Application.BORDER_ERROR);
            //JOptionPane.showMessageDialog(panel, "Direccion requerida","ERROR",JOptionPane.ERROR_MESSAGE);
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (zonajeFld.getText().isEmpty()) {
            valid = false;
            zonajeLbl.setBorder(Application.BORDER_ERROR);
            //JOptionPane.showMessageDialog(panel, "Zonaje requerido","ERROR",JOptionPane.ERROR_MESSAGE);
        } else if(!zonajeFld.getText().matches("^[0-9]+\\.?[0-9]*$")) {
            valid = false;
            zonajeLbl.setBorder(Application.BORDER_ERROR);
            //JOptionPane.showMessageDialog(panel, "Zonaje debe ser numerico","ERROR",JOptionPane.ERROR_MESSAGE);
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }
        if(codigoFld.getText().isEmpty() && referenciaFld.getText().isEmpty() && direccionFld.getText().isEmpty() && zonajeFld.getText().isEmpty()){
            JOptionPane.showMessageDialog(panel, "Todos los espacios estan vacios !!! ","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if(referenciaFld.getText().isEmpty() && direccionFld.getText().isEmpty() && zonajeFld.getText().isEmpty()){
            JOptionPane.showMessageDialog(panel, "Falto la referencia, la direccion y el zonaje !!! ","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if( direccionFld.getText().isEmpty() && zonajeFld.getText().isEmpty()){
            JOptionPane.showMessageDialog(panel, "Falto la direccion y el zonaje !!! ","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        else if( zonajeFld.getText().isEmpty()){
            JOptionPane.showMessageDialog(panel, "Falto el zonaje !!! ","ERROR",JOptionPane.ERROR_MESSAGE);
        }


        return valid;
    }

    private void createUIComponents() throws IOException {
        mapaLabel = new JLabel();
        mapa = ImageIO.read(new File("src/main/resources/MapCR.png"));
        mapa = mapa.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        BufferedImage result = new BufferedImage(400,400, BufferedImage.TYPE_INT_ARGB);
        Graphics g = result.getGraphics();
        g.drawImage(mapa, 10, 10, mapaLabel);
        mapaLabel.setIcon(new ImageIcon(result));
    }
}