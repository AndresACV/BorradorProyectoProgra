package sucursales.presentation.empleados;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;

import java.util.List;

public class ControllerEmpleados {
    ViewEmpleados viewEmpleados;
    ModelEmpleados modelEmpleados;

    public ControllerEmpleados(ViewEmpleados viewEmpleados, ModelEmpleados modelEmpleados) {
        modelEmpleados.setEmpleados(Service.instance().empleadosSearch(""));
        this.viewEmpleados = viewEmpleados;
        this.modelEmpleados = modelEmpleados;
        viewEmpleados.setController(this);
        viewEmpleados.setModel(modelEmpleados);
    }

    public void buscar(String filtro){
        List<Empleado> rows = Service.instance().empleadosSearch(filtro);
        modelEmpleados.setEmpleados(rows);
        modelEmpleados.commit();
    }

    public void show(){
        Application.window.setContentPane(viewEmpleados.getPanel());
    }
}
