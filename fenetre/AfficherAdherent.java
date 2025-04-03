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

public class AfficherAdherent extends JFrame {
    public AfficherAdherent(){

        // Lecture de livres.txt pour afficher l'ensemble des livre disponible=========================

        ArrayList<ArrayList<Object>> donneAdherent = new ArrayList<>();
        try{
            BufferedReader lectureDesLivres = new BufferedReader(new FileReader("baseDeDonnee/adherent.txt"));
            String ligne;
            while ((ligne = lectureDesLivres.readLine())!=null) {
                String[] Tableaumot = ligne.split("&&");
                donneAdherent.add(new ArrayList<>(Arrays.asList(Tableaumot[0], Tableaumot[1],Tableaumot[2], Tableaumot[3])));
            }
            Object[][] tableauAdherent =new  Object[donneAdherent.size()][];
            for(int i = 0; i< donneAdherent.size(); i++){
                tableauAdherent[i] = donneAdherent.get(i).toArray();
            }
            String[] enteteDocuments = {"Matricule", "Nom", "Prenom","Email"};
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

        
    }
}
