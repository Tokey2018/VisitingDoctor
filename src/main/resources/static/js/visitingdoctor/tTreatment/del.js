layui.define(['table', 'tTreatmentApi'], function(exports) {
    var tTreatmentApi = layui.tTreatmentApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"tTreatmentTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些TTreatment?",function(){
            var ids =Common.concatBatchId(data,"rid");
            tTreatmentApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});