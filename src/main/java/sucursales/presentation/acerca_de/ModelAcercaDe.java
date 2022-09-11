package sucursales.presentation.acerca_de;

import java.util.Observer;

public class ModelAcercaDe extends java.util.Observable {

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
