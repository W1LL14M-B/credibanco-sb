package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import modelo.Transaccion;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findByTarjetaId(Long tarjetaId);
    List<Transaccion> findByEstado(Transaccion.EstadoTransaccion estado);
    List<Transaccion> findByTipo(Transaccion.TipoTransaccion tipo);
    // Puedes agregar otros métodos si necesitas más consultas personalizadas.
}

