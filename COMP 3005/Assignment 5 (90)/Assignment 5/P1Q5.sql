set serveroutput on

DECLARE
  s_tuple Sailer%rowtype;
  b_tuple Boat%rowtype;

  CURSOR s_cursor IS select * from Sailer ORDER BY S# ASC;
  CURSOR b_cursor(snum Sailer.s#%type)
  IS  select B.* 
      FROM Boat B, Reservation R
      WHERE B.B# = R.B#
      AND   R.S# = snum
      ORDER BY B.B# ASC;

  BEGIN
    dbms_output.put_line('+-------------------------------------------------+');
    dbms_output.put_line('| S#       | Name          | Age                  |');
    dbms_output.put_line('+-------------------------------------------------+');

  OPEN s_cursor;
  LOOP
    fetch s_cursor into s_tuple;
    exit when s_cursor%NOTFOUND;

    dbms_output.put_line(
    '| ' || rpad(to_char(s_tuple.s#), 9) ||
    '| ' || rpad(s_tuple.Name, 14) ||
    '| ' || rpad(s_tuple.Age, 9)  ||
    '            |');

    dbms_output.put_line('+-------------------------------------------------+');
    dbms_output.put_line('|  +--------------------------------------------+ |');  
    dbms_output.put_line('|  | B#       | Name          | Color           | |');
    dbms_output.put_line('|  +--------------------------------------------+ |');  

    OPEN b_cursor(s_tuple.s#);
    LOOP
      FETCH b_cursor into b_tuple;
      exit when b_cursor%NOTFOUND;

      dbms_output.put_line('|  | ' || rpad(to_char(b_tuple.b#), 9) ||
                              '| ' || rpad(b_tuple.Name, 14) ||
                              '| ' || rpad(b_tuple.Color, 9) ||
                                                  '       | |');
      dbms_output.put_line('|  +--------------------------------------------+ |');
    END LOOP;
    CLOSE b_cursor;

    dbms_output.put_line('+-------------------------------------------------+');  
  END LOOP;
  CLOSE s_cursor;
END;
/