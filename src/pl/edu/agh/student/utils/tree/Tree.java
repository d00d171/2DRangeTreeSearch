package pl.edu.agh.student.utils.tree;

import pl.edu.agh.student.utils.node.MyNode;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class Tree {

    private MyNode root;

    public Tree() {
    }

    public MyNode findSplitNode(int a, int b) {

        if (root == null) {
            return null;
        }

        MyNode v = root;
        BigDecimal bigA = BigDecimal.valueOf(a);
        BigDecimal bigB = BigDecimal.valueOf(b);

        while (!v.isLeaf() && (bigB.compareTo(v.getValue()) <= 0
            || bigA.compareTo(v.getValue()) > 0)) {

            if (bigA.compareTo(v.getValue()) <= 0) {
                v = v.getLeft();
            } else {
                v = v.getRight();
            }
        }

        return v;
    }

    public List<MyNode> oneDimRangeQuery(int a, int b) {

        BigDecimal bigA = BigDecimal.valueOf(a);
        BigDecimal bigB = BigDecimal.valueOf(b);

        List<MyNode> result = new LinkedList<MyNode>();

        MyNode splitNode = findSplitNode(a, b);
        MyNode tmpNode = splitNode;


        if (checkIfInRange(splitNode.getValue(), bigA, bigB)) {
            result.add(splitNode);
        }

        if (!splitNode.isLeaf()) {

            tmpNode = splitNode.getLeft();

            while (tmpNode != null && !tmpNode.isLeaf()) {

                if (checkIfInRange(tmpNode.getValue(), bigA, bigB)) {
                    result.add(tmpNode);
                }

                if (bigA.compareTo(tmpNode.getValue()) <= 0) {
                    reportSubtree(tmpNode.getRight(), result);
                    tmpNode = tmpNode.getLeft();
                } else {
                    tmpNode = tmpNode.getRight();
                }
            }

            if (tmpNode != null && checkIfInRange(tmpNode.getValue(), bigA, bigB)) {
                result.add(tmpNode);
            }

            tmpNode = splitNode.getRight();

            while (tmpNode != null && !tmpNode.isLeaf()) {

                if (checkIfInRange(tmpNode.getValue(), bigA, bigB)) {
                    result.add(tmpNode);
                }

                if (bigB.compareTo(tmpNode.getValue()) > 0) {
                    reportSubtree(tmpNode.getLeft(), result);
                    tmpNode = tmpNode.getRight();
                } else {
                    tmpNode = tmpNode.getLeft();
                }
            }

            if (tmpNode != null && checkIfInRange(tmpNode.getValue(), bigA, bigB)) {
                result.add(tmpNode);
            }

        }

        return result;
    }

    public boolean checkIfInRange(BigDecimal value, BigDecimal a, BigDecimal b) {
        return (value.compareTo(a) >= 0 && value.compareTo(b) <= 0);
    }

    public void reportSubtree(MyNode tree, List<MyNode> result) {
        if (tree != null) {
            result.add(tree);
            reportSubtree(tree.getLeft(), result);
            reportSubtree(tree.getRight(), result);
        }
    }

    public MyNode getRoot() {
        return root;
    }

    public void setRoot(MyNode root) {
        this.root = root;
    }

    @Override public String toString() {
        return "BinarySearchTree{" +
            "root=" + root +
            '}';
    }
}
