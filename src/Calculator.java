/**
 *
 * @author ekuri
 */

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.event.*;

// Main Class of whole project
public class Calculator {
    public static void main(String args[]) {
        
        // Create GUI
        CalculatorGUI gui = new CalculatorGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}

class CalculatorGUI extends JFrame {
    
    // Constructor
    public CalculatorGUI() {
        // Construct super class explicitily
        super();
        
        // initial all widgets, controls and event listener
        this.init();
    }
    
    // event listener of operand button("+", "-", "*", "/")
    private OperandButtonClickListener operand;
    // event listener of "ok" button
    private ResultButtonClickListener resultListener;
    
    // initial all widgets, controls and event listener
    private void init() {
        // set initial size of main frame
        this.setSize(400, 80);
        
        // instantiate event listener
        operand = new OperandButtonClickListener();
        operand.setTargetLabel(this.getOperandLabel());
        resultListener = new ResultButtonClickListener();
        resultListener.setTarget(this);
        
        // instantiate grid layout with 5 columns
        GridLayout layout = new GridLayout(0, 5);
        // set layout gap to 2px
        layout.setHgap(2);
        layout.setVgap(2);
        this.getContentPane().setLayout(layout);
        
        // create all the labels, buttons and text-fields
        // the order is very important
        this.add(this.getLeftOperandTextField());
        this.add(this.getOperandLabel());
        this.add(this.getRightOperandTextField());
        this.add(this.getEqualSignLabel());
        this.add(this.getResultTextField());
        this.add(this.getPlusOperandButton());
        this.add(this.getSubstractOperandButton());
        this.add(this.getProductOperandButton());
        this.add(this.getDivisionOperandButton());
        this.add(this.getCalculateButton());
    }
    
    
    private JTextField leftOperandTextField;
    private JTextField rightOperandTextField;
    private JLabel resultTextField;
    private JTextField getLeftOperandTextField() {
        if (this.leftOperandTextField == null) {
            this.leftOperandTextField = new JTextField();
            
            // set the text alignment to center
            this.leftOperandTextField.setHorizontalAlignment(JTextField.CENTER);
        }
        return this.leftOperandTextField;
    }
    private JTextField getRightOperandTextField() {
        if (this.rightOperandTextField == null) {
            this.rightOperandTextField = new JTextField();
            // set the text alignment to center
            this.rightOperandTextField.setHorizontalAlignment(JTextField.CENTER);
        }
        return this.rightOperandTextField;
    }
    private JLabel getResultTextField() {
        if (this.resultTextField == null) {
            // create label with no text and set the text alignment to center
            this.resultTextField = new JLabel("", JLabel.CENTER);
        }
        return this.resultTextField;
    }
    
    private JLabel operandLabel;
    private JLabel equalSignLabel;
    private JLabel getOperandLabel() {
        if (this.operandLabel == null) {
            // create label with "+" sign and set the text alignment to center
            this.operandLabel = new JLabel("+", JLabel.CENTER);
        }
        return this.operandLabel;
    }
    private JLabel getEqualSignLabel() {
        if (this.equalSignLabel == null) {
            // create label with "-" sign and set the text alignment to center
            this.equalSignLabel = new JLabel("=", JLabel.CENTER);
        }
        return this.equalSignLabel;
    }
    
    private class OperandButtonClickListener implements ActionListener {
        // the target label is the label which show current operand
        JLabel targetLabel;
        public void setTargetLabel(JLabel target) {
            this.targetLabel = target;
        }
        
        // override funtion to change the text of target label
        @Override
        public void actionPerformed(ActionEvent e) {
            this.targetLabel.setText(e.getActionCommand());
        }
    }
    private class ResultButtonClickListener implements ActionListener {
        // targetGUI should be the main frame
        CalculatorGUI targetGUI;
        public void setTarget(CalculatorGUI target) {
            this.targetGUI = target;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            double leftOperand = 0;
            double rightOperand = 0;
            
            // error may be occur when convert string to number
            try {
                leftOperand = Double.parseDouble(this.targetGUI.getLeftOperandTextField().getText());
                rightOperand = Double.parseDouble(this.targetGUI.getRightOperandTextField().getText());
            } catch(java.lang.NumberFormatException err) {
                JOptionPane.showMessageDialog(null, "Operand format incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // calculate the output according to operand
            double result = 0;
            switch(this.targetGUI.getOperandLabel().getText()) {
                case "+":
                    result = leftOperand + rightOperand;
                    break;
                case "-":
                    result = leftOperand - rightOperand;
                    break;
                case "*":
                    result = leftOperand * rightOperand;
                    break;
                case "/":
                    if (rightOperand == 0) {
                    	JOptionPane.showMessageDialog(null, "Can not divide zero!", "Error", JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
                    result = leftOperand / rightOperand;
                    break;
                default:
                    // the next sentence of code shell never run
                    JOptionPane.showMessageDialog(null, "unknown operand!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            
            // the result label show operation result
            this.targetGUI.getResultTextField().setText(String.valueOf(result));
        }
    }
    
    
    // functions that get operand button, which will be created if not exist
    private JButton plusOperandButton;
    private JButton substractOperandButton;
    private JButton productOperandButton;
    private JButton divisionOperandButton;
    private JButton calculateButton;
    private JButton getPlusOperandButton() {
        if (this.plusOperandButton == null) {
            this.plusOperandButton = new JButton();
            //this.plusOperandButton.setBounds(0, 30, 80, 50);
            this.plusOperandButton.setText("+");
            this.plusOperandButton.addActionListener(this.operand);
        }
        return this.plusOperandButton;
    }
    
    
    private JButton getSubstractOperandButton() {
        if (this.substractOperandButton == null) {
            this.substractOperandButton = new JButton();
            //this.substractOperandButton.setBounds(80, 30, 80, 50);
            this.substractOperandButton.setText("-");
            this.substractOperandButton.addActionListener(this.operand);
        }
        return this.substractOperandButton;
    }
    private JButton getProductOperandButton() {
        if (this.productOperandButton == null) {
            this.productOperandButton = new JButton();
            //this.productOperandButton.setBounds(160, 30, 80, 50);
            this.productOperandButton.setText("*");
            this.productOperandButton.addActionListener(this.operand);
        }
        return this.productOperandButton;
    }
    private JButton getDivisionOperandButton() {
        if (this.divisionOperandButton == null) {
            this.divisionOperandButton = new JButton();
            //this.divisionOperandButton.setBounds(240, 30, 80, 50);
            this.divisionOperandButton.setText("/");
            this.divisionOperandButton.addActionListener(this.operand);
        }
        return this.divisionOperandButton;
    }
    private JButton getCalculateButton() {
        if (this.calculateButton == null) {
            this.calculateButton = new JButton();
            //this.calculateButton.setBounds(320, 30, 80, 50);
            this.calculateButton.setText("OK");
            this.calculateButton.addActionListener(this.resultListener);
        }
        return this.calculateButton;
    }
}
