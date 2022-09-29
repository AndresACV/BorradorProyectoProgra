package sucursales.presentation.empleados;

import sucursales.logic.Empleado;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {

    String[] colNames = new String[7];
    public static final int CEDULA=0;
    public static final int NOMBRE=1;
    public static final int TELEFONO=2;
    public static final int SALARIO_BASE=3;
    public static final int SUCURSAL=4;
    public static final int ZONAJE = 5;
    public static final int SALARIO_TOTAL = 6;

    List<Empleado> rows;
    int[] cols;

    private void initColNames(){
        colNames[CEDULA]= "Cedula";
        colNames[NOMBRE]= "Nombre";
        colNames[TELEFONO]= "Telefono";
        colNames[SALARIO_BASE]= "Salario";
        colNames[SUCURSAL]= "Sucursal";
        colNames[ZONAJE]= "%Zonaje";
        colNames[SALARIO_TOTAL]= "Sal. Total";
    }

    public TableModel(int[] cols, List<Empleado> rows){
        initColNames();
        this.cols=cols;
        this.rows=rows;
    }

    public int getColumnCount() {
        return cols.length;
    }
    public String getColumnName(int col){
        return colNames[cols[col]];
    }
    public int getRowCount() {
        return rows.size();
    }
    public Class<?> getColumnClass(int col){ return super.getColumnClass(col); }

    public Object getValueAt(int row, int col) {
        Empleado empleado = rows.get(row);
        switch (cols[col]){
            case CEDULA: return empleado.getCedula();
            case NOMBRE: return empleado.getNombre();
            case TELEFONO: return empleado.getTelefono();
            case SALARIO_BASE: return empleado.getSalarioBase();
            case SUCURSAL: return empleado.getSucursal().getReferencia();
            case ZONAJE: return empleado.getSucursal().getPorcentajeZonaje();
            case SALARIO_TOTAL: return empleado.getSalarioTotal();
            default: return "";
        }
    }
}