package lk.ijse.dep.sms.dao.custom;

import lk.ijse.dep.sms.dao.CrudDAO;
import lk.ijse.dep.sms.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student, String> {

    String getLastStudentId() throws Exception;

    List<Student> getStudent(String id) throws Exception;
}
