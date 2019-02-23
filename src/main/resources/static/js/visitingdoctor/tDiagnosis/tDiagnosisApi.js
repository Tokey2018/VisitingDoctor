/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateTDiagnosis:function(form,callback){
                Lib.submitForm("/visitingdoctor/tDiagnosis/update.json",form,{},callback)
            },
            addTDiagnosis:function(form,callback){
                Lib.submitForm("/visitingdoctor/tDiagnosis/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/visitingdoctor/tDiagnosis/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('tDiagnosisApi',api);
});