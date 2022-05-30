import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PassengerFilter {

    private List<Passenger> passengers;

    public PassengerFilter(int pClass,String minId,String maxId,String name,String sex,
                           String sibSp,String parch,String ticket,String minFare,
                           String maxFare,String cabin,String embarked){

        File file = new File(Constants.PATH_TO_DATA_FILE);
        this.passengers=readFromFile(file);
        List<Passenger> filterPassenger=byPClass(pClass,this.passengers);
        filterPassenger=byId(minId,maxId,filterPassenger);
        filterPassenger=byName(name,filterPassenger);
        filterPassenger=bySex(sex,filterPassenger);
        filterPassenger=bySibSp(sibSp,filterPassenger);
        filterPassenger=byParch(parch,filterPassenger);
        filterPassenger=byTicket(ticket,filterPassenger);
        filterPassenger=byFare(minFare,maxFare,filterPassenger);
        filterPassenger=byCabin(cabin,filterPassenger);
        filterPassenger=byEmbarked(embarked,filterPassenger);
        System.out.println(filterPassenger.size());
    }

    private List<Passenger> byPClass(int PClass,List<Passenger>passengers) {
        return  passengers.stream().filter(passenger -> passenger.classSort(PClass)).collect(Collectors.toList());
    }
    private List<Passenger> byId(String min,String max,List<Passenger> passengers) {
        List<Passenger> passengerList = new ArrayList<>();
        try {
            if (Integer.parseInt(min)>Integer.parseInt(max)&&Integer.parseInt(max)!=0){
                System.out.println("min is more than max, try again");
            }else if (Integer.parseInt(min)<0||Integer.parseInt(max)<0){
                System.out.println("Cannot get negative number");
            } else if (Integer.parseInt(min)>passengers.size()||Integer.parseInt(max)>passengers.size()){
                System.out.println("min or max cannot be biggest than list");
            }else{
                passengerList =passengers.stream().filter(Passenger -> Passenger.rangeId(Integer.parseInt(min), Integer.parseInt(max))).collect(Collectors.toList());

            }
        } catch (NumberFormatException e) {
            System.out.println("please enter number in the id field!");
        }
        return passengerList;
    }
    private List<Passenger> byName (String name,List<Passenger> passengers){
        return passengers.stream().filter(passenger -> passenger.subName(name)).collect(Collectors.toList());
    }
    private List<Passenger> bySex(String sex,List<Passenger> passengers){
        return passengers.stream().filter(passenger -> passenger.isSameSex(sex)).collect(Collectors.toList());
    }
    private List<Passenger> bySibSp (String sibSp,List<Passenger> passengers){
        List<Passenger> passengerList = null;
        if (isEmptyField(sibSp)){
            return passengers;
        }
        try {
            passengerList=passengers.stream().filter(passenger -> passenger.isSibSp(Integer.parseInt(sibSp))).collect(Collectors.toList());
        }catch (NumberFormatException e){
            System.out.println("Enter number please");
            return passengers;
        }
        return passengerList;
    }
    private List<Passenger> byParch(String parch,List<Passenger> passengers){
        List<Passenger> passengerList=null;
        if (isEmptyField(parch)){
            return passengers;
        }
        try {
            passengerList=passengers.stream().filter(passenger -> passenger.isParch(Integer.parseInt(parch))).collect(Collectors.toList());
        }catch (NumberFormatException e){
            System.out.println("Enter number please");
            return passengers;
        }
        return passengerList;
    }
    private List<Passenger>  byTicket(String ticket,List<Passenger> passengers){
        if (isEmptyField(ticket)){
            return passengers;
        }
        return passengers.stream().filter(passenger -> passenger.sameTicket(ticket)).collect(Collectors.toList());
    }
    private List<Passenger> byFare(String min,String max,List<Passenger> passengers){
        List<Passenger> passengerList=new ArrayList<>();
        try {
            if (Double.parseDouble(min)>Double.parseDouble(max)&&Double.parseDouble(max)!=0){
                System.out.println("min is more than max, try again");
            }else {
                passengerList = passengers.stream().filter(Passenger -> Passenger.rangeFare(Double.parseDouble(min), Double.parseDouble(max))).collect(Collectors.toList());
            }
        }catch (NumberFormatException e){
            System.out.println("please enter number in the fare Field!");
        }
        return passengerList;
    }
    private List<Passenger> byCabin(String cabin,List<Passenger> passengers){
        if (cabin.equals("null")){
            return passengers;
        }
        return passengers.stream().filter(passenger -> passenger.sameCabin(cabin)).collect(Collectors.toList());
    }
    private List<Passenger> byEmbarked (String embarked,List<Passenger> passengers){
        return passengers.stream().filter(passenger -> passenger.embarkedSort(embarked)).collect(Collectors.toList());
    }


    private boolean isEmptyField(String s){
        return s.equals("");
    }
    private ArrayList<Passenger> readFromFile(File file) {
        ArrayList<Passenger> passengers = new ArrayList<>();
        if (file.exists()) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            boolean firstTime = true;
            while (scanner.hasNextLine()) {
                String[] details = scanner.nextLine().split(",");
                if (!firstTime) {
                    Passenger passenger = new Passenger(details);
                    passengers.add(passenger);
                }
                firstTime = false;
            }

        }
        return passengers;
    }
}
