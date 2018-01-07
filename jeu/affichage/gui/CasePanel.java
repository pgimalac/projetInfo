package jeu.affichage.gui;

import jeu.plateau.cases.Case;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

import java.util.Random;
import java.util.Iterator;

import java.util.concurrent.CopyOnWriteArrayList;

public class CasePanel extends JPanel implements Iterable<PionG>{

	private final Image background;

	public static int cote=0;
	private final boolean affScore;
	private final int numero;
	private final int score;

	private final CopyOnWriteArrayList<PionG> pions;

	public static final int MINIMUM=100; // taille minimale d'une case

	private final Case c;

	public CasePanel(Case c, int numero, int numeroBis){
		super();
		this.c=c;
		pions=new CopyOnWriteArrayList<PionG>();
		String nom=c.toString();
		this.numero=numero;
		setMinimumSize(new Dimension(MINIMUM,MINIMUM));
		if (nom.startsWith("score(")){
			score=Integer.parseInt(nom.substring(6,nom.length()-1));
			affScore=true;
			Random rand=new Random();
			int n=rand.nextInt(9)+1;
			background=(new ImageIcon("assets/cases/nature"+n+".png")).getImage();
		}else if (nom.equals("normale")){
			Random rand=new Random();
			int n=rand.nextInt(9)+1;
			affScore=false;
			score=0;
			background=(new ImageIcon("assets/cases/nature"+n+".png")).getImage();
		}else if(numero!=numeroBis){ // pont et labyrinthe
			affScore=true;
			score=numeroBis;
			background=(new ImageIcon("assets/cases/"+nom+".png")).getImage();
		}else{
			affScore=false;
			score=0;
			background=(new ImageIcon("assets/cases/"+nom.replace("é","e").replace("ô","o")+".png")).getImage();
		}
	}

	public Iterator<PionG> iterator(){
		return pions.iterator();
	}

	public Case getCase(){
		return c;
	}

	public void add(PionG p){
		if (pions.add(p))
			this.repaint();
	}

	public PionG remove(PionG p){
		if (pions.remove(p))
			this.repaint();
		return p;
	}

	public boolean contains(PionG p){
		return pions.contains(p);
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(background,0,0,getWidth(),getHeight(),null);
		g.drawString(""+numero,getWidth()-15,getHeight()-5); // numéro de la case affiché en bas à gauche
		if (affScore)
			g.drawString("("+score+")",getWidth()-22,10); // score affiché en haut à droite

		int n=1;
		for (PionG p : pions){
			p.paint(g,0,0,getHeight()/2);
		}
	}



	
}