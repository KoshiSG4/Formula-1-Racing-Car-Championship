/**Referred to:
 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDialogRunnerProject/src/components/ListDialogRunner.java
 * https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TablePrintDemoProject/src/components/TablePrintDemo.java
 **/

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddRace extends JDialog {

    private static AddRace addRace;
    private static String value = "";
    private static JTable table1;
    private static JTable table2;
    private Formula1ChampionshipManager formula1ChampionshipManager;

    //setup and show the table.
    public static JTable showTable(Component frameComp,
                                   Component locationComp,
                                   String labelText,
                                   String title,
                                   TableModel tableModel){
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        addRace = new AddRace(frame, locationComp, labelText, title, tableModel);
        addRace.setVisible(true);
        return table1;
    }

    private AddRace(Frame frame, Component locationComp, String labelText, String title, TableModel tableModel){
        super(frame,title,true);

        // create and initialize the buttons
        JButton addRace = new JButton("Add A Random Race");

        JButton close = new JButton("Close");

        //buttons pane
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createRigidArea(new Dimension(0,5)));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(addRace);
        buttonPane.add(Box.createRigidArea(new Dimension(0,5)));

        //close button
        JPanel closePane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10,30,10,10));
        closePane.add(close);
        close.add(Box.createRigidArea(new Dimension(50,20)));

        //assigning table
        table1 = new JTable(new AddRace.DriverStatTableModel ());
        table1.setPreferredScrollableViewportSize(new Dimension(840,400));

        table2 = new JTable(new AddRace.RaceTableModel ());
        table2.setPreferredScrollableViewportSize(new Dimension(840,400));

        addRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RaceTableModel model = (RaceTableModel) table2.getModel();
                model.refresh();

                DriverStatTableModel model1 = (DriverStatTableModel) table1.getModel();
                model1.refresh();
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRace.addRace.setVisible(false);
            }
        });

        JScrollPane randomRaceTable = new JScrollPane(table2);
        randomRaceTable.setPreferredSize(new Dimension(840,200));
        randomRaceTable.setAlignmentX(Component.CENTER_ALIGNMENT);

        JScrollPane statisticsTable = new JScrollPane(table1);
        statisticsTable.setPreferredSize(new Dimension(840,200));
        statisticsTable.setAlignmentX(Component.CENTER_ALIGNMENT);

        //create container and add the table name label
        JPanel mainPane = new JPanel();
        JLabel driverStatistics = new JLabel("Random Race Generator");
        driverStatistics.setFont(new Font("Arial",Font.BOLD,20));
        driverStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPane.add(driverStatistics);
        mainPane.setLayout(new BoxLayout(mainPane,BoxLayout.PAGE_AXIS));

        //setting up the layout and adding statisticsTable scroll pane and closePane panel
        JPanel tablePane = new JPanel();
        tablePane.setLayout(new BoxLayout(tablePane,BoxLayout.PAGE_AXIS));
        tablePane.add(Box.createRigidArea(new Dimension(0,5)));
        JLabel label1 = new JLabel("Random Race");
        tablePane.add(label1);
        mainPane.add(Box.createRigidArea(new Dimension(0,10)));
        tablePane.add(randomRaceTable);
        JLabel label = new JLabel("Driver Statistics Table");
        tablePane.add(label);
        mainPane.add(Box.createRigidArea(new Dimension(0,5)));
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
    class RaceTableModel extends AbstractTableModel {
        private String[] columnNames = {"Date",
                "Name",
                "Position",
                "Points",
                "Status(Finished('Y')/Unfinished('N')"};

        private Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();

        private ArrayList<Race> races = new ArrayList<>(formula1ChampionshipManager.randomRace());

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
           // System.out.println("Address get val "+ races);
            Race race = races.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return race.getRaceDate();
                case 1:
                    return race.getDriverName();
                case 2:
                    return race.getDriverWonPosition();
                case 3:
                    return race.getNumberOfPoints();
                case 4:
                    return race.getRaceStatus();
            }

            return null;
        }

        public void refresh(){
            races = new ArrayList<>(formula1ChampionshipManager.randomRace());
           // System.out.println("22"+races);
            fireTableDataChanged();
        }
    }

    class DriverStatTableModel extends AbstractTableModel {
        private String[] columnNames = {"Name",
                "Team",
                "1st Positions",
                "2nd Positions",
                "3rd Positions",
                "Total Points",
                "Number of Races"};

        private Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();

        private ArrayList<Formula1Driver> formula1Drivers = new ArrayList<>(formula1ChampionshipManager.loadDataFromTheFile());

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
                case 5: return formula1Driver.getNumberOfTotalPoints();
                case 6: return formula1Driver.getRaceCount();
            }

            return null;
        }
        public void refresh(){
            formula1Drivers = new ArrayList<>(formula1ChampionshipManager.loadDataFromTheFile());
            fireTableDataChanged();
        }
    }
}
