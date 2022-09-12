package sucursales.presentation.sucursales;

import sucursales.presentation.agregarSucursal.ViewAgregarSucursal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class ViewSucursales implements Observer {

    private JPanel panel;
    private JTextField direccionFld;
    private JButton buscarFld;
    private JLabel direccionLbl;
    private JTable sucursalesFld;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JLabel imagenLbl;
    private JButton reporteButton;
    private ViewAgregarSucursal sucursal;

    ControllerSucursales controllerSucursales;
    ModelSucursales modelSucursales;

    public JPanel getPanel() {
        return panel;
    }

    public ViewSucursales() {
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerSucursales.buscarSucursal(direccionFld.getText());
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerSucursales.eliminarSucursal(direccionFld.getText());
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sucursal = new ViewAgregarSucursal();
                sucursal.showWindow();
            }
        });
    }

    public void setController(ControllerSucursales controllerSucursales) { this.controllerSucursales = controllerSucursales; }
    public void setModel(ModelSucursales modelSucursales) {
        this.modelSucursales = modelSucursales;
        modelSucursales.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {TableModelSucursales.CODIGO, TableModelSucursales.REFERENCIA, TableModelSucursales.DIRECCION, TableModelSucursales.ZONAJE};
        sucursalesFld.setModel(new TableModelSucursales(cols, modelSucursales.getSucursales()));
        sucursalesFld.setRowHeight(30);
        this.panel.revalidate();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        reporteButton = new JButton();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/IconPDF.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        reporteButton.setIcon(imageIcon);

        imagenLbl = new JLabel();
        ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("src/main/resources/MapCR.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
        imagenLbl.setIcon(imageIcon2);
    }
}