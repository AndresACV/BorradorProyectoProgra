package sucursales.presentation.empleado;

import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    List<Sucursal> sucursales;
    Empleado current;
    int modo;

    public Model() {
        this.setSucursales(new ArrayList<Sucursal>());
    }

    public void setSucursales(List<Sucursal> sucursales){
        this.sucursales = sucursales;
    }
    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public int getModo() {
        return modo;
    }
    public void setModo(int modo) {
        this.modo = modo;
    }

    public Empleado getCurrent() {
        return current;
    }
    public void setCurrent(Empleado current) {
        this.current = current;
    }


    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }
    public void commit(){
        setChanged();
        notifyObservers(null);
    }
}
