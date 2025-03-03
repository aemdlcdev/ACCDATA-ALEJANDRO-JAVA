SET SERVEROUTPUT ON;

-- Recreate the types and table
create or replace type direccion as object(
calle varchar2(25),
ciudad varchar2(20),
codigo_post number(5));

create or replace type persona as object (
codigo number,
nombre varchar2(35),
direc direccion,
fecha_nac date);

create or replace type T_ALUMNO as object (
    persona_obj persona,
    nota1 number(3,2),
    nota2 number(3,2),
    nota3 number(3,2),
    member function obtener_nota_media return number
);

create or replace type body T_ALUMNO as 
    member function obtener_nota_media return number is
    begin
        return (nota1 + nota2 + nota3) / 3;
    end;
end;

create table ALUMNOS2 of T_ALUMNO;

-- Reinsert the data
insert into ALUMNOS2 values (
    T_ALUMNO(
        persona(1, 'Alejandro Esteban', direccion('Calle Pintor Lopez Torres', 'Ciudad Real', 13005), to_date('2000-01-01', 'YYYY-MM-DD')),
        8.5,
        9.0,
        7.5
    )
);

insert into ALUMNOS2 values (
    T_ALUMNO(
        persona(2, 'Maria Lopez', direccion('Calle La Mata', 'Ciudad Real', 13001), to_date('1999-05-15', 'YYYY-MM-DD')),
        7.0,
        8.0,
        6.5
    )
);

insert into ALUMNOS2 values (
    T_ALUMNO(
        persona(3, 'Juan Garcia', direccion('Avenida del Ejercito', 'Guadalajara', 19001), to_date('2001-03-22', 'YYYY-MM-DD')),
        9.0,
        8.5,
        9.5
    )
);

insert into ALUMNOS2 values (
    T_ALUMNO(
        persona(4, 'Ana Martinez', direccion('Calle de la Paz', 'Guadalajara', 19002), to_date('2002-07-30', 'YYYY-MM-DD')),
        6.5,
        7.0,
        8.0
    )
);

declare
    alumno T_ALUMNO;
begin
    alumno := T_ALUMNO(
        persona(1, 'Alejandro Esteban', direccion('Calle Pintor Lopez Torres', 'Ciudad Real', 13005), to_date('2000-01-01', 'YYYY-MM-DD')),
        8.5,
        9.0,
        7.5
    );
    
    dbms_output.put_line('Alumno inicializado: ' || alumno.persona_obj.nombre);
end;

-- Modificar una de las notas de un alumno
update ALUMNOS2 a
set a.nota1 = 9.0
where a.persona_obj.codigo = 1;

-- Modificar los datos personales de uno de los alumnos
update ALUMNOS2 a
set a.persona_obj.nombre = 'Alejandro Gomez', a.persona.direc.calle = 'Calle Nueva'
where a.persona_obj.codigo = 1;

-- Verificar que los datos se han modificado
select a.persona.nombre, a.persona.direc.calle, a.nota1, a.nota2, a.nota3
from ALUMNOS2 a
where a.persona_obj.codigo = 1;

select a.persona_obj.nombre, a.obtener_nota_media() as nota_media
from ALUMNOS2 a;

select a.persona_obj.nombre, a.obtener_nota_media() as nota_media
from ALUMNOS2 a
where a.persona_obj.direc.ciudad = 'Guadalajara' and a.obtener_nota_media() > 6;

SELECT a.persona_obj.nombre as nombre, a.calcular_nota_media() as nota_media from alumnos2 a
where a.calcular_nota_media() = (select max(b.calcular_nota_media()) from alumnos2 b);

-- Eliminar alumnos que son de CIUDAD REAL
delete from ALUMNOS2 a
where a.persona_obj.direc.ciudad = 'Ciudad Real';

-- Verificar que se han eliminado
select a.persona_obj.nombre, a.persona_obj.direc.ciudad
from ALUMNOS2 a
where a.persona_obj.direc.ciudad = 'Ciudad Real';



