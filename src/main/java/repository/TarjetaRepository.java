package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modelo.Tarjeta;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {
    // Puedes agregar métodos personalizados si lo necesitas, por ejemplo, buscar por tipo de tarjeta.
    Optional<Tarjeta> findByNombre(String nombre);
}