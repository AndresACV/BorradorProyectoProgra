package sucursales.presentation.empleado;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;

import javax.swing.*;
import java.awt.*;

public class Controller {

    View view;
    Model model;
    JDialog dialog;

    public Controller(View view, Model model) throws Exception {
        Sucursal e = new Sucursal();
        e.setReferencia("");
        model.setSucursales(Service.instance().sucursalesSearch(e));
        model.setCurrent(new Empleado());
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void preAgregar() throws Exception {
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Empleado());
        model.commit();
        this.show();
    }

    public void show() throws Exception {
        Sucursal e = new Sucursal();
        e.setReferencia("");
        model.setSucursales(Service.instance().sucursalesSearch(e));
        dialog = new JDialog(Application.window,"Empleado", true);
        dialog.setSize(700,470);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    public void hide(){
        dialog.dispose();
       dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
       view.clean();
    }

    public void guardar(Empleado e) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().agregarEmpleado(e);
                model.setCurrent(new Empleado());
                break;
            case Application.MODO_EDITAR:
                Service.instance().empleadoUpdate(e);
                model.setCurrent(e);
                break;
        }
        Application.controllerEmpleados.buscar("");
        model.commit();
    }

    public void editar(Empleado e) throws Exception {
        model.setModo(Application.MODO_EDITAR);
        model.setCurrent(e);
        model.commit();
        this.show();
    }
}