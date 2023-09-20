package com.example.test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmzoneRepository extends CrudRepository<AmzoneProduct, Integer> {

}
