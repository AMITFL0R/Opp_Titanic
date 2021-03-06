import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class WriteToFile {


    public void writeToExcel(int fileNumber, List<Passenger> passengers, String topMenu) throws IOException {
        List<String> stringListPassenger;
        stringListPassenger = passengers.stream().map(Passenger::allDetails).collect(Collectors.toList());
        FileWriter fileWriter = new FileWriter(Constants.EXCEL_PATH_FILE + fileNumber + ".csv");
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
            FileWriter fileWriter = new FileWriter(Constants.STATISTICS_PATH_FILE);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
