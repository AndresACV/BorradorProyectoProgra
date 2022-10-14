package sucursales.logic;

import sucursales.data.SucursalDao;
import java.util.Objects;



public class Sucursal {

    String codigo;
    String referencia;
    String direccion;
    double porcentajeZonaje;
    int x;
    int y;

    public Sucursal(String codigo, String referencia, String direccion, double porcentajeZonaje) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.direccion = direccion;
        this.porcentajeZonaje = porcentajeZonaje;
    }

    public Sucursal(String codigo, String referencia, String direccion, double porcentajeZonaje, int x, int y) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.direccion = direccion;
        this.porcentajeZonaje = porcentajeZonaje;
        this.x = x;
        this.y = y;
    }

    public Sucursal() { this("","","",0.0); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sucursal sucursal = (Sucursal) o;
        return codigo.equals(sucursal.codigo);
    }

    public String getCodigo() { return codigo;}
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion;}

    public double getPorcentajeZonaje() { return porcentajeZonaje; }
    public void setPorcentajeZonaje(double porcentajeZonaje) { this.porcentajeZonaje = porcentajeZonaje; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }

    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
}