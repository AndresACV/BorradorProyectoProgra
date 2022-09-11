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

    public void buscar(String filtro){
        List<Sucursal> rows = Service.instance().sucursalesSearch(filtro);
        modelSucursales.setSucursales(rows);
        modelSucursales.commit();
    }

public void show(){
        Application.window.setContentPane(viewSucursales.getPanel());
    }
}



