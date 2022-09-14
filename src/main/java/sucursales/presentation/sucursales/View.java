package sucursales.presentation.sucursales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel panel;
    private JTextField direccionFld;
    private JButton buscarFld;
    private JLabel direccionLbl;
    private JTable sucursalesFld;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JLabel imagenLbl;
    private JButton reporteButton;
    private sucursales.presentation.sucursal.View sucursal;

    Controller controller;
    Model model;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscarSucursal(direccionFld.getText());
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.eliminarSucursal(direccionFld.getText());
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sucursal = new sucursales.presentation.sucursal.View();
                sucursal.showWindow();
            }
        });
    }

    public void setController(Controller controller) { this.controller = controller; }
    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {TableModel.CODIGO, TableModel.REFERENCIA, TableModel.DIRECCION, TableModel.ZONAJE};
        sucursalesFld.setModel(new TableModel(cols, model.getSucursales()));
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