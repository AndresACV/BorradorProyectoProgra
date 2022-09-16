package sucursales.logic;

public class Empleado {

    String cedula;
    String nombre;
    String telefono;
    double salarioBase;
    double salarioTotal;
    public Empleado(String cedula, String nombre, String telefono, double salarioBase) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.salarioBase = salarioBase;
        this.salarioTotal = salarioBase + (salarioBase / 100);
    }
    public Empleado() {
        this("", "", "", 0);
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

    public double getSalarioTotal() {
        return salarioTotal;
    }
    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }
}