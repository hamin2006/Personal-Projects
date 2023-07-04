import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    JPanel contentPane;
    JTextField nums;
    JLabel op;
    JLabel error;
    JTextField num1;
    JTextField num2;
    boolean s;
    int ops = 1;
    double answer;
    public static void main(String[] args) {
        Main harsh = new Main();
        harsh.setVisible(true);
        harsh.setResizable(false);
    }
    public Main(){
        setTitle("Calculator"); //frame setup
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(0, 0, 400, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        Color teal = new Color(67, 138, 138);
        contentPane.setBackground(teal);
        nums = new JTextField();
        nums.setBounds(15, 50, 350, 50);
        nums.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        nums.setVisible(false);
        nums.setEditable(false);
        nums.setHorizontalAlignment(JTextField.CENTER);
        nums.setBackground(Color.white);
        contentPane.add(nums);
        op = new JLabel("+");
        op.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        op.setBounds(180, 60, 30, 30);
        contentPane.add(op);
        error = new JLabel();
        error.setFont(new Font("Times New Roman", Font.PLAIN, 37));
        error.setBounds(15, 5, 350, 50);
        contentPane.add(error);
        num1 = new JTextField();
        num1.setBounds(15, 50, 100, 50);
        num1.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        contentPane.add(num1);
        num2 = new JTextField();
        num2.setBounds(265, 50, 100, 50);
        num2.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        contentPane.add(num2);
        Button add = new Button("+");
        Button mul = new Button("x");
        Button div = new Button("÷");
        Button sub = new Button("-");
        Button enter = new Button("=");
        add.setBounds(15,150,150,100);
        sub.setBounds(215,150,150,100);
        mul.setBounds(15,300,150,100);
        div.setBounds(215,300,150,100);
        enter.setBounds(15,450,350,100);
        add.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        sub.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        mul.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        div.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        enter.setFont(new Font("Times New Roman", Font.PLAIN, 50));
        contentPane.add(add);
        contentPane.add(sub);
        contentPane.add(div);
        contentPane.add(mul);
        contentPane.add(enter);
        add.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                op.setText("+");
                error.setText("");
                nums.setVisible(false);
                num1.setVisible(true);
                num2.setVisible(true);
                ops = 1;
            }
        });
        sub.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                op.setText(" -");error.setText("");
                nums.setVisible(false);
                num1.setVisible(true);
                num2.setVisible(true);
                ops = 2;
            }
        });
        mul.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                op.setText("x");error.setText("");
                nums.setVisible(false);
                num1.setVisible(true);
                num2.setVisible(true);
                ops = 3;
            }
        });
        div.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                op.setText("÷");error.setText("");
                nums.setVisible(false);
                num1.setVisible(true);
                num2.setVisible(true);
                ops = 4;
            }
        });
        enter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                nums.setVisible(true);
                num1.setVisible(false);
                num2.setVisible(false);
                calculate();
                if (s){
                    nums.setText("");
                }
            }
        });

    }
    public void calculate(){
        try {
            double one;
            double two;
            if (String.valueOf(num1.getText()).equals("")){
                one = 0;
            }else if (String.valueOf(num1.getText()).equals("ans")){
                one = answer;
            } else{
                one = Double.valueOf(num1.getText());
            }
            if (String.valueOf(num2.getText()).equals("")){
                two = 0;
            }else{
                two = Double.valueOf(num2.getText());
            }
            if (ops == 1) {
                answer = one + two;
            } else if (ops == 2) {
                answer = one - two;
            } else if (ops == 3) {
                answer = one * two;
            } else if (ops == 4) {
                answer = one / two;
            }

            if (Math.round(answer) == answer) {
                int yo = (int) answer;
                nums.setText(String.valueOf(yo));
            } else {
                nums.setText(String.valueOf(answer));
            }
            if (String.valueOf(nums.getText()).equals("NaN")){
                nums.setText("Undefined");
            }
        }catch (NumberFormatException e){
            s = true;
            error.setForeground(Color.red);
            error.setText("Please Enter A Number");
        }
    }
}
