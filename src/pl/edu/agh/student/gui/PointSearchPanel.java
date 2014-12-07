package pl.edu.agh.student.gui;

import pl.edu.agh.student.utils.common.MyPoint;

import java.awt.*;
import java.util.List;
import java.util.Set;

public class PointSearchPanel extends PointsPanel {

    private Integer x;
    private Integer x1;
    private Integer y;
    private Integer y1;
    private List<MyPoint> points;
    Set<MyPoint> foundPoints;

    public void initialize(List<MyPoint> points, Set<MyPoint> foundPoints, int x, int x1, int y,
        int y1) {
        this.points = points;
        this.foundPoints = foundPoints;
        this.x = x;
        this.x1 = x1;
        this.y = y;
        this.y1 = y1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (points != null && foundPoints != null && x != null && x1 != null && y != null
            && y1 != null) {
            System.out.println(foundPoints);
            g.setColor(Color.BLACK);
            drawPoints(points, g);
            g.setColor(Color.BLUE);
            drawPoints(foundPoints, g);
            g.setColor(Color.RED);
            drawLine(x, y, x, y1, g);
            drawLine(x, y1, x1, y1, g);
            drawLine(x1, y1, x1, y, g);
            drawLine(x, y, x1, y, g);
        }
    }

    public void clear() {
        foundPoints = null;
        points = null;
        x = null;
        y = null;
        x1 = null;
        y1 = null;
        repaint();
    }

    ;

}
