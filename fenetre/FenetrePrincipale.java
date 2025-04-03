package fenetre;

import javax.swing.JFrame;

import contenu.ContenueFPrincipale;

public class FenetrePrincipale extends JFrame {
    
    public FenetrePrincipale(){

        setContentPane(new ContenueFPrincipale());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dkbiblio");
        setVisible(true);
    }
}
