package sucursales.data;

import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Empleado> empleados;
    private List<Sucursal> sucursales;

    public Data() {
        empleados = new ArrayList<>();
        sucursales = new ArrayList<>();

        empleados.add(new Empleado("111", "Franklin Chang"));
        empleados.add(new Empleado("222", "Sandra Cauffman"));
        empleados.add(new Empleado("333", "Ivan Vargas"));

        sucursales.add(new Sucursal("001", "Liberia", "Guanacaste, Liberia, 250 S Iglesia", 2.0));
        sucursales.add(new Sucursal("002", "Sabana", "San Jose, 100 O Teletica", 1.0));
        sucursales.add(new Sucursal("003", "Golfito", "Puntarenas, Golfito, Barrio Huston", 4.0));
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
};
