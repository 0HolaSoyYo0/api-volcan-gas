package cl.duoc.api_volcan_gas.repository;


import cl.duoc.api_volcan_gas.entity.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRespository extends JpaRepository<Producto, Long> {
}
