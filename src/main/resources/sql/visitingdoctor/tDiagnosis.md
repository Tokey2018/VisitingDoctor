queryByCondition
===


    select 
    @pageTag(){
    t.*
    @}
    from t_diagnosis t
    where del_flag=0 
    @//数据权限，该sql语句功能点  
    and #function("tDiagnosis.query")#
    
    
    

batchDelTDiagnosisByIds
===

* 批量逻辑删除

    update t_diagnosis set del_flag = 1 where rid  in( #join(ids)#)
    

searchHistoryDoctor
===
select doctorId from t_diagnosis where del_flag=0 
    and patientId=#patientid# and (symptomFlags1 & #symptomflags1# > 0)
    and imMode=#immode# and medicineType=#medicinetype# and treatmentType=#treatmenttype#
    and createTime>=#starttime# and createTime<=#endtime#
    group by doctorId, createTime
    order by createTime desc
