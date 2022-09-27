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
                    JOptionPane.showMessageDialog(panel, "Sucursal tiene empleados","ERROR",JOptionPane.ERROR_MESSAGE);
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
        reporteButton = new JButton();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/IconPDF.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        reporteButton.setIcon(imageIcon);

        selectedLabel = new JLabel();
        sucursalSelectedImage = ImageIO.read(new File("src/main/resources/SucursalSel.png"));
        selectedLabel.setIcon(new ImageIcon(sucursalSelectedImage));

        unselectedLabel = new JLabel();
        sucursalUnselectedImage = ImageIO.read(new File("src/main/resources/Sucursal.png"));
        unselectedLabel.setIcon(new ImageIcon(sucursalUnselectedImage));

        mapLabel = new JLabel();
        mapLabel.removeAll();
        mapImage = ImageIO.read(new File("src/main/resources/MapCR.png"));
        mapImage = mapImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        result = new BufferedImage(400,400, BufferedImage.TYPE_INT_ARGB);
        graphics = result.getGraphics();
        graphics.drawImage(mapImage, 10, 10, mapLabel);
        mapLabel.setIcon(new ImageIcon(result));
    }
}