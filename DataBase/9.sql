SELECT s.com
FROM Salespeople s, Customers c
WHERE s.snum = c.snum AND c.city = "Rome";

SELECT com
FROM Salespeople 
WHERE snum IN (
SELECT snum FROM Customers WHERE city = "Rome"
) ORDER BY com;

SELECT cname, rating
FROM Customers
WHERE rating < 
(SELECT AVG(rating) FROM Customers); 

SELECT city, rating
FROM Customers
GROUP BY city
HAVING MIN(rating) = 
(SELECT MIN(rating) FROM Customers); 

SELECT DISTINCT city, rating 
FROM Customers 
WHERE rating = (SELECT min(rating) FROM Customers);


SELECT s.FIO
FROM Student s, G g, Kafedra k
WHERE k.Decanat = "Физико-математический" AND k.Kafedra=g.Kafedra AND s.[Group]=g.[Group];

SELECT FIO
FROM Student
WHERE "Group" IN 
    (SELECT "Group" 
    FROM G
    WHERE Kafedra IN
     (SELECT Kafedra
      FROM  Kafedra  
      WHERE Decanat = "Физико-математический")
    );

SELECT k.Kafedra, k.Decanat, s.FIO
FROM Kafedra k, Exzamen e, Student s, G g
WHERE e.Predmet GLOB "Социология"
 AND e.Id_St = s.Id_St 
 AND s."Group" = g."Group" 
 AND g.Kafedra = k.Kafedra;
 
SELECT Kafedra, Decanat
FROM Kafedra 
WHERE Kafedra IN 
    (SELECT Kafedra FROM G WHERE "Group" IN 
        (SELECT "Group" FROM Student WHERE ID_St IN 
            (SELECT Id_St FROM Exzamen WHERE Predmet='Социология' )));
            
SELECT Kafedra, Decanat, (SELECT FIO FROM Kafedra k, Exzamen e, Student s, G g
                          WHERE e.Predmet GLOB "Социология" AND
                          e.Id_St = s.Id_St AND
                          s."Group" = g."Group" AND
                          g.Kafedra = k.Kafedra)              
FROM Kafedra 
WHERE Kafedra IN 
    (SELECT Kafedra FROM G WHERE "Group" IN 
        (SELECT "Group" FROM Student WHERE Id_St IN 
            (SELECT Id_St FROM Exzamen WHERE Predmet='Социология' )));
            


 
SELECT s.FIO, (SELECT g.Kafedra
                FROM G g
                WHERE s."Group" = g."Group") AS Кафедра
FROM Student s
ORDER BY s.FIO;


SELECT Kafedra, Decanat, (SELECT FIO 
        FROM Student 
        WHERE ID_St=(SELECT Id_St FROM Exzamen 
        WHERE Predmet="Социология") ) 
    FROM Kafedra WHERE Kafedra IN 
    (SELECT Kafedra 
    FROM G WHERE "Group" IN 
    (SELECT "Group" FROM Student 
    WHERE ID_St IN (SELECT Id_St FROM Exzamen WHERE Predmet='Социология' )));
    
SELECT FIO 
FROM Student 
WHERE "Group" IN 
    (SELECT "Group"
    FROM G 
    WHERE Kafedra IN 
        (SELECT Kafedra 
        FROM Kafedra
        WHERE Decanat="Физико-технический")) 
            AND ID_ST IN 
            (SELECT Id_St
             FROM Exzamen WHERE Predmet='Иностр. язык' AND Ball=5 );

SELECT Special, COUNT (FIO)
FROM Student
GROUP BY Special
HAVING COUNT (FIO) <
(SELECT COUNT(FIO) FROM Student);

SELECT COUNT (Special), Special
FROM Student
GROUP BY Special;

SELECT * 
FROM
Orders o
WHERE EXISTS 
    (SELECT c.cnum
    FROM CUSTOMERS c
    WHERE c.cname = 'Cineros' AND c.cnum=o.cnum);
    
SELECT snum AS НОМЕР, sname AS ИМЯ
FROM Salespeople
WHERE city = 'London'

UNION

SELECT snum, cname
FROM Customers
WHERE city = 'London';


SELECT snum AS НОМЕР, city AS ГОРОД
FROM Salespeople

UNION  ALL

SELECT snum, city
FROM Customers;

SELECT cname, city, rating, "Высокий рейтинг"
FROM Customers
WHERE rating>=200

UNION

SELECT cname, city, rating, "Низкий рейтинг"
FROM Customers
WHERE rating<200;


SELECT c.cname, s.sname
FROM Customers c INNER JOIN Salespeople s ON c.snum=s.snum;

SELECT c.cname, s.sname
FROM Customers c, Salespeople s 
WHERE c.snum=s.snum;

SELECT c.cname, o.onum
FROM Customers c INNER JOIN Orders o ON c.snum=o.snum
ORDER BY c.cname;

SELECT c.cname, o.onum
FROM Customers c RIGHT JOIN Orders o ON c.cnum=o.cnum
ORDER BY c.cname;

SELECT c.cname, o.onum
FROM  Orders o LEFT JOIN Customers c ON c.cnum=o.cnum
ORDER BY c.cname;

