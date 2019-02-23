/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateTTreatment:function(form,callback){
                Lib.submitForm("/visitingdoctor/tTreatment/update.json",form,{},callback)
            },
            addTTreatment:function(form,callback){
                Lib.submitForm("/visitingdoctor/tTreatment/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/visitingdoctor/tTreatment/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('tTreatmentApi',api);
});