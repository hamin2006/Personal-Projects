import java.time.*;
import java.util.*;
public class Human implements Comparable<Human>{
  final int birthYear;
  final int birthMonth;
  final int birthDay;
  String firstName;
  String lastName;
  Gender gender;
  //Constructor
  public Human(int birthYear, int birthMonth, int birthDay, String firstName, String lastName, Gender gender) {
    this.birthYear = birthYear;
    this.birthMonth = birthMonth;
    this.birthDay = birthDay;
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    }
    //Mutators
  public void setFirstName(String firstName) {
    this.firstName = firstName;
    }
  public void setLastName(String lastName) {
    this.lastName = lastName;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    //Accessors
    public int getBirthYear() {
        return birthYear;
    }
    public int getBirthMonth() {
        return birthMonth;
    }
    public int getBirthDay() {
        return birthDay;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Gender getGender() {
        return gender;
    }
    //Methods
    int calculateCurrentAgeInYears(){
        int age;
        LocalDate harsh = LocalDate.now();
        String date = harsh.toString();
        String[] values = date.split("-");
        if (birthMonth<Integer.valueOf(values[1]) || birthMonth==Integer.valueOf(values[1])&&birthDay<=Integer.valueOf(values[2])) {
            age = Integer.valueOf(values[0]) - birthYear;
        }else{
            age = Integer.valueOf(values[0]) - birthYear-1;
        }
        return age;
    }
    LocalDate createBirthdate(){
      return LocalDate.of(birthYear,Month.of(birthMonth),birthDay);
    }
    //defauilt comparator
    public int compareTo(Human n) {
        return createBirthdate().compareTo(n.createBirthdate());
    }

    static Comparator<Human> AGE_ORDER = new Comparator<Human>() { //ordering by age
      public int compare(Human person1, Human person2) {
        return person1.createBirthdate().compareTo(person2.createBirthdate());
      }
    };
  static Comparator<Human> NAME_ORDER = new Comparator<Human>() { //ordering by name
    public int compare(Human person1, Human person2){
    return (person1.lastName+person1.firstName).compareTo((person2.lastName+person2.firstName));
    }
  };
  static Comparator<Human> ASSEMBLY_ORDER = new Comparator<Human>(){ 
    public int compare(Human person1, Human person2){
      String Compare1 = "";
      String Compare2 = "";
      if (person1 instanceof Human){ //creating comparable hierarchy
        if (person1 instanceof HenryWiseWoodStudent){
          Compare1 = "1";
        }else if (person1 instanceof Youth){
          Compare1 = "2";
        }else if (person1 instanceof Adult){
          Compare1 = "3";
        }else {
          Compare1 = "4";
        }
      }
      if (person2 instanceof Human){
        if(person2 instanceof HenryWiseWoodStudent){
          Compare2 = "1";
        }else if(person2 instanceof Youth){
          Compare2 = "2";
        }else if(person2 instanceof Adult){
          Compare2 = "3";
        }else{
          Compare2 = "4";
        }
      }
    return (Compare1+person1.lastName+person1.firstName).compareTo((Compare2+person2.lastName+person2.firstName));
    }
  };
  
  
}
    
   


