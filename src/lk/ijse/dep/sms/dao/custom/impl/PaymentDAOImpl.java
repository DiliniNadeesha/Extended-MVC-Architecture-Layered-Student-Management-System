package lk.ijse.dep.sms.dao.custom.impl;

import lk.ijse.dep.sms.dao.CrudUtil;
import lk.ijse.dep.sms.dao.custom.PaymentDAO;
import lk.ijse.dep.sms.entity.Payment;
import lk.ijse.dep.sms.entity.PaymentPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public String getLastPayentId() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT pno FROM payment ORDER BY pno DESC LIMIT 1");
        if (rst.next()) {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public boolean existsByStudentId(String sid) throws Exception {
        ResultSet rst =  CrudUtil.execute("SELECT * FROM payment WHERE studentId=?",sid);
        return rst.next();
    }

    @Override
    public boolean existsByPaymentId(PaymentPK paymentPK) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM payment WHERE pno=?",paymentPK.getStudentId());
        return rst.next();
    }

/*    @Override
    public Payment find(PaymentPK paymentPK) throws Exception {

        ResultSet rst = CrudUtil.execute("SELECT * FROM payment WHERE batchNo=? AND studentId=?", paymentPK.getBatchNo(), paymentPK.getStudentId());
        if (rst.next()){
            return new Payment(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4));
        }
        return null;
    }*/

/*
    @Override
    public boolean delete(PaymentPK paymentPK) throws Exception {
        return CrudUtil.execute("DELETE FROM payment WHERE batchNo=? AND studentId=?",
                paymentPK.getBatchNo(), paymentPK.getStudentId());
    }
*/

    @Override
    public List<Payment> findAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM payment");
        List<Payment> payments = new ArrayList<>();
        while (rst.next()){
            payments.add(new Payment(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)));
        }
        System.out.println("dao"+payments);
        return payments;
    }

    @Override
    public Payment find(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM payment WHERE studentId=?", s);
        if (rst.next()){
            return new Payment(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4));
        }
        return null;
    }

    @Override
    public boolean save(Payment payment) throws Exception {
        return CrudUtil.execute("INSERT INTO payment VALUES (?,?,?,?)", payment.getPno(), payment.getPaymentPK().
                        getBatchNo(), payment.getPaymentPK().getStudentId(), payment.getTotalFee());
    }

    @Override
    public boolean update(Payment payment) throws Exception {

        System.out.println("pNo"+payment.getPno());
        System.out.println("batchNo"+payment.getPaymentPK().getBatchNo());
        System.out.println("studentNo"+payment.getPaymentPK().getStudentId());
        System.out.println("totalFee"+payment.getTotalFee());

        boolean r = CrudUtil.execute("UPDATE payment SET  pno=?  WHERE studentId=? AND batchNo=? AND totalFee=?"
                , payment.getPno(), payment.getPaymentPK().getBatchNo(), payment.getPaymentPK()
                        .getStudentId(), payment.getTotalFee());
        System.out.println(r);
        return r;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM payment WHERE pno");
    }
}
