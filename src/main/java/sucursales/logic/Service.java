package sucursales.logic;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import sucursales.data.EmpleadoDao;
import sucursales.data.SucursalDao;

public class Service {

    private static Service instance;


    private SucursalDao sucursalDao;
    private EmpleadoDao empleadoDao;

    public static Service instance() {
        if (instance == null) {
            instance = new Service();

        }
        return instance;
    }
    private Service(){
        sucursalDao= new SucursalDao();
        empleadoDao = new EmpleadoDao();
    }


    public List<Empleado> empleadosSearch(Empleado filtro) throws Exception {
        return  empleadoDao.findByNombre(filtro.getNombre());
    }

    public List<Sucursal> sucursalesSearch(Sucursal filtro) throws Exception {
        return  sucursalDao.findByReferencia(filtro.getReferencia());
    }

    public void agregarEmpleado(Empleado empleado) throws Exception {
       empleadoDao.create(empleado);
    }

    public void agregarSucursal(Sucursal sucursal) throws Exception {
        sucursalDao.create(sucursal);
    }

    public void empleadoUpdate(Empleado empleado) throws Exception {
        empleadoDao.update(empleado);
    }

    public void sucursalUpdate(Sucursal sucursal) throws Exception {
        sucursalDao.update(sucursal);
    }

    public List<Empleado> eliminarEmpleado(Empleado empleado) throws Exception {
        empleadoDao.delete(empleado);
        Empleado e = new Empleado();
        e.setNombre("");
        return empleadosSearch(e);
    }

    public List<Sucursal> eliminarSucursal(Sucursal sucursal) throws Exception {
        sucursalDao.delete(sucursal);
        Sucursal s = new Sucursal();
        s.setCodigo("");
        return sucursalesSearch(s);
    }
}