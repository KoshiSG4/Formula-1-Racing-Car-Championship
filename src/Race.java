import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Race implements Serializable {
    private static final long serialVersionUID = 2906642554793891381L;
    private String raceDate;
    private String driverName;
    private int driverWonPosition;
    private int numberOfPoints;
    private String raceStatus;

    Race(){}    //default constructor
    Race(String raceDate, String driverName, int driverWonPosition, int numberOfPoints, String raceStatus){ //constructor
        this.raceDate = raceDate;
        this.driverName = driverName;
        this.driverWonPosition = driverWonPosition;
        this.numberOfPoints = numberOfPoints;
        this.raceStatus = raceStatus;
    }

    public void setRaceDate(String date){
        this.raceDate = date;
    }

    public LocalDate getRaceDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(this.raceDate,dateTimeFormatter);
        return date;
    }
    public void setDriverName(String driverName){
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public int getDriverWonPosition() {
        return driverWonPosition;
    }

    public void setDriverWonPosition(int driverWonPosition) {
        this.driverWonPosition = driverWonPosition;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public String getRaceStatus() {
        return raceStatus;
    }

    public void setRaceStatus(String raceStatus) {
        this.raceStatus = raceStatus;
    }

    @Override
    public String toString() {
        return "Race{" +
                "raceDate=" + raceDate +
                ", driverName='" + driverName + '\'' +
                ", driverWonPosition=" + driverWonPosition +
                ", numberOfPoints=" + numberOfPoints +
                '}';
    }


}
