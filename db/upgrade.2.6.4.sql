ALTER TABLE "C##APMS"."APMS_AUX_INFO"
ADD ( "AUX_SALARY_S_SW" NUMBER(5) NULL  )
ADD ( "AUX_SALARY_S_SY" NUMBER(5) NULL  )
ADD ( "AUX_SALARY_S_GS" NUMBER(5) NULL  )
ADD ( "AUX_SALARY_S_YW" NUMBER(5) NULL  );

COMMENT ON COLUMN "C##APMS"."APMS_AUX_INFO"."AUX_SALARY_S_SW" IS '失业保险';

COMMENT ON COLUMN "C##APMS"."APMS_AUX_INFO"."AUX_SALARY_S_SY" IS '生育保险';

COMMENT ON COLUMN "C##APMS"."APMS_AUX_INFO"."AUX_SALARY_S_GS" IS '工伤保险';

COMMENT ON COLUMN "C##APMS"."APMS_AUX_INFO"."AUX_SALARY_S_YW" IS '意外伤亡';