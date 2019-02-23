package com.tokeys.visitingdoctor.service;

import com.ibeetl.admin.core.service.BaseService;
import com.ibeetl.admin.core.util.PlatformException;
import com.tokeys.visitingdoctor.dao.TDiagnosisDao;
import com.tokeys.visitingdoctor.dao.TMedicalRecordDao;
import com.tokeys.visitingdoctor.dao.TTreatmentDao;
import com.tokeys.visitingdoctor.dao.TVisitDao;
import com.tokeys.visitingdoctor.entity.TDiagnosis;
import com.tokeys.visitingdoctor.entity.TMedicalRecord;
import com.tokeys.visitingdoctor.entity.TTreatment;
import com.tokeys.visitingdoctor.entity.TVisit;
import org.beetl.sql.core.engine.PageQuery;
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

    public class User{
        private long rid;
        private String name;
        private int sex;
        private Date birthday;

        public long getRid() {
            return rid;
        }

        public void setRid(long rid) {
            this.rid = rid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    };

    public class Condition{

        /**
         * 症状标志位。
         */
        long symptomFlags[] = new long[SYMPTOM_ELE_NUM];

        /**
         * IM沟通方式，包括视频、图文+语音。
         */
        private int imMode;
        /**
         * 医疗体系，包括传统中医、现代医疗。
         */
        private int medicineType;
        /**
         * 会诊方式，包括1对1，1对2。
         */
        private int treatmentType;
        private Date startTime;
        private Date endTime;

        public int getImMode() {
            return imMode;
        }

        public void setImMode(int imMode) {
            this.imMode = imMode;
        }

        public int getMedicineType() {
            return medicineType;
        }

        public void setMedicineType(int medicineType) {
            this.medicineType = medicineType;
        }

        public int getTreatmentType() {
            return treatmentType;
        }

        public void setTreatmentType(int treatmentType) {
            this.treatmentType = treatmentType;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public long[] getSymptomFlags() {
            return symptomFlags;
        }

        public void setSymptomFlags(long[] symptomFlags) {
            this.symptomFlags = symptomFlags;
        }
    }

    public class Doctor{
        private long rid;
        private String name;
        private int degree;

        public long getRid() {
            return rid;
        }

        public void setRid(long rid) {
            this.rid = rid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getDegree() {
            return degree;
        }

        public void setDegree(int degree) {
            this.degree = degree;
        }
    };

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
        if(user == null || cond == null)
            return null;
        TVisit visit = new TVisit();
        visit.setPatientid(user.getRid());
        visit.setPatientname(user.getName());
        visit.setSymptomflags1(cond.getSymptomFlags()[0]);
        visit.setImmode(cond.getImMode());
        visit.setMedicinetype(cond.getMedicineType());
        visit.setTreatmenttype(cond.getTreatmentType());
        visit.setStarttime(cond.getStartTime());
        visit.setEndtime(cond.getEndTime());
        tVisitDao.insert(visit);
        return visit;
    }

    public List<Doctor> searchHistoryDoctor(TVisit visit){
        return null;
    }

    public TTreatment selectHistoryDoctor(TVisit visit, Doctor doc){
        return null;
    }

    public List<TTreatment> matchDoctor(TVisit visit){
        return null;
    }

    public TTreatment startWork(Doctor doc, Condition cond){
        TTreatment treatment = new TTreatment();
        /* 获取treatement，没有则创建一个 */

        treatment.setDoctorid(doc.getRid());
        treatment.setDoctorname(doc.getName());
        treatment.setSymptomflags1(cond.getSymptomFlags()[0]);
        treatment.setImmode(cond.getImMode());
        treatment.setMedicinetype(cond.getMedicineType());
        treatment.setTreatmenttype(cond.getTreatmentType());
        treatment.setStarttime(cond.getStartTime());
        treatment.setEndtime(cond.getEndTime());
        treatment.setStatus(TTreatment.STATUS_NORMAL);
        tTreatmentDao.insert(treatment);
        return treatment;
    }

    public boolean stopWork(Doctor doc){
        return true;
    }

    public boolean setTreatmentStatus(TTreatment treat){
        return true;
    }

    public TDiagnosis acceptPatient(TVisit visit, TTreatment treat){
        return null;
    }

    public boolean cancelDiagnosis(TDiagnosis diag, int flag){
        return true;
    }

    public boolean makeRecord(TDiagnosis diag, TMedicalRecord mr){
        return true;
    }
}