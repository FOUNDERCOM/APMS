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
  is '������';
comment on column C##APMS.APMS_ATTACHMENT.att_id
  is 'ID';
comment on column C##APMS.APMS_ATTACHMENT.att_name
  is '��������';
comment on column C##APMS.APMS_ATTACHMENT.att_type
  is '��������';
comment on column C##APMS.APMS_ATTACHMENT.att_data
  is '��������';
comment on column C##APMS.APMS_ATTACHMENT.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_ATTACHMENT.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_ATTACHMENT.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_ATTACHMENT.att_suffix
  is '������׺';
comment on column C##APMS.APMS_ATTACHMENT.att_size
  is '������Сbyte';
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
  is '�������';
comment on column C##APMS.APMS_AUX_APPRAISE.appr_id
  is 'ID';
comment on column C##APMS.APMS_AUX_APPRAISE.appr_year
  is '���';
comment on column C##APMS.APMS_AUX_APPRAISE.appr_level
  is '���۵ȼ�';
comment on column C##APMS.APMS_AUX_APPRAISE.appr_desc
  is '����';
comment on column C##APMS.APMS_AUX_APPRAISE.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_AUX_APPRAISE.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_AUX_APPRAISE.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_AUX_APPRAISE.aux_id
  is '����ID';
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
  is '�����������';
comment on column C##APMS.APMS_AUX_AWARD.award_id
  is 'ID';
comment on column C##APMS.APMS_AUX_AWARD.award_title
  is '��������';
comment on column C##APMS.APMS_AUX_AWARD.award_dept
  is '������λ';
comment on column C##APMS.APMS_AUX_AWARD.award_date
  is '����ʱ��';
comment on column C##APMS.APMS_AUX_AWARD.award_desc
  is '����˵��';
comment on column C##APMS.APMS_AUX_AWARD.award_has_cert
  is '�Ƿ���֤��';
comment on column C##APMS.APMS_AUX_AWARD.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_AUX_AWARD.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_AUX_AWARD.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_AUX_AWARD.aux_id
  is '����ID';
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
  is '��������';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_id
  is 'ID';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_school
  is 'ԺУ����';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_degree
  is 'ѧλ';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_major
  is 'רҵ';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_start
  is '��ʼʱ��';
comment on column C##APMS.APMS_AUX_EDUCATION.edu_end
  is '����ʱ��';
comment on column C##APMS.APMS_AUX_EDUCATION.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_AUX_EDUCATION.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_AUX_EDUCATION.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_AUX_EDUCATION.aux_id
  is '����ID';
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
  is '������ͥ���';
comment on column C##APMS.APMS_AUX_FAMILY.family_id
  is 'ID';
comment on column C##APMS.APMS_AUX_FAMILY.family_rel
  is '�뱾�˹�ϵ';
comment on column C##APMS.APMS_AUX_FAMILY.family_birthday
  is '����';
comment on column C##APMS.APMS_AUX_FAMILY.family_mobile
  is '�ֻ�';
comment on column C##APMS.APMS_AUX_FAMILY.family_identity_card
  is '���֤��';
comment on column C##APMS.APMS_AUX_FAMILY.family_dept
  is '������λ';
comment on column C##APMS.APMS_AUX_FAMILY.family_job
  is 'ְ��';
comment on column C##APMS.APMS_AUX_FAMILY.family_native_place
  is '�漮';
comment on column C##APMS.APMS_AUX_FAMILY.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_AUX_FAMILY.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_AUX_FAMILY.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_AUX_FAMILY.aux_id
  is '����ID';
comment on column C##APMS.APMS_AUX_FAMILY.family_name
  is '��ͥ��Ա����';
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
  is '������Ϣ';
comment on column C##APMS.APMS_AUX_INFO.aux_id
  is 'ID';
comment on column C##APMS.APMS_AUX_INFO.aux_name
  is '����';
