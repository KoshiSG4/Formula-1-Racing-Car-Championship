/**referred to:  https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/components/ListDialogRunnerProject/src/components/ListDialog.java
**/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JPanel{

    private static JFrame frame;
    static TableModel tableModel;

    public static JPanel GUI(){
        //create main panel of the frame
        JPanel mainPanel = new JPanel();
        JLabel title = new JLabel("Formula 1 Championship");
        title.setFont(new Font("Britannic Bold",Font.BOLD,40));
        title.setBorder(new EmptyBorder(30,10,0,10));

        //create Driver Statistics button
        JButton driverStatistics = new JButton("Driver Statistics");
        driverStatistics.setFont(new Font("Arial",Font.BOLD,17));
        driverStatistics.setBackground(Color.decode("#127380"));
        driverStatistics.setForeground(Color.BLACK);
        driverStatistics.add(Box.createRigidArea(new Dimension(280,20)));
        driverStatistics.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable details = DisplayDriverStatistics.showTable(frame,driverStatistics,"Display Drivers", "Drivers Table", tableModel);

            }
        });

        //create Add Race button
        JButton addRace = new JButton("Add Race");
        addRace.setFont(new Font("Arial",Font.BOLD,17));
        addRace.setBackground(Color.decode("#127380"));
        addRace.setForeground(Color.BLACK);
        addRace.add(Box.createRigidArea(new Dimension(280,20)));
        addRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable addRaceDetails = AddRace.showTable(frame,addRace,"Random Races", "Generate A Random Race",tableModel);
            }
        });

        //create Display Completed Races button
        JButton displayCompletedRaces = new JButton("Display Completed Races");
        displayCompletedRaces.setFont(new Font("Arial",Font.BOLD,17));
        displayCompletedRaces.setBackground(Color.decode("#127380"));
        displayCompletedRaces.setForeground(Color.BLACK);
        displayCompletedRaces.add(Box.createRigidArea(new Dimension(280,20)));
        displayCompletedRaces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable showRace = DisplayCompletedRaces.showTable(frame,displayCompletedRaces,"Completed Races", "Completed Race Details",tableModel);
            }
        });

        //create Search a Driver button
        JButton search = new JButton("Search a Driver");
        search.setFont(new Font("Arial",Font.BOLD,17));
        search.setBackground(Color.decode("#127380"));
        search.setForeground(Color.BLACK);
        search.add(Box.createRigidArea(new Dimension(280,20)));
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable searchDriver = SearchADriver.showTable(frame,search,"Search Driver", "Search A Driver",tableModel);
            }
        });

        //create Close button
        JButton close = new JButton("Close");
        close.setFont(new Font("Arial",Font.BOLD,17));
        close.setBackground(Color.decode("#4a1c1c"));
        close.setForeground(Color.WHITE);
        close.add(Box.createRigidArea(new Dimension(150,20)));
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
        title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        driverStatistics.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        addRace.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        displayCompletedRaces.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        search.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        close.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        //add labels and buttons to the main panel
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(50)); // extra space
        mainPanel.add(driverStatistics);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(addRace);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(displayCompletedRaces);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(search);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(close);

        return mainPanel;

    }

    //create the frame and display
    public static void createAndShowGUI(){
        frame = new JFrame("Formula 1 Championship Manager");
        frame.setBounds(250,10,900,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent newContentPane = GUI();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.setVisible(true);
    }

}
