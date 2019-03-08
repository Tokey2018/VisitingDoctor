package com.tokeys.visitingdoctor.model;

import com.tokeys.visitingdoctor.service.InterrogationService;

import java.util.Date;

public class Condition {

    /**
     * 症状标志位。
     */
    long symptomFlags[] = new long[InterrogationService.SYMPTOM_ELE_NUM];

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

    private int timeType;
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

    public int getTimeType() {
        return timeType;
    }

    public void setTimeType(int timeType) {
        this.timeType = timeType;
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
