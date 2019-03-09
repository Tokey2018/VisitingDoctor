package com.tokeys.visitingdoctor.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.SeqID;

import com.ibeetl.admin.core.util.ValidateConfig;

import org.beetl.sql.core.TailBean;
import java.math.*;

import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.entity.BaseEntity;

import org.beetl.sql.core.annotatoin.InsertIgnore;
import org.beetl.sql.core.annotatoin.Version;
import org.beetl.sql.core.annotatoin.LogicDelete;


/* 
* 
* gen by Spring Boot2 Admin 2019-03-04
*/
public class TDiagnosis extends BaseEntity{
    public static final int NORMAL = 1;
    public static final int PAUSE = 2;
    public static final int CANCEL = 3;
    public static final int FINISH = 4;

    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
    @SeqID(name = ORACLE_CORE_SEQ_NAME)
    @AutoID	

    private Long rid ;
	

    private Long patientid ;
	

    private String patientname ;
	

    private Long doctorid ;
	

    private String doctorname ;
	

    private Long symptomflags1 ;
	

    private Integer immode ;
	

    private Integer medicinetype ;
	

    private Integer treatmenttype ;
	

    private Date starttime ;
	

    private Date endtime ;
	

    private Integer status ;
	
	/*逻辑删除标志*/
	@InsertIgnore
	@LogicDelete(value = 1)

    private Integer delFlag ;


    @InsertIgnore
    private Date createtime ;
	
    public TDiagnosis()
    {
    }

    public Long getRid(){
	    return  rid;
    }
    public void setRid(Long rid){
        this.rid = rid;
    }

    public Long getPatientid(){
	    return  patientid;
    }
    public void setPatientid(Long patientid){
        this.patientid = patientid;
    }

    public String getPatientname(){
	    return  patientname;
    }
    public void setPatientname(String patientname){
        this.patientname = patientname;
    }

    public Long getDoctorid(){
	    return  doctorid;
    }
    public void setDoctorid(Long doctorid){
        this.doctorid = doctorid;
    }

    public String getDoctorname(){
	    return  doctorname;
    }
    public void setDoctorname(String doctorname){
        this.doctorname = doctorname;
    }

    public Long getSymptomflags1(){
	    return  symptomflags1;
    }
    public void setSymptomflags1(Long symptomflags1){
        this.symptomflags1 = symptomflags1;
    }

    public Integer getImmode(){
	    return  immode;
    }
    public void setImmode(Integer immode){
        this.immode = immode;
    }

    public Integer getMedicinetype(){
	    return  medicinetype;
    }
    public void setMedicinetype(Integer medicinetype){
        this.medicinetype = medicinetype;
    }

    public Integer getTreatmenttype(){
	    return  treatmenttype;
    }
    public void setTreatmenttype(Integer treatmenttype){
        this.treatmenttype = treatmenttype;
    }

    public Date getStarttime(){
	    return  starttime;
    }
    public void setStarttime(Date starttime){
        this.starttime = starttime;
    }

    public Date getEndtime(){
	    return  endtime;
    }
    public void setEndtime(Date endtime){
        this.endtime = endtime;
    }

    public Integer getStatus(){
	    return  status;
    }
    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getDelFlag(){
	    return  delFlag;
    }
    public void setDelFlag(Integer delFlag){
        this.delFlag = delFlag;
    }

    public Date getCreatetime(){
	    return  createtime;
    }
    public void setCreatetime(Date createtime){
        this.createtime = createtime;
    }


}
