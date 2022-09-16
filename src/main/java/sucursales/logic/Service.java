package sucursales.logic;

import sucursales.data.Data;
import sucursales.data.XmlPersister;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {

    private static Service instancia;
    private Data data;

    public static Service instance() {
        if (instancia == null) {
            instancia = new Service();
        }
        return instancia;
    }

    private Service(){
        try{
            data= XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }
    }

    public List<Empleado> empleadosSearch(String filtro) {
        return data.getEmpleados().stream()
                .filter(e -> e.getNombre().contains(filtro))
                .sorted(Comparator.comparing(Empleado::getCedula))
                .collect(Collectors.toList());
    }

    public List<Sucursal> sucursalesSearch(String filtro) {
        return data.getSucursales().stream()
                .filter(e -> e.getReferencia().contains(filtro))
                .sorted(Comparator.comparing(Sucursal::getReferencia))
                .collect(Collectors.toList());
    }

    public Empleado empleadoGet(String cedula) throws Exception {
        Empleado result = data.getEmpleados().stream().filter(e -> e.getCedula().equals(cedula)).findFirst().orElse(null);
        if (result != null) return result;
        else throw new Exception("Empleado no existe");
    }

    public Sucursal sucursalGet(String referencia) throws Exception {
        Sucursal result = data.getSucursales().stream().filter(e -> e.getReferencia().equals(referencia)).findFirst().orElse(null);
        if (result != null) return result;
        else throw new Exception("Sucursal no existe");
    }

    public void agregarEmpleado(Empleado empleado) throws Exception {
        Empleado result = data.getEmpleados().stream().filter(e -> e.getCedula().equals(empleado.getCedula())).findFirst().orElse(null);
        if (result == null) data.getEmpleados().add(empleado);
        else throw new Exception("Empleado ya existe");
    }

    public void agregarSucursal(Sucursal sucursal) throws Exception {
        Sucursal result = data.getSucursales().stream().filter(e -> e.getCodigo().equals(sucursal.getCodigo())).findFirst().orElse(null);
        if (result == null) data.getSucursales().add(sucursal);
        else throw new Exception("Sucursal ya existe");
    }

    public void empleadoUpdate(Empleado empleado) throws Exception {
        Empleado result;
        try {
            result = this.empleadoGet(empleado.cedula);
            data.getEmpleados().remove(result);
            data.getEmpleados().add(empleado);
        } catch (Exception e) {
            throw new Exception("Empleado no existe");
        }
    }

    public void sucursalUpdate(Sucursal sucursal) throws Exception {
        Sucursal result;
        try {
            result = this.sucursalGet(sucursal.referencia);
            data.getSucursales().remove(result);
            data.getSucursales().add(sucursal);
        } catch (Exception e) {
            throw new Exception("Sucursal no existe");
        }
    }

    public List<Empleado> eliminarEmpleado(String nombre) throws Exception {
        for (int i = 0; i < data.getEmpleados().size(); i++) {
            if (Objects.equals(data.getEmpleados().get(i).getNombre(), nombre)) {
                data.getEmpleados().remove(i);
                return data.getEmpleados();
            }
        }
        throw new Exception("Empleado no existe");
    }

    public List<Sucursal> eliminarSucursal(String referencia) throws Exception {
        for (int i = 0; i < data.getSucursales().size(); i++) {
            if (Objects.equals(data.getSucursales().get(i).getReferencia(), referencia)) {
                data.getSucursales().remove(i);
                return data.getSucursales();
            }
        }
        throw new Exception("Sucursal no existe");
    }
    public void store(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}