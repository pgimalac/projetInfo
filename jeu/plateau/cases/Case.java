package jeu.plateau.cases;

/**
 *	La classe représentant une case 'par défaut' (une case qui n'a rien de particulier) dont les autres cases vont hériter
 */

public class Case{

	// pas final car un éventuel outil de création intéractive de plateau pourrait obliger à changer ce numéro au fur et à mesure de la création...
	private int numero; 
	private Case suivante;
	private Case precedente;

	public Case(int i, Case p, Case s){
		numero=i;
		precedente=p;
		suivante=s;
	}

	Case next(){ return suivante; }
	Case previous(){ return precedente; }

	boolean peutBouger(){ return false; }
	
}