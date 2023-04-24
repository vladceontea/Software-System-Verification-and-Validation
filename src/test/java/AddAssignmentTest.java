import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;import domain.Nota;

import domain.Student;
import domain.Tema;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.*;

public class AddAssignmentTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    private static final String INVALID_ID_ERROR_MESSAGE = "ID invalid! \n";
    private static final String INVALID_DESCRIPTION_ERROR_MESSAGE = "Descriere invalida! \n";
    private static final String INVALID_DEADLINE_ERROR_MESSAGE = "Deadline invalid! \n";
    private static final String INVALID_STARTLINE_ERROR_MESSAGE = "Data de primire invalida! \n";

    @Test
    public void tc_1_id_allTrue(){
        assertDoesNotThrow(() -> service.saveTema("4", "tema_4", 7, 5));
    }

    @Test
    public void tc_2_id_null(){
        assertThrows(ValidationException.class, () -> this.service.saveTema(null, "a", 1, 1), INVALID_ID_ERROR_MESSAGE);
    }

    @Test
    public void tc_3_id_empty(){
        assertThrows(ValidationException.class, () -> this.service.saveTema("", "a", 1, 1), INVALID_ID_ERROR_MESSAGE);
    }

    @Test
    public void tc_4_description_null(){
        assertThrows(ValidationException.class, () -> this.service.saveTema("5", null, 1, 1), INVALID_DESCRIPTION_ERROR_MESSAGE);
    }

    @Test
    public void tc_5_description_empty(){
        assertThrows(ValidationException.class, () -> this.service.saveTema("6", "", 1, 1), INVALID_DESCRIPTION_ERROR_MESSAGE);
    }

    @Test
    public void tc_6_deadline_UnderLowerBound(){
        assertThrows(ValidationException.class, () -> this.service.saveTema("7", "a", 0, 1), INVALID_DEADLINE_ERROR_MESSAGE);
    }

    @Test
    public void tc_7_deadline_AboveUpperBound(){
        assertThrows(ValidationException.class, () -> this.service.saveTema("8", "a", 15, 1), INVALID_DEADLINE_ERROR_MESSAGE);
    }

    @Test
    public void tc_8_deadline_startline_reversed(){
        assertThrows(ValidationException.class, () -> this.service.saveTema("9", "a", 6, 10), INVALID_DEADLINE_ERROR_MESSAGE);
    }

    @Test
    public void tc_9_startline_UnderLowerBound(){
        assertThrows(ValidationException.class, () -> this.service.saveTema("10", "a", 1, 0), INVALID_STARTLINE_ERROR_MESSAGE);
    }
}
