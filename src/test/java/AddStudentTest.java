
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

public class AddStudentTest {

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

    @Test
    public void testCase_1_valid(){
        assertEquals(1, service.saveStudent("6", "Ana", 933));
    }

    @Test
    public void testCase_2_emptyId(){
        assertEquals(1, service.saveStudent("", "Ana", 933));
    }

    @Test
    public void testCase_3_nullId(){
        assertEquals(1, service.saveStudent(null, "Ana", 933));
    }

    @Test
    public void testCase_4_emptyName(){
        assertEquals(1, service.saveStudent("7", "", 933));
    }

    @Test
    public void testCase_5_nullName(){
        assertEquals(1, service.saveStudent("8", null, 933));
    }

    @Test
    public void testCase_6_smallGroup(){
        assertEquals(1, service.saveStudent("9", "Ana", 105));
    }

    @Test
    public void testCase_7_largeGroup(){
        assertEquals(1, service.saveStudent("10", "Ana", 1000));
    }

    @Test
    public void testCase_8_bva_LowerBound(){
        assertEquals(1, service.saveStudent("11", "Vasile", 111));
    }


    @Test
    public void testCase_9_bva_UpperBound(){
        assertEquals(1, service.saveStudent("12", "Vasile", 937));
    }

    @Test
    public void testCase_10_bva_UnderLowerBound(){
        assertEquals(1, service.saveStudent("13", "Vasile", 110));
    }


    @Test
    public void testCase_11_bva_AboveLowerBound(){
        assertEquals(1, service.saveStudent("14", "Vasile", 112));
    }

    @Test
    public void testCase_12_bva_UnderUpperBound(){
        assertEquals(1, service.saveStudent("15", "Vasile", 936));
    }


    @Test
    public void testCase_13_bva_AboveUpperBound(){
        assertEquals(1, service.saveStudent("16", "Vasile", 938));
    }


}
