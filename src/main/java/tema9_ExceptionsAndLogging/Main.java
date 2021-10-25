package tema9_ExceptionsAndLogging;

import tema9_ExceptionsAndLogging.exceptions.AgeValidationException;
import tema9_ExceptionsAndLogging.exceptions.EmptyInputException;
import tema9_ExceptionsAndLogging.exceptions.IncorrectSearchException;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {


    private static Logger loggers = Logger.getLogger("studentRepository");
    static StudentRepository studentRepository = new StudentRepository();


    public static void main(String[] args) throws AgeValidationException, ParseException, EmptyInputException, NumberFormatException, IncorrectSearchException {
        loggers.log(Level.WARNING, "Creating new students, ihuuu!");


        Student student1 = studentRepository.addNewStudent("Gheorghita","Ion","20-01-1989",Gender.M,"1234567890123");
        Student student2 = studentRepository.addNewStudent("Elena","Bejan","15-03-1993",Gender.F,"1234567895436");
        Student student3 = studentRepository.addNewStudent("Alina","Stelian","04-06-1992",Gender.F,"1234567892678");
        Student student4 = studentRepository.addNewStudent("Stejara","Parcan","10-09-1995",Gender.F,"1234567897952");
        Student student5 = studentRepository.addNewStudent("Vasile","Chifeac","18-03-1990",Gender.M,"1234567893421");
        Student student6 = studentRepository.addNewStudent("Mihai","Lupascu","13-04-1991",Gender.M,"1234567894562");
        Student student7 = studentRepository.addNewStudent("Mihaela","Movila","25-02-1989",Gender.M,"1234567899632");

        //line for testing of missing inputs !
        //  Student student8 = studentRepository.addNewStudent("","Movila","25-02-1989",Gender.M,"1234567899632");

        System.out.println(studentRepository.getStudentByName());

        studentRepository.removeStudent("1234567894562");
        //  studentRepository.removeStudent("2234567894562");


        studentRepository.retrieveSutdentsbyAge(32);

        System.out.println(studentRepository.getStudentByName());



    }

}