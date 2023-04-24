import exception.AlreadyExistingEntityException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;import domain.Nota;

import domain.Student;
import domain.Tema;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.*;

public class AddGradeTest {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    private static final String INVALID_ID_STUDENT_ERROR_MESSAGE = "ID Student invalid! \n";
    private static final String INVALID_ID_TEMA_ERROR_MESSAGE = "ID Tema invalid!  \n";
    private static final String INVALID_NOTA_ERROR_MESSAGE = "Nota invalida! \n";
    private static final String INVALID_SDP_ERROR_MESSAGE = "Saptamana de predare invalida! \n";

    @Test
    public void tc_1_validGrade(){
        assertDoesNotThrow(() -> service.saveNota("2", "1", 8.5, 7, "good"));
    }

    @Test
    public void tc_2_invalidStudent(){
        assertEquals(-1, service.saveNota("99", "1", 8.5, 7, "good"));
    }

    @Test
    public void tc_3_invalidTema(){
        assertEquals(-1, service.saveNota("1", "99", 8.5, 7, "good"));
    }

    @Test
    public void tc_4_invalidNota(){
        assertEquals(-1, service.saveNota("1", "99", -1, 7, "good"));
    }

    @Test
    public void tc_5_invalidDealine(){
        assertEquals(-1, service.saveNota("1", "99", 10, 0, "good"));
    }
}
