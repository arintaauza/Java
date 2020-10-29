import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.plaf.ProgressBarUI;

public class PanneauActivite extends JPanel {
	private Rectangle r1;
	private Rectangle r2;
	JLabel energie = new JLabel("    Energie ");
	JLabel energie1 =new JLabel("    Arme ");
	JPanel pan1 = new JPanel();//1er  premier partie contient energie et Missil
	JPanel pan2 = new JPanel();// 2 contient les gateux
	JPanel pan3 = new JPanel();// content time et level
	JProgressBar progressbarEnergie = new JProgressBar(0, 100);
	JTextField armShow = new JTextField();
	public PanneauActivite() {
// test image
		

		
		// TODO Auto-generated constructor stub
		this.setBackground(Color.gray);
		this.setSize(new Dimension(500, 100));
		this.setPreferredSize(new Dimension(500,100));
// progress bar 
		progressbarEnergie.setValue(100);
		progressbarEnergie.setStringPainted(true);
		progressbarEnergie.setBackground(Color.black);
		progressbarEnergie.setForeground(Color.blue);
//Arme text
		armShow.setBackground(Color.black);
		//armShow.setForeground(Color.blue);
		armShow.setText("kokook"); 
		armShow.enable(false);
		armShow.setFont(new Font("SansSerif", Font.BOLD, 20));
		armShow.setHorizontalAlignment(JLabel.CENTER);
		//armShow.setEditable(false);
		//armShow.setSize(30, 30);

// label energie
			
		energie.setSize(new Dimension(50,50));
		energie.setForeground(Color.blue);
		energie.setBackground(Color.black);
		energie.setFont( new Font("SansSerif", Font.BOLD, 14));
		
		energie1.setSize(new Dimension(50,50));
		energie1.setForeground(Color.blue);
		energie1.setBackground(Color.black);
		energie1.setFont( new Font("SansSerif", Font.BOLD, 14));
		
		pan1.setPreferredSize(new Dimension(190,100));
		pan2.setPreferredSize(new Dimension(100,100));
		pan3.setPreferredSize(new Dimension(190,100));
		
		pan1.setBackground(Color.black);
		pan2.setBackground(Color.black);
		pan3.setBackground(Color.black);
		
		BorderLayout bl = new BorderLayout();
		GridLayout gl = new GridLayout(2,2);
		pan1.setLayout(gl);
		
		
		
		pan1.add(energie);
		pan1.add(progressbarEnergie);
		pan1.add(energie1);
		pan1.add(armShow);
	
		
		
		
		
		
		add(pan1,bl.WEST);
		add(pan2,bl.EAST);
		add(pan3,bl.CENTER);
	
	
	
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		repaint();
	}
	
	public void paintComponent(Graphics g){
		
		
	}
	
}
