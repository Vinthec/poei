package fr.vinthec.personnage.modele;

public interface Views {

	// Defined view composed by elementary one to set up in Controller

	// elementary View element to set up on the getter according there return value type (can be set up in controleur if need) 


	// @formatter:off
	public static interface DetailUtilisateur {}
	public static interface DetailUtilisateurDate {}
	
	public static interface OneUnivers {}
	public static interface OneMaison {}
	public static interface OnePersonnage {}
	public static interface OneActeur {}

	
	public static interface ManyMaison {}
	public static interface ManyPersonnage {}
	public static interface ManyActeur {}
	public static interface ManyRelation{}
	public static interface ManyUtilisateur {}
	
	
	public static interface DetailDeclaration extends DetailUtilisateur, DetailUtilisateurDate{}
	// @formatter:on


	//only use in controler prevent for allowing only getter without @JsonView 
	public static interface Minimal {}
	
	// use Internal to prevent attribut to be use as default
	public static interface Internal {}

}
