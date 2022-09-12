package sucursales.presentation.acerca_de;

import sucursales.presentation.empleados.ControllerEmpleados;
import sucursales.presentation.empleados.ModelEmpleados;
import sucursales.presentation.empleados.TableModelEmpleados;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ViewAcercaDe implements Observer {

    private JPanel panel;
    private JLabel tituloLbl;
    private JLabel datosLbl;
    private JLabel imagenLbl;

    JPanel ImagePanel;
    private ImageIcon image;
    public JPanel getPanel() {
        return panel;
    }

    ControllerAcercaDe controllerAcercaDe;
    ModelAcercaDe modelAcercaDe;

    public void setController(ControllerAcercaDe controllerAcercaDe) {
        this.controllerAcercaDe = controllerAcercaDe;
    }
    public void setModel(ModelAcercaDe modelAcercaDe) {
        this.modelAcercaDe = modelAcercaDe;
        modelAcercaDe.addObserver(this);
    }
    @Override
    public void update(Observable updatedModel, Object parametros) {
        this.panel.revalidate();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        imagenLbl = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/CompanyLogo.png").getImage().getScaledInstance(800, 400, Image.SCALE_DEFAULT));
        imagenLbl.setIcon(imageIcon);
    }
}