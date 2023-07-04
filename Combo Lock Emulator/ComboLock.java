import java.util.ArrayList; //imports
import java.util.Arrays;
class ComboLock{
  private int secretOne; //instance fields
  private int secretTwo;
  private int secretThree;
  private String password;
  public ComboLock(String password){ //first constructor creating 3 random unequal numbers between 1-60
    this.password=password;
    secretOne = (int)(Math.random() * (60)+1);
    secretTwo = (int)(Math.random() * (60)+1);
    secretThree = (int)(Math.random() * (60)+1);
    while ((secretOne == secretTwo) || (secretOne == secretThree) || (secretTwo == secretThree)){
      secretTwo = (int)(Math.random() * (60)+1);
      secretThree = (int)(Math.random() * (60)+1);
    }
  }
  public ComboLock(int secretOne,int secretTwo,int secretThree){ //second contructor
    this.secretOne=secretOne;
    this.secretTwo=secretTwo;
    this.secretThree=secretThree;
    password = null;
  }
  public ComboLock(int secretOne,int secretTwo,int secretThree,String password){ //third constructor
    this.secretOne=secretOne;
    this.secretTwo=secretTwo;
    this.secretThree=secretThree;
    this.password=password;
  }//arraylists
  ArrayList<Integer> secrets = new ArrayList<>(Arrays.asList(0,0,0));
	ArrayList<String> side = new ArrayList<>(Arrays.asList("","",""));
	public void turnRight(int number) { //method to turn right
		secrets.add(number); //updating arraylist
		secrets.remove(0);
		side.add("right");
		side.remove(0);
	}
	public void turnLeft(int number) { //method to turn left
		secrets.add(number); //updating arraylist
		secrets.remove(0);
		side.add("left");
		side.remove(0);
	}
	public Boolean isOpen() { //method determining if the lock is open
		if (secretOne==secrets.get(0) && side.get(0)=="right" &&secretTwo==secrets.get(1) && side.get(1)=="left" && secretThree==secrets.get(2) && side.get(2)=="right"){
      return true;
    }else{
      return false;
    }
	}
	public int[] forgotNumbers(String passwordGuess) { //method to return passcode
		if (password.equals(passwordGuess)) {
			int[] combo = {secretOne, secretTwo, secretThree};
			return combo;
		}
		else {
			return null;
		}
	}
}