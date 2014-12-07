package pl.edu.agh.student.utils.tree;

import pl.edu.agh.student.utils.common.MyPoint;
import pl.edu.agh.student.utils.common.Utils;
import pl.edu.agh.student.utils.node.AssociatedTreeNode;
import pl.edu.agh.student.utils.node.MyNode;
import pl.edu.agh.student.utils.node.RangeTreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RangeTree extends Tree {

    private RangeTreeNode root;
    private List<MyPoint> allPoints;

    public RangeTree(List<MyPoint> points) {
        Utils.sortByX(points);
        allPoints = points;
        root = buildTree(points);
        super.setRoot(root);
    }

    private RangeTreeNode buildTree(List<MyPoint> points) {

        if (points.size() == 0) {
            return null;
        }

        if (points.size() == 1) {
            MyPoint p = points.get(0);
            return new RangeTreeNode(p.getX(), null, null,
                buildAssociatedTree(p));
        }

        int medianIndex = Utils.getMedianIndex(points, true);
        MyPoint medianPoint = points.get(medianIndex);

        return new RangeTreeNode(medianPoint.getX(),
            buildTree(Utils.pointsToLeftOf(points, medianIndex)),
            buildTree(Utils.pointsToRightOf(points, medianIndex)),
            buildAssociatedTree(medianPoint));
    }

    private AssociatedTree buildAssociatedTree(MyPoint point) {
        return new AssociatedTree(Utils.getPointsByXValue(allPoints, point));
    }

    public Set<MyPoint> twoDimRangeQuery(int x, int x1, int y, int y1) {
        List<MyNode> matchingXNodes = oneDimRangeQuery(x, x1);

        List<MyNode> matchingYNodes = new LinkedList<MyNode>();
        for (MyNode node : matchingXNodes) {
            RangeTreeNode rtNode = (RangeTreeNode) node;
            matchingYNodes.addAll(rtNode.getAssociatedTree().oneDimRangeQuery(y, y1));
        }

        Set<MyPoint> result = new HashSet<MyPoint>();
        for (MyNode node : matchingYNodes) {
            result.add(((AssociatedTreeNode) node).getPoint());
        }

        return result;
    }

    public RangeTreeNode getRoot() {
        return root;
    }

    public void clear() {
        root = null;
    }

    @Override public String toString() {
        return "RangeTree{" +
            "root=" + root +
            '}';
    }
}
