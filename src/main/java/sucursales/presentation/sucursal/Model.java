package sucursales.presentation.sucursal;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {
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