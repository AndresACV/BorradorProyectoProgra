package sucursales.presentation.about;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel panel;
    private JLabel tituloLabel;
    private JLabel aboutLabel;
    private JLabel imagenLabel;

    JPanel ImagePanel;
    private ImageIcon image;
    public JPanel getPanel() {
        return panel;
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }
    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }
    @Override
    public void update(Observable updatedModel, Object parametros) {
        this.panel.revalidate();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        imagenLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/CompanyLogo.jpg").getImage().getScaledInstance(800, 400, Image.SCALE_DEFAULT));
        imagenLabel.setIcon(imageIcon);
    }
}