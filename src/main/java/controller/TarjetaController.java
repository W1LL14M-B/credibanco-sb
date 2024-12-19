package controller;

import java.math.BigDecimal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modelo.Tarjeta;

import service.TarjetaService;


@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {
    @Autowired
    private TarjetaService tarjetaService;

    @PostMapping
    public ResponseEntity<Tarjeta> crearTarjeta(@RequestBody Tarjeta tarjeta) {
        return ResponseEntity.ok(tarjetaService.crearTarjeta(tarjeta.getNombre(), tarjeta.getTipoTarjeta()));
    }

    @PostMapping("/recargar/{id}")
    public ResponseEntity<Tarjeta> recargarSaldo(@PathVariable Long id, @RequestBody BigDecimal monto) {
        return ResponseEntity.ok(tarjetaService.recargarSaldo(id, monto));
    }
}


