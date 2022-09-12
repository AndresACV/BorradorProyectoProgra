package sucursales.logic;

public class Empleado {

    String cedula;
    String nombre;
    String telefono;
    double salarioBase;
    Sucursal sucursal;
    double zonaje;
    double salarioTotal;

    public Empleado(String cedula, String nombre, String telefono, double salarioBase, Sucursal sucursal) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.salarioBase = salarioBase;
        this.sucursal = sucursal;
        this.zonaje = sucursal.getPorcentajeZonaje();
        this.salarioTotal = salarioBase + (salarioBase / 100);
    }
    public Empleado() {
        this("", "", "", 0, null);
    }

    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }
    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public double getZonaje() {
        return zonaje;
    }
    public void setZonaje(double zonaje) {
        this.zonaje = zonaje;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }
    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }
}