comment on column C##APMS.APMS_AUX_INFO.aux_tel
  is '����';
comment on column C##APMS.APMS_AUX_INFO.aux_mobile
  is '�ֻ�';
comment on column C##APMS.APMS_AUX_INFO.aux_mail
  is '����';
comment on column C##APMS.APMS_AUX_INFO.aux_identity_card
  is '���֤��';
comment on column C##APMS.APMS_AUX_INFO.aux_sex
  is '�Ա𣬹����ֵ�SEX';
comment on column C##APMS.APMS_AUX_INFO.aux_birthday
  is '����';
comment on column C##APMS.APMS_AUX_INFO.aux_nation
  is '���壬�����ֵ�NATION';
comment on column C##APMS.APMS_AUX_INFO.aux_health
  is '����״���������ֵ�HEALTH';
comment on column C##APMS.APMS_AUX_INFO.aux_political_status
  is '������ò�������ֵ�POLITICAL_STATUS';
comment on column C##APMS.APMS_AUX_INFO.aux_education_degree
  is 'ѧλ�������ֵ�EDUCATION_DEGREE';
comment on column C##APMS.APMS_AUX_INFO.aux_institutions
  is '��ҵԺУ';
comment on column C##APMS.APMS_AUX_INFO.aux_major
  is '��ѧרҵ';
comment on column C##APMS.APMS_AUX_INFO.aux_native_place
  is '�漮';
comment on column C##APMS.APMS_AUX_INFO.aux_job
  is 'ְ��';
comment on column C##APMS.APMS_AUX_INFO.aux_join_date
  is '��ְʱ��';
comment on column C##APMS.APMS_AUX_INFO.aux_add_province
  is '��ס��ʡ';
comment on column C##APMS.APMS_AUX_INFO.aux_add_city
  is '��ס����';
comment on column C##APMS.APMS_AUX_INFO.aux_add_country
  is '��ס����';
comment on column C##APMS.APMS_AUX_INFO.aux_add_detail
  is '��ס����ַ';
comment on column C##APMS.APMS_AUX_INFO.aux_add_postcode
  is '��ס���ʱ�';
comment on column C##APMS.APMS_AUX_INFO.aux_photo
  is '��Ƭ';
comment on column C##APMS.APMS_AUX_INFO.aux_status
  is '���״̬�������ֵ�PROCESS_STATUS';
comment on column C##APMS.APMS_AUX_INFO.aux_salary
  is 'н��';
comment on column C##APMS.APMS_AUX_INFO.station_id
  is '�����ɳ���';
comment on column C##APMS.APMS_AUX_INFO.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_AUX_INFO.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_AUX_INFO.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_AUX_INFO.latest_approve_user
  is '��������';
comment on column C##APMS.APMS_AUX_INFO.latest_approve_date
  is '������ʱ��';
comment on column C##APMS.APMS_AUX_INFO.latest_approve_ip
  is '�����˿ͻ���IP';
comment on column C##APMS.APMS_AUX_INFO.is_enabled
  is '�Ƿ����ã�1���ã�����������';
comment on column C##APMS.APMS_AUX_INFO.aux_old_identity
  is '��ְǰ��ݣ������ֵ�OLD_IDENTITY';
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
  is '����������¼';
comment on column C##APMS.APMS_AUX_PUNISH.punish_id
  is 'ID';
comment on column C##APMS.APMS_AUX_PUNISH.punish_dept
  is '������λ';
comment on column C##APMS.APMS_AUX_PUNISH.punish_date
  is '����ʱ��';
comment on column C##APMS.APMS_AUX_PUNISH.punish_title
  is '��������';
comment on column C##APMS.APMS_AUX_PUNISH.punish_desc
  is '����˵��';
comment on column C##APMS.APMS_AUX_PUNISH.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_AUX_PUNISH.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_AUX_PUNISH.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_AUX_PUNISH.aux_id
  is '����ID';
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
  is '֤���ļ�';
