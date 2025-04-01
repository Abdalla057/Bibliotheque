package Bibliothèque;



import java.util.Arrays;

public class Main {
    private static Object  email;

	public static void main(String[] args) {
        BaseDeDonnees base = new BaseDeDonnees();

        // Création d'adhérents
        Adherent alice = new Adherent("20", "Ndiaye", 200, email );
        base.enregistrerAdherent(alice);

        // Création de documents
        Livre livre = new Livre("L’enfant Noir", "Camara Laye", 100, 0);
        Revue revue = new Revue("La revue Informatique", "Microsoft", "Décembre-2021");
        Article article = new Article("Ubuntu", "Les Linuxiens", "Janvier-2023");

        // Enregistrement des documents
        base.enregistrerLivre(livre);
        base.enregistrerRevue(revue);
        base.enregistrerArticle(article);

        // Alice emprunte un livre et une revue
        alice.emprunter(livre);
        alice.emprunter(revue);


        // Alice retourne un document en retard
        base.enregistrerTaxe("Retard", 1200, alice, false);

        // Alice paie sa taxe
        base.enregistrerTaxe("Retard", 1200, alice, true);
    }
}
