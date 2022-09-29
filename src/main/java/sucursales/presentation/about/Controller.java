package sucursales.presentation.about;

public class Controller {

    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
}