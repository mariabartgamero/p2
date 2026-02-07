package edu.comillas.icai.gitt.pat.spring.p2;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ModeloCarrito(
        @NotBlank(message="Debe rellenar el id carrito")
        String idCarrito,

        @NotBlank(message="Debe rellenar el id artículo")
        String idArticulo,

        @NotBlank(message="Debe rellenar la descripción")
        String descripcion,

        @NotNull(message="El valor no puede ser nulo")
        @Min(value = 1, message = "Las unidades deben ser al menos 1")
        Integer unidades,

        @NotNull(message = "El precio final no puede ser nulo")
        @DecimalMin(value = "0.0", inclusive = true)
        double precioFinal)



{}









