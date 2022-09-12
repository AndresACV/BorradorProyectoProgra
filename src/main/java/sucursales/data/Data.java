package sucursales.data;

import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Data {
    private List<Empleado> empleados;
    private List<Sucursal> sucursales;

    public Data() {
        empleados = new ArrayList<>();
        sucursales = new ArrayList<>();

        sucursales.add(new Sucursal("001", "San Jose", "500mts sur del Banco Nacional", 1.0));
        sucursales.add(new Sucursal("002", "Alajuela", "100mts norte del Banco Nacional", 1.0));
        sucursales.add(new Sucursal("003", "Cartago", "200mts sur del Banco Nacional", 1.0));
        sucursales.add(new Sucursal("004", "Heredia", "300mts norte del Banco Nacional", 1.0));
        sucursales.add(new Sucursal("005", "Limon", "400mts sur del Banco Nacional", 1.0));

        empleados.add(new Empleado("118809", "Juan", "2356", 213412, sucursales.get(0)));
        empleados.add(new Empleado("118810", "Pedro", "2357", 213413, sucursales.get(1)));
        empleados.add(new Empleado("118811", "Maria", "2358", 213414, sucursales.get(2)));
        empleados.add(new Empleado("118812", "Jose", "2359", 213415, sucursales.get(3)));
        empleados.add(new Empleado("118813", "Ana", "2360", 213416, sucursales.get(4)));
    }
    public List<Sucursal> eliminarSucursal(String referencia){
        for(Sucursal i: sucursales){ // For each
            if(Objects.equals(i.getReferencia(), referencia)){
                sucursales.remove(i);
            }
        }
        return sucursales;
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
}
