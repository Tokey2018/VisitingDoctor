package com.tokeys.visitingdoctor.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.tokeys.visitingdoctor.entity.*;

/**
 * TVisit Dao
 */
@SqlResource("visitingdoctor.tVisit")
public interface TVisitDao extends BaseMapper<TVisit>{
    public PageQuery<TVisit> queryByCondition(PageQuery query);
    public void batchDelTVisitByIds( List<Long> ids);
}