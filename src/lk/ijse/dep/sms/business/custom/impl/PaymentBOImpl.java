package lk.ijse.dep.sms.business.custom.impl;

import lk.ijse.dep.sms.business.custom.PaymentBO;
import lk.ijse.dep.sms.business.exception.AlreadyExistsInPaymentException;
import lk.ijse.dep.sms.business.exception.AlreadyExistsInStudentException;
import lk.ijse.dep.sms.dao.DAOFactory;
import lk.ijse.dep.sms.dao.DAOTypes;
import lk.ijse.dep.sms.dao.custom.PaymentDAO;
import lk.ijse.dep.sms.dao.custom.StudentDAO;
import lk.ijse.dep.sms.dto.PaymentDTO;
import lk.ijse.dep.sms.dto.StudentDTO;
import lk.ijse.dep.sms.entity.Payment;
import lk.ijse.dep.sms.entity.PaymentPK;
import lk.ijse.dep.sms.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    private PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOTypes.PAYMENT);
    private StudentDAO studentDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

    public PaymentBOImpl() {
    }

    @Override
    public boolean savePayment(PaymentDTO payment) throws Exception {
        return paymentDAO.save(new Payment(payment.getPno(), payment.getBatchNo(), payment.getStudentId(),
                payment.getTotalFee()));
    }

    @Override
    public boolean updatePayment(PaymentDTO payment) throws Exception {
        return paymentDAO.update(new Payment(payment.getPno(), payment.getBatchNo(), payment.getStudentId(),
                payment.getTotalFee()));
    }

    @Override
    public boolean deletePayment(PaymentPK paymentPK) throws Exception {
        if (paymentDAO.existsByPaymentId(paymentPK)){
            throw new AlreadyExistsInPaymentException("Payment already exists in an student, hence unable to delete");
        }
        return paymentDAO.delete(paymentPK.getStudentId());
    }

    @Override
    public List<PaymentDTO> findAllPayments() throws Exception {
        System.out.println(paymentDAO);
        List<Payment> alPayents = paymentDAO.findAll();

        List<PaymentDTO> dtos = new ArrayList<>();
        for (Payment payment : alPayents) {
            dtos.add(new PaymentDTO(payment.getPno(), payment.getPaymentPK().getBatchNo(),payment.getPaymentPK().getStudentId(), payment.getTotalFee()));
        }
        System.out.println("ddd"+dtos);
        return dtos;
    }

    @Override
    public String getLastPaymentId() throws Exception {
        return paymentDAO.getLastPayentId();
    }

    @Override
    public PaymentDTO findPayment(PaymentPK paymentPK) throws Exception {
        Payment payment = paymentDAO.find(paymentPK.getStudentId());
        return new PaymentDTO(payment.getPno(),payment.getPaymentPK(), payment.getTotalFee());
    }


    @Override
    public List<String> getAllPaymentIDs() throws Exception {
        List<Payment> payments = paymentDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Payment payment : payments) {
            ids.add(payment.getPaymentPK().getBatchNo());
        }
        return ids;
    }
}
