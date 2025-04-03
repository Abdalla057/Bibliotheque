package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Adherent {
    
    public static int matricule = 0;

    public static void payerTaxe(String natureTaxe, String montantAPayer, String matricule) {
        // Lire le fichier taxe.txt
        StringBuilder contenuTaxe = new StringBuilder();
        boolean taxeTrouvee = false;
    
        try (BufferedReader lecteurTaxe = new BufferedReader(new FileReader("BaseDeDonnee/taxe.txt"))) {
            String ligne;
            while ((ligne = lecteurTaxe.readLine()) != null) {
                String[] attributs = ligne.split("&&");
                // Vérifier si la taxe correspond à celle à payer
                if (attributs[0].equalsIgnoreCase(natureTaxe) && attributs[2].equals(matricule)) {
                    // Mettre à jour la situation à "payée"
                    attributs[3] = "payée";
                    taxeTrouvee = true;
                }
                contenuTaxe.append(String.join("&&", attributs)).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de la lecture du fichier : " + e.getMessage());
            return;
        }
    
        // Si la taxe a été trouvée, l'écrire dans le fichier taxe.txt
        if (taxeTrouvee) {
            try (BufferedWriter ecrireTaxe = new BufferedWriter(new FileWriter("BaseDeDonnee/taxe.txt"))) {
                ecrireTaxe.write(contenuTaxe.toString());
                JOptionPane.showMessageDialog(null, "La taxe a été mise à jour avec succès.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors de l'écriture dans le fichier : " + e.getMessage());
            }
        }else {
            JOptionPane.showMessageDialog(null, "La taxe est deja régler");
        }
    }
    public static Boolean valid_nom(String nomAuteur){
        String nomRegex = "^[A-ZÀ-Ÿ][a-zà-ÿ]+(?: [A-ZÀ-Ÿ][a-zà-ÿ]+)*$";
        Pattern emPattern = Pattern.compile(nomRegex);
        Matcher matcher = emPattern.matcher(nomAuteur);
        return matcher.matches();
    }

    public static boolean mailValid(String  email){
        String emailRegex = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$";
        Pattern emPattern = Pattern.compile(emailRegex);
        Matcher matcher = emPattern.matcher(email);
        return matcher.matches();
    }
}
