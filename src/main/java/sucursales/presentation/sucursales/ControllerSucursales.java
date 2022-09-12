package sucursales.presentation.sucursales;

import sucursales.Application;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;

import java.util.List;

public class ControllerSucursales {

    ViewSucursales viewSucursales;
    ModelSucursales modelSucursales;

    public ControllerSucursales(ViewSucursales viewSucursales, ModelSucursales modelSucursales) {
        modelSucursales.setSucursales(Service.instance().sucursalesSearch(""));
        this.viewSucursales = viewSucursales;
        this.modelSucursales = modelSucursales;
        viewSucursales.setController(this);
        viewSucursales.setModel(modelSucursales);
    }

    public void buscarSucursal(String filtro){
        List<Sucursal> rows = Service.instance().sucursalesSearch(filtro);
        modelSucursales.setSucursales(rows);
        modelSucursales.commit();
    }
    public void eliminarSucursal(String referencia){
        List<Sucursal> rows = Service.instance().eliminarSucursal(referencia);
       modelSucursales.setSucursales(rows); // Elimina el objeto y retorna la lista
        this.buscarSucursal("");               // Itera la lista
        modelSucursales.commit();
    }

    public void show(){
        Application.window.setContentPane(viewSucursales.getPanel());
    }
}



