package be.vinci.wishlists;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "products")
public interface ProductProxy {

  @GetMapping("/produits/{id}")
  AmzoneProduct getProduct(@PathVariable int id);
}
