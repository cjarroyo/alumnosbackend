package arroyo.curi.cursos.alumnosbackend.repositories;

import arroyo.curi.cursos.alumnosbackend.entities.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CursoRepository extends MongoRepository<Curso, Long> {
    List<Curso> findByActiveIsTrue();
}
