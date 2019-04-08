package arroyo.curi.cursos.alumnosbackend.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "cursos")
@TypeAlias("curso")
public class Curso {
    @Transient
    public static final String SEQUENCE_NAME = "cursos_sequence";
    @Id
    private Long id;
    private String codigo;
    private String descripcion;
    private Integer anio;
    private boolean active;
}
