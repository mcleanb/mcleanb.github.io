import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * 
 * This our first team program. my change
 * 
 *
 */
public class FC2 extends JApplet
                     implements ActionListener
{       // reference variables

    JTextField  cInput, fInput;
    JLabel      cLabel, fLabel;
    JButton clearButton;


    public void init( )
    {    //layout and container
       Container  c  =  getContentPane();
       FlowLayout flo = new FlowLayout();
       c.setLayout(flo);
            //create 2 objects
       cInput   =  new   JTextField( 4 );
       fInput   =  new   JTextField( 4 );
       clearButton = new JButton("Clear");
           //  make them action listeners
       cInput.addActionListener( this );
       fInput.addActionListener( this );
       clearButton.addActionListener( this );
            //  create the labels
       cLabel = new JLabel("Celsius:");
       fLabel = new JLabel("Fahrenheit:");
             //  add the objects to the container
       c.add(cLabel); c.add(cInput);
       c.add(fLabel); c.add(fInput);
       c.add(clearButton);
    }

    public void actionPerformed (ActionEvent e)
    {
       String canswer, fanswer;
       int f, c;
       if ( e.getSource( ) ==  cInput)
         {
         canswer = cInput.getText();
         c = Integer.parseInt(canswer);
         f = fah(c);
         fanswer = String.valueOf(f);
         fInput.setText( fanswer );
         showStatus("Celsius converted to Fahrenheit");
         }
       else if ( e.getSource( ) ==  fInput)
         {
         fanswer = fInput.getText();
         f = Integer.parseInt(fanswer);
         c = cel(f);
         canswer = String.valueOf(c);
         cInput.setText( canswer );
         showStatus("Fahrenheit converted to Celsius ");
         }
        else if ( e.getSource( ) ==  clearButton)
         {
			 cInput.setText( "" );
			 fInput.setText( "" );
			 showStatus( "" );
	      }

     }


     public int fah(int c)
     {
       return round(9.0*c/5.0+32.0);
     }
     public int cel(int f)
     {
            return round((f-32.0)*5.0/9.0);
     }

     public int round(double x)
     {
        return (int) Math.floor (x + 0.5);
     }
 }