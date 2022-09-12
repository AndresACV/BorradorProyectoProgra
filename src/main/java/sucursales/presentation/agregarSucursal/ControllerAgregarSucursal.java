package sucursales.presentation.agregarSucursal;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;
import sucursales.logic.Sucursal;

public class ControllerAgregarSucursal {

    ViewAgregarSucursal viewAgregarSucursal;
    ModelAgregarSucursal modelAgregarSucursal;

    public ControllerAgregarSucursal(ViewAgregarSucursal viewAgregarSucursal, ModelAgregarSucursal modelAgregarSucursal) {
        this.viewAgregarSucursal = viewAgregarSucursal;
        this.modelAgregarSucursal = modelAgregarSucursal;
        viewAgregarSucursal.setControllerAgregarSucursal(this);
        viewAgregarSucursal.setModelAgregarSucursal(modelAgregarSucursal);
    }
    public void agregar(Sucursal s){
        Service.instance().agregarSucursal(s);
    }
    public void show(){
        Application.window.setContentPane(viewAgregarSucursal.getPanel());
    }

}
