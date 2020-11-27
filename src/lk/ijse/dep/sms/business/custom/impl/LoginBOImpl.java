package lk.ijse.dep.sms.business.custom.impl;

import lk.ijse.dep.sms.business.custom.LoginBO;
import lk.ijse.dep.sms.dao.DAOFactory;
import lk.ijse.dep.sms.dao.DAOTypes;
import lk.ijse.dep.sms.dao.custom.LoginDAO;

import java.util.List;

public class LoginBOImpl implements LoginBO {

    private LoginDAO loginDAO = DAOFactory.getInstance().getDAO(DAOTypes.LOGIN);


    @Override
    public List<String> getUserTypes() throws Exception {
        return loginDAO.getUserTypes();
    }

    @Override
    public boolean isUser(String userType,String username,String password) throws Exception {
        return loginDAO.existsIsUser(userType, username, password);
    }
}
