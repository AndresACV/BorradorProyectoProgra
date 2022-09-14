package sucursales;

import sucursales.presentation.about.Controller;
import sucursales.presentation.about.Model;
import sucursales.presentation.about.View;
import javax.swing.*;

public class Application {

    public static sucursales.presentation.main.Controller mainController;

    public static sucursales.presentation.empleados.Controller controllerEmpleados;
    public static sucursales.presentation.sucursales.Controller controllerSucursales;
    public static Controller controllerAbout;

    public static JFrame window;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ignored) {};

        sucursales.presentation.empleados.Model modelEmpleados = new sucursales.presentation.empleados.Model();
        sucursales.presentation.empleados.View viewEmpleados = new sucursales.presentation.empleados.View();
        controllerEmpleados = new sucursales.presentation.empleados.Controller(viewEmpleados, modelEmpleados);

        sucursales.presentation.sucursales.Model modelSucursales = new sucursales.presentation.sucursales.Model();
        sucursales.presentation.sucursales.View viewSucursales = new sucursales.presentation.sucursales.View();
        controllerSucursales = new sucursales.presentation.sucursales.Controller(viewSucursales, modelSucursales);

        Model modelAcercaDe = new sucursales.presentation.about.Model();
        View viewAcercaDe = new sucursales.presentation.about.View();
        controllerAbout = new sucursales.presentation.about.Controller(viewAcercaDe, modelAcercaDe);

        sucursales.presentation.main.Model mainModel= new sucursales.presentation.main.Model();
        sucursales.presentation.main.View mainView = new sucursales.presentation.main.View();
        mainController = new sucursales.presentation.main.Controller(mainView, mainModel);

        mainView.getPanel().add("Empleados", viewEmpleados.getPanel());
        mainView.getPanel().add("Sucursales", viewSucursales.getPanel());
        mainView.getPanel().add("Acerca de..", viewAcercaDe.getPanel());

        window = new JFrame();
        window.setSize(400,300);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("SISE: Sistema de Sucursales y Empleados");
        window.setVisible(true);

        mainController.show();
    }
}