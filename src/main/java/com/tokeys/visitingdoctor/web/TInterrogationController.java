package com.tokeys.visitingdoctor.web;

import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.file.FileService;
import com.ibeetl.admin.core.util.ConvertUtil;
import com.ibeetl.admin.core.util.ValidateConfig;
import com.ibeetl.admin.core.web.JsonResult;
import com.tokeys.visitingdoctor.entity.TDiagnosis;
import com.tokeys.visitingdoctor.entity.TMedicalRecord;
import com.tokeys.visitingdoctor.entity.TTreatment;
import com.tokeys.visitingdoctor.entity.TVisit;
import com.tokeys.visitingdoctor.model.Condition;
import com.tokeys.visitingdoctor.model.Doctor;
import com.tokeys.visitingdoctor.model.User;
import com.tokeys.visitingdoctor.service.InterrogationService;
import com.tokeys.visitingdoctor.service.TDiagnosisService;
import com.tokeys.visitingdoctor.web.query.TDiagnosisQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * TDiagnosis 接口
 */
@Controller
public class TInterrogationController {

    private final Log logger = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/visitingdoctor/interrogation";


    @Autowired private InterrogationService tInterrogationService;

    @PostMapping(MODEL + "/startWork")
    @Function("InterrogationService.startWork")
    @ResponseBody
    public JsonResult<TTreatment> startWork(@RequestBody TTreatment treat) {
        if(treat == null){
            logger.info("parameter is invalid");
            return JsonResult.failMessage("parameter is error");
        }
        Doctor doc = new Doctor();
        doc.setRid(treat.getDoctorid());
        doc.setName(treat.getDoctorname());
        Condition cond = new Condition();
        cond.setSymptomFlags(new long[]{treat.getSymptomflags1()});
        cond.setImMode(treat.getImmode());
        cond.setMedicineType(treat.getMedicinetype());
        cond.setTreatmentType(treat.getTreatmenttype());
        cond.setStartTime(treat.getStarttime());
        cond.setEndTime(treat.getEndtime());
        TTreatment tTreatment = tInterrogationService.startWork(doc, cond);
        if(tTreatment != null)
            return  JsonResult.success(tTreatment);
        else
            return JsonResult.failMessage("startWork failed");
    }

    @PostMapping(MODEL + "/stopWork")
    @Function("InterrogationService.stopWork")
    @ResponseBody
    public JsonResult<TTreatment> stopWork(Long doctorid) {
        if(doctorid == null){
            logger.info("parameter is invalid");
            return JsonResult.failMessage("parameter is error");
        }
        Doctor doc = new Doctor();
        doc.setRid(doctorid);
        TTreatment treat = tInterrogationService.stopWork(doc);
        if(treat != null)
            return  JsonResult.success(treat);
        else
            return JsonResult.failMessage("startWork failed");
    }

    @PostMapping(MODEL + "/searchHistoryDoctor")
    @Function("InterrogationService.searchHistoryDoctor")
    @ResponseBody
    public JsonResult<List<Long>> searchHistoryDoctor(@RequestBody TVisit visit) {
        if(visit == null){
            logger.info("parameter is invalid");
            return JsonResult.failMessage("parameter is error");
        }
        List<Long> doctors = tInterrogationService.searchHistoryDoctor(visit);
        if(doctors == null)
            return JsonResult.failMessage("searchHistoryDoctor failed");
        else if(doctors.size() <= 0)
            return JsonResult.failMessage("search no history doctor");
        else
            return  JsonResult.success(doctors);
    }

    @PostMapping(MODEL + "/selectHistoryDoctor")
    @Function("InterrogationService.selectHistoryDoctor")
    @ResponseBody
    public JsonResult<TTreatment> selectHistoryDoctor(Long doctorid) {
        if(doctorid == null){
            logger.info("parameter is invalid");
            return JsonResult.failMessage("parameter is error");
        }
        Doctor doc = new Doctor();
        doc.setRid(doctorid);
        TTreatment tTreatment = tInterrogationService.selectHistoryDoctor(doc);
        if(tTreatment != null)
            return  JsonResult.success(tTreatment);
        else
            return JsonResult.failMessage("selectHistoryDoctor failed");
    }

