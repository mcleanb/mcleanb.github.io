import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class flex4 extends JApplet
                     implements ActionListener
{     // reference variables

    JTextField  cInput, fInput;
    JLabel      cLabel, fLabel;
    JTextArea outs;
    JButton seeButton,  pinchButton,
    fillButton, vflexButton, rightrotateButton, rotateButton;
    String canswer=" ", fanswer, pat_thick =" ";

    int face [], pat [];
    int  w = 0, max = 27, pats = 6, no_of_faces = 0,
    no_of_rotations=0, total = 33;




    public void init( )
    {    //layout and container
       Container  c  =  getContentPane();
       FlowLayout flo = new FlowLayout();
       c.setLayout(flo);
            //create 2 objects
       cInput   =  new   JTextField( 4);
       fInput   =  new   JTextField( 50);
       outs = new JTextArea();
       fillButton=new JButton("Fill");
       seeButton = new JButton("See");

       vflexButton = new JButton("V-flex");
       pinchButton = new JButton("Pinch");
       rotateButton = new JButton("Rotate");
       rightrotateButton = new JButton("Rotate Right");
           //  make them action listeners
       seeButton.addActionListener( this );

       fillButton.addActionListener( this );
       pinchButton.addActionListener( this );
       vflexButton.addActionListener( this );
       rotateButton.addActionListener( this );
       rightrotateButton.addActionListener( this );
            //  create the labels
       cLabel = new JLabel("House Number");
       fLabel = new JLabel("House Contents");
             //  add the objects to the container
       c.add(cLabel);
       c.add(cInput);


       c.add(fLabel);
       c.add(fInput);
       c.add(fillButton);
       c.add(seeButton);c.add(pinchButton);
       c.add(vflexButton);c.add(rotateButton);
       c.add(rightrotateButton);
       c.add(outs);
       face=new int [33];
       pat = new int [33];
       outs.setText("No.     Pat1           Pat2               Pat 3                  Pat 4                Pat 5              Pat 6      Actions \n");
       //5 entries starting with 0 in house no 0
                         //4 entries starting with 5 in house no 6
       fill(face,5,0,0);fill(face,4,6,5);
		fill(face,5,11,9);
	   	fill(face,4,17,14);fill(face,5,22,18);
		fill(face,4,28,23);

    }//init

    public void actionPerformed (ActionEvent e)
    {

       int f, c;
      if ( e.getSource( ) ==  seeButton)
         {
			 fanswer = String.valueOf(no_of_faces)+"      ";
			 for(w=0;w<face.length;w++)
		     	 {

		          	 if (face[w]>=0)
		        	 	fanswer = fanswer+" "+String.valueOf(face[w]);
		        	 else if (w<face.length-1) fanswer = fanswer+" - ";
		          }
		     outs.append(fanswer+" "+ canswer+pat_thick+"\n");
		     no_of_rotations =0;
			 fInput.setText(fanswer);
			 showStatus( "see" );
			 canswer=" ";
		     w=0;
		     pat_thick=" ";
		  }


		 if ( e.getSource( ) ==  fillButton)
			{
				fill(face,5,0,0);fill(face,4,6,5);
				       fill(face,5,11,9);
				       fill(face,4,17,14);fill(face,5,22,18);
				       fill(face,4,28,23);

			    showStatus("New data filled.");
			    w=0;   no_of_faces=0;
			    canswer="f";
			    outs.setText("No.     Pat1           Pat2               Pat 3                  Pat 4                Pat 5              Pat 6      Actions \n");
				pat_thick=" ";
			}
		else if ( e.getSource( ) ==  rotateButton)
         {//rotate is left
	             rotate(face,1,true);
	             canswer=canswer+"R";


	      }
	      else if ( e.getSource( ) ==  rightrotateButton)
		           {//
		  	             rotate(face,5,true);
		  	             canswer=canswer+"r";

	      }
	     else if ( e.getSource( ) ==  pinchButton)
         {
			 pinch(face);
			 build_pat_thick(face);

	      }
	     else if ( e.getSource( ) ==  vflexButton)
         {
			 vflex(face);build_pat_thick(face);
	      }


	 }//public action performed

/*
 public void swap(byte A[], int a, int b)
     {
		 byte temp = A[a];
		 A[a] = A[b];
		 A[b] = temp;

     }
*/
public int patlength (int A[ ])
  {
	  //returns pat length of first pat in A
	 int k=0;
	 while(A[k]>=0) k++ ;
          return k;
  }

     public void fill (int A[ ], int p, int begadd, int begdigit )

	{
		if ( p == 5 )
		{
			A[begadd]=begdigit;A[begadd+2]=(begdigit+4);A[begadd+1]=(begdigit+3);
			A[begadd+4]=(begdigit+2);A[begadd+3]=(begdigit+1);
		}
		else if (p == 4)
		{
			A[begadd+1]=begdigit;A[begadd]=(begdigit+1)%max;
			A[begadd+3]=(begdigit+2)%max;A[begadd+2]=(begdigit+3);
			//24+3=0mod27
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

	 public void pinch(int A[])
	 {
		 int th, count=0, len=1;
		 //make sure no singletons in 1 3 or 5
		 do
		 {
			 count++;
			 len = len*(patlength(A)-1);
			 rotate(A,2,false);
		 } while ((len>0) && (count<3));
		 if (len>0)
		 {

			 for (count=0;count<3;count++)
			 {
				 th=thumbhole(A);
			 	 r_sr(A,th);
			 	 rotate(A,2, false);
			 }
			 showStatus( "Pinch Flex" );
			 canswer=canswer+"P";
		 }
		 else
		 {

			 rotate(A,2*(3-count),false);
			 showStatus( "Can't Pinch Flex" );
			 pat_thick=" ";
		 }
	 }

	 public void vflex(int A[])
	 	 {
	 		 int th, len=1;
	 		 //make sure no singletons in 1 4 or 5

	 			 len = len*(patlength(A)-1);
	 			 rotate(A,3, false);
	 			 len = len*(patlength(A)-1);
	 			 rotate(A,1, false);
	 			 len = len*(patlength(A)-1);
	 			 rotate(A,2, false);
	 		 if (len>0)
	 		 {
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
			 		 canswer=canswer+"V";
	 		 }
	 		 else
	 		 {
	 			 showStatus( "Can't V - Flex" );
	 			 pat_thick=" ";
			 }

	 }
	 public int thumbhole(int A[])
	 {

		 int sum=0, len, th=0, i=0,k ,j;

		 len = patlength(A);
		 j=th+1;
		 /*Need to check to see if zero and 26 are in the same pat*/
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
		 	for(k=0;k<len;k++) if (A[k]>26) A[k]=A[k]-max;
		 return th;
	 }
	 public void r_sr(int A[], int th)
	 {
		 int B[];
		 B=new int[33];
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