comment on column C##APMS.APMS_AUX_STUFF_FILE.file_id
  is 'id';
comment on column C##APMS.APMS_AUX_STUFF_FILE.aux_id
  is '����id';
comment on column C##APMS.APMS_AUX_STUFF_FILE.file_data
  is '֤���ļ�';
comment on column C##APMS.APMS_AUX_STUFF_FILE.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_AUX_STUFF_FILE.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_AUX_STUFF_FILE.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_AUX_STUFF_FILE.file_name
  is '�ļ�����';
comment on column C##APMS.APMS_AUX_STUFF_FILE.att_id
  is 'Դ�ļ�ID�븽�������';
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
  is '����������¼';
comment on column C##APMS.APMS_AUX_WORK.work_id
  is 'ID';
comment on column C##APMS.APMS_AUX_WORK.work_dept
  is '������λ';
comment on column C##APMS.APMS_AUX_WORK.work_job
  is 'ְ��';
comment on column C##APMS.APMS_AUX_WORK.work_start
  is '��ʼʱ��';
comment on column C##APMS.APMS_AUX_WORK.work_end
  is '����ʱ��';
comment on column C##APMS.APMS_AUX_WORK.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_AUX_WORK.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_AUX_WORK.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_AUX_WORK.aux_id
  is '����ID';
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
  contact_name       VARCHAR2(16) default 'δ����',
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
  is '�־ֱ�';
comment on column C##APMS.APMS_DEPT_BUREAU.bureau_id
  is 'ID';
comment on column C##APMS.APMS_DEPT_BUREAU.bureau_name
  is '����';
comment on column C##APMS.APMS_DEPT_BUREAU.bureau_strength
  is '����';
comment on column C##APMS.APMS_DEPT_BUREAU.bureau_std_salary
  is '���������';
comment on column C##APMS.APMS_DEPT_BUREAU.contact_name
  is '��ϵ������';
comment on column C##APMS.APMS_DEPT_BUREAU.contact_tel
  is '��ϵ�˵�����';
comment on column C##APMS.APMS_DEPT_BUREAU.contact_mobile
  is '��ϵ�˵��ֻ�';
comment on column C##APMS.APMS_DEPT_BUREAU.contact_mail
  is '��ϵ�˵�����';
comment on column C##APMS.APMS_DEPT_BUREAU.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_DEPT_BUREAU.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_DEPT_BUREAU.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_DEPT_BUREAU.is_enabled
  is '�Ƿ����ã�1���ã�����������';
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
  contact_name       VARCHAR2(16) default 'δ����',
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
  is '�����ӱ�';
comment on column C##APMS.APMS_DEPT_STATION.station_id
  is 'ID';
comment on column C##APMS.APMS_DEPT_STATION.bureau_id
  is '�־�ID';
comment on column C##APMS.APMS_DEPT_STATION.station_name
  is '����';
comment on column C##APMS.APMS_DEPT_STATION.station_strength
  is '����';
comment on column C##APMS.APMS_DEPT_STATION.contact_name
  is '��ϵ������';
comment on column C##APMS.APMS_DEPT_STATION.contact_tel
  is '��ϵ�˵�����';
comment on column C##APMS.APMS_DEPT_STATION.contact_mobile
  is '��ϵ�˵��ֻ�';
comment on column C##APMS.APMS_DEPT_STATION.contact_mail
  is '��ϵ�˵�����';
comment on column C##APMS.APMS_DEPT_STATION.latest_update_user
  is '���������ID���û������';
comment on column C##APMS.APMS_DEPT_STATION.latest_update_date
  is '�������ʱ��';
comment on column C##APMS.APMS_DEPT_STATION.latest_update_ip
  is '�������IP';
comment on column C##APMS.APMS_DEPT_STATION.is_enabled
  is '�Ƿ����ã�1���ã�����������';
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
  is '�ֵ��';
