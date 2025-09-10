package tp.sbapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import tp.sbapp.entity.ProductEntity;

public interface ProductDao extends JpaRepository<ProductEntity, Long> {

}
