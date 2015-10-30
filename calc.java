import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by kururu on 2015/10/29.
 */
public class calc extends JFrame implements ActionListener {

    private static final String CALCULATOR_TITLE = "My simple calculator";

    private static JPanel panelOfCalc;

    static JButton
            equalsButton, clearButton,
            plusMinusButton,decimalButton;

    static JTextField ansField;

    static Double
            firstNum, secondNum, ans, plusminus;
    static boolean
            addclick = false, subclick = false, mulclick = false, divclick = false,
            clearfield;

    public calc(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(CALCULATOR_TITLE);
        setResizable(false);
        setSize(230,370);
        setLocationRelativeTo(null);
        panelOfCalc = new JPanel();
        panelOfCalc.setSize(230,370);
        panelOfCalc.setLayout(null);

        //set operation buttons
        makeOperationButton("+",50,50,170,175);
        makeOperationButton("-",50,50,170,120);
        makeOperationButton("*",50,50,170,65);
        makeOperationButton("/",50,50,115,65);

        //set number buttons
        makeNumberButton("0",105,50,5,285);
        makeNumberButton("1",50,50,5,230);
        makeNumberButton("2",50,50,60,230);
        makeNumberButton("3",50,50,115,230);
        makeNumberButton("4",50,50,5,175);
        makeNumberButton("5",50,50,60,175);
        makeNumberButton("6",50,50,115,175);
        makeNumberButton("7",50,50,5,120);
        makeNumberButton("8",50,50,60,120);
        makeNumberButton("9",50,50,115,120);

        //set other buttons
        equalsButton = new JButton("=");
        equalsButton.setSize(50,105);
        equalsButton.setLocation(170,230);
        clearButton = new JButton("C");
        clearButton.setSize(50,50);
        clearButton.setLocation(5,65);
        decimalButton = new JButton(".");
        decimalButton.setSize(50,50);
        decimalButton.setLocation(115,285);
        plusMinusButton = new JButton("+/-");
        plusMinusButton.setSize(50,50);
        plusMinusButton.setLocation(60,65);



        ansField = new JTextField();
        ansField.setBackground(Color.WHITE);
        ansField.setSize(215,50);
        ansField.setLocation(5,10);
        ansField.setFont(new Font("Arial",Font.BOLD,24));
        ansField.setEditable(false);
        panelOfCalc.add(ansField);
        panelOfCalc.add(plusMinusButton);
        panelOfCalc.add(decimalButton);
        panelOfCalc.add(equalsButton);
        panelOfCalc.add(clearButton);

        add(panelOfCalc);

        clearButton.addActionListener(this);
        decimalButton.addActionListener(this);
        plusMinusButton.addActionListener(this);
        equalsButton.addActionListener(this);
    }

    public void makeOperationButton(final String operationName, int widthOfSize, int heightOfSize, int xLocation, int yloction){
        final JButton operationButton = new JButton(operationName);
        operationButton.setSize(widthOfSize,heightOfSize);
        operationButton.setLocation(xLocation,yloction);
        panelOfCalc.add(operationButton);
        operationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ansField.getText()!= null){
                    if(operationName == "+")
                        addclick = true;
                    if(operationName == "-")
                        subclick = true;
                    if(operationName == "*")
                        mulclick = true;
                    if(operationName == "/")
                        divclick = true;
                    firstNum = Double.parseDouble(String.valueOf(ansField.getText()));
                    clearfield = true;
                }
            }
        });
    }

    public void makeNumberButton(final String numberName, int widthOfSize, int heightOfSize, int xLocation, int yloction) {
        final JButton numberButton = new JButton(numberName);
        numberButton.setSize(widthOfSize, heightOfSize);
        numberButton.setLocation(xLocation, yloction);
        panelOfCalc.add(numberButton);
        numberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == numberButton) {
                    if (ansField.getText().length() < 20) {
                        if (clearfield) {
                            ansField.setText(numberName);
                            clearfield = false;
                        } else {
                            ansField.setText(ansField.getText() + numberName);
                        }
                    }
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == equalsButton){
            secondNum = Double.parseDouble(String.valueOf(ansField.getText()));
            ansField.setText(ansField.getText());
            if(addclick){
                ans = firstNum + secondNum;
                ansField.setText(String.valueOf(ans));
                addclick = false;
            }
            if(subclick){
                ans = firstNum - secondNum;
                ansField.setText(String.valueOf(ans));
                subclick = false;
            }
            if(mulclick){
                ans = firstNum * secondNum;
                ansField.setText(String.valueOf(ans));
                mulclick = false;
            }
            if(divclick){
                ans = firstNum / secondNum;
                if(secondNum == 0.0)
                    ansField.setText("ERROR !");
                else
                    ansField.setText(String.valueOf(ans));
                divclick = false;
            }
        }

        if(e.getSource()==plusMinusButton){
            if(ansField.getText().equals("")|| ansField.getText().equals("-")){
                ansField.setText("-");
            }else{
                plusminus=(Double.parseDouble(String.valueOf(ansField.getText())));
                plusminus *= -1;
                ansField.setText(String.valueOf(plusminus));
            }
        }

        if(e.getSource()==clearButton){
            ansField.setText("");
            firstNum = 0.0;
            secondNum = 0.0;
            addclick = false;
            subclick = false;
            mulclick = false;
            divclick = false;
        }

        if(e.getSource()==decimalButton){
            if(ansField.getText().contains(".")){
                ansField.setText(ansField.getText());
            }else{
                ansField.setText(ansField.getText()+".");
            }
        }
    }
}
