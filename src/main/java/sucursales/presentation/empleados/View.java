package sucursales.presentation.empleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class View implements Observer {

    private JPanel panel;
    private JTextField nombreFld;
    private JButton buscarFld;
    private JButton agregarFld;
    private JTable empleadosFld;
    private JLabel nombreLbl;
    private JButton eliminarFld;
    private JButton reporteButton;
    private JLabel imagenLbl;
    private sucursales.presentation.empleado.View empleado;

    Controller controller;
    Model model;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscarEmpleado(nombreFld.getText());
            }
        });
        eliminarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.eliminarEmpleado(nombreFld.getText());
            }
        });
        agregarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empleado = new sucursales.presentation.empleado.View();
                empleado.showWindow();
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
        int[] cols = {TableModel.CEDULA, TableModel.NOMBRE, TableModel.TELEFONO, TableModel.SALARIO_BASE, TableModel.SUCURSAL, TableModel.ZONAJE, TableModel.SALARIO_TOTAL};
        empleadosFld.setModel(new TableModel(cols, model.getEmpleados()));
        empleadosFld.setRowHeight(30);
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