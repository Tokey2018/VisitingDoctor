queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from t_treatment t
    where del_flag=0 
    @//数据权限，该sql语句功能点  
    and #function("tTreatment.query")#
    
    
    

batchDelTTreatmentByIds
===

* 批量逻辑删除

    update t_treatment set del_flag = 1 where rid  in( #join(ids)#)
    
