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
 * TTreatment 接口
 */
@Controller
public class TTreatmentController{

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/visitingdoctor/tTreatment";


    @Autowired private TTreatmentService tTreatmentService;
    
    @Autowired
    FileService fileService;
    /* 页面 */

    @GetMapping(MODEL + "/index.do")
    @Function("visitingdoctor.tTreatment.query")
    @ResponseBody
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("/visitingdoctor/tTreatment/index.html") ;
        view.addObject("search", TTreatmentQuery.class.getName());
        return view;
    }

    @GetMapping(MODEL + "/edit.do")
    @Function("visitingdoctor.tTreatment.edit")
    @ResponseBody
    public ModelAndView edit(Long rid) {
        ModelAndView view = new ModelAndView("/visitingdoctor/tTreatment/edit.html");
        TTreatment tTreatment = tTreatmentService.queryById(rid);
        view.addObject("tTreatment", tTreatment);
        return view;
    }

    @GetMapping(MODEL + "/add.do")
    @Function("visitingdoctor.tTreatment.add")
    @ResponseBody
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("/visitingdoctor/tTreatment/add.html");
        return view;
    }

    /* ajax json */

    @PostMapping(MODEL + "/list.json")
    @Function("visitingdoctor.tTreatment.query")
    @ResponseBody
    public JsonResult<PageQuery> list(TTreatmentQuery condtion)
    {
        PageQuery page = condtion.getPageQuery();
        tTreatmentService.queryByCondition(page);
        return JsonResult.success(page);
    }

    @PostMapping(MODEL + "/add.json")
    @Function("visitingdoctor.tTreatment.add")
    @ResponseBody
    public JsonResult add(@Validated(ValidateConfig.ADD.class)TTreatment tTreatment)
    {
        tTreatmentService.save(tTreatment);
        return new JsonResult().success();
    }

    @PostMapping(MODEL + "/update.json")
    @Function("visitingdoctor.tTreatment.update")
    @ResponseBody
    public JsonResult<String> update(@Validated(ValidateConfig.UPDATE.class)  TTreatment tTreatment) {
        boolean success = tTreatmentService.update(tTreatment);
        if (success) {
            return new JsonResult().success();
        } else {
            return JsonResult.failMessage("保存失败");
        }
    }


   
    @GetMapping(MODEL + "/view.json")
    @Function("visitingdoctor.tTreatment.query")
    @ResponseBody
    public JsonResult<TTreatment>queryInfo(Long rid) {
        TTreatment tTreatment = tTreatmentService.queryById( rid);
        return  JsonResult.success(tTreatment);
    }

    @PostMapping(MODEL + "/delete.json")
    @Function("visitingdoctor.tTreatment.delete")
    @ResponseBody
    public JsonResult delete(String ids) {
        if (ids.endsWith(",")) {
            ids = StringUtils.substringBeforeLast(ids, ",");
        }
        List<Long> idList = ConvertUtil.str2longs(ids);
        tTreatmentService.batchDelTTreatment(idList);
        return new JsonResult().success();
    }
    

}
