package com.tokeys.visitingdoctor.dao;

import com.tokeys.visitingdoctor.entity.TDiagnosis;
import com.tokeys.visitingdoctor.entity.TVisit;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

/**
 * TDiagnosis Dao
 */
@SqlResource("visitingdoctor.tDiagnosis")
public interface TDiagnosisDao extends BaseMapper<TDiagnosis>{
    public PageQuery<TDiagnosis> queryByCondition(PageQuery query);
    public void batchDelTDiagnosisByIds( List<Long> ids);
    public List<Long> searchHistoryDoctor(TVisit cond);
}