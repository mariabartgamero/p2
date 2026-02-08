package edu.comillas.icai.gitt.pat.spring.p2;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ControladorRest {
    private final Map<String, ModeloCarrito> carritos = new HashMap<>(); //genero un mapa que guarda cada carrito creado


    //crear
    @PostMapping("/api/carritos")
    @ResponseStatus(HttpStatus.CREATED) //200 OK: se crea el carrito
    public ModeloCarrito crea(@Valid @RequestBody ModeloCarrito carritoNuevo, BindingResult bindingResult) {

        //extra: para errores de validación
        if (bindingResult.hasErrors()) {
            throw new ExcepcionCarritoIncorrecto(bindingResult); //400
        }

        //para evitar que haya dos carritos con el mismo id
        if (carritos.get(carritoNuevo.idCarrito())!=null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El idCarrito ya existe"); //409
        }
        carritos.put(carritoNuevo.idCarrito(), carritoNuevo);
        return carritoNuevo; //me devuelve la info que le acabo de meter del carrito nuevo
    }

    //mostrar información
    @GetMapping("/api/carritos/{idCarrito}")
    public ModeloCarrito lee(@PathVariable String idCarrito) {
        ModeloCarrito carrito = carritos.get(idCarrito);
        if (carrito == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe tal carrito"); //404
        }
        return carrito; //200 OK
    }

    //actualizar información
    @PutMapping("/api/carritos/{idCarrito}")
    public ModeloCarrito actualiza(@PathVariable String idCarrito,
                                   @Valid @RequestBody ModeloCarrito carritoNuevo, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ExcepcionCarritoIncorrecto(bindingResult); //400
        }

        // Si no existe el carrito
        if (carritos.get(idCarrito) == null) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "No existe el carrito a actualizar"); //404
        }
        if (!idCarrito.equals(carritoNuevo.idCarrito())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El id Carrito nuevo no coincide con el de la URL");

        }

        //ModeloCarrito carritoActual = carritos.get(idCarrito);
        ModeloCarrito carritoActualizado = new ModeloCarrito(
                idCarrito,
                carritoNuevo.idArticulo(),
                carritoNuevo.descripcion(),
                carritoNuevo.unidades(),
                carritoNuevo.precioFinal()
        );

        //Sustituir carrito antiguo
        carritos.put(idCarrito, carritoActualizado);

        return carritoActualizado; //200 OK
    }

    //borrar carrito
    @DeleteMapping("/api/carritos/{idCarrito}")
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    public void borrar(@PathVariable String idCarrito) {
        if (carritos.get(idCarrito) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe el carrito a borrar"); //404
        }
        carritos.remove(idCarrito);
    }


    @ExceptionHandler(ExcepcionCarritoIncorrecto.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ModeloCampoIncorrecto> carritoIncorrecto(ExcepcionCarritoIncorrecto ex) {
        return ex.getErrores().stream().map(error -> new ModeloCampoIncorrecto(
                error.getDefaultMessage(), error.getField(), error.getRejectedValue()
        )).toList();
    }
}
