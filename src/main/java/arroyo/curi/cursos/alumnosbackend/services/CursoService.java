package arroyo.curi.cursos.alumnosbackend.services;

import arroyo.curi.cursos.alumnosbackend.entities.Curso;

import java.util.List;

public interface CursoService {
    List<Curso> listar();

    Curso obtenerPorId(Long id);

    Curso crear(Curso curso);

    Curso editar(Curso curso);

    void desactivar(Long id);

    List<Curso> listarActivos();
}
