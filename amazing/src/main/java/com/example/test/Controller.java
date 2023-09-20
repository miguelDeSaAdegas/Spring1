package com.example.test;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    private static final List<AmzoneProduct> produits = new ArrayList<>();

    static {
        produits.add(new AmzoneProduct(1,"françois","beau",0));
        produits.add(new AmzoneProduct(2,"damien","jesus",10));
        produits.add(new AmzoneProduct(3,"corentin","test?",-5));
        produits.add(new AmzoneProduct(4,"alex","echec",5));
    }

    @PostMapping("/produits")
    public ResponseEntity<AmzoneProduct> create(@RequestBody AmzoneProduct produit) {
        if (produits.contains(produit)) return new ResponseEntity<>(HttpStatus.CONFLICT);
        produits.add(produit);
        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }

    @GetMapping("/produits/{id}")
    public ResponseEntity<AmzoneProduct> getOne(@PathVariable int id) {
        for (AmzoneProduct produit : produits) {
            if (produit.getId() == id) {
                return new ResponseEntity<>(produit, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/produits")
    public Iterable <AmzoneProduct> getAll() {
        return produits;
    }

    @DeleteMapping("/produits/{id}")
    public ResponseEntity<AmzoneProduct> deleteOne(@PathVariable int id) {
        for (AmzoneProduct produit : produits) {
            if (produit.getId() == id) {
                produits.remove(produit);
                return new ResponseEntity<>(produit, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/produits")
    public ResponseEntity<AmzoneProduct> deleteAll() {
        produits.clear();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/produits")
    public ResponseEntity<AmzoneProduct> patchProduct(@RequestBody AmzoneProduct produit) {
        for (AmzoneProduct amzoneProduct : produits) {
            if (amzoneProduct.getId() == produit.getId()) {
                amzoneProduct.setName(produit.getName());
                amzoneProduct.setCategory(produit.getCategory());
                amzoneProduct.setPrice(produit.getPrice());
                return  new ResponseEntity<>(amzoneProduct, HttpStatus.OK);
            }
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
