package sucursales.data;

import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDao {
    Database db;

    public EmpleadoDao() {
        db = Database.instance();
    }

    public void create(Empleado e) throws Exception {
        String sql = "insert into " +
                "Empleado " +
                "(cedula, nombre, telefono, salarioBase, salarioTotal, sucursal) " +
                "values(?,?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCedula());
        stm.setString(2, e.getNombre());
        stm.setString(3, e.getTelefono());
        stm.setDouble(4, e.getSalarioBase());
        stm.setDouble(5, e.getSalarioTotal());
        stm.setString(6, e.getSucursal().getCodigo());
        db.executeUpdate(stm);
    }

    public Empleado read(String cedula) throws Exception {
        String sql = "select " +
                "* " +
                "from  Empleado e " +
                " inner join Sucursal s on e.sucursal=s.codigo " +
                "where e.cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, cedula);
        ResultSet rs = db.executeQuery(stm);
        Empleado e;
        SucursalDao sucursalDao = new SucursalDao();
        if (rs.next()) {
            e=from(rs,"e");
            e.setSucursal(sucursalDao.from(rs,"s"));
            return e;
        } else {
            throw new Exception("EMPLEADO NO EXISTE");
        }
    }

    public void update(Empleado e) throws Exception {
        String sql = "update " +
                "Empleado " +
                "set nombre=?, telefono=?, salarioBase=?, salarioTotal=?, sucursal=?  " +
                "where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getNombre());
        stm.setString(2, e.getTelefono());
        stm.setDouble(3, e.getSalarioBase());
        stm.setDouble(4, e.getSalarioTotal());
        stm.setString(5,e.getSucursal().getCodigo());
        stm.setString(6, e.getCedula());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("EMPLEADO NO EXISTE");
        }

    }

    public void delete(Empleado e) throws Exception {
        String sql = "delete " +
                "from Empleado " +
                "where cedula=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCedula());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("EMPLEADO NO EXISTE");
        }
    }

    public List<Empleado> findByNombre(String nombre) throws Exception {
        SucursalDao sucursalDao = new SucursalDao();
        List<Empleado> resultado = new ArrayList<Empleado>();
        String sql = "select * " +
                "from " +
                "empleado e " +
                "  inner join Sucursal s on e.sucursal=s.codigo " +
                "where e.nombre like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + nombre + "%");
        ResultSet rs = db.executeQuery(stm);
        Empleado empleado;
        while (rs.next()) {
            empleado = from(rs,"e");
            empleado.setSucursal(sucursalDao.from(rs,"s"));
            resultado.add(empleado);
        }
        return resultado;
    }

    public Empleado from(ResultSet rs, String alias) throws Exception {
        Empleado e = new Empleado();
        e.setCedula(rs.getString(alias + ".cedula"));
        e.setNombre(rs.getString(alias + ".nombre"));
        e.setTelefono(rs.getString(alias + ".telefono"));
        e.setSalarioBase(rs.getDouble(alias + ".salarioBase"));
        e.setSalarioTotal(rs.getDouble(alias + ".salarioTotal"));
        return e;
    }

}
