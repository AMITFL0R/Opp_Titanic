import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class WriteToFile {




    public WriteToFile(int fileNumber, List<Passenger> passengers) throws IOException {
//        List<String> passenger=passengers.stream().map(Passenger::getPassengerId).collect(Collectors.toList());
        String[] strings={"sadas","fcgvh","retyu"};
        FileWriter fileWriter=new FileWriter("C:\\Users\\DELL\\OneDrive\\שולחן העבודה\\TitanicPassangers\\"+fileNumber+".csv");
        fileWriter.write("strings.toString(),vghv");
        fileWriter.write("\nstrings.()");
        fileWriter.close();


    }


}
