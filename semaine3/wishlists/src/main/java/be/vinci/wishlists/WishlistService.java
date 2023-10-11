package be.vinci.wishlists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    public final WishlistRepository repository;
    public final ProductProxy repoProduct;

    public WishlistService(WishlistRepository repository, ProductProxy productProxy) {
        this.repository = repository;
        this.repoProduct = productProxy;
    }

    public ArrayList<AmzoneProduct> getWishlist(String pseudo) {
        ArrayList<AmzoneProduct> arrayList = new ArrayList();
        for (Wishlist wishlist : repository.findByPseudo(pseudo))
            arrayList.add(repoProduct.getProduct(wishlist.getProductId()));

        return arrayList;
    }


    public Boolean deleteProduct(String pseudo, int productId) {
        if (repository.existsByPseudoAndProductId(pseudo, productId)) {
            repository.deleteByPseudoAndProductId(pseudo, productId);
            return true;
        }
        return false;
    }

    public Boolean deleteWishlist(String pseudo) {
        if (repository.existsByPseudo(pseudo)) {
            repository.deleteByPseudo(pseudo);
            return false;
        }
        return true;
    }

    public Boolean deleteProducts(int productId) {
        if (repository.existsByProductId(productId)) {
            repository.deleteByProductId(productId);
            return true;
        }
        return false;
    }

    public Wishlist putProductWishlist(String pseudo, int productId) {
        Wishlist wishlist = new Wishlist();
        wishlist.setPseudo(pseudo);
        wishlist.setProductId(productId);
        repository.save(wishlist);
        return wishlist;
    }
}
