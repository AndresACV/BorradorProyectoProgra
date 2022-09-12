package sucursales.presentation.Empleado;

import sucursales.logic.Sucursal;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ModelEmpleado extends Observable {



//    public void creaEmpleado()


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
