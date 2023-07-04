import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class WindowDeposit extends JFrame {

  private JPanel contentPane;
  private JTextField textFieldOne;
  private BankAccount chequing;
  private BankAccount savings;
  private String accountName;

 

  // Create the frame
  public WindowDeposit(String accountName,BankAccount chequing,BankAccount savings) {
    this.chequing = chequing; //recording account information
    this.savings = savings;
    this.accountName = accountName;
    setTitle("Depositing"); //setting up window 
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(0, 0, 330, 330); 
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setLayout(null); 
    setContentPane(contentPane);

    JLabel lblNumberOne = new JLabel("Amount");  
    lblNumberOne.setBounds(65, 46, 90, 21);
    contentPane.add(lblNumberOne);

    textFieldOne = new JTextField();
    textFieldOne.setBounds(160, 46, 96, 20); 
    textFieldOne.setColumns(10); 
    contentPane.add(textFieldOne);
    

    JButton btnCalculate = new JButton("Done");
    btnCalculate.addMouseListener(new MouseAdapter() { //event handler acting when the button is pressed to calculate the balance 
      public void mouseClicked(MouseEvent e) {
        calculate();
      }
    });
    btnCalculate.setBounds(115, 155, 100, 25);
    contentPane.add(btnCalculate);

    JButton cancel = new JButton("Cancel");
    cancel.setBounds(115, 225, 100, 25);
    contentPane.add(cancel);
    cancel.addMouseListener(new MouseAdapter() { //event handler returning you back to windowOne if button is clicked  
      public void mouseClicked(MouseEvent e) {
        returnBack();
      }
    });
  }

  public void calculate() { //updating balance of the respective account 
    double currentBalance = Double.valueOf(textFieldOne.getText());
    if(accountName.equals("Chequing")){
      chequing.deposit(currentBalance,"Deposit"); //description is provided for transactions
    } else {
      savings.deposit(currentBalance,"Deposit");
    }
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
  public void returnBack(){//activated when cancel button is pressed
    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }
}