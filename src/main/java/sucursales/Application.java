package sucursales;

import sucursales.presentation.acerca_de.ControllerAcercaDe;
import sucursales.presentation.acerca_de.ModelAcercaDe;
import sucursales.presentation.acerca_de.ViewAcercaDe;
import sucursales.presentation.empleados.ControllerEmpleados;
import sucursales.presentation.empleados.ModelEmpleados;
import sucursales.presentation.empleados.ViewEmpleados;
import sucursales.presentation.sucursales.ControllerSucursales;
import sucursales.presentation.sucursales.ModelSucursales;
import sucursales.presentation.sucursales.ViewSucursales;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};


        ModelEmpleados empleadosModelEmpleados = new ModelEmpleados();
        ViewEmpleados empleadosViewEmpleados = new ViewEmpleados();
        empleadosControllerEmpleados = new ControllerEmpleados(empleadosViewEmpleados, empleadosModelEmpleados);

        ModelSucursales sucursalesModelSucursales = new ModelSucursales();
        ViewSucursales sucursalesViewSucursales = new ViewSucursales();
        sucursalesControllerSucursales = new ControllerSucursales(sucursalesViewSucursales, sucursalesModelSucursales);

        ModelAcercaDe acerca_deModelAcercaDe = new ModelAcercaDe();
        ViewAcercaDe acerca_deViewAcercaDe = new ViewAcercaDe();
        acerca_deControllerAcercaDe = new ControllerAcercaDe(acerca_deViewAcercaDe, acerca_deModelAcercaDe);

        sucursales.presentation.main.Model mainModel= new sucursales.presentation.main.Model();
        sucursales.presentation.main.View mainView = new sucursales.presentation.main.View();
        mainController = new sucursales.presentation.main.Controller(mainView, mainModel);

        mainView.getPanel().add("Empleados", empleadosViewEmpleados.getPanel());
        mainView.getPanel().add("Sucursales", sucursalesViewSucursales.getPanel());
        mainView.getPanel().add("Acerca de..", acerca_deViewAcercaDe.getPanel());

        window = new JFrame();
        window.setSize(400,300);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("SISE: Sistema de Sucursales y Empleados");
        window.setVisible(true);
        mainController.show();
    }

    public static ControllerEmpleados empleadosControllerEmpleados;
    public static ControllerSucursales sucursalesControllerSucursales;

    public static ControllerAcercaDe acerca_deControllerAcercaDe;
    public static sucursales.presentation.main.Controller mainController;

    public static JFrame window;
}
