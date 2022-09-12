package sucursales.presentation.agregarEmpleado;

import java.util.Observable;
import java.util.Observer;

public class ModelAgregarEmpleado extends Observable {

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
