
import java.util.List;
import java.util.stream.Collectors;

public class Statistics {

    public Statistics(List<Passenger> passengers) {
        WriteToFile writeToFile = new WriteToFile();
        writeToFile.writeStatistics(survivedByClass(passengers) + "\n" + survivedBySex(passengers) + "\n" +
                survivedByAges(passengers) + "\n" + survivedByFamily(passengers) + "\n" +
                survivedByFare(passengers) + "\n" + survivedByEmbarked(passengers));

    }

    private String survivedByClass(List<Passenger> passengers) {
        String text = "Percentage Survival by Class: \n";
        for (int i = 1; i < Constants.PASSENGER_CLASS_OPTIONS.length; i++) {
            int finalI = i;
            text += "Class: " + i + "\n";
            List<Passenger> passengers1 = passengers.stream().filter(passenger -> passenger.classSort(finalI)).collect(Collectors.toList());
            text += survivedPercent(passengers1);
        }
        return text;
    }

    private String survivedBySex(List<Passenger> passengers) {
        String text = "Survived by sex:\n";
        for (int i = 1; i < Constants.SEX_OPTION.length; i++) {
            int finalI = i;
            text += Constants.SEX_OPTION[i] + "--";
            List<Passenger> passengers1 = passengers.stream().filter(passenger -> passenger.isSameSex(Constants.SEX_OPTION[finalI])).collect(Collectors.toList());
            text += survivedPercent(passengers1);
        }
        return text;
    }

    private String survivedByAges(List<Passenger> passengers) {
        String text = "Survived by age: \n";
        int j = 0;
        for (int i = 0; i < Constants.MAX_AGE_SEARCH+1; i = j ) {
            j += Constants.INITIAL_MAX;
            if (i < Constants.MAX_AGE_SEARCH) {
                text += "ages: " + i + "-" + j + "\n";
            } else {
                text += "ages: " + "+" + i + "\n";
            }
            int finalI = i;
            int finalJ = j;
            List<Passenger> passengers1 = passengers.stream().filter(passenger -> passenger.rangeAge(finalI, finalJ)).collect(Collectors.toList());
            text += survivedPercent(passengers1);

        }
        return text;
    }

    private String survivedByFamily(List<Passenger> passengers) {
        String text = "Survived by family: \n";
        for (int i = 0; i < Constants.TWO_OPTION; i++) {
            int finalI = i;
            if (i == 0) {
                text += "person without family: \n";
            } else {
                text += "person with at least one: \n";
            }
            List<Passenger> passengers1 = passengers.stream().filter(passenger -> passenger.familyMember(finalI)).collect(Collectors.toList());
            text += survivedPercent(passengers1);
        }
        return text;
    }

    private String survivedByFare(List<Passenger> passengers) {
        String text = "Survived by fare: \n";
        int j = 0;
        for (int i = 0; i < Constants.MAX_FARE_SEARCH+1; i = j) {
           if (i==0){
               j+=Constants.INITIAL_MAX;
           }else {
               j+=Constants.JUMPING;
           }
            if (i < Constants.MAX_FARE_SEARCH) {
                text += "fare: " + i + "$-" + j + "$\n";
            } else {
                text += "fare: " + "+" + i + "$\n";
            }
            int finalI = i;
            int finalJ = j;
            List<Passenger> passengers1 = passengers.stream().filter(passenger -> passenger.rangeFareStatistics(finalI, finalJ)).collect(Collectors.toList());
            text += survivedPercent(passengers1);
        }
        return text;
    }

    private String survivedByEmbarked(List<Passenger> passengers) {
        String text = "Survived by embarked: \n";
        for (int i = 1; i < Constants.EMBARKED_OPTION.length; i++) {
            int finalI = i;
            text += "Embarked " + Constants.EMBARKED_OPTION[i] + "\n";
            List<Passenger> passengers1 = passengers.stream().filter(passenger -> passenger.embarkedSort(Constants.EMBARKED_OPTION[finalI])).collect(Collectors.toList());
            text += survivedPercent(passengers1);
        }
        return text;
    }

    private String survivedPercent(List<Passenger> passengers) {
        String text = "";
        int sum = passengers.size();
        passengers = passengers.stream().filter(Passenger::isSurvived).collect(Collectors.toList());
        int survived = passengers.size();
        text += (survived * Constants.CHANGE_TO_PERCENT) / sum + "%\n";
        return text;
    }

}
