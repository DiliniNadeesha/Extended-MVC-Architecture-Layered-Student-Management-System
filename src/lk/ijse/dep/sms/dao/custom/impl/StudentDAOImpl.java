package lk.ijse.dep.sms.dao.custom.impl;

import lk.ijse.dep.sms.business.exception.AlreadyExistsInPaymentException;
import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.custom.StudentDAO;
import lk.ijse.dep.sms.dto.StudentDTO;
import lk.ijse.dep.sms.entity.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public String getLastStudentId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT sid FROM Student ORDER BY sid DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public List<Student> getStudent(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Student WHERE sid LIKE ? || sname like ? || address like ? || contactNo like ? || email like ?",id,id,id,id,id);
        List<Student> students = new ArrayList<>();
        while (rst.next()) {
            students.add(new Student(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)));
        }
        System.out.println(students);
        return students;
    }

    @Override
    public List<Student> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Student");
        List<Student> students = new ArrayList<>();
        while (rst.next()) {
            students.add(new Student(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)));
        }
        return students;
    }

    @Override
    public Student find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Student WHERE sid=?", s);
        if (rst.next()) {
            return new Student(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5));
        }
        return null;
    }

    @Override
    public boolean save(Student entity) throws Exception {
        return CrudUtil.execute("INSERT INTO Student VALUES(?,?,?,?,?)", entity.getStudentId(), entity.getName(),
                entity.getAddress(),entity.getcontactNo(),entity.getEmail());
    }

    @Override
    public boolean update(Student entity) throws Exception {
        return CrudUtil.execute("UPDATE student SET sname=?, address=?, contactNo=?, email=? WHERE sid=?", entity.getName(),
                entity.getAddress(), entity.getcontactNo(), entity.getEmail(), entity.getStudentId());
    }

    @Override
    public boolean delete(String sid) throws Exception {
       return CrudUtil.execute("DELETE FROM student WHERE sid=?", sid);
    }
}
