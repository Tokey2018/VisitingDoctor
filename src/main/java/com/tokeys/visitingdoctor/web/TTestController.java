package com.tokeys.visitingdoctor.web;

import com.ibeetl.admin.core.annotation.Function;
import com.ibeetl.admin.core.web.JsonResult;
import com.tokeys.visitingdoctor.entity.TDiagnosis;
import com.tokeys.visitingdoctor.entity.TMedicalRecord;
import com.tokeys.visitingdoctor.entity.TTreatment;
import com.tokeys.visitingdoctor.entity.TVisit;
import com.tokeys.visitingdoctor.model.Condition;
import com.tokeys.visitingdoctor.model.Doctor;
import com.tokeys.visitingdoctor.model.User;
import com.tokeys.visitingdoctor.service.InterrogationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * TTreatment 接口
 */
@Controller
public class TTestController {

    private final Log log = LogFactory.getLog(this.getClass());
    private static final String MODEL = "/visitingdoctor/test/InterrogationService";


    @Autowired private InterrogationService tInterrogationService;

    @GetMapping(MODEL + "/startWork")
    @Function("test.InterrogationService.startWork")
    @ResponseBody
    public JsonResult<TTreatment> startWork() {
        Doctor doc = new Doctor();
        doc.setRid(1);
        doc.setName("测试医生");
        doc.setDegree(3);
        Condition cond = new Condition();
        cond.setSymptomFlags(new long[]{1});
        cond.setImMode(1);
        cond.setMedicineType(2);
        cond.setTreatmentType(3);
        cond.setTimeType(1);
        cond.setStartTime(new Date());
        cond.setEndTime( new Date(System.currentTimeMillis() + 60 * 60 * 1000));

        TTreatment tTreatment = tInterrogationService.startWork(doc, cond);
        if(tTreatment != null)
            return  JsonResult.success(tTreatment);
        else
            return JsonResult.failMessage("startWork failed");
    }

    @GetMapping(MODEL + "/stopWork")
    @Function("test.InterrogationService.stopWork")
    @ResponseBody
    public JsonResult<TTreatment> stopWork() {
        Doctor doc = new Doctor();
        doc.setRid(1);
        doc.setName("测试医生");
        doc.setDegree(3);
        TTreatment treat = tInterrogationService.stopWork(doc);
        if(treat != null)
            return  JsonResult.success(treat);
        else
            return JsonResult.failMessage("startWork failed");
    }

    @GetMapping(MODEL + "/searchHistoryDoctor")
    @Function("test.InterrogationService.searchHistoryDoctor")
    @ResponseBody
    public JsonResult<List<Long>> searchHistoryDoctor() {

        TVisit visit = new TVisit();
        visit.setPatientid(1l);
        visit.setSymptomflags1(1l);
        visit.setImmode(1);
        visit.setMedicinetype(2);
        visit.setTreatmenttype(3);
        visit.setStarttime(new Date());
        visit.setEndtime(new Date());
        List<Long> doctors = tInterrogationService.searchHistoryDoctor(visit);
        if(doctors == null)
            return JsonResult.failMessage("searchHistoryDoctor failed");
        else if(doctors.size() <= 0)
            return JsonResult.failMessage("search no history doctor");
        else
            return  JsonResult.success(doctors);
    }

    @GetMapping(MODEL + "/selectHistoryDoctor")
    @Function("test.InterrogationService.selectHistoryDoctor")
    @ResponseBody
    public JsonResult<TTreatment> selectHistoryDoctor() {
        Doctor doc = new Doctor();
        doc.setRid(1);
        doc.setName("测试医生");
        doc.setDegree(3);

        TTreatment tTreatment = tInterrogationService.selectHistoryDoctor(doc);
        if(tTreatment != null)
            return  JsonResult.success(tTreatment);
        else
            return JsonResult.failMessage("selectHistoryDoctor failed");
    }

    @GetMapping(MODEL + "/matchDoctor")
    @Function("test.InterrogationService.matchDoctor")
    @ResponseBody
    public JsonResult<TTreatment> matchDoctor() {
        TVisit visit = new TVisit();
        visit.setRid(1l);
        List<TTreatment> treats = tInterrogationService.matchDoctor(visit);
        if(treats == null)
            return JsonResult.failMessage("match doctor error");
        else if(treats.size() <= 0)
            return JsonResult.failMessage("match no doctor");
        else
            return JsonResult.success(treats.get(0));
    }

    @GetMapping(MODEL + "/setTreatmentStatus")
    @Function("test.InterrogationService.setTreatmentStatus")
    @ResponseBody
    public JsonResult<TTreatment> setTreatmentStatus() {
        TTreatment treat = new TTreatment();
        treat.setDoctorid(1l);
        treat.setStatus(2);
        treat = tInterrogationService.setTreatmentStatus(treat);
        if(treat != null)
            return JsonResult.success(treat);
        else
            return JsonResult.failMessage("error");
    }

    @GetMapping(MODEL + "/acceptPatient")
    @Function("test.InterrogationService.acceptPatient")
    @ResponseBody
    public JsonResult<TDiagnosis> acceptPatient() {
        TVisit visit = new TVisit();
        visit.setRid(1l);
        TTreatment treat = new TTreatment();
        treat.setRid(1l);
        TDiagnosis dg = tInterrogationService.acceptPatient(visit, treat);
        if(dg == null){
            return JsonResult.failMessage("accept patient failed");
        }else{
            return JsonResult.success(dg);
        }
    }

    @GetMapping(MODEL + "/cancelDiagnosis")
    @Function("test.InterrogationService.cancelDiagnosis")
    @ResponseBody
    public JsonResult<TDiagnosis> cancelDiagnosis() {
        TDiagnosis dg = new TDiagnosis();
        dg.setRid(1l);
        dg = tInterrogationService.cancelDiagnosis(dg, TDiagnosis.CANCEL);
        if(dg == null){
            return JsonResult.failMessage("cancel patient failed");
        }else{
            return JsonResult.success(dg);
        }
    }

    @GetMapping(MODEL + "/makeRecord")
    @Function("test.InterrogationService.makeRecord")
    @ResponseBody
    public JsonResult<TMedicalRecord> makeRecord() {
        TDiagnosis dg = new TDiagnosis();
        dg.setRid(1l);
        dg.setPatientid(1l);

        TMedicalRecord mr = new TMedicalRecord();
        mr.setRecordype(TMedicalRecord.DIAGNOSIS);
        mr.setFilename("test file");
        mr.setFiletype("test type");
        mr.setFilepath("test path");
        if(tInterrogationService.makeRecord(dg, mr) == null){
            return JsonResult.failMessage("cancel patient failed");
        }else{
            return JsonResult.success(mr);
        }
    }

    @GetMapping(MODEL + "/makeVisit")
    @Function("test.InterrogationService.makeVisit")
    @ResponseBody
    public JsonResult<TVisit> makeVisit() {
        User user = new User();
        user.setRid(1);
        user.setName("test");
        Condition cond = new Condition();
        cond.setSymptomFlags(new long[]{1});
        cond.setImMode(1);
        cond.setMedicineType(2);
        cond.setTreatmentType(3);
        cond.setTimeType(1);
        cond.setStartTime(new Date());
        cond.setEndTime( new Date(System.currentTimeMillis() + 60 * 60 * 1000));
        TVisit visit = tInterrogationService.makeVisit(user, cond);
        if(visit == null){
            return JsonResult.failMessage("cancel patient failed");
        }else{
            return JsonResult.success(visit);
        }
    }

}
