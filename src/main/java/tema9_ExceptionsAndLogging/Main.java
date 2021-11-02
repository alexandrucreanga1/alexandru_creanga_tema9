package tema9_ExceptionsAndLogging;

import tema9_ExceptionsAndLogging.exceptions.AgeValidationException;
import tema9_ExceptionsAndLogging.exceptions.EmptyInputException;
import tema9_ExceptionsAndLogging.exceptions.IncorrectSearchException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {




    static StudentRepository studentRepository = new StudentRepository();


    public static void main(String[] args) throws AgeValidationException, ParseException, EmptyInputException, NumberFormatException, IncorrectSearchException {


        //Setting up logger file
        Logger loggers  = Logger.getLogger(Main.class.getName());
        FileHandler fh;
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler("C:/Users/Sania/exceptions.log");
            loggers.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }




         loggers.log(Level.INFO, "Creating new students, ihuuu!");
        Student student1 = studentRepository.addNewStudent("Gheorghita","Ion","20-01-1989",Gender.M,"1234567890123");
        Student student2 = studentRepository.addNewStudent("Elena","Bejan","15-03-1993",Gender.F,"1234567895436");
        Student student3 = studentRepository.addNewStudent("Alina","Stelian","04-06-1992",Gender.F,"1234567892678");
        Student student4 = studentRepository.addNewStudent("Stejara","Parcan","10-09-1995",Gender.F,"1234567897952");
        Student student5 = studentRepository.addNewStudent("Vasile","Chifeac","18-03-1990",Gender.M,"1234567893421");
        Student student6 = studentRepository.addNewStudent("Mihai","Lupascu","13-04-1991",Gender.M,"1234567894562");
        Student student7 = studentRepository.addNewStudent("Mihaela","Movila","25-02-1989",Gender.M,"1234567899632");

        //line for testing of missing inputs !
        //Student student8 = studentRepository.addNewStudent("","Movila","25-02-1989",Gender.M,"1234567899632");

        System.out.println(studentRepository.getStudentByName());

        studentRepository.removeStudent("1234567894562");
        //  studentRepository.removeStudent("2234567894562");


        studentRepository.retrieveSutdentsbyAge(32);

        System.out.println(studentRepository.getStudentByName());



        //Sorting Students by name >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        System.out.println("Sorting Students by lastName: ");
        studentRepository.sortStudentByLastName(studentRepository.getStudentByName());


    }


//TODO - (optional) list students order by Birth Date !!



    public static class StudentRepository {

        Logger loggers  = Logger.getLogger(Main.class.getName());
        List<Student> studentByName = new ArrayList<>();

        public Student addNewStudent (String firtsName, String lastName, String dayofbithDDmmYYYY, Gender gender, String idCNP) throws ParseException, EmptyInputException, NumberFormatException {

            if(firtsName.isEmpty() || lastName.isEmpty() || dayofbithDDmmYYYY.length()==0 || idCNP.length()==0) {
                loggers.log(Level.WARNING, "Something is wrong,OOps!(empty fields)");
                throw new EmptyInputException("Oops, something is wrong. Please be sure to put in data in all relevant fields!");

            }

            if(idCNP.length() != 13) {
                loggers.log(Level.WARNING, "Something is wrong,OOps!(wrong lenght)");
                throw new EmptyInputException("Oops, something is wrong. Please be sure your idCNP has 13 digits");
            }

            validateDateOfBirth (dayofbithDDmmYYYY);

            loggers.log(Level.INFO, "Good Job, adding new student!");
            Student studentToAdd = new Student(firtsName,lastName,dayofbithDDmmYYYY,gender,idCNP);
            studentByName.add(studentToAdd);
            return studentToAdd;
        }

        public void sortStudentByLastName (List<Student> studentByName) {
            studentByName.sort(Comparator.comparing(Student::getLastName));
            System.out.println(studentByName);
        }



        private void validateDateOfBirth(String dayofbithDDmmYYYY) throws NumberFormatException {
            String[] tokens = dayofbithDDmmYYYY.split("-");
            int day = Integer.parseInt(tokens[0]);
            int month = Integer.parseInt(tokens[1]);
            int year = Integer.parseInt(tokens[2]);
            if (day <1 || day >30) {
                loggers.log(Level.WARNING, "Something is wrong,OOps!(not good day)");
                throw new NumberFormatException("Hey, please reconsider your day!");
            }
            if (month <1 || month >12) {
                loggers.log(Level.WARNING, "Something is wrong,OOps!(not good month)");
                throw new NumberFormatException("Hey, please reconsider your Month!");
            }
            if (year <1900) {
                loggers.log(Level.WARNING, "Something is wrong,OOps!(not good year)");
                throw new NumberFormatException("Hey, please reconsider your year!");
            }
        }


        public void removeStudent (String idCNP) throws EmptyInputException, IncorrectSearchException {
            boolean found = false;
            if(idCNP.length() != 13) {
                loggers.log(Level.WARNING, "Something is wrong,OOps!(wrong lenght for removing)");
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
                loggers.log(Level.INFO, "Student doesn't exist)");
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









}