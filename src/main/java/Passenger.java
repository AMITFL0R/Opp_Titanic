public class Passenger {

    private int passengerId;
    private boolean survived;
    private int pClass;
    private String name;
    private String sex;
    private double age;
    private int sibSp;
    private int parch;
    private String ticket;
    private double fare;
    private String cabin;
    private String embarked;

    public Passenger(String[] details){
        initial(details);

    }
    private void initial(String[] details){
        this.passengerId=Integer.valueOf(details[0]);
        if (details[1].equals("1")){
            this.survived=true;
        }else {
            this.survived=false;
        }
        this.pClass=Integer.valueOf(details[2]);
        this.name=details[3]+","+details[4];
        this.sex=details[5];
        if (details[6].equals("")){
            this.age=0;
        }else {
            this.age=Double.valueOf(details[6]);
        }
        this.sibSp=Integer.valueOf(details[7]);
        this.parch=Integer.valueOf(details[8]);
        this.ticket=details[9];
        this.fare=Double.valueOf(details[10]);
        this.cabin=details[11];
        if (details.length<13){
            this.embarked="";
        }else {
            this.embarked=details[12];
        }
    }
    public  boolean classSort(int pClass){
        if (pClass==0){
            return true;
        }
        return this.pClass==pClass;
    }
    public boolean rangeId(int min, int max) {
        if (min==0&&max>0){
            return this.passengerId<=max;
        }else if (min>0&&max==0){
            return this.passengerId>=min;
        }else if (min>0&&max>0&&max>min){
            return this.passengerId>=min&&this.passengerId<=max;
        }else if (min==max&&min!=0){
            return this.passengerId==max;
        }
        return true;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public boolean isSurvived() {
        return this.survived;
    }

    public void setSurvived(boolean survived) {
        this.survived = survived;
    }

    public int getpClass() {
        return pClass;
    }

    public void setpClass(int pClass) {
        this.pClass = pClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public int getSibSp() {
        return sibSp;
    }

    public void setSibSp(int sibSp) {
        this.sibSp = sibSp;
    }

    public int getParch() {
        return parch;
    }

    public void setParch(int parch) {
        this.parch = parch;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getCabin() {
        return cabin;
    }

    public void setCabin(String cabin) {
        this.cabin = cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    public void setEmbarked(String embarked) {
        this.embarked = embarked;
    }

    public String getFormattedName(){
        String name=this.name;
        String[] lastName=name.split(",");
        String[] firstName=lastName[1].split("\\.");
        name=firstName[1]+" "+lastName[0];
        name=name.replaceAll("\"","");
        return name;
    }

}
