package com.autourducode.autourducode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produit")
@AllArgsConstructor
public class ProduitController {
        private final ProduitService produitService;
        @PostMapping("/create")
        public Produit create(@RequestBody Produit poProduit)
        {
            return produitService.creer(produit);
        }


        @GetMapping
    public List<Produit> read(){
            return produitService.lire();
        }



        @PutMapping("/update/{id}")
    pubic Produit update(@PathVariable Long id, Produit produit)
    {
        return produitService.modifier(id, produit);
    }
}
