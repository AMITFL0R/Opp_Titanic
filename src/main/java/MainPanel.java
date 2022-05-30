import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainPanel extends JPanel {
    private JComboBox<String> survivedComboBox;
    private JComboBox<String> sexComboBox;
    private List<Passenger> passengers;
    private List<JTextField> rangeId;
    private List<JTextField> fare;
    private JTextField nameTextField;
    private JTextField sibSp;
    private JTextField parch;
    private JTextField ticket;
    private JTextField cabin;
    private JComboBox<String> embarked;

    public MainPanel(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        this.setLayout(null);
        this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width - Constants.MARGIN_FROM_RIGHT, height);
        mainView();
        this.passengers = readFromFile(file);

    }

    private void mainView() {
        JLabel survivedLabel = Helper.addLabel(this, "Survived Status: ", this.getX() + Constants.MARGIN_FROM_LEFT, this.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.survivedComboBox = Helper.addComboBox(Constants.PASSENGER_CLASS_OPTIONS, this, survivedLabel.getX() + survivedLabel.getWidth() + 1, survivedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.rangeId = new ArrayList<>();
        this.fare=new ArrayList<>();
        this.rangeId.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.getY() - Constants.MARGIN_FROM_TOP, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.rangeId.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.rangeId.get(0).getY() + Constants.TEXT_FIELD_HEIGHT, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.rangeId.get(0).setText("0");
        this.rangeId.get(1).setText("0");
        JLabel minId = Helper.addLabel(this, "Min: ", this.rangeId.get(0).getX() - Constants.LABEL_WIDTH / 3, this.rangeId.get(0).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel maxId = Helper.addLabel(this, "Max: ", this.rangeId.get(1).getX() - Constants.LABEL_WIDTH / 3, this.rangeId.get(1).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel passengerLabel = Helper.addLabel(this, "Passenger ID: ", minId.getX() - Constants.LABEL_WIDTH, (minId.getY() + maxId.getY()) / 2, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        JLabel name = Helper.addLabel(this,"Name: ",Constants.MARGIN_FROM_LEFT,survivedLabel.getY()+survivedLabel.getHeight()+Constants.MARGIN_FROM_TOP*4,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.nameTextField=Helper.addTextField(this,name.getX()+name.getWidth(),name.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        JLabel sex=Helper.addLabel(this,"Sex: ",passengerLabel.getX(),name.getY(),Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.sexComboBox=Helper.addComboBox(Constants.SEX_OPTION,this,this.rangeId.get(0).getX(),sex.getY(),Constants.COMBO_BOX_WIDTH,Constants.COMBO_BOX_HEIGHT);
        JLabel sibSp=Helper.addLabel(this,"SibSp: ",Constants.MARGIN_FROM_LEFT,name.getY()+name.getHeight()+Constants.MARGIN_FROM_TOP*4,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.sibSp=Helper.addTextField(this,this.nameTextField.getX(),sibSp.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        JLabel parch = Helper.addLabel(this,"Parch: ",sex.getX(),sibSp.getY(),Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.parch=Helper.addTextField(this,this.sexComboBox.getX(),parch.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        JLabel ticket=Helper.addLabel(this,"Ticket: ",Constants.MARGIN_FROM_LEFT,sibSp.getY()+sibSp.getHeight()+Constants.MARGIN_FROM_TOP*4,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.ticket=Helper.addTextField(this,this.sibSp.getX(),ticket.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        this.fare.add(Helper.addTextField(this,this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH,this.ticket.getY()-Constants.MARGIN_FROM_TOP,Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT));
        this.fare.add(Helper.addTextField(this,this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH,this.fare.get(0).getY()+this.fare.get(0).getHeight(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT));
        this.fare.get(0).setText("0");
        this.fare.get(1).setText("0");
        JLabel minFare=Helper.addLabel(this,"Min: ",this.fare.get(0).getX()-Constants.LABEL_WIDTH/3,this.fare.get(0).getY(),Constants.LABEL_WIDTH/3,Constants.LABEL_HEIGHT);
        JLabel maxFare=Helper.addLabel(this,"Max: ",this.fare.get(1).getX()-Constants.LABEL_WIDTH/3,this.fare.get(1).getY(),Constants.LABEL_WIDTH/3,Constants.LABEL_HEIGHT);
        JLabel fare=Helper.addLabel(this,"Fare: ",parch.getX(),(minFare.getY()+maxFare.getY())/2,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        JLabel cabin=Helper.addLabel(this,"Cabin: ",Constants.MARGIN_FROM_LEFT,ticket.getY()+ticket.getHeight()+Constants.MARGIN_FROM_TOP*4,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.cabin=Helper.addTextField(this,this.ticket.getX(),cabin.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        JLabel embarked=Helper.addLabel(this,"Embarked: ",fare.getX(),cabin.getY(),Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.embarked=Helper.addComboBox(Constants.EMBARKED_OPTION,this,this.parch.getX(),embarked.getY(),Constants.COMBO_BOX_WIDTH,Constants.COMBO_BOX_HEIGHT);

        JButton temp = Helper.addButton(this, "temp", 300, 500, 100, 50);
        temp.addActionListener((e) -> {
//            byId();
//            byName();
//            bySex((String) this.sexComboBox.getSelectedItem());
//            bySibSp(this.sibSp.getText());
//            byParch(this.parch.getText());
//            byTicket(this.ticket.getText());
//            byFare();
//            byCabin(this.cabin.getText());
           List<Passenger> passengerList=byEmbarked((String) this.embarked.getSelectedItem(),byCabin(this.cabin.getText(),byFare(byTicket(this.ticket.getText(), byParch(this.parch.getText(),bySibSp(this.sibSp.getText(),bySex((String) this.sexComboBox.getSelectedItem(),byName(byId(byPClass(Integer.parseInt((String) this.survivedComboBox.getSelectedItem()),this.passengers))))))))));
            System.out.println(passengerList.size());

        });
    }



    private List<Passenger> byId(List<Passenger> passengers) {
        List<Passenger> passengerList = new ArrayList<>();
        try {
        if (Integer.parseInt(this.rangeId.get(0).getText())>Integer.parseInt(this.rangeId.get(1).getText())&&Integer.parseInt(this.rangeId.get(1).getText())!=0){
            System.out.println("min is more than max, try again");
        }else {
                passengerList =passengers.stream().filter(Passenger -> Passenger.rangeId(Integer.parseInt(this.rangeId.get(0).getText()), Integer.parseInt(this.rangeId.get(1).getText()))).collect(Collectors.toList());
                System.out.println(passengerList.size());
        }
        } catch (NumberFormatException e) {
            System.out.println("please enter number in the id field!");
        }
        return passengerList;
    }
    private List<Passenger> byFare(List<Passenger> passengers){
        List<Passenger> passengerList=new ArrayList<>();
        try {
            if (Double.parseDouble(this.fare.get(0).getText())>Double.parseDouble(this.fare.get(1).getText())&&Double.parseDouble(this.fare.get(1).getText())!=0){
                System.out.println("min is more than max, try again");
            }else {
                passengerList = passengers.stream().filter(Passenger -> Passenger.rangeFare(Double.parseDouble(this.fare.get(0).getText()), Double.parseDouble(this.fare.get(1).getText()))).collect(Collectors.toList());
                System.out.println(passengerList.size());
            }

        }catch (NumberFormatException e){
            System.out.println("please enter number in the fare Field!");
        }
        return passengerList;

    }


    private List<Passenger> byPClass(int PClass,List<Passenger> passengers) {
        return  passengers.stream().filter(Passenger::isSurvived).filter(passenger -> passenger.classSort(PClass)).collect(Collectors.toList());
    }
    private List<Passenger> byEmbarked(String embarked,List<Passenger> passengers){
        return passengers.stream().filter(passenger -> passenger.embarkedSort(embarked)).collect(Collectors.toList());
    }
    private List<Passenger> byName(List<Passenger> passengers){
        return passengers.stream().filter(passenger -> passenger.subName(this.nameTextField.getText())).collect(Collectors.toList());
    }

    private List<Passenger> bySex(String sex,List<Passenger> passengers){
        return passengers.stream().filter(passenger -> passenger.isSameSex(sex)).collect(Collectors.toList());
    }
    private List<Passenger> bySibSp(String sibSp,List<Passenger> passengers){
        List<Passenger> passengerList = null;
        try {
            passengerList=passengers.stream().filter(passenger -> passenger.isSibSp(Integer.parseInt(sibSp))).collect(Collectors.toList());
        }catch (NumberFormatException e){
            System.out.println("Enter number please");
        }
        return passengerList;
    }
    private List<Passenger> byParch(String parch,List<Passenger> passengers){
        List<Passenger> passengerList=null;
        try {
            passengerList=passengers.stream().filter(passenger -> passenger.isParch(Integer.parseInt(parch))).collect(Collectors.toList());
        }catch (NumberFormatException e){
            System.out.println("Enter number please");
        }
        return passengerList;
    }
    private List<Passenger>  byTicket(String ticket,List<Passenger> passengers){
        return passengers.stream().filter(passenger -> passenger.subTicket(ticket)).collect(Collectors.toList());
    }
    private List<Passenger> byCabin(String cabin,List<Passenger> passengers){
        return passengers.stream().filter(passenger -> passenger.subCabin(cabin)).collect(Collectors.toList());
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
