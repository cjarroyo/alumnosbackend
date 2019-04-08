package arroyo.curi.cursos.alumnosbackend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Document(collection = "alumnos")
@TypeAlias("alumno")
public class Alumno {
    @Transient
    public static final String SEQUENCE_NAME = "alumnos_sequence";

    @Id
    private Long id;
    private String nombres;
    private String apellidos;
    private String telefono;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fechaNacimiento;
    private String email;
    private String direccion;
    @DBRef
    private List<Curso> cursos;
}
