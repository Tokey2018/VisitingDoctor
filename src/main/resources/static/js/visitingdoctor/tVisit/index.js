layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var tVisitTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),tVisitTable)
            }
        },
        initTable:function(){
            tVisitTable = table.render({
                elem : '#tVisitTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/visitingdoctor/tVisit/list.json' // 数据接口
                ,page : Lib.tablePage // 开启分页
                ,limit : 10,
                cols : [ [ // 表头
                    {
                        type : 'checkbox',
                        fixed:'left',
                    },
                {

                    field : 'rid', 
                        title : 'rid',
                    fixed:'left',
                        width : 60,
                },
                {

                    field : 'patientid', 
                        title : 'patientid',
                },
                {

                    field : 'patientname', 
                        title : 'patientname',
                },
                {

                    field : 'symptomflags1', 
                        title : 'symptomflags1',
                },
                {

                    field : 'immode', 
                        title : 'immode',
                },
                {

                    field : 'medicinetype', 
                        title : 'medicinetype',
                },
                {

                    field : 'treatmenttype', 
                        title : 'treatmenttype',
                },
                {

                    field : 'starttime', 
                        title : 'starttime',
                },
                {

                    field : 'endtime', 
                        title : 'endtime',
                },
                {

                    field : 'timetype', 
                        title : 'timetype',
                },
                {

                    field : 'createtime', 
                        title : 'createtime',
                }

        ] ]

        });

            table.on('checkbox(tVisitTable)', function(obj){
                var tVisit = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),tVisitTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/visitingdoctor/tVisit/add.do";
                    Common.openDlg(url,"TVisit管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"tVisitTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/visitingdoctor/tVisit/edit.do?rid="+data.rid;
                    Common.openDlg(url,"TVisit管理>"+data.rid+">编辑");
                },
                del : function() {
                    layui.use(['del'], function(){
                        var delView = layui.del
                        delView.delBatch();
                    });
                }
        };
            $('.ext-toolbar').on('click', function() {
                var type = $(this).data('type');
                toolbar[type] ? toolbar[type].call(this) : '';
            });
        }
    }
    exports('index',view);

});