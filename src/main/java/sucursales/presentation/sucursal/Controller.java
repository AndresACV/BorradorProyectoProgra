package sucursales.presentation.sucursal;

import sucursales.Application;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setControllerAgregarSucursal(this);
        view.setModelAgregarSucursal(model);
    }
    public void agregar(Sucursal s){
        Service.instance().agregarSucursal(s);
    }
    public void show(){
        Application.window.setContentPane(view.getPanel());
    }
}