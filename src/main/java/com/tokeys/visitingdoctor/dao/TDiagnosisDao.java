package com.tokeys.visitingdoctor.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.tokeys.visitingdoctor.entity.*;

/**
 * TDiagnosis Dao
 */
@SqlResource("visitingdoctor.tDiagnosis")
public interface TDiagnosisDao extends BaseMapper<TDiagnosis>{
    public PageQuery<TDiagnosis> queryByCondition(PageQuery query);
    public void batchDelTDiagnosisByIds( List<Long> ids);
}