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
    private PassengerFilter passengerFilter;
    private List<JTextField> rangeId;
    private List<JTextField> fare;
    private JTextField nameTextField;
    private JTextField sibSp;
    private JTextField parch;
    private JTextField ticket;
    private JTextField cabin;
    private JComboBox<String> embarkedComboBox;
    private JButton search;
    private int numOfSearches;


    public MainPanel(int x, int y, int width, int height) {
        this.backGround = new ImageIcon("titanicImage.jpeg");
        this.numOfSearches=1;
        this.setLayout(null);
        this.setBounds(x, y, width, height);
        mainView();
    }

    private void mainView() {
        JLabel pClass = Helper.addLabel(this, "PClass: ", this.getX() + Constants.MARGIN_FROM_LEFT, this.getY()+Constants.MARGIN_FROM_TOP, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
        this.pClassComboBox = Helper.addComboBox(Constants.PASSENGER_CLASS_OPTIONS, this, pClass.getX() + pClass.getWidth() + 1, pClass.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);

        this.rangeId = new ArrayList<>();
        this.rangeId.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.getY()+Constants.MARGIN_FROM_TOP, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.rangeId.add(Helper.addTextField(this, this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH, this.rangeId.get(0).getY() + Constants.TEXT_FIELD_HEIGHT, Constants.TEXT_FIELD_WIDTH, Constants.TEXT_FIELD_HEIGHT));
        this.rangeId.get(0).setText("0");
        this.rangeId.get(1).setText("0");

        JLabel minId = Helper.addLabel(this, "Min: ", this.rangeId.get(0).getX() - Constants.LABEL_WIDTH / 3, this.rangeId.get(0).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel maxId = Helper.addLabel(this, "Max: ", this.rangeId.get(1).getX() - Constants.LABEL_WIDTH / 3, this.rangeId.get(1).getY(), Constants.LABEL_WIDTH / 3, Constants.LABEL_HEIGHT);
        JLabel passengerLabel = Helper.addLabel(this, "Passenger ID: ", minId.getX() - Constants.LABEL_WIDTH, (minId.getY() + maxId.getY()) / 2, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);

        JLabel name = Helper.addLabel(this,"Name: ",Constants.MARGIN_FROM_LEFT,pClass.getY()+pClass.getHeight()+Constants.MARGIN_FROM_TOP*4,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.nameTextField=Helper.addTextField(this,name.getX()+name.getWidth(),name.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);

        JLabel sex=Helper.addLabel(this,"Sex: ",passengerLabel.getX(),name.getY(),Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.sexComboBox=Helper.addComboBox(Constants.SEX_OPTION,this,this.rangeId.get(0).getX(),sex.getY(),Constants.COMBO_BOX_WIDTH,Constants.COMBO_BOX_HEIGHT);

        JLabel sibSp=Helper.addLabel(this,"SibSp: ",Constants.MARGIN_FROM_LEFT,name.getY()+name.getHeight()+Constants.MARGIN_FROM_TOP*4,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.sibSp=Helper.addTextField(this,this.nameTextField.getX(),sibSp.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);

        JLabel parch = Helper.addLabel(this,"Parch: ",sex.getX(),sibSp.getY(),Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.parch=Helper.addTextField(this,this.sexComboBox.getX(),parch.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);

        JLabel ticket=Helper.addLabel(this,"Ticket: ",Constants.MARGIN_FROM_LEFT,sibSp.getY()+sibSp.getHeight()+Constants.MARGIN_FROM_TOP*4,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.ticket=Helper.addTextField(this,this.sibSp.getX(),ticket.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);

        this.fare=new ArrayList<>();
        this.fare.add(Helper.addTextField(this,this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH,this.ticket.getY()-Constants.MARGIN_FROM_TOP,Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT));
        this.fare.add(Helper.addTextField(this,this.getX() + this.getWidth() - Constants.MARGIN_FROM_RIGHT - Constants.TEXT_FIELD_WIDTH,this.fare.get(0).getY()+this.fare.get(0).getHeight(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT));
        this.fare.get(0).setText("0");
        this.fare.get(1).setText("0");

        JLabel minFare=Helper.addLabel(this,"Min: ",this.fare.get(0).getX()-Constants.LABEL_WIDTH/3,this.fare.get(0).getY(),Constants.LABEL_WIDTH/3,Constants.LABEL_HEIGHT);
        JLabel maxFare=Helper.addLabel(this,"Max: ",this.fare.get(1).getX()-Constants.LABEL_WIDTH/3,this.fare.get(1).getY(),Constants.LABEL_WIDTH/3,Constants.LABEL_HEIGHT);
        JLabel fare=Helper.addLabel(this,"Fare: ",parch.getX(),(minFare.getY()+maxFare.getY())/2,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);

        JLabel cabin=Helper.addLabel(this,"Cabin: ",Constants.MARGIN_FROM_LEFT,ticket.getY()+ticket.getHeight()+Constants.MARGIN_FROM_TOP*4,Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.cabin=Helper.addTextField(this,this.ticket.getX(),cabin.getY(),Constants.TEXT_FIELD_WIDTH,Constants.TEXT_FIELD_HEIGHT);
        this.cabin.setText("null");

        JLabel embarked=Helper.addLabel(this,"Embarked: ",fare.getX(),cabin.getY(),Constants.LABEL_WIDTH,Constants.LABEL_HEIGHT);
        this.embarkedComboBox =Helper.addComboBox(Constants.EMBARKED_OPTION,this,this.parch.getX(),embarked.getY(),Constants.COMBO_BOX_WIDTH,Constants.COMBO_BOX_HEIGHT);

        this.search = Helper.addButton(this, "Search", this.getWidth()-Constants.BUTTON_WIDTH-Constants.MARGIN_FROM_RIGHT, this.getHeight()*5/7, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);

        this.search.addActionListener((e) -> {
            this.passengerFilter=new PassengerFilter(this.pClassComboBox.getSelectedIndex(),
                    this.rangeId.get(0).getText(),this.rangeId.get(1).getText(),
                    this.nameTextField.getText(),(String) this.sexComboBox.getSelectedItem(),
                    this.sibSp.getText(),this.parch.getText(),this.ticket.getText(),
                    this.fare.get(0).getText(),this.fare.get(1).getText(),this.cabin.getText(),
                    (String) this.embarkedComboBox.getSelectedItem(),this.numOfSearches);
            this.numOfSearches++;
            printFilters(this.passengerFilter.getFilterPassenger());


        });
    }
    private void printFilters(List<Passenger> passengers){
        List<Passenger> survived=passengers.stream().filter(Passenger::isSurvived).collect(Collectors.toList());
        Thread printResult=new Thread(()->{
            JLabel result=Helper.addLabel(this,"Total Row: "+passengers.size()+"("+survived.size()+" survived"+","+(passengers.size()-survived.size())+" did not)",
                    this.getWidth()/2-Constants.RESULT_PRINT_WIDTH/2,this.getHeight()*5/8,Constants.RESULT_PRINT_WIDTH,Constants.RESULT_PRINT_HEIGHT);
            result.setForeground(Color.black);
            result.setFont(new Font("Ariel",Font.BOLD,Constants.RESULT_FONT_SIZE));
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            result.setVisible(false);
        });
        printResult.start();






    }
    private void print(String printOnBoard) {
        new Thread(() -> {
            JLabel print = Helper.addLabel(this, printOnBoard, 400,400,300,50);
            repaint();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            print.setVisible(false);
        }).start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(this.backGround.getImage(), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }

}
