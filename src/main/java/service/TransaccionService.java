package service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Tarjeta;
import modelo.Transaccion;
import repository.TarjetaRepository;
import repository.TransaccionRepository;

@Service
public class TransaccionService {
    @Autowired
    private TransaccionRepository transaccionRepository;
    @Autowired
    private TarjetaRepository tarjetaRepository;

    public Transaccion realizarCompra(Long tarjetaId, BigDecimal monto) {
        Tarjeta tarjeta = tarjetaRepository.findById(tarjetaId).orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
        
        if (tarjeta.getSaldo().compareTo(monto) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        Transaccion transaccion = new Transaccion();
        transaccion.setMonto(monto);
        transaccion.setEstado(Transaccion.EstadoTransaccion.EXITOSA);
        transaccion.setFechaCreacion(LocalDateTime.now());
        transaccion.setTipo(Transaccion.TipoTransaccion.COMPRA);
        transaccion.setTarjeta(tarjeta);
        transaccionRepository.save(transaccion);

        tarjeta.setSaldo(tarjeta.getSaldo().subtract(monto));
        tarjetaRepository.save(tarjeta);

        return transaccion;
    }

    public List<Transaccion> listarTransacciones(Long tarjetaId) {
        return transaccionRepository.findByTarjetaId(tarjetaId);
    }

    public Transaccion anularTransaccion(Long transaccionId) {
        Transaccion transaccion = transaccionRepository.findById(transaccionId).orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
        
        if (transaccion.getEstado() == Transaccion.EstadoTransaccion.ANULADA) {
            throw new RuntimeException("La transacción ya está anulada");
        }

        if (Duration.between(transaccion.getFechaCreacion(), LocalDateTime.now()).toHours() > 24) {
            throw new RuntimeException("No se puede anular una transacción después de 24 horas");
        }

        transaccion.setEstado(Transaccion.EstadoTransaccion.ANULADA);
        transaccionRepository.save(transaccion);

        Tarjeta tarjeta = transaccion.getTarjeta();
        tarjeta.setSaldo(tarjeta.getSaldo().add(transaccion.getMonto()));
        tarjetaRepository.save(tarjeta);

        return transaccion;
    }
}
