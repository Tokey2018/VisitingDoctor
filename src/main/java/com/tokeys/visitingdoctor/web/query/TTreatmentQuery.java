package com.tokeys.visitingdoctor.web.query;

import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.util.enums.CoreDictType;
import com.ibeetl.admin.core.web.query.PageParam;
import java.util.Date;
/**
 *TTreatment查询
 */
public class TTreatmentQuery extends PageParam {
    @Query(name = "rid", display = true)        
    private Long rid;
    public Long getRid(){
        return  rid;
    }
    public void setRid(Long rid ){
        this.rid = rid;
    }
 
}
