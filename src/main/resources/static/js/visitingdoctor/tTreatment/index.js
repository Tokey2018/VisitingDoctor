layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var tTreatmentTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),tTreatmentTable)
            }
        },
        initTable:function(){
            tTreatmentTable = table.render({
                elem : '#tTreatmentTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/visitingdoctor/tTreatment/list.json' // 数据接口
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

                    field : 'doctorid', 
                        title : 'doctorid',
                },
                {

                    field : 'doctorname', 
                        title : 'doctorname',
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

                    field : 'status', 
                        title : 'status',
                },
                {

                    field : 'visittimes', 
                        title : 'visittimes',
                },
                {

                    field : 'diagtimes', 
                        title : 'diagtimes',
                },
                {

                    field : 'createtime', 
                        title : 'createtime',
                }

        ] ]

        });

            table.on('checkbox(tTreatmentTable)', function(obj){
                var tTreatment = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),tTreatmentTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/visitingdoctor/tTreatment/add.do";
                    Common.openDlg(url,"TTreatment管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"tTreatmentTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/visitingdoctor/tTreatment/edit.do?rid="+data.rid;
                    Common.openDlg(url,"TTreatment管理>"+data.rid+">编辑");
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