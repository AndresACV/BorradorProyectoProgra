package sucursales;

import sucursales.presentation.about.Controller;
import sucursales.presentation.about.Model;
import sucursales.presentation.about.View;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Application {

    public static sucursales.presentation.main.Controller mainController;

    public static sucursales.presentation.empleados.Controller controllerEmpleados;
    public static sucursales.presentation.sucursales.Controller controllerSucursales;
    public static Controller controllerAbout;

    public static sucursales.presentation.empleado.Controller controllerEmpleado;
    public static sucursales.presentation.sucursal.Controller controllerSucursal;

    public static JFrame window;

    public static  final int  MODO_AGREGAR=0;
    public static  final int   MODO_EDITAR=1;

    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);

    public static void main(String[] args) throws Exception {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ignored) {};

        sucursales.presentation.empleados.Model modelEmpleados = new sucursales.presentation.empleados.Model();
        sucursales.presentation.empleados.View viewEmpleados = new sucursales.presentation.empleados.View();
        controllerEmpleados = new sucursales.presentation.empleados.Controller(viewEmpleados, modelEmpleados);

        sucursales.presentation.empleado.Model modelEmpleado = new sucursales.presentation.empleado.Model();
        sucursales.presentation.empleado.View viewEmpleado = new sucursales.presentation.empleado.View();
        controllerEmpleado = new sucursales.presentation.empleado.Controller(viewEmpleado, modelEmpleado);

        sucursales.presentation.sucursales.Model modelSucursales = new sucursales.presentation.sucursales.Model();
        sucursales.presentation.sucursales.View viewSucursales = new sucursales.presentation.sucursales.View();
        controllerSucursales = new sucursales.presentation.sucursales.Controller(viewSucursales, modelSucursales);

        sucursales.presentation.sucursal.Model modelSucursal = new sucursales.presentation.sucursal.Model();
        sucursales.presentation.sucursal.View viewSucursal = new sucursales.presentation.sucursal.View();
        controllerSucursal = new sucursales.presentation.sucursal.Controller(viewSucursal, modelSucursal);

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
        window.setSize(900,600);
        window.setExtendedState(JFrame.NORMAL);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("SECR: Sucursales y Empleados Costa Rica");
        window.setVisible(true);
        mainController.show();
    }
}