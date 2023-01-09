package src.metier;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Resultat{
	private int    rang;
	private String nomJoueur;
	private int    score;
	private int    nbWagonsRestants;
	private int    nbObjectifsReussis;

	public Resultat(int rang, String nomJoueur, int score, int nbWagonsRestants, int nbObjectifsReussis){
		this.rang = rang;
		this.nomJoueur = nomJoueur;
		this.score = score;
		this.nbWagonsRestants = nbWagonsRestants;
		this.nbObjectifsReussis = nbObjectifsReussis;
	}

	public int getRang(){
		return this.rang;
	}

	public String getNomJoueur(){
		return this.nomJoueur;
	}

	public int getScore(){
		return this.score;
	}

	public int getNbWagonsRestants(){
		return this.nbWagonsRestants;
	}

	public int getNbObjectifsReussis(){
		return this.nbObjectifsReussis;
	}

	public void setRang(int rang){
		this.rang = rang;
	}

	public void setNomJoueur(String nomJoueur){
		this.nomJoueur = nomJoueur;
	}

	public void setScore(int score){
		this.score = score;
	}

	public void setNbWagonsRestants(int nbWagonsRestants){
		this.nbWagonsRestants = nbWagonsRestants;
	}

	public void setNbObjectifsReussis(int nbObjectifsReussis){
		this.nbObjectifsReussis = nbObjectifsReussis;
	}
}