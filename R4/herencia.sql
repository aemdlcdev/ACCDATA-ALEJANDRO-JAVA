CREATE OR REPLACE TYPE TIPO_PERSONA AS OBJECT(

DNI VARCHAR2(10),
NOMBRE VARCHAR2(25),
FEC_NAC DATE,

MEMBER FUNCTION EDAD RETURN NUMBER,
FINAL MEMBER FUNCTION GET_DNI RETURN VARCHAR2,
MEMBER FUNCTION GET_NOMBRE RETURN VARCHAR2,
MEMBER PROCEDURE VER_DATOS
) NOT FINAL;

-- Crear el cuerp del tipo

CREATE OR REPLACE TYPE BODY TIPO_PERSONA AS
    MEMBER FUNCTION EDAD RETURN NUMBER IS
    BEGIN
        RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, FEC_NAC) / 12);
    END;
    FINAL MEMBER FUNCTION GET_DNI RETURN VARCHAR2 IS
    BEGIN
        RETURN DNI;
    END;
    MEMBER FUNCTION GET_NOMBRE RETURN VARCHAR2 IS
    BEGIN
        RETURN NOMBRE;
    END;
    MEMBER PROCEDURE VER_DATOS IS
    BEGIN
        DBMS_OUTPUT.PUT_LINE('DNI: ' || DNI);
        DBMS_OUTPUT.PUT_LINE('Nombre: ' || NOMBRE);
        DBMS_OUTPUT.PUT_LINE('Fecha de Nacimiento: ' || TO_CHAR(FEC_NAC, 'YYYY-MM-DD'));
    END;
END;

CREATE OR REPLACE TYPE T_NOTICIA AS OBJECT(
    id_noticia NUMBER(10),
    fecha DATE,
    titulo VARCHAR2(30),
    descripcion VARCHAR2(100),
    pais VARCHAR2(40),

    FINAL MEMBER FUNCTION F_DIAS_NOTICIA RETURN NUMBER,
    MEMBER FUNCTION F_NOTICIA_DONDE RETURN VARCHAR2
) NOT FINAL;

-- Crear un subtipo de noticia con la localidadd

CREATE OR REPLACE TYPE BODY T_NOTICIA AS
    FINAL MEMBER FUNCTION F_DIAS_NOTICIA RETURN NUMBER IS
    BEGIN
        RETURN TRUNC(SYSDATE - fecha);
    END;
    MEMBER FUNCTION F_NOTICIA_DONDE RETURN VARCHAR2 IS
    BEGIN
        RETURN pais;
    END;
END;

CREATE OR REPLACE TYPE T_NOTICIA_LOCAL UNDER T_NOTICIA(
    localidad VARCHAR2(100),

    OVERRIDING MEMBER FUNCTION F_NOTICIA_DONDE RETURN VARCHAR2
);

-- Crear la tabla de noticias

CREATE OR REPLACE TYPE BODY T_NOTICIA_LOCAL AS
    OVERRIDING MEMBER FUNCTION F_NOTICIA_DONDE RETURN VARCHAR2 IS
    BEGIN
        RETURN localidad;
    END;
END;

CREATE TABLE TABLA_T_NOTICIA_LOCAL OF T_NOTICIA_LOCAL;

-- Bloque anonimo oara probar la funcion

DECLARE
    v_noticia T_NOTICIA := T_NOTICIA( -- Noticia con el supertipo (notica general)
        1,
        TO_DATE('2023-01-01', 'YYYY-MM-DD'),
        'Titulo de Ejemplo',
        'Descripcion de Ejemplo',
        'Pais de Ejemplo'
    );
