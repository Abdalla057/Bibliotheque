package Bibliothèque;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Bibliotheque {
    private List<Document> documents;
    private Map<String, Double> adherents; // Matricule → Amende
    private List<Emprunt> emprunts;
    private List<Document> documentsRendus;

    public Bibliotheque() {
        this.documents = new ArrayList<>();
        this.adherents = new HashMap<>();
        this.emprunts = new ArrayList<>();
        this.documentsRendus = new ArrayList<>();
    }

    // 1. Ajouter un document
    public void ajouterDocument(Document document) {
        documents.add(document);
        System.out.println("Document ajouté : " + document.getTitre());
    }

    // 2. Vérifier la disponibilité d’un document
    public boolean verifierDisponibilite(String titre) {
        for (Emprunt emprunt : emprunts) {
            if (emprunt.getDocument().getTitre().equalsIgnoreCase(titre)) {
                return false; // Document actuellement emprunté
            }
        }
        return true;
    }

    // 3. Afficher tous les documents de la bibliothèque
    public void afficherTousDocuments() {
        System.out.println("\n📚 Liste des documents dans la bibliothèque :");
        for (Document doc : documents) {
            doc.afficherDetails();
        }
    }

    // 4. Vérifier l’état d’un document rendu
    public void verifierEtatDocument(Document document, boolean estAbime) {
        if (estAbime) {
            System.out.println("⚠️ Le document '" + document.getTitre() + "' est abîmé. Une amende peut être appliquée.");
        } else {
            System.out.println("✅ Le document '" + document.getTitre() + "' est en bon état.");
        }
    }

    // 5. Classer les documents rendus et les mettre à jour
    public void classerDocRendu() {
        documents.addAll(documentsRendus);
        documentsRendus.clear();
        System.out.println("📂 Tous les documents rendus ont été classés et mis à jour.");
    }

    // 6. Enregistrer un adhérent
    public void enregistrerAdherent(String matricule) {
        if (!adherents.containsKey(matricule)) {
            adherents.put(matricule, 0.0);
            System.out.println("👤 Adhérent enregistré avec le matricule : " + matricule);
        } else {
            System.out.println("⚠️ Cet adhérent existe déjà.");
        }
    }

    // 7. Rechercher un adhérent par matricule
    public boolean rechercherAdherent(String matricule) {
        return adherents.containsKey(matricule);
    }

    // 8. Taxer une amende pour un retard ou un document abîmé
    public void taxerAmende(String matricule, double montant) {
        if (adherents.containsKey(matricule)) {
            adherents.put(matricule, adherents.get(matricule) + montant);
            System.out.println("💰 Amende de " + montant + "€ ajoutée pour l'adhérent " + matricule);
        } else {
            System.out.println("❌ Adhérent non trouvé !");
        }
    }

    // 9. Enregistrer une amende payée
    public void enregistrerAmendePayer(String matricule, double montant) {
        if (adherents.containsKey(matricule)) {
            double amendeActuelle = adherents.get(matricule);
            if (montant >= amendeActuelle) {
                adherents.put(matricule, 0.0);
            } else {
                adherents.put(matricule, amendeActuelle - montant);
            }
            System.out.println("✅ Paiement de " + montant + "€ enregistré pour l'adhérent " + matricule);
        } else {
            System.out.println("❌ Adhérent non trouvé !");
        }
    }

    // 10. Enregistrer une date d’emprunt et de retour prévu
    public void enregistrerDateEmprunt(String matricule, Document document, LocalDate dateEmprunt, LocalDate dateRetourPrevu) {
        if (verifierDisponibilite(document.getTitre())) {
            emprunts.add(new Emprunt(matricule, document, dateEmprunt, dateRetourPrevu));
            System.out.println("📖 L'adhérent " + matricule + " a emprunté '" + document.getTitre() + "' jusqu'au " + dateRetourPrevu);
        } else {
            System.out.println("❌ Le document '" + document.getTitre() + "' n'est pas disponible.");
        }
    }

    // 11. Gérer les retours et mettre à jour les documents
    public void gererMiseAJourDocRendu(String matricule, Document document, LocalDate dateRetour, boolean estAbime) {
        Emprunt empruntRetrouve = null;

        for (Emprunt emprunt : emprunts) {
            if (emprunt.getMatricule().equals(matricule) && emprunt.getDocument().equals(document)) {
                empruntRetrouve = emprunt;
                break;
            }
        }

        if (empruntRetrouve != null) {
            long joursDeRetard = ChronoUnit.DAYS.between(empruntRetrouve.getDateRetourPrevu(), dateRetour);
            if (joursDeRetard > 0) {
                double amende = joursDeRetard * 1.0; // 1€ par jour de retard
                taxerAmende(matricule, amende);
                System.out.println("⚠️ Retour en retard de " + joursDeRetard + " jours. Amende appliquée.");
            }

            if (estAbime) {
                taxerAmende(matricule, 5.0);
                System.out.println("⚠️ Le document est abîmé. Une amende de 5€ a été ajoutée.");
            }

            documentsRendus.add(document);
            emprunts.remove(empruntRetrouve);
            System.out.println("📚 Le document '" + document.getTitre() + "' a été rendu par l'adhérent " + matricule);
        } else {
            System.out.println("❌ Ce document n'a pas été enregistré comme emprunté.");
        }
    }

	public void ajouterAdherent(Adherent adherent) {
		// TODO Auto-generated method stub
		
	}

	public Adherent chercherAdherent(String matricule) {
		// TODO Auto-generated method stub
		return null;
	}

	public Document chercherDocument(String titre) {
		// TODO Auto-generated method stub
		return null;
	}

	public Adherent[] getAdherents() {
		// TODO Auto-generated method stub
		return null;
	}

	public double calculerAmende(Adherent adherent) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean reserverDocument(Adherent adherent, Document document) {
		// TODO Auto-generated method stub
		return false;
	}

	public Document[] getDocuments() {
		// TODO Auto-generated method stub
		return null;
	}
}
