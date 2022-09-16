package sucursales.data;

import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Data {
    private List<Empleado> empleados;
    private List<Sucursal> sucursales;

    public List<Empleado> getEmpleados() { return empleados; }
    public List<Sucursal> getSucursales() { return sucursales; }

    public void setEmpleados(List<Empleado> empleados) { this.empleados = empleados; }
    public void setSucursales(List<Sucursal> sucursales) { this.sucursales = sucursales; }

    public Data() {
        empleados = new ArrayList<>();
        sucursales = new ArrayList<>();

        sucursales.add(new Sucursal("001", "Liberia", "Guanacaste, Liberia, 250 S Iglesia", 2.0));
        sucursales.add(new Sucursal("002", "Sabana", "San Jose, 100 O Teletica", 1.0));
        sucursales.add(new Sucursal("003", "Golfito", "Puntarenas, Golfito, barrio Huston...", 4.0));
        sucursales.add(new Sucursal("004", "Tortugero", "Limon, Tortuguero, frente Iglesia", 4.0));
        sucursales.add(new Sucursal("005", "Cahuita", "Limon, Cahuita, Playa Negra", 4.0));

        empleados.add(new Empleado("111", "Franklin Chang", "78972356", 7500,new Sucursal("003", "liberia", "Guanacaste, Liberia, 250 S Iglesia", 5.0)));
        empleados.add(new Empleado("222", "Sandra Cauffman", "54986721", 8200,new Sucursal("005", "Sabana", "Guanacaste, Liberia, 250 S Iglesia", 7.0)));
        empleados.add(new Empleado("333", "Ivan Vargas", "45233246", 7800,new Sucursal("008", "Golfito", "Guanacaste, Liberia, 250 S Iglesia", 8.0)));
    }
}