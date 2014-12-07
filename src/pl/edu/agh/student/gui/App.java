package pl.edu.agh.student.gui;

import pl.edu.agh.student.TreeSearch;
import pl.edu.agh.student.utils.AssociatedTree;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class App extends JFrame {
    private JTabbedPane tabbedPane;
    private RangeTreePanel rangeTreePanel;
    private AddPointsPanel addPointsPanel;
    private JButton createTreeButton;
    private JPanel mainPanel;
    private JButton clearButton;

    private JTextField xTextField;
    private JTextField x1TextField;
    private JTextField yTextField;
    private JTextField y1TextField;
    private JButton searchButton;
    private BinaryTreePanel binaryTreePanel;
    private SearchPanel searchPanel;

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

        prepareRangeTreePanel();
        preparePointAddingPanel();
        prepareSearchPanel();
        prepareButtons();

        setVisible(true);
    }


    private void prepareButtons() {
        createTreeButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                treeSearch.buildRangeTree();
                searchPanel.clear();
                searchPanel.initialize(treeSearch.getRangeTree());
                rangeTreePanel.repaint();
                tabbedPane.setSelectedIndex(1);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                treeSearch.clear();
                rangeTreePanel.clear();
                binaryTreePanel.clear();
                searchPanel.clear();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                try {
                    Integer x = Integer.valueOf(xTextField.getText());
                    Integer x1 = Integer.valueOf(x1TextField.getText());
                    Integer y = Integer.valueOf(yTextField.getText());
                    Integer y1 = Integer.valueOf(y1TextField.getText());

                    //DO USUNIECIA JAK BEDZIE SKONCZONE
                    //searchPanel.addActiveNode(treeSearch.getSplitNode(x, x1));
                    searchPanel.clear();
                    searchPanel.initialize(treeSearch.getRangeTree());

                    treeSearch.search(x, x1, y, y1);

                    searchPanel.setSpecialNode(treeSearch.getSplitNode(x, x1));
                    searchPanel.addActiveNodes(treeSearch.getRangeQuery(x, x1));
                    //KONIEC DO USUNIECIA

                    searchPanel.repaint();

                } catch (NumberFormatException ex) {

                }
            }
        });
    }

    public void showRangeTree(AssociatedTree tree) {
        binaryTreePanel.initialize(tree);
        tabbedPane.setSelectedIndex(2);
    }

    private void repaintAll() {
        addPointsPanel.repaint();
        rangeTreePanel.repaint();
    }

    private void prepareSearchPanel() {

    }

    private void prepareRangeTreePanel() {
        rangeTreePanel.initialize(this);
    }

    private void preparePointAddingPanel() {

        addPointsPanel.setTreeSearch(treeSearch);

        addPointsPanel.addMouseListener(new MouseListener() {
            @Override public void mouseClicked(MouseEvent e) {

            }

            @Override public void mousePressed(MouseEvent e) {
                addPointsPanel.addPoint(e.getX(), e.getY());
                addPointsPanel.repaint();
            }

            @Override public void mouseReleased(MouseEvent e) {

            }

            @Override public void mouseEntered(MouseEvent e) {

            }

            @Override public void mouseExited(MouseEvent e) {

            }
        });

    }

}

