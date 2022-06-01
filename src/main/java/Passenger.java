import java.util.List;

public class Passenger implements Comparable<Passenger> {


    private String name;
    private String sex;
    private String cabin;
    private String embarked;
    private String ticket;
    private int passengerId;
    private int pClass;
    private int sibSp;
    private int parch;
    private double age;
    private double fare;
    private boolean survived;


    public Passenger(String[] details) {
        initial(details);

    }

    private void initial(String[] details) {
        this.passengerId = Integer.parseInt(details[0]);
        if (details[1].equals(Constants.SURVIVED)) {
            this.survived = true;
        } else {
            this.survived = false;
        }
        this.pClass = Integer.parseInt(details[2]);
        this.name = getFormattedName(details[3] + "," + details[4]);
        this.sex = details[5];
        if (details[6].equals("")) {
            this.age = 0;
        } else {
            this.age = Double.parseDouble(details[6]);
        }
        this.sibSp = Integer.parseInt(details[7]);
        this.parch = Integer.parseInt(details[8]);
        this.ticket = details[9];
        this.fare = Double.parseDouble(details[10]);
        this.cabin = details[11];
        if (details.length < 13) {
            this.embarked = "";
        } else {
            this.embarked = details[12];
        }
    }

    public String allDetails() {
        return this.passengerId + "," + this.getSurvived() + "," + this.pClass + "," + this.name + "," +
                this.sex + "," + this.age + "," + this.sibSp + "," + this.parch + "," + this.ticket +
                "," + this.fare + "," + this.cabin + "," + this.embarked;
    }

    public boolean isParch(int numOfParch) {
        return this.parch == numOfParch;
    }

    public boolean isSibSp(int numOfSibSp) {
        return this.sibSp == numOfSibSp;
    }

    public boolean classSort(int pClass) {
        if (pClass == Constants.All_CLASS) {
            return true;
        }
        return this.pClass == pClass;
    }

    public boolean embarkedSort(String embarked) {
        if (embarked.equals(Constants.EMBARKED_OPTION[0])) {
            return true;
        }
        return this.embarked.equals(embarked);
    }

    public boolean rangeId(int min, int max) {
        if (min == 0 && max > 0) {
            return this.passengerId <= max;
        } else if (min > 0 && max == 0) {
            return this.passengerId >= min;
        } else if (min > 0 && max > 0 && max > min) {
            return this.passengerId >= min && this.passengerId <= max;
        } else if (min == max && min != 0) {
            return this.passengerId == max;
        }
        return true;
    }

    public boolean rangeFare(double min, double max) {
        if (min == 0 && max > 0) {
            return this.fare <= max;
        } else if (min > 0 && max == 0) {
            return this.fare >= min;
        } else if (min > 0 && max > 0 && max > min) {
            return this.fare >= min && this.fare < max;
        } else if (min == max && min != 0) {
            return this.fare == max;
        }
        return true;
    }

    public boolean rangeFareStatistics(int min,int max){
        if (min>=Constants.MAX_FARE_SEARCH){
            return this.fare>=Constants.MAX_FARE_SEARCH;
        }
        return this.fare >= min && this.fare < max;
    }

    public boolean rangeAge(int min, int max) {
        if (this.age==Constants.EMPTY_AGE){
            return false;
        }
        if (min >= Constants.MAX_AGE_SEARCH) {
            return this.age>=Constants.MAX_AGE_SEARCH;
        }
        return this.age >= min && this.age < max;
    }

    public boolean familyMember(int members) {
        if (members == 0) {
            return this.parch + this.sibSp == members;
        }
        return this.parch + this.sibSp >= members;
    }

    public boolean sameCabin(String cabin) {
        return this.cabin.equals(cabin);
    }


    public boolean sameTicket(String ticket) {
        return this.ticket.equals(ticket);
    }

    public boolean isSurvived() {
        return this.survived;
    }

    public String getSurvived() {
        if (this.survived) {
            return Constants.SURVIVED;
        }
        return Constants.DIED;
    }

    public boolean isSameSex(String sex) {
        if (sex.equals(Constants.SEX_OPTION[0])) {
            return true;
        }
        return this.sex.equals(sex);
    }

    public boolean subName(String sub) {
        return this.name.toLowerCase().contains(sub.toLowerCase());
    }

    public String getName() {
        return this.name;
    }

    public String getFormattedName(String name) {
        String[] lastName = name.split(Constants.COMMA_SPLIT);
        String[] firstName = lastName[Constants.FIRST_NAME].split(Constants.POINT_SPLIT);
        name = firstName[Constants.FIRST_NAME] + " " + lastName[Constants.LAST_NAME];
        name = name.replaceAll(Constants.QUOT_SPLIT, "");
        return name;
    }

    public int compareTo(Passenger o) {
        return this.name.compareTo(o.getName());
    }
}
