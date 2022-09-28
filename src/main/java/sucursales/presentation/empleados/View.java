package sucursales.presentation.empleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;


public class View implements Observer {

    private JPanel panel;
    private JTextField nombreField;
    private JButton buscarButton;
    private JButton agregarButton;
    private JTable empleadosTable;
    private JLabel nombreLabel;
    private JButton eliminarButton;
    private JButton reporteButton;
    private JLabel imagenLabel;

    Controller controller;
    Model model;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        buscarButton.addActionListener(e -> controller.buscar(nombreField.getText()));
        agregarButton.addActionListener(e -> controller.preAgregar());
        empleadosTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = empleadosTable.getSelectedRow();
                    controller.editar(row);
                }
            }
        });
        eliminarButton.addActionListener(e -> {
            try {
                if(!Objects.equals(nombreField.getText(), "")){
                    controller.eliminar(nombreField.getText());
                }
            } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Empleado no existe","ERROR",JOptionPane.ERROR_MESSAGE);
        }
        });
        reporteButton.addActionListener(e -> {
            try {
                controller.imprimir();
                if (Desktop.isDesktopSupported()) {
                    File myFile = new File("empleados.pdf");
                    Desktop.getDesktop().open(myFile);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "No se pudo imprimir el reporte","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void setController(Controller controller) { this.controller = controller; }
    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {TableModel.CEDULA, TableModel.NOMBRE, TableModel.TELEFONO, TableModel.SALARIO_BASE, TableModel.SUCURSAL, TableModel.ZONAJE, TableModel.SALARIO_TOTAL};
        empleadosTable.setModel(new TableModel(cols, model.getEmpleados()));
        empleadosTable.setRowHeight(30);
        this.panel.revalidate();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        init();
    }
    private void init(){
        reporteButton = new JButton();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/IconPDF.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        reporteButton.setIcon(imageIcon);
    }
}