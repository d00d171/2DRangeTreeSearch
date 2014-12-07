package pl.edu.agh.student.gui;

import pl.edu.agh.student.TreeSearch;
import pl.edu.agh.student.utils.MyPoint;

import javax.swing.*;
import java.awt.*;

public class AddPointsPanel extends JPanel {

    private TreeSearch treeSearch;
    private Graphics g;

    public void setTreeSearch(TreeSearch treeSearch) {
        this.treeSearch = treeSearch;
    }

    public void addPoint(int x, int y) {
        treeSearch.addPoint(x, y);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        drawPoints(treeSearch.getPoints());

    }

    private void drawPoints(java.util.List<MyPoint> points) {
        for (MyPoint point : points) {
            draw(new Point(point.getX().intValue(), point.getY().intValue()));
        }
    }

    public void draw(Point point) {
        int r = 3;
        int x = (int) point.x;
        int y = (int) point.y;
        g.fillOval(x - r, y - r, r + r, r + r);
    }

}
