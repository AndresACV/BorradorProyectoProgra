package sucursales.presentation.acerca_de;

import sucursales.Application;
import sucursales.presentation.empleados.ControllerEmpleados;

public class ControllerAcercaDe {

    ViewAcercaDe viewAcercaDe;
    ModelAcercaDe modelAcercaDe;

    public ControllerAcercaDe(ViewAcercaDe viewAcercaDe, ModelAcercaDe modelAcercaDe) {
        this.viewAcercaDe = viewAcercaDe;
        this.modelAcercaDe = modelAcercaDe;
        viewAcercaDe.setController(this);
        viewAcercaDe.setModel(modelAcercaDe);
    }

    public void show(){
        Application.window.setContentPane(viewAcercaDe.getPanel());
    }
}