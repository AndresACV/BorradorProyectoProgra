package sucursales.presentation.sucursales;

import sucursales.logic.Service;
import sucursales.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private JLabel mapLabel;
    private JScrollPane scrollPane1;
    private Image mapImage;
    private Graphics graphics;
    private BufferedImage result;
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
        sucursalesFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    int row = sucursalesFld.getSelectedRow();
                    referenciaTemporal = model.getSucursales().get(row).getReferencia();
                    actualizarMapa();
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
                    JOptionPane.showMessageDialog(panel, "Sucursal tiene empleados o no existe","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.imprimir();
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File("sucursales.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) { }
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
        sucursalesFld.setModel(new sucursales.presentation.sucursales.TableModel(cols, model.getSucursales()));
        sucursalesFld.setRowHeight(30);
        this.panel.revalidate();
    }

    private void llenarMapa() {
        for (int j = 0; j < Service.instance().getData().getSucursales().size(); j++) {
            JLabel temp = new JLabel();
            Sucursal s = Service.instance().getData().getSucursales().get(j);
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

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
       init();
    }

    public void init() {
        try {
            mapLabel = new JLabel(); mapLabel.removeAll();
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