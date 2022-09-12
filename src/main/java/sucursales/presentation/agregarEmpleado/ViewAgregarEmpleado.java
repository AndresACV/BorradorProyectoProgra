package sucursales.presentation.agregarEmpleado;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ViewAgregarEmpleado implements Observer {

    private JPanel panel;
    private JTextField cedulaField;
    private JTextField nombreField;
    private JTextField telefonoField;
    private JTextField salarioField;
    private JTextField sucursalField;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JFrame window;

    ControllerAgregarEmpleado controllerempleado;
    ModelAgregarEmpleado modelAgregarEmpleado;

    public JPanel getPanel() {
        return panel;
    }

    public void showWindow(){
        window = new JFrame();
        window.setSize(400,400);
        window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        window.setTitle("Empleado");
        window.add(panel);
        window.setVisible(true);
    }

    public void setControllerempleado(ControllerAgregarEmpleado controllerEmpleado) { this.controllerempleado = controllerEmpleado; }
    public void setModelEmpleado(ModelAgregarEmpleado modelAgregarEmpleado) { this.modelAgregarEmpleado = modelAgregarEmpleado; }
    @Override
    public void update(Observable o, Object arg) {
        this.panel.revalidate();
    }
}
