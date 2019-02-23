/*访问后台的代码*/
layui.define([], function(exports) {
    var api={
            updateTVisit:function(form,callback){
                Lib.submitForm("/visitingdoctor/tVisit/update.json",form,{},callback)
            },
            addTVisit:function(form,callback){
                Lib.submitForm("/visitingdoctor/tVisit/add.json",form,{},callback)
            },
            del:function(ids,callback){
                Common.post("/visitingdoctor/tVisit/delete.json",{"ids":ids},function(){
                    callback();
                })
            }
		
    };
    exports('tVisitApi',api);
});