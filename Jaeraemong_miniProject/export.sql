--------------------------------------------------------
--  파일이 생성됨 - 수요일-9월-19-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence CAL_MEMO_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SCOTT"."CAL_MEMO_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence CAL_MEMO_SEQ1
--------------------------------------------------------

   CREATE SEQUENCE  "SCOTT"."CAL_MEMO_SEQ1"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence EX_CONTACT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SCOTT"."EX_CONTACT_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table CAL_MEMO
--------------------------------------------------------

  CREATE TABLE "SCOTT"."CAL_MEMO" 
   (	"CAL_DATE" DATE, 
	"CAL_TEXT" CHAR(1000 CHAR)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SCOTT.CAL_MEMO
SET DEFINE OFF;
Insert into SCOTT.CAL_MEMO (CAL_DATE,CAL_TEXT) values (to_date('18/01/01','RR/MM/DD'),'새로 쓴 일기...                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ');
--------------------------------------------------------
--  DDL for Index CAL_MEMO_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SCOTT"."CAL_MEMO_PK" ON "SCOTT"."CAL_MEMO" ("CAL_DATE") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Trigger CAL_MEMO_TRG1
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SCOTT"."CAL_MEMO_TRG1" 
BEFORE INSERT ON CAL_MEMO 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SCOTT"."CAL_MEMO_TRG1" ENABLE;
--------------------------------------------------------
--  DDL for Trigger CAL_MEMO_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SCOTT"."CAL_MEMO_TRG" 
BEFORE INSERT ON CAL_MEMO 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    NULL;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SCOTT"."CAL_MEMO_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table CAL_MEMO
--------------------------------------------------------

  ALTER TABLE "SCOTT"."CAL_MEMO" ADD CONSTRAINT "CAL_MEMO_PK" PRIMARY KEY ("CAL_DATE")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SCOTT"."CAL_MEMO" MODIFY ("CAL_TEXT" NOT NULL ENABLE);
  ALTER TABLE "SCOTT"."CAL_MEMO" MODIFY ("CAL_DATE" NOT NULL ENABLE);
