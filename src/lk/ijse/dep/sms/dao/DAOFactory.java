package lk.ijse.dep.sms.dao;

import lk.ijse.dep.sms.dao.custom.impl.BatchDAOImpl;
import lk.ijse.dep.sms.dao.custom.impl.LoginDAOImpl;
import lk.ijse.dep.sms.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.dep.sms.dao.custom.impl.StudentDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        switch (daoType) {
            case STUDENT:
                return (T) new StudentDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case LOGIN:
                return (T) new LoginDAOImpl();
            case BATCH:
                return (T) new BatchDAOImpl();
            default:
                return null;
        }
    }
}
