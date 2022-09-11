package sucursales.presentation.main;

import sucursales.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Escinf
 */
public class Controller implements ActionListener {
    Model model;
    View view;

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
    } 
    

    // ActionListener Interface    
    public void actionPerformed(ActionEvent e) {
        model.commit();
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }
}
