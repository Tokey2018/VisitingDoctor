package com.tokeys.visitingdoctor.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.tokeys.visitingdoctor.dao.TTreatmentDao;
import com.tokeys.visitingdoctor.entity.TTreatment;
import com.ibeetl.admin.core.service.BaseService;

/**
 * TTreatment Service
 */

@Service
@Transactional
public class TTreatmentService extends BaseService<TTreatment>{

    @Autowired private TTreatmentDao tTreatmentDao;

    public PageQuery<TTreatment>queryByCondition(PageQuery query){
        PageQuery ret =  tTreatmentDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelTTreatment(List<Long> ids){
        try {
            tTreatmentDao.batchDelTTreatmentByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除TTreatment失败", e);
        }
    }
}