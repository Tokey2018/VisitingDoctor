layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var tMedicalRecordTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),tMedicalRecordTable)
            }
        },
        initTable:function(){
            tMedicalRecordTable = table.render({
                elem : '#tMedicalRecordTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/visitingdoctor/tMedicalRecord/list.json' // 数据接口
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

                    field : 'diagnosisid', 
                        title : 'diagnosisid',
                },
                {

                    field : 'recordype', 
                        title : 'recordype',
                },
                {

                    field : 'filename', 
                        title : 'filename',
                },
                {

                    field : 'filetype', 
                        title : 'filetype',
                },
                {

                    field : 'filepath', 
                        title : 'filepath',
                },
                {

                    field : 'createtime', 
                        title : 'createtime',
                }

        ] ]

        });

            table.on('checkbox(tMedicalRecordTable)', function(obj){
                var tMedicalRecord = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),tMedicalRecordTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/visitingdoctor/tMedicalRecord/add.do";
                    Common.openDlg(url,"TMedicalRecord管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"tMedicalRecordTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/visitingdoctor/tMedicalRecord/edit.do?rid="+data.rid;
                    Common.openDlg(url,"TMedicalRecord管理>"+data.rid+">编辑");
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