package sucursales.presentation.agregarSucursal;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class ViewAgregarSucursal implements Observer {

    private JPanel panel;
    private JFrame window;

    ControllerAgregarSucursal controllerAgregarSucursal;
    ModelAgregarSucursal modelAgregarSucursal;

    public JPanel getPanel() {
        return panel;
    }

    public void showWindow(){
        window = new JFrame();
        window.setSize(400,400);
        window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        window.setTitle("Sucursal");
        window.add(panel);
        window.setVisible(true);
    }
    public void setControllerAgregarSucursal(ControllerAgregarSucursal controllerAgregarSucursal) { this.controllerAgregarSucursal = controllerAgregarSucursal; }

    public void setModelAgregarSucursal(ModelAgregarSucursal modelAgregarSucursal) { this.modelAgregarSucursal = modelAgregarSucursal; }
    @Override
    public void update(Observable o, Object arg) {
        this.panel.revalidate();
    }
}