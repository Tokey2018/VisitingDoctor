/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateTMedicalRecord:function(form,callback){
                Lib.submitForm("/visitingdoctor/tMedicalRecord/update.json",form,{},callback)
            },
            addTMedicalRecord:function(form,callback){
                Lib.submitForm("/visitingdoctor/tMedicalRecord/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/visitingdoctor/tMedicalRecord/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('tMedicalRecordApi',api);
});