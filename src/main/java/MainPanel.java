import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainPanel extends JPanel {
    private ImageIcon backGround;

    private JComboBox<String> pClassComboBox;
    private JComboBox<String> sexComboBox;
    private JComboBox<String> embarkedComboBox;
    private JButton search;
    private JButton statistics;
    private JTextField nameTextField;
    private JTextField sibSp;
    private JTextField parch;
    private JTextField ticket;
    private JTextField cabin;
    private List<JTextField> rangeId;
    private List<JTextField> fare;
    private List<Passenger> passengers;
    private PassengerFilter passengerFilter;
    private String topMenu;
    private int numOfSearches;


    public MainPanel(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE);
        this.passengers = readFromFile(file);
        this.backGround = new ImageIcon(Constants.BACKGROUND_NAME);
        this.numOfSearches = Constants.FIRST_SEARCH;
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        mainView();
    }

    private void mainView() {
        JLabel pClass = Helper.addLabel(this, "PClass: ", this.getX() + Constants.MARGIN_FROM_LEFT, this.getY() + Constants.MARGIN_FROM_TOP, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.pClassComboBox = Helper.addComboBox(Constants.PASSENGER_CLASS_OPTIONS, this, pClass.getX() + pClass.getWidth() + 1, pClass.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);


        this.rangeId = new ArrayList<>();
        this.rangeId.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.getY() + Constants.MARGIN_FROM_TOP, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.rangeId.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.rangeId.get(Constants.MIN).getY() + Constants.TEXT_FIELD_HEIGHT, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.rangeId.get(Constants.MIN).setText(Constants.INITIAL_ZERO);
        this.rangeId.get(Constants.MAX).setText(Constants.INITIAL_ZERO);


        JLabel minId = Helper.addLabel(this, "Min: ", this.rangeId.get(Constants.MIN).getX() - Constants.LABEL_WIDTH / 3, this.rangeId.get(Constants.MIN).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel maxId = Helper.addLabel(this, "Max: ", this.rangeId.get(Constants.MAX).getX() - Constants.LABEL_WIDTH / 3, this.rangeId.get(Constants.MAX).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel passengerLabel = Helper.addLabel(this, "Passenger ID: ", minId.getX() - Constants.LABEL_WIDTH, (minId.getY() + maxId.getY()) / 2, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);


        JLabel name = Helper.addLabel(this, "Name: ", Constants.MARGIN_FROM_LEFT, pClass.getY() + pClass.getHeight() + Constants.MARGIN_FROM_TOP * 4, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.nameTextField = Helper.addTextField(this, name.getX() + name.getWidth(), name.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);


        JLabel sex = Helper.addLabel(this, "Sex: ", passengerLabel.getX(), name.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.sexComboBox = Helper.addComboBox(Constants.SEX_OPTION, this, this.rangeId.get(Constants.MIN).getX(), sex.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);


        JLabel sibSp = Helper.addLabel(this, "SibSp: ", Constants.MARGIN_FROM_LEFT, name.getY() + name.getHeight() + Constants.MARGIN_FROM_TOP * 4, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.sibSp = Helper.addTextField(this, this.nameTextField.getX(), sibSp.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);


        JLabel parch = Helper.addLabel(this, "Parch: ", sex.getX(), sibSp.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.parch = Helper.addTextField(this, this.sexComboBox.getX(), parch.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);


        JLabel ticket = Helper.addLabel(this, "Ticket: ", Constants.MARGIN_FROM_LEFT, sibSp.getY() + sibSp.getHeight() + Constants.MARGIN_FROM_TOP * 4, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.ticket = Helper.addTextField(this, this.sibSp.getX(), ticket.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);


        this.fare = new ArrayList<>();
        this.fare.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.ticket.getY() - Constants.MARGIN_FROM_TOP, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.fare.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.fare.get(Constants.MIN).getY() + this.fare.get(Constants.MIN).getHeight(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.fare.get(Constants.MIN).setText(Constants.INITIAL_ZERO);
        this.fare.get(Constants.MAX).setText(Constants.INITIAL_ZERO);


        JLabel minFare = Helper.addLabel(this, "Min: ", this.fare.get(Constants.MIN).getX() - Constants.LABEL_WIDTH / 3, this.fare.get(0).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel maxFare = Helper.addLabel(this, "Max: ", this.fare.get(Constants.MAX).getX() - Constants.LABEL_WIDTH / 3, this.fare.get(1).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel fare = Helper.addLabel(this, "Fare: ", parch.getX(), (minFare.getY() + maxFare.getY()) / 2, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);


        JLabel cabin = Helper.addLabel(this, "Cabin: ", Constants.MARGIN_FROM_LEFT, ticket.getY() + ticket.getHeight() + Constants.MARGIN_FROM_TOP * 4, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.cabin = Helper.addTextField(this, this.ticket.getX(), cabin.getY(), Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT);
        this.cabin.setText(Constants.INITIAL_NULL);


        JLabel embarked = Helper.addLabel(this, "Embarked: ", fare.getX(), cabin.getY(), Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.embarkedComboBox = Helper.addComboBox(Constants.EMBARKED_OPTION, this, this.parch.getX(), embarked.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
        searchButton();
        statisticsButton();
    }

    private void searchButton() {
        this.search = Helper.addButton(this, Constants.SEARCH_BUTTON, this.getWidth() - Constants.BUTTON_WIDTH - Constants.MARGIN_FROM_RIGHT / 2, this.getHeight() * 5 / 7, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        this.search.addActionListener((e) -> {
            if (isNumber() && validIdOrFare()) {
                this.passengerFilter = new PassengerFilter();
                this.passengerFilter.filterAndWrite(this.passengers, this.topMenu, this.pClassComboBox.getSelectedIndex(),
                        this.rangeId.get(Constants.MIN).getText(), this.rangeId.get(Constants.MAX).getText(),
                        this.nameTextField.getText(), (String) this.sexComboBox.getSelectedItem(),
                        this.sibSp.getText(), this.parch.getText(), this.ticket.getText(),
                        this.fare.get(Constants.MIN).getText(), this.fare.get(Constants.MAX).getText(), this.cabin.getText(),
                        (String) this.embarkedComboBox.getSelectedItem(), this.numOfSearches);
                this.numOfSearches++;
                printFilters(this.passengerFilter.getFilterPassenger());
            }


        });


    }

    private void statisticsButton() {
        this.statistics = Helper.addButton(this, Constants.STATISTIC_BUTTON, this.search.getX() - (int) (Constants.BUTTON_WIDTH * 1.5), this.search.getY(), (int) (Constants.BUTTON_WIDTH * 1.5), Constants.BUTTON_HEIGHT);
        this.statistics.addActionListener((e) -> {
            Statistics statistics = new Statistics(this.passengers);
            printCopySucceeded();
        });
    }

    private void printFilters(List<Passenger> passengers) {
        List<Passenger> survived = passengers.stream().filter(Passenger::isSurvived).collect(Collectors.toList());
        Thread printResult = new Thread(() -> {
            JLabel result = Helper.addLabel(this, "Total Row: " + passengers.size() + "(" + survived.size() + " survived" + "," + (passengers.size() - survived.size()) + " did not)",
                    this.getWidth() / 2 - Constants.RESULT_PRINT_WIDTH / 2, this.getHeight() * 5 / 8, Constants.RESULT_PRINT_WIDTH, Constants.RESULT_PRINT_HEIGHT);
            Helper.setBigFont(result, Color.black, Constants.RESULT_FONT_SIZE);
            try {
                Thread.sleep(Constants.PRINT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.setVisible(false);
        });
        printResult.start();
    }

    private void printCopySucceeded() {
        Thread printResult = new Thread(() -> {
            JLabel result = Helper.addLabel(this, Constants.COPY_PRINT, this.getWidth() / 2 - Constants.RESULT_PRINT_WIDTH / 2, this.getHeight() / 2, Constants.RESULT_PRINT_WIDTH, Constants.RESULT_PRINT_HEIGHT);
            Helper.setBigFont(result, Color.black, Constants.RESULT_FONT_SIZE);
            try {
                Thread.sleep(Constants.PRINT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.setVisible(false);
        });
        printResult.start();
    }

    public void printError(String printOnBoard) {
        new Thread(() -> {
            JLabel print = Helper.addLabel(this, printOnBoard, this.getWidth() / 2 - Constants.LABEL_WIDTH * 2, this.getHeight() / 2 - Constants.BUTTON_HEIGHT / 2, Constants.LABEL_WIDTH * 4, Constants.BUTTON_HEIGHT);
            Helper.setBigFont(print, Color.red, Constants.RESULT_FONT_SIZE);
            repaint();
            try {
                Thread.sleep(Constants.PRINT_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print.setVisible(false);
        }).start();
    }

    private boolean isNumber() {
        try {
            if (this.sibSp.getText().equals("") || this.parch.getText().equals("")) {
                return true;
            }
            if (Double.parseDouble(this.fare.get(Constants.MIN).getText()) < 0 || Double.parseDouble(this.fare.get(Constants.MAX).getText()) < 0 ||
                    Integer.parseInt(this.rangeId.get(Constants.MIN).getText()) < 0 || Integer.parseInt(this.rangeId.get(Constants.MAX).getText()) < 0 ||
                    Integer.parseInt(this.sibSp.getText()) < 0 || Integer.parseInt(this.parch.getText()) < 0) {
                printError(Constants.ERROR_NEGATIVE);
                return false;
            }
        } catch (NumberFormatException e) {
            printError(Constants.ERROR_NUMBER);
            return false;
        }
        return true;
    }

    private boolean validIdOrFare() {
        try {
            if ((Integer.parseInt(this.rangeId.get(Constants.MIN).getText()) > Integer.parseInt(this.rangeId.get(Constants.MAX).getText()) && Integer.parseInt(this.rangeId.get(Constants.MAX).getText()) != 0) ||
                    (Double.parseDouble(this.fare.get(Constants.MIN).getText()) > Double.parseDouble(this.fare.get(Constants.MAX).getText()) && Double.parseDouble(this.fare.get(Constants.MAX).getText()) != 0)) {
                printError(Constants.ERROR_MIN_MAX);
                return false;
            }
        } catch (NumberFormatException e) {
            printError(Constants.ERROR_NUMBER);
            return false;
        }
        return true;
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
                String line = scanner.nextLine();
                String[] details = line.split(Constants.COMMA_SPLIT);
                if (!firstTime) {
                    Passenger passenger = new Passenger(details);
                    passengers.add(passenger);
                }
                if (firstTime) {
                    this.topMenu = line;
                }
                firstTime = false;
            }
        }
        return passengers;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(this.backGround.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }

}
