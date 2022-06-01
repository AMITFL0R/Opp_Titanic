import javax.swing.*;
import java.awt.*;

public class Helper {


    public static JButton addButton(JPanel panel, String buttonText, int x, int y, int width, int height) {
        JButton button = new JButton(buttonText);
        Font font = new Font(Constants.KIND_OF_FONT, Font.BOLD, Constants.FONT_SIZE_BUTTON);
        button.setBounds(x, y, width, height);
        button.setVisible(true);
        button.setFont(font);
        panel.add(button);
        return button;
    }

    public static JLabel addLabel(JPanel panel, String labelText, int x, int y, int width, int height) {
        JLabel label = new JLabel(labelText);
        Font font = new Font(Constants.KIND_OF_FONT, Font.BOLD, Constants.SIZE_FONT_LABEL);
        label.setFont(font);
        label.setForeground(Color.white);
        label.setBounds(x, y, width, height);
        panel.add(label);
        return label;
    }

    public static JTextField addTextField(JPanel panel, int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        Font font = new Font(Constants.KIND_OF_FONT, Font.BOLD, Constants.SIZE_FONT_TEXT_FIELD);
        textField.setFont(font);
        panel.add(textField);
        return textField;
    }

    public static JComboBox addComboBox(Object[] list, JPanel panel, int x, int y, int width, int height) {
        JComboBox comboBox = new JComboBox(list);
        comboBox.setBounds(x, y, width, height);
        panel.add(comboBox);
        return comboBox;
    }
    public static void setBigFont(JLabel label,Color color,int size){
        label.setForeground(color);
        label.setFont(new Font(Constants.KIND_OF_FONT, Font.BOLD, size));
    }
}
