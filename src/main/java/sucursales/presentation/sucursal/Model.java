package sucursales.presentation.sucursal;

import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    Sucursal current;
    int modo;

    public Model() {}

    public int getModo() {
        return modo;
    }
    public void setModo(int modo) {
        this.modo = modo;
    }
    public Sucursal getCurrent() {
        return current;
    }
    public void setCurrent(Sucursal current) {
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