public enum TypeMeuble {  
//	http://blog.paumard.org/cours/java/chap04-structure-classe-enumeration.html
	table("Une table", "rouge", "carré", 4, "table".charAt(0)),
	canape("Une canapé", "noir", "rectangle", 2, "canapé".charAt(4)),
	television("Une télévision", "noir", "", 1, "télévision".charAt(4)),
	chaise("Une chaise", "rouge", "", 1, "chaise".charAt(0)),
	wc("Des wc", "blanc", "", 1, "wc".charAt(0)),
	douche("Une douche", "blanc", "carré", 4, "douche".charAt(0)),
	lavabo("Un lavabo", "blanc", "", 1, "lavabo".charAt(4)),
	lit("Un lit", "rouge", "carré", 4, "lit".charAt(0)),
	microOnde("Un micro-onde", "blanc", "", 1, "micro-onde".charAt(0)),
	evier("Un évier", "argent", "", 1, "évier".charAt(0)),
	frigo("Un frigo", "blanc", "rectangle", 2, "frigo".charAt(0)),
	porte("Une porte", "blanc", "", 0, "porte".charAt(1));
      
     private String nom; 
     private String couleur;  
     private String forme;  
     private int taille;  
     private char charactere;   
      
     private TypeMeuble(String nom, String couleur, String forme, int taille, char charactere) {  
         this.nom = nom;  
         this.couleur = couleur;  
         this.forme = forme;  
         this.taille = taille;  
         this.charactere = charactere;  

    }  
      
     public String getNom() {  
         return  this.nom;
     }  
     public String getCouleur() {  
         return  this.couleur;
     }
     public String getForme() {  
         return  this.forme;
     }
     public int getTaille() {  
         return  this.taille;
     }
     public char getCharactere() {  
         return  this.charactere;
     }
}
