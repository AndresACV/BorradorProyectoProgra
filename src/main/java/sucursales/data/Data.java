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

        empleados.add(new Empleado("118800941", "Andres", "85185158", 450000, sucursales.get(0)));
        empleados.add(new Empleado("118800942", "Pedro", "85185159", 450000, sucursales.get(1)));
        empleados.add(new Empleado("118800943", "Maria", "85185160", 450000, sucursales.get(2)));
        empleados.add(new Empleado("118800944", "Jose", "85185161", 450000, sucursales.get(3)));
        empleados.add(new Empleado("118800945", "Ana", "85185162", 450000, sucursales.get(4)));


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

    public void agregarEmpleado(Empleado e){
        this.empleados.add(e);
    }
}
