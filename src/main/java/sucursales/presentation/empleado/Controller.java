package sucursales.presentation.empleado;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;

import javax.swing.*;
import java.awt.*;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setControllerempleado(this);
        view.setModelEmpleado(model);
    }
    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Empleado());
        model.commit();
        this.show();
    }

    JDialog dialog;

    public void show(){
        dialog = new JDialog(Application.window,"Empleado", true);
        dialog.setSize(300,200);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        Point location = Application.window.getLocation();
        dialog.setLocation( location.x+400,location.y+100);
        dialog.setVisible(true);
    }

    public void hide(){
        dialog.dispose();
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

    public void editar(Empleado e){
        model.setModo(Application.MODO_EDITAR);
        model.setCurrent(e);
        model.commit();
        this.show();
    }
}