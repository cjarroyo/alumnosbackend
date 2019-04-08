package arroyo.curi.cursos.alumnosbackend.services.impl;

import arroyo.curi.cursos.alumnosbackend.entities.Alumno;
import arroyo.curi.cursos.alumnosbackend.entities.Curso;
import arroyo.curi.cursos.alumnosbackend.repositories.AlumnoRepository;
import arroyo.curi.cursos.alumnosbackend.repositories.CursoRepository;
import arroyo.curi.cursos.alumnosbackend.services.AlumnoService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private AlumnoRepository alumnoRepository;
    private CursoRepository cursoRepository;

    @Autowired
    public AlumnoServiceImpl(final AlumnoRepository alumnoRepository, CursoRepository cursoRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Alumno> listar() {
        return alumnoRepository.findAll();
    }

    @Override
    public Alumno obtenerPorId(Long id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    @Override
    public Alumno crear(Alumno alumno) {
        alumno.setId(null);
        return editarAlumno(alumno);
    }

    @Override
    public Alumno editar(Alumno alumno) {
        return editarAlumno(alumno);
    }

    @Override
    public void eliminar(Long id) {
        Alumno alumno = obtenerPorId(id);
        if (alumno != null) {
            alumnoRepository.delete(alumno);
        }
    }

    @Override
    public List<Alumno> listarPorCursoId(Long cursoId) {
        return alumnoRepository.findByCursoId(cursoId);
    }

    @Override
    public void eliminarCurso(Long alumnoId, Long cursoId) {
        modificarListadoCursos(alumnoId, cursoId, false);
    }

    @Override
    public void agregarCurso(Long alumnoId, Long cursoId) {
        modificarListadoCursos(alumnoId, cursoId, true);
    }

    private void modificarListadoCursos(Long alumnoId, Long cursoId, boolean agregar) {
        Alumno alumno = obtenerPorId(alumnoId);
        Curso curso = cursoRepository.findById(cursoId).orElse(null);
        if (alumno != null && curso != null) {
            alumno.setCursos(agregar ? agregarCursoLista(alumno.getCursos(), curso) : quitarCursoLista(alumno.getCursos(), curso));
            alumnoRepository.save(alumno);
        }
    }

    private List<Curso> quitarCursoLista(final List<Curso> cursos, final Curso curso) {
        if (cursos != null) {
            cursos.removeIf(objCurso -> objCurso.getId().equals(curso.getId()));
            return cursos;
        }
        return null;
    }

    private List<Curso> agregarCursoLista(final List<Curso> cursos, final Curso curso) {
        List<Curso> cursosAdd = quitarCursoLista(cursos, curso);
        if (cursosAdd == null) {
            cursosAdd = new ArrayList<>();
        }
        cursosAdd.add(curso);
        return cursosAdd;
    }


    private Alumno editarAlumno(final Alumno alumno) {
        alumno.setCursos(getCursos(getIds(alumno.getCursos())));
        return alumnoRepository.save(alumno);
    }

    private List<Curso> getCursos(final List<Long> cursoIds) {
        if (cursoIds != null && cursoIds.size() > 0) {
            return Lists.newArrayList(cursoRepository.findAllById(cursoIds));
        }
        return null;
    }

    private List<Long> getIds(final List<Curso> cursos) {
        if (cursos != null && cursos.size() > 0) {
            return cursos.stream().map(Curso::getId).collect(Collectors.toList());
        }
        return null;
    }
}