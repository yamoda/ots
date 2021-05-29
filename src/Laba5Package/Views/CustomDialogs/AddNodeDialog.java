package Laba5Package.Views.CustomDialogs;

import Laba5Package.Views.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AddNodeDialog extends CustomDialog {
    private final JTextField nameTextField;
    private final JTextField valueTextField;
    private final JButton addNodeButton;

    public AddNodeDialog(JFrame jFrame) {
        super(jFrame, "Добавление узла");
        JPanel jPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        addNodeButton = new MyButton("Добавить узел");
        jPanel.add(new JLabel("Имя"));
        jPanel.add(nameTextField = new JTextField());
        jPanel.add(new JLabel("Значение"));
        jPanel.add(valueTextField = new JTextField());

        JPanel buttonJPanel = new JPanel();
        buttonJPanel.add(addNodeButton);

        setLayout(new BorderLayout(5, 5));

        getContentPane().add(jPanel, BorderLayout.CENTER);
        getContentPane().add(buttonJPanel, BorderLayout.SOUTH);
        pack();
        setCustomDialogLocation();
    }

    public void setActionToAddNodeButton(ActionListener action) {
        addNodeButton.addActionListener(action);
    }

    public void clearTextFields() {
        nameTextField.setText(null);
        valueTextField.setText(null);
    }

    public String[] getValuesFromUser() {
        return new String[]{
                nameTextField.getText(),
                valueTextField.getText(),
        };
    }
}
