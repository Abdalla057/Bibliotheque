package Bibliothèque;


public interface Document {
    String getTitre();
    String getAuteur();
    boolean isDisponible();
    void setDisponible(boolean disponible);
	void afficherDetails();
	Object getPrix();
}

