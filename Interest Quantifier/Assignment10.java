/*
Walther Alvarez
CISC 500
Assignment #10
This program improves the output quality of the investment application in Section 10.3.2. 
by formatting the numbers with two decimal digits, using the String.format method. 
It also sets the font of the text area to a fixed width font, 
using the call textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
This is the framework for the program viewer.
*/
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
   A frame that shows the growth of an investment with variable interest,
   using a text area.
*/
public class Assignment10 extends JFrame
{
   private static final int FRAME_WIDTH = 400;
   private static final int FRAME_HEIGHT = 250;
  
   private static final int AREA_ROWS = 10;
   private static final int AREA_COLUMNS = 30;

   private static final double DEFAULT_RATE = 5;
   private static final double INITIAL_BALANCE = 1000;   
      
   private JLabel rateLabel;
   private JTextField rateField;
   private JButton button;
   private JTextArea resultArea;
   private double balance;

   public Assignment10()
   { 
      balance = INITIAL_BALANCE;
      resultArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
      resultArea.setText(balance + "\n");
      resultArea.setEditable(false);
      resultArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));


      createTextField();
      createButton();
      createPanel();

      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }

   private void createTextField()
   {
      rateLabel = new JLabel("Interest Rate: ");

      final int FIELD_WIDTH = 10;
      rateField = new JTextField(FIELD_WIDTH);
      rateField.setText("" + DEFAULT_RATE);
   }
   
   class AddInterestListener implements ActionListener
   {
      public void actionPerformed(ActionEvent event)
      {
         double rate = Double.parseDouble(rateField.getText());
         double interest = balance * rate / 100;
         balance = balance + interest;
         String resultingBalance = String.format("%.2f",balance);
         resultArea.append(resultingBalance + "\n");
      }
   }

   private void createButton()
   {
      button = new JButton("Add Interest");
      
      ActionListener listener = new AddInterestListener();
      button.addActionListener(listener);
   }

   private void createPanel()
   {
      JPanel panel = new JPanel();
      panel.add(rateLabel);
      panel.add(rateField);
      panel.add(button);
      JScrollPane scrollPane = new JScrollPane(resultArea);
      panel.add(scrollPane);
      add(panel);
   }
}