package sucursales.logic;

public class Sucursal {

    String codigo;

    String referencia;
    String direccion;
    double porcentajeZonaje;

    public Sucursal(String codigo, String referencia, String direccion, double porcentajeZonaje) {
        this.codigo = codigo;
        this.referencia = referencia;
        this.direccion = direccion;
        this.porcentajeZonaje = porcentajeZonaje;
    }

    public Sucursal() { this("","","",0.0); }

    public String getCodigo() { return codigo;}

    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getDireccion() { return direccion; }

    public void setDireccion(String direccion) { this.direccion = direccion;}

    public double getPorcentajeZonaje() { return porcentajeZonaje; }

    public void setPorcentajeZonaje(double porcentajeZonaje) { this.porcentajeZonaje = porcentajeZonaje; }

    public String getReferencia() { return referencia; }

    public void setReferencia(String referencia) { this.referencia = referencia; }
}


