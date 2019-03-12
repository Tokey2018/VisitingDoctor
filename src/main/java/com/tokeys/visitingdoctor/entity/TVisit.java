package com.tokeys.visitingdoctor.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.beetl.sql.core.annotatoin.*;

import com.ibeetl.admin.core.util.ValidateConfig;

import org.beetl.sql.core.TailBean;
import java.math.*;

import com.ibeetl.admin.core.annotation.Dict;
import com.ibeetl.admin.core.entity.BaseEntity;


/* 
* 
* gen by Spring Boot2 Admin 2019-03-04
*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TVisit extends BaseEntity{

    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
    @SeqID(name = ORACLE_CORE_SEQ_NAME)
    @AssignID("simple")

    private Long rid ;
	

    private Long patientid ;
	

    private String patientname ;
	

    private Long symptomflags1 ;
	

    private Integer immode ;
	

    private Integer medicinetype ;
	

    private Integer treatmenttype ;
	

    private Date starttime ;
	

    private Date endtime ;


    @JsonIgnore
    private Integer timetype ;
	
	/*逻辑删除标志*/
	@InsertIgnore
	@LogicDelete(value = 1)

    private Integer delFlag ;

    @InsertIgnore
    private Date createtime ;
	
    public TVisit()
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

    public Integer getTimetype(){
	    return  timetype;
    }
    public void setTimetype(Integer timetype){
        this.timetype = timetype;
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
