---------------  组织机构  -----------------

-------------------
--    分局表     --
-------------------
-- CREATE TABLE
CREATE TABLE APMS_DEPT_BUREAU
(
  BUREAU_ID          NUMBER(9) NOT NULL,
  BUREAU_NAME        VARCHAR2(64) NOT NULL,
  BUREAU_STRENGTH    NUMBER(6) DEFAULT 0 NOT NULL,
  BUREAU_STD_SALARY  NUMBER(5) NOT NULL,
  CONTACT_NAME       VARCHAR2(16) NOT NULL,
  CONTACT_TEL        VARCHAR2(16) NOT NULL,
  CONTACT_MOBILE     VARCHAR2(16) NOT NULL,
  CONTACT_MAIL       VARCHAR2(64) NOT NULL,
  LATEST_UPDATE_USER NUMBER(9) NOT NULL,
  LATEST_UPDATE_DATE VARCHAR2(30) NOT NULL,
  LATEST_UPDATE_IP   VARCHAR2(16) NOT NULL,
  IS_ENABLED         VARCHAR2(1) NOT NULL
)
TABLESPACE APMS
  STORAGE
  (
    INITIAL 64K
    MINEXTENTS 1
    MAXEXTENTS UNLIMITED
  );
-- CREATE/RECREATE PRIMARY, UNIQUE AND FOREIGN KEY CONSTRAINTS
ALTER TABLE APMS_DEPT_BUREAU
  ADD CONSTRAINT APMS_DEPT_BUREAU_KEY PRIMARY KEY (BUREAU_ID);
-- ADD COMMENTS TO THE TABLE
COMMENT ON TABLE APMS_DEPT_BUREAU
  IS '分局表';
-- ADD COMMENTS TO THE COLUMNS
COMMENT ON COLUMN APMS_DEPT_BUREAU.BUREAU_ID
  IS 'ID';
COMMENT ON COLUMN APMS_DEPT_BUREAU.BUREAU_NAME
  IS '名称';
COMMENT ON COLUMN APMS_DEPT_BUREAU.BUREAU_STRENGTH
  IS '编制';
COMMENT ON COLUMN APMS_DEPT_BUREAU.BUREAU_STD_SALARY
  IS '辅警额定工资';
COMMENT ON COLUMN APMS_DEPT_BUREAU.CONTACT_NAME
  IS '联系人姓名';
COMMENT ON COLUMN APMS_DEPT_BUREAU.CONTACT_TEL
  IS '联系人的座机';
COMMENT ON COLUMN APMS_DEPT_BUREAU.CONTACT_MOBILE
  IS '联系人的手机';
COMMENT ON COLUMN APMS_DEPT_BUREAU.CONTACT_MAIL
  IS '联系人的邮箱';
COMMENT ON COLUMN APMS_DEPT_BUREAU.LATEST_UPDATE_USER
  IS '最近更新人ID与用户表关联';
COMMENT ON COLUMN APMS_DEPT_BUREAU.LATEST_UPDATE_DATE
  IS '最近更新时间';
COMMENT ON COLUMN APMS_DEPT_BUREAU.LATEST_UPDATE_IP
  IS '最近更新IP';
COMMENT ON COLUMN APMS_DEPT_BUREAU.IS_ENABLED
  IS '是否启用，1启用，其他，禁用';


-------------------
--   科所队表    --
-------------------
-- CREATE TABLE
CREATE TABLE APMS_DEPT_STATION
(
  STATION_ID         NUMBER(9) NOT NULL,
  BUREAU_ID          NUMBER(9) NOT NULL,
  STATION_NAME       VARCHAR2(64) NOT NULL,
  STATION_STRENGTH   NUMBER(6) DEFAULT 0 NOT NULL,
  CONTACT_NAME       VARCHAR2(16) NOT NULL,
  CONTACT_TEL        VARCHAR2(16) NOT NULL,
  CONTACT_MOBILE     VARCHAR2(16) NOT NULL,
  CONTACT_MAIL       VARCHAR2(64) NOT NULL,
  LATEST_UPDATE_USER NUMBER(9) NOT NULL,
  LATEST_UPDATE_DATE VARCHAR2(30) NOT NULL,
  LATEST_UPDATE_IP   VARCHAR2(16) NOT NULL,
  IS_ENABLED         VARCHAR2(1) NOT NULL
)
TABLESPACE APMS
  STORAGE
  (
    INITIAL 64K
    MINEXTENTS 1
    MAXEXTENTS UNLIMITED
  );
-- CREATE/RECREATE PRIMARY, UNIQUE AND FOREIGN KEY CONSTRAINTS
ALTER TABLE APMS_DEPT_STATION
  ADD CONSTRAINT APMS_DEPT_STATION_KEY PRIMARY KEY (STATION_ID);
-- ADD COMMENTS TO THE TABLE
COMMENT ON TABLE APMS_DEPT_STATION
  IS '科所队表';
-- ADD COMMENTS TO THE COLUMNS
COMMENT ON COLUMN APMS_DEPT_STATION.STATION_ID
  IS 'ID';
COMMENT ON COLUMN APMS_DEPT_STATION.BUREAU_ID
  IS '分局ID';
COMMENT ON COLUMN APMS_DEPT_STATION.STATION_NAME
  IS '名称';
COMMENT ON COLUMN APMS_DEPT_STATION.STATION_STRENGTH
  IS '编制';
COMMENT ON COLUMN APMS_DEPT_STATION.CONTACT_NAME
  IS '联系人姓名';
COMMENT ON COLUMN APMS_DEPT_STATION.CONTACT_TEL
  IS '联系人的座机';
COMMENT ON COLUMN APMS_DEPT_STATION.CONTACT_MOBILE
  IS '联系人的手机';
COMMENT ON COLUMN APMS_DEPT_STATION.CONTACT_MAIL
  IS '联系人的邮箱';
COMMENT ON COLUMN APMS_DEPT_STATION.LATEST_UPDATE_USER
  IS '最近更新人ID与用户表关联';
COMMENT ON COLUMN APMS_DEPT_STATION.LATEST_UPDATE_DATE
  IS '最近更新时间';
COMMENT ON COLUMN APMS_DEPT_STATION.LATEST_UPDATE_IP
  IS '最近更新IP';
COMMENT ON COLUMN APMS_DEPT_STATION.IS_ENABLED
  IS '是否启用，1启用，其他，禁用';


---------------  组织机构  -----------------