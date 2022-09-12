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

    public void buscarEmpleado(String filtro){
        List<Empleado> rows = Service.instance().empleadosSearch(filtro);
        modelEmpleados.setEmpleados(rows);
        modelEmpleados.commit();
    }

    public void eliminarEmpleado(String nombre){
        List<Empleado> rows = Service.instance().eliminarEmpleado(nombre);
        modelEmpleados.setEmpleados(rows); // Elimina el objeto y retorna la lista
        this.buscarEmpleado("");               // Itera la lista
        modelEmpleados.commit();
    }

    public void show(){
        Application.window.setContentPane(viewEmpleados.getPanel());
    }
}
