package pl.edu.agh.student.gui;

import pl.edu.agh.student.utils.Tree;
import pl.edu.agh.student.utils.MyNode;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreePanel extends JPanel {

    private Graphics g;

    private Tree tree;

    protected Map<Integer, Position> nodesPositions = new HashMap<Integer, Position>();

    public void initialize(Tree tree) {
        this.tree = tree;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        drawTree();
    }

    public void clear() {
        this.tree = null;
        repaint();
    }

    public void drawTree() {
        if (tree != null) {
            MyNode root = tree.getRoot();

            Dimension panelSize = getSize();
            drawNode((int) (panelSize.getWidth() / 2), 0, root, null,
                (int) panelSize.getWidth());
        }
    }

    protected void drawNode(int parentX, int parentY, MyNode treeNode, Boolean left, int width) {
        if (treeNode != null) {

            String value = String.valueOf(treeNode.getValue().intValue());
            int myX = 0;
            int myY = parentY + 60;

            if (left == null) {
                myX = parentX;
            }
            if (left != null) {
                if (left) {
                    myX = parentX - width / 2;
                } else {
                    myX = parentX + width / 2;
                }
            }

            drawLine(myX, myY, parentX, parentY);
            drawNode(myX, myY, treeNode.getLeft(), true, width / 2);
            drawNode(myX, myY, treeNode.getRight(), false, width / 2);
            addVisibleNode(treeNode.getId(), myX, myY);
            drawCircleWithLabel(value, myX, myY);
        }

    }

    protected void addVisibleNode(int id, int x, int y) {
        nodesPositions.put(id, new Position(x, y));
    }

    protected void drawLine(int x, int y, int x1, int y1) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 2);

        polyline.moveTo(x + 15, y + 15);
        polyline.lineTo(x1 + 15, y1 + 15);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        g2D.draw(polyline);
    }

    protected Shape drawCircleWithLabel(String label, final int x, final int y, Color... color) {

        Ellipse2D s = new Ellipse2D.Double((double) x, (double) y, 30, 30);


        if (color.length != 0) {
            g.setColor(color[0]);
        } else {
            g.setColor(Color.yellow);
        }

        Graphics2D g2D = (Graphics2D) g;

        g2D.fill(s);

        g.setColor(Color.blue);
        g.drawOval(x, y, (int) s.getWidth(), (int) s.getHeight());

        int xOffSet = 12;
        if (label.length() == 2) {
            xOffSet = 8;
        } else if (label.length() == 3) {
            xOffSet = 4;
        } else if (label.length() > 3) {
            xOffSet = 1;
        }

        g.drawString(label, x + xOffSet, y + 20);

        return s;
    }

}
