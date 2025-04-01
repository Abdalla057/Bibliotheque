package Bibliothèque;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Adherent {
    private static final LocalDate dateEmprunt = null;
	private static final LocalDate dateRetourPrevu = null;
	private static final List<Document> NbDocuments = null;
	private String matricule;
    private String nom;
    private double amende;
    private List<Emprunt> emprunts;
    private List<Document> reservations;
	private String prenom;
	private String email;

    public Adherent(String matricule, String nom, String prenom2, String email2) {
        this.matricule = matricule;
        this.nom = nom;
        this.amende = 0.0;
        this.emprunts = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public Adherent(String matricule2, String nom2, int i, Object email2) {
		// TODO Auto-generated constructor stub
	}

	// 1. Emprunter un document
    public boolean emprunter(Document document) {
        emprunts.add(new Emprunt(matricule, document, dateEmprunt, dateRetourPrevu));
        System.out.println(" + nom + " a emprunté '" + document.getTitre() + "' jusqu'au " + dateRetourPrevu);
		return false;
    }

    // 2. Afficher les documents empruntés
    public void afficherEmprunts() {
        if (emprunts.isEmpty()) {
            System.out.println(nom + " n'a aucun emprunt en cours.");
        } else {
            System.out.println(" Documents empruntés par " + nom + " :");
            for (Emprunt emprunt : emprunts) {
                System.out.println("- " + emprunt.getDocument().getTitre() + " (Retour prévu : " + emprunt.getDateRetourPrevu() + ")");
            }
        }
    }

    // 3. Rendre un document
    public void rendreDocument(Document document, LocalDate dateRetour, boolean estAbime) {
        Emprunt empruntTrouve = null;
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getDocument().equals(document)) {
                empruntTrouve = emprunt;
                break;
            }
        }

        if (empruntTrouve != null) {
            long joursDeRetard = ChronoUnit.DAYS.between(empruntTrouve.getDateRetourPrevu(), dateRetour);
            if (joursDeRetard > 0) {
                double amendeRetard = joursDeRetard * 1.0;
                amende += amendeRetard;
                System.out.println(" + nom + " a rendu '" + document.getTitre() + "' avec " + joursDeRetard + " jours de retard. Amende de " + amendeRetard + "€ ajoutée.");
            }

            if (estAbime) {
                amende += 5.0;
                System.out.println(" Le document est abîmé. Amende de 5€ ajoutée.");
            }

            emprunts.remove(empruntTrouve);
            System.out.println(" + document.getTitre() + "' a été rendu par " + nom);
        } else {
            System.out.println(" + nom + " n'a pas emprunté ce document.");
        }
    }

    // 4. Réserver un document
    public void reserverDocument(Document document) {
        reservations.add(document);
        System.out.println("  + nom + " a réservé le document : " + document.getTitre());
    }

    // 5. Payer les taxes
    public void payerTaxes(double montant) {
        if (montant >= amende) {
            System.out.println(" + nom + " a payé toutes ses amendes (" + amende + "€).");
            amende = 0.0;
        } else {
            amende -= montant;
            System.out.println(" + nom + " a payé " + montant + "€. Reste à payer : " + amende + "€.");
        }
    }
    

    public String getMatricule() {
        return matricule;
    }

    public double getAmende() {
        return amende;
    }

	public List<Document> getNbDocumentsEmpruntes() {
		// TODO Auto-generated method stub
		return NbDocuments;
	}

	public String getNom() {
		// TODO Auto-generated method stub
		return nom;
	}

	public String getEmail() {
		// TODO Auto-generated method stub
		return email;
	}

	public String getPrenom() {
		// TODO Auto-generated method stub
		return prenom;
	}

	public Document[] getEmprunts() {
		// TODO Auto-generated method stub
		return null;
	}

	public void rendre(Document document) {
		// TODO Auto-generated method stub
		
	}

	
	

	

	
}
