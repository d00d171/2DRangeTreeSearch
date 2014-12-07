package pl.edu.agh.student.gui;


import pl.edu.agh.student.utils.common.MyPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.Set;

public class PointsPanel extends JPanel {

    protected void drawPoints(java.util.List<MyPoint> points, Graphics g) {
        for (MyPoint point : points) {
            draw(new Point(point.getX().intValue(), point.getY().intValue()), g);
        }
    }

    protected void drawPoints(Set<MyPoint> points, Graphics g) {
        for (MyPoint point : points) {
            draw(new Point(point.getX().intValue(), point.getY().intValue()), g);
        }
    }

    protected void draw(Point point, Graphics g) {
        int r = 3;
        int x = (int) point.x;
        int y = (int) point.y;
        g.fillOval(x - r, y - r, r + r, r + r);
    }

    protected void drawLine(int x, int y, int x1, int y1, Graphics g) {
        GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 2);

        polyline.moveTo(x, y);
        polyline.lineTo(x1, y1);

        Graphics2D g2D = (Graphics2D) g;
        g2D.draw(polyline);
    }

}
