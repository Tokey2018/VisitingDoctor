package com.tokeys.visitingdoctor.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.tokeys.visitingdoctor.entity.*;

/**
 * TMedicalRecord Dao
 */
@SqlResource("visitingdoctor.tMedicalRecord")
public interface TMedicalRecordDao extends BaseMapper<TMedicalRecord>{
    public PageQuery<TMedicalRecord> queryByCondition(PageQuery query);
    public void batchDelTMedicalRecordByIds( List<Long> ids);
}