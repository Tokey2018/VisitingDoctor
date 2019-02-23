package com.tokeys.visitingdoctor.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.tokeys.visitingdoctor.dao.TMedicalRecordDao;
import com.tokeys.visitingdoctor.entity.TMedicalRecord;
import com.ibeetl.admin.core.service.BaseService;

/**
 * TMedicalRecord Service
 */

@Service
@Transactional
public class TMedicalRecordService extends BaseService<TMedicalRecord>{

    @Autowired private TMedicalRecordDao tMedicalRecordDao;

    public PageQuery<TMedicalRecord>queryByCondition(PageQuery query){
        PageQuery ret =  tMedicalRecordDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelTMedicalRecord(List<Long> ids){
        try {
            tMedicalRecordDao.batchDelTMedicalRecordByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除TMedicalRecord失败", e);
        }
    }
}