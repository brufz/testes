package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CollegeStudent studentOne;

    @Autowired
    StudentGrades studentGrades;

//    @Mock
    @MockBean
    //criando um mock para ApplicationDAO
    private ApplicationDao applicationDao;

//    @InjectMocks
    @Autowired
    //injetando mocks dependencies
    private ApplicationService applicationService;

    @BeforeEach
    public void beforeEach(){
        studentOne.setFirstname("Bruna");
        studentOne.setLastname("Franchi");
        studentOne.setEmailAddress("bfzdev@gmail.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @DisplayName("When and Verify")
    @Test
    public void assertEqualsTestAddGrades(){
        when(applicationDao
                .addGradeResultsForSingleClass(studentGrades.getMathGradeResults()))
                .thenReturn(100.0);

        assertEquals(100,applicationService
                .addGradeResultsForSingleClass(studentOne.getStudentGrades().getMathGradeResults()));

        verify(applicationDao).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
        //quantas vezes o método foi chamado
        verify(applicationDao, times(1))
                .addGradeResultsForSingleClass(studentGrades.getMathGradeResults());
    }

    @DisplayName("Find Gpa")
    @Test
    public void testAssertEqualsFindGpa(){
        when(applicationDao.findGradePointAverage(studentGrades
                .getMathGradeResults())).thenReturn(88.0);

        assertEquals(88.0, applicationService
                .findGradePointAverage(studentOne.getStudentGrades().getMathGradeResults()));

        when(applicationDao.findGradePointAverage(studentGrades
                .getMathGradeResults())).thenReturn(90.0);

        assertEquals(90.0, applicationService
                .findGradePointAverage(studentOne.getStudentGrades().getMathGradeResults()));


        verify(applicationDao, times(2))
                .findGradePointAverage(studentGrades.getMathGradeResults());
    }

    @DisplayName("Not Null")
    @Test
    public void testAssertNotNull(){
        when(applicationDao.checkNull(studentGrades.getMathGradeResults())).thenReturn(true);

        assertNotNull(applicationService.checkNull(studentOne.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Throw runtime error")
    @Test
    public void assertThrowRuntimeError(){
        CollegeStudent studentNull = (CollegeStudent) context.getBean("collegeStudent");

        doThrow(new RuntimeException()).when(applicationDao).checkNull(studentNull);

        assertThrows(RuntimeException.class, () -> {
            applicationService.checkNull(studentNull);
        });

        verify(applicationDao, times(1)).checkNull(studentNull);
    }

    @DisplayName("Multiple Stubbing")
    @Test
    public void stubbingConsecutiveCalls(){
        CollegeStudent studentNull = (CollegeStudent) context.getBean("collegeStudent");

        when(applicationDao.checkNull(studentNull))
                .thenThrow(RuntimeException.class)
                .thenReturn("Should not throw exception on the second time");

        assertThrows(RuntimeException.class, () -> {
            applicationService.checkNull(studentNull);
        });

        assertEquals("Should not throw exception on the second time",
                applicationService.checkNull(studentNull));

        verify(applicationDao, times(2)).checkNull(studentNull);
    }




}
