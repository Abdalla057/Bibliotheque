package Bibliothèque;

import java.time.LocalDate;

//Classe pour gérer les amendes
class Amende {
 private Adherent adherent;
 private Emprunt emprunt;
 private double montant;
 private String raison;
 private boolean payee;
 private LocalDate dateAmende;
 private LocalDate datePaiement;
 
 public Amende(Adherent adherent, Emprunt emprunt, double montant, String raison) {
     this.adherent = adherent;
     this.emprunt = emprunt;
     this.montant = montant;
     this.raison = raison;
     this.payee = false;
     this.dateAmende = LocalDate.now();
 }
 
 // Getters et setters
 public Adherent getAdherent() { return adherent; }
 public void setAdherent(Adherent adherent) { this.adherent = adherent; }
 
 public Emprunt getEmprunt() { return emprunt; }
 public void setEmprunt(Emprunt emprunt) { this.emprunt = emprunt; }
 
 public double getMontant() { return montant; }
 public void setMontant(double montant) { this.montant = montant; }
 
 public String getRaison() { return raison; }
 public void setRaison(String raison) { this.raison = raison; }
 
 public boolean isPayee() { return payee; }
 public void setPayee(boolean payee) { this.payee = payee; }
 
 public LocalDate getDateAmende() { return dateAmende; }
 public void setDateAmende(LocalDate dateAmende) { this.dateAmende = dateAmende; }
 
 public LocalDate getDatePaiement() { return datePaiement; }
 public void setDatePaiement(LocalDate datePaiement) { 
     this.datePaiement = datePaiement;
     if (datePaiement != null) {
         this.payee = true;
     }
 }
 
 @Override
 public String toString() {
     return "Amende{" +
             "adherent=" + adherent.getNom() + " " + adherent.getNom() +
             ", document=" + (emprunt != null ? emprunt.getDocument().getTitre() : "N/A") +
             ", montant=" + montant +
             ", raison='" + raison + '\'' +
             ", payee=" + payee +
             ", dateAmende=" + dateAmende +
             ", datePaiement=" + datePaiement +
             '}';
 }
}

