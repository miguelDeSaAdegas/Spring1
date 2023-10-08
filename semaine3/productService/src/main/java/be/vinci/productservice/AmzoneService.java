package be.vinci.productservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class AmzoneService {
    public final AmzoneRepository repo;

    public AmzoneService(final AmzoneRepository repo) {
        this.repo = repo;
    }


    public AmzoneProduct create( AmzoneProduct produit) {
        if (repo.existsById(produit.getId())) return null;
        repo.save(produit);
        return produit;
    }

    public AmzoneProduct getOne( int id) {
        for (AmzoneProduct produit : repo.findAll()) {
            if (produit.getId() == id) {
                return produit;
            }
        }
        return null;
    }

    public Iterable <AmzoneProduct> getAll() {
        return repo.findAll();
    }

    public boolean deleteOne( int id) {
        for (AmzoneProduct produit : repo.findAll()) {
            if (produit.getId() == id) {
                repo.delete(produit);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAll() {
        repo.deleteAll();
        return true;
    }

    public boolean putProduct(AmzoneProduct produit) {
        AmzoneProduct amzoneProduct = repo.findById(produit.getId()).orElse(null);

        if (amzoneProduct == null) {
            return false;
        }

        amzoneProduct.setName(produit.getName());
        amzoneProduct.setCategory(produit.getCategory());
        amzoneProduct.setPrice(produit.getPrice());
        repo.save(amzoneProduct);

        return  true;
    }

}
