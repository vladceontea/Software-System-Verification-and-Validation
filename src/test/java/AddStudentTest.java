
import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.*;

public class AddStudentTest {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    private static final String INVALID_ID_ERROR_MESSAGE = "ID invalid! \n";
    private static final String INVALID_NAME_ERROR_MESSAGE = "Nume invalid! \n";
    private static final String INVALID_GROUP_ERROR_MESSAGE = "Grupa invalida! \n";

    @Test
    public void testCase_1_valid(){
        assertEquals(0, service.saveStudent("6", "Ana", 933));
    }

    @Test
    public void testCase_2_emptyId(){
        assertThrows(ValidationException.class, () -> service.saveStudent("", "Ana", 933), INVALID_ID_ERROR_MESSAGE);
    }

    @Test
    public void testCase_3_nullId(){
        assertThrows(ValidationException.class, () -> service.saveStudent(null, "Ana", 933), INVALID_ID_ERROR_MESSAGE);
    }

    @Test
    public void testCase_4_emptyName(){
        assertThrows(ValidationException.class, () -> service.saveStudent("7", "", 933), INVALID_NAME_ERROR_MESSAGE);
    }

    @Test
    public void testCase_5_nullName(){
        assertThrows(ValidationException.class, () -> service.saveStudent("8", null, 933), INVALID_NAME_ERROR_MESSAGE);
    }

    @Test
    public void testCase_6_smallGroup(){
        assertThrows(ValidationException.class, () -> service.saveStudent("9", "Ana", 105), INVALID_GROUP_ERROR_MESSAGE);
    }

    @Test
    public void testCase_7_largeGroup(){
        assertThrows(ValidationException.class, () -> service.saveStudent("10", "Ana", 1000), INVALID_GROUP_ERROR_MESSAGE);
    }

    @Test
    public void testCase_8_bva_LowerBound(){
        assertEquals(0, service.saveStudent("11", "Vasile", 111));
    }

    @Test
    public void testCase_9_bva_UpperBound(){
        assertEquals(0, service.saveStudent("12", "Vasile", 937));
    }

    @Test
    public void testCase_10_bva_UnderLowerBound(){
        assertThrows(ValidationException.class, () -> service.saveStudent("13", "Vasile", 110), INVALID_GROUP_ERROR_MESSAGE);
    }

    @Test
    public void testCase_11_bva_AboveLowerBound(){
        assertEquals(0, service.saveStudent("14", "Vasile", 112));
    }

    @Test
    public void testCase_12_bva_UnderUpperBound(){
        assertEquals(0, service.saveStudent("15", "Vasile", 936));
    }

    @Test
    public void testCase_13_bva_AboveUpperBound(){
        assertThrows(ValidationException.class, () -> service.saveStudent("16", "Vasile", 938), INVALID_GROUP_ERROR_MESSAGE);
    }


}
