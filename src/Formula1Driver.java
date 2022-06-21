import java.io.Serializable;

public class Formula1Driver extends Driver implements Serializable {
    private static final long serialVersionUID = 4237341568044529521L;

    private int numberOfTotalPoints;
    private int numberOfFirstPositions;
    private int numberOfSecondPositions;
    private int numberOfThirdPositions;
    private int[] pointsArray = new int[11];
    private int numberOfPointsForOneRace;

    //default constructor
    Formula1Driver() {
    }

    //copy constructor
    public Formula1Driver(Formula1Driver driver) {
        driverName = driver.driverName;
        driverLocation = driver.driverLocation;
        driverTeam = driver.driverTeam;
        driverWonPosition = driver.driverWonPosition;
        raceCount = driver.raceCount;
        raceStatus = driver.raceStatus;
        numberOfTotalPoints = driver.numberOfTotalPoints;
        numberOfFirstPositions = driver.numberOfFirstPositions;
        numberOfSecondPositions = driver.numberOfSecondPositions;
        numberOfThirdPositions = driver.numberOfThirdPositions;
        pointsArray = driver.pointsArray;
    }

    //accumulating the points
    public void numberOfPoints() {
        if (getRaceStatus().equals("Y")) {
//            System.out.println("status"+getRaceStatus());
            switch (getDriverWonPosition()) {
                case 1:
                    numberOfFirstPositions++;
                    pointsArray[0] = 25;
                    break;
                case 2:
                    numberOfSecondPositions++;
                    pointsArray[1] = 18;
                    break;
                case 3:
                    numberOfThirdPositions++;
                    pointsArray[2] = 15;
                    break;
                case 4:
                    pointsArray[3] = 12;
                    break;
                case 5:
                    pointsArray[4] = 10;
                    break;
                case 6:
                    pointsArray[5] = 8;
                    break;
                case 7:
                    pointsArray[6] = 6;
                    break;
                case 8:
                    pointsArray[7] = 4;
                    break;
                case 9:
                    pointsArray[8] = 2;
                    break;
                case 10:
                    pointsArray[9] = 1;
                    break;
                default:
                    pointsArray[10]=0;
            }
            setNumberOfPointsForOneRace(pointsArray[getDriverWonPosition()-1]); // setting points for a one race
            for (int i = 0; i < pointsArray.length; i++) {
                numberOfTotalPoints += pointsArray[i];
//                System.out.println(pointsArray[i]);
            }
        }
    }
    public int getNumberOfFirstPositions() {
        return numberOfFirstPositions;
    }

    public void setNumberOfFirstPositions(int numberOfFirstPositions) {
        this.numberOfFirstPositions = numberOfFirstPositions;
    }

    public int getNumberOfSecondPositions() {
        return numberOfSecondPositions;
    }

    public void setNumberOfSecondPositions(int numberOfSecondPositions) {
        this.numberOfSecondPositions = numberOfSecondPositions;
    }

    public int getNumberOfThirdPositions() {
        return numberOfThirdPositions;
    }

    public void setNumberOfThirdPositions(int numberOfThirdPositions) {
        this.numberOfThirdPositions = numberOfThirdPositions;
    }

    public void setNumberOfTotalPoints(int numberOfTotalPoints) {
        this.numberOfTotalPoints = numberOfTotalPoints;
    }

    public int getNumberOfTotalPoints() { return this.numberOfTotalPoints;}

    public void setRaceCount(int raceCount) {
        this.raceCount = raceCount;
    }

    public int getRaceCount() { return raceCount; }

    public void setNumberOfPointsForOneRace(int numberOfPointsForOneRace) {
        this.numberOfPointsForOneRace = numberOfPointsForOneRace;
    }

    public int getNumberOfPointsForOneRace() { return numberOfPointsForOneRace; }


    public String toString2(){
        return "Driver Name: \t"+this.driverName + "\n" +
                "Number of first positions: \t"+this.numberOfFirstPositions + "\n" +
                "Number of second positions: \t"+this.numberOfSecondPositions + "\n" +
                "Number of third positions: \t"+this.numberOfThirdPositions + "\n" +
                "Number of total points: \t"+this.numberOfTotalPoints + "\n" +
                "Number of races participated: \t"+this.raceCount;
    }

    @Override
    public String toString() {
        return "Formula1Driver{" +
                "driverName='" + driverName + '\'' +
                ", driverLocation='" + driverLocation + '\'' +
                ", driverTeam='" + driverTeam + '\'' +
                ", driverWonPosition=" + driverWonPosition +
                ", raceCount=" + raceCount +
                ", raceStatus='" + raceStatus + '\'' +
                ", numberOfTotalPoints=" + numberOfTotalPoints +
                ", numberOfFirstPositions=" + numberOfFirstPositions +
                ", numberOfSecondPositions=" + numberOfSecondPositions +
                ", numberOfThirdPositions=" + numberOfThirdPositions +
                '}';
    }
}




