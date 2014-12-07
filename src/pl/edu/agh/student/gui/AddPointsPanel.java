package pl.edu.agh.student.gui;

import pl.edu.agh.student.TreeSearch;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddPointsPanel extends PointsPanel {

    private TreeSearch treeSearch;
    private Graphics g;

    public void setTreeSearch(TreeSearch treeSearch) {
        this.treeSearch = treeSearch;
    }

    public void addPoint(int x, int y) {
        treeSearch.addPoint(x, y);
    }

    public AddPointsPanel() {
        this.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {

            }

            @Override public void mousePressed(MouseEvent e) {
                addPoint(e.getX(), e.getY());
                repaint();
            }

            @Override public void mouseReleased(MouseEvent e) {

            }

            @Override public void mouseEntered(MouseEvent e) {

            }

            @Override public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        drawGrid();
        drawPoints(treeSearch.getPoints(), g);
    }

    private void drawGrid() {
        double w = getSize().getWidth();
        double h = getSize().getHeight();

        double tmpW = 0;
        double tmpH = 0;

        double wHop = 50;
        double hHop = 50;

        while (tmpW <= w) {
            drawLine((int) tmpW, 0, (int) tmpW, (int) h, g);
            tmpW += wHop;
        }

        while (tmpH <= h) {
            drawLine(0, (int) tmpH, (int) w, (int) tmpH, g);
            tmpH += hHop;
        }
    }

}
