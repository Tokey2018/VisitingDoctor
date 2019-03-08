package com.tokeys.visitingdoctor.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;
import org.beetl.sql.core.engine.PageQuery;

import  com.tokeys.visitingdoctor.entity.*;

/**
 * TTreatment Dao
 */
@SqlResource("visitingdoctor.tTreatment")
public interface TTreatmentDao extends BaseMapper<TTreatment>{
    public PageQuery<TTreatment> queryByCondition(PageQuery query);
    public void batchDelTTreatmentByIds( List<Long> ids);
    public List<TTreatment> getByDoctorId(Long doctorid);
    public List<TTreatment> matchDoctor(TVisit visit);
}