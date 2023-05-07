import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IntegrationTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    private static final String INVALID_NAME_ERROR_MESSAGE = "Nume invalid! \n";

    @Test
    public void tc_1_emptyStudentName(){
        assertThrows(ValidationException.class, () -> service.saveStudent("7", "", 933), INVALID_NAME_ERROR_MESSAGE);
    }

    @Test
    public void tc_2_validTema(){
        assertEquals(0, service.saveTema("4", "tema_4", 7, 5));
    }

    @Test
    public void tc_3_invalidStudent(){
        assertEquals(-1, service.saveNota("99", "1", 8.5, 7, "good"));
    }

    @Test
    public void tc_4_call() {
        assertEquals(0, service.saveStudent("11", "Vasile", 111));
        assertEquals(0, service.saveTema("5", "tema_5", 7, 5));
        assertEquals(0, service.saveNota("11", "5", 7.5, 7, "excellent"));
    }
}
