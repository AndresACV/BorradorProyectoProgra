package sucursales.presentation.empleados;

import sucursales.presentation.agregarEmpleado.ViewAgregarEmpleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;


public class ViewEmpleados implements Observer {
    private JPanel panel;
    private JTextField nombreFld;
    private JButton buscarFld;
    private JButton agregarFld;
    private JTable empleadosFld;
    private JLabel nombreLbl;
    private JButton eliminarFld;
    private JButton reporteButton;

    private ViewAgregarEmpleado empleado;
    public ViewEmpleados() {
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerEmpleados.buscarEmpleado(nombreFld.getText());
            }
        });
        eliminarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerEmpleados.eliminarEmpleado(nombreFld.getText());
            }
        });
        agregarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empleado = new ViewAgregarEmpleado();
                empleado.showWindow();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    ControllerEmpleados controllerEmpleados;
    ModelEmpleados modelEmpleados;

    public void setController(ControllerEmpleados controllerEmpleados) {
        this.controllerEmpleados = controllerEmpleados;
    }

    public void setModel(ModelEmpleados modelEmpleados) {
        this.modelEmpleados = modelEmpleados;
        modelEmpleados.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {TableModelEmpleados.CEDULA, TableModelEmpleados.NOMBRE, TableModelEmpleados.TELEFONO, TableModelEmpleados.SALARIO_BASE, TableModelEmpleados.SUCURSAL, TableModelEmpleados.ZONAJE, TableModelEmpleados.SALARIO_TOTAL};
        empleadosFld.setModel(new TableModelEmpleados(cols, modelEmpleados.getEmpleados()));
        empleadosFld.setRowHeight(30);
        this.panel.revalidate();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        reporteButton = new JButton();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/IconoPDF.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        reporteButton.setIcon(imageIcon);
    }
}
