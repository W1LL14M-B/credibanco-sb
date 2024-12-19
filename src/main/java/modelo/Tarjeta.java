package modelo;


import java.math.BigDecimal;
import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Tarjeta {


 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    

    @NotNull
    @Size(min = 1, max = 50)
    private String nombre;

    @NotNull
    @Future
    private LocalDate fechaVencimiento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoTarjeta tipoTarjeta;

    @DecimalMin("0.0")
    private BigDecimal saldo = BigDecimal.ZERO; // Inicialmente saldo 0

    public enum TipoTarjeta {
        CREDITO, DEBITO
    }

    public Tarjeta() {}

    public Tarjeta(String nombre, TipoTarjeta tipoTarjeta) {
        this.nombre = nombre;
        this.tipoTarjeta = tipoTarjeta;
        this.fechaVencimiento = LocalDate.now().plusYears(3);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaCreacion) {
        this.fechaVencimiento = fechaCreacion.plusYears(3);
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }



}
