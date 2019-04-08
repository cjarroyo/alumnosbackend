package arroyo.curi.cursos.alumnosbackend.services.impl;

import arroyo.curi.cursos.alumnosbackend.entities.Curso;
import arroyo.curi.cursos.alumnosbackend.repositories.AlumnoRepository;
import arroyo.curi.cursos.alumnosbackend.repositories.CursoRepository;
import arroyo.curi.cursos.alumnosbackend.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {
    private CursoRepository cursoRepository;

    @Autowired
    public CursoServiceImpl(final AlumnoRepository alumnoRepository, CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    @Override
    public Curso crear(Curso curso) {
        curso.setId(null);
        return editarCurso(curso);
    }

    @Override
    public Curso editar(Curso curso) {
        return editarCurso(curso);
    }

    @Override
    public void desactivar(Long id) {
        Curso curso = obtenerPorId(id);
        if (curso != null) {
            curso.setActive(false);
            cursoRepository.save(curso);
        }

    }

    @Override
    public List<Curso> listarActivos() {
        return cursoRepository.findByActiveIsTrue();
    }

    private Curso editarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

}
