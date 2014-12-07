package pl.edu.agh.student.gui;

import pl.edu.agh.student.gui.utils.Position;
import pl.edu.agh.student.utils.node.MyNode;
import pl.edu.agh.student.utils.node.RangeTreeNode;
import pl.edu.agh.student.utils.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreePanel extends JPanel {

    private Graphics g;

    private Tree tree;

    private boolean subtree = true;

    protected Map<Integer, Position> nodesPositions = new HashMap<Integer, Position>();
    protected Map<Shape, MyNode> visiblePoints = new HashMap<Shape, MyNode>();

    public void initialize(Tree tree, boolean subtree) {
        this.tree = tree;
        this.subtree = subtree;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        nodesPositions.clear();
        visiblePoints.clear();
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
        } else {
            g.setColor(Color.RED);
            g.drawString("Select node of range tree first", (int) getSize().getWidth() / 2 - 40,
                (int) getSize().getHeight() / 2);
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
            registerNodePosition(treeNode.getId(), myX, myY);

            visiblePoints
                .put(drawCircleWithLabel(value, myX, myY, showBold(treeNode)), treeNode);
        }

    }

    protected boolean showBold(MyNode treeNode) {
        boolean boldCircle = false;
        if (!subtree) {
            boldCircle = !((RangeTreeNode) treeNode).getAssociatedTree().getRoot().isLeaf();
        }
        return boldCircle;
    }

    protected void registerNodePosition(int id, int x, int y) {
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

    protected Shape drawCircleWithLabel(String label, final int x, final int y, boolean boldCircle,
        Color... color) {

        Ellipse2D s = new Ellipse2D.Double((double) x, (double) y, 30, 30);


        if (color.length != 0) {
            g.setColor(color[0]);
        } else {
            g.setColor(Color.yellow);
        }

        Graphics2D g2D = (Graphics2D) g;

        g2D.fill(s);

        g.setColor(Color.BLACK);
        if (boldCircle) {
            ((Graphics2D) g).setStroke(new BasicStroke(3));
        }
        g.drawOval(x, y, (int) s.getWidth(), (int) s.getHeight());
        ((Graphics2D) g).setStroke(new BasicStroke(1));

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
