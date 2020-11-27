package lk.ijse.dep.sms.business.custom;

import lk.ijse.dep.sms.business.SuperBO;
import lk.ijse.dep.sms.dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {

    boolean saveStudent(StudentDTO student)throws Exception;

    boolean updateStudent(StudentDTO student)throws Exception;

    boolean deleteStudent(String sid) throws Exception;

    List<StudentDTO> findAllStudents() throws Exception;

    String getLastStudentId()throws Exception;

    StudentDTO findStudent(String sid) throws Exception;

    List<String> getAllStudentIDs() throws Exception;

    List<StudentDTO> getStudentInfo(String s) throws Exception;
}
