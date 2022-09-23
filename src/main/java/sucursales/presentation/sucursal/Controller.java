package sucursales.presentation.sucursal;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;

import javax.swing.*;
import java.awt.*;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        model.setCurrent(new Sucursal());
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Sucursal());
        model.commit();
        this.show();
    }

    JDialog dialog;

    public void show(){
        dialog = new JDialog(Application.window,"Sucursal", true);
        dialog.setSize(800,800);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
//        Point location = Application.window.getLocation();
//        dialog.setLocation( location.x+400,location.y+100);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void hide(){
        dialog.dispose();
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        view.clean();
    }

    public void guardar(Sucursal e) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().agregarSucursal(e);
                model.setCurrent(new Sucursal());
                break;
            case Application.MODO_EDITAR:
                Service.instance().sucursalUpdate(e);
                model.setCurrent(e);
                break;
        }
        Application.controllerSucursales.buscar("");
        model.commit();
    }

    public void editar(Sucursal e){
        model.setModo(Application.MODO_EDITAR);
        model.setCurrent(e);
        model.commit();
        this.show();
    }
}