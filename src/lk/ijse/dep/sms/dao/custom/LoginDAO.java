package lk.ijse.dep.sms.dao.custom;

import lk.ijse.dep.sms.dao.CrudDAO;
import lk.ijse.dep.sms.entity.Login;

import java.util.List;

public interface LoginDAO extends CrudDAO<Login,Integer> {

    public boolean existsIsUser(String usertype, String username, String password) throws Exception;

    List<String> getUserTypes() throws Exception;
}
