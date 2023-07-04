import javax.swing.*; //imports
import java.awt.event.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
public class WindowOne extends JFrame implements WindowListener, Serializable{
  //creating an object for each account 
  BankAccount chequing = new BankAccount("Chequing",0,0,0); 
  BankAccount savings = new BankAccount("Savings",0,0,0);
  Serializer a = new Serializer();
  //instance fields 
  private JPanel contentPane;
  private JLabel labelHello;
  private JLabel labelHello1;
  private JTextField textField;
  static JComboBox accounts;
  private WindowDeposit two;
  private WindowWithdraw one;
  private WindowTransactions three;
  private String accountName;
  public double findBal(String accountName) { //a method which returns the double value of the current balance in either account using the BankAccount.java file variable
    double bal=0;
    if (accountName.equals("Chequing")) {
      bal = chequing.balance;
    }else if (accountName.equals("Savings")) {
      bal = savings.balance;
    }
    return bal;
  }
  public static void main(String[] args) { //in order to run the GUI
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          WindowOne frame = new WindowOne();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
  public WindowOne() {
    if (a.deserializeToObject("chequing.txt")==(null)) { //deserializing chequing account object
      chequing = new BankAccount("Chequing",0,0,0);
    } else {
      chequing = (BankAccount) a.deserializeToObject("chequing.txt");
    }
    if (a.deserializeToObject("savings.txt")==(null)) { //deserializing savings account object
      savings = new BankAccount("Savings",0,0,0);
    } else {
      savings = (BankAccount) a.deserializeToObject("savings.txt");
    }
    accountName = "Chequing"; //setting up chequing as initial account as it will appear as the first option on the dropbox
    setTitle("Harsh Banking"); //frame setup 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    setBounds(0, 0, 330, 330); 
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); 
    contentPane.setLayout(null); 
    setContentPane(contentPane);
    labelHello = new JLabel("Account:");  //labels
    labelHello.setHorizontalAlignment(JTextField.CENTER); 
    labelHello.setBounds(80, 50, 70, 50);
    contentPane.add(labelHello);
    labelHello1 = new JLabel("Your Current Balance:");    
    labelHello1.setHorizontalAlignment(JTextField.CENTER); 
    labelHello1.setBounds(30, 200, 170, 20);
    contentPane.add(labelHello1);
    textField = new JTextField(); //textfield showing the current balance in the respective account
    textField.setEditable(false); //making the textfield uneditable so the balance isn't visibly changeable by the user 
    textField.setBounds(200, 200, 100, 20); 
    contentPane.add(textField);
    if(chequing.balance>=0){ // if bal is not overdrawn set the textfield to display the balance of the respective account in a black font
      textField.setForeground(Color.BLACK);
      textField.setText("$" + String.format("%.2f",chequing.balance));
    }else if (chequing.balance<0) { //if bal is overdrawn set the textfield to display the absolute value of the balance of its respective account in a red font
      textField.setForeground(Color.RED);
      textField.setText("$" + String.format("%.2f",Math.abs(chequing.balance)));
    }
    JButton btnPress = new JButton("Deposit"); //buttons
    JButton btnPress1 = new JButton("Withdraw");
    JButton btnPress2 = new JButton("Transactions");
    btnPress.setBounds(35, 235, 100, 30);
    btnPress1.setBounds(190, 235, 110, 30);
    btnPress2.setBounds(95, 150, 150, 30);
    contentPane.add(btnPress);
    contentPane.add(btnPress1);
    contentPane.add(btnPress2);
    String [] accountSelect={"Chequing","Savings"}; //creating dropbox
    accounts= new JComboBox(accountSelect);
    accounts.setBounds(175, 60, 100, 30);
    contentPane.add(accounts);

    accounts.addActionListener(new ActionListener() { //finding balance depending on the account chosen from the dropbox 
      public void actionPerformed(ActionEvent e) {
        double bal = 0; //creating bal 
        if (accounts.getSelectedItem().equals("Savings")) {
          accountName = "Savings"; //resetting the account name if savings is chosen 
          bal = savings.balance; //finding balance of savings 
        }else if (accounts.getSelectedItem().equals("Chequing")) {
          accountName = "Chequing";//resetting the account name if chequing is chosen 
          bal = chequing.balance; //finding balance of chequing 
        }
        if(bal>=0){ // if bal is not overdrawn set the textfield to display the balance of the respective account in a black font 
          textField.setForeground(Color.BLACK);
          textField.setText("$" + String.format("%.2f",bal));
        }else if (bal<0) { //if bal is overdrawn set the textfield to display the absolute value of the balance of its respective account in a red font 
          textField.setForeground(Color.RED);
          textField.setText("$" + String.format("%.2f",Math.abs(bal)));
        }
      }
    });
    btnPress.addMouseListener(new MouseAdapter() { //if the deposit button is pressed the event handler will open the deposit window
      public void mouseClicked(MouseEvent e) {
        openWindowDeposit();
      }
      
    });
    btnPress1.addMouseListener(new MouseAdapter() { //if the withdraw button is pressed the event handler will open the withdraw window
      public void mouseClicked(MouseEvent e) {
        openWithdraw();
      }
    });
    btnPress2.addMouseListener(new MouseAdapter() { //if the Transactions button is pressed the event handler will open the Transactions window
      public void mouseClicked(MouseEvent e) {
        openTransaction();
      }
    });
  }
  public void openWindowDeposit(){
    two = new WindowDeposit(accountName, chequing, savings); //transferring the data of both accounts and account chosen 
    two.addWindowListener(this);
    two.setVisible(true);
  }
  public void openWithdraw(){
    one = new WindowWithdraw(accountName, chequing, savings);
    one.addWindowListener(this); 
    one.setVisible(true);
  }
  public void openTransaction(){
    three = new WindowTransactions(accountName, chequing, savings);
    three.addWindowListener(this); 
    three.setVisible(true);
  }
  
  public void windowOpened(WindowEvent e) {
  }
  public void windowClosing(WindowEvent e) {
    a.serialize(chequing,"chequing.txt"); //serialize both objects as the main window closes
    a.serialize(savings,"savings.txt");

  }
  public void windowClosed(WindowEvent e) {  
    if(findBal(accountName)>=0){ //refreshing the textfield 
      textField.setForeground(Color.BLACK);
      double bal=findBal(accountName);
      textField.setText("$" + String.format("%.2f",bal));
    }else if (findBal(accountName)<0) {
      textField.setForeground(Color.RED);
      double bal=findBal(accountName);
      textField.setText("$" + String.format("%.2f",Math.abs(bal)));
    }
  }
  public void windowIconified(WindowEvent e) {
  }
  public void windowDeiconified(WindowEvent e) {
  }
  public void windowActivated(WindowEvent e) {
  }
  public void windowDeactivated(WindowEvent e) {
  }
}