package fenetre;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class AfficherEmpreint extends JFrame {
    public AfficherEmpreint(){

        // Lecture de livres.txt pour afficher l'ensemble des livre disponible=========================
        ArrayList<ArrayList<Object>> donneAdherent = new ArrayList<>();
        try{
            try (BufferedReader lectureDesLivres = new BufferedReader(new FileReader("baseDeDonnee/empreint.txt"))) {
                String ligne;
                while ((ligne = lectureDesLivres.readLine())!=null) {
                    String[] Tableaumot = ligne.split("&&");
                    donneAdherent.add(new ArrayList<>(Arrays.asList(Tableaumot[0], Tableaumot[1],Tableaumot[2])));
                }
            }
            Object[][] tableauAdherent =new  Object[donneAdherent.size()][];
            for(int i = 0; i< donneAdherent.size(); i++){
                tableauAdherent[i] = donneAdherent.get(i).toArray();
            }
            String[] enteteDocuments = {"Matricule adherent", "Titre", "type document"};
            JTable tableauDocuments = new JTable(tableauAdherent, enteteDocuments);
            
            tableauDocuments.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseExited(MouseEvent e) {
                    String matriculeEmpreint ;
                    int ligneSelectionnee = tableauDocuments.getSelectedRow();
                    matriculeEmpreint = tableauDocuments.getValueAt(ligneSelectionnee, 0).toString();
                    String titreLivreEmpreinter = tableauDocuments.getValueAt(ligneSelectionnee, 1).toString();
                    if (ligneSelectionnee >= -1) {
                        int choix1= JOptionPane.showConfirmDialog(null, "Voulez vous rendre ce document ?", "Rendre document", 0);
                        int choix = JOptionPane.showConfirmDialog(null, "Le document est il en bon etat ?", "Taxe", 0);
                
                    if(choix == JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(null, "Le livre est il rendu en retard", "taxe", 0);
                        try{
                            BufferedWriter ecris = new BufferedWriter(new FileWriter("BaseDeDonnee/taxe.txt", true));
                            ecris.write("Retard&&1200&&"+matriculeEmpreint+"&&non payée");
                            ecris.newLine();
                            ecris.close();
                        }
                        catch(IOException err){
                            JOptionPane.showMessageDialog(null, err.getMessage(), titreLivreEmpreinter, choix);
                        }
                    }
                    else{
                        try{
                            BufferedWriter ecris = new BufferedWriter(new FileWriter("BaseDeDonnee/taxe.txt", true));
                            ecris.write("Abimé&&5000&&"+matriculeEmpreint+"&&non payée");
                            ecris.newLine();
                            ecris.close();
                        }
                        catch(IOException err){
                            JOptionPane.showMessageDialog(null, err.getMessage(), titreLivreEmpreinter, choix);
                        }
                    }
                    }
                    
                }
            });
           

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