BEGIN
    DBMS_OUTPUT.PUT_LINE('Id_noticia: ' || v_noticia.id_noticia);
    DBMS_OUTPUT.PUT_LINE('Fecha: ' || TO_CHAR(v_noticia.fecha, 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Titulo: ' || v_noticia.titulo);
    DBMS_OUTPUT.PUT_LINE('Descripcion: ' || v_noticia.descripcion);
    DBMS_OUTPUT.PUT_LINE('Pais: ' || v_noticia.pais);

    DBMS_OUTPUT.PUT_LINE('Dias desde la noticia: ' || v_noticia.F_DIAS_NOTICIA);
    DBMS_OUTPUT.PUT_LINE('Localizacion de la noticia: ' || v_noticia.F_NOTICIA_DONDE);
END;


DECLARE
    v_noticia_local T_NOTICIA_LOCAL := T_NOTICIA_LOCAL( -- Noticia con el subtipo (noticia con la loclidad)
        2,
        TO_DATE('2023-01-01', 'YYYY-MM-DD'),
        'Titulo Local',
        'Descripcion Local',
        'Pais Local',
        'Localidad Ejemplo'
    );
BEGIN
    DBMS_OUTPUT.PUT_LINE('Id_noticia: ' || v_noticia_local.id_noticia);
    DBMS_OUTPUT.PUT_LINE('Fecha: ' || TO_CHAR(v_noticia_local.fecha, 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Titulo: ' || v_noticia_local.titulo);
    DBMS_OUTPUT.PUT_LINE('Descripcion: ' || v_noticia_local.descripcion);
    DBMS_OUTPUT.PUT_LINE('Pais: ' || v_noticia_local.pais);
    DBMS_OUTPUT.PUT_LINE('Localidad: ' || v_noticia_local.localidad);

    DBMS_OUTPUT.PUT_LINE('Dias desde la noticia: ' || v_noticia_local.F_DIAS_NOTICIA);
    DBMS_OUTPUT.PUT_LINE('Localizacion de la noticia: ' || v_noticia_local.F_NOTICIA_DONDE);
END;

BEGIN
    -- Insertar noticias locales en la tabla
    INSERT INTO TABLA_T_NOTICIA_LOCAL VALUES (
        T_NOTICIA_LOCAL(
            2,
            TO_DATE('2023-01-01', 'YYYY-MM-DD'),
            'Titulo Local',
            'Descripcion Local',
            'Pais Local',
            'Localidad Ejemplo'
        )
    );
    INSERT INTO TABLA_T_NOTICIA_LOCAL VALUES (
        T_NOTICIA_LOCAL(
            3,
            TO_DATE('2023-02-01', 'YYYY-MM-DD'),
            'Otro Titulo Local',
            'Otra Descripcion Local',
            'Otro Pais Local',
            'Otra Localidad Ejemplo'
        )
    );
    COMMIT;
END;

SELECT * FROM TABLA_T_NOTICIA_LOCAL;


-- ANIDADAS

CREATE TYPE tabla_anidada AS TABLE OF direccion;

CREATE TABLE ejemplo_tabla_anidada(
id number(2),
apellidos varchar2(35),
direc tabla_anidada)
NESTED TABLE DIREC STORE AS DIREC_ANIDADA;



INSERT INTO EJEMPLO_TABLA_ANIDADA VALUES (1, ‘RAMOS’,

TABLA_ANIDADA (

DIRECCION(‘C/Los manantiales 5’, ‘GUADALAJARA’, 19004),
DIRECCION(‘C/Los manantiales 10’, ‘GUADALAJARA’, 19004),
DIRECCION(‘C/Av de Paris 25’, ‘CACERES’, 10005),
DIRECCION(‘C/Segovia 23-3A’, ‘TOLEDO’, 45005)

)

);


SELECT ID, APELLIDOS, ALIAS_DIRECC.*
FROM EJEMPLO_TABLA_ANIDADA, TABLE(DIREC) ALIAS_DIRECC;


--MODIFICAR UN OBJETO DE LA TABLA ANIDADA

update TABLE (

SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID=1)

ALIAS_TABLA
SET VALUE(ALIAS_TABLA) = DIRECCION ('C/Pilón 11', 'TOLEDO', 45589)
WHERE VALUE(ALIAS_TABLA)=DIRECCION('C/Los manantiales 5',
'GUADALAJARA', 19004);



• ELIMINAR UN OBJETO DE LA TABLA ANIDADA

DELETE FROM TABLE (

SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID=1)

ALIAS_TABLA
WHERE VALUE(ALIAS_TABLA)=DIRECCION('C/Los manantiales 10',
'GUADALAJARA', 19004);



• ELIMINAR UN OBJETO DE LA TABLA ANIDADA DONDE UNO DE SUS ATRIBUTOS TENGA
UN DETERMINADO VALOR

DELETE FROM TABLE (

SELECT DIREC FROM EJEMPLO_TABLA_ANIDADA WHERE ID=1)

ALIAS_TABLA
WHERE ALIAS_TABLA.CIUDAD=‘GUADALAJARA’;
