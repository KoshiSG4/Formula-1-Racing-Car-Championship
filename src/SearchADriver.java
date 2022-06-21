/**Referred to:
 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDialogRunnerProject/src/components/ListDialogRunner.java
 * https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TablePrintDemoProject/src/components/TablePrintDemo.java
 * https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/TableFilterDemoProject/src/components/TableFilterDemo.java
 **/

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;

public class SearchADriver extends JDialog {

    private static SearchADriver searchDriver;
    private static JTable table;

    //setup and show the table.
    public static JTable showTable(Component frameComp,
                                   Component locationComp,
                                   String labelText,
                                   String title,
                                   TableModel tableModel){
        Frame frame = JOptionPane.getFrameForComponent(frameComp);
        searchDriver = new SearchADriver(frame, locationComp, labelText, title, tableModel);
        searchDriver.setVisible(true);
        return table;
    }

    private SearchADriver(Frame frame, Component locationComp, String labelText, String title, TableModel tableModel){
        super(frame,title,true);

        // create buttons
        JButton search = new JButton("Search");

        JButton close = new JButton("Close");

        //create text field
        JTextField searchName = new JTextField();

        //buttons pane
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.LINE_AXIS));
        buttonPane.add(Box.createRigidArea(new Dimension(0,5)));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(search);
        buttonPane.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPane.add(searchName);
        buttonPane.add(Box.createRigidArea(new Dimension(0,5)));

        //close button
        JPanel closePane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10,30,10,10));
        closePane.add(close);
        close.add(Box.createRigidArea(new Dimension(50,20)));

        //assigning table
        table = new JTable(new SearchADriver.SearchDriverTableModel ());
        table.setPreferredScrollableViewportSize(new Dimension(840,400));
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel()); //creating row sorter
        table.setRowSorter(sorter);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchName.getText().toUpperCase(Locale.ROOT);
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchADriver.searchDriver.setVisible(false);
            }
        });

        JScrollPane searchedDriverStatTable = new JScrollPane(table);
        searchedDriverStatTable.setPreferredSize(new Dimension(840,400));
        searchedDriverStatTable.setAlignmentX(Component.CENTER_ALIGNMENT);

        //create container and add the table name label
        JPanel mainPane = new JPanel();
        JLabel driverStatistics = new JLabel("Search A Driver");
        driverStatistics.setFont(new Font("Arial",Font.BOLD,20));
        driverStatistics.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPane.add(driverStatistics);
        mainPane.setLayout(new BoxLayout(mainPane,BoxLayout.PAGE_AXIS));

        //setting up the layout and adding searchedDriverStatTable scroll pane and closePane panel
        JPanel tablePane = new JPanel();
        tablePane.setLayout(new BoxLayout(tablePane,BoxLayout.PAGE_AXIS));
        tablePane.add(Box.createRigidArea(new Dimension(0,5)));
        JLabel label1 = new JLabel("Race Details");
        tablePane.add(label1);
        mainPane.add(Box.createRigidArea(new Dimension(0,10)));
        tablePane.add(searchedDriverStatTable);
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
    class SearchDriverTableModel extends AbstractTableModel {
        private String[] columnNames = {"Date",
                "Name",
                "Position",
                "Points",
                "Status(Finished('Y')/Unfinished('N')"};

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

//                }
//            }
            return null;
        }
    }
}
