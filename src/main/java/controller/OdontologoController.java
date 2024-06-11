package controller;


import entity.Odontologo;
import service.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService= new OdontologoService();
    }
    @PostMapping
    public Odontologo registrarUnOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }
    @GetMapping("/{id}")
    public Odontologo buscarOdontologoPorId(@PathVariable Integer id){
        return odontologoService.buscarPorId(id);
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

}
