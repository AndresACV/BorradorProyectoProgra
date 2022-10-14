package sucursales.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import sucursales.data.SucursalDao;

public class Service {

    private static Service instance;


    private SucursalDao sucursalDao;

    public static Service instance() {
        if (instance == null) {
            instance = new Service();

        }
        return instance;
    }
    private Service(){
        sucursalDao= new SucursalDao();
    }


    public List<Empleado> empleadosSearch(String filtro) throws Exception {
        throw new Exception("Not supported yet.");
    }

    public List<Sucursal> sucursalesSearch(Sucursal filtro) throws Exception {
        return  sucursalDao.findByReferencia(filtro.getReferencia());
    }

//    public Empleado empleadoGet(String cedula) { return data.getEmpleados().stream().filter(e -> e.getCedula().equals(cedula)).findFirst().orElse(null); }
//    public Sucursal sucursalGet(String referencia) { return sucursalDao.findByReferencia().stream().filter(e -> e.getReferencia().equals(referencia)).findFirst().orElse(null); }

    public void agregarEmpleado(Empleado empleado) throws Exception {
       throw new Exception("Not supported yet.");
    }

    public void agregarSucursal(Sucursal sucursal) throws Exception {
        sucursalDao.create(sucursal);
    }

    public void empleadoUpdate(Empleado empleado) throws Exception {
        throw new Exception("Not supported yet.");
    }

    public void sucursalUpdate(Sucursal sucursal) throws Exception {
        sucursalDao.update(sucursal);
    }

    public List<Empleado> eliminarEmpleado(String nombre) throws Exception {
        throw new Exception("Not supported yet.");
    }

    public List<Sucursal> eliminarSucursal(String referencia) throws Exception {
        throw new Exception("Not supported yet.");
    }
}