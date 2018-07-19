
package group12.DBDAOTest;

import group12.data_access.DBDAO;
import group12.registration.StudentSignupForm;
import group12.registration.TutorSignupForm;
import org.junit.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static junit.framework.TestCase.*;

public class DBDAORegistration {
    static ClassPathXmlApplicationContext context;
    static DBDAO dbda;

    @BeforeClass
    public static void confingDBConnection() {
        context = new ClassPathXmlApplicationContext("spring.xml");
        dbda = context.getBean("DBDAO", DBDAO.class);
    }

    @Test
    public void testRegStudentRightInfo() {
        StudentSignupForm student = MockData.getStudentObject();
        boolean actual = dbda.regStudent(student);
        assertTrue(actual);
    }

    @Test
    public void testRegStudentReEmail() {
        StudentSignupForm student = MockData.getStudentObject();
        boolean actual = dbda.regStudent(student);
        assertFalse(actual);
    }

    @Test
    public void testRegStudentRePhone() {
        StudentSignupForm student = MockData.getStudentObject();
        student.setEmail("testemailStu2@gmail.com");
        boolean actual = dbda.regStudent(student);
        assertFalse(actual);
    }

    @Test
    public void testRegTutorRightInfo() {
        TutorSignupForm tutor = MockData.getTutorObject();
        boolean actual = dbda.regTutor(tutor);
        assertTrue(actual);
    }

    @Test
    public void testRegTutorReEmail() {
        TutorSignupForm tutor = MockData.getTutorObject();
        tutor.setEmail("testemailTut2@gmail.com");
        boolean actual = dbda.regTutor(tutor);
        assertFalse(actual);
    }

    @Test
    public void testSaveActivationCode() {
        boolean actual = dbda.saveActivationCode(MockData.getActivationCode());
        assertTrue(actual);
    }

    @AfterClass
    public static void testDeleteStudent() {
        StudentSignupForm student = MockData.getStudentObject();
        int id = dbda.getStudentId(student.getEmail());
        boolean actual = dbda.delelteStudent(id);
        assertTrue(actual);
    }

    @AfterClass
    public static void testDeleteTutor() {
        TutorSignupForm tutor = MockData.getTutorObject();
        int id = dbda.getTutorID(tutor.getEmail());
        boolean actual = dbda.delelteTutor(id);
        assertTrue(actual);
    }
}