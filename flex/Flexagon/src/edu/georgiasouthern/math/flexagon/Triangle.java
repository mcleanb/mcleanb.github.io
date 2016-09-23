/**
 * 
 */
package edu.georgiasouthern.math.flexagon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * @author johnnelson
 *
 */
public class Triangle {
	public static final int OUTSIDE = 0;
	public static final int PREVIOUS = 1;
	public static final int NEXT = 2;
	
	public static int edge = 150;
	public static int xc = 200;
	public static int yc = 200;
	public static int radius = 25;

	public static void draw(Graphics2D g, int pat, String label, int mixa, int mixb, 
			int mixc,  int shadea, int shadeb, int shadec) 
	{

		int x2 = (int) (Math.cos(Math.PI/3 + (pat - 1) * Math.PI/3) * edge + xc);
		int y2 = (int) (-Math.sin(Math.PI/3 + (pat - 1) * Math.PI/3) * edge + yc);

		int x1 = (int) (Math.cos(Math.PI/3 + pat * Math.PI/3) * edge + xc);
		int y1 = (int) (-Math.sin(Math.PI/3 + pat * Math.PI/3) * edge + yc);

		int cx = (x1 + x2 + xc) / 3;
		int cy = (y1 + y2 + yc) / 3;
		int x1c, x12, x2c;
		int y1c, y12, y2c;
		x1c = (x1 + xc) / 2;
		y1c = (y1 + yc) / 2;

		x2c = (xc + x2) / 2;
		y2c = (yc + y2) / 2;

		x12 = (x1 + x2) / 2;
		y12 = (y1 + y2) / 2;
		
		Color mix, mixaa, mixbb;
		mix = new Color(mixa,mixb,mixc);
		if ((mixa%50)==0) mixa=mixa+80;
		if ((mixb%50)==0) mixb=mixb+80;
		if((mixc%50)==0) mixc=mixc+80;
		mixaa =  new Color(mixa,mixb,mixc);
		if ((mixa%10)==0) mixa=mixa+30;
		if ((mixb%10)==0) mixb=mixb+30;
		if((mixc%10)==0) mixc=mixc+30;
		mixbb = new Color(mixa,mixb,mixc);
        if (shadea==0) 	
			g.setColor(mix);
        else if (shadea==1)
        	g.setColor(mixaa);
        else 
        	g.setColor(mixbb);
		
		g.fillPolygon(new int[] {cx, x1c, x1, x12}, new int[] {cy, y1c, y1, y12}, 4);//upper left
		if (shadeb==0) 	
			g.setColor(mix);
        else if (shadeb==1)
        	g.setColor(mixaa);
        else 
        	g.setColor(mixbb);
		g.fillPolygon(new int[] {cx, x12, x2, x2c}, new int[] {cy, y12, y2, y2c}, 4);//upper right
		if (shadec==0) 	
			g.setColor(mix);
        else if (shadec==1)
        	g.setColor(mixaa);
        else 
        	g.setColor(mixbb);
		
		g.fillPolygon(new int[] {xc, x2c, cx, x1c}, new int[] {yc, y2c, cy, y1c}, 4);// center
		g.setColor(Color.cyan);
		g.fillArc(cx-radius/2, cy-radius/2, radius, radius, 0, 360);	
		g.setStroke(new BasicStroke(1));
		//g.setColor(Color.orange);
		g.drawLine(x1, y1, xc, yc);
		g.drawLine(x1, y1, x2, y2);
		FontMetrics fm = g.getFontMetrics();
		g.setColor(Color.black);
		int dy = fm.getAscent();
		int dx = fm.stringWidth(label);
		g.drawString(label, cx-dx/2, cy+dy/2);
		
		}

	
}
