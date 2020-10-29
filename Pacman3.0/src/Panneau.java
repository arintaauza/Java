import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Panneau extends JPanel {
	private int posX = 10;
	private int posY = 10;
	private int  posXm = 300;
    private int posYm = 300;
    int level = 1;
	private Rectangle r1;
	private Rectangle r2;
	public Goinfer goinfer1 = new Goinfer(50, 50);
	public Gateau gateau1 = new Gateau(10, 3);
	public Nutritioniste[] table_nut= new Nutritioniste[level];// un tableau qui contient les nuts
	Ellipse2D.Double ball,vgateau;
	Ellipse2D.Double[] nutritioniste;
	Ellipse2D.Double vMissile;
	public boolean gateau = false;
	public Rectangle [] obstacles;
	public int nbObstacles;
	JLabel energie = new JLabel("300");
	public static int [][] mat_panneau = new int [500][500];
	
	public Panneau()
	{
		// image
	
		
		createMatPanneau();
		energie.setText("hojo");
		energie.setSize(new Dimension(50,50));
		energie.setForeground(Color.red);
		energie.setBackground(Color.red);
		
		JLabel energie1 = new JLabel("200");
		energie1.setSize(new Dimension(50,50));
		energie1.setForeground(Color.red);
		energie1.setBackground(Color.red);
		
		BorderLayout bl = new BorderLayout();
		this.add(energie,bl.WEST);
		this.add(energie1,bl.EAST);
		
		Thread t1 = new Thread(gateau1);
		t1.start();
		Thread t2 = new Thread(goinfer1);
		t2.start();
		
		for (int i = 0; i < level; i++) 
		{
			table_nut[i] = new Nutritioniste((i+1) * 150, (i+1) * 100, 2);		
		}
		// create Matrice of obstacles
		
		this.setBackground(Color.BLACK);
		this.setSize(new Dimension(500, 500));
		this.setPreferredSize(new Dimension(500,500));
		
		switch (level) {
		case 1:
			nbObstacles = 5;
			break;
		case 2:
			nbObstacles = 6;
			break;
		case 3:
			nbObstacles = 8;
			break;
		default:
			break;
		}
		//gateau1.go();gateau1.go();
		  
	}
	
public void paintComponent(Graphics g){
	   
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g);
		
//		nutr =new Ellipse2D.Double(nut1.getPosX() ,nut1.getPosY(), 20, 20);
// les nutrustionniste

		nutritioniste = new Ellipse2D.Double[level];
				
		for (int i = 0; i < level; i++) 
		{
			nutritioniste[i]  =new Ellipse2D.Double(table_nut[i].getPosX() ,table_nut[i].getPosY(), 20, 20);
			g2.setColor(Color.yellow);
			// g2.fill(nutritioniste[i] );
			 File koko = new File("Nut.png");
			 BufferedImage img;
			try {
				img = ImageIO.read(koko);
				int w = 10;
				int h = 10;

					BufferedImage bi =new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
					
					g2.drawImage(img,table_nut[0].getPosX(),table_nut[0].getPosY(), 35,35,null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		File koko = new File("koko1-0.png");
		
		
		switch (goinfer1.getDir()) {
		case 0:
			koko = new File("koko1-0.png");
			break;
			
			case 1:
				 koko = new File("koko1-1.png");
			break;
			
			case 2:
				 koko = new File("koko1-2.png");
			break;
	
			case 3	:
				 koko = new File("koko1-3.png");
				break;

		default:
			break;
		}
		
		
		int h = 10 ;int w = 10 ; 
		try {
		BufferedImage	img = ImageIO.read(koko);
			 w = 10;
			 h = 10;

				BufferedImage bi =new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
				
				g2.drawImage(img,goinfer1.getPosX(),goinfer1.getPosY(), 35,35,null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
// gateau	
	
		if(gateau1.afficher())
		{
			
	   	vgateau =new Ellipse2D.Double(gateau1.getX() ,gateau1.getY(), 20, 20);
		 g2.setColor(Color.blue);
		 g2.fill(vgateau);
		}
// Missil		
		// on paint la balle d'arm si le bolean Missil.attaquer est true
		if(Missile.attaquer)
		{
			 vMissile =new Ellipse2D.Double(Missile.x ,Missile.y, 10, 10);
			 g2.setColor(Color.green);
			 g2.fill(vMissile);
			 
		}
		//ball = new Ellipse2D.Double(posX ,posY, 20, 20);
		//ennemi = new Ellipse2D.Double(posXm ,posYm, 20, 20);
        g2.setColor(Color.RED);
// goinfer        
        if(goinfer1.estvivant)
        {
	        ball =new Ellipse2D.Double(goinfer1.getPosX() ,goinfer1.getPosY(), 20, 20);
	        g2.setColor(Color.red);
	        //g2.fill(ball);
	       // g2.draw(ball);
	        
        }
 
       createObstacle(1, g2);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
	
	public void getPosRectangle()
	{
		//System.out.println(" pos de raectangle 1 "+r1.getBounds() + "rectangle 2" + r2.getBounds());
	}
	
	// to create matrix of panneau
	public void createMatPanneau() {
	    for (int i = 0; i < 500; i++) {
	        for (int j = 0; j < 500; j++) {
	            mat_panneau[i][j] = 0;
	        
	        	 
	        }
	    }
	}
	
	public boolean checkovelap()
	{
		/*
		if(ball.getBounds().intersects(r1) || ball.getBounds().intersects(r2) )
			return true;
			else
				return false ;
	*/
		return false;
		
	}
	
	public int getPosX() {
	
		return posX;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosY() {
		
		return posY;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
	public int getPosXM() {
		
		return posXm ;
	}
	
	public void setPosXM(int posXm) {
		this.posXm = posXm;
	}
	
	public int getPosYM() {
		
		return posYm;
	}
	
	public void setPosYM(int posYm) {
				this.posY = posYm;

	}
	// fill matrix panneau with obstacles
    public void fillObstacle(Rectangle obstacle) {
        int x = (int)obstacle.getX();
        int y = (int)obstacle.getY();
        int w = (int)obstacle.getWidth();
        int h = (int)obstacle.getHeight();
        for (int i = x; i <= x+w; i++) {
            for (int j = y; j <= y+h; j++) {
                mat_panneau[i][j] = 1;
               
                	
                			
            }
        }
    }
	public void createObstacle(int level,Graphics2D g2) 
	{
		switch (level) {
		case 1:
			obstacles = new Rectangle[5];
			obstacles[0] = new Rectangle(110, 40, 300,20);
			obstacles[1] = new Rectangle(50,100,20,300);
			obstacles[2] = new Rectangle(110,430,300 ,20);
			obstacles[3] = new Rectangle(420,100,20,300);
			obstacles[4] = new Rectangle(150,250,200,20);
			//obstacles[3] = new Rectangle(1, 20, 480,460);
			for(int i = 0;i<5;i++)
			{
				g2.draw(obstacles[i]);
				fillObstacle(obstacles[i]);
			}
			break;
		default:
			break;
		}
	}
	
}

