
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PassengerFilter {

    private List<Passenger> filterPassenger;


    public void filterAndWrite(List<Passenger> passengers, String topMenu, int pClass, String minId, String maxId, String name, String sex,
                               String sibSp, String parch, String ticket, String minFare,
                               String maxFare, String cabin, String embarked, int fileNumber){

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

    }

    public List<Passenger> getFilterPassenger() {
        return this.filterPassenger;
    }

    public List<Passenger> byPClass(int PClass, List<Passenger> passengers) {
        return passengers.stream().filter(passenger -> passenger.classSort(PClass)).collect(Collectors.toList());
    }

    public List<Passenger> byId(String min, String max, List<Passenger> passengers) {
        return passengers.stream().filter(Passenger -> Passenger.rangeId(Integer.parseInt(min), Integer.parseInt(max))).collect(Collectors.toList());
    }

    public List<Passenger> byName(String name, List<Passenger> passengers) {
        return passengers.stream().filter(passenger -> passenger.subName(name)).collect(Collectors.toList());
    }

    public List<Passenger> bySex(String sex, List<Passenger> passengers) {
        return passengers.stream().filter(passenger -> passenger.isSameSex(sex)).collect(Collectors.toList());
    }

    public List<Passenger> bySibSp(String sibSp, List<Passenger> passengers) {
        if (isEmptyField(sibSp)) {
            return passengers;
        }
        return passengers.stream().filter(passenger -> passenger.isSibSp(Integer.parseInt(sibSp))).collect(Collectors.toList());
    }

    public List<Passenger> byParch(String parch, List<Passenger> passengers) {
        if (isEmptyField(parch)) {
            return passengers;
        }
        return passengers.stream().filter(passenger -> passenger.isParch(Integer.parseInt(parch))).collect(Collectors.toList());
    }

    public List<Passenger> byTicket(String ticket, List<Passenger> passengers) {
        if (isEmptyField(ticket)) {
            return passengers;
        }
        return passengers.stream().filter(passenger -> passenger.sameTicket(ticket)).collect(Collectors.toList());
    }

    public List<Passenger> byFare(String min, String max, List<Passenger> passengers) {
        return passengers.stream().filter(Passenger -> Passenger.rangeFare(Double.parseDouble(min), Double.parseDouble(max))).collect(Collectors.toList());
    }

    public List<Passenger> byCabin(String cabin, List<Passenger> passengers) {
        if (cabin.equals(Constants.INITIAL_NULL)) {
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
