import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    //Write your ComboLockRun code here, it will be easiest, don't worry about making a separate file.
    Scanner input = new Scanner(System.in); //initializing scanner
    ComboLock yo = new ComboLock("a"); //setting up object
    boolean i=false; 
    while (i==false){    //while loop 
      System.out.println("1) Turn the lock to the right to a number");//input options
      System.out.println("2) Turn the lock to the left to a number");
      System.out.println("3) Open the lock");
      System.out.println("4) Retrieve the combination");
      System.out.print("Enter A Command: "); //input
      String choice = input.nextLine().toLowerCase(); //recording input
      if (choice.equals("1")){  //turn lock right
        System.out.print("Enter number to turn right to: ");
        int number = input.nextInt();
        input.nextLine();
        yo.turnRight(number);
      }
      else if (choice.equals("2")){ //turn lock left
        System.out.print("Enter number to turn left to: ");
        int number = input.nextInt();
        input.nextLine();
        yo.turnLeft(number);
      }
      else if (choice.equals("3")){ //open lock
        if (yo.isOpen()) {
          System.out.println("The lock was opened");
          i=true; //end the while loop once lock is open
        }
        else {
          System.out.println("The lock remains locked");
        }
      }
      else if (choice.equals("4")){ //retrieve password 
        System.out.print("Enter your password: ");
        String guess = input.nextLine();
        System.out.println(yo.forgotNumbers(guess)[0] + ", " + yo.forgotNumbers(guess)[1] + ", " + yo.forgotNumbers(guess)[2]);
      }
    }
  }
}