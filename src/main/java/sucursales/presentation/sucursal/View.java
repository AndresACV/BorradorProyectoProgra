package sucursales.presentation.sucursal;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Sucursal;
import sucursales.presentation.empleado.Controller;
import sucursales.presentation.empleado.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel panel;
    private JTextField codigoFld;
    private JTextField referenciaFld;

    private JButton guardarFld;
    private JButton cancelarFld;

    private JLabel codigoLbl;
    private JLabel referenciaLbl;

    private JFrame window;

    public View() {
        guardarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Sucursal n = take();
                    try {
                        controller.guardar(n);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        cancelarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
            }
        }
        );
    }

    public JPanel getPanel() {
        return panel;
    }

    sucursales.presentation.sucursal.Controller controller;
    sucursales.presentation.sucursal.Model model;

    public void setController(sucursales.presentation.sucursal.Controller controller) {
        this.controller = controller;
    }

    public void setModel(sucursales.presentation.sucursal.Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        Sucursal current = model.getCurrent();
        if(current != null)      {
            this.codigoFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
            this.codigoFld.setText(current.getCodigo());
            referenciaFld.setText(current.getReferencia());
            this.panel.validate();
        }
    }

    public Sucursal take() {
        Sucursal s = new Sucursal();
        s.setCodigo(codigoFld.getText());
        s.setReferencia(referenciaFld.getText());
        return s;
    }

    private boolean validate() {
        boolean valid = true;
        if (codigoFld.getText().isEmpty()) {
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
            codigoLbl.setToolTipText("Codigo requerido");
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (referenciaFld.getText().length() == 0) {
            valid = false;
            referenciaFld.setBorder(Application.BORDER_ERROR);
            referenciaFld.setToolTipText("Referencia requerida");
        } else {
            referenciaFld.setBorder(null);
            referenciaFld.setToolTipText(null);
        }
        return valid;
    }
}