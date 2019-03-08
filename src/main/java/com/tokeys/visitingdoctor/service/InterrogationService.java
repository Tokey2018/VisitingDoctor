package com.tokeys.visitingdoctor.service;

import com.ibeetl.admin.core.service.BaseService;
import com.tokeys.visitingdoctor.dao.TDiagnosisDao;
import com.tokeys.visitingdoctor.dao.TMedicalRecordDao;
import com.tokeys.visitingdoctor.dao.TTreatmentDao;
import com.tokeys.visitingdoctor.dao.TVisitDao;
import com.tokeys.visitingdoctor.entity.TDiagnosis;
import com.tokeys.visitingdoctor.entity.TMedicalRecord;
import com.tokeys.visitingdoctor.entity.TTreatment;
import com.tokeys.visitingdoctor.entity.TVisit;
import com.tokeys.visitingdoctor.model.Condition;
import com.tokeys.visitingdoctor.model.Doctor;
import com.tokeys.visitingdoctor.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * TDiagnosis Service
 */

@Service
@Transactional
public class InterrogationService extends BaseService<TDiagnosis>{
    public static final int SYMPTOM_ELE_NUM = 1;


    ;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TDiagnosisDao tDiagnosisDao;

    @Autowired
    private TMedicalRecordDao tMedicalRecordDao;

    @Autowired
    private TVisitDao tVisitDao;

    @Autowired
    TTreatmentDao tTreatmentDao;

    public boolean init(){
        return true;
    }

    public TVisit makeVisit(User user, Condition cond){
        if(user == null || cond == null) {
            logger.info("parameter is invalid");
            return null;
        }
        TVisit visit = new TVisit();
        visit.setPatientid(user.getRid());
        visit.setPatientname(user.getName());
        visit.setSymptomflags1(cond.getSymptomFlags()[0]);
        visit.setImmode(cond.getImMode());
        visit.setMedicinetype(cond.getMedicineType());
        visit.setTreatmenttype(cond.getTreatmentType());
        visit.setTimetype(0);
        visit.setStarttime(cond.getStartTime());
        visit.setEndtime(cond.getEndTime());
        try {
            tVisitDao.insert(visit, true);
        }catch(Exception e){
            logger.error("insert visit error: {}", e.getMessage());
            return null;
        }
        return visit;
    }

    public List<Long> searchHistoryDoctor(TVisit visit){
        if(visit == null || visit.getPatientid() <= 0){
            logger.info("parameter is invalid");
            return null;
        }
        List<Long> ld = tDiagnosisDao.searchHistoryDoctor(visit);
        if(ld == null){
            logger.info("search history doctor error");
            return null;
        }
        if(ld.size() <= 0){
            logger.info("search no doctor");
        }
        return ld;
    }

    public TTreatment selectHistoryDoctor(Doctor doc){
        if(doc == null || doc.getRid() <= 0){
            logger.info("parameter is invalid");
            return null;
        }
        return getDoctorTreatment(doc.getRid());
    }

    public List<TTreatment> matchDoctor(TVisit visit){
        if(visit == null || visit.getPatientid() <= 0){
            logger.info("parameter is invalid");
            return null;
        }
        List<TTreatment> treats = tTreatmentDao.matchDoctor(visit);
        if(treats == null){
            logger.warn("match doctor error");
            return null;
        }
        return treats;
    }

    public TTreatment startWork(Doctor doc, Condition cond){
        logger.info("startWork start");
        if(doc == null || cond == null || doc.getRid() <= 0) {
            logger.info("parameter is invalid");
            return null;
        }
        TTreatment treatment = getDoctorTreatment(doc.getRid());
        if(treatment != null){
            treatment.setDoctorname(doc.getName());
            setTreatmentByCond(treatment, cond);
            try {
                tTreatmentDao.updateById(treatment);
            }catch(Exception e){
                logger.error("update treatment {} error: {}", treatment.getRid(), e.getMessage());
                return null;
            }
        }else{
            treatment = new TTreatment();
            treatment.setDoctorid(doc.getRid());
            treatment.setDoctorname(doc.getName());
            treatment.setVisittimes(0);
            treatment.setDiagtimes(0);
            setTreatmentByCond(treatment, cond);
            try {
                tTreatmentDao.insert(treatment, true);
            }catch(Exception e){
                logger.error("insert treatment for doctor {} error: {}", doc.getRid(), e.getMessage());
                return null;
            }
        }
        logger.info("startWork finished");
        return treatment;
    }

