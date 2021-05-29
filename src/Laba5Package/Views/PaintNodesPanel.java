package Laba5Package.Views;

import Laba5Package.Models.Node;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;


public class PaintNodesPanel extends JPanel {

    private final static int RADIUS_OF_OVAL = 20;
    private List<Pair<Node, List<Node>>> nodes;
    private final static String message = "Невозможно отобразить граф, когда его сумма значений не равна 1";

    public PaintNodesPanel() {
        this.setPreferredSize(new Dimension(500, 500));
        nodes = null;
    }

    public void setNodesToDraw(List<Pair<Node, List<Node>>> nodes) {
        this.nodes = nodes;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D) g.create();

        g2D.setPaint(Color.black);
        g2D.setStroke(new BasicStroke(2));

        FontMetrics fm = g2D.getFontMetrics();


        int centerX = this.getPreferredSize().width / 2;
        int centerY = this.getPreferredSize().height / 2;


        if (nodes == null) {
            double textWidth = fm.getStringBounds(message, g2D).getWidth();
            g2D.drawString(message, (int) (centerX - textWidth / 2), (int) (centerY + fm.getMaxAscent() / 2));
            return;
        }


        for (int i = 0, x, y; i < nodes.size(); i++) {
            Node currentNode = nodes.get(i).getKey();
            x = (int) ((centerX * 0.75) * Math.cos(2 * i * Math.PI / nodes.size())) + centerX;
            y = (int) ((centerY * 0.75) * Math.sin(2 * i * Math.PI / nodes.size())) + centerY;
            currentNode.setPoint(new Point2D.Double(x, y));
            g2D.drawOval(x - RADIUS_OF_OVAL, y - RADIUS_OF_OVAL, RADIUS_OF_OVAL * 2, RADIUS_OF_OVAL * 2);
            String text = Integer.toString(i + 1);
            double textWidth = fm.getStringBounds(text, g2D).getWidth();
            g2D.drawString(text, (int) (x - textWidth / 2), (int) (y + fm.getMaxAscent() / 2));

            nodes.get(i).getValue().forEach(node -> {
                drawArrow(g
                        , node.getValue() == currentNode.getValue() ? ConnectionType.NON_DIRECTIONAL : ConnectionType.DIRECTIONAL
                        , getPointOnCircle(currentNode.getPoint(), angleBetween(currentNode.getPoint(), node.getPoint()))
                        , getPointOnCircle(node.getPoint(), angleBetween(node.getPoint(), currentNode.getPoint())));
            });
        }
    }

    private void drawArrow(Graphics g1, ConnectionType type, final Point2D from, final Point2D to) {
        Graphics2D g = (Graphics2D) g1.create();
        int ARR_SIZE = 6;

        double dx = to.getX() - from.getX(), dy = to.getY() - from.getY();
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx * dx + dy * dy);
        AffineTransform at = AffineTransform.getTranslateInstance(from.getX(), from.getY());
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.drawLine(0, 0, len, 0);

        if (type == ConnectionType.DIRECTIONAL) {
            g.fillPolygon(new int[]{len, len - ARR_SIZE, len - ARR_SIZE, len},
                    new int[]{0, -ARR_SIZE, ARR_SIZE, 0}, 4);
        }
    }

    private double angleBetween(Point2D from, Point2D to) {
        double rotation = -Math.atan2(to.getX() - from.getX(), to.getY() - from.getY());
        rotation = Math.toRadians(Math.toDegrees(rotation) + 180);

        return rotation;
    }

    private Point2D getPointOnCircle(Point2D point, double rotation) {
        rotation = rotation - Math.toRadians(90.0);

        double xPosy = Math.round((float) (point.getX() + Math.cos(rotation) * RADIUS_OF_OVAL));
        double yPosy = Math.round((float) (point.getY() + Math.sin(rotation) * RADIUS_OF_OVAL));

        return new Point2D.Double(xPosy, yPosy);
    }

    private enum ConnectionType {
        DIRECTIONAL,
        NON_DIRECTIONAL
    }
}