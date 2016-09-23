/**
 * 
 */
package edu.georgiasouthern.math.flexagon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

/**
 * @author Emil
 *
 */
public class HexagonPanel extends JPanel {
	
	public static final Color[] faceColors = {
		
	
		
		Color.blue,Color.yellow,
		Color.red,
		Color.blue,Color.yellow,
		Color.red,
		Color.blue,Color.yellow,
		Color.red,
		Color.blue,Color.yellow,
		Color.red,
		Color.blue,Color.yellow,
		Color.red,
		Color.blue,Color.yellow,
		Color.red,
	
		Color.green,	Color.green,
		Color.orange,Color.orange,
		Color.gray,Color.gray,
		Color.green,Color.green,
		Color.orange,Color.orange,
		Color.gray,Color.gray,
		Color.green,Color.green,
		Color.orange,Color.orange,
		Color.gray,Color.gray
	
		
	};
	
	int[] state = new int[] {-1, -1, -1, -1, -1, -1};
	int [] B = new int [24]; int [] location = new int [6];
boolean top;
	public HexagonPanel() {
		setPreferredSize(new Dimension(400,400));
	}
	
	public void showHexagon(String state, int attr[], int loc[], boolean topp) {
		int[] pats = new int[6]; int i;
		top=topp;
		for ( i = 0 ; i <24; i++)
			   B[i]=attr[i];
		for (i=0; i<6; i++)
			location[i] = loc[i];
		String[] tokens = state.split("-");
		String token = tokens[0];
		
		String[] faces = token.trim().substring(token.trim().indexOf(" ")).trim().split(" ");
		int p = Integer.parseInt(faces[0]);
		if (attr[loc[0]]<10) pats[0] = p;
		else pats[0] = p +18;

		for ( i = 1; i < pats.length; i++) {
			token = tokens[i];
			
			faces = token.trim().split(" ");
			p = Integer.parseInt(faces[0]);
			if (attr[loc[i]] < 10) pats[i] = p;
			else pats[i] = p + 18;  //back color 
			
			
		}
		
		showHexagon(pats[0], pats[1], pats[2], pats[3], pats[4], pats[5] );
	}
	
	public void showHexagon(int p1, int p2, int p3, int p4, int p5, int p6 ) {
		state[0] = p1;
		state[1] = p2;
		state[2] = p3;
		state[3] = p4;
		state[4] = p5;
		state[5] = p6;
		
		repaint( );
	}

	public void paint(Graphics g) {

		int mixa=0, mixb=0, mixc=0 ;
		int shadea, shadeb, shadec ;
		if (state[0] == -1) {
			return;
		}
		
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i < 6; i++) {
		
			if ((state[i]%6) == 0)
			{
				mixa=255; 
				if (B[location[i]]<10) mixb=0;else mixb=255;
				mixc=0;
			}
			else if ((state[i]%6) == 1)
			{
				mixb=255; 
				if (B[location[i]]<10) mixa=0;else mixa=255;
				mixc=0;
			}
			else if ((state[i]%6) == 2)
			{
				mixa=0; 
				if (B[location[i]]<10) mixb=0;else mixb=255;
				mixc=255;
			} 
			else if ((state[i]%6) == 3)
			{
				if (B[location[i]]<10) {mixa = 255; mixb=0;mixc=0;}
				else {mixa = 0; mixb=255;mixc=255;}
			} 
			else if ((state[i]%6) == 4)
			{
				if (B[location[i]]<10) {mixa = 0; mixb=255;mixc=0;}
				else {mixa = 255; mixb=0;mixc=255;}
			} 
			else 
			{
				if (B[location[i]]<10) {mixa = 0; mixb=0;mixc=255;}
				else {mixa = 255; mixb=0;mixc=255;}
			}  
			// Now the shadings     a   b
			//                        c
			
			
			if ((B[location[i]]% 10) == 0) //all upper next
			{
				if (top)
				{
					shadea = 2;shadeb=1;shadec=0;
				}
				else
				{
					if (B[location[i]] < 10)
						{
						shadea = 0;shadeb=1;shadec=2;
						}
					else
					{
						shadea = 2;shadeb=0;shadec=1;
					}
				}
			} 
			else if (((B[location[i]]% 10) == 2))//previous
			{
				shadea= 1;
				
				if(top)
				{
				shadeb = 2; shadec = 0; 
				}
				else
				{
					shadeb = 0; shadec = 2; 
				}
					
			}
			else //outer
			{
					shadec=1;			
					if(state[i]%2==1)
						{
							if(B[location[i]]<9) {shadea=2;shadeb=0;}
							else {shadea=0;shadeb=2;}
						}
						else //even
						{
							if (!top)
								{
									shadea=0;shadeb=2;
								}
							else
								{
									if (B[location[i]]>9)
										{
										shadea=1;shadeb=2;shadec=0;
										}
									else
									{
										shadea=0;shadeb=2;shadec=1;
									}
								}
						}
			}
					
				
				Triangle.draw(g2d, i+1, ""+(state[i]), mixa, mixb, mixc, shadea, shadeb,shadec);
			/*else if ((B[location[i]]% 10) == 0) 
				Triangle.draw(g2d, i+1, ""+(state[i]), mixa, mixb, mixc ,  Triangle.NEXT);
			else 
			Triangle.draw(g2d, i+1, ""+(state[i]),    mixa, mixb, mixc, Triangle.PREVIOUS);
	*/
	}
	
   }
	
}
	
	
	
	