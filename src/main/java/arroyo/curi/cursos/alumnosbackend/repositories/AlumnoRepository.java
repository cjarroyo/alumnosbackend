package arroyo.curi.cursos.alumnosbackend.repositories;

import arroyo.curi.cursos.alumnosbackend.entities.Alumno;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AlumnoRepository extends MongoRepository<Alumno, Long> {

    @Query(value = "{ 'cursos.$id' : ?0 }")
    List<Alumno> findByCursoId(Long id);

}
