import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PassengerFilter {


    private List<Passenger> filterPassenger;
    private String error;


    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public PassengerFilter(List<Passenger> passengers, String topMenu, int pClass, String minId, String maxId, String name, String sex,
                           String sibSp, String parch, String ticket, String minFare,
                           String maxFare, String cabin, String embarked, int fileNumber) {


        this.filterPassenger = byPClass(pClass, passengers);
        this.filterPassenger = byId(minId, maxId, this.filterPassenger);
        this.filterPassenger = byName(name, this.filterPassenger);
        this.filterPassenger = bySex(sex, this.filterPassenger);
        this.filterPassenger = bySibSp(sibSp, this.filterPassenger);
        this.filterPassenger = byParch(parch, this.filterPassenger);
        this.filterPassenger = byTicket(ticket, this.filterPassenger);
        this.filterPassenger = byFare(minFare, maxFare, this.filterPassenger);
        this.filterPassenger = byCabin(cabin, this.filterPassenger);
        this.filterPassenger = byEmbarked(embarked, this.filterPassenger);
        try {
            this.filterPassenger = this.filterPassenger.stream().sorted().collect(Collectors.toList());
            WriteToFile writeToFile = new WriteToFile();
            writeToFile.writeToExcel(fileNumber, this.filterPassenger, topMenu);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(this.filterPassenger.size());

    }

    public List<Passenger> getFilterPassenger() {
        return filterPassenger;
    }

    public List<Passenger> byPClass(int PClass, List<Passenger> passengers) {
        return passengers.stream().filter(passenger -> passenger.classSort(PClass)).collect(Collectors.toList());
    }

    public List<Passenger> byId(String min, String max, List<Passenger> passengers) {
        List<Passenger> passengerList = new ArrayList<>();
        try {
            if (Integer.parseInt(min) > Integer.parseInt(max) && Integer.parseInt(max) != 0) {
                System.out.println("min is more than max, try again");
                this.error = "min is more than max, try again";
            } else if (Integer.parseInt(min) < 0 || Integer.parseInt(max) < 0) {
                System.out.println("Cannot get negative number");
            } else if (Integer.parseInt(min) > passengers.size() || Integer.parseInt(max) > passengers.size()) {
                System.out.println("min or max cannot be biggest than list");
            } else {
                passengerList = passengers.stream().filter(Passenger -> Passenger.rangeId(Integer.parseInt(min), Integer.parseInt(max))).collect(Collectors.toList());

            }
        } catch (NumberFormatException e) {
            System.out.println("please enter number in the id field!");
        }
        return passengerList;
    }

    public List<Passenger> byName(String name, List<Passenger> passengers) {
        return passengers.stream().filter(passenger -> passenger.subName(name)).collect(Collectors.toList());
    }

    public List<Passenger> bySex(String sex, List<Passenger> passengers) {
        return passengers.stream().filter(passenger -> passenger.isSameSex(sex)).collect(Collectors.toList());
    }

    public List<Passenger> bySibSp(String sibSp, List<Passenger> passengers) {
        List<Passenger> passengerList = null;
        if (isEmptyField(sibSp)) {
            return passengers;
        }
        try {
            passengerList = passengers.stream().filter(passenger -> passenger.isSibSp(Integer.parseInt(sibSp))).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Enter number please");
            return passengers;
        }
        return passengerList;
    }

    public List<Passenger> byParch(String parch, List<Passenger> passengers) {
        List<Passenger> passengerList = null;
        if (isEmptyField(parch)) {
            return passengers;
        }
        try {
            passengerList = passengers.stream().filter(passenger -> passenger.isParch(Integer.parseInt(parch))).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("Enter number please");
            return passengers;
        }
        return passengerList;
    }

    public List<Passenger> byTicket(String ticket, List<Passenger> passengers) {
        if (isEmptyField(ticket)) {
            return passengers;
        }
        return passengers.stream().filter(passenger -> passenger.sameTicket(ticket)).collect(Collectors.toList());
    }

    public List<Passenger> byFare(String min, String max, List<Passenger> passengers) {
        List<Passenger> passengerList = new ArrayList<>();
        try {
            if (Double.parseDouble(min) > Double.parseDouble(max) && Double.parseDouble(max) != 0) {
                System.out.println("min is more than max, try again");
            } else {
                passengerList = passengers.stream().filter(Passenger -> Passenger.rangeFare(Double.parseDouble(min), Double.parseDouble(max))).collect(Collectors.toList());
            }
        } catch (NumberFormatException e) {
            System.out.println("please enter number in the fare Field!");
        }
        return passengerList;
    }

    public List<Passenger> byCabin(String cabin, List<Passenger> passengers) {
        if (cabin.equals("null")) {
            return passengers;
        }
        return passengers.stream().filter(passenger -> passenger.sameCabin(cabin)).collect(Collectors.toList());
    }

    public List<Passenger> byEmbarked(String embarked, List<Passenger> passengers) {
        return passengers.stream().filter(passenger -> passenger.embarkedSort(embarked)).collect(Collectors.toList());
    }


    private boolean isEmptyField(String s) {
        return s.equals("");
    }


}
