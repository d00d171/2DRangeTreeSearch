package pl.edu.agh.student.utils.tree;

import pl.edu.agh.student.utils.node.AssociatedTreeNode;
import pl.edu.agh.student.utils.common.MyPoint;
import pl.edu.agh.student.utils.common.Utils;

import java.util.List;

public class AssociatedTree extends Tree {

    private AssociatedTreeNode root;

    public AssociatedTree(List<MyPoint> points) {
        Utils.sortByY(points);
        this.root = buildTree(points);
        super.setRoot(root);
    }

    private AssociatedTreeNode buildTree(List<MyPoint> points) {

        if (points.size() == 0) {
            return null;
        }

        if (points.size() == 1) {
            return new AssociatedTreeNode(points.get(0).getY(), null, null, points.get(0));
        }

        int medianIndex = Utils.getMedianIndex(points, false);

        return new AssociatedTreeNode(points.get(medianIndex).getY(), buildTree(
            Utils.pointsToLeftOf(points, medianIndex)), buildTree(
            Utils.pointsToRightOf(points, medianIndex)), points.get(medianIndex));
    }

    public AssociatedTreeNode getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "AssociatedTree{" +
            "root=" + root +
            '}';
    }

}
