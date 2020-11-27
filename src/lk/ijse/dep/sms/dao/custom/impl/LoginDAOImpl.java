package lk.ijse.dep.sms.dao.custom.impl;

import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.custom.LoginDAO;
import lk.ijse.dep.sms.entity.Login;
import lk.ijse.dep.sms.entity.Student;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean existsIsUser(String usertype ,String username,  String password ) throws Exception {
        ResultSet rst =  CrudUtil.execute("SELECT * FROM login WHERE usertype=? AND username=? AND password=?",usertype,username,password);
        if (rst.next()){
            return true;
        }else {
            return false;
        }
    }

    public List<String> getUserTypes() throws Exception {
        ResultSet rst =CrudUtil.execute("SELECT DISTINCT usertype FROM login");
        List<String> userTypes=new ArrayList<>();
        while(rst.next()){
            userTypes.add(rst.getString(1));
        }
        return userTypes;
    }

    @Override
    public List<Login> findAll() throws Exception {
        return null;
    }

    @Override
    public Login find(Integer integer) throws Exception {
        return null;
    }

    @Override
    public boolean save(Login entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Login entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Integer integer) throws Exception {
        return false;
    }
}
