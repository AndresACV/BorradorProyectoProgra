package sucursales.logic;

import sucursales.data.Data;

import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private static Service theInstance;

    public static Service instance(){
        if (theInstance == null){
            theInstance = new Service();
        }
        return theInstance;
    }

    private final Data data;

    private Service(){
        data = new Data();
    }

    public List<Empleado> empleadosSearch(String filtro){
        return data.getEmpleados().stream().filter(e->e.getNombre().contains(filtro)).collect(Collectors.toList());
    }

    public List<Sucursal> sucursalesSearch(String filtro){
        return data.getSucursales().stream().filter(e->e.getReferencia().contains(filtro)).collect(Collectors.toList());
    }
    public List<Sucursal> eliminarSucursal(String referencia){
        return data.eliminarSucursal(referencia);
    }
 }
