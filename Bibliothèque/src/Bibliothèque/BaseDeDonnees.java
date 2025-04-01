package Bibliothèque;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BaseDeDonnees {
	private static BaseDeDonnees baseDeDonnees = new BaseDeDonnees();
    private static final String DOSSIER_BASE = "BaseDeDonnees";
    private static final String ADHERENTS_FILE = "adherent.txt";
    private static final String LIVRES_FILE = "livre.txt";
    private static final String REVUES_FILE = "revue.txt";
    private static final String ARTICLES_FILE = "article.txt";
    private static final String EMPRUNTS_FILE = "emprunt.txt";
    private static final String TAXES_FILE = "taxe.txt";

    public BaseDeDonnees() {
        creerDossierEtFichiers();
    }
    

    // 1. Création du dossier et des fichiers
    private void creerDossierEtFichiers() {
        File dossier = new File(DOSSIER_BASE);
        if (!dossier.exists()) {
            if (dossier.mkdir()) {
                System.out.println("📁 Dossier créé : " + dossier.getAbsolutePath());
            } else {
                System.out.println("❌ Impossible de créer le dossier !");
                return;
            }
        }

        creerFichier(ADHERENTS_FILE);
        creerFichier(LIVRES_FILE);
        creerFichier(REVUES_FILE);
        creerFichier(ARTICLES_FILE);
        creerFichier(EMPRUNTS_FILE);
        creerFichier(TAXES_FILE);
    }

    private void creerFichier(String nomFichier) {
        File fichier = new File(Paths.get(DOSSIER_BASE, nomFichier).toString());
        try {
            if (fichier.createNewFile()) {
                System.out.println("📄 Fichier créé : " + fichier.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de la création du fichier " + nomFichier);
            e.printStackTrace();
        }
    }

    // 2. Enregistrer un adhérent dans adherent.txt
    public void enregistrerAdherent(Adherent adherent) {
        String ligne = ((Adherent) adherent).getMatricule() + " && " + adherent.getNom() + " && " + adherent.getPrenom() + " && " +
                       adherent.getEmail() + " && " + adherent.getNbDocumentsEmpruntes();
        ecrireDansFichier(ADHERENTS_FILE, ligne);
    }

    // 3. Enregistrer un livre dans livre.txt
    public void enregistrerLivre(Livre livre) {
        String ligne = livre.getTitre() + " && " + livre.getAuteur() + " && " +
                       livre.getNombrePages() + " && " + livre.getNbExemplaires();
        ecrireDansFichier(LIVRES_FILE, ligne);
    }

    // 4. Enregistrer une revue dans revue.txt
    public void enregistrerRevue(Revue revue) {
        String ligne = revue.getTitre() + " && " + revue.getAuteur() + " && " +
                       revue.getPeriode() + " && " + revue.getNbExemplaires();
        ecrireDansFichier(REVUES_FILE, ligne);
    }

    // 5. Enregistrer un article dans article.txt
    public void enregistrerArticle(Article article) {
        String ligne = article.getTitre() + " && " + article.getAuteur() + " && " +
                       article.getPeriode() + " && " + article.getNbExemplaires();
        ecrireDansFichier(ARTICLES_FILE, ligne);
    }

    // 6. Enregistrer un emprunt dans emprunt.txt
    public void enregistrerEmprunt(Adherent adherent) {
        List<Document> emprunts = adherent.getNbDocumentsEmpruntes();
        String doc1 = emprunts.size() > 0 ? emprunts.get(0).getTitre() : "Rien";
        String doc2 = emprunts.size() > 1 ? emprunts.get(1).getTitre() : "Rien";
        String doc3 = emprunts.size() > 2 ? emprunts.get(2).getTitre() : "Rien";

        String ligne = adherent.getMatricule() + " && " + doc1 + " && " + doc2 + " && " + doc3;
        ecrireDansFichier(EMPRUNTS_FILE, ligne);
    }

    // 7. Enregistrer une taxe dans taxe.txt
    public void enregistrerTaxe(String nature, double montant, Adherent adherent, boolean payee) {
        String situation = payee ? "payé" : "non payé";
        String ligne = nature + " && " + montant + " && " + adherent.getMatricule() + " && " + situation;
        ecrireDansFichier(TAXES_FILE, ligne);
    }

    // Méthode générique pour écrire dans un fichier
    private void ecrireDansFichier(String nomFichier, String contenu) {
        try (FileWriter writer = new FileWriter(Paths.get(DOSSIER_BASE, nomFichier).toString(), true)) {
            writer.write(contenu + "\n");
            System.out.println("✅ Enregistrement dans " + nomFichier + " : " + contenu);
        } catch (IOException e) {
            System.out.println("❌ Erreur lors de l'écriture dans " + nomFichier);
            e.printStackTrace();
        }
    }
    public List<Adherent> chargerAdherents() {
        List<Adherent> adherents = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(DOSSIER_BASE, ADHERENTS_FILE).toString()))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                String[] data = ligne.split(" && ");
                if (data.length == 5) {
                    adherents.add(new Adherent(data[0], data[1], data[2], data[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Erreur lors du chargement des adhérents.");
            e.printStackTrace();
        }
        return adherents;
    }

}
