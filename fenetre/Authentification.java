package fenetre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Authentification extends JFrame {
    public Authentification(){
        JPanel panel  =new JPanel();

        ImageIcon image =new ImageIcon("images/Auth.png");
        Image imagered = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel etiquetImage = new JLabel(new ImageIcon(imagered));
        

        panel.setLayout(new GridBagLayout());
        JPanel autJPanel = new JPanel();
        autJPanel.setPreferredSize(new Dimension(500, 400));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2, true);
        autJPanel.setBorder(border);
        autJPanel.add(etiquetImage);
        autJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel text = new JLabel("Connexion");
        text.setFont(new Font("Poppins", Font.BOLD, 25));
        
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 0, 0);
        JLabel identifiants = new JLabel("Identifiants");
        autJPanel.add(identifiants, gbc);
        JTextField identifiant = new JTextField();
        identifiant.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        autJPanel.add(identifiant, gbc);
        JLabel motPass = new JLabel("Mots de pass");
        gbc.gridx = 0;
        gbc.gridy = 2;
        autJPanel.add(motPass, gbc);
        JTextField password = new JTextField();
        password.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        autJPanel.add(password, gbc);
        JButton connexion = new JButton("Se connecter");
        connexion.setBackground(Color.BLUE);
        connexion.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        autJPanel.add(connexion, gbc);
        connexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e ){
                String id = identifiant.getText();
                String pas = password.getText();
                if(id.equals("Abdallah") && pas.equals("Abdallahpass")){
                    dispose();
                    new FenetrePrincipale();
                }
                else
                    JOptionPane.showMessageDialog(null, "Identifiant incorrect", "connexion", 0);
            }
        });
        gbc.anchor = new GridBagConstraints().CENTER;
        gbc.gridy=0;
        autJPanel.add(text, gbc);
        panel.add(autJPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setSize(1000,500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
