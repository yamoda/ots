package Laba5Package.Views;

import Laba5Package.Models.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class MyTable extends JPanel {
    private final DefaultTableModel tableModel;
    private List<Node> nodesOfGraph;
    private String remainingValue;

    public MyTable() {
        this.nodesOfGraph = new ArrayList<>();
        JTable jTable = new JTable();
        tableModel = new DefaultTableModel();
        String[] names = {"#", "Имя", "Значение"};

        tableModel.setColumnIdentifiers(names);
        jTable.setModel(tableModel);
        remainingValue = Double.toString(0.0);

        add(new JScrollPane(jTable));
        setPreferredSize(new Dimension(500, 700));
        displayPage();
    }

    public void setRemainingValue(String value) {
        this.remainingValue = value;
        displayPage();
    }

    public void setNodesToDisplay(final List<Node> nodesOfGraph) {
        if (nodesOfGraph == null) return;
        this.nodesOfGraph = nodesOfGraph;
        displayPage();
    }

    private void displayPage() {
        tableModel.setRowCount(0);
        for (int i = 0; i < nodesOfGraph.size(); i++) {
            tableModel.addRow(new Object[]{i + 1, nodesOfGraph.get(i).getName(), nodesOfGraph.get(i).getValue()});
        }
        tableModel.addRow(new Object[]{"-", "сумма значений", remainingValue});
    }
}