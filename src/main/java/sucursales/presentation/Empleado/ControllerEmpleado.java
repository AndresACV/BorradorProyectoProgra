package sucursales.presentation.Empleado;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;
import sucursales.presentation.sucursales.ModelSucursales;
import sucursales.presentation.sucursales.ViewSucursales;

import java.util.List;

public class ControllerEmpleado {
    ViewEmpleado viewEmpleado;
    ModelEmpleado modelEmpleado;


    public ControllerEmpleado(ViewEmpleado viewEmpleado, ModelEmpleado modelEmpleado) {
        this.viewEmpleado = viewEmpleado;
        this.modelEmpleado = modelEmpleado;
        viewEmpleado.setControllerempleado(this);
        viewEmpleado.setModelEmpleado(modelEmpleado);
    }
    public void agregar(Empleado e){
        Service.instance().agregarEmpleado(e);
    }
    public void show(){
        Application.window.setContentPane(viewEmpleado.getPanel());
    }
}
