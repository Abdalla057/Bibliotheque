package fenetre;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class AfficherLivres extends JFrame {
    public AfficherLivres(){

        // Lecture de livres.txt pour afficher l'ensemble des livre disponible=========================
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
        ArrayList<ArrayList<Object>> donneAdherent = new ArrayList<>();
        try{
            BufferedReader lectureDesLivres = new BufferedReader(new FileReader("baseDeDonnee/"+documentType+".txt"));
            String ligne;
            while ((ligne = lectureDesLivres.readLine())!=null) {
                String[] Tableaumot = ligne.split("&&");
                donneAdherent.add(new ArrayList<>(Arrays.asList(Tableaumot[0], Tableaumot[1],Tableaumot[2], Tableaumot[3], Tableaumot[4])));
            }
            Object[][] tableauAdherent =new  Object[donneAdherent.size()][];
            for(int i = 0; i< donneAdherent.size(); i++){
                tableauAdherent[i] = donneAdherent.get(i).toArray();
            }
            String[] enteteDocuments = {"Titre", "Auteur", "Prix","Nombre de page", "Nombre d'exemplaire" };
            JTable tableauDocuments = new JTable(tableauAdherent, enteteDocuments);

            setLayout(new BorderLayout());
            add(tableauDocuments.getTableHeader(), BorderLayout.NORTH);
            add(tableauDocuments, BorderLayout.CENTER);
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "test", 0);
        }
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        
       



        // ============================================================================================

        
        JTable tableauDocuments = new JTable();
    }
}
