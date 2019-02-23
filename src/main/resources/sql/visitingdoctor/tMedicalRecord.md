queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from t_medical_record t
    where del_flag=0 
    @//数据权限，该sql语句功能点  
    and #function("tMedicalRecord.query")#
    
    
    

batchDelTMedicalRecordByIds
===

* 批量逻辑删除

    update t_medical_record set del_flag = 1 where rid  in( #join(ids)#)
    
