package sucursales.logic;

import sucursales.data.Data;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {

    private static Service instancia;
    private final Data data;

    public static Service instance(){
        if (instancia == null){ instancia = new Service(); }
        return instancia;
    }

    private Service(){
        data = new Data();
    }

    public List<Empleado> empleadosSearch(String filtro){
        return data.getEmpleados().stream().filter(e->e.getNombre().contains(filtro)).collect(Collectors.toList());
    }
    public List<Sucursal> sucursalesSearch(String filtro){
        return data.getSucursales().stream().filter(e->e.getReferencia().contains(filtro)).collect(Collectors.toList());
    }

    public void agregarEmpleado(Empleado e){
        data.getEmpleados().add(e);
    }
    public void agregarSucursal(Sucursal s){ data.getSucursales().add(s); }

    public List<Empleado> eliminarEmpleado(String nombre){
        for (int i = 0; i < data.getEmpleados().size(); i++) {
            if(Objects.equals(data.getEmpleados().get(i).getNombre(), nombre)){
                data.getEmpleados().remove(i);
            }
        }
        return data.getEmpleados();
    }
    public List<Sucursal> eliminarSucursal(String referencia){
        for (int i = 0; i < data.getSucursales().size(); i++) {
            if(Objects.equals(data.getSucursales().get(i).getReferencia(), referencia)){
                data.getSucursales().remove(i);
            }
        }
        return data.getSucursales();
    }
}