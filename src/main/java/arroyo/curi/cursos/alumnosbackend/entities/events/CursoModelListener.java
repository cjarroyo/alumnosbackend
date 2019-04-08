package arroyo.curi.cursos.alumnosbackend.entities.events;

import arroyo.curi.cursos.alumnosbackend.entities.Curso;
import arroyo.curi.cursos.alumnosbackend.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class CursoModelListener extends AbstractMongoEventListener<Curso> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public CursoModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Curso> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(sequenceGenerator.generateSequence(Curso.SEQUENCE_NAME));
        }
    }
}
