--------------------------------------------------------
--  파일이 생성됨 - 목요일-9월-20-2018   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table EX_CONTACT
--------------------------------------------------------

  CREATE TABLE "SCOTT"."EX_CONTACT" 
   (	"CID" NUMBER, 
	"NAME" VARCHAR2(50 BYTE), 
	"PHONE" VARCHAR2(50 BYTE), 
	"EMAIL" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into SCOTT.EX_CONTACT
SET DEFINE OFF;
Insert into SCOTT.EX_CONTACT (CID,NAME,PHONE,EMAIL) values (1,'재혁','1111-2222','jake@itwill.co.kr');
Insert into SCOTT.EX_CONTACT (CID,NAME,PHONE,EMAIL) values (2,'재혁','1111-2222343434235634673573','jake@itwill.co.kr');
Insert into SCOTT.EX_CONTACT (CID,NAME,PHONE,EMAIL) values (4,'이동균','1111-9999','IDG@itwill.co.kr');
--------------------------------------------------------
--  DDL for Index EX_CONTACT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "SCOTT"."EX_CONTACT_PK" ON "SCOTT"."EX_CONTACT" ("CID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  DDL for Trigger EX_CONTACT_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SCOTT"."EX_CONTACT_TRG" 
BEFORE INSERT ON EX_CONTACT 
FOR EACH ROW 
BEGIN
  <<COLUMN_SEQUENCES>>
  BEGIN
    IF INSERTING AND :NEW.CID IS NULL THEN
      SELECT EX_CONTACT_SEQ.NEXTVAL INTO :NEW.CID FROM SYS.DUAL;
    END IF;
  END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "SCOTT"."EX_CONTACT_TRG" ENABLE;
--------------------------------------------------------
--  Constraints for Table EX_CONTACT
--------------------------------------------------------

  ALTER TABLE "SCOTT"."EX_CONTACT" ADD CONSTRAINT "EX_CONTACT_PK" PRIMARY KEY ("CID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "SCOTT"."EX_CONTACT" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "SCOTT"."EX_CONTACT" MODIFY ("CID" NOT NULL ENABLE);