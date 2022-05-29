import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainPanel extends JPanel {
    private JComboBox<String> survivedComboBox;
    private List<Passenger> passengers;
    private List<JTextField> rangeId;

    public MainPanel(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        this.setLayout(null);
        this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width - Constants.MARGIN_FROM_RIGHT, height);
        mainView();
        this.passengers = readFromFile(file);
        listeners();
    }

    private void mainView() {
        JLabel survivedLabel = Helper.addLabel(this, "Survived Status: ", this.getX() + Constants.MARGIN_FROM_LEFT, this.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.survivedComboBox = Helper.addComboBox(Constants.PASSENGER_CLASS_OPTIONS, this, survivedLabel.getX() + survivedLabel.getWidth() + 1, survivedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        this.rangeId = new ArrayList<>();
        this.rangeId.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.getY() - Constants.MARGIN_FROM_TOP, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.rangeId.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.rangeId.get(0).getY() + Constants.TEXT_FIELD_HEIGHT, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.rangeId.get(0).setText("0");
        this.rangeId.get(1).setText("0");
        JLabel minId = Helper.addLabel(this, "Min: ", this.rangeId.get(0).getX() - Constants.LABEL_WIDTH / 3, this.rangeId.get(0).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel maxId = Helper.addLabel(this, "Max: ", this.rangeId.get(1).getX() - Constants.LABEL_WIDTH / 3, this.rangeId.get(1).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel passengerLabel = Helper.addLabel(this, "Passenger ID: ", minId.getX() - Constants.LABEL_WIDTH, (minId.getY() + maxId.getY()) / 2, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        JButton temp = Helper.addButton(this, "temp", 300, 300, 100, 50);
        temp.addActionListener((e) -> {
            byId();
        });
    }

    private void listeners() {
        this.survivedComboBox.addActionListener((e) -> {
            byPClass(this.survivedComboBox.getSelectedIndex());
        });

    }

    private void byId() {
        List<Passenger> passengerList = new ArrayList<>();
        if (Integer.parseInt(this.rangeId.get(0).getText())>Integer.parseInt(this.rangeId.get(1).getText())&&Integer.parseInt(this.rangeId.get(1).getText())!=0){
            System.out.println("min is more than max, try again");
        }else {
            try {
                passengerList = this.passengers.stream().filter(Passenger -> Passenger.rangeId(Integer.parseInt(this.rangeId.get(0).getText()), Integer.parseInt(this.rangeId.get(1).getText()))).collect(Collectors.toList());
                System.out.println(passengerList.size());
            } catch (NumberFormatException e) {
                System.out.println("please enter number in the id field!");
            }
        }


    }


    private List<Passenger> byPClass(int PClass) {
        return  this.passengers.stream().filter(Passenger::isSurvived).filter(passenger -> passenger.classSort(PClass)).collect(Collectors.toList());
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
