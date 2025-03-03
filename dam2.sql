CREATE OR REPLACE TYPE T_ALUMNO AS OBJECT (
    persona TIPO_PERSONA,
    nota1 NUMBER,
    nota2 NUMBER,
    nota3 NUMBER,
    MEMBER FUNCTION calcular_nota_media RETURN NUMBER
);

DECLARE
    alumno T_ALUMNO;
BEGIN
    -- Inicialio el objeto
    alumno := T_ALUMNO(TIPO_PERSONA('06345276N','Juan Pérez', '20/07/2000'), 8.5, 7.0, 9.2);
    
    -- Muestro los valores para ver si esta bien
    DBMS_OUTPUT.PUT_LINE('Nombre: ' || alumno.persona.nombre);
    DBMS_OUTPUT.PUT_LINE('Edad: ' || alumno.persona.edad);
    DBMS_OUTPUT.PUT_LINE('Nota 1: ' || alumno.nota1);
    DBMS_OUTPUT.PUT_LINE('Nota 2: ' || alumno.nota2);
    DBMS_OUTPUT.PUT_LINE('Nota 3: ' || alumno.nota3);
END;

CREATE OR REPLACE TYPE BODY T_ALUMNO AS
    MEMBER FUNCTION calcular_nota_media RETURN NUMBER IS
    BEGIN
        RETURN (nota1 + nota2 + nota3) / 3;
    END calcular_nota_media;
END;

CREATE TABLE ALUMNOS2 OF T_ALUMNO(
persona primary key
);

--INSERT INTO ALUMNOS VALUES ('06345276N','Juan Pérez', '20/07/2000',


