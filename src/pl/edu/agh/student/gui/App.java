package pl.edu.agh.student.gui;

import pl.edu.agh.student.TreeSearch;
import pl.edu.agh.student.utils.common.MyPoint;
import pl.edu.agh.student.utils.tree.AssociatedTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class App extends JFrame {

    private App _this = this;
    private JTabbedPane tabbedPane;
    private AddPointsPanel addPointsPanel;
    private JButton createTreeButton;
    private JPanel mainPanel;
    private JButton clearButton;

    private JTextField xTextField;
    private JTextField x1TextField;
    private JTextField yTextField;
    private JTextField y1TextField;
    private JButton searchButton;
    private TreeSearchPanel treeSearchPanel;
    private TreeSearchPanel selectedSubtreePanel;
    private PointSearchPanel pointsSearchPanel;

    private TreeSearch treeSearch = new TreeSearch();

    public TreeSearch getTreeSearch() {
        return treeSearch;
    }

    public App() {
        super("2D Range tree search");
        setContentPane(mainPanel);
        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        treeSearch = new TreeSearch();
        addPointsPanel.setTreeSearch(treeSearch);
        prepareButtons();

        setVisible(true);
    }


    private void prepareButtons() {
        createTreeButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                treeSearch.buildRangeTree();
                treeSearchPanel.clear();
                treeSearchPanel.initialize(_this, false);
                tabbedPane.setSelectedIndex(1);
                selectedSubtreePanel.clear();
                pointsSearchPanel.clear();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                treeSearch.clear();
                treeSearchPanel.clear();
                selectedSubtreePanel.clear();
                pointsSearchPanel.clear();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                Integer x = getXValue();
                Integer x1 = getX1Value();
                Integer y = getYValue();
                Integer y1 = getY1Value();

                if (x != null && x1 != null && y != null && y1 != null) {
                    selectedSubtreePanel.clear();
                    treeSearchPanel.clear();

                    treeSearchPanel.initialize(_this, false);
                    treeSearchPanel.setSpecialNode(treeSearch.getRangeTree().findSplitNode(x, x1));
                    treeSearchPanel
                        .addActiveNodes(treeSearch.getRangeTree().oneDimRangeQuery(x, x1));
                    treeSearchPanel.repaint();

                    pointsSearchPanel.clear();
                    Set<MyPoint> points = treeSearch.search(x, x1, y, y1);
                    pointsSearchPanel.initialize(treeSearch.getPoints(), points, x, x1, y, y1);
                    pointsSearchPanel.repaint();

                    tabbedPane.setSelectedIndex(2);
                }
            }
        });
    }

    private Integer getXValue() {
        try {
            return Integer.valueOf(xTextField.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer getX1Value() {
        try {
            return Integer.valueOf(x1TextField.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer getYValue() {
        try {
            return Integer.valueOf(yTextField.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer getY1Value() {
        try {
            return Integer.valueOf(y1TextField.getText());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void showAssociatedSearchTree(AssociatedTree tree, boolean search) {
        selectedSubtreePanel.clear();
        selectedSubtreePanel.initialize(tree, true);

        if (search) {
            Integer y = getYValue();
            Integer y1 = getY1Value();

            if (y != null && y1 != null) {
                selectedSubtreePanel.setSpecialNode(tree.findSplitNode(y, y1));
                selectedSubtreePanel.addActiveNodes(tree.oneDimRangeQuery(y, y1));
            }
        }

        selectedSubtreePanel.repaint();
    }

}

