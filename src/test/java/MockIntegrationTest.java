import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;

import static org.junit.Assert.assertEquals;

import org.mockito.Mock;
import static org.mockito.Mockito.*;


public class MockIntegrationTest {

    @Mock
    StudentXMLRepository fileRepository1;

    @Mock
    TemaXMLRepository fileRepository2;

    @Mock
    NotaXMLRepository fileRepository3;

    Service service;

    @Before
    public void setup() {
        fileRepository1 = mock(StudentXMLRepository.class);
        fileRepository2 = mock(TemaXMLRepository.class);
        fileRepository3 = mock(NotaXMLRepository.class);
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }


    @Test
    public void mock_1_addStudent() {
        when(fileRepository1.save(new Student("7", "Anna", 933))).thenReturn(null);

        var result = service.saveStudent("7", "Anna", 933);
        assertEquals(result, 1);
    }


    @Test
    public void mock_2_addAssignment() {
        when(fileRepository1.save(new Student("7", "Anna", 933))).thenReturn(null);

        when(fileRepository2.save(new Tema("4", "tema_4", 7, 5))).thenReturn(null);

        var resultStudent = service.saveStudent("7", "Anna", 933);
        assertEquals(resultStudent, 1);

        var resultAssignment = service.saveTema("4", "tema_4", 7, 5);
        assertEquals(resultAssignment, 1);
    }

    @Test
    public void mock_3_addGrade() {
        Student student = new Student("7", "Anna", 933);

        Tema tema = new Tema("4", "tema_4", 7, 5);

        when(fileRepository1.save(student)).thenReturn(null);

        when(fileRepository2.save(tema)).thenReturn(null);

        when(service.saveNota("7", "4", 8.5, 7, "good")).thenReturn(null);

        when(fileRepository1.findOne("7")).thenReturn(student);
        when(fileRepository2.findOne("4")).thenReturn(tema);

        var resultStudent = service.saveStudent("7", "Anna", 933);
        assertEquals(resultStudent, 1);

        var resultAssignment = service.saveTema("4", "tema_4", 7, 5);
        assertEquals(resultAssignment, 1);

        var resultGrade = service.saveNota("7", "4", 8.5, 7, "good");
        assertEquals(resultGrade, 1);
    }
}
