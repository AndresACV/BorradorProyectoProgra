package sucursales;

import sucursales.presentation.agregarEmpleado.ControllerAgregarEmpleado;
import sucursales.presentation.acerca_de.ControllerAcercaDe;
import sucursales.presentation.acerca_de.ModelAcercaDe;
import sucursales.presentation.acerca_de.ViewAcercaDe;
import sucursales.presentation.empleados.ControllerEmpleados;
import sucursales.presentation.empleados.ModelEmpleados;
import sucursales.presentation.empleados.ViewEmpleados;
import sucursales.presentation.main.Controller;
import sucursales.presentation.main.Model;
import sucursales.presentation.main.View;
import sucursales.presentation.sucursales.ControllerSucursales;
import sucursales.presentation.sucursales.ModelSucursales;
import sucursales.presentation.sucursales.ViewSucursales;
import javax.swing.*;

public class Application {

    public static Controller mainController;

    public static ControllerEmpleados controllerEmpleados;
    public static ControllerSucursales controllerSucursales;
    public static ControllerAcercaDe controllerAcercaDe;

    public static JFrame window;

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ignored) {};

        ModelEmpleados modelEmpleados = new ModelEmpleados();
        ViewEmpleados viewEmpleados = new ViewEmpleados();
        controllerEmpleados = new ControllerEmpleados(viewEmpleados, modelEmpleados);

        ModelSucursales modelSucursales = new ModelSucursales();
        ViewSucursales viewSucursales = new ViewSucursales();
        controllerSucursales = new ControllerSucursales(viewSucursales, modelSucursales);

        ModelAcercaDe modelAcercaDe = new ModelAcercaDe();
        ViewAcercaDe viewAcercaDe = new ViewAcercaDe();
        controllerAcercaDe = new ControllerAcercaDe(viewAcercaDe, modelAcercaDe);

        Model mainModel= new sucursales.presentation.main.Model();
        View mainView = new sucursales.presentation.main.View();
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