comment on column C##APMS.APMS_SYS_DICT.dict_id
  is 'ID';
comment on column C##APMS.APMS_SYS_DICT.dict_parent_id
  is '�ϼ��ֵ䣬����ֱ�������������������Ǹ��ӽṹ��';
comment on column C##APMS.APMS_SYS_DICT.dict_nature
  is '����';
comment on column C##APMS.APMS_SYS_DICT.dict_code
  is '����';
comment on column C##APMS.APMS_SYS_DICT.dict_value
  is '��ʾֵ';
comment on column C##APMS.APMS_SYS_DICT.dict_desc
  is '������ֻ����������������Ҫ��д���';
comment on column C##APMS.APMS_SYS_DICT.is_enabled
  is '�Ƿ����ã�1���ã�����������';
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
  is '���ܲ˱�';
comment on column C##APMS.APMS_SYS_FUNC.func_id
  is 'ID';
comment on column C##APMS.APMS_SYS_FUNC.func_parent_id
  is '�ϼ�����';
comment on column C##APMS.APMS_SYS_FUNC.func_code
  is '���ܱ��룬ǰ̨��Ҫ����';
comment on column C##APMS.APMS_SYS_FUNC.func_name
  is '������Ҫ��ʾ������';
comment on column C##APMS.APMS_SYS_FUNC.func_type
  is '�������ͣ�ROOT(��),GROUP(����),MENU(�˵�),LINK(����),FUNC(���ܲ˵�),ACTION(��Ϊ)';
comment on column C##APMS.APMS_SYS_FUNC.func_desc
  is '����˵��';
comment on column C##APMS.APMS_SYS_FUNC.func_seq
  is '������ͬ����������';
comment on column C##APMS.APMS_SYS_FUNC.func_icon
  is '��������ʾʱ��ͬʱ��Ҫ��ʾ��ͼ���CSS��ʽ';
comment on column C##APMS.APMS_SYS_FUNC.func_is_built_in
  is '�Ƿ���Ƕ�����LINK(����),FUNC(���ܲ˵�)������ҳ���Ƿ�����Ƕ����������ҳ���ڣ�������ǣ��򵯳���';
comment on column C##APMS.APMS_SYS_FUNC.is_enabled
  is '�Ƿ����ã�1���ã�����������';
comment on column C##APMS.APMS_SYS_FUNC.is_visible
  is '�Ƿ�ɼ���1�ɼ�������������';
comment on column C##APMS.APMS_SYS_FUNC.func_url
  is '��������ӣ������ô�URL';
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
  is '�û���';
comment on column C##APMS.APMS_SYS_USER.user_id
  is 'ID';
comment on column C##APMS.APMS_SYS_USER.station_id
  is '������ID';
comment on column C##APMS.APMS_SYS_USER.user_name
  is '�û�����';
comment on column C##APMS.APMS_SYS_USER.user_tel
  is '��ϵ�绰';
comment on column C##APMS.APMS_SYS_USER.user_mobile
  is '�ֻ�����';
comment on column C##APMS.APMS_SYS_USER.user_mail
  is '����';
comment on column C##APMS.APMS_SYS_USER.user_account
  is '�û��ʺ�';
comment on column C##APMS.APMS_SYS_USER.user_pwd
  is '�û�����';
comment on column C##APMS.APMS_SYS_USER.user_photo
  is '�û�ͷ��';
comment on column C##APMS.APMS_SYS_USER.is_enabled
  is '�Ƿ����ã�1���ã�����������';
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
  is '�û����ܹ�����';
comment on column C##APMS.APMS_SYS_USER_FUNC.rel_id
  is 'ID';
comment on column C##APMS.APMS_SYS_USER_FUNC.user_id
  is '�û�ID';
comment on column C##APMS.APMS_SYS_USER_FUNC.func_id
  is '����ID';
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
