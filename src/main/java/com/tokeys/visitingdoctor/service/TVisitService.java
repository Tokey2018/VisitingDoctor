package com.tokeys.visitingdoctor.service;

import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibeetl.admin.core.util.PlatformException;

import com.tokeys.visitingdoctor.dao.TVisitDao;
import com.tokeys.visitingdoctor.entity.TVisit;
import com.ibeetl.admin.core.service.BaseService;

/**
 * TVisit Service
 */

@Service
@Transactional
public class TVisitService extends BaseService<TVisit>{

    @Autowired private TVisitDao tVisitDao;

    public PageQuery<TVisit>queryByCondition(PageQuery query){
        PageQuery ret =  tVisitDao.queryByCondition(query);
        queryListAfter(ret.getList());
        return ret;
    }

    public void batchDelTVisit(List<Long> ids){
        try {
            tVisitDao.batchDelTVisitByIds(ids);
        } catch (Exception e) {
            throw new PlatformException("批量删除TVisit失败", e);
        }
    }
}