layui.define([ 'form', 'laydate', 'table' ], function(exports) {
    var form = layui.form;
    var laydate = layui.laydate;
    var table = layui.table;
    var tDiagnosisTable = null;
    var view ={
        init:function(){
            this.initTable();
            this.initSearchForm();
            this.initToolBar();
            window.dataReload = function(){
                Lib.doSearchForm($("#searchForm"),tDiagnosisTable)
            }
        },
        initTable:function(){
            tDiagnosisTable = table.render({
                elem : '#tDiagnosisTable',
                height : Lib.getTableHeight(1),
                cellMinWidth: 100,
                method : 'post',
                url : Common.ctxPath + '/visitingdoctor/tDiagnosis/list.json' // 数据接口
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

                    field : 'status', 
                        title : 'status',
                },
                {

                    field : 'createtime', 
                        title : 'createtime',
                }

        ] ]

        });

            table.on('checkbox(tDiagnosisTable)', function(obj){
                var tDiagnosis = obj.data;
                if(obj.checked){
                    //按钮逻辑Lib.buttonEnable()
                }else{

                }
            })
        },

        initSearchForm:function(){
            Lib.initSearchForm( $("#searchForm"),tDiagnosisTable,form);
        },
        initToolBar:function(){
            toolbar = {
                add : function() { // 获取选中数据
                    var url = "/visitingdoctor/tDiagnosis/add.do";
                    Common.openDlg(url,"TDiagnosis管理>新增");
                },
                edit : function() { // 获取选中数目
                    var data = Common.getOneFromTable(table,"tDiagnosisTable");
                    if(data==null){
                        return ;
                    }
                    var url = "/visitingdoctor/tDiagnosis/edit.do?rid="+data.rid;
                    Common.openDlg(url,"TDiagnosis管理>"+data.rid+">编辑");
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