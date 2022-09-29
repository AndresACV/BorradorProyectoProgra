package sucursales.presentation.sucursal;

import sucursales.Application;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;
import javax.swing.*;

public class Controller {

    View view;
    Model model;
    JDialog dialog;

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

    public void show(){
        dialog = new JDialog(Application.window,"Sucursal", true);
        dialog.setSize(700,450);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    public void hide(){
        dialog.dispose();
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        view.cleanBorders();
    }

    public void guardar(Sucursal e) throws Exception {
        if(e != null) {
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
        }
        else{
            throw new Exception("Error");
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