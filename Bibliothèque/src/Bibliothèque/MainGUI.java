package Bibliothèque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainGUI {
    private static Bibliotheque bibliotheque = new Bibliotheque();
    private static JTextField txtTitre, txtAuteur, txtMatricule, txtNom, txtPrenom, txtEmail;
    private static JTextArea txtAreaEmprunts, txtAreaAdherents;
    private static JTextField txtTaxeAdherent;
	private static Document[] document;

    public static void main(String[] args) {
        // Création de la fenêtre principale
        JFrame frame = new JFrame("Gestion de Bibliothèque");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Formulaire pour ajouter un document
        JPanel panelDocument = new JPanel(new FlowLayout());
        panelDocument.add(new JLabel("Titre:"));
        txtTitre = new JTextField(15);
        panelDocument.add(txtTitre);

        panelDocument.add(new JLabel("Auteur:"));
        txtAuteur = new JTextField(15);
        panelDocument.add(txtAuteur);

        JButton btnAjouterDocument = new JButton("Ajouter Document");
        panelDocument.add(btnAjouterDocument);
        panel.add(panelDocument);

        // Formulaire pour ajouter un adhérent
        JPanel panelAdherent = new JPanel(new FlowLayout());
        panelAdherent.add(new JLabel("Matricule:"));
        txtMatricule = new JTextField(10);
        panelAdherent.add(txtMatricule);

        panelAdherent.add(new JLabel("Nom:"));
        txtNom = new JTextField(10);
        panelAdherent.add(txtNom);

        panelAdherent.add(new JLabel("Prénom:"));
        txtPrenom = new JTextField(10);
        panelAdherent.add(txtPrenom);

        panelAdherent.add(new JLabel("Email:"));
        txtEmail = new JTextField(15);
        panelAdherent.add(txtEmail);

        JButton btnAjouterAdherent = new JButton("Ajouter Adhérent");
        panelAdherent.add(btnAjouterAdherent);
        panel.add(panelAdherent);

        // Zone de texte pour afficher les emprunts
        txtAreaEmprunts = new JTextArea(5, 40);
        txtAreaEmprunts.setEditable(false);
        panel.add(new JScrollPane(txtAreaEmprunts));

        // Zone de texte pour afficher la liste des adhérents
        txtAreaAdherents = new JTextArea(5, 40);
        txtAreaAdherents.setEditable(false);
        panel.add(new JScrollPane(txtAreaAdherents));

        // Ajouter un adhérent
        btnAjouterAdherent.addActionListener(e -> {
            String matricule = txtMatricule.getText();
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String email = txtEmail.getText();

            Adherent adherent = new Adherent(matricule, nom, prenom, email);
            bibliotheque.ajouterAdherent(adherent);

            JOptionPane.showMessageDialog(frame, "Adhérent ajouté : " + nom + " " + prenom);
            afficherListeAdherents();
        });

        // Ajouter un document
        btnAjouterDocument.addActionListener(e -> {
            String titre = txtTitre.getText();
            String auteur = txtAuteur.getText();
            double prix = 15.99; 
            Document document = new Livre(titre, auteur, prix, 100);

            bibliotheque.ajouterDocument(document);

            JOptionPane.showMessageDialog(frame, "Document ajouté : " + titre);
        });

        // Bouton pour afficher le nombre de documents
        JButton btnNombreDocuments = new JButton("Nombre de Documents");
        panel.add(btnNombreDocuments);
        btnNombreDocuments.addActionListener(e -> {
            int nombreDocuments = bibliotheque.getDocuments().length;
            JOptionPane.showMessageDialog(frame, "📚 Nombre total de documents : " + nombreDocuments);
        });

        // Bouton pour afficher le nombre d'adhérents
        JButton btnNombreAdherents = new JButton("Nombre d'Adhérents");
        panel.add(btnNombreAdherents);
        btnNombreAdherents.addActionListener(e -> {
            int nombreAdherents = bibliotheque.getAdherents().length;
            JOptionPane.showMessageDialog(frame, "👥 Nombre total d'adhérents : " + nombreAdherents);
        });

        // Bouton pour consulter les documents ajoutés
        JButton btnConsulterDocuments = new JButton("Consulter les Documents");
        panel.add(btnConsulterDocuments);
        btnConsulterDocuments.addActionListener(e -> {
            Object documents = bibliotheque.getDocuments();
            if (((String) documents).isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Aucun document enregistré.");
                return;
            }

            StringBuilder message = new StringBuilder("📚 Liste des Documents :\n");
            for (Document doc : document) {
                message.append(doc.getTitre()).append(" - ").append(doc.getAuteur()).append("\n");
            }

            JOptionPane.showMessageDialog(frame, message.toString(), "Documents Disponibles", JOptionPane.INFORMATION_MESSAGE);
        });

        // Bouton pour afficher les emprunts d'un adhérent
        JButton btnAfficherEmprunts = new JButton("Afficher Emprunts");
        panel.add(btnAfficherEmprunts);
        btnAfficherEmprunts.addActionListener(e -> {
            String matricule = txtMatricule.getText();
            Adherent adherent = bibliotheque.chercherAdherent(matricule);

            if (adherent != null) {
                StringBuilder empruntsText = new StringBuilder();
                for (Document doc : adherent.getEmprunts()) {
                    empruntsText.append(doc.getTitre()).append("\n");
                }
                txtAreaEmprunts.setText(empruntsText.toString());
            } else {
                JOptionPane.showMessageDialog(frame, "Adhérent non trouvé");
            }
        });

        // Taxer une amende
        JButton btnTaxerAmende = new JButton("Taxer Amende");
        panel.add(btnTaxerAmende);

        txtTaxeAdherent = new JTextField(10);
        panel.add(new JLabel("Matricule Adhérent:"));
        panel.add(txtTaxeAdherent);

        btnTaxerAmende.addActionListener(e -> {
            String matricule = txtTaxeAdherent.getText();
            Adherent adherent = bibliotheque.chercherAdherent(matricule);

            if (adherent != null) {
                double amende = bibliotheque.calculerAmende(adherent);
                JOptionPane.showMessageDialog(frame, "Amende calculée pour " + matricule + " : " + amende);
            } else {
                JOptionPane.showMessageDialog(frame, "Adhérent non trouvé");
            }
        });

        // Affichage de la fenêtre
        frame.setVisible(true);
    }

    // Méthode pour afficher la liste des adhérents
    private static void afficherListeAdherents() {
        StringBuilder adherentsText = new StringBuilder();
        for (Adherent adherent : bibliotheque.getAdherents()) {
            adherentsText.append(adherent.getNom()).append(" ").append(adherent.getPrenom())
                    .append(" (").append(adherent.getMatricule()).append(")\n");
        }
        txtAreaAdherents.setText(adherentsText.toString());
    }
}
