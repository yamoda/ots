package Laba5Package.Controllers;

import Laba5Package.Exceptions.GraphException;
import Laba5Package.Models.Graph;
import Laba5Package.Views.View;

import javax.swing.*;

public class Controller {
    private final View view;
    private final Graph graph;

    public Controller(final View view, final Graph graph) {
        this.view = view;
        this.graph = graph;

        view.getAddNodeDialog().setActionToAddNodeButton(e -> addNode());
        view.getDeleteNodeDialog().setActionToDeleteNodeJButton(e -> deleteNode());
    }

    public void addNode() {
        String[] input = view.getAddNodeDialog().getValuesFromUser();
        try {
            graph.addNewNode(input[0], Double.parseDouble(input[1]));
            updateView();
        } catch (GraphException e) {
            JOptionPane.showMessageDialog(view.getAddNodeDialog(),
                    e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view.getAddNodeDialog(),
                    "Введено некорректное значение!");
        }
    }

    public void deleteNode() {
        try {
            graph.deleteNodeByName(view.getDeleteNodeDialog().getValueForDelete());
            updateView();
        } catch (GraphException e) {
            JOptionPane.showMessageDialog(view.getDeleteNodeDialog(),
                    "Введено некорректное имя!");
        }
    }


    public void updateView() {
        view.getTableOfNodes().setNodesToDisplay(graph.getNodesOfGraph());
        view.getTableOfNodes().setRemainingValue(graph.getSumOfValues());
        try {
            view.getPaintNodesPanel().setNodesToDraw(graph.getGraph());
        } catch (GraphException e) {
            view.getPaintNodesPanel().setNodesToDraw(null);
        }
        view.getMainFrame().repaint();
    }
}