package be.vinci.productservice;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    public final AmzoneService service;

    public Controller(final AmzoneService service) {
        this.service = service;
    }

    @PostMapping("/produits")
    public ResponseEntity<AmzoneProduct> create(@RequestBody AmzoneProduct produit) {
        if (service.getOne(produit.getId()) != null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        service.create(produit);
        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }

    @GetMapping("/produits/{id}")
    public ResponseEntity<AmzoneProduct> getOne(@PathVariable int id) {
        AmzoneProduct produit = service.getOne(id);

        if (produit != null) {
            return new ResponseEntity<>(produit, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/produits")
    public Iterable<AmzoneProduct> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/produits/{id}")
    public ResponseEntity<AmzoneProduct> deleteOne(@PathVariable int id) {

        boolean rep = service.deleteOne(id);

        if (rep) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/produits")
    public ResponseEntity<AmzoneProduct> deleteAll() {
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/produits")
    public ResponseEntity<AmzoneProduct> putProduct(@RequestBody AmzoneProduct produit) {

        boolean rep = service.putProduct(produit);

        if (rep) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
