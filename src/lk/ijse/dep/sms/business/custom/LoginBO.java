package lk.ijse.dep.sms.business.custom;

import lk.ijse.dep.sms.business.SuperBO;

import java.util.List;

public interface LoginBO extends SuperBO {
    List<String> getUserTypes() throws Exception;

    boolean isUser(String userType,String username,String password) throws Exception;
}
