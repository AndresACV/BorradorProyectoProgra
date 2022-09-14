package sucursales.presentation.empleado;

import sucursales.logic.Empleado;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    Empleado current;
    int modo;

    public Model() {}

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
