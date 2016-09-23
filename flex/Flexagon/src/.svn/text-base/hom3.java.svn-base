import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class hom3 extends JApplet implements ActionListener {
	//Feed this program the degree of a pat and it will produce the number of partitions
	//D(p) = 10 can be partitioned 2 + 8, 5 + 5, and 8 + 2 so f(p) = 3
	// reference variables
	JTextField DInput, fOuts, NOuts;
	JLabel DLabel, fLabel, NLabel;
	//N counts the number of pats of degree D(p)
	JButton clearButton;

	public void init() { //layout and container
		Container c = getContentPane();
		FlowLayout flo = new FlowLayout();
		c.setLayout(flo);
		//create 2 objects
		DInput = new JTextField(4);
		fOuts = new JTextField(4);
		NOuts = new JTextField(12);
		clearButton = new JButton("Clear");
		//  make them action listeners
		DInput.addActionListener(this);
		//fInput.addActionListener( this );
		clearButton.addActionListener(this);
		//  create the labels
		DLabel = new JLabel("D(p):");
		fLabel = new JLabel("f(p):");
		NLabel = new JLabel("N(p):");
		//  add the objects to the container
		c.add(DLabel);
		c.add(DInput);
		c.add(fLabel);
		c.add(fOuts);
		c.add(NLabel);
		c.add(NOuts);
		c.add(clearButton);
	}

	public void actionPerformed(ActionEvent e) {
		String danswer, fanswer, nanswer;
		int f, d, n;
		if (e.getSource() == DInput) {
			danswer = DInput.getText();
			d = Integer.parseInt(danswer);
			if ((d > 0) && (d < 40) && ((d % 3) != 0)) {
				f = fah(d);
				n = numberofpats(d);
				fanswer = String.valueOf(f);
				nanswer = String.valueOf(n);
				fOuts.setText(fanswer);
				NOuts.setText(nanswer);
				showStatus("D(p) converted to f(p) and N(p)");
			} else {
				showStatus("D(p) is not correct");
				nanswer = "Bad D(p)!";
				NOuts.setText(nanswer);

			}/*
			       else if ( e.getSource( ) ==  fInput)
			         {
			         fanswer = fInput.getText();
			         f = Integer.parseInt(fanswer);
			         c = cel(f);
			         canswer = String.valueOf(c);
			         DInput.setText( canswer );
			         showStatus("Fahrenheit converted to Celsius ");
			         }*/
		} else if (e.getSource() == clearButton) {
			DInput.setText("");
			fOuts.setText("");
			NOuts.setText("");
			showStatus("");
		}

	}

	public boolean even(int c) {
		return ((c / 3) == (c - 2) / 3);
	}

	public int fah(int c) {
		return (int) Math.floor((c + 1) / 3);
	}

	public int numberofpats(int d) {
		int i, sum = 0;
		if (d < 5)
			sum = 1;
		else {
			if (even(d))

				for (i = 0; i <= (d - 2) / 3; i++)
					sum = sum + numberofpats(1 + 3 * i)
							* numberofpats(d - (1 + 3 * i));
			else
				for (i = 0; i <= (d - 4) / 3; i++)
					sum = sum + numberofpats(2 + 3 * i)
							* numberofpats(d - (2 + 3 * i));
		}
		return sum;

	}

	public int round(double x) {
		return (int) Math.floor(x + 0.5);
	}
}