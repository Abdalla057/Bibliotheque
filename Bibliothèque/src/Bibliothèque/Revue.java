package Bibliothèque;




public class Revue implements Document {
    private String titre;
    private String auteur;
    private String periode;
    private boolean disponible;

    public Revue(String titre, String auteur, String periode) {
        this.titre = titre;
        this.auteur = auteur;
        this.periode = periode;
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

	public String getPeriode() {
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

    // D'autres méthodes spécifiques à la revue
}
