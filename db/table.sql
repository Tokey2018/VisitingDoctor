drop table if exists t_visit;
create table t_visit(
  rid           bigint NOT NULL AUTO_INCREMENT,
  patientId     bigint NOT NULL,
  patientName   varchar(30),
  symptomFlags1 bigint NOT NULL DEFAULT 0,
  imMode        int NOT NULL DEFAULT 0,
  medicineType  int NOT NULL DEFAULT 0,
  treatmentType int NOT NULL DEFAULT 0,
  startTime     datetime,
  endTime       datetime,
  timeType      int NOT NULL DEFAULT 0,
  del_flag      int NOT NULL DEFAULT 0,
  createTime    datetime NOT NULL DEFAULT now(),
  primary key (rid)
);

drop table if exists t_treatment;
create table t_treatment(
  rid           bigint NOT NULL AUTO_INCREMENT,
  doctorId      bigint NOT NULL,
  doctorName    varchar(30),
  symptomFlags1 bigint NOT NULL DEFAULT 0,
  imMode        int NOT NULL DEFAULT 0,
  medicineType  int NOT NULL DEFAULT 0,
  treatmentType int NOT NULL DEFAULT 0,
  startTime     datetime,
  endTime       datetime,
  timeType      int NOT NULL DEFAULT 0,
  status        int NOT NULL DEFAULT 0,
  visitTimes    int NOT NULL DEFAULT 0,
  diagTimes     int NOT NULL DEFAULT 0,
  del_flag      int NOT NULL DEFAULT 0,
  createTime    datetime NOT NULL DEFAULT now(),
  primary key (rid)
);

drop table if exists t_diagnosis;
create table t_diagnosis(
  rid           bigint NOT NULL AUTO_INCREMENT,
  patientId     bigint NOT NULL,
  patientName   varchar(30),
  doctorId      bigint NOT NULL,
  doctorName    varchar(30),
  symptomFlags1 bigint NOT NULL DEFAULT 0,
  imMode        int NOT NULL DEFAULT 0,
  medicineType  int NOT NULL DEFAULT 0,
  treatmentType int NOT NULL DEFAULT 0,
  startTime     datetime,
  endTime       datetime,
  status        int NOT NULL DEFAULT 0,
  del_flag      int NOT NULL DEFAULT 0,
  createTime    datetime NOT NULL DEFAULT now(),
  primary key (rid)
);

drop table if exists t_medical_record;
create table t_medical_record(
  rid           bigint NOT NULL AUTO_INCREMENT,
  diagnosisId   bigint NOT NULL,
  recordype     int NOT NULL DEFAULT 0,
  fileName      varchar(100),
  fileType      varchar(50),
  filePath      varchar(300),
  del_flag      int NOT NULL DEFAULT 0,
  createTime    datetime NOT NULL DEFAULT now(),
  primary key (rid)
);