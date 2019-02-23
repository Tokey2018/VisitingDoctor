layui.define(['table', 'tVisitApi'], function(exports) {
    var tVisitApi = layui.tVisitApi;
    var table=layui.table;
    var view = {
        init:function(){
        },
        delBatch:function(){
            var data = Common.getMoreDataFromTable(table,"tVisitTable");
            if(data==null){
                return ;
            }
            Common.openConfirm("确认要删除这些TVisit?",function(){
            var ids =Common.concatBatchId(data,"rid");
            tVisitApi.del(ids,function(){
                Common.info("删除成功");
                    dataReload();
                })
            })
        }
    }
    exports('del',view);
	
});