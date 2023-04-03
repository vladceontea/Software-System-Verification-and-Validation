
import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import static org.junit.Assert.*;

import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class AddAssignmentTest {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @Test
    public void tc_1_id_allTrue(){
        assertEquals(1, service.saveTema("4", "tema_4", 7, 5));
    }

    @Test
    public void tc_2_id_null(){
        assertEquals(1, service.saveTema(null, "tema_4", 7, 5));
    }

    @Test
    public void tc_3_id_empty(){
        assertEquals(1, service.saveTema("", "tema_4", 7, 5));
    }
}
