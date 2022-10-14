package sucursales.data;

import sucursales.logic.Sucursal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SucursalDao {
    Database db;

    public SucursalDao() {
        db = Database.instance();
    }

    public void create(Sucursal e) throws Exception {
        String sql = "insert into " +
                "Sucursal " +
                "(codigo, referencia, direccion, porcentajeZonaje, x, y) " +
                "values(?,?,?,?,?,?)";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        stm.setString(2, e.getReferencia());
        stm.setString(3, e.getDireccion());
        stm.setDouble(4, e.getPorcentajeZonaje());
        stm.setInt(5, e.getX());
        stm.setInt(6, e.getY());

        db.executeUpdate(stm);
    }

    public Sucursal read(String codigo) throws Exception {
        String sql = "select " +
                "* " +
                "from  Sucursal s " +
                "where s.codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, codigo);
        ResultSet rs = db.executeQuery(stm);
        if (rs.next()) {
            return from(rs, "s");
        } else {
            throw new Exception("SUCURSAL NO EXISTE");
        }
    }

    public void update(Sucursal e) throws Exception {
        String sql = "update " +
                "Sucursal " +
                "set referencia=?, direccion=?, porcentajeZonaje=?, x=?, y=?  " +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getReferencia());
        stm.setString(2, e.getDireccion());
        stm.setDouble(3, e.getPorcentajeZonaje());
        stm.setInt(4, e.getX());
        stm.setInt(5, e.getY());
        stm.setString(6, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("SUCURSAL NO EXISTE");
        }

    }

    public void delete(Sucursal e) throws Exception {
        String sql = "delete " +
                "from Sucursal " +
                "where codigo=?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, e.getCodigo());
        int count = db.executeUpdate(stm);
        if (count == 0) {
            throw new Exception("SUCURSAL NO EXISTE");
        }
    }

    public List<Sucursal> findByReferencia(String referencia) throws Exception {
        List<Sucursal> resultado = new ArrayList<Sucursal>();
        String sql = "select * " +
                "from " +
                "sucursal s " +
                "where s.referencia like ?";
        PreparedStatement stm = db.prepareStatement(sql);
        stm.setString(1, "%" + referencia + "%");
        ResultSet rs = db.executeQuery(stm);
        while (rs.next()) {
            resultado.add(from(rs, "s"));
        }
        return resultado;
    }

    public Sucursal from(ResultSet rs, String alias) throws Exception {
        Sucursal e = new Sucursal();
        e.setCodigo(rs.getString(alias + ".codigo"));
        e.setReferencia(rs.getString(alias + ".referencia"));
        e.setDireccion(rs.getString(alias + ".direccion"));
        e.setPorcentajeZonaje(rs.getDouble(alias + ".porcentajeZonaje"));
        e.setX(rs.getInt(alias + ".x"));
        e.setY(rs.getInt(alias + ".y"));
        return e;
    }

}
