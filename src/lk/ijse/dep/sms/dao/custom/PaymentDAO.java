package lk.ijse.dep.sms.dao.custom;

import lk.ijse.dep.sms.dao.CrudDAO;
import lk.ijse.dep.sms.entity.Payment;
import lk.ijse.dep.sms.entity.PaymentPK;

public interface PaymentDAO extends CrudDAO<Payment, String> {

    String getLastPayentId() throws Exception;

    boolean existsByStudentId(String sid) throws Exception;

    boolean existsByPaymentId(PaymentPK paymentPK) throws Exception;

//    public Payment find(PaymentPK paymentPK) throws Exception;
//
//    public boolean delete(PaymentPK paymentPK) throws Exception;
}