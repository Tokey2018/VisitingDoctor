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
    

getByDoctorId
===
select * from t_treatment where del_flag=0 and doctorId=#doctorid# order by createTime desc

matchDoctor
===
select * from t_treatment where del_flag=0 and (symptomFlags1 & #symptomflags1# > 0)
   and imMode=#immode# and medicineType=#medicinetype# and treatmentType=#treatmenttype#
   and ((startTime<=#starttime# and endTime>=#starttime#)
       or (startTime<=#endtime# and endTime>=#endtime#))
   order by diagTimes, visitTimes desc