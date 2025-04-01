package Bibliothèque;

import java.time.LocalDate;

public class Emprunt {
    private String adherent;
    private Document document;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevu;

    public Emprunt(String matricule, Document document, LocalDate dateEmprunt, LocalDate dateRetourPrevu) {
        this.adherent = matricule;
        this.document = document;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevu = dateRetourPrevu;
    }

    public String getAdherent() { return adherent; }
    public Document getDocument() { return document; }
    public LocalDate getDateEmprunt() { return dateEmprunt; }
    public LocalDate getDateRetourPrevu() { return dateRetourPrevu; }

	public Object getMatricule() {
		// TODO Auto-generated method stub
		return getMatricule();
	}
}
