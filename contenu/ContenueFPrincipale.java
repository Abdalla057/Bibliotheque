package contenu;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Classes.Adherent;
import fenetre.AfficherAdherent;
import fenetre.AfficherEmpreint;
import fenetre.AfficherLivres;
import fenetre.AfficherTaxe;


public class ContenueFPrincipale extends JPanel {
    private  Image arrierePlan;
    public ContenueFPrincipale(){

        setLayout(new GridBagLayout());
        arrierePlan = new ImageIcon("images/back.jpg").getImage();
        JLabel etiquetteBienvenue = new JLabel("Bienvenu sur LabeLib");
        etiquetteBienvenue.setForeground(Color.BLACK);
        etiquetteBienvenue.setFont(new Font("Poppins", Font.BOLD, 50));
        // Creation des buttons
        JButton ajouterDocument = new JButton("Ajouter document");
        ajouterDocument.setBackground(Color.WHITE);
        ajouterDocument.setForeground(Color.BLUE);
        ajouterDocument.setPreferredSize(new Dimension(200, 30));
        JButton ajouterAdherent = new JButton("Ajouter Adherent");
        ajouterAdherent.setBackground(Color.WHITE);
        ajouterAdherent.setForeground(Color.BLUE);
        ajouterDocument.setPreferredSize(new Dimension(200, 30));
        JButton afficherDocument = new JButton("Afficher les documents");
        afficherDocument.setPreferredSize(new Dimension(200, 30));
        afficherDocument.setBackground(Color.WHITE);
        afficherDocument.setForeground(Color.BLUE);
        JButton afficherAdherent = new JButton("Afficher les adherents");
        ajouterAdherent.setPreferredSize(new Dimension(200, 30));
        afficherAdherent.setBackground(Color.WHITE);
        afficherAdherent.setForeground(Color.BLUE);
        afficherAdherent.setPreferredSize(new Dimension(200, 30));
        JButton afficherTaxe = new JButton("Afficher les taxex");
        afficherTaxe.setPreferredSize(new Dimension(200, 30));
        afficherTaxe.setBackground(Color.WHITE);
        afficherTaxe.setForeground(Color.BLUE);

        // Empreinter un document 

        JButton empreinterDocuemnt = new JButton("Empreinter");
        empreinterDocuemnt.setBackground(Color.WHITE);
        empreinterDocuemnt.setPreferredSize(new Dimension(200, 30));
        empreinterDocuemnt.setForeground(Color.BLUE);
        empreinterDocuemnt.setPreferredSize(new Dimension(200, 30));
        JButton afficherEmpreint =new JButton("Afficher la liste des empreints");
        afficherEmpreint.setBackground(Color.WHITE);
        afficherEmpreint.setForeground(Color.BLUE);
        afficherEmpreint.setPreferredSize(new Dimension(200, 30));
        // Ajouter les boutons au contenu principale
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(etiquetteBienvenue);
        add(ajouterAdherent, gbc);
        gbc.gridy = 2;
        add(ajouterDocument, gbc);
        gbc.gridy = 3;
        add(afficherAdherent, gbc);
        gbc.gridy = 4;
        add(afficherDocument, gbc);
        gbc.gridy = 5;
        add(empreinterDocuemnt, gbc);
        gbc.gridy = 6;
        add(afficherEmpreint, gbc);
        gbc.gridy = 7;
        add(afficherTaxe, gbc);
        // Evenement sur les boutons :


        // Ajouter un document
        
        ajouterDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String titreLivre, auteur;
                int prix, nombreExemplaire, nombrePage;
                String[] options = {"Livres", "Articles", "Revues"};
                String documentType;
                // Affichage de la liste déroulante
                String choix = (String) JOptionPane.showInputDialog(
                null,
                "Sélectionnez un type :",
                "Liste Déroulante",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0] // Valeur par défaut
                );
                documentType = choix;
                titreLivre = JOptionPane.showInputDialog("Entrer le titre du "+documentType);
                auteur = JOptionPane.showInputDialog("Entrer le nom de l'auteur");
                nombrePage = Integer.parseInt(JOptionPane.showInputDialog("Enter le nombre de page"));
                nombreExemplaire = Integer.parseInt(JOptionPane.showInputDialog("Enter le nombre d'exemplaire"));
                prix = Integer.parseInt(JOptionPane.showInputDialog("Enter le prix du "+documentType));

