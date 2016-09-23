package edu.georgiasouthern.math.flexagon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//started with HexaFlexa_2_0_b
//then became 2_0_1a which may be lost
//and now5_0
//Click V-Flex then Pinch then see - set check mark - click clear and then see repeat (clear see)
public class HexaFlexa_5_0 extends JApplet
                     implements ActionListener
{     // reference variables

   // JTextField  cInput, fInput;
    //JLabel      cLabel, fLabel;
    JTextArea outs;
    JCheckBox  quickSort;//checking box allows clearing 30 entries and then seeing next 30
    JButton seeButton,  pinchButton,
    fillButton, vflexButton, //rightrotateButton, rotateButton,
    addButton;
    String canswer=" ", fanswer=" ", pat_thick =" ";

    int face [], pat [];
    int  w = 0, level=1, pth=0, max = 18, go, pats = 6, counts=0, sixcounter=0,
    no_of_faces = 0,bot=0, bottt,kkk=0,xx, cc=0,  //dynamic check
    no_of_rotations=0, total = max+6, bottom = 0, newbot,nxt,tablerows=200, prev = 0; //bottom of table
    boolean nofill = false, trouble=false;
    int table[] [], next[][];




    public void init( )
    {    //layout and container
        int kk,row;
       Container  c  =  getContentPane();
       FlowLayout flo = new FlowLayout();
       c.setLayout(flo);
            //create 2 objects
 		//cInput = new JTextField( 4 );
	   //fInput = new JTextField( 4 );
       outs = new JTextArea();
       addButton=new JButton("Print path");
       fillButton=new JButton("Clear");
       seeButton = new JButton("See");
       quickSort=new JCheckBox("zero counter only");



       //cLabel = new JLabel("Start");
		//fLabel = new JLabel("Finish");
		vflexButton = new JButton("V-flex until done");
       pinchButton = new JButton("Pinch flex until done");
       //rotateButton = new JButton("Rotate");
       //rightrotateButton = new JButton("Rotate Right");
           //  make them action listeners
       seeButton.addActionListener( this );
       addButton.addActionListener( this );
       fillButton.addActionListener( this );
       pinchButton.addActionListener( this );
       vflexButton.addActionListener( this );
       //rotateButton.addActionListener( this );
       //rightrotateButton.addActionListener( this );
       quickSort.addActionListener(this);

       //cInput.addActionListener( this );

	   //fInput.addActionListener( this );


		c.add(seeButton);c.add(addButton);
       //c.add(fInput);
       c.add(quickSort);c.add(pinchButton);c.add(vflexButton);
       c.add(fillButton);/*

       c.add(rotateButton);
       c.add(rightrotateButton);*/
       c.add(outs);
       //c.add(cLabel);
       //c.add(cInput);c.add(fLabel);
       //c.add(fInput);
       face=new int [total];
       pat = new int [total];
       table = new int [tablerows][total];
       next = new int [tablerows][14];//0 holds parent 1-6 for v 7-12 for pinch 13 where to start


       //4 entries starting with 0 in house no 0
                         //2 entries starting with 2 in house no 4
       fill(face,4,0,0);fill(face,2,5,4);
		fill(face,4,8,6);
	   	fill(face,2,13,10);fill(face,4,16,12);
		fill(face,2,21,16);
        for (kk=0;kk<total;kk++)
        {
			table[bottom][kk]=face[kk];//make sure first column is odd AND not a singleton
		}
			for (row=0;row<tablerows;row++)
			{
				for (kk=0;kk<13;kk++)
					next[row][kk]=-1;
				next[row][13]=1;
			}
        bottom++; //bottom is empty
    }//init

    public void actionPerformed (ActionEvent e)
    {

      int w,f;
      if ( e.getSource( ) ==  seeButton)
         {
			 kkk++;
	         while((cc < 30*kkk)&& ( cc<200))
	         {
				fanswer = String.valueOf(cc)+" ";
			  	for(w=0;w<14;w++)
			  		     {
			  		          	 if (next[cc][w]>=0)
			  		        	 	fanswer = fanswer+String.valueOf(next[cc][w])+" ";
			  		        	 else fanswer = fanswer+"-";
			  		      }
			   for(w=0;w<total;w++)
			   {
			   			  		          	 if (table[cc][w]>=0)
			   			  		        	 	fanswer = fanswer+String.valueOf(table[cc][w])+" ";
			   			  		        	 else fanswer = fanswer+"+";
			  		      }

			  		     outs.append(fanswer+"\n");

			  		     no_of_rotations =0;
			  			// fInput.setText(fanswer);
			  			 showStatus( "see" );
			  			 //canswer=" ";
                   cc++;
		    }//while


		  }
else

		 if ( e.getSource( ) ==  fillButton)
			{

				if ( !nofill )
				{ //4 entries starting with 0 in house no 0
                         //2 entries starting with 4 in house no 5
       fill(face,4,0,0);fill(face,2,5,4);
		fill(face,4,8,6);
	   	fill(face,2,13,10);fill(face,4,16,12);
		fill(face,2,21,16);

			        showStatus("New data filled.");
			         w=0;
			         //canswer="f";
                    bot=0;
                    kkk=0;
                    cc=0;

			    }
			     else
			    {

		    	showStatus("counter set to zero");
		    	//canswer="z";
			     }
			 pat_thick=" ";
			 no_of_faces=0;
			 outs.setText("");
		 }
		/*else if ( e.getSource( ) ==  rotateButton)
         {//rotate is left
	             rotate(face,1,true);
	             canswer=canswer+"R";


	      }
	      */
	      else if ( e.getSource( ) ==  addButton)
	      {
			  if (level == 1)
			  {
				  go = follow_to(bot);
			       fanswer = fanswer + String.valueOf(bot)+" ";
		              bot=go;

		              while ( bot >0 )
		              {
		  				bot=follow_to(bot);
		  				if (sixes(bot))
		  				{
							fanswer = fanswer + "c"+String.valueOf(counts)+" ";
							fanswer = fanswer + String.valueOf(bot)+" ";
							counts=0;
						}
		  				else counts++;
		  		    }//while
		  		   fanswer = fanswer + "c"+String.valueOf(counts)+" ";
					fanswer = fanswer + String.valueOf(bot)+" ";
					counts=0;
		  		    outs.append(fanswer+"\n");
		  			//fInput.setText(fanswer);
		  			showStatus( "Big path" );level++;bot=0;


     			}//if
				else //if (level==2)
			           {

                           do
                           	   {

				                 if (next[bot][next[bot][13]]>=0)
				                   {

									fanswer = " "+String.valueOf(bot)+" ";
						            second(bot); fanswer = fanswer + "c"+String.valueOf(counts)+" ";
								    fanswer = fanswer + String.valueOf(bot)+" ";
								    counts=0;
						            outs.append(fanswer+"\n");
									//fInput.setText(fanswer);
									}

									bot=next[bot][1];
						  			showStatus( "Level 2" );
								 }
							  	while(bot>0);
								level++;bot=1;// change for 27

			}//else
			/*
			else
			{
						do
						{
							if (next[bot][13]>0)
							if (next[bot][next[bot][13]]>0)
							{
				                   fanswer = " "+String.valueOf(bot)+" ";
						            second(bot);
						           outs.append(fanswer+"\n");
									fInput.setText(fanswer);
								}
								bot=next[bot][1];
								showStatus( "Level 3 path" );


						} while (bot>0);
						level++;bot=0;
			}//else
           */
		 }//addbutton
	     /* else if ( e.getSource( ) ==  rightrotateButton)
		           {//
		  	             rotate(face,5,true);
		  	             canswer=canswer+"r";

	      			}
	   */
	   else if ( e.getSource( ) ==  pinchButton)
         {
			while (bot<bottom)
			{
							pinchdoit(bot);
							bot++;
			}canswer = " "+String.valueOf(bot)+" ";showStatus( canswer );
	      }
	     else if ( e.getSource( ) ==  vflexButton)
         {
			bot=0;
			while (bot<bottom)
			{
				doit(bot);
				bot++;
			}
			canswer = " "+String.valueOf(bot)+" ";showStatus( canswer );
			bot=0;
			slide();
		}
	      else if (e.getSource( ) == quickSort)

			nofill= !nofill;/*

		else if ( e.getSource( ) == cInput)
		{
		canswer = cInput.getText();
		c = Integer.parseInt(canswer);
		f = fah(c);
		fanswer = String.valueOf(f);
		fInput.setText( fanswer );
		outs.append(canswer+"\t"+fanswer+"\n");
		showStatus("Celsius converted to Fahrenheit");
		}
		else if ( e.getSource( ) == fInput)
		{
		fanswer = fInput.getText();
		f = Integer.parseInt(fanswer);
		c = cel(f);
		canswer = String.valueOf(c);
		cInput.setText( canswer );
		outs.append(canswer+"\t"+fanswer+"\n");
		showStatus("Fahrenheit converted to Celsius ");*/



	 }//public action performed

public int follow_to(int start)// returns where to go next or -2 if depleted
{// also updates the stack
	int posofgo;
	posofgo=next[start][13];// 1 in level 1
	if (posofgo>=0)
	{
		next[start][13]++;//level 1 increases to 2
		return next[start][posofgo];
	}
	else
	{
		next[start][13]=-2;
		return -2;
	}
}


public void second(int start)
{
	int go=-2, st;
	boolean done = false;
    st = start;
	while((go != start) || done)
	{

		go = follow_to(st);
	    if (go>=0)
	    {
			if (sixes(go))
			{
			fanswer=fanswer+"c"+String.valueOf(counts)+" ";
			fanswer=fanswer+String.valueOf(go)+" ";
			counts=0;
		    }else counts++;

		//	if (level>2){
		  //	outs.append(fanswer+"\n");
		//							fInput.setText(fanswer);
			st=go;

		} else done = true;
	}

}

public boolean sixes(int bo )
{
	int i, w=1;
	int B[];
	B=new int[total];
	for ( i=0;i<total;i++) B[i]=table[bo][i];
	i=0;
	do
	{
		w=w*(patlength(B)-1);
		rotate(B,1,false);
		i++;
	} while((w>0)&&(i<6));
	rotate(B,6-i,false);
	return (w>0);
}
/*
public boolean k_is_in_B(int k, int B[])
{
	int i;boolean foundit=false;
	for ( i=0; i<6; i++)
		if (k == B[i]) foundit = true;
	return foundit;
}
*/
public void doit(int start)
{
	int bott, i,j,k;boolean six;
	int A[];String fanswer=" ";


    A = new int [total];
	for(i=0;i<6;i++)
		{
			for(j=0;j<total;j++)
	    				A[j] =	table[start][j];
	    	if (i>0) rotate(A,i,false);
			if (V_possible (A))
			{
				vflex(A);
				k = in_the_table(A);
				if ( k < 0 ) //wasn't there but now it is
				{
		            next[bottom-1][0]=start;
                    next[start][i+1]=bottom-1;
				} //if k
				else
				{
					next[start][i+1]=k;
				}
			}//ifV_poss
		}
		rotate(A,1,false);



}//doit
public void pinchdoit(int start)
{
	int bott, i,j,k;
	int A[];String fanswer=" ";
    A = new int [total];

		for(i=0;i<6;i++)
		{
			for(j=0;j<total;j++)
	    				A[j] =	table[start][j];
	    	if (i>0) rotate(A,i,false);
			if (pinch_possible (A))
			{
				pinch(A);
				k = in_the_table(A);
				if ( k < 0 ) //wasn't there but now it is
				{
		            next[bottom-1][0]=start;
		            next[start][i+7]=bottom-1;
				} //if k
				else next[start][i+7]=k;

			}//ifpinch_poss

		}
		rotate(A,1,false);


}//doit

public void slide()
{
	int i, ka=-1, kb=-2, row;
	for (row=0;row<100;row++)
	{

		for (i=1;i<6;i++)
		{
			if(next[row][i]<0)
			{
				next[row][i] = next[row][i+1];
				next[row][i+1]=-1;
			}
		}
	}
}

public boolean notequal(int A[], int i)
{
	int index, k=0, len, j;
	boolean same=true;
	for (j=0;j<3;j++)

	{

		index = A[0] - table[i][0];
		while ((same) && ( k < total))

		{
			if(A[k]>=0)
			same = ((A[k] == (table[i][k]+index + max) % max));

			k++;
		};
		if ((k<total)&&(j<2))
		{
			k=0;
			same=true;
		}
		rotate(A, 2, false);


	}
	return(!same);
}
public int in_the_table(int A[] )
{
	int i=0, singlecounter=0, onea, oneb,onec;
	boolean notfound = true, even, singleton;
	even = ((patlength (A) % 3) ==2) ;// make sure first pat is odd
		if ( even) rotate(A,1 , false);
	//trying to make lowest pat in position 3
	onea=patlength(A);
    onec=singlecounter;                     // 7-2-1-2-4-2   701111124
	while (singlecounter<2)
	{

		rotate(A,2,false);
		singlecounter++;
		oneb=patlength(A);
		if (oneb<onea)
		{
			onea=oneb;
			onec=singlecounter;
		}
	}
	rotate(A,2*onec,false);
			// 1 5 3 0->ok  3  1  5 1->r2  5 3 1 2->r4

	while ((i< bottom) && ( notfound))
	{
		notfound = notequal(A,i);//need a false
		//if found, i-1 is accurate
		i++;
	};

	if (notfound) //add it to the table - try not to put singleton in pat 1
	{

		for (i=0;i<total;i++)
			table[bottom][i]=A[i];

		bottom++;
		//following not needed?
		   if (even) rotate(A,5,false);
		   if(singlecounter>0) rotate(A,6-2*singlecounter,false);

		return -1;//means not in the table before but now it is
	}
	else
	{
		   if (even) rotate(A,5,false);
		   return i-1;
	   }


}
public int patlength (int A[ ])
  {
	  //returns pat length of first pat in A
	 int k=0;
	 while(A[k]>=0) k++ ;
	 return k;
  }

     public void fill (int A[ ], int p, int begadd, int begdigit )

	{
		if ( p == 2 )
		{
			A[begadd]=begdigit;A[begadd+1]=(begdigit+1);
		}
		else if (p == 4)
		{
			A[begadd+1]=begdigit;A[begadd]=(begdigit+1);
			A[begadd+3]=(begdigit+2);A[begadd+2]=(begdigit+3);

		}
		A[p+begadd]=-1;
	}

	public void rotate(int A[], int howmany,boolean last)
	{
		//rotate is left
	      String number;
	      no_of_rotations=no_of_rotations+howmany;
	      number = String.valueOf(howmany);
				 int k, len, t;
				 for (t=0;t<howmany;t++)
				 {
				      len = patlength(A);
			          for (k=0;k<=len;k++)
			               pat[k] = A[k];
			          for(k=len+1;k<A.length;k++)
		                     A[k-len-1] = A[k];
		                     //+1 needed to get past the -1
		              for (k=0;k<=len;k++)
		                     A[k+A.length-len-1]=pat[k];
			      }
		      if (last) showStatus( "rotated  "+ number );

	 }

 public boolean pinch_possible(int A[])
	 {
		 int count=0, len=1;
		 //make sure no singletons in 1 3 or 5
		 do
		 {
			 count++;
			 len = len*(patlength(A)-1);
			 rotate(A,2,false);
		 }
		 while ((len>0) && (count<3));
		 return (len>0);
	 }

public void pinch(int A[])
	 {
		 int th, count=0;
		 //make sure no singletons in 1 3 or 5

		 if (pinch_possible(A))

		 {

			 for (count=0;count<3;count++)
			 {
				 th=thumbhole(A);
			 	 r_sr(A,th);
			 	 rotate(A,2, false);
			 }
			 showStatus( "Pinch Flex" );
			// canswer=canswer+"P";
		 }
		 else
		 {

			 rotate(A,2*(3-count),false);
			 showStatus( "Can't Pinch Flex" );
			 pat_thick=" ";
		 }
	 }
     public boolean V_possible(int A[])
     		{
		        int len=1;
	 		 //make sure no singletons in 1 4 or 5
		 	 		len = len*(patlength(A)-1);
		 	 		rotate(A,3, false);
		 	 		len = len*(patlength(A)-1);
		 	 		rotate(A,1, false);
		 	 		len = len*(patlength(A)-1);
	 			 	rotate(A,2, false);
	 			 	return (len>0);
			 }
	 public void vflex(int A[])
	 	 {
			 int th;



	 				 th=thumbhole(A);
	 			 	 r_sr(A,th);
	 			 	 rotate(A,3, false);
	 			 	 th=thumbhole(A);
	 			 	 rotate(A,5, false);
	 			 	 rs_r(A,th);
	 			 	 rotate(A,2, false);
	 			 	 th=thumbhole(A);
	 			 	 r_sr(A,th);
	 			 	 rotate(A,2, false);
	 			     showStatus( "V - Flex" );
	 			     no_of_faces=no_of_faces+1;
			 		// canswer=canswer+"V";



	 }
	 public int thumbhole(int A[])
	 {

		 int sum=0, len, th=0, i=0,k ,j;

		 len = patlength(A);
		 j=th+1;
		 /*Need to check to see if zero and max -1 are in the same pat*/
		 for (k=0;k<len;k++)
		 {
		 	if (A[k]==0) sum = sum +1;
		 	if (A[k]==max-1) sum=sum+1;
		}
		 if (sum==2) //then there is a disconnect so raise some
			 for (k=0;k<len;k++) if (A[k]<A[total-2]) A[k]=A[k]+max;

		 while (j<len)
		 {

		 		while((j<len) && (A[i]<A[j])) j++;


		 	     //that unique spot where all numbers to left are smaller
		 	     //than all numbers to the right th=0 means between 0 and others
		 		if (j<len)
		 		{
					 th=j;
					 j=th+1;
				 }
		 }
		 if (sum==2)
		 	for(k=0;k<len;k++) if (A[k]>=max) A[k]=A[k]-max;
		 return th;
	 }
	 public void r_sr(int A[], int th)
	 {
		 int B[];
		 B=new int[total];
		 int i, len, len1;
		 for(i=0;i<total;i++)
			 B[i]=A[i];
		 len = patlength(A);
		 for(i=0;i<=len;i++)
			 pat[i]=A[i];
		 for(i=0;i<=th;i++)
			 A[i]=pat[th-i];//reverse
		 A[th+1] = -1;

		 for(i=th+2;i<=len;i++)
			 A[i]=pat[i-1];//same
		rotate(B,1, false);
		len1 = patlength(B);

		for (i=0;i<len1;i++)
			A[len+1+i]=B[len1-1-i];

	}
	public void rs_r(int A[], int th)
		 {
			 int B[];
			 B=new int[total];
			 int i, len, len1;
			 for(i=0;i<total;i++)
				 B[i]=A[i];
			 len = patlength(A);
			 for(i=0;i<len;i++)
				 A[i]=B[len-i-1];//reverse B's first pat
			 rotate(B,1, false);
			 len1=patlength(B);
         	 for(i=0;i<=th;i++)
				 A[i+len]=B[i];//same on first half of 2nd pat
			 A[len+th+1] = -1;

			 for(i=th+2;i<=len1;i++)
				 A[len+i]=B[len1-i+th+1];//reverse 2nd half of 2nd pat


		}
	public void build_pat_thick(int A[] )
	{
		int i;
		for (i=0;i<5;i++)
		{
			pat_thick = pat_thick + String.valueOf(patlength(A))+"-";
			rotate(A,1, false);
		}
			pat_thick = pat_thick + String.valueOf(patlength(A));
			rotate(A,1, false);

	}

 }//class