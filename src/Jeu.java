import java.util.Scanner;

public class Jeu {
	private String 	tabCouleur[] = {"Acajou","Brique","Cyan","Denim","Ecru","Fuchsia","Gris","Havane","Indigo","Jaune","Kaki","Lavande"};
	private int 	tabAlea[] = new int [4];
	private int 	alea;
	private char 	tabAscii[] = new char[4];
	private String 	propInvalide = "Vous avez entrez plusieurs fois la même couleurs, proposition invalide \n";
	
	
	public int getAlea(){
		this.alea = (int)(Math.random()*12);
		return this.alea;
	}
	
	public void tabAlea(){
		int nbAlea;
		for(int i = 0; i<4; i++){
			nbAlea = getAlea();
			while (existe(nbAlea,i)==true){
				nbAlea = getAlea();
			}
			tabAlea[i] = nbAlea;			
		}
	}
	
	public boolean existe(int alea, int pos){
		for(int i=0; i<pos; i++){
			if (tabAlea[i] == alea)
				return true;		
		}
		return false;
	}
	
	public int getTabAlea(int pos){
		return tabAlea[pos];
	}
	
	public String getTabCouleur(int pos){
		return tabCouleur[pos];
	}
	
	public String getProp(String proposition){
		String selection = "";
		for(int i = 0 ; i < proposition.length() ; i++){
			tabAscii[i] = proposition.charAt(i);
			selection = selection + getTabCouleur(((int)tabAscii[i])-65) + ", ";
			if (existeAscii(tabAscii[i], i)== true) {
				return propInvalide;
			}	
		}
		return selection;
		
	}
	public boolean existeAscii(char ascii, int pos){
		for (int i= 0; i<pos; i++){
			if(tabAscii[i] == ascii){
				return true;
			}
		}
		return false;
		
	}
	
	public int resultat(String proposition){
		int verif;
		int valide = 0;
		for(int i = 0 ; i < proposition.length() ; i++){
			char nbascii = proposition.charAt(i);
			verif = (((int)nbascii)-65);
			for (int j = 0; j< 4; j++){
				if (verif == getTabAlea(j)){
					valide ++;
				}
			}
		}
		return valide;
	}
	
	public String placement(String proposition){
		String position = "Position : ";
		int verif;
		for(int i = 0 ; i < proposition.length() ; i++){
			char nbascii = proposition.charAt(i);
			verif = (((int)nbascii)-65);
			for (int j = 0; j< 4; j++){
				if (verif == getTabAlea(j)){
					position = position + (i+1) + ", ";
				}
			}
		}
		return position;
	}
	
	public int resultatDifficile(String proposition){
		int verif;
		int valide = 0;
		for(int i = 0 ; i < proposition.length() ; i++){
			char nbascii = proposition.charAt(i);
			verif = (((int)nbascii)-65);
			if (verif == getTabAlea(i)){
				valide ++;
			}
		}
		return valide;
	}
	
	public void affichage(){
		Scanner kb = new Scanner(System.in);
		String proposition = "";
		int tentative = 0;
		System.out.println("Quel niveaux de difficulté voulez vous choisir ?");
		System.out.println("1- Facile \t 2- Difficile");
		int choix = kb.nextInt();
		kb.nextLine();
		if (choix == 1){
			// System.out.println(tabAlea[0] + ", " + tabAlea[1] + ", " + tabAlea[2] +  ", " + tabAlea[3]); 	AFFICHE LES VALEURS ALEATOIRE
			while(resultat(proposition) != 4 || getProp(proposition) == propInvalide ){
				tentative ++;
				System.out.println("Codes couleurs : ABCDEFGHIJKL ");
				System.out.print("Votre proposition : ");
				proposition = kb.nextLine();
				System.out.println("proposition " + tentative + " : " + getProp(proposition) );
				if (getProp(proposition) != propInvalide){
					System.out.println("nombre de couleurs trouvée : " + resultat(proposition) + "\n" + placement(proposition) + "\n");
				}
			}
			
			if (resultat(proposition) == 4 && getProp(proposition) != propInvalide){
				System.out.println("Félicitaion vous avez trouvé la solution en " + tentative + " tentatives !");
			}
		}
		else if (choix == 2){
			while(resultatDifficile(proposition) != 4  || getProp(proposition) != propInvalide){
				tentative ++;
				System.out.println("Codes couleurs : ABCDEFGHIJKL ");
				System.out.print("Votre proposition : ");
				proposition = kb.nextLine();
				System.out.println("proposition " + tentative + " : " + getProp(proposition));
				if (getProp(proposition) != propInvalide){
					System.out.println("nombre de couleurs trouvée : " + resultat(proposition) + "\n" + resultatDifficile(proposition) + " Sont biens placés !");
				}
				
			}
			
			if (resultat(proposition) == 4 && getProp(proposition) != propInvalide){
				System.out.println("Félicitaion vous avez trouvé la solution en " + tentative + " tentatives !");
			
			}
		}
		
		else {System.out.println("Votre choix n'est pas valide !");
		affichage();}
	}
	
}