    @PostMapping(MODEL + "/matchDoctor")
    @Function("InterrogationService.matchDoctor")
    @ResponseBody
    public JsonResult<TTreatment> matchDoctor(Long visitid) {
        if(visitid == null){
            logger.info("parameter is invalid");
            return JsonResult.failMessage("parameter is error");
        }
        TVisit visit = new TVisit();
        visit.setRid(visitid);
        List<TTreatment> treats = tInterrogationService.matchDoctor(visit);
        if(treats == null)
            return JsonResult.failMessage("match doctor error");
        else if(treats.size() <= 0)
            return JsonResult.failMessage("match no doctor");
        else
            return JsonResult.success(treats.get(0));
    }

    @PostMapping(MODEL + "/setTreatmentStatus")
    @Function("InterrogationService.setTreatmentStatus")
    @ResponseBody
    public JsonResult<TTreatment> setTreatmentStatus(Long doctorid, Integer status) {
        if(doctorid == null || status == null){
            logger.info("parameter is invalid");
            return JsonResult.failMessage("parameter is error");
        }
        TTreatment treat = new TTreatment();
        treat.setDoctorid(doctorid);
        treat.setStatus(status);
        treat = tInterrogationService.setTreatmentStatus(treat);
        if(treat != null)
            return JsonResult.success(treat);
        else
            return JsonResult.failMessage("error");
    }

    @PostMapping(MODEL + "/acceptPatient")
    @Function("InterrogationService.acceptPatient")
    @ResponseBody
    public JsonResult<TDiagnosis> acceptPatient(Long visitid, Long treatid) {
        if(visitid == null || treatid == null){
            logger.info("parameter is invalid");
            return JsonResult.failMessage("parameter is error");
        }
        TVisit visit = new TVisit();
        visit.setRid(visitid);
        TTreatment treat = new TTreatment();
        treat.setRid(treatid);
        TDiagnosis dg = tInterrogationService.acceptPatient(visit, treat);
        if(dg == null){
            return JsonResult.failMessage("accept patient failed");
        }else{
            return JsonResult.success(dg);
        }
    }

    @PostMapping(MODEL + "/cancelDiagnosis")
    @Function("InterrogationService.cancelDiagnosis")
    @ResponseBody
    public JsonResult<TDiagnosis> cancelDiagnosis(Long diagid, Integer status) {
        if(diagid == null || status == null){
            logger.info("parameter is invalid");
            return JsonResult.failMessage("parameter is error");
        }
        TDiagnosis dg = new TDiagnosis();
        dg.setRid(diagid);
        dg = tInterrogationService.cancelDiagnosis(dg, status);
        if(dg == null){
            return JsonResult.failMessage("cancel patient failed");
        }else{
            return JsonResult.success(dg);
        }
    }

    @PostMapping(MODEL + "/makeRecord")
    @Function("InterrogationService.makeRecord")
    @ResponseBody
    public JsonResult<TMedicalRecord> makeRecord(@RequestBody TMedicalRecord medicalRecord) {
        if(medicalRecord == null){
            logger.info("parameter is invalid");
            return JsonResult.failMessage("parameter is error");
        }
        TDiagnosis dg = new TDiagnosis();
        dg.setRid(medicalRecord.getDiagnosisid());
        TMedicalRecord mr = new TMedicalRecord();
        mr.setRecordype(medicalRecord.getRecordype());
        mr.setFilename(medicalRecord.getFilename());
        mr.setFiletype(medicalRecord.getFiletype());
        mr.setFilepath(medicalRecord.getFilepath());
        if(tInterrogationService.makeRecord(dg, mr) == null){
            return JsonResult.failMessage("cancel patient failed");
        }else{
            return JsonResult.success(mr);
        }
    }

    @PostMapping(MODEL + "/makeVisit")
    @Function("InterrogationService.makeVisit")
    @ResponseBody
    public JsonResult<TVisit> makeVisit(@RequestBody TVisit visit) {
        User user = new User();
        user.setRid(visit.getPatientid());
        user.setName(visit.getPatientname());
        Condition cond = new Condition();
        cond.setSymptomFlags(new long[]{visit.getSymptomflags1()});
        cond.setImMode(visit.getImmode());
        cond.setMedicineType(visit.getMedicinetype());
        cond.setTreatmentType(visit.getTreatmenttype());
        cond.setStartTime(visit.getStarttime());
        cond.setEndTime(visit.getEndtime());
        TVisit v = tInterrogationService.makeVisit(user, cond);
        if(v == null){
            return JsonResult.failMessage("make visit failed");
        }else{
            return JsonResult.success(v);
        }
    }

}
