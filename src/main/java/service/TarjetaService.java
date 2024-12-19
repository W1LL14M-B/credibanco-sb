package service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import modelo.Tarjeta;
import modelo.Tarjeta.TipoTarjeta;
import repository.TarjetaRepository;

@Service
public class TarjetaService {
    @Autowired
    private TarjetaRepository tarjetaRepository;

    public Tarjeta crearTarjeta(String nombre, TipoTarjeta tipoTarjeta) {
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setNombre(nombre);
        tarjeta.setTipoTarjeta(tipoTarjeta);
        tarjeta.setFechaVencimiento(LocalDate.now().plusYears(3)); // Fecha de vencimiento 3 años
        return tarjetaRepository.save(tarjeta);
    }

    public Tarjeta recargarSaldo(Long tarjetaId, BigDecimal monto) {
        Tarjeta tarjeta = tarjetaRepository.findById(tarjetaId).orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));
        tarjeta.setSaldo(tarjeta.getSaldo().add(monto));
        return tarjetaRepository.save(tarjeta);
    }
}

