package pl.edu.agh.student.utils.node;

import pl.edu.agh.student.utils.tree.AssociatedTree;

import java.math.BigDecimal;

public class RangeTreeNode extends MyNode {

    private AssociatedTree associatedTree;

    private RangeTreeNode left;

    private RangeTreeNode right;

    public RangeTreeNode(BigDecimal value, RangeTreeNode left, RangeTreeNode right,
        AssociatedTree associatedTree) {
        super(value);
        this.left = left;
        this.right = right;
        this.associatedTree = associatedTree;
    }

    public AssociatedTree getAssociatedTree() {
        return associatedTree;
    }

    @Override
    public RangeTreeNode getLeft() {
        return left;
    }

    @Override
    public RangeTreeNode getRight() {
        return right;
    }

    @Override public String toString() {
        return super.toString() + " = > RangeTreeNode{" +
            "associatedTree=" + associatedTree +
            ", left=" + left +
            ", right=" + right +
            "}";
    }
}
