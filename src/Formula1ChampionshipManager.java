import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

interface ChampionshipManager{
    void createANewDriver(ArrayList<Formula1Driver>  formula1Driver, ArrayList<Race> races );
    void deleteADriver(ArrayList<Formula1Driver>  formula1Driver,ArrayList<Race> races);
    void changeDriverTeam(ArrayList<Formula1Driver> formula1Driver,ArrayList<Race> races);
    void displayStatisticsOfTheDriver(ArrayList<Formula1Driver> formula1Driver);
    void displayFormula1DriverTable(ArrayList<Formula1Driver> formula1Driver);
    void addRaceDetails(ArrayList<Formula1Driver> formula1Driver,ArrayList<Race> races);
    void saveDataInAFile(ArrayList<Formula1Driver> formula1Driver,ArrayList<Race> races);
    ArrayList<Formula1Driver> loadDataFromTheFile();
}
public class Formula1ChampionshipManager implements ChampionshipManager {

    public static void main(String[] args){
        Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager(); //create new Formula1ChampionshipManager object
        ArrayList<Formula1Driver>  formula1Driver = new ArrayList<>();  //creating new arraylist of formula1driver objects
        formula1Driver = formula1ChampionshipManager.loadDataFromTheFile(); //assigning objects to formula1driver arraylist
        ArrayList<Race> races = formula1ChampionshipManager.loadRaceDataFromTheFile(); //create races arraylist and assigning Race objects to it.

        String option ="";
        Scanner scan = new Scanner(System.in);

        //console menu
        do{
            System.out.println("FORMULA 1 CHAMPIONSHIP MENU\n" +
                    "\n" +
                    "001 or CND:   Create a New Driver\n" +
                    "002 or DDT:   Delete a Driver from a Team\n" +
                    "003 or CDT:   Change the Driver's Team\n" +
                    "004 or DRS:   Display the Race Statistics of the Driver\n" +
                    "005 or DFT:   Display the Formula 1 Driver Table\n" +
                    "006 or ANR:   Add a New Completed Race Details\n" +
                    "007 or SPD:   Save Programme Data into a File\n" +
                    "008 or LPD:   Load Programme Data from the File\n" +
                    "009 or GUI:   Open GUI\n"+
                    "000 or EXT:   Exit from the programme\n" +
                    "Please Enter Your Option:");
            option = scan.nextLine();
            switch (option.toUpperCase()){
                case "001":
                case "CND"  :
                    System.out.println("Create a New Driver:\n");
                    formula1ChampionshipManager.createANewDriver(formula1Driver,races );
                    break;
                case "002":
                case "DDT"  :
                    System.out.println("Delete a Driver:\n");
                    formula1ChampionshipManager.deleteADriver(formula1Driver,races);
                    break;
                case "003":
                case "CDT"  :
                    System.out.println("Change the Driver's Team:\n");
                    formula1ChampionshipManager.changeDriverTeam(formula1Driver,races);
                    break;
                case "004":
                case "DRS"  :
                    System.out.println("Display the Race Statistics of the Driver:\n");
                    formula1ChampionshipManager.displayStatisticsOfTheDriver(formula1Driver);
                    break;
                case "005":
                case "DFT"  :
                    System.out.println("Display the Formula 1 Driver Table:\n");
                    formula1ChampionshipManager.displayFormula1DriverTable(formula1Driver);
                    break;
                case "006":
                case "ANR"  :
                    System.out.println("Add a New Completed Race Details:\n");
                    formula1ChampionshipManager.addRaceDetails(formula1Driver,races);
                    break;
                case "007":
                case "SPD"  :
                    System.out.println("Save Programme Data into a File:\n");
                    formula1ChampionshipManager.saveDataInAFile(formula1Driver,races);
                    break;
                case "008":
                case "LPD"  :
                    System.out.println("Load Programme Data from the File:\n");
                    formula1ChampionshipManager.loadDataFromTheFile();
                    break;
                case "000":
                case "EXT"  :
                    System.out.println("Exit from the programme:\n");
                    break;
                case "009"  :
                case "GUI"  :
                    System.out.println("Open GUI:\n");
                    GUI gui = new GUI();
                    gui.createAndShowGUI();
                    break;
                default:
                    System.out.println("Invalid Option.Please Try Again");
            }
        }while(!(option.equals("000")) && !(option.toUpperCase().equals("EXT")));
    }

