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
import java.util.List;

public class DisplayCompletedRaces extends JDialog {
    private static DisplayCompletedRaces driverStats;
    private static JTable table;

    //setup and show the table.
    public static JTable showTable(Component frameComp,
                                   Component locationComp,
                                   String labelText,
                                   String title,
                                   TableModel tableModel){
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        driverStats = new DisplayCompletedRaces(frame, locationComp, labelText, title, tableModel);
        driverStats.setVisible(true);
        return table;
    }

    private DisplayCompletedRaces(Frame frame, Component locationComp, String labelText, String title, TableModel tableModel){
        super(frame,title,true);

        // create button
        JButton close = new JButton("Close");

        //buttons pane
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createRigidArea(new Dimension(30,0)));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0,30,10,10));
        buttonPane.add(Box.createHorizontalGlue());;

        //close button
        JPanel closePane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(50,30,10,10));
        closePane.add(close);
        close.add(Box.createRigidArea(new Dimension(50,20)));

        //assigning the table
        table = new JTable(new DisplayDriversTableModel());
        table.setPreferredScrollableViewportSize(new Dimension(840,400));

        //sort table data
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0,SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayCompletedRaces.driverStats.setVisible(false);
            }
        });

        JScrollPane statisticsTable = new JScrollPane(table);
        statisticsTable.setPreferredSize(new Dimension(840,400));
        statisticsTable.setAlignmentX(Component.CENTER_ALIGNMENT);

        //create container and add the table name label
        JPanel mainPane = new JPanel();
        JLabel driverStatistics = new JLabel("Completed Race Details");
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
    class DisplayDriversTableModel extends AbstractTableModel {
        private String[] columnNames = {"Date",
                "Name",
                "Positions",
                "Points",
                "Race Status(Finished('Y')/Unfinished('N')"};

        private Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();

        private ArrayList<Race> races = formula1ChampionshipManager.loadRaceDataFromTheFile();

        public int getRowCount() {
            return races.size();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
//            System.out.println(races);
            Race race = races.get(rowIndex);
            switch (columnIndex) {
                case 0: return race.getRaceDate();
                case 1: return race.getDriverName();
                case 2: return race.getDriverWonPosition();
                case 3: return race.getNumberOfPoints();
                case 4: return race.getRaceStatus();
            }

            return null;
        }
    }
}