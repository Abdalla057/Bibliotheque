package Bibliothèque;

public abstract class Periodique implements Document {
    protected String titre;
    protected String auteur;
    protected int nombrePages;
    protected int numeroEdition;

    public Periodique(String titre, String auteur, int nombrePages, int numeroEdition) {
        this.titre = titre;
        this.auteur = auteur;
        this.nombrePages = nombrePages;
        this.numeroEdition = numeroEdition;
    }

    @Override
    public String getTitre() {
        return titre;
    }

    @Override
    public String getAuteur() {
        return auteur;
    }

    public int getNombrePages() {
        return nombrePages;
    }

    public int getNumeroEdition() {
        return numeroEdition;
    }

    public class Revue extends Periodique {
        public Revue(String titre, String auteur, int nombrePages, int numeroEdition) {
            super(titre, auteur, nombrePages, numeroEdition);
        }

        @Override
        public void afficherDetails() {
            System.out.println("Revue: " + titre + " | Auteur: " + auteur + " | Pages: " + nombrePages + " | Édition N°: " + numeroEdition);
        }

		@Override
		public boolean isDisponible() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setDisponible(boolean disponible) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getPrix() {
			// TODO Auto-generated method stub
			return null;
		}
    }

    public class Article extends Periodique {
        private String journal;

        public Article(String titre, String auteur, int nombrePages, int numeroEdition, String journal) {
            super(titre, auteur, nombrePages, numeroEdition);
            this.journal = journal;
        }

        public String getJournal() {
            return journal;
        }

        @Override
        public void afficherDetails() {
            System.out.println("Article: " + titre + " | Auteur: " + auteur + " | Pages: " + nombrePages +
                               " | Édition N°: " + numeroEdition + " | Publié dans: " + journal);
        }

		@Override
		public boolean isDisponible() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setDisponible(boolean disponible) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getPrix() {
			// TODO Auto-generated method stub
			return null;
		}
    }


}