    //Create a new driver
    public void createANewDriver(ArrayList<Formula1Driver> formula1Driver,ArrayList<Race> races){
        for(int i=0;i< formula1Driver.size();i++){
            System.out.println(formula1Driver.get(i));
        }
        try{
            char option = 'c';
            Scanner scan = new Scanner(System.in);
            while ((option != 'Q') && (option != 'q')) {
                if ((option == 'C') || (option == 'c')) {
                    Formula1Driver driver = new Formula1Driver();
                    driver.newDriverInformation();
                    formula1Driver.add(driver);
                    Set<String> driversSet = new HashSet<>();    //to avid a driver being in more than one team
                    for (int i = 0; i < formula1Driver.size(); i++) {
                        if (!(driversSet.add(formula1Driver.get(i).getDriverName()))) {
                            System.out.println("Sorry, " + formula1Driver.get(i).getDriverName() + " is associated with another team.Please try again.");
                                formula1Driver.remove(i);
                        }
                    }
                    HashSet<String> teamsSet = new HashSet<>();     //to avid a team having more than one driver
                    for (int i = 0; i < formula1Driver.size(); i++) {
                        if (!(teamsSet.add(formula1Driver.get(i).getDriverTeam()))) {
                            System.out.println("Sorry, the team you entered for " + formula1Driver.get(i).getDriverName() + " is exists.Please try again.");
                            formula1Driver.remove(i);
                        }
                    }
                    for(int i=0; i<formula1Driver.size();i++) {
                        System.out.println(formula1Driver.get(i));
                    }
                    System.out.println("Please enter 'C' if you want to continue adding a new driver or 'Q' to return to main menu: ");
                } else {
                    System.out.println("Invalid input.Please enter 'C' if you want to continue adding a new driver or 'Q' to return to main menu: ");
                }option = scan.next().charAt(0);
            }
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid input.Please try again.");
        }
        saveDataInAFile(formula1Driver,races); //to save changes
    }

