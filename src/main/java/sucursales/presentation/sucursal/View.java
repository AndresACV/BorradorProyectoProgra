package sucursales.presentation.sucursal;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel panel;
    private JFrame window;

    Controller controller;
    Model model;

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
    public void setControllerAgregarSucursal(Controller controller) { this.controller = controller; }

    public void setModelAgregarSucursal(Model model) { this.model = model; }
    @Override
    public void update(Observable o, Object arg) {
        this.panel.revalidate();
    }
}