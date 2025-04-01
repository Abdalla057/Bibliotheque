package Bibliothèque;



public class Livre implements Document {
    private String titre;
    private String auteur;
    private double prix;
    private int pages;
    private boolean disponible;

    public Livre(String titre, String auteur, double prix, int pages) {
        this.titre = titre;
        this.auteur = auteur;
        this.prix = prix;
        this.pages = pages;
        this.disponible = true;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public String getAuteur() {
        return auteur;
    }

    @Override
    public boolean isDisponible() {
        return disponible;
    }

    @Override
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

	public String getNbExemplaires() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombrePages() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afficherDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getPrix() {
		// TODO Auto-generated method stub
		return null;
	}

    // D'autres méthodes spécifiques au livre
}
