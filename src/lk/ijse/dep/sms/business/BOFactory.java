package lk.ijse.dep.sms.business;

import lk.ijse.dep.sms.business.custom.impl.LoginBOImpl;
import lk.ijse.dep.sms.business.custom.impl.PaymentBOImpl;
import lk.ijse.dep.sms.business.custom.impl.StudentBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        return (boFactory == null)? (boFactory = new BOFactory()): boFactory;
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes){
            case STUDENT:
                return (T) new StudentBOImpl();
            case PAYMENT:
                return (T) new PaymentBOImpl();
            case LOGIN:
                return (T) new LoginBOImpl();
            default:
                return null;
        }
    }

}
