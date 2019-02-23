package com.tokeys.visitingdoctor.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.sql.core.engine.PageQuery;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.ReaderConfig;
import org.jxls.reader.XLSReadMessage;
import org.jxls.reader.XLSReadStatus;
import org.jxls.reader.XLSReader;
import org.jxls.util.JxlsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ibeetl.admin.console.web.dto.DictExcelImportData;
import com.ibeetl.admin.console.web.query.UserQuery;
import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.annotation.Query;
import com.ibeetl.admin.core.entity.CoreDict;
import com.ibeetl.admin.core.entity.CoreUser;
import com.ibeetl.admin.core.file.FileItem;
import com.ibeetl.admin.core.file.FileService;
import com.ibeetl.admin.core.web.JsonResult;
import com.ibeetl.admin.core.util.*;
import com.tokeys.visitingdoctor.entity.*;
import com.tokeys.visitingdoctor.service.*;
import com.tokeys.visitingdoctor.web.query.*;

/**
 * TDiagnosis 接口
 */
@Controller
public class TDiagnosisController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/visitingdoctor/tDiagnosis";


    @Autowired private TDiagnosisService tDiagnosisService;
    
    @Autowired
    FileService fileService;
    /* 页面 */

    @GetMapping(MODEL + "/index.do")
    @Function("visitingdoctor.tDiagnosis.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/visitingdoctor/tDiagnosis/index.html") ;
        view.addObject("search", TDiagnosisQuery.class.getName());
        return view;
    }

    @GetMapping(MODEL + "/edit.do")
    @Function("visitingdoctor.tDiagnosis.edit")
    @ResponseBody
    public ModelAndView edit(Long rid) {
        ModelAndView view = new ModelAndView("/visitingdoctor/tDiagnosis/edit.html");
        TDiagnosis tDiagnosis = tDiagnosisService.queryById(rid);
        view.addObject("tDiagnosis", tDiagnosis);
        return view;
    }

    @GetMapping(MODEL + "/add.do")
    @Function("visitingdoctor.tDiagnosis.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/visitingdoctor/tDiagnosis/add.html");
        return view;
    }

    /* ajax json */

    @PostMapping(MODEL + "/list.json")
    @Function("visitingdoctor.tDiagnosis.query")
    @ResponseBody
    public JsonResult<PageQuery> list(TDiagnosisQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        tDiagnosisService.queryByCondition(page);
        return JsonResult.success(page);
    }

    @PostMapping(MODEL + "/add.json")
    @Function("visitingdoctor.tDiagnosis.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)TDiagnosis tDiagnosis)
    {
        tDiagnosisService.save(tDiagnosis);
        return new JsonResult().success();
    }

    @PostMapping(MODEL + "/update.json")
    @Function("visitingdoctor.tDiagnosis.update")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  TDiagnosis tDiagnosis) {
        boolean success = tDiagnosisService.update(tDiagnosis);
        if (success) {
            return new JsonResult().success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


   
    @GetMapping(MODEL + "/view.json")
    @Function("visitingdoctor.tDiagnosis.query")
    @ResponseBody
    public JsonResult<TDiagnosis>queryInfo(Long rid) {
        TDiagnosis tDiagnosis = tDiagnosisService.queryById( rid);
        return  JsonResult.success(tDiagnosis);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("visitingdoctor.tDiagnosis.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<Long> idList = ConvertUtil.str2longs(ids);
        tDiagnosisService.batchDelTDiagnosis(idList);
        return new JsonResult().success();
    }
    

}
