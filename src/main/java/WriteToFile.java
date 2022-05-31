import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WriteToFile {


    public void writeToExcel(int fileNumber, List<Passenger> passengers, String topMenu) throws IOException {
        List<String> stringListPassenger;
        stringListPassenger = passengers.stream().map(Passenger::allDetails).collect(Collectors.toList());
        FileWriter fileWriter = new FileWriter("C:\\Users\\User\\Desktop\\לימודים עדו\\שנה א\\סמסטר ב\\תכנות מונחה עצמים\\טיטאניק\\" + fileNumber + ".csv");
        fileWriter.write(topMenu + "\n");
        stringListPassenger.stream().forEach(passenger ->
                {
                    try {
                        fileWriter.write(passenger + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
        fileWriter.close();
    }

    public void writeStatistics(String text) {
        try {
            FileWriter fileWriter = new FileWriter("C:\\Users\\User\\Desktop\\לימודים עדו\\שנה א\\סמסטר ב\\תכנות מונחה עצמים\\טיטאניק\\statistics.txt");
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
