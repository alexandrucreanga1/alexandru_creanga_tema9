package tema9_ExceptionsAndLogging;

import tema9_ExceptionsAndLogging.exceptions.AgeValidationException;
import tema9_ExceptionsAndLogging.exceptions.EmptyInputException;
import tema9_ExceptionsAndLogging.exceptions.IncorrectSearchException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRepository {


    List<Student> studentByName = new ArrayList<>();


    public Student addNewStudent (String firtsName, String lastName, String dayofbithDDmmYYYY, Gender gender, String idCNP) throws ParseException, EmptyInputException, NumberFormatException {

        if(firtsName.isEmpty() || lastName.isEmpty() || dayofbithDDmmYYYY.length()==0 || idCNP.length()==0) {
            throw new EmptyInputException("Oops, something is wrong. Please be sure to put in data in all relevant fields!");
        }

        if(idCNP.length() != 13) {
            throw new EmptyInputException("Oops, something is wrong. Please be sure your idCNP has 13 digits");
        }

        validateDateOfBirth (dayofbithDDmmYYYY);


        Student studentToAdd = new Student(firtsName,lastName,dayofbithDDmmYYYY,gender,idCNP);
        studentByName.add(studentToAdd);
        return studentToAdd;
    }

    private void validateDateOfBirth(String dayofbithDDmmYYYY) throws NumberFormatException {
        String[] tokens = dayofbithDDmmYYYY.split("-");
        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        if (day <1 || day >30) {
            throw new NumberFormatException("Hey, please reconsider your day!");
        }
        if (month <1 || month >12) {
            throw new NumberFormatException("Hey, please reconsider your Month!");
        }
        if (year <1900) {
            throw new NumberFormatException("Hey, please reconsider your year!");
        }
    }


    public void removeStudent (String idCNP) throws EmptyInputException, IncorrectSearchException {
        boolean found = false;
        if(idCNP.length() != 13) {
            throw new EmptyInputException("Oops, something is wrong. Please be sure your idCNP has 13 digits");
        }

        for(Student student : studentByName) {
            if(student.getIdCNP().equals(idCNP)) {
                found = true;
                studentByName.remove(student);
                System.out.println("Student "+student.getFirstName() + " " + student.getLastName() + " successfully deleted!" );
            }
        }
        if(!found) {
            throw new IncorrectSearchException("Student does not exist");
        }


        //      studentByName.removeIf(s ->s.getIdCNP().equals(idCNP));
    }






    public void retrieveSutdentsbyAge (int age) throws AgeValidationException, ParseException {

        if(age <= 0) {
            throw new AgeValidationException("Age is not a number!");
        }

        for(Student student : studentByName) {
            //convert string to Date;
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = formatter.parse(student.getDayofbithDDmmYYYY());
            //Converting obtained Date object to LocalDate object
            Instant instant = date.toInstant();
            ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
            LocalDate givenDate = zone.toLocalDate();
            //Calculating the difference between given date to current date.
            Period period = Period.between(givenDate, LocalDate.now());
            if (age == period.getYears()) {
                System.out.println("Student found with age " + age + " years old:");
                System.out.println("Last Name: "+student.getLastName() + " First Name: " + student.getFirstName() + " idCNP:" + student.getIdCNP());
            }
        }
    }



    public List<Student> getStudentByName() {
        return studentByName;
    }

    @Override
    public String toString() {
        return "StudentRepository{" +
                "studentByName=" + studentByName +
                '}';
    }
}

