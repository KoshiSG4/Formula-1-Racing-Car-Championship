import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Driver implements Serializable {
    protected String driverName;
    protected String driverLocation;
    protected String driverTeam;
    protected int driverWonPosition;
    protected int raceCount;
    protected String raceName;
    protected String raceStatus;
    private String date;

    //default constructor
    Driver(){}

    //get driver information from the user
    public void newDriverInformation(){
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("Please complete below with the driver's information:");
            System.out.println("Name: ");
            String driverName = scan.nextLine().toUpperCase();
            setDriverName(driverName);
            System.out.println("Location: ");
            String driverLocation = scan.nextLine().toUpperCase();
            setDriverLocation(driverLocation);
            System.out.println("Team(Car Manufacturer): ");
            String driverTeam = scan.nextLine().toUpperCase();
            setDriverTeam(driverTeam);
        }catch (InputMismatchException e){
            System.out.println("You have entered an invalid input.Please try again.");
        }
    }

    public void setDriverName(String driverName){
        this.driverName = driverName;
    }
    public String getDriverName(){
        return this.driverName;
    }
    public void setDriverTeam(String driverTeam){
        this.driverTeam = driverTeam;
    }
    public String getDriverTeam(){
        return this.driverTeam;
    }
    public void setDriverLocation(String driverLocation){
        this.driverLocation = driverLocation;
    }
    public String getDriverLocation(){
        return this.driverLocation;
    }
    public void setDriverWonPosition(int driverWonPosition){
        this.driverWonPosition = driverWonPosition;
    }
    public int getDriverWonPosition(){
        return this.driverWonPosition;
    }
    public String getRaceName() {
        return raceName;
    }
    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }
    public void setRaceStatus(String raceStatus){
        raceCount++;
        this.raceStatus = raceStatus;
    }
    public String getRaceStatus(){
        return this.raceStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}




