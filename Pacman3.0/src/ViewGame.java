import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

public class ViewGame extends JFrame implements Runnable{
	private PanneauActivite panneauActivite = new PanneauActivite();

	private Panneau pan = new Panneau();
	private int x = pan.getX();
	private int y = pan.getY();
	

	int v =pan.goinfer1.getVitesse();//vitesse de mouvement
	
	private int dir = 0; 

	// constructeur par defaut 
	public ViewGame(){

		panneauActivite.setPreferredSize(new Dimension(50,500));

		BorderLayout bl =new BorderLayout();

		this.setTitle("Ma première fenêtre Java");
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);               
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(panneauActivite,bl.NORTH);
		this.add(pan,bl.SOUTH);
		this.setVisible(true);

		//*****************************  Listner clavier ***********************************   
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				
				detecterNutristioniste("goinfer");
				switch (ke.getKeyCode()) 
				{ 	
				case KeyEvent.VK_RIGHT:
				
					pan.goinfer1.setDir(3);

					if(!detecterObstacles("goinfer"))
					{
						//pan.setPosX(x=x+v);
						pan.goinfer1.droit();
						detecterGateau();
						pan.repaint();
					}else 
					{
					
					}
					break;

				case KeyEvent.VK_LEFT:
					pan.goinfer1.setDir(1);

					if(!detecterObstacles("goinfer"))
					{
						pan.goinfer1.gauche();
						detecterGateau();						
						pan.repaint();
					}else 
					{
					
					}
					break;

				case KeyEvent.VK_UP:
					pan.goinfer1.setDir(0);
					if(!detecterObstacles("goinfer"))
					{
						pan.goinfer1.monter();
						detecterGateau();						
						pan.repaint();
					}else 
					{
					
					}
					break;

				case KeyEvent.VK_DOWN:
					pan.goinfer1.setDir(2);
					if(!detecterObstacles("goinfer"))
					{
						pan.goinfer1.descendre();
						detecterGateau();
						pan.repaint();
					}else 
					{
					
					}
					break;
					/*
					 * case space permet de lancer de missil en faisant l'animation de la balle de Missille
					 */
				case KeyEvent.VK_SPACE:
					if(Missile.quantite>0)
					{
						Missile.x = pan.goinfer1.getPosX();
						Missile.y = pan.goinfer1.getPosY();
						Missile.dir = pan.goinfer1.getDir();
						Missile.attaquer=true;
						Missile.quantite -=1;
						detecterNutristioniste("arme");
						final Timer t1 = new Timer();
						t1.schedule(new TimerTask() {
							int compt = 0;
							@Override
							public void run() {
								// TODO Auto-generated method stub
								detecterNutristioniste("arme");
								switch (Missile.dir ) {
								
								case 3:
									if (detecterObstacles("arme"))
									{
										Missile.attaquer=false;
										t1.cancel();
									}
									else
										Missile.x =Missile.x + 2;

									break;

								case 1:
									if (detecterObstacles("arme"))
									{
										Missile.attaquer=false;
										t1.cancel();
									}
									else
										Missile.x =Missile.x - 2;

									break;

								case 0:
									if (detecterObstacles("arme"))
									{
										Missile.attaquer=false;
										t1.cancel();
									}
									else
										Missile.y =Missile.y - 2;
																		break;

								case 2:
									if (detecterObstacles("arme"))
									{
										Missile.attaquer=false;
										t1.cancel();
									}
									else
										Missile.y =Missile.y + 2;
									break;

								default:
									break;
								}

								compt++;

								if (Missile.x>500 ||Missile.x<0 || Missile.y>500 ||Missile.y<0)
								{
									t1.cancel();
							
									compt = 0;
									Missile.attaquer=false;
								}
								repaint();

							}
						},0,5);
					}
					break;

				default:
				
					break;
				}

			}
		}
				);

	}

	 // go pour fair bouger le nutritioniste d'une maniere aleatoire
	 private void go()  {
		   int i = 0;
		
	
		   for(;;)
		   {
			   if(pan.goinfer1.getEnergie()<45)
			   {
				   panneauActivite.progressbarEnergie.setForeground(Color.red);
			   panneauActivite.progressbarEnergie.setValue(pan.goinfer1.getEnergie());
			   }
			   else
			   {
				   panneauActivite.progressbarEnergie.setForeground(Color.blue);
			   panneauActivite.progressbarEnergie.setValue(pan.goinfer1.getEnergie());
			   
			   StringBuilder sb = new StringBuilder();
				sb.append("");
				sb.append(Missile.quantite);
				String strI = sb.toString();
				   panneauActivite.armShow.setText(strI);
			   }
		       for (int j = 0; j < pan.level;j++)
		       {
		          try {
		            Thread.sleep(100);
		           
		          } catch (InterruptedException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		          }
		          if(!detecterObstacles("nutritioniste"))
		          {
		        	  if(!pan.table_nut[j].stop)
		        	  {
		        		  pan.table_nut[j].move();
		        	  }
		        	  
			           pan.repaint();
		    
		          } else 
		          {
		        	  
		          pan.table_nut[0].changer_direction();
		          
		         
		               
		          		pan.repaint();
		          }
		       }  
		   } 
		   }


	
	// permer au ackman de dtecter un gateau et de le manger
	void detecterGateau()
	{
		//
		if(pan.ball.getBounds().intersects(pan.gateau1.getX(), pan.gateau1.getY(), 20, 20) && pan.gateau1.afficher())
		{
			pan.gateau1.masquer();
			pan.goinfer1.manger(pan.gateau1.getType());	
			if (pan.gateau1.getType()==3) {
				pan.table_nut[0].stop = true;
			Timer t1 = new Timer();
			t1.schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					pan.table_nut[0].stop = false;
				}
			}, 6000);
			}
			
		}
	}

	public boolean detecterNutristioniste(String source)
	{
		double x ,y,h,w; // cordonnees de Nutritioniste
		// initialisation
		x = pan.nutritioniste[0].getCenterX();
		y = pan.nutritioniste[0].getCenterY();
		h =  pan.nutritioniste[0].getHeight();
		w = pan.getWidth();
		if (pan.level ==1)
		{
			x = pan.table_nut[0].getPosX();
			y = pan.table_nut[0].getPosY();
			h =  pan.nutritioniste[0].getHeight();
			w = pan.getWidth();
		}
		
		if(source.toString().equals("goinfer"))
		{
			try {
				if(pan.ball.getBounds().intersects(x, y, w, h))
				{
					System.out.println("chock  avec Nutrisioniset>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<< d");
				pan.table_nut[0].changer_direction();
				pan.goinfer1.setPosX(10);
				pan.goinfer1.setPosY(10);
				return true;
				}
			} catch (Exception e) {
				// TODO: handle exception
			
			}
			
			
		}
		else
		{
			if(source.toString().equals("arme"))
			{
				try {
					if(pan.vMissile.getBounds().intersects(x, y, w, h))
					{
					System.out.println("chock  with missile>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<< d");
					pan.table_nut[0].changer_direction();
					 Missile.attaquer = false ;
					 pan.table_nut[0].changer_direction();
					 pan.table_nut[0].move();
					return true;
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				}
		}
		return false ;
	}
	
	public boolean detecterObstacles(String source) 
	{
		// detecter les obstacles (les rectangles "
		double xObs ;
		double yObs;
		double h ;
		double w ;
		int x ;
		int y,dm ;
		// dm : diametre da la balse ou bien de l'arme
		boolean trouver = false;
		int vx = 0 ,vy = 0;
		for(int i=0;i<5;i++)
		{
			
				xObs = pan.obstacles[i].getX();
				yObs = pan.obstacles[i].getY();
				h = pan.obstacles[i].getHeight();
				w = pan.obstacles[i].getWidth();
			
			

			/*
			 * source pour destinguer entre le cas de mouvement de goinfer ou de l'arme
			 */
			x = pan.goinfer1.getPosX();
			y = pan.goinfer1.getPosY();
			dm = 20;
			dir = pan.goinfer1.getDir();
			vx = 0 ;vy = 0;
			if (source.toString().equals("goinfer"))
			{
				x = pan.goinfer1.getPosX();
				y = pan.goinfer1.getPosY();
				dm = 20;
				dir = pan.goinfer1.getDir();
			}
			else 
				if(source.toString().equals("arme"))
				{
					x = Missile.x;
					y = Missile.y;
					dm = 10;
					dir = Missile.dir;
				} else if(source.toString().equals("nutritioniste")) {
					x = pan.table_nut[0].getPosX();
					y = pan.table_nut[0].getPosY();
					dm = 20;
					dir = pan.table_nut[0].getDir();
					}
					
			
			
			
				
			

			switch (dir) {
			case 0: // nord

				if( y-v<0 ||( ((x +dm)> xObs) && ((x) < (xObs + w )) && ((y-v) > yObs) && ((y-v) < (yObs + h ))))
					return true;
				break;
			case 1:// ouest//gauche
				if(x-v <0 ||(((x -v)> xObs) && ((x-v) < (xObs + w )) && ((y+dm) > yObs) && ((y) < (yObs + h ))))
					return true;
				break;

			case 2://sud
				if(y+v+dm>500 ||(((x +dm)> xObs) && ((x) < (xObs + w )) && ((y+v+dm) > yObs) && ((y+v) < (yObs + h ))))
					return true;
				break;
			case 3://est (droit)
				if(x+v+dm>500 ||(((x +dm +v )> xObs) && ((x+ dm +v) < (xObs + w )) && ((y+dm) > yObs) && ((y) < (yObs + h ))))
					return true;
				break;
			default:
				break;
			}
		}

		return trouver;

	}
	public double abs(double a)
	{
		if (a <0)
			return -a;
		else 
			return 
					a;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
			go();		
	}
}

