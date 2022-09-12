package sucursales.presentation.Empleado;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ViewEmpleado implements Observer {
    private JPanel panel;
    private JTextField cedulaField;
    private JTextField nombreField;
    private JTextField telefonoField;
    private JTextField salarioField;
    private JTextField sucursalField;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JFrame window;
    ControllerEmpleado controllerempleado;
    ModelEmpleado modelEmpleado;

    public JPanel getPanel() {
        return panel;
    }
    public void showWindow(){
        window = new JFrame();
        window.setSize(400,400);
//        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        window.setTitle("Empleado");
        window.add(panel);
        window.setVisible(true);
    }

    public void setControllerempleado(ControllerEmpleado controllerempleado) {
        this.controllerempleado = controllerempleado;
    }

    public void setModelEmpleado(ModelEmpleado modelEmpleado) {
        this.modelEmpleado = modelEmpleado;
    }

    @Override
    public void update(Observable o, Object arg) {
        this.panel.revalidate();
    }
}
