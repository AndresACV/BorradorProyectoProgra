package sucursales.presentation.sucursales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel panel;
    private JTextField referenciaFld;
    private JButton buscarFld;
    private JButton agregarFld;
    private JTable sucursalesFld;
    private JLabel referenciaLbl;
    private JButton eliminarFld;
    private JButton reporteButton;
    private JLabel imagenLbl;

    sucursales.presentation.sucursales.Controller controller;
    sucursales.presentation.sucursales.Model model;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscar(referenciaFld.getText());
            }
        });
        agregarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.preAgregar();
            }
        });
        sucursalesFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = sucursalesFld.getSelectedRow();
                    controller.editar(row);
                }
            }
        });
        eliminarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(!Objects.equals(referenciaFld.getText(), "")){
                        controller.eliminar(referenciaFld.getText());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panel, "Sucursal no existe","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void setController(sucursales.presentation.sucursales.Controller controller) { this.controller = controller; }
    public void setModel(sucursales.presentation.sucursales.Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {sucursales.presentation.sucursales.TableModel.CODIGO, sucursales.presentation.sucursales.TableModel.REFERENCIA, sucursales.presentation.sucursales.TableModel.DIRECCION, sucursales.presentation.sucursales.TableModel.ZONAJE};
        sucursalesFld.setModel(new sucursales.presentation.sucursales.TableModel(cols, model.getSucursales()));
        sucursalesFld.setRowHeight(30);
        this.panel.revalidate();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        reporteButton = new JButton();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/IconPDF.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        reporteButton.setIcon(imageIcon);

        imagenLbl = new JLabel();
        ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("src/main/resources/MapCR.png").getImage().getScaledInstance(700, 500, Image.SCALE_DEFAULT));
        imagenLbl.setIcon(imageIcon2);
    }
}