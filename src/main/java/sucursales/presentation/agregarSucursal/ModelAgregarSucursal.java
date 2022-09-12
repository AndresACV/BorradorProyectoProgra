package sucursales.presentation.agregarSucursal;

import java.util.Observable;
import java.util.Observer;

public class ModelAgregarSucursal extends Observable {
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