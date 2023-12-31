package be.vinci.wishlists;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@org.springframework.stereotype.Controller
public class Controller {

  public final WishlistService service;

  public Controller(WishlistService service) {
    this.service = service;
  }

  @GetMapping("/wishlists/user/{pseudo}")
  public ResponseEntity<List<AmzoneProduct>> getOne(@PathVariable String pseudo) {
    ArrayList<AmzoneProduct> amzoneProducts = service.getWishlist(pseudo);

    if (!amzoneProducts.isEmpty()) {
      return new ResponseEntity<>(amzoneProducts, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/wishlists/{pseudo}/{productId}")
  public ResponseEntity<AmzoneProduct> deleteOne(@PathVariable String pseudo, @PathVariable int productId) {

    boolean rep = service.deleteProduct(pseudo, productId);

    if (rep) {
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/wishlists/user/{pseudo}")
  public ResponseEntity<Wishlist> deleteAll(@PathVariable String pseudo) {

    boolean rep = service.deleteWishlist(pseudo);

    if (rep) {
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/wishlists/product/{productId}")
  public ResponseEntity<Wishlist> deleteAllProduct(@PathVariable int productId) {

    boolean rep = service.deleteProducts(productId);

    if (rep) {
      return new ResponseEntity<>(HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("/wishlists/{pseudo}/{productId}")
  public ResponseEntity<Wishlist> putProduct(@PathVariable String pseudo, @PathVariable int productId) {

    Wishlist rep = service.putProductWishlist(pseudo, productId);

    return new ResponseEntity<>(rep, HttpStatus.OK);
  }
}