                // Ecriture des données dans livres.txt
                try{
                    BufferedWriter ecrirLivre = new BufferedWriter(new FileWriter("baseDeDonnee/"+documentType+".txt", true));
                    if(Adherent.valid_nom(auteur)){
                        ecrirLivre.write(titreLivre+"&&"+auteur+"&&"+prix+"&&"+nombrePage+"&&"+nombreExemplaire);
                        JOptionPane.showMessageDialog(null, "Livre enregistrer avec succes");
                        ecrirLivre.newLine();
                        ecrirLivre.close();
                    }
                    else
                        JOptionPane.showConfirmDialog(null, "Veuillez entrer un nom valide", "NOm", 0);
                    
                }
                catch(IOException erreur){
                    JOptionPane.showMessageDialog(null, erreur.getMessage());
                }
            }
        });
        afficherDocument.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AfficherLivres();
            }
        });
















        // Ajouter un adherent
        ajouterAdherent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String matricule, nom, prenom ,email;

                nom = JOptionPane.showInputDialog("Entrer le nom de l'adherent");
                prenom = JOptionPane.showInputDialog("Entrer le prenom de l'adherent");
                email = JOptionPane.showInputDialog("Enter l'adresse mail  de l'adherent");
                ArrayList<String> testlilst = new ArrayList<>();
                
                // Ici je teste de recuperer le dernier matricule pour pouvoire incrementer proprement les matricules donc c'est un parti de test
                // ====================================================================================================================
                try{
                    BufferedReader lectureDeteste = new BufferedReader(new FileReader("baseDeDonnee/adherent.txt"));
                    String ligne;
                    
                    while ((ligne = lectureDeteste.readLine())!=null) {
                        System.out.println(ligne);
                        testlilst.add(ligne);
                    }
                    Adherent.matricule = testlilst.size();
                }
                catch(IOException ess){
                    JOptionPane.showConfirmDialog(null, ess.getMessage());
                }

                // ====================================================================================================================


                // Ecriture des données dans livres.txt
                try{
                    BufferedWriter ecrirLivre = new BufferedWriter(new FileWriter("baseDeDonnee/adherent.txt", true));
                    if(Adherent.valid_nom(nom ) && Adherent.valid_nom(prenom) && Adherent.mailValid(email)){

                        ecrirLivre.write(Adherent.matricule++ +"&&"+nom+"&&"+prenom+"&&"+email);
                        JOptionPane.showMessageDialog(null, nom+" enregistrer avec succes");
                        ecrirLivre.newLine();
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Veuillez entrer des informations valides", "email", 0);
                    ecrirLivre.close();
                }
                catch(IOException erreur){
                    JOptionPane.showMessageDialog(null, erreur.getMessage());
                }
            }
        });

        // Afficher les adherents
        afficherAdherent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AfficherAdherent();
            }
        });


























        // Empreinter un document 
        empreinterDocuemnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String matriculeAdherent, titreDocument, typeDocument ,email;
               
                matriculeAdherent = JOptionPane.showInputDialog("Entrer le matricule de l'adherent");
                titreDocument = JOptionPane.showInputDialog("Entrer le titre du document");
                typeDocument = JOptionPane.showInputDialog("Entrer le type du document");
                
                // Ecriture des données dans livres.txt
                try{
                    BufferedWriter ecrirLivre = new BufferedWriter(new FileWriter("baseDeDonnee/empreint.txt", true));
                    ecrirLivre.write(matriculeAdherent+"&&"+titreDocument+"&&"+typeDocument);
                    JOptionPane.showMessageDialog(null, titreDocument+" emprinter avec succes");
                    ecrirLivre.newLine();
                    ecrirLivre.close();
                }
                catch(IOException erreur){
                    JOptionPane.showMessageDialog(null, erreur.getMessage());
                }
            }
        });

        // Afficher la liste des empreints
        afficherEmpreint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AfficherEmpreint();
            }
        });

        afficherTaxe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new AfficherTaxe();
            }
        });
       

        
        

    }

    
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(arrierePlan, 0, 0, getWidth(), getHeight(), this);
    }
}
