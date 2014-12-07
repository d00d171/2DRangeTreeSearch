package pl.edu.agh.student.utils.common;

import java.math.BigDecimal;

public class MyPoint {

    private BigDecimal x;

    private BigDecimal y;

    public MyPoint(BigDecimal x, BigDecimal y) {
        this.x = x;
        this.y = y;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "MyPoint{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}
