//Written August 21, 2001 by Bruce McLean
//for Denise Battles
//Copyright 2001
//T. Bruce McLean
//19 Northlake Drive
//Statesboro, GA 30458
//            triangle now equilateral
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class Aternary extends Applet implements ActionListener
{

  public static int ITEM_SIZE = 3;
  public static int TASK_SIZE =50;
  public static int CHART_SIZE 	= 281;
  public static int length = 231;
  public static double slope = 200/115.5;
  private Label l1 = new Label("Research");
  private Label l2 = new Label("Teaching");
  private Label l3 = new Label("Service");
  private Label l4 = new Label("% Effort");
  private Label l5 = new Label("Remaining Effort");
  TextField number1 = new TextField(5);
  TextField number2 = new TextField(5);
  TextField number3 = new TextField(5);
  TextField number4 = new TextField(5);
  TextField number5 = new TextField(5);
  DrawOn canvas = new DrawOn();
  int [] item = new int[ITEM_SIZE];
  int [][] task = new int[TASK_SIZE][ITEM_SIZE];
  int count = 0, taskcount=0, remaining=100, service;
  double ninth;
  public void init() {
    add(l1);add(number1);
    add(l2);add(number2);
    add(l3);add(number3);
    add(l4);add(number4);
    add(l5);add(number5);
    canvas.setSize(CHART_SIZE,CHART_SIZE);
    add(canvas);

    number1.addActionListener(this);
    number2.addActionListener(this);
    number4.addActionListener(this);
    number1.setText("*");number5.setText(""+remaining);
  }
  public void actionPerformed(ActionEvent event)
  {

	if (count==0)
        {
		item[count] = new Integer(number1.getText()).intValue();
		if (item[count]>100 || item[count]<0)
		number1.setText("*");
		else
		{
			number2.setText("*");
         		++count;
		}


	}//if count is zero

	else if (count==1)
    {
	item[count] = new Integer(number2.getText()).intValue();
	if (item[count]+item[count-1]>100 || item[count]+item[count-1]<0)
			number2.setText("*");
	else
	{
		number4.setText("*");
    		++count;
		item[count]=100-item[count-1]-item[count-2];
			//ITEM[2] REPLACED LATER
		number3.setText(""+item[count]);
	}
    }  //if count is one
	else if (count==2)
	{
		//ITEM[2] REPLACED
		item[count] = new Integer(number4.getText()).intValue();
                if (item[count]>100 || item[count]<0)
			number4.setText("*");
                else
                {
                   number1.setText("*");
	    	   remaining=remaining-item[count];
		   number5.setText(""+remaining);
		   for (int i=0;i<3;++i) task[taskcount][i]=
					item[i];
		   ++taskcount;
		   count=0;
		   canvas.repaint();
                }
	}  //count is two


  }  //action event

  class DrawOn extends Canvas
  {
    public void paint(Graphics g)
    {
		double sum=0.0;int bd, rd=16,//blue diam red diameter
		tsum=0;
		canvas.setBackground(Color.pink);
		g.setColor(Color.green);
		g.fillOval(25-2,225-2,5,5);//lower left
		g.fillOval(115+25-2, 25-2, 5, 5);
		g.fillOval(25+length-2,225-2,5,5);
		g.setColor(Color.cyan);
		double lover20=length/20.0,
		       h=Math.sqrt(3)*length/20;
		for (int i=0;i<4;++i)
		{

			g.drawLine(25+(int)(3*i*lover20), 225-(int)(i*h),
			       25+ (int)((length+0.5)/2) ,(int)(25+2*i*h));
			g.drawLine(25+ (int)((length+0.5)/2) ,(int)(25+2*i*h),
			       (int)(25+ length-i*3*lover20),(int)(225-i*h));
			g.drawLine(25+(int)(3*i*lover20), (int)(225-i*h),
			       25+(int)( length-i*3*lover20),(int)(225-i*h));
		}

		g.setColor(Color.black);
		g.drawString("Teaching",107,16);
		g.drawString("Research",3,240);
		g.drawString("Service",25+length -19,240);
		for (int i=0; i<ITEM_SIZE;++i)
			item[i]=0;
		for (int i=0;i<taskcount;++i)
		{
        	for ( int j = 0; j<ITEM_SIZE-1;++j)
		item[j]= item[j] + task[i][j]*task[i][ITEM_SIZE-1];
		item[ITEM_SIZE-1]=item[ITEM_SIZE-1]+task[i][ITEM_SIZE-1];
		}
		for (int j=0;j<ITEM_SIZE-1;++j)
			item[j] = (int)((double)item[j]/item[ITEM_SIZE-1]+0.5);
           if (item[ITEM_SIZE - 1] <= 100)
		rd = (int)(3*Math.sqrt(item[ITEM_SIZE-1]/Math.PI));
           else
        rd=16;
		g.setColor(Color.blue);
		for (int i=0;i<taskcount;++i)
		{
			service=100-task[i][1]-task[i][0];
			bd = (int)(7*Math.sqrt(task[i][2]/Math.PI));
			g.fillOval(25-bd/2+(int)(2*task[i][1]/slope+service/100.0*length+0.5),
                                225-bd/2-2*task[i][1],
		         	bd,bd);
		}  //for
              g.setColor(Color.red);
              service=100-item[1]-item[0];
              g.fillOval(25+(int)(2*item[1]/slope+
              service/100.0*length-rd/2.0-0.5),
                    (int)(224.5-rd/2.0-2*item[1]),rd,rd);
		      g.setColor(Color.green);
              g.drawString("Teaching",107+1,16+1);
			  g.drawString("Research",3+1,240+1);
		      g.drawString("Service",25+length -19+1,240+1);


	}//paint


  }//applet
}
