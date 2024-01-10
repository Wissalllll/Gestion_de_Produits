package com.votre.nom;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {
 private List<Produit> produits;

 public ProduitService() {
     this.produits = new ArrayList<>();
 }

 public void ajouterProduit(Produit produit) {
     if (produitExiste(produit.getId()) || nomProduitExiste(produit.getNom())) {
         throw new IllegalArgumentException("Un produit avec le même ID ou nom existe déjà.");
     }

     if (produit.getPrix() < 0 || produit.getQuantite() < 0) {
         throw new IllegalArgumentException("Le prix et la quantité des produits doivent être positifs.");
     }

     produits.add(produit);
 }


 public Produit chercherProduitParId(Long id) {
     Produit produit = produits.stream()
             .filter(p -> p.getId().equals(id))
             .findFirst()
             .orElse(null);

     if (produit == null) {
         throw new IllegalArgumentException("Le produit avec l'ID " + id + " n'existe pas.");
     }

     return produit;
 }

 private boolean produitExiste(Long id) {
     return produits.stream().anyMatch(p -> p.getId().equals(id));
 }

 private boolean nomProduitExiste(String nom) {
     return produits.stream().anyMatch(p -> p.getNom().equals(nom));
 }
}
