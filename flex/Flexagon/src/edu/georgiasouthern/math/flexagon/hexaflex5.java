package edu.georgiasouthern.math.flexagon;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;

public class hexaflex5 extends JApplet
                     implements ActionListener
{     // reference variables

    JTextField  cInput, fInput;
    JLabel      cLabel, fLabel;
    JList outs;
    private DefaultListModel outListModel = new DefaultListModel();
    JButton seeButton,  pinchButton,
    fillButton, vflexButton, rightrotateButton, rotateButton;
    String canswer=" ", fanswer, pat_thick =" ";

    int face [], pat [], attrib[], patB [];//top=0 bottom=10 forward=0 outer=1 back=2
  
	
    int  w = 0, max = 18, pats = 6, no_of_faces = 0,
    no_of_rotations=0, total = 24;
    boolean ok;

    private JTabbedPane rightPanel = new JTabbedPane();
    private HexagonPanel hexagonPanel = new HexagonPanel();
    private GraphPanel graphPanel = new GraphPanel();

    public void init( )
    {    //layout and container
    	  int loc[]; loc = new int[6];
       Container  c  =  getContentPane();
       BorderLayout layout = new BorderLayout();
       c.setLayout(layout);

       fInput   =  new JTextField( 38);

       JPanel panel = new JPanel();
       panel.setLayout(new FlowLayout());
       
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

             //  add the objects to the container

       panel.add(fInput);
       panel.add(fillButton);
       panel.add(seeButton);
       panel.add(pinchButton);
       panel.add(vflexButton);
       panel.add(rotateButton);
       panel.add(rightrotateButton);

       c.add(panel, BorderLayout.NORTH);
       
       panel = new JPanel();
       panel.setLayout(new BorderLayout());
       
       JLabel label = new JLabel("No.  Pat1        Pat2        Pat 3          Pat 4          Pat 5        Pat 6      Actions");
       panel.add(label, BorderLayout.NORTH);
       
       outs = new JList();
       outs.setModel(outListModel);
       outs.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			int loc[]; loc = new int[6];
			String state = outs.getSelectedValue().toString();
			fillit(loc, face);
			hexagonPanel.showHexagon(state, attrib, loc);
			//loc[0] is 0 and attrib[0] is 2
		}});
       panel.add(outs, BorderLayout.CENTER);

       rightPanel.addTab("Hexagon", hexagonPanel);
       rightPanel.addTab("Graph", graphPanel);
       
       JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
       								panel, rightPanel);
       
       //c.add(rightPanel, BorderLayout.EAST);
       c.add(splitPanel, BorderLayout.CENTER);
       
       face=new int [total];attrib=new int[total];
       pat = new int [total]; patB = new int [total];
       //outs.setText("No.  Pat1        Pat2        Pat 3          Pat 4          Pat 5        Pat 6      Actions \n");
       //5 entries starting with 0 in house no 0
                         //4 entries starting with 5 in house no 6
       fill(face,attrib,4,0,0);fill(face,attrib,2,5,4);
		fill(face,attrib,4,8,6);
	   	fill(face,attrib,2,13,10);fill(face,attrib,4,16,12);
		fill(face,attrib,2,21,16);
		fanswer = String.valueOf(no_of_faces)+"      ";
		 for(w=0;w<face.length;w++)
	     	 {

	          	 if (face[w]>=0)
	        	 	fanswer = fanswer+" "+String.valueOf(face[w]);
	        	 else if (w<face.length-1) fanswer = fanswer+" - ";
	          }
	     //outs.append(fanswer+" "+ canswer+pat_thick+"\n");
		 outListModel.add(outListModel.getSize(), fanswer+" "+ canswer+pat_thick);
		 
	     no_of_rotations =0;
		 fInput.setText(fanswer);
		 showStatus( "see" );
		 canswer=" ";
	     w=0;
	     pat_thick=" ";
	     //create graph node (added by Emil)
	     graphPanel.addVertex(fanswer);
	     fillit(loc, face);
			hexagonPanel.showHexagon(fanswer, attrib, loc);

    }//init

    public void actionPerformed (ActionEvent e)
    {

       int f, c;
       int loc[]; loc = new int[6];
      if ( e.getSource( ) ==  seeButton)
         {
			 fanswer = String.valueOf(no_of_faces)+"      ";
			 for(w=0;w<face.length;w++)
		     	 {

		          	 if (face[w]>=0)
		        	 	fanswer = fanswer+" "+String.valueOf(face[w]);
		        	 else if (w<face.length-1) fanswer = fanswer+" - ";
		          }
		     //outs.append(fanswer+" "+ canswer+pat_thick+"\n");
			 outListModel.add(outListModel.getSize(), fanswer+" "+ canswer+pat_thick);
			 
		     no_of_rotations =0;
			 fInput.setText(fanswer);
			 showStatus( "see" );
			 canswer=" ";
		     w=0;
		     pat_thick=" ";
		     fillit(loc, face);
				hexagonPanel.showHexagon(fanswer, attrib, loc);
		     //create graph node (added by Emil)
		     graphPanel.addVertex(fanswer);
		  }


		 if ( e.getSource( ) ==  fillButton)
			{
				fill(face,attrib,4,0,0);fill(face,attrib,2,5,4);
						fill(face,attrib,4,8,6);
					   	fill(face, attrib, 2, 13, 10);fill(face,attrib,4,16,12);
		fill(face,attrib,2,21,16);

			    showStatus("New data filled.");
			    w=0;   no_of_faces=0;
			    canswer="f";
			    //outs.setText("No.     Pat1           Pat2               Pat 3                  Pat 4                Pat 5              Pat 6      Actions \n");
				pat_thick=" ";
				fanswer = String.valueOf(no_of_faces)+"      ";
				 for(w=0;w<face.length;w++)
			     	 {

			          	 if (face[w]>=0)
			        	 	fanswer = fanswer+" "+String.valueOf(face[w]);
			        	 else if (w<face.length-1) fanswer = fanswer+" - ";
			          }
			     //outs.append(fanswer+" "+ canswer+pat_thick+"\n");
				 outListModel.add(outListModel.getSize(), fanswer+" "+ canswer+pat_thick);
				 
			     no_of_rotations =0;
				 fInput.setText(fanswer);
				 showStatus( "see" );
				 canswer=" ";
			     w=0;
			     pat_thick=" ";
			     //create graph node (added by Emil)
			     graphPanel.addVertex(fanswer);
			}
		 else if ( e.getSource( ) ==  rotateButton)
         {//rotate is left
	             rotate(face, attrib, 1, true);
	             canswer=canswer+"R";


	      }
	      else if ( e.getSource( ) ==  rightrotateButton)
		           {//
		  	             rotate(face,attrib,5,true);
		  	             canswer=canswer+"r";

	      }
	     else if ( e.getSource( ) ==  pinchButton)
         {
			if ( pinch(face, attrib))
			{
			 build_pat_thick(face, attrib);
			 fanswer = String.valueOf(no_of_faces)+"      ";
			 for(w=0;w<face.length;w++)
		     	 {

		          	 if (face[w]>=0)
		        	 	fanswer = fanswer+" "+String.valueOf(face[w]);
		        	 else if (w<face.length-1) fanswer = fanswer+" - ";
		          }
		     //outs.append(fanswer+" "+ canswer+pat_thick+"\n");
			 outListModel.add(outListModel.getSize(), fanswer+" "+ canswer+pat_thick);
			 
		     no_of_rotations =0;
			 fInput.setText(fanswer);
			 showStatus( "see" );
			 canswer=" ";
		     w=0;
		     pat_thick=" ";
		     fillit(loc, face);
				hexagonPanel.showHexagon(fanswer, attrib, loc);
		     //create graph node (added by Emil)
		     graphPanel.addVertex(fanswer);
			}
	      }
	     else if ( e.getSource( ) ==  vflexButton)
         {
			if ( vflex(face, attrib))
			
			 {
				 build_pat_thick(face, attrib);
				 fanswer = String.valueOf(no_of_faces)+"      ";
				 for(w=0;w<face.length;w++)
			     	 {

			          	 if (face[w]>=0)
			        	 	fanswer = fanswer+" "+String.valueOf(face[w]);
			        	 else if (w<face.length-1) fanswer = fanswer+" - ";
			          }
			     //outs.append(fanswer+" "+ canswer+pat_thick+"\n");
				 outListModel.add(outListModel.getSize(), fanswer+" "+ canswer+pat_thick);
				 
			     no_of_rotations =0;
				 fInput.setText(fanswer);
				 showStatus( "see" );
				 canswer=" ";
			     w=0;
			     pat_thick=" ";
			     fillit(loc, face);
					hexagonPanel.showHexagon(fanswer, attrib, loc);
			     //create graph node (added by Emil)
			     graphPanel.addVertex(fanswer);

		      }
		     else if ( e.getSource( ) ==  vflexButton)
	         {
				if ( vflex(face, attrib))
				
				 {
					 fanswer = String.valueOf(no_of_faces)+"      ";
					 for(w=0;w<face.length;w++)
				     	 {

				          	 if (face[w]>=0)
				        	 	fanswer = fanswer+" "+String.valueOf(face[w]);
				        	 else if (w<face.length-1) fanswer = fanswer+" - ";
				          }
				     //outs.append(fanswer+" "+ canswer+pat_thick+"\n");
					 outListModel.add(outListModel.getSize(), fanswer+" "+ canswer+pat_thick);
					 
				     no_of_rotations =0;
					 fInput.setText(fanswer);
					 showStatus( "see" );
					 canswer=" ";
				     w=0;
				     pat_thick=" ";
				     //create graph node (added by Emil)
				     graphPanel.addVertex(fanswer); 
				 }
			 }
			 
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
   public void fillit(int locat[], int A[]) {
	   int i;
	   int j =1;
	   locat[0]=0;
       for (i=1;i<total-1;i++)	
    	   if (A[i]== -1)
    	   {
    		   locat[j] = i+1;
    		   j++;
    	   }
       
	   
   }
public int patlength (int A[ ])
  {
	  //returns pat length of first pat in A
	 int k=0;
	 while(A[k]>=0) k++ ;
          return k;
  }

     public void fill (int A[ ], int B[ ], int p, int begadd, int begdigit )

	{
		if ( p == 2 )
		{
			A[begadd]=begdigit;A[begadd+1]=(begdigit+1);
			B[begadd]=0; B[begadd+1]=12;
		}
			else if (p == 4)
		{        // 1 0 3 2 - 1 4 5 in A 2 11 1 10 -1 0  12 in B
			A[begadd+1]=begdigit;A[begadd]=(begdigit+1);
			A[begadd+3]=(begdigit+2);A[begadd+2]=(begdigit+3);
			B[begadd+1]=11;B[begadd]=2;
			B[begadd+3]=10;B[begadd+2]=1;
			//24+3=0mod27
		}
		A[p+begadd]=-1;B[p+begadd]=-1;
	}

	public void rotate(int A[], int B[ ], int howmany, boolean last)
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
			          {
			        	  pat[k] = A[k];
			        	  patB[k] = B[k];
			          }
			          for(k=len+1;k<A.length;k++)
		                     {
			        	  A[k-len-1] = A[k];
			        	  B[k-len-1] = B[k];
		                     }
		                     //+1 needed to get past the -1
		              for (k=0;k<=len;k++)
		              {
		                     A[k+A.length-len-1]=pat[k];
		                     B[k+A.length-len-1]=patB[k];
		              }
			      }
		      if (last) showStatus( "rotated  "+ number );

	 }

	 public boolean pinch(int A[], int B[])
	 {
		 int th, count=0, len=1; boolean ok;
		 //make sure no singletons in 1 3 or 5
		 do
		 {
			 count++;
			 len = len*(patlength(A)-1);
			 rotate(A,B,2,false);
		 } while ((len>0) && (count<3));
		 if (len>0)
		 {
             ok = true;
			 for (count=0;count<3;count++)
			 {
				 th=thumbhole(A);
			 	 r_sr(A,B,th);
			 	 rotate(A,B,2, false);
			 }
			 showStatus( "Pinch Flex" );
			 canswer=canswer+"P";
		 }
		 else
		 {
             ok  = false;
			 rotate(A,B,2*(3-count),false);
			 showStatus( "Can't Pinch Flex" );
			 pat_thick=" ";
		 }
		 return ok;
	 }

	 public boolean  vflex(int A[], int B[])
	 	 {
	 		 int th, len=1;
	 		 //make sure no singletons in 1 4 or 5
                 boolean ok;
	 			 len = len*(patlength(A)-1);
	 			 rotate(A,B,3, false);
	 			 len = len*(patlength(A)-1);
	 			 rotate(A,B,1, false);
	 			 len = len*(patlength(A)-1);
	 			 rotate(A,B,2, false);
	 			
	 		 if (len>0)
	 		 {
	 			 ok=true;
	 				 th=thumbhole(A);
	 			 	 r_sr(A,B,th);
	 			 	 rotate(A,B,3, false);
	 			 	 th=thumbhole(A);
	 			 	 rotate(A,B,5, false);
	 			 	 rs_r(A,B,th);
	 			 	 rotate(A,B,2, false);
	 			 	 th=thumbhole(A);
	 			 	 r_sr(A,B,th);
	 			 	 rotate(A,B,2, false);
	 			     showStatus( "V - Flex" );
	 			     no_of_faces=no_of_faces+1;
			 		 canswer=canswer+"V";
	 		 }
	 		 else
	 		 {
	 			 showStatus( "Can't V - Flex" );
	 			 pat_thick=" ";
	 			 ok = false;
			 }
         return ok;
	 }
	 public int thumbhole(int A[])
	 {

		 int sum=0, len, th=0, i=0,k ,j;

		 len = patlength(A);
		 j=th+1;
		 /*Need to check to see if zero and 17 are in the same pat*/
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
		 	for(k=0;k<len;k++) if (A[k]>max-1) A[k]=A[k]-max;
		 return th;
	 }
	 public void r_sr(int A[], int B[], int th)
	 { // 1 0 3 2 - 1 4 5 in A                  2 11 1 10 -1 0  12 in B
		 // 0 1 -1 3 2, 5 4 in A               0 12 -1 2 2 11 1 10
		 // 0 -1   1 4 5 2 3 
		 int BB[];int CC[];
		 BB=new int[total];
		 CC=new int[total];
		 int i, len, len1;
		 for(i=0;i<total;i++)
			 {
			 BB[i]=A[i];
			 CC[i]=B[i];
			 }
		 len = patlength(A);
		 for(i=0;i<=len;i++)
		 {
			 pat[i]=A[i];
			 patB[i]=B[i];
		 }
		 for(i=0;i<=th;i++)
		 {
			 A[i]=pat[th-i];//reverse
			 if      ((patB[th-i] % 10)== 1) B[i] = patB[th-i]-1;//forward 
			 else if ((patB[th-i] %10) == 0 )   B[i] = patB[th -i] + 1;//outer
			 else B[i]=patB[th-i];
			 B[i] = (B[i]+10) % 20;
		}
		 A[th+1] = -1;
		 B[th+1] = -1;
		 for(i=th+2;i<=len;i++)
		 {
			 A[i]=pat[i-1];//same
			 if ((patB[i-1] % 10) < 2) B[i] = patB[i-1]+1;  //outer
			                                                //  backward
			 else B[i] = patB[i-1] -2; //forward
		 }
		rotate(BB, CC, 1, false);
		len1 = patlength(BB);

		for (i=0;i<len1;i++)
		{
			A[len+1+i]=BB[len1-1-i];//reverse
		    if ((CC[len1-1-i] % 10)== 2) B[len+1+i]=CC[len1-1-i]-1;//outer
		    else if ((CC[len1-1-i]% 10) ==  1) B[len+1+i] = CC[len1-1-i]+1;//backward
		    else B[len+1+i]=CC[len1-1-i];
		    B[len+1+i]=(B[len+1+i]+10) % 20;
		}

	}
	public void rs_r(int A[], int B[], int th)
		 {
			 int BB[], CC[];
			 BB=new int[total];
			 CC = new int[total];
			 int i, len, len1;
			 for(i=0;i<total;i++)
			 {
				 BB[i]=A[i];
				 CC[i]= B[i];
			 }				 
			 len = patlength(A);
			 for(i=0;i<len;i++)
			 {
				 A[i]=BB[len-i-1];//reverse BB's first pat
				 if ((CC[len - i -1]%10)== 0)B[i] = CC[len - i -1] + 1;
				 else if ((CC[len - i - 1]%10)== 1)B[i] = CC[len-i-1] - 1;
				 else B[i]=CC[len-i-1];
				 B[i] = (B[i]+10)% 20;
			 }
				 
			 rotate(BB, CC, 1, false);
			 len1=patlength(BB);
         	 for(i=0;i<=th;i++)
         	 {
				 A[i+len]=BB[i];//same on first half of 2nd pat
				 if ((CC[i] % 10)>0) B[i+len]=CC[i]-1;
				 else B[i+len]=CC[i]+2;
         	 }
			 A[len+th+1] = -1;B[len+th+1]= -1;

			 for(i=th+2;i<=len1;i++)
			 {
				 A[len+i]=BB[len1-i+th+1];//reverse 2nd half of 2nd pat
				 if ((CC[len1-i+th+1] % 10) == 2) B[len+i] = CC[len1-i+th+1] - 1; //back becomes out
				 else if ((CC[len1-i+th+1] % 10) == 1) B[len+i] = CC[len1-i+th+1] + 1; //out => back
				 else  B[len+i]=CC[len1-i+th+1];
				 B[len+i]= (B[len+i]+10) % 20;
			 }


		}
	public void build_pat_thick(int A[], int B[] )
	{
		int i;
		for (i=0;i<5;i++)
		{
			pat_thick = pat_thick + String.valueOf(patlength(A))+"-";
			rotate(A, B, 1, false);
		}
			pat_thick = pat_thick + String.valueOf(patlength(A));
			rotate(A, B, 1, false);

	}

 }//class