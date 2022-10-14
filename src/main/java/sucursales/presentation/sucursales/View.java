package sucursales.presentation.sucursales;

import sucursales.logic.Sucursal;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel panel;
    private JTextField referenciaField;
    private JButton buscarButton;
    private JButton agregarField;
    private JTable sucursalesField;
    private JLabel referenciaLabel;
    private JButton eliminarButton;
    private JButton reporteButton;
    private JScrollPane tabla;

    private JLabel mapLabel;
    private Image mapImage;

    private JLabel selectedLabel;
    private Image sucursalSelectedImage;

    private JLabel unselectedLabel;
    private Image sucursalUnselectedImage;

    private String referenciaTemporal;
    private ImageIcon reporteImage;

    sucursales.presentation.sucursales.Controller controller;
    sucursales.presentation.sucursales.Model model;

    public JPanel getPanel() {
        return panel;
    }

    public View() {
        buscarButton.addActionListener(e -> {
            try {
                controller.buscar(referenciaField.getText());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        agregarField.addActionListener(e -> controller.preAgregar());
        sucursalesField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = sucursalesField.getSelectedRow();
                    referenciaTemporal = model.getSucursales().get(row).getReferencia();
                    actualizarMapa();
                } else if (e.getClickCount() == 2) {
                    int row = sucursalesField.getSelectedRow();
                    controller.editar(row);
                }
            }
        });
        eliminarButton.addActionListener(e -> {
            try {
                if(!Objects.equals(referenciaField.getText(), "")){
                    controller.eliminar(referenciaField.getText());
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Sucursal tiene empleados o no existe","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
        reporteButton.addActionListener(e -> {
            try {
                controller.imprimir();
                if (Desktop.isDesktopSupported()) {
                    File myFile = new File("sucursales.pdf");
                    Desktop.getDesktop().open(myFile);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "No se pudo generar el reporte","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void setController(sucursales.presentation.sucursales.Controller controller) { this.controller = controller; }
    public void setModel(sucursales.presentation.sucursales.Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public void actualizarMapa(){
        mapLabel.removeAll();
        llenarMapa();
        panel.updateUI();
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        actualizarMapa();
        int[] cols = {sucursales.presentation.sucursales.TableModel.CODIGO, sucursales.presentation.sucursales.TableModel.REFERENCIA, sucursales.presentation.sucursales.TableModel.DIRECCION, sucursales.presentation.sucursales.TableModel.ZONAJE};
        sucursalesField.setModel(new sucursales.presentation.sucursales.TableModel(cols, model.getSucursales()));
        sucursalesField.setRowHeight(30);
        this.panel.revalidate();
    }

    private void llenarMapa() {
        for (int j = 0; j < model.getSucursales().size(); j++) {
            JLabel temp = new JLabel();
            Sucursal s = model.getSucursales().get(j);
            temp.setSize(30, 30);
            temp.setLocation(s.getX() - 15, s.getY() - 31);
            temp.setToolTipText("<html>" + s.getReferencia()  + "<br/>" + s.getDireccion() +"</html>");
            if(Objects.equals(referenciaTemporal, s.getReferencia()))
                temp.setIcon(new ImageIcon(sucursalSelectedImage));
             else
                temp.setIcon(new ImageIcon(sucursalUnselectedImage));
            temp.setVisible(true);
            mapLabel.add(temp);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            mapLabel = new JLabel();
            selectedLabel = new JLabel();
            unselectedLabel = new JLabel();
            reporteButton = new JButton();

            sucursalSelectedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/SucursalSel.png")));
            sucursalUnselectedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Sucursal.png")));
            mapImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/MapCR.png")));
            reporteImage = new ImageIcon(new ImageIcon("src/main/resources/IconPDF.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

            sucursalSelectedImage = sucursalSelectedImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            sucursalUnselectedImage = sucursalUnselectedImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            mapImage = mapImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);

            selectedLabel.setIcon(new ImageIcon(sucursalSelectedImage));
            unselectedLabel.setIcon(new ImageIcon(sucursalUnselectedImage));
            mapLabel.setIcon(new ImageIcon(mapImage));
            reporteButton.setIcon(reporteImage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}