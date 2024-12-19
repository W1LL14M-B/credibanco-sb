package controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import modelo.Transaccion;
import service.TransaccionService;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {
    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/compra/{id}")
    public ResponseEntity<Transaccion> realizarCompra(@PathVariable Long id, @RequestBody BigDecimal monto) {
        return ResponseEntity.ok(transaccionService.realizarCompra(id, monto));
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<Transaccion>> listarTransacciones(@PathVariable Long id) {
        return ResponseEntity.ok(transaccionService.listarTransacciones(id));
    }

    @PostMapping("/anular/{id}")
    public ResponseEntity<Transaccion> anularTransaccion(@PathVariable Long id) {
        return ResponseEntity.ok(transaccionService.anularTransaccion(id));
    }
}
