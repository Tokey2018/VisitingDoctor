layui.define(['table', 'tDiagnosisApi'], function(exports) {
    var tDiagnosisApi = layui.tDiagnosisApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"tDiagnosisTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些TDiagnosis?",function(){
            var ids =Common.concatBatchId(data,"rid");
            tDiagnosisApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});