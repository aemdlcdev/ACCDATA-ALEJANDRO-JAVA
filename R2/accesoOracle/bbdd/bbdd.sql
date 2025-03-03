--------------------------------------------------------
-- Archivo creado  - lunes-noviembre-11-2024   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table DEPARTAMENTOS
--------------------------------------------------------

  CREATE TABLE "DEPARTAMENTOS" 
   (	"DEPT_NO" NUMBER(2,0), 
	"DNOMBRE" VARCHAR2(15), 
	"LOC" VARCHAR2(15)
   ) ;
--------------------------------------------------------
--  DDL for Table EMPLEADOS
--------------------------------------------------------

  CREATE TABLE "EMPLEADOS" 
   (	"EMP_NO" NUMBER(4,0), 
	"APELLIDO" VARCHAR2(10), 
	"OFICIO" VARCHAR2(10), 
	"DIR" NUMBER(4,0), 
	"FECHA_ALT" DATE, 
	"SALARIO" NUMBER(6,2), 
	"COMISION" NUMBER(6,2), 
	"DEPT_NO" NUMBER(2,0)
   ) ;
REM INSERTING into DEPARTAMENTOS
SET DEFINE OFF;
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('2','Informatica','Ciudad Real');
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('3','Consumo','Toledo');
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('4','Administracion','Toledo');
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('10','CONTABILIDAD','SEVILLA');
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('20','INVESTIGACI�N','MADRID');
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('30','VENTAS','BARCELONA');
Insert into DEPARTAMENTOS (DEPT_NO,DNOMBRE,LOC) values ('40','PRODUCCI�N','BILBAO');
REM INSERTING into EMPLEADOS
SET DEFINE OFF;
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7369','SANCHEZ','EMPLEADO','7902',to_date('17/12/1990','DD/MM/YYYY'),'1040',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7499','ARROYO','VENDEDOR','7698',to_date('20/02/1990','DD/MM/YYYY'),'1500','390','30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7521','SALA','VENDEDOR','7698',to_date('22/02/1991','DD/MM/YYYY'),'1625','650','30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7566','JIMENEZ','DIRECTOR','7839',to_date('02/04/1991','DD/MM/YYYY'),'2900',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7654','MARTIN','VENDEDOR','7698',to_date('29/09/1991','DD/MM/YYYY'),'1600','1020','30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7698','NEGRO','DIRECTOR','7839',to_date('01/05/1991','DD/MM/YYYY'),'3005',null,'30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7782','CEREZO','DIRECTOR','7839',to_date('09/06/1991','DD/MM/YYYY'),'2885',null,'10');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7788','GIL','ANALISTA','7566',to_date('09/11/1991','DD/MM/YYYY'),'3000',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7839','REY','PRESIDENTE',null,to_date('17/11/1991','DD/MM/YYYY'),'4100',null,'10');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7844','TOVAR','VENDEDOR','7698',to_date('08/09/1991','DD/MM/YYYY'),'1350','0','30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7876','ALONSO','EMPLEADO','7788',to_date('23/09/1991','DD/MM/YYYY'),'1430',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7900','JIMENO','EMPLEADO','7698',to_date('03/12/1991','DD/MM/YYYY'),'1335',null,'30');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7902','FERNANDEZ','ANALISTA','7566',to_date('03/12/1991','DD/MM/YYYY'),'3000',null,'20');
Insert into EMPLEADOS (EMP_NO,APELLIDO,OFICIO,DIR,FECHA_ALT,SALARIO,COMISION,DEPT_NO) values ('7934','MU�OZ','EMPLEADO','7782',to_date('23/01/1992','DD/MM/YYYY'),'1690',null,'10');
--------------------------------------------------------
--  DDL for Procedure P_NOMBRE_DEPART
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "P_NOMBRE_DEPART" (
    ndepart IN NUMBER,
    nombre_depart OUT VARCHAR2)
AS
BEGIN
    SELECT dnombre INTO nombre_depart
    FROM departamentos
    WHERE dept_no = ndepart;
END;

/
--------------------------------------------------------
--  DDL for Procedure P_SUBIDA_SAL
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "P_SUBIDA_SAL" 
(
  DEPT_NO IN NUMBER,
  SUBIDA IN NUMBER 
) AS 
BEGIN
    UPDATE empleados
    SET SALARIO = SALARIO + SUBIDA
    WHERE empleados.DEPT_NO = DEPT_NO;  -- Ahora la columna se refiere a la tabla y el parámetro al valor pasado
    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No existe el departamento');
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error en la subida de salario');
        RAISE;
END P_SUBIDA_SAL;

/
--------------------------------------------------------
--  DDL for Function F_N_EMPLEADO
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "F_N_EMPLEADO" 
(
  DEPT_NO IN NUMBER 
, NUM_EMPLEADOS_DEPT OUT NUMBER 
) RETURN VARCHAR2 AS 
BEGIN
  RETURN NULL;
END F_N_EMPLEADO;

/
--------------------------------------------------------
--  DDL for Function F_NOMBRE_DEPART
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE FUNCTION "F_NOMBRE_DEPART" (
    ndepart NUMBER) RETURN VARCHAR2
IS
    nombre_depart departamentos.dnombre%TYPE;
BEGIN
    SELECT dnombre INTO nombre_depart FROM
    departamentos WHERE dept_no = ndepart;
    RETURN (nombre_depart);
END;

/
--------------------------------------------------------
--  Constraints for Table EMPLEADOS
--------------------------------------------------------

  ALTER TABLE "EMPLEADOS" MODIFY ("EMP_NO" NOT NULL ENABLE);
  ALTER TABLE "EMPLEADOS" ADD PRIMARY KEY ("EMP_NO")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Constraints for Table DEPARTAMENTOS
--------------------------------------------------------

  ALTER TABLE "DEPARTAMENTOS" MODIFY ("DEPT_NO" NOT NULL ENABLE);
  ALTER TABLE "DEPARTAMENTOS" ADD PRIMARY KEY ("DEPT_NO")
  USING INDEX  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table EMPLEADOS
--------------------------------------------------------

  ALTER TABLE "EMPLEADOS" ADD CONSTRAINT "FK_DEP" FOREIGN KEY ("DEPT_NO")
	  REFERENCES "DEPARTAMENTOS" ("DEPT_NO") ENABLE;
