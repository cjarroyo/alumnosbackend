package arroyo.curi.cursos.alumnosbackend.services;

import arroyo.curi.cursos.alumnosbackend.entities.Alumno;

import java.util.List;

public interface AlumnoService {
    List<Alumno> listar();

    Alumno obtenerPorId(Long id);

    Alumno crear(Alumno alumno);

    Alumno editar(Alumno alumno);

    void eliminar(Long id);

    List<Alumno> listarPorCursoId(Long cursoId);

    void eliminarCurso(Long alumnoId, Long cursoId);

    void agregarCurso(Long alumnoId, Long cursoId);
}
