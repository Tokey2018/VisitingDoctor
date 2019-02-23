queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from t_visit t
    where del_flag=0 
    @//数据权限，该sql语句功能点  
    and #function("tVisit.query")#
    
    
    

batchDelTVisitByIds
===

* 批量逻辑删除

    update t_visit set del_flag = 1 where rid  in( #join(ids)#)
    
