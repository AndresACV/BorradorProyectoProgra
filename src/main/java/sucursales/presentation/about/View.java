package sucursales.presentation.about;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel panel;
    private JLabel titleLabel;
    private JLabel aboutLabel;

    private JLabel imagenLabel;
    private Image companyLogo;

    Controller controller;
    Model model;

    public JPanel getPanel() {
        return panel;
    }

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
        imageLoad();
    }

    public void imageLoad() {
        try {
            imagenLabel = new JLabel();
            companyLogo = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/CompanyLogo.jpg")));
            companyLogo = companyLogo.getScaledInstance(800, 400, Image.SCALE_DEFAULT);
            imagenLabel.setIcon(new ImageIcon(companyLogo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}