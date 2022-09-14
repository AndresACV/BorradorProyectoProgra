package sucursales.presentation.empleados;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;

import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.setEmpleados(Service.instance().empleadosSearch(""));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void buscarEmpleado(String filtro){
        List<Empleado> rows = Service.instance().empleadosSearch(filtro);
        model.setEmpleados(rows);
        model.commit();
    }

    public void eliminarEmpleado(String nombre){
        List<Empleado> rows = Service.instance().eliminarEmpleado(nombre);
        model.setEmpleados(rows);
        this.buscarEmpleado("");
        model.commit();
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }
}