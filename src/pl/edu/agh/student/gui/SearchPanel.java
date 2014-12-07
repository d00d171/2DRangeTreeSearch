package pl.edu.agh.student.gui;

import pl.edu.agh.student.utils.MyNode;

import java.awt.*;
import java.util.LinkedList;

public class SearchPanel extends BinaryTreePanel {

    private Graphics g;
    private java.util.List<MyNode> activeNodes = new LinkedList<MyNode>();
    private MyNode specialNode;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        drawActiveNodes();
    }

    public void addActiveNode(MyNode node) {
        this.activeNodes.add(node);
    }

    public void setSpecialNode(MyNode specialNode) {
        this.specialNode = specialNode;
    }

    public void addActiveNodes(java.util.List<MyNode> nodes) {
        this.activeNodes.addAll(nodes);
    }

    public void clear() {
        super.clear();
        activeNodes.clear();
        specialNode = null;
    }

    private void drawActiveNodes() {

        for (MyNode activeNode : activeNodes) {
            drawNode(activeNode, Color.GREEN);
        }

        if (specialNode != null) {
            drawNode(specialNode, Color.RED);
        }
    }

    private void drawNode(MyNode activeNode, Color c) {
        if (nodesPositions.containsKey(activeNode.getId())) {
            Position p = nodesPositions.get(activeNode.getId());
            drawCircleWithLabel(activeNode.getValue().toString(), p.x, p.y, c);
        } else if (activeNode != null) {
            System.out.println("Doesn't exist " + activeNode.getId());
        }
    }
}
