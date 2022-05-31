import javax.swing.*;


class Main extends JFrame {

    public static void main(String[] args) {
        new Main();


    }

    public Main() {
        this.setTitle("Titanic Passengers Data by AIUA");
        this.setLayout(null);
        this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(new MainPanel(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        this.setVisible(true);

    }



}