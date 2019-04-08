package arroyo.curi.cursos.alumnosbackend.web;

import arroyo.curi.cursos.alumnosbackend.entities.Alumno;
import arroyo.curi.cursos.alumnosbackend.services.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/alumno")
public class AlumnoRest {

    private AlumnoService service;

    @Autowired
    public AlumnoRest(final AlumnoService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public List<Alumno> obtenerAlumnos() {
        return service.listar();
    }

    @GetMapping(value = "/curso/{id}")
    public List<Alumno> obtenerAlumnosPorCursoId(@PathVariable("id") Long id) {
        return service.listarPorCursoId(id);
    }

    @GetMapping(value = "/{id}")
    public Alumno obtenerAlumnoPorId(@PathVariable("id") Long id) {
        return service.obtenerPorId(id);
    }

    @PutMapping(value = "/{id}")
    public Alumno moddificar(@PathVariable("id") Long id, @Valid @RequestBody Alumno alumno) {
        alumno.setId(id);
        return service.editar(alumno);
    }

    @PostMapping(value = "/")
    public Alumno crear(@Valid @RequestBody Alumno alumno) {
        return service.crear(alumno);
    }

    @PostMapping(value = "/{id}/curso/{cursoId}")
    public void agregarCurso(@PathVariable Long id, @PathVariable Long cursoId) {
        service.agregarCurso(id, cursoId);
    }

    @DeleteMapping(value = "/{id}/curso/{cursoId}")
    public void eliminarCurso(@PathVariable Long id, @PathVariable Long cursoId) {
        service.eliminarCurso(id, cursoId);
    }

    @DeleteMapping(value = "/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