    //delete a driver
    public void deleteADriver(ArrayList<Formula1Driver> formula1Driver,ArrayList<Race> races){
        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Please enter the name of the driver you want to delete: ");
            String driverName = scan.nextLine().toUpperCase();
            for(int i=0;i<formula1Driver.size();i++){
                System.out.println(formula1Driver.get(i).getDriverName());
                if(formula1Driver.get(i).getDriverName().equals(driverName)){
                    formula1Driver.remove(i);
                }
            }
            System.out.println("You have successfully deleted the diver "+ driverName+ "and the "+ driverName+"'s team");
        }catch (InputMismatchException e){
            System.out.println("You have entered an invalid input.Please try again.");
        }
        saveDataInAFile(formula1Driver,races); //to save changes
    }

    //change the driver's team
    public void changeDriverTeam(ArrayList<Formula1Driver> formula1Driver,ArrayList<Race> races){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the driver you want to change the team:");
        String driverName = scan.nextLine().toUpperCase();
        System.out.println("Please enter the team name you want to assign the driver:");
        String teamName = scan.nextLine().toUpperCase();
        ArrayList<String> temporaryDrivers = new ArrayList<>();
        ArrayList<String> temporaryDriverTeams = new ArrayList<>();
        for(int i=0;i< formula1Driver.size();i++){
            if(formula1Driver.get(i).getDriverTeam().equals(teamName)){
                temporaryDrivers.add(formula1Driver.get(i).getDriverName());
                formula1Driver.remove(i);
            }
            if(formula1Driver.get(i).getDriverName().equals(driverName)){
                formula1Driver.get(i).setDriverTeam(teamName);
                temporaryDriverTeams.add(formula1Driver.get(i).getDriverTeam());
            }
        }
        System.out.println("You have successfully assigned "+driverName+" into "+teamName+" team.");
        saveDataInAFile(formula1Driver,races); //to save changes
//        for(int i=0; i<formula1Driver.size();i++) {
//            System.out.println(formula1Driver.get(i));
//        }
//        System.out.println(temporaryDrivers+" "+temporaryDriverTeams);
    }

    //display race statistics of a selected driver
    public void displayStatisticsOfTheDriver(ArrayList<Formula1Driver> formula1Driver){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the driver you want to view race statistics: ");
        String driverName = scan.nextLine().toUpperCase();
        for(int i=0; i<formula1Driver.size();i++){
            if(formula1Driver.get(i).getDriverName().equals(driverName)){
                System.out.println(formula1Driver.get(i).toString2());
            }
        }
    }

    //display all the drivers who are in the Formula 1 Championship Manager
    public void displayFormula1DriverTable(ArrayList<Formula1Driver> formula1Driver) {

        ArrayList<Formula1Driver> sortedDrivers = new ArrayList<>(formula1Driver.size()); //creating a copy of formula1driver object arraylist using a copy constructor
        for (Formula1Driver driver : formula1Driver) {
            sortedDrivers.add(new Formula1Driver(driver));
        }

        //sort Formula 1 drivers in descending order according to the number of first positions won within the season
        Collections.sort(sortedDrivers, new Comparator<Formula1Driver>() {
            @Override
            public int compare(Formula1Driver o1, Formula1Driver o2) {
                if (o1.getNumberOfTotalPoints() == o2.getNumberOfTotalPoints()) {   // check whether two drivers has the same number of first positions
                    return o2.getNumberOfFirstPositions() - o1.getNumberOfFirstPositions(); //sort according to the number of first positions
                } else {
                    return o2.getNumberOfTotalPoints() - o1.getNumberOfTotalPoints(); //sort according the number of total points
                }
            }
        });

        //defining and displaying the column names
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n","Name","Team","Location","First Positions","Second Positions","Third Positions","Total Points","Total Races Participated");

        //displaying rows and assigning Formula1Drivers to the rows
        for(int i = 0; i< sortedDrivers.size(); i++) {
            System.out.printf("%-10s %-10s %-20s %-15s %-15s %-10s %-20s %-50s\n", sortedDrivers.get(i).getDriverName(), sortedDrivers.get(i).getDriverTeam(), formula1Driver.get(i).getDriverLocation(), formula1Driver.get(i).getNumberOfFirstPositions(), formula1Driver.get(i).getNumberOfSecondPositions(), formula1Driver.get(i).getNumberOfThirdPositions(), formula1Driver.get(i).getNumberOfTotalPoints(), formula1Driver.get(i).getRaceCount());
        }
    }

    //Add a new race to the system
    public void addRaceDetails(ArrayList<Formula1Driver> formula1Driver,ArrayList<Race> races) {
        try {
            Scanner scan = new Scanner(System.in);
            HashSet<String> racesSet = new HashSet<>();
            System.out.println("Please enter the date(yyyy-MM-dd): ");
            String strDate = scan.nextLine();
            System.out.println("Please enter race name: ");
            String raceName = scan.nextLine();
            char option = 'C';
            while  ((option != 'Q') && (option != 'q')) {
                if ((option == 'C') || (option == 'c')) {
                    System.out.println("Please enter the driver name: ");
                    String driverName = scan.next().toUpperCase();
                    for (int i = 0; i < formula1Driver.size(); i++) {
                        if (formula1Driver.get(i).getDriverName().equals(driverName)) {
                            formula1Driver.get(i).setRaceName(raceName);
//                            race.setRaceDate(date);
                            System.out.println(racesSet);
                            if (!racesSet.contains(formula1Driver.get(i).getDriverName())) { //avoid repeating the same driver in the same race
                                racesSet.add(formula1Driver.get(i).getDriverName());
//                                System.out.println(racesSet);
                                System.out.println("Status of the race(Please enter 'Y' for finished and 'N' for unfinished): ");
                                String raceStatus = scan.next().toUpperCase();
                                formula1Driver.get(i).setRaceStatus(raceStatus);
                                System.out.println("Achieved position: ");
                                int achievedPosition = scan.nextInt();
                                formula1Driver.get(i).setDriverWonPosition(achievedPosition);
                                formula1Driver.get(i).numberOfPoints();
                                formula1Driver.get(i).setDate(strDate);

                                //Creating new race object of Race class and assigning values for the race
                                Race race = new Race(strDate,formula1Driver.get(i).getDriverName(),formula1Driver.get(i).getDriverWonPosition(),formula1Driver.get(i).getNumberOfPointsForOneRace(),formula1Driver.get(i).getRaceStatus());
                                races.add(race);
                                for (int x=0; x<races.size();x++){
                                    System.out.println(races.get(x));
                                }
                                saveDataInAFile(formula1Driver,races);  //to save changes

                            } else {
                                System.out.println("You have already entered race details for this driver");
                            }
                        }
                    }
                    System.out.println("Please enter 'C' if you want to continue adding a new driver or 'Q' to return to main menu: ");
                    option = scan.next().charAt(0);
                }else{
                    System.out.println("You have entered an invalid option.Please enter 'C' if you want to continue adding a new driver or 'Q' to return to main menu: ");
                    option = scan.next().charAt(0);
                }
            }
            Collections.sort(races, new Comparator<Race>() {
                @Override
                public int compare(Race o1, Race o2) {
                    return o1.getRaceDate().compareTo(o2.getRaceDate());
                }
            });
        } catch (InputMismatchException e) {
            System.out.println("You have entered an invalid input.Please try again.");
        }
    }

    //save data in a file
    public void saveDataInAFile(ArrayList<Formula1Driver> formula1Driver,ArrayList<Race> races){
        try {
            FileOutputStream fileOut = new FileOutputStream("formula1championship.ser"); // saves Formula1Driver Arraylist
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(formula1Driver);
            out.flush();
            out.close();
            FileOutputStream fileOutRace = new FileOutputStream("formula1championshipRaces.ser");  //save Race Arraylist
            ObjectOutputStream outRace = new ObjectOutputStream(fileOutRace);
            outRace.writeObject(races);
//            System.out.println("file save"+races);
            System.out.println("Session serialized");
//            System.out.println(formula1Driver);
            outRace.flush();
            outRace.close();

        }catch (IOException i){
            System.out.println("Sorry,Error occurred.");
            i.printStackTrace();
        }
    }

    //load saved data from the file - Formula1Driver Arraylist
    public ArrayList<Formula1Driver> loadDataFromTheFile(){
        ArrayList<Formula1Driver> formula1Driver = new ArrayList<>();
        try (
            FileInputStream fileIn = new FileInputStream("formula1championship.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn)){
            formula1Driver = (ArrayList<Formula1Driver>) in.readObject();
            in.close();
        } catch (EOFException e){
            System.out.println("error");
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c){
            System.out.println("Error");
            c.printStackTrace();
        }

        return formula1Driver;
    }

    //load saved data from the file - Race Arraylist
    public ArrayList<Race> loadRaceDataFromTheFile(){
        ArrayList<Race> races = new ArrayList<>();
//        System.out.println(races);
        try (
                FileInputStream fileInRace = new FileInputStream("formula1championshipRaces.ser");
                ObjectInputStream inRace = new ObjectInputStream(fileInRace)){
                races = (ArrayList<Race>) inRace.readObject();
                inRace.close();
//                System.out.println("races in fom1champ "+races);
        } catch (EOFException e){
            e.printStackTrace();
        }
        catch (IOException i){
            i.printStackTrace();
        }catch (ClassNotFoundException c){
            System.out.println("Error");
            c.printStackTrace();
        }

        return races;
    }

    //creating a random race
    public ArrayList<Race> randomRace(){
        ArrayList<Race> randomRace = new ArrayList<>();
        ArrayList<Race> races = loadRaceDataFromTheFile();
        ArrayList<Formula1Driver> formula1Drivers = loadDataFromTheFile();
//        System.out.println(formula1Drivers);

        //generate a random date
        Random randomDate = new Random();
        int minDay = (int) LocalDate.of(2020,1,1).toEpochDay();
        int maxDay = (int)LocalDate.of(2021,12,31).toEpochDay();
        long randomDay = minDay + randomDate.nextInt(maxDay-minDay);
        LocalDate randomRaceDate = LocalDate.ofEpochDay(randomDay);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");//convert Loacaldate into string format
        String date = randomRaceDate.format(dateTimeFormatter);

        HashSet<Integer> position = new HashSet<>(); // to avoid duplicate values
        //generate random positions
        for(int i=0;i< formula1Drivers.size();i++){
            Race race = new Race();
            race.setDriverName(formula1Drivers.get(i).getDriverName());
//            formula1Drivers.get(i).setDate(date);
            race.setRaceDate(date);
            formula1Drivers.get(i).setRaceStatus("Y");
            race.setRaceStatus("Y");
            Random rand = new Random();
            int randomPosition;
            int max = 10;
            int min = 1;
            do{
                randomPosition = rand.nextInt(max-min+1)+min;
            }while(position.contains(randomPosition));
            formula1Drivers.get(i).setDriverWonPosition(randomPosition);
            race.setDriverWonPosition(formula1Drivers.get(i).getDriverWonPosition());
            formula1Drivers.get(i).numberOfPoints();
            race.setNumberOfPoints(formula1Drivers.get(i).getNumberOfPointsForOneRace());
            races.add(race);
            randomRace.add(i,race);
        }
        saveDataInAFile(formula1Drivers,races); //to save changes
//        System.out.println(formula1Drivers);
//        System.out.println(randomRace);
        return randomRace;
    }
}
