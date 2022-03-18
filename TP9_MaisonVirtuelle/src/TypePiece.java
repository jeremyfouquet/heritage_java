import java.util.Arrays;
import java.util.List;

public enum  TypePiece {
//	http://blog.paumard.org/cours/java/chap04-structure-classe-enumeration.html
	cuisine("La cuisine", "blanc", Arrays.asList(TypeMeuble.microOnde, TypeMeuble.evier, TypeMeuble.frigo)),
	chambre("La chambre", "blanc",  Arrays.asList(TypeMeuble.lit)),
	salleDeBain("La salle de bain", "blanc", Arrays.asList(TypeMeuble.douche, TypeMeuble.lavabo)),
	salon("Le salon", "blanc", Arrays.asList(TypeMeuble.table, TypeMeuble.canape, TypeMeuble.television, TypeMeuble.chaise, TypeMeuble.chaise, TypeMeuble.chaise, TypeMeuble.chaise)),
	toilettes("Les toilettes", "blanc", Arrays.asList(TypeMeuble.wc));
		

	 private String nom; 
	 private String couleur; 
     private List<TypeMeuble> typeMeubles;  
      
     private TypePiece(String nom, String couleur, List<TypeMeuble> typeMeubles) {  
         this.nom = nom; 
         this.couleur = couleur; 
         this.typeMeubles = typeMeubles; 
     }  
     public String getNom() {  
         return  this.nom;
     }
     public String getCouleur() {  
         return  this.couleur;
     }
     public List<TypeMeuble> getTypeMeubles() {  
         return  this.typeMeubles;
     }		
}