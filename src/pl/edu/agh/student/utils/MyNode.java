package pl.edu.agh.student.utils;

import java.math.BigDecimal;

public abstract class MyNode {

    private int id;

    private static int idSeed = 0;

    private BigDecimal value;

    public MyNode(BigDecimal value) {
        this.id = getNextId();
        this.value = value;
    }

    private int getNextId() {
        return idSeed++;
    }

    public abstract MyNode getLeft();

    public abstract MyNode getRight();

    public int getId() {
        return id;
    }

    public boolean isLeaf() {
        return getLeft() == null && getRight() == null;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override public String toString() {
        return "Node{" +
            "value=" + value +
            '}';
    }
}
