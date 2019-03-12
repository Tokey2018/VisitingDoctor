package com.tokeys.visitingdoctor.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

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
public class TMedicalRecord extends BaseEntity{
    public static final int DIAGNOSIS = 1;

    @NotNull(message = "ID不能为空", groups =ValidateConfig.UPDATE.class)
    @SeqID(name = ORACLE_CORE_SEQ_NAME)
    @AssignID("simple")

    private Long rid ;
	

    private Long diagnosisid ;
	

    private Integer recordype ;
	

    private String filename ;
	

    private String filetype ;
	

    private String filepath ;
	
	/*逻辑删除标志*/
	@InsertIgnore
	@LogicDelete(value = 1)

    private Integer delFlag ;


    @InsertIgnore
    private Date createtime ;
	
    public TMedicalRecord()
    {
    }

    public Long getRid(){
	    return  rid;
    }
    public void setRid(Long rid){
        this.rid = rid;
    }

    public Long getDiagnosisid(){
	    return  diagnosisid;
    }
    public void setDiagnosisid(Long diagnosisid){
        this.diagnosisid = diagnosisid;
    }

    public Integer getRecordype(){
	    return  recordype;
    }
    public void setRecordype(Integer recordype){
        this.recordype = recordype;
    }

    public String getFilename(){
	    return  filename;
    }
    public void setFilename(String filename){
        this.filename = filename;
    }

    public String getFiletype(){
	    return  filetype;
    }
    public void setFiletype(String filetype){
        this.filetype = filetype;
    }

    public String getFilepath(){
	    return  filepath;
    }
    public void setFilepath(String filepath){
        this.filepath = filepath;
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
