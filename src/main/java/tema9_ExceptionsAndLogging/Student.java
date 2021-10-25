package tema9_ExceptionsAndLogging;

public class Student {

    private String firstName;
    private String lastName;
    private String dayofbithDDmmYYYY;
    private Gender gender;
    private String idCNP;


    public Student(String firstName, String lastName, String dayofbithDDmmYYYY, Gender gender, String idCNP) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dayofbithDDmmYYYY = dayofbithDDmmYYYY;
        this.gender = gender;
        this.idCNP = idCNP;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDayofbithDDmmYYYY() {
        return dayofbithDDmmYYYY;
    }

    public Gender getGender() {
        return gender;
    }

    public String getIdCNP() {
        return idCNP;
    }


    @Override
    public String toString() {
        return "Student{" +
                "firtsName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dayofbithDDmmYYYY='" + dayofbithDDmmYYYY + '\'' +
                ", gender='" + gender + '\'' +
                ", idCNP='" + idCNP + '\'' +
                '}';
    }
}
