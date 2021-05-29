package Laba5Package.Views.CustomDialogs;

import Laba5Package.Views.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DeleteNodeDialog extends CustomDialog {

    private final JTextField deleteNodeTextField;
    private final JButton deleteNodeButton;

    public DeleteNodeDialog(JFrame jFrame) {
        super(jFrame, "Удаление узла");

        setLayout(new GridLayout(3, 1, 10, 5));

        deleteNodeTextField = new JTextField();
        deleteNodeButton = new MyButton("Удалить узел");
        getContentPane().add(new JLabel("Имя узла"));
        getContentPane().add(deleteNodeTextField);
        getContentPane().add(deleteNodeButton);
        pack();
        setCustomDialogLocation();
    }

    public void setActionToDeleteNodeJButton(ActionListener action) {
        deleteNodeButton.addActionListener(action);
    }

    public String getValueForDelete() {
        return deleteNodeTextField.getText();
    }

    public void clearTextFields() {
        deleteNodeTextField.setText(null);
    }
}