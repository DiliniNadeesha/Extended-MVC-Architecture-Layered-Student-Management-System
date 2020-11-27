package lk.ijse.dep.sms.business.custom.impl;

import lk.ijse.dep.sms.business.custom.StudentBO;
import lk.ijse.dep.sms.business.exception.AlreadyExistsInStudentException;
import lk.ijse.dep.sms.dao.DAOFactory;
import lk.ijse.dep.sms.dao.DAOTypes;
import lk.ijse.dep.sms.dao.custom.PaymentDAO;
import lk.ijse.dep.sms.dao.custom.StudentDAO;
import lk.ijse.dep.sms.dto.StudentDTO;
import lk.ijse.dep.sms.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    private StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);
    private PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOTypes.PAYMENT);

    public StudentBOImpl() {
    }

    @Override
    public boolean saveStudent(StudentDTO student) throws Exception {
        return studentDAO.save(new Student(student.getSid(), student.getSname(), student.getAddress(),
                student.getContactNo(), student.getEmail()));
    }

    @Override
    public boolean updateStudent(StudentDTO student) throws Exception {
        return studentDAO.update(new Student(student.getSid(), student.getSname(), student.getAddress(),
                student.getContactNo(), student.getEmail()));
    }

    @Override
    public boolean deleteStudent(String sid) throws Exception {
        if (paymentDAO.existsByStudentId(sid)){
            throw new AlreadyExistsInStudentException("Student already exists in an payment, hence unable to delete");
        }
        return studentDAO.delete(sid);
    }

    @Override
    public List<StudentDTO> findAllStudents() throws Exception {
        List<Student> alStudents = studentDAO.findAll();
        List<StudentDTO> dtos = new ArrayList<>();
        for (Student student : alStudents) {
            dtos.add(new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getcontactNo(),
                    student.getEmail()));
        }
        return dtos;
    }

    @Override
    public String getLastStudentId() throws Exception {
        return studentDAO.getLastStudentId();
    }

    @Override
    public StudentDTO findStudent(String sid) throws Exception {
        Student student = studentDAO.find(sid);
        return new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getcontactNo(),
                student.getEmail());
    }

    @Override
    public List<String> getAllStudentIDs() throws Exception {
        List<Student> students = studentDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Student student : students) {
            ids.add(student.getStudentId());
        }
        return ids;
    }

    @Override
    public List<StudentDTO> getStudentInfo(String s) throws Exception {
        List<Student> getStudents = studentDAO.getStudent(s);
        List<StudentDTO> dtos = new ArrayList<>();
        System.out.println(getStudents);
        for (Student student : getStudents) {
            dtos.add(new StudentDTO(student.getStudentId(), student.getName(), student.getAddress(), student.getcontactNo(),
                    student.getEmail()));
        }
        return dtos;
    }
}

