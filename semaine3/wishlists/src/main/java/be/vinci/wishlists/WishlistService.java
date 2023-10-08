package be.vinci.wishlists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

  public final WishlistRepository repository;

  public WishlistService(WishlistRepository repository) {
    this.repository = repository;
  }

  public ArrayList<Wishlist> getOne(String pseudo) {
    ArrayList<Wishlist> wishlistList = new ArrayList<>();
    for (Wishlist wishlist : repository.findAll()) {
      if (wishlist.getPseudo().equals(pseudo)) {
        wishlistList.add(wishlist);
      }
    }
    return wishlistList;
  }

  public Boolean deleteProduct(String pseudo, int productId) {
    for (Wishlist wishlist : repository.findAll()) {
      if (wishlist.getPseudo().equals(pseudo) && wishlist.getProductId() == productId) {
        repository.delete(wishlist);
        return true;
      }
    }
    return false;
  }

  public Boolean deleteWishlist(String pseudo) {
    for (Wishlist wishlist : repository.findAll()) {
      if (wishlist.getPseudo().equals(pseudo)) {
        repository.delete(wishlist);
      }
    }
    return true;
  }

  public Boolean deleteProducts(int productId) {
    for (Wishlist wishlist : repository.findAll()) {
      if (wishlist.getProductId() == productId) {
        repository.delete(wishlist);
      }
    }
    return true;
  }

  public Wishlist putProductWishlist(String pseudo, int productId) {
    Wishlist wishlist = new Wishlist();
    wishlist.setPseudo(pseudo);
    wishlist.setProductId(productId);
    repository.save(wishlist);
    return wishlist;
  }
}
