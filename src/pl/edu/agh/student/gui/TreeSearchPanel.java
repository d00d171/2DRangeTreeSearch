package pl.edu.agh.student.gui;

import pl.edu.agh.student.gui.utils.Position;
import pl.edu.agh.student.utils.node.MyNode;
import pl.edu.agh.student.utils.node.RangeTreeNode;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class TreeSearchPanel extends BinaryTreePanel {

    private Graphics g;
    private java.util.List<MyNode> activeNodes = new LinkedList<MyNode>();
    private MyNode specialNode;
    private App callback;

    private String hoveredText = null;
    private Shape hoveredShape = null;

    public TreeSearchPanel() {
        this.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {

            }

            @Override public void mousePressed(MouseEvent e) {
                if (callback != null) {
                    for (Shape s : visiblePoints.keySet()) {
                        if (s.contains(e.getPoint())) {
                            MyNode node = visiblePoints.get(s);
                            callback.showAssociatedSearchTree(
                                ((RangeTreeNode) node).getAssociatedTree(),
                                activeNodes.contains(node));
                        }
                    }
                }
            }

            @Override public void mouseReleased(MouseEvent e) {

            }

            @Override public void mouseEntered(MouseEvent e) {

            }

            @Override public void mouseExited(MouseEvent e) {

            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override public void mouseDragged(MouseEvent e) {

            }

            @Override public void mouseMoved(MouseEvent e) {

                if (callback != null) {
                    boolean wasVisible = hoveredText != null && hoveredShape != null ? true : false;

                    for (Shape s : visiblePoints.keySet()) {
                        if (s.contains(e.getPoint())) {
                            hoveredShape = s;
                            extractHoverText((RangeTreeNode) visiblePoints.get(s));
                            repaint();
                            return;
                        }
                    }

                    if (wasVisible) {
                        hoveredShape = null;
                        hoveredText = null;
                        repaint();
                    }
                }

            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        drawActiveNodes();
        drawHoverText();
    }

    public void initialize(App callback, boolean subtree) {
        super.initialize(callback.getTreeSearch().getRangeTree(), subtree);
        this.callback = callback;
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

    public void setSpecialNode(MyNode specialNode) {
        this.specialNode = specialNode;
    }

    public void addActiveNodes(java.util.List<MyNode> nodes) {
        this.activeNodes.addAll(nodes);
    }

    public void addActiveNode(MyNode node) {
        this.activeNodes.add(node);
    }

    public void clear() {
        super.clear();
        hoveredText = null;
        hoveredShape = null;
        activeNodes.clear();
        specialNode = null;
    }

    private void drawActiveNodes() {

        if (specialNode != null) {
            drawNode(specialNode, Color.RED);
        }

        for (MyNode activeNode : activeNodes) {
            drawNode(activeNode, Color.GREEN);
        }

    }

    private void drawNode(MyNode activeNode, Color c) {
        if (nodesPositions.containsKey(activeNode.getId())) {
            Position p = nodesPositions.get(activeNode.getId());
            drawCircleWithLabel(activeNode.getValue().toString(), p.x, p.y, showBold(activeNode),
                c);
        } else if (activeNode != null) {
            System.out.println("Doesn't exist " + activeNode.getId());
        }
    }
}
