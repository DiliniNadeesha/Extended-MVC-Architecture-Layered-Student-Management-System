package lk.ijse.dep.sms.business.custom;

import lk.ijse.dep.sms.business.SuperBO;
import lk.ijse.dep.sms.dto.PaymentDTO;
import lk.ijse.dep.sms.dto.StudentDTO;
import lk.ijse.dep.sms.entity.PaymentPK;

import java.util.List;

public interface PaymentBO extends SuperBO {

    boolean savePayment(PaymentDTO payment)throws Exception;

    boolean updatePayment(PaymentDTO payment)throws Exception;

    boolean deletePayment(PaymentPK paymentPK) throws Exception;

    List<PaymentDTO> findAllPayments() throws Exception;

    String getLastPaymentId()throws Exception;

    PaymentDTO findPayment(PaymentPK paymentPK) throws Exception;

    List<String> getAllPaymentIDs() throws Exception;
}
