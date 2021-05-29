package Laba5Package.Views;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

import Laba5Package.Views.CustomDialogs.AddNodeDialog;
import Laba5Package.Views.CustomDialogs.DeleteNodeDialog;

public class View {
    private final JFrame MainFrame;

    private final AddNodeDialog addNodeDialog;
    private final DeleteNodeDialog deleteNodeDialog;

    private final MyTable tableOfNodes;
    private final PaintNodesPanel paintNodesPanel;

    public View() {
        MainFrame = new JFrame("Граф");
        JPanel southJPanel = new JPanel(new GridLayout(1, 2, 10, 5));
        JPanel tableJPanel = new JPanel(new BorderLayout());
        JButton addNodeButton = new MyButton(Paths.get("Pictures//add-node.png").toFile());
        JButton deleteNodeButton = new MyButton(Paths.get("Pictures//delete-node.png").toFile());
        tableOfNodes = new MyTable();
        addNodeDialog = new AddNodeDialog(MainFrame);
        deleteNodeDialog = new DeleteNodeDialog(MainFrame);
        paintNodesPanel = new PaintNodesPanel();


        MainFrame.setLayout(new BorderLayout(5, 5));
        MainFrame.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 500,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 275,
                1000, 550);

        addNodeButton.addActionListener(e -> {
            addNodeDialog.setCustomDialogLocation();
            addNodeDialog.setVisible(true);
        });

        deleteNodeButton.addActionListener(e -> {
            deleteNodeDialog.setCustomDialogLocation();
            deleteNodeDialog.setVisible(true);
        });

        southJPanel.add(addNodeButton);
        southJPanel.add(deleteNodeButton);
        tableJPanel.add(tableOfNodes, BorderLayout.CENTER);
        tableJPanel.add(southJPanel, BorderLayout.SOUTH);

        MainFrame.add(paintNodesPanel, BorderLayout.CENTER);
        MainFrame.add(tableJPanel, BorderLayout.WEST);

        MainFrame.setVisible(true);
        MainFrame.setResizable(false);
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JFrame getMainFrame() {
        return MainFrame;
    }

    public AddNodeDialog getAddNodeDialog() {
        return addNodeDialog;
    }

    public DeleteNodeDialog getDeleteNodeDialog() {
        return deleteNodeDialog;
    }

    public MyTable getTableOfNodes() {
        return tableOfNodes;
    }

    public PaintNodesPanel getPaintNodesPanel() {
        return paintNodesPanel;
    }
}