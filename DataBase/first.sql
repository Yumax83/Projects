SELECT * FROM T1 WHERE FName LIKE 'П%' OR FName LIKE 'Е%';

SELECT * FROM T1 WHERE FName GLOB '[ПЕ]*';

SELECT FName FROM T1 WHERE FName GLOB '[А-Л]*';

SELECT FName, ORab FROM T1 WHERE ZP is NULL;

SELECT DISTINCT ZP, Doljnost FROM T1 WHERE Doljnost = 'Менеджер' ORDER BY ZP;

SELECT  Doljnost, ZP FROM T1 WHERE Doljnost='Оператор' AND ORab<3;

SELECT *FROM T1 WHERE (Doljnost='Оператор' OR Doljnost='Секретарь') AND ORab>2;