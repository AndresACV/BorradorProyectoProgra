package sucursales.presentation.sucursales;

import sucursales.logic.Sucursal;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableModel extends AbstractTableModel implements javax.swing.table.TableModel {
    List<Sucursal> rows;
    int[] cols;

    public TableModel(int[] cols, List<Sucursal> rows){
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

    public Class<?> getColumnClass(int col){
        switch (cols[col]){
            default: return super.getColumnClass(col);
        }
    }

    public Object getValueAt(int row, int col) {
        Sucursal sucursal = rows.get(row);
        switch (cols[col]){
            case CODIGO: return sucursal.getCodigo();
            case REFERENCIA: return sucursal.getReferencia();
            case DIRECCION: return sucursal.getDireccion();
            case ZONAJE: return sucursal.getPorcentajeZonaje();
            default: return "";
        }
    }

    public static final int CODIGO=0;
    public static final int REFERENCIA=1;
    public static final int DIRECCION=2;
    public static final int ZONAJE=3;

    String[] colNames = new String[4];
    private void initColNames(){
        colNames[CODIGO]= "Codigo";
        colNames[REFERENCIA]= "Referencia";
        colNames[DIRECCION]= "Direccion";
        colNames[ZONAJE]= "%Zonaje";
    }
}