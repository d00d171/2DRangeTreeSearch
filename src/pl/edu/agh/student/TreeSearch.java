package pl.edu.agh.student;

import pl.edu.agh.student.gui.App;
import pl.edu.agh.student.utils.common.MyPoint;
import pl.edu.agh.student.utils.tree.RangeTree;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TreeSearch {

    private List<MyPoint> points;

    private RangeTree rangeTree;

    public TreeSearch() {
        points = new LinkedList<MyPoint>();
    }

    public void addPoint(int x, int y) {
        points.add(new MyPoint(BigDecimal.valueOf(x), BigDecimal.valueOf(y)));
    }

    public void buildRangeTree() {
        rangeTree = new RangeTree(points);
    }

    public void clear() {
        points.clear();
        rangeTree.clear();
    }

    public Set<MyPoint> search(int x, int x1, int y, int y1) {
        Set<MyPoint> result = rangeTree.twoDimRangeQuery(x, x1, y, y1);
        return result;
    }

    public RangeTree getRangeTree() {
        return rangeTree;
    }

    public List<MyPoint> getPoints() {
        return points;
    }

    public static void main(String[] args) {
        App app = new App();
    }

    @Override public String toString() {
        return "TreeSearch{" +
            "points=" + points +
            ", rangeTree=" + rangeTree +
            '}';
    }
}
