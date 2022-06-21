/**Referred to:
 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDialogRunnerProject/src/components/ListDialogRunner.java
 * https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TablePrintDemoProject/src/components/TablePrintDemo.java
 **/

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DisplayDriverStatistics extends JDialog {
    private static DisplayDriverStatistics driverStats;
    private static JTable table;
    private JButton descendingOrderPoints;
    private JButton ascendingOrderPoints;
    private JButton descendingOrderOfFirsts;

    private Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();    //create new Formula1ChampionshipManager object
    private ArrayList<Formula1Driver> formula1Drivers = formula1ChampionshipManager.loadDataFromTheFile();  //create new Formula1Driver arraylist and assign driver details

    //setup and show the table.
    public static JTable showTable(Component frameComp,
                                   Component locationComp,
                                   String labelText,
                                   String title,
                                   TableModel tableModel){
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        driverStats = new DisplayDriverStatistics(frame, locationComp, labelText, title, tableModel);
        driverStats.setVisible(true);
        return table;
    }

    private DisplayDriverStatistics(Frame frame, Component locationComp, String labelText, String title, TableModel tableModel){
        super(frame,title,true);

        // create and initialize the buttons
        descendingOrderPoints = new JButton("Points(Descending Order)");

        ascendingOrderPoints = new JButton("Points(Ascending Order)");

        descendingOrderOfFirsts = new JButton("First Positions(Descending Order)");

        JButton close = new JButton("Close");

        //buttons pane
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.LINE_AXIS));
        JLabel sort = new JLabel("Sorting Options :");
        buttonPane.add(sort);
        buttonPane.add(Box.createRigidArea(new Dimension(30,0)));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0,30,10,10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(descendingOrderPoints);
        buttonPane.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPane.add(ascendingOrderPoints);
        buttonPane.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPane.add(descendingOrderOfFirsts);

        //close button
        JPanel closePane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(50,30,10,10));
        closePane.add(close);
        close.add(Box.createRigidArea(new Dimension(50,20)));

        //assigning table
        table = new JTable(new MyTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(840,400));

        descendingOrderPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                sorter.setComparator(5, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1.compareTo(o2);
                    }
                });
                table.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                sortKeys.add(new RowSorter.SortKey(5,SortOrder.DESCENDING));
                sorter.setSortKeys(sortKeys);
            }
        });

        ascendingOrderPoints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());

                sorter.setComparator(5, new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
//                        int int1 = o1.compareTo(o2);
                        return o1.compareTo(o2);
                    }
                });
                table.setRowSorter(sorter);
                List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                sortKeys.add(new RowSorter.SortKey(5,SortOrder.ASCENDING));
                sorter.setSortKeys(sortKeys);

            }
        });
        descendingOrderOfFirsts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
                table.setRowSorter(sorter);

                List<RowSorter.SortKey> sortKeys = new ArrayList<>();
                sortKeys.add(new RowSorter.SortKey(2,SortOrder.DESCENDING));
                sorter.setSortKeys(sortKeys);
            }
        });
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayDriverStatistics.driverStats.setVisible(false);
            }
        });
        JScrollPane statisticsTable = new JScrollPane(table);
        statisticsTable.setPreferredSize(new Dimension(840,400));
        statisticsTable.setAlignmentX(Component.CENTER_ALIGNMENT);

        //create container and add the table name label
        JPanel mainPane = new JPanel();
        JLabel driverStatistics = new JLabel("Driver Statistics");
        driverStatistics.setFont(new Font("Arial",Font.BOLD,20));
        driverStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPane.add(driverStatistics);
        mainPane.setLayout(new BoxLayout(mainPane,BoxLayout.PAGE_AXIS));
        mainPane.add(Box.createRigidArea(new Dimension(0,5)));

        //setting up the layout and adding statisticsTable scroll pane and closePane panel
        JPanel tablePane = new JPanel();
        tablePane.setLayout(new BoxLayout(tablePane,BoxLayout.PAGE_AXIS));
        tablePane.add(Box.createRigidArea(new Dimension(0,5)));
        tablePane.add(statisticsTable);
        tablePane.add(closePane);

        //adding button pane to main pane
        mainPane.add(buttonPane);
        mainPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //put everything together
        Container container = getContentPane();
        container.add(mainPane,BorderLayout.CENTER);
        container.add(tablePane,BorderLayout.PAGE_END);

        setLocationRelativeTo(locationComp);
        setSize(850,600);
        setLocation(280,50);
    }

    //creating and initializing the table
    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = {"Name",
                "Team",
                "1st Positions",
                "2nd Positions",
                "3rd Positions",
                "Total Points",
                "Number of Races"};

        private ArrayList<Formula1Driver> formula1Drivers = formula1ChampionshipManager.loadDataFromTheFile();

        public int getRowCount() {
            return formula1Drivers.size();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Formula1Driver formula1Driver = formula1Drivers.get(rowIndex);
            switch(columnIndex){
                case 0: return formula1Driver.getDriverName();
                case 1: return formula1Driver.getDriverTeam();
                case 2: return formula1Driver.getNumberOfFirstPositions();
                case 3: return formula1Driver.getNumberOfSecondPositions();
                case 4: return formula1Driver.getNumberOfThirdPositions();
                case 5:
                    return formula1Driver.getNumberOfTotalPoints();
                case 6: return formula1Driver.getRaceCount();
            }

            return null;
        }

    }
}

