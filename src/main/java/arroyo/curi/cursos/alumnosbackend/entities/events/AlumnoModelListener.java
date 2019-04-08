package arroyo.curi.cursos.alumnosbackend.entities.events;

import arroyo.curi.cursos.alumnosbackend.entities.Alumno;
import arroyo.curi.cursos.alumnosbackend.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class AlumnoModelListener extends AbstractMongoEventListener<Alumno> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public AlumnoModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Alumno> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(sequenceGenerator.generateSequence(Alumno.SEQUENCE_NAME));
        }
    }
}
