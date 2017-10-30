--------------------------------------------------------
-- Export file for user C##APMS@192.168.2.4:1521/ORCL --
-- Created by Jimmybly Lee on 2017/10/30, 22:18:41 -----
--------------------------------------------------------

set define off
spool TEST.log

prompt
prompt Creating table APMS_ATTACHMENT
prompt ==============================
prompt
create table C##APMS.APMS_ATTACHMENT
(
  att_id             VARCHAR2(36) not null,
  att_name           VARCHAR2(200),
  att_type           VARCHAR2(100),
  att_data           BLOB,
  latest_update_user VARCHAR2(36),
  latest_update_date VARCHAR2(30),
  latest_update_ip   VARCHAR2(40),
  att_suffix         VARCHAR2(10),
  att_size           NUMBER(9)
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_ATTACHMENT
  is '附件表';
comment on column C##APMS.APMS_ATTACHMENT.att_id
  is 'ID';
comment on column C##APMS.APMS_ATTACHMENT.att_name
  is '附件名称';
comment on column C##APMS.APMS_ATTACHMENT.att_type
  is '附件类型';
comment on column C##APMS.APMS_ATTACHMENT.att_data
  is '附件数据';
comment on column C##APMS.APMS_ATTACHMENT.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_ATTACHMENT.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_ATTACHMENT.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_ATTACHMENT.att_suffix
  is '附件后缀';
comment on column C##APMS.APMS_ATTACHMENT.att_size
  is '附件大小byte';
alter table C##APMS.APMS_ATTACHMENT
  add primary key (ATT_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_AUX_APPRAISE
prompt ================================
prompt
create table C##APMS.APMS_AUX_APPRAISE
(
  appr_id            VARCHAR2(36) not null,
  appr_year          VARCHAR2(4),
  appr_level         VARCHAR2(50),
  appr_desc          VARCHAR2(500),
  latest_update_user VARCHAR2(36),
  latest_update_date VARCHAR2(30),
  latest_update_ip   VARCHAR2(40),
  aux_id             VARCHAR2(36)
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_AUX_APPRAISE
  is '年度评价';
comment on column C##APMS.APMS_AUX_APPRAISE.appr_id
  is 'ID';
comment on column C##APMS.APMS_AUX_APPRAISE.appr_year
  is '年度';
comment on column C##APMS.APMS_AUX_APPRAISE.appr_level
  is '评价等级';
comment on column C##APMS.APMS_AUX_APPRAISE.appr_desc
  is '描述';
comment on column C##APMS.APMS_AUX_APPRAISE.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_AUX_APPRAISE.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_AUX_APPRAISE.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_AUX_APPRAISE.aux_id
  is '辅警ID';
alter table C##APMS.APMS_AUX_APPRAISE
  add primary key (APPR_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_AUX_AWARD
prompt =============================
prompt
create table C##APMS.APMS_AUX_AWARD
(
  award_id           VARCHAR2(36) not null,
  award_title        VARCHAR2(100),
  award_dept         VARCHAR2(100),
  award_date         VARCHAR2(30),
  award_desc         VARCHAR2(500),
  award_has_cert     VARCHAR2(1) default '0',
  latest_update_user VARCHAR2(36),
  latest_update_date VARCHAR2(30),
  latest_update_ip   VARCHAR2(40),
  aux_id             VARCHAR2(36)
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_AUX_AWARD
  is '辅警奖励情况';
comment on column C##APMS.APMS_AUX_AWARD.award_id
  is 'ID';
comment on column C##APMS.APMS_AUX_AWARD.award_title
  is '奖励名称';
comment on column C##APMS.APMS_AUX_AWARD.award_dept
  is '奖励单位';
comment on column C##APMS.APMS_AUX_AWARD.award_date
  is '奖励时间';
comment on column C##APMS.APMS_AUX_AWARD.award_desc
  is '奖励说明';
comment on column C##APMS.APMS_AUX_AWARD.award_has_cert
  is '是否有证书';
comment on column C##APMS.APMS_AUX_AWARD.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_AUX_AWARD.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_AUX_AWARD.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_AUX_AWARD.aux_id
  is '辅警ID';
alter table C##APMS.APMS_AUX_AWARD
  add primary key (AWARD_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_AUX_EDUCATION
prompt =================================
prompt
create table C##APMS.APMS_AUX_EDUCATION
(
  edu_id             VARCHAR2(36) not null,
  edu_school         VARCHAR2(100),
  edu_degree         VARCHAR2(50),
  edu_major          VARCHAR2(100),
  edu_start          VARCHAR2(30),
  edu_end            VARCHAR2(30),
  latest_update_user VARCHAR2(36),
  latest_update_date VARCHAR2(30),
  latest_update_ip   VARCHAR2(40),
  aux_id             VARCHAR2(36)
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_AUX_EDUCATION
  is '教育背景';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_id
  is 'ID';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_school
  is '院校名称';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_degree
  is '学位';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_major
  is '专业';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_start
  is '开始时间';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_end
  is '结束时间';
comment on column C##APMS.APMS_AUX_EDUCATION.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_AUX_EDUCATION.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_AUX_EDUCATION.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_AUX_EDUCATION.aux_id
  is '辅警ID';
alter table C##APMS.APMS_AUX_EDUCATION
  add primary key (EDU_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_AUX_FAMILY
prompt ==============================
prompt
create table C##APMS.APMS_AUX_FAMILY
(
  family_id            VARCHAR2(36) not null,
  family_rel           VARCHAR2(50),
  family_birthday      VARCHAR2(30),
  family_mobile        VARCHAR2(16),
  family_identity_card VARCHAR2(18),
  family_dept          VARCHAR2(100),
  family_job           VARCHAR2(100),
  family_native_place  VARCHAR2(300),
  latest_update_user   VARCHAR2(36),
  latest_update_date   VARCHAR2(30),
  latest_update_ip     VARCHAR2(40),
  aux_id               VARCHAR2(36),
  family_name          VARCHAR2(50)
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_AUX_FAMILY
  is '辅警家庭情况';
comment on column C##APMS.APMS_AUX_FAMILY.family_id
  is 'ID';
comment on column C##APMS.APMS_AUX_FAMILY.family_rel
  is '与本人关系';
comment on column C##APMS.APMS_AUX_FAMILY.family_birthday
  is '生日';
comment on column C##APMS.APMS_AUX_FAMILY.family_mobile
  is '手机';
comment on column C##APMS.APMS_AUX_FAMILY.family_identity_card
  is '身份证号';
comment on column C##APMS.APMS_AUX_FAMILY.family_dept
  is '工作单位';
comment on column C##APMS.APMS_AUX_FAMILY.family_job
  is '职务';
comment on column C##APMS.APMS_AUX_FAMILY.family_native_place
  is '祖籍';
comment on column C##APMS.APMS_AUX_FAMILY.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_AUX_FAMILY.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_AUX_FAMILY.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_AUX_FAMILY.aux_id
  is '辅警ID';
comment on column C##APMS.APMS_AUX_FAMILY.family_name
  is '家庭成员名称';
alter table C##APMS.APMS_AUX_FAMILY
  add primary key (FAMILY_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_AUX_INFO
prompt ============================
prompt
create table C##APMS.APMS_AUX_INFO
(
  aux_id               VARCHAR2(36) not null,
  aux_name             VARCHAR2(16),
  aux_tel              VARCHAR2(16),
  aux_mobile           VARCHAR2(16),
  aux_mail             VARCHAR2(64),
  aux_identity_card    VARCHAR2(18),
  aux_sex              VARCHAR2(50),
  aux_birthday         VARCHAR2(30),
  aux_nation           VARCHAR2(50),
  aux_health           VARCHAR2(50),
  aux_political_status VARCHAR2(50),
  aux_education_degree VARCHAR2(50),
  aux_institutions     VARCHAR2(100),
  aux_major            VARCHAR2(100),
  aux_native_place     VARCHAR2(200),
  aux_job              VARCHAR2(100),
  aux_join_date        VARCHAR2(30),
  aux_add_province     VARCHAR2(50),
  aux_add_city         VARCHAR2(50),
  aux_add_country      VARCHAR2(50),
  aux_add_detail       VARCHAR2(300),
  aux_add_postcode     VARCHAR2(6),
  aux_photo            CLOB,
  aux_status           VARCHAR2(50),
  aux_salary           NUMBER(5) default 0,
  station_id           VARCHAR2(36),
  latest_update_user   VARCHAR2(36),
  latest_update_date   VARCHAR2(30),
  latest_update_ip     VARCHAR2(40),
  latest_approve_user  VARCHAR2(36),
  latest_approve_date  VARCHAR2(30),
  latest_approve_ip    VARCHAR2(40),
  is_enabled           VARCHAR2(1) default '1',
  aux_old_identity     VARCHAR2(50) default '99'
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_AUX_INFO
  is '辅警信息';
comment on column C##APMS.APMS_AUX_INFO.aux_id
  is 'ID';
comment on column C##APMS.APMS_AUX_INFO.aux_name
  is '姓名';
comment on column C##APMS.APMS_AUX_INFO.aux_tel
  is '座机';
comment on column C##APMS.APMS_AUX_INFO.aux_mobile
  is '手机';
comment on column C##APMS.APMS_AUX_INFO.aux_mail
  is '邮箱';
comment on column C##APMS.APMS_AUX_INFO.aux_identity_card
  is '身份证号';
comment on column C##APMS.APMS_AUX_INFO.aux_sex
  is '性别，关联字典SEX';
comment on column C##APMS.APMS_AUX_INFO.aux_birthday
  is '生日';
comment on column C##APMS.APMS_AUX_INFO.aux_nation
  is '民族，关联字典NATION';
comment on column C##APMS.APMS_AUX_INFO.aux_health
  is '健康状况，关联字典HEALTH';
comment on column C##APMS.APMS_AUX_INFO.aux_political_status
  is '政治面貌，关联字典POLITICAL_STATUS';
comment on column C##APMS.APMS_AUX_INFO.aux_education_degree
  is '学位，关联字典EDUCATION_DEGREE';
comment on column C##APMS.APMS_AUX_INFO.aux_institutions
  is '毕业院校';
comment on column C##APMS.APMS_AUX_INFO.aux_major
  is '所学专业';
comment on column C##APMS.APMS_AUX_INFO.aux_native_place
  is '祖籍';
comment on column C##APMS.APMS_AUX_INFO.aux_job
  is '职务';
comment on column C##APMS.APMS_AUX_INFO.aux_join_date
  is '入职时间';
comment on column C##APMS.APMS_AUX_INFO.aux_add_province
  is '现住地省';
comment on column C##APMS.APMS_AUX_INFO.aux_add_city
  is '现住地市';
comment on column C##APMS.APMS_AUX_INFO.aux_add_country
  is '现住地县';
comment on column C##APMS.APMS_AUX_INFO.aux_add_detail
  is '现住地详址';
comment on column C##APMS.APMS_AUX_INFO.aux_add_postcode
  is '现住地邮编';
comment on column C##APMS.APMS_AUX_INFO.aux_photo
  is '照片';
comment on column C##APMS.APMS_AUX_INFO.aux_status
  is '审核状态，关联字典PROCESS_STATUS';
comment on column C##APMS.APMS_AUX_INFO.aux_salary
  is '薪资';
comment on column C##APMS.APMS_AUX_INFO.station_id
  is '所属派出所';
comment on column C##APMS.APMS_AUX_INFO.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_AUX_INFO.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_AUX_INFO.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_AUX_INFO.latest_approve_user
  is '最近审核人';
comment on column C##APMS.APMS_AUX_INFO.latest_approve_date
  is '最近审核时间';
comment on column C##APMS.APMS_AUX_INFO.latest_approve_ip
  is '最近审核客户端IP';
comment on column C##APMS.APMS_AUX_INFO.is_enabled
  is '是否启用，1启用，其他，禁用';
comment on column C##APMS.APMS_AUX_INFO.aux_old_identity
  is '入职前身份，关联字典OLD_IDENTITY';
alter table C##APMS.APMS_AUX_INFO
  add primary key (AUX_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_AUX_PUNISH
prompt ==============================
prompt
create table C##APMS.APMS_AUX_PUNISH
(
  punish_id          VARCHAR2(36) not null,
  punish_dept        VARCHAR2(100),
  punish_date        VARCHAR2(30),
  punish_title       VARCHAR2(100),
  punish_desc        VARCHAR2(500),
  latest_update_user VARCHAR2(36),
  latest_update_date VARCHAR2(30),
  latest_update_ip   VARCHAR2(40),
  aux_id             VARCHAR2(36)
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_AUX_PUNISH
  is '辅警处罚记录';
comment on column C##APMS.APMS_AUX_PUNISH.punish_id
  is 'ID';
comment on column C##APMS.APMS_AUX_PUNISH.punish_dept
  is '处罚单位';
comment on column C##APMS.APMS_AUX_PUNISH.punish_date
  is '处罚时间';
comment on column C##APMS.APMS_AUX_PUNISH.punish_title
  is '处罚名称';
comment on column C##APMS.APMS_AUX_PUNISH.punish_desc
  is '处罚说明';
comment on column C##APMS.APMS_AUX_PUNISH.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_AUX_PUNISH.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_AUX_PUNISH.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_AUX_PUNISH.aux_id
  is '辅警ID';
alter table C##APMS.APMS_AUX_PUNISH
  add primary key (PUNISH_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_AUX_STUFF_FILE
prompt ==================================
prompt
create table C##APMS.APMS_AUX_STUFF_FILE
(
  file_id            VARCHAR2(36) not null,
  aux_id             VARCHAR2(36),
  file_data          CLOB,
  latest_update_user VARCHAR2(36),
  latest_update_date VARCHAR2(30),
  latest_update_ip   VARCHAR2(40),
  file_name          VARCHAR2(100),
  att_id             VARCHAR2(36)
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_AUX_STUFF_FILE
  is '证明文件';
comment on column C##APMS.APMS_AUX_STUFF_FILE.file_id
  is 'id';
comment on column C##APMS.APMS_AUX_STUFF_FILE.aux_id
  is '辅警id';
comment on column C##APMS.APMS_AUX_STUFF_FILE.file_data
  is '证明文件';
comment on column C##APMS.APMS_AUX_STUFF_FILE.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_AUX_STUFF_FILE.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_AUX_STUFF_FILE.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_AUX_STUFF_FILE.file_name
  is '文件名称';
comment on column C##APMS.APMS_AUX_STUFF_FILE.att_id
  is '源文件ID与附件表关联';
alter table C##APMS.APMS_AUX_STUFF_FILE
  add primary key (FILE_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_AUX_WORK
prompt ============================
prompt
create table C##APMS.APMS_AUX_WORK
(
  work_id            VARCHAR2(36) not null,
  work_dept          VARCHAR2(100),
  work_job           VARCHAR2(100),
  work_start         VARCHAR2(30),
  work_end           VARCHAR2(30),
  latest_update_user VARCHAR2(36),
  latest_update_date VARCHAR2(30),
  latest_update_ip   VARCHAR2(40),
  aux_id             VARCHAR2(36)
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_AUX_WORK
  is '辅警工作记录';
comment on column C##APMS.APMS_AUX_WORK.work_id
  is 'ID';
comment on column C##APMS.APMS_AUX_WORK.work_dept
  is '工作单位';
comment on column C##APMS.APMS_AUX_WORK.work_job
  is '职务';
comment on column C##APMS.APMS_AUX_WORK.work_start
  is '开始时间';
comment on column C##APMS.APMS_AUX_WORK.work_end
  is '结束时间';
comment on column C##APMS.APMS_AUX_WORK.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_AUX_WORK.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_AUX_WORK.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_AUX_WORK.aux_id
  is '辅警ID';
alter table C##APMS.APMS_AUX_WORK
  add primary key (WORK_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_DEPT_BUREAU
prompt ===============================
prompt
create table C##APMS.APMS_DEPT_BUREAU
(
  bureau_id          VARCHAR2(36) not null,
  bureau_name        VARCHAR2(64),
  bureau_strength    NUMBER(6) default 1000,
  bureau_std_salary  NUMBER(5) default 0,
  contact_name       VARCHAR2(16) default '未设置',
  contact_tel        VARCHAR2(16) default '0857-0000000',
  contact_mobile     VARCHAR2(16) default '13000000000',
  contact_mail       VARCHAR2(64) default '13000000000@qq.com',
  latest_update_user VARCHAR2(36) default -2,
  latest_update_date VARCHAR2(30) default '2000-01-01 00:00:00',
  latest_update_ip   VARCHAR2(40) default '127.0.0.1',
  is_enabled         VARCHAR2(1) default '1'
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_DEPT_BUREAU
  is '分局表';
comment on column C##APMS.APMS_DEPT_BUREAU.bureau_id
  is 'ID';
comment on column C##APMS.APMS_DEPT_BUREAU.bureau_name
  is '名称';
comment on column C##APMS.APMS_DEPT_BUREAU.bureau_strength
  is '编制';
comment on column C##APMS.APMS_DEPT_BUREAU.bureau_std_salary
  is '辅警额定工资';
comment on column C##APMS.APMS_DEPT_BUREAU.contact_name
  is '联系人姓名';
comment on column C##APMS.APMS_DEPT_BUREAU.contact_tel
  is '联系人的座机';
comment on column C##APMS.APMS_DEPT_BUREAU.contact_mobile
  is '联系人的手机';
comment on column C##APMS.APMS_DEPT_BUREAU.contact_mail
  is '联系人的邮箱';
comment on column C##APMS.APMS_DEPT_BUREAU.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_DEPT_BUREAU.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_DEPT_BUREAU.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_DEPT_BUREAU.is_enabled
  is '是否启用，1启用，其他，禁用';
alter table C##APMS.APMS_DEPT_BUREAU
  add primary key (BUREAU_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_DEPT_STATION
prompt ================================
prompt
create table C##APMS.APMS_DEPT_STATION
(
  station_id         VARCHAR2(36) not null,
  bureau_id          VARCHAR2(36),
  station_name       VARCHAR2(64),
  station_strength   NUMBER(6) default 0,
  contact_name       VARCHAR2(16) default '未设置',
  contact_tel        VARCHAR2(16) default '0857-0000000',
  contact_mobile     VARCHAR2(16) default '13000000000',
  contact_mail       VARCHAR2(64) default '13000000000@qq.com',
  latest_update_user NUMBER(9) default -2,
  latest_update_date VARCHAR2(30) default '2000-01-01 00:00:00',
  latest_update_ip   VARCHAR2(40) default '127.0.0.1',
  is_enabled         VARCHAR2(1) default '1'
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_DEPT_STATION
  is '科所队表';
comment on column C##APMS.APMS_DEPT_STATION.station_id
  is 'ID';
comment on column C##APMS.APMS_DEPT_STATION.bureau_id
  is '分局ID';
comment on column C##APMS.APMS_DEPT_STATION.station_name
  is '名称';
comment on column C##APMS.APMS_DEPT_STATION.station_strength
  is '编制';
comment on column C##APMS.APMS_DEPT_STATION.contact_name
  is '联系人姓名';
comment on column C##APMS.APMS_DEPT_STATION.contact_tel
  is '联系人的座机';
comment on column C##APMS.APMS_DEPT_STATION.contact_mobile
  is '联系人的手机';
comment on column C##APMS.APMS_DEPT_STATION.contact_mail
  is '联系人的邮箱';
comment on column C##APMS.APMS_DEPT_STATION.latest_update_user
  is '最近更新人ID与用户表关联';
comment on column C##APMS.APMS_DEPT_STATION.latest_update_date
  is '最近更新时间';
comment on column C##APMS.APMS_DEPT_STATION.latest_update_ip
  is '最近更新IP';
comment on column C##APMS.APMS_DEPT_STATION.is_enabled
  is '是否启用，1启用，其他，禁用';
alter table C##APMS.APMS_DEPT_STATION
  add primary key (STATION_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_SYS_DICT
prompt ============================
prompt
create table C##APMS.APMS_SYS_DICT
(
  dict_id        VARCHAR2(36) not null,
  dict_parent_id VARCHAR2(36),
  dict_nature    VARCHAR2(30),
  dict_code      VARCHAR2(50),
  dict_value     VARCHAR2(100),
  dict_desc      VARCHAR2(50),
  is_enabled     VARCHAR2(1) default 1
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_SYS_DICT
  is '字典表';
comment on column C##APMS.APMS_SYS_DICT.dict_id
  is 'ID';
comment on column C##APMS.APMS_SYS_DICT.dict_parent_id
  is '上级字典，往往直接是类型描述，除非是父子结构的';
comment on column C##APMS.APMS_SYS_DICT.dict_nature
  is '类型';
comment on column C##APMS.APMS_SYS_DICT.dict_code
  is '编码';
comment on column C##APMS.APMS_SYS_DICT.dict_value
  is '显示值';
comment on column C##APMS.APMS_SYS_DICT.dict_desc
  is '描述，只有类型描述的项需要填写这个';
comment on column C##APMS.APMS_SYS_DICT.is_enabled
  is '是否启用，1启用，其他，禁用';
alter table C##APMS.APMS_SYS_DICT
  add primary key (DICT_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_SYS_FUNC
prompt ============================
prompt
create table C##APMS.APMS_SYS_FUNC
(
  func_id          VARCHAR2(36) not null,
  func_parent_id   VARCHAR2(36),
  func_code        VARCHAR2(30),
  func_name        VARCHAR2(20),
  func_type        VARCHAR2(10),
  func_desc        VARCHAR2(100),
  func_seq         NUMBER(2),
  func_icon        VARCHAR2(30),
  func_is_built_in VARCHAR2(1) default 1,
  is_enabled       VARCHAR2(1) default 1,
  is_visible       VARCHAR2(1) default 1,
  func_url         VARCHAR2(100)
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_SYS_FUNC
  is '功能菜表';
comment on column C##APMS.APMS_SYS_FUNC.func_id
  is 'ID';
comment on column C##APMS.APMS_SYS_FUNC.func_parent_id
  is '上级功能';
comment on column C##APMS.APMS_SYS_FUNC.func_code
  is '功能编码，前台需要解析';
comment on column C##APMS.APMS_SYS_FUNC.func_name
  is '功能需要显示的名称';
comment on column C##APMS.APMS_SYS_FUNC.func_type
  is '功能类型，ROOT(根),GROUP(分组),MENU(菜单),LINK(链接),FUNC(功能菜单),ACTION(行为)';
comment on column C##APMS.APMS_SYS_FUNC.func_desc
  is '功能说明';
comment on column C##APMS.APMS_SYS_FUNC.func_seq
  is '功能在同级排序的序号';
comment on column C##APMS.APMS_SYS_FUNC.func_icon
  is '功能在显示时，同时需要显示的图标的CSS样式';
comment on column C##APMS.APMS_SYS_FUNC.func_is_built_in
  is '是否内嵌，针对LINK(链接),FUNC(功能菜单)关联的页面是否是内嵌到屏主功能页面内，如果不是，则弹出。';
comment on column C##APMS.APMS_SYS_FUNC.is_enabled
  is '是否启用，1启用，其他，禁用';
comment on column C##APMS.APMS_SYS_FUNC.is_visible
  is '是否可见，1可见，其他，禁用';
comment on column C##APMS.APMS_SYS_FUNC.func_url
  is '如果是连接，则设置此URL';
alter table C##APMS.APMS_SYS_FUNC
  add primary key (FUNC_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_SYS_USER
prompt ============================
prompt
create table C##APMS.APMS_SYS_USER
(
  user_id      VARCHAR2(36) not null,
  station_id   VARCHAR2(36) default 0,
  user_name    VARCHAR2(16) default ' ',
  user_tel     VARCHAR2(16) default ' ',
  user_mobile  VARCHAR2(16) default ' ',
  user_mail    VARCHAR2(64),
  user_account VARCHAR2(30),
  user_pwd     VARCHAR2(64),
  user_photo   CLOB default ' ',
  is_enabled   VARCHAR2(1) default 1
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_SYS_USER
  is '用户表';
comment on column C##APMS.APMS_SYS_USER.user_id
  is 'ID';
comment on column C##APMS.APMS_SYS_USER.station_id
  is '科所队ID';
comment on column C##APMS.APMS_SYS_USER.user_name
  is '用户名称';
comment on column C##APMS.APMS_SYS_USER.user_tel
  is '联系电话';
comment on column C##APMS.APMS_SYS_USER.user_mobile
  is '手机号码';
comment on column C##APMS.APMS_SYS_USER.user_mail
  is '邮箱';
comment on column C##APMS.APMS_SYS_USER.user_account
  is '用户帐号';
comment on column C##APMS.APMS_SYS_USER.user_pwd
  is '用户密码';
comment on column C##APMS.APMS_SYS_USER.user_photo
  is '用户头像';
comment on column C##APMS.APMS_SYS_USER.is_enabled
  is '是否启用，1启用，其他，禁用';
alter table C##APMS.APMS_SYS_USER
  add primary key (USER_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table APMS_SYS_USER_FUNC
prompt =================================
prompt
create table C##APMS.APMS_SYS_USER_FUNC
(
  rel_id  VARCHAR2(36) not null,
  user_id VARCHAR2(36) not null,
  func_id VARCHAR2(36) not null
)
tablespace APMS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on table C##APMS.APMS_SYS_USER_FUNC
  is '用户功能关联表';
comment on column C##APMS.APMS_SYS_USER_FUNC.rel_id
  is 'ID';
comment on column C##APMS.APMS_SYS_USER_FUNC.user_id
  is '用户ID';
comment on column C##APMS.APMS_SYS_USER_FUNC.func_id
  is '功能ID';
alter table C##APMS.APMS_SYS_USER_FUNC
  add primary key (REL_ID)
  using index 
  tablespace APMS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


spool off
