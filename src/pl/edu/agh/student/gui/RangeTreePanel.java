package pl.edu.agh.student.gui;

import pl.edu.agh.student.TreeSearch;
import pl.edu.agh.student.utils.RangeTreeNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class RangeTreePanel extends JPanel {

    private Graphics g;
    private TreeSearch treeSearch;
    private App callback;

    private String hoveredText = null;
    private Shape hoveredShape = null;

    private Map<Shape, RangeTreeNode> visiblePoints = new HashMap<Shape, RangeTreeNode>();

    public RangeTreePanel() {

        this.setLayout(null);

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override public void mouseDragged(MouseEvent e) {

            }

            @Override public void mouseMoved(MouseEvent e) {

                boolean wasVisible = hoveredText != null && hoveredShape != null ? true : false;

                for (Shape s : visiblePoints.keySet()) {
                    if (s.contains(e.getPoint())) {
                        if (!wasVisible) {
                            hoveredShape = s;
                            extractHoverText(visiblePoints.get(s));
                            repaint();
                            return;
                        }
                        return;
                    }
                }

                if (wasVisible) {
                    hoveredShape = null;
                    hoveredText = null;
                    repaint();
                }

            }
        });

        this.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {
                for (Shape s : visiblePoints.keySet()) {
                    if (s.contains(e.getPoint())) {
                        callback.showRangeTree(visiblePoints.get(s).getAssociatedTree());
                    }
                }
            }

            @Override public void mousePressed(MouseEvent e) {

            }

            @Override public void mouseReleased(MouseEvent e) {

            }

            @Override public void mouseEntered(MouseEvent e) {

            }

            @Override public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void clear() {
        hoveredText = null;
        hoveredShape = null;
        repaint();
    }

    private void extractHoverText(RangeTreeNode node) {
        if (node.getAssociatedTree().getRoot().isLeaf()) {
            int x = node.getValue().intValue();
            int y = node.getAssociatedTree().getRoot().getValue().intValue();
            hoveredText = "(" + x + "," + y + ")";
        } else {
            hoveredText = "Click for more";
        }
    }

    public void initialize(App callback) {
        this.callback = callback;
        this.treeSearch = callback.getTreeSearch();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        drawRangeTree();
        drawHoverText();
    }

    private void drawHoverText() {
        if (hoveredShape != null && hoveredText != null) {

            double xPos = hoveredShape.getBounds().getMaxX();
            double yPos = hoveredShape.getBounds().getMinY();

            Rectangle2D background = new Rectangle2D.Double(xPos, yPos, 100, 20);
            Graphics2D g2D = (Graphics2D) g;
            g2D.setColor(Color.DARK_GRAY);
            ((Graphics2D) g).fill(background);

            g.setColor(Color.WHITE);
            g.drawString(hoveredText, (int) xPos + 5, (int) yPos + 15);

        }
    }

    public void drawRangeTree() {
        if (treeSearch.getRangeTree() != null) {
            RangeTreeNode root = treeSearch.getRangeTree().getRoot();

            Dimension panelSize = getSize();
            drawNode((int) (panelSize.getWidth() / 2), 0, root, null,
                (int) panelSize.getWidth());
        }
    }

    public void drawNode(int parentX, int parentY, RangeTreeNode node, Boolean left, int width) {
        if (node != null) {
            String value = String.valueOf(node.getValue().intValue());
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
            drawNode(myX, myY, node.getLeft(), true, width / 2);
            drawNode(myX, myY, node.getRight(), false, width / 2);
            visiblePoints
                .put(drawCircleWithLabel(value, myX, myY,
                        !node.getAssociatedTree().getRoot().isLeaf()),
                    node);
        }

    }

    private void drawLine(int x, int y, int x1, int y1) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 2);

        polyline.moveTo(x + 15, y + 15);
        polyline.lineTo(x1 + 15, y1 + 15);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.BLACK);
        g2D.draw(polyline);
    }

    private Shape drawCircleWithLabel(String label, final int x, final int y, boolean hasChildren) {

        Ellipse2D s = new Ellipse2D.Double((double) x, (double) y, 30, 30);


        g.setColor(Color.yellow);
        Graphics2D g2D = (Graphics2D) g;

        if (!hasChildren) {
            g2D.setColor(Color.YELLOW);
        } else {
            g2D.setColor(Color.CYAN);
        }
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

