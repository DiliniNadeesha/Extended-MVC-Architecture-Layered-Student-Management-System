package lk.ijse.dep.sms.dao.custom;

import lk.ijse.dep.sms.dao.CrudDAO;
import lk.ijse.dep.sms.entity.Batch;

import java.util.List;

public interface BatchDAO extends CrudDAO<Batch, String> {

    String getLastBatchId() throws Exception;

    List<Batch> getBatch(String id) throws Exception;
}
