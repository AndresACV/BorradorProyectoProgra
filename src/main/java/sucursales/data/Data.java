package sucursales.data;

import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
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

//        sucursales.add(new Sucursal("001", "Liberia", "Guanacaste, Liberia, 250 S Iglesia", 2.0, 70, 80));
//        sucursales.add(new Sucursal("002", "Sabana", "San Jose, 100 O Teletica", 1.0, 100, 200));
//        sucursales.add(new Sucursal("003", "Golfito", "Puntarenas, Golfito, barrio Huston...", 4.0, 150, 300));
//        sucursales.add(new Sucursal("004", "Tortugero", "Limon, Tortuguero, frente Iglesia", 4.0, 200, 300));
//        sucursales.add(new Sucursal("005", "Cahuita", "Limon, Cahuita, Playa Negra", 4.0));
//
//        empleados.add(new Empleado("111", "Franklin Chang", "78972356", 7500, sucursales.get(0)));
//        empleados.add(new Empleado("222", "Sandra Cauffman", "54986721",7500, sucursales.get(1)));
//        empleados.add(new Empleado("333", "Ivan Vargas", "45233246", 7800, sucursales.get(2)));
    }

}