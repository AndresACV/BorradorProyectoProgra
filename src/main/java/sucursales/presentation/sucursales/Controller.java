package sucursales.presentation.sucursales;

import sucursales.Application;
import sucursales.logic.Empleado;
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

    public void buscar(String filtro){
        List<Sucursal> rows = Service.instance().sucursalesSearch(filtro);
        model.setSucursales(rows);
        model.commit();
    }

    public void eliminar(String codigo) throws Exception {
        List<Sucursal> rows = Service.instance().eliminarSucursal(codigo);
        model.setSucursales(rows);
        this.buscar("");
        model.commit();
    }

    public void preAgregar(){
        Application.controllerSucursal.preAgregar();
    }

    public void editar(int row){
        String codigo = model.getSucursales().get(row).getCodigo();
        Sucursal e=null;
        try {
            e= Service.instance().sucursalGet(codigo);
            Application.controllerSucursal.editar(e);
        } catch (Exception ex) {}
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }
}