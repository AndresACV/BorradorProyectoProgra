package sucursales.presentation.agregarEmpleado;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;

public class ControllerAgregarEmpleado {

    ViewAgregarEmpleado viewAgregarEmpleado;
    ModelAgregarEmpleado modelAgregarEmpleado;

    public ControllerAgregarEmpleado(ViewAgregarEmpleado viewAgregarEmpleado, ModelAgregarEmpleado modelAgregarEmpleado) {
        this.viewAgregarEmpleado = viewAgregarEmpleado;
        this.modelAgregarEmpleado = modelAgregarEmpleado;
        viewAgregarEmpleado.setControllerempleado(this);
        viewAgregarEmpleado.setModelEmpleado(modelAgregarEmpleado);
    }

    public void agregar(Empleado e){
        Service.instance().agregarEmpleado(e);
    }
    public void show(){
        Application.window.setContentPane(viewAgregarEmpleado.getPanel());
    }
}