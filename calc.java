import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by kururu on 2015/10/29.
 */
public class calc extends JFrame implements ActionListener {

    private static final String CALCULATOR_TITLE = "Calculator";

    private static JPanel panelOfCalc;

    static JButton
            equalsButton, clearButton,
            plusMinusButton,decimalButton;

    static JTextField ansField;

    static JMenu fileMenu,managementMenu,helpMenu;

    static JMenuItem exit,scientific,about;

    static Double
            firstNum, secondNum, ans, plusminus;
    static boolean
            addclick = false, subclick = false, mulclick = false, divclick = false, powerclick = false, sqrtclick = false,
            clearfield;

    public calc(){

        JMenuBar menkar = new JMenuBar();
        setMenuBar(menkar);
        setJMenuBar(menkar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(CALCULATOR_TITLE);
        setResizable(false);
        setSize(310,525);
        setLocationRelativeTo(null);
        panelOfCalc = new JPanel();
        panelOfCalc.setSize(310,525);
        panelOfCalc.setLayout(null);

        //set operation buttons
        makeOperationButton("+",70,70,230,310);
        makeOperationButton("-",70,70,230,235);
        makeOperationButton("*",70,70,230,160);
        makeOperationButton("/",70,70,230,85);
        makeOperationButton("^2",70,70,155,85);
        makeOperationButton("√",70,70,230,385);

        //set number buttons
        makeNumberButton("0",70,70,5,385);
        makeNumberButton("1",70,70,5,310);
        makeNumberButton("2",70,70,80,310);
        makeNumberButton("3",70,70,155,310);
        makeNumberButton("4",70,70,5,235);
        makeNumberButton("5",70,70,80,235);
        makeNumberButton("6",70,70,155,235);
        makeNumberButton("7",70,70,5,160);
        makeNumberButton("8",70,70,80,160);
        makeNumberButton("9",70,70,155,160);

        //set other buttons
        equalsButton = new JButton("=");
        equalsButton.setSize(70,70);
        equalsButton.setLocation(155,385);
        equalsButton.setFont(new Font("Arial", Font.BOLD, 24));
        clearButton = new JButton("C");
        clearButton.setSize(70,70);
        clearButton.setLocation(5, 85);
        clearButton.setFont(new Font("Arial", Font.BOLD, 24));
        decimalButton = new JButton(".");
        decimalButton.setSize(70,70);
        decimalButton.setLocation(80,385);
        decimalButton.setFont(new Font("Arial", Font.BOLD, 24));
        plusMinusButton = new JButton("+/-");
        plusMinusButton.setSize(70,70);
        plusMinusButton.setLocation(80,85);
        plusMinusButton.setFont(new Font("Arial", Font.BOLD, 24));



        ansField = new JTextField();
        ansField.setBackground(Color.WHITE);
        ansField.setSize(295,70);
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

    private void setMenuBar(JMenuBar menkar) {

        fileMenu = new JMenu("fileMenu");
        managementMenu = new JMenu("managementMenu");
        helpMenu = new JMenu("help");

        exit = new JMenuItem("exit");
        scientific = new JMenuItem("scientific");
        about = new JMenuItem("about");

        menkar.add(fileMenu);
        menkar.add(managementMenu);
        menkar.add(helpMenu);

        fileMenu.add(exit);
        managementMenu.add(scientific);
        helpMenu.add(about);

        exit.addActionListener(this);
        scientific.addActionListener(this);
        about.addActionListener(this);

    }

    public void makeOperationButton(final String operationName, int widthOfSize, int heightOfSize, int xLocation, int yloction){
        final JButton operationButton = new JButton(operationName);
        operationButton.setFont(new Font("Arial",Font.BOLD,24));
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
                    if(operationName == "^2")
                        powerclick = true;
                    if(operationName == "√")
                        sqrtclick = true;
                    firstNum = Double.parseDouble(String.valueOf(ansField.getText()));
                    clearfield = true;
                }
            }
        });
    }

    public void makeNumberButton(final String numberName, int widthOfSize, int heightOfSize, int xLocation, int yloction) {
        final JButton numberButton = new JButton(numberName);
        numberButton.setFont(new Font("Arial",Font.BOLD,24));
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

        if(e.getSource() == exit){
            System.exit(0);
        }

        if(e.getSource() == scientific){
            JOptionPane.showMessageDialog(null, "Tips: science-based untapped！","scientific",JOptionPane.WARNING_MESSAGE);
        }

        if(e.getSource() == about){
            JOptionPane.showMessageDialog(null, "A simple GUI calculator made by kururu007 in whut.\n"+"Thank you for use!","about",JOptionPane.PLAIN_MESSAGE);
        }

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
            if(powerclick){
                secondNum = firstNum;
                //ans = firstNum * secondNum;
                ans = Math.pow(firstNum,2);
                ansField.setText(String.valueOf(ans));
                powerclick = false;
            }
            if(sqrtclick){
                ans = Math.sqrt(firstNum);
                ansField.setText(String.valueOf(ans));
                sqrtclick = false;
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
            powerclick = false;
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
