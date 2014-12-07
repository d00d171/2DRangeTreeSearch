package pl.edu.agh.student.utils.node;

import pl.edu.agh.student.utils.common.MyPoint;

import java.math.BigDecimal;

public class AssociatedTreeNode extends MyNode {

    private MyPoint point;

    private AssociatedTreeNode left;

    private AssociatedTreeNode right;

    public AssociatedTreeNode(BigDecimal value, AssociatedTreeNode left, AssociatedTreeNode right,
        MyPoint point) {
        super(value);
        this.left = left;
        this.right = right;
        this.point = point;
    }

    @Override
    public AssociatedTreeNode getLeft() {
        return left;
    }

    @Override
    public AssociatedTreeNode getRight() {
        return right;
    }

    public MyPoint getPoint() {
        return point;
    }

    @Override public String toString() {
        return "AssociatedTreeNode{" +
            "point=" + point +
            ", left=" + left +
            ", right=" + right +
            "} => " + super.toString();
    }
}
