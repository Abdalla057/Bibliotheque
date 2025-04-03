package fenetre;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import Classes.Adherent;

public class AfficherTaxe extends JFrame {
    public AfficherTaxe(){

        // Lecture de livres.txt pour afficher l'ensemble des livre disponible=========================
        ArrayList<ArrayList<Object>> donneAdherent = new ArrayList<>();
        try{
            BufferedReader lectureDesLivres = new BufferedReader(new FileReader("baseDeDonnee/taxe.txt"));
            String ligne;
            while ((ligne = lectureDesLivres.readLine())!=null) {
                String[] Tableaumot = ligne.split("&&");
                donneAdherent.add(new ArrayList<>(Arrays.asList(Tableaumot[0], Tableaumot[1],Tableaumot[2],Tableaumot[3])));
            }
            Object[][] tableauAdherent =new  Object[donneAdherent.size()][];
            for(int i = 0; i< donneAdherent.size(); i++){
                tableauAdherent[i] = donneAdherent.get(i).toArray();
            }
            String[] enteteDocuments = {"nature Taxe", "Montat", "Matricule adherent", "Status Taxe"};
            JTable tableauDocuments = new JTable(tableauAdherent, enteteDocuments);

            setLayout(new BorderLayout());
            add(tableauDocuments.getTableHeader(), BorderLayout.NORTH);
            add(tableauDocuments, BorderLayout.CENTER);
            JButton payerTaxe = new JButton("Payer la taxe");
            add(payerTaxe, BorderLayout.SOUTH);
            tableauDocuments.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e){
                    int ligneSelectionnee = tableauDocuments.getSelectedRow();
                    String Matricule = tableauDocuments.getValueAt(ligneSelectionnee, 0).toString();
                    String statusTaxe = tableauDocuments.getValueAt(ligneSelectionnee, 3).toString();
                    payerTaxe.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e){
                        try{
                            BufferedReader litTaxe = new  BufferedReader(new FileReader("baseDeDonnee/taxe.txt"));
                            if(statusTaxe.equals(   "payée"))
                            JOptionPane.showMessageDialog(null, "La taxe est deja payée");
                            else{
                                Adherent.payerTaxe(statusTaxe, "ligne", Matricule);
                            }
                        }
                        catch(IOException erre){
                            JOptionPane.showMessageDialog(null , erre.getMessage());
                        }
                    }
                    });
                    
                   
                        
                }   
            });
            
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
