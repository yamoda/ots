package Laba5Package.Models;

import Laba5Package.Exceptions.GraphException;

import java.awt.geom.Point2D;

public class Node implements Comparable<Node> {
    private final double value;
    private final String name;
    private Point2D point;

    public Node(String name, double value) throws GraphException {
        if (name == null) throw new GraphException("Имя не может быть null");
        this.value = value;
        this.name = name;
        this.point = new Point2D.Double(0, 0);
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Point2D getPoint() {
        return point;
    }

    public void setPoint(Point2D point) {
        this.point = point;
    }

    @Override
    public int compareTo(Node o) {
        if (this.value - o.value < 0) {
            return -1;
        } else if (this.value - o.value > 0) {
            return 1;
        } else return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = prime + name.hashCode();
        return prime * result + (int) value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (this.getClass() != obj.getClass()) return false;
        Node otherNode = (Node) obj;

        return this.value == otherNode.value && this.name.equals(otherNode.name);
    }
}