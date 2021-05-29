package Laba5Package.Models;


import Laba5Package.Exceptions.GraphException;
import javafx.util.Pair;

import java.text.DecimalFormat;
import java.util.*;

public class Graph {
    private final List<Node> nodesOfGraph;
    private double remainingValue;
    private final DecimalFormat format = new DecimalFormat("#.####");

    public Graph() {
        this.nodesOfGraph = new ArrayList<>();
        this.remainingValue = 1.0;
    }

    public void addNewNode(final String name, final double value) throws GraphException {
        if (remainingValue < value) throw new GraphException("Общая сумма значений не должна превышать 1");
        remainingValue = Double.parseDouble(format.format(remainingValue -= value));
        nodesOfGraph.add(new Node(name, value));
        Collections.sort(nodesOfGraph);
    }

    public void deleteNodeByName(final String name) throws GraphException {
        if (name == null) throw new GraphException("Невозможно удалить узел null");
        nodesOfGraph.removeIf(node -> {
            boolean result = node.getName().equals(name);
            if (result) {
                remainingValue = Double.parseDouble(format.format(remainingValue += node.getValue()));
            }
            return result;
        });
    }

    public List<Node> getNodesOfGraph() {
        return new ArrayList<>(nodesOfGraph);
    }

    public String getSumOfValues() {
        return format.format(1.0 - remainingValue);
    }

    public List<Pair<Node, List<Node>>> getGraph() throws GraphException {
        if (remainingValue != 0.0) throw new GraphException("Общая сумма значений должна быть равна 1");

        List<Pair<Node, List<Node>>> result = new ArrayList<>(nodesOfGraph.size());

        List<Node> nodesToConnectWith;
        List<Node> sameNode = new ArrayList<>(nodesOfGraph.size());
        List<Node> smallerNode = new ArrayList<>();

        Node previousNode = null;

        for (Node node : nodesOfGraph) {
            if (previousNode == null) {
                previousNode = node;
                result.add(new Pair<>(node, new ArrayList<>()));
                continue;
            }

            sameNode.add(previousNode);

            if (node.getValue() != previousNode.getValue()) {
                smallerNode = sameNode;
                sameNode = new ArrayList<>(nodesOfGraph.size());
            }

            nodesToConnectWith = new ArrayList<>(sameNode);
            nodesToConnectWith.addAll(smallerNode);

            result.add(new Pair<>(node, nodesToConnectWith));

            previousNode = node;
        }

        return result;
    }
}