layui.define(['table', 'tMedicalRecordApi'], function(exports) {
    var tMedicalRecordApi = layui.tMedicalRecordApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"tMedicalRecordTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些TMedicalRecord?",function(){
            var ids =Common.concatBatchId(data,"rid");
            tMedicalRecordApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});