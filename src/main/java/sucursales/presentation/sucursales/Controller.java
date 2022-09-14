package sucursales.presentation.sucursales;

import sucursales.Application;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;

import java.util.List;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        model.setSucursales(Service.instance().sucursalesSearch(""));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void buscarSucursal(String filtro){
        List<Sucursal> rows = Service.instance().sucursalesSearch(filtro);
        model.setSucursales(rows);
        model.commit();
    }
    public void eliminarSucursal(String referencia){
        List<Sucursal> rows = Service.instance().eliminarSucursal(referencia);
       model.setSucursales(rows); // Elimina el objeto y retorna la lista
        this.buscarSucursal("");               // Itera la lista
        model.commit();
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }
}