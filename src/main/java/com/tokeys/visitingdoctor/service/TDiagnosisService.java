package com.tokeys.visitingdoctor.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.tokeys.visitingdoctor.dao.TDiagnosisDao;
import com.tokeys.visitingdoctor.entity.TDiagnosis;
import com.ibeetl.admin.core.service.BaseService;

/**
 * TDiagnosis Service
 */

@Service
@Transactional
public class TDiagnosisService extends BaseService<TDiagnosis>{

    @Autowired private TDiagnosisDao tDiagnosisDao;

    public PageQuery<TDiagnosis>queryByCondition(PageQuery query){
        PageQuery ret =  tDiagnosisDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelTDiagnosis(List<Long> ids){
        try {
            tDiagnosisDao.batchDelTDiagnosisByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除TDiagnosis失败", e);
        }
    }
}