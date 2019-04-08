package arroyo.curi.cursos.alumnosbackend.web;

import arroyo.curi.cursos.alumnosbackend.entities.Curso;
import arroyo.curi.cursos.alumnosbackend.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api//curso")
public class CursoRest {
    private CursoService service;

    @Autowired
    public CursoRest(final CursoService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public List<Curso> obtenerCursos() {
        return service.listar();
    }

    @GetMapping(value = "/active")
    public List<Curso> obtenerActivos() {
        return service.listarActivos();
    }

    @GetMapping(value = "/{id}")
    public Curso obtenerCursoPorId(@PathVariable("id") Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping(value = "/{id}")
    public Curso moddificar(@PathVariable("id") Long id, @Valid @RequestBody Curso curso) {
        curso.setId(id);
        return service.editar(curso);
    }

    @PostMapping(value = "/")
    public Curso crear(@Valid @RequestBody Curso curso) {
        return service.crear(curso);
    }

    @DeleteMapping(value = "/{id}")
    public void desactivar(@PathVariable Long id) {
        service.desactivar(id);
    }
}