    private void setTreatmentByCond(TTreatment treatment, Condition cond){
        treatment.setSymptomflags1(cond.getSymptomFlags()[0]);
        treatment.setImmode(cond.getImMode());
        treatment.setMedicinetype(cond.getMedicineType());
        treatment.setTreatmenttype(cond.getTreatmentType());
        treatment.setTimetype(cond.getTimeType());
        treatment.setStarttime(cond.getStartTime());
        treatment.setEndtime(cond.getEndTime());
        treatment.setStatus(TTreatment.STATUS_NORMAL);
    }

    private TTreatment getDoctorTreatment(long did){
        TTreatment treatment = null;
        List<TTreatment> treats = tTreatmentDao.getByDoctorId(did);
        if(treats != null && treats.size() > 0)
            treatment = treats.get(0);
        return treatment;
    }

    public boolean stopWork(Doctor doc){
        logger.info("stopWork start");
        if(doc == null || doc.getRid() <= 0){
            logger.info("parameter is invalid");
            return false;
        }
        TTreatment treatment = getDoctorTreatment(doc.getRid());
        if(treatment != null){
            treatment.setStatus(TTreatment.STATUS_OFF);
            try {
                tTreatmentDao.updateById(treatment);
            }catch(Exception e){
                logger.error("update treatment {} for doctor {} error", treatment.getRid(), doc.getRid());
                return false;
            }
        }
        logger.info("stopWork finished");
        return true;
    }

    public boolean setTreatmentStatus(TTreatment treat){
        if(treat == null || treat.getDoctorid() <= 0){
            logger.info("parameter is invalid");
            return false;
        }
        TTreatment treatment = getDoctorTreatment(treat.getDoctorid());
        if(treatment == null){
            logger.info("search no treatment for doctor {}", treat.getDoctorid());
            return false;
        }
        treatment.setStatus(treat.getStatus());
        try {
            tTreatmentDao.updateById(treatment);
        }catch(Exception e){
            logger.error("update treatment of {} error: {}", treatment.getRid(), e.getMessage());
            return false;
        }
        return true;
    }

    public TDiagnosis acceptPatient(TVisit visit, TTreatment treat){
        if(visit==null || visit.getPatientid() <= 0 || treat == null || treat.getRid() <= 0){
            logger.info("parameter is invalid");
            return null;
        }
        treat = tTreatmentDao.single(treat.getRid());
        if(treat == null){
            logger.info("get treatment {} failed", treat.getRid());
            return null;
        }
        treat.setVisittimes(treat.getVisittimes() + 1);
        try{
            tTreatmentDao.updateById(treat);
        }catch(Exception e){
            logger.error("update visit times for doctor {} error: {}", treat.getDoctorid(), e.getMessage());
            return null;
        }

        TDiagnosis dg = new TDiagnosis();
        dg.setPatientid(visit.getPatientid());
        dg.setPatientname(visit.getPatientname());
        dg.setDoctorid(treat.getDoctorid());
        dg.setDoctorname(treat.getDoctorname());
        dg.setSymptomflags1(visit.getSymptomflags1());
        dg.setImmode(visit.getImmode());
        dg.setMedicinetype(visit.getMedicinetype());
        dg.setTreatmenttype(visit.getTreatmenttype());
        dg.setStarttime(visit.getStarttime());
        dg.setEndtime(visit.getEndtime());
        dg.setStatus(TDiagnosis.NORMAL);
        try {
            tDiagnosisDao.insert(dg, true);
        }catch(Exception e){
            logger.error("insert diagnosis error: {}", e.getMessage());
            return null;
        }
        return dg;
    }

    public boolean cancelDiagnosis(TDiagnosis diag, int flag){
        if(diag == null || diag.getRid() <= 0 || flag != TDiagnosis.PAUSE && flag != TDiagnosis.CANCEL){
            logger.info("parameter is invalid");
            return false;
        }
        diag = tDiagnosisDao.single(diag.getRid());
        if(diag == null){
            logger.info("get diagnosis {} failed", diag.getRid());
            return false;
        }
        diag.setStatus(flag);
        try {
            tDiagnosisDao.updateById(diag);
        }catch(Exception e){
            logger.error("update diagnosis status error: {}", e.getMessage());
            return false;
        }
        return true;
    }

    public TMedicalRecord makeRecord(TDiagnosis diag, TMedicalRecord mr){
        if(diag == null || diag.getRid() <= 0 || mr == null){
            logger.info("parameter is invalid");
            return null;
        }
        mr.setDiagnosisid(diag.getRid());
        try {
            tMedicalRecordDao.insert(mr, true);
        }catch(Exception e){
            logger.error("make record error: {}", e.getMessage());
            return null;
        }
        return mr;
    }
}