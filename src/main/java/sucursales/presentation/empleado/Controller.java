package sucursales.presentation.empleado;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setControllerempleado(this);
        view.setModelEmpleado(model);
    }

    public void agregar(Empleado e){
        Service.instance().agregarEmpleado(e);
    }
    public void show(){
        Application.window.setContentPane(view.getPanel());
    }
}