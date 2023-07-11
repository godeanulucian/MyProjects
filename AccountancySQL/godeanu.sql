drop table tranzactii;
drop table conturi;
drop procedure tranzactie;
drop trigger update_sold;
drop view total_debit;
drop view total_credit;
drop view Debitor;
drop view Creditor;

/*1. Sa se proiecteze baza de date, scriindu-se comenzile de creare a tabelelor si 
impunandu-se toate constrangerile care sunt considerate utile din punct de vedere 
functional si al integritatii datelor. */
--ALTER SESSION SET nls_date_format='DD.MM.YYYY';
SET AUTOCOMMIT ON;
set linesize 1000;
select sysdate from dual;
ALTER session SET nls_date_format=’DD.MM.YYYY’;

CREATE TABLE conturi (
numar_cont NUMBER (5) primary key,
descriere VARCHAR (50),
tip_cont CHAR (2) check ((tip_cont = 'ca') OR (tip_cont = 'pa') OR (tip_cont = 'ac')),
sold_initial NUMBER (*, 2) not null,
sold NUMBER (*, 2) not null
);

CREATE TABLE tranzactii (
numar_tranzactie NUMBER (5) primary key check (numar_tranzactie <= 10000),
data_tranzactie DATE not null,
numar_cont_debitor NUMBER (5) referencing conturi (numar_cont) on delete cascade,
numar_cont_creditor NUMBER (5) referencing conturi (numar_cont) on delete cascade,
suma_tranzactionata NUMBER (5) check (suma_tranzactionata <= 10000),
descriere_tranzactie VARCHAR (10)
);

describe conturi;
describe tranzactii;


--2. Sa se scrie comenzile de populare a tabelelor cu informatii (minim 5 articole in fiecare tabela).
INSERT INTO conturi VALUES (42266, 'Irina Deaconescu', 'ac', 14226, 19942);
INSERT INTO conturi VALUES (22520, 'Godeanu Lucian', 'ca', 22520, 0);
INSERT INTO conturi VALUES (69420, 'Mirela Ionela', 'ac', 6942, 10565);
INSERT INTO conturi VALUES (18069, 'Brailoiu Razvan', 'pa', 34226, 0);
INSERT INTO conturi VALUES (33370, 'Cristina Maria', 'ca', 3700, 3700);

INSERT INTO tranzactii VALUES (111, '04-06-2018', 42266, 22520, 8805, 'InelAur');
INSERT INTO tranzactii VALUES (222, '22-05-2019', 22520, 69420, 9688, 'Porsche911');
INSERT INTO tranzactii VALUES (333, '16-12-2020', 69420, 18069, 3336, 'PlataClub');
INSERT INTO tranzactii VALUES (444, '18-09-2021', 18069, 33370, 8187, 'Investitii');
INSERT INTO tranzactii VALUES (555, '03-03-2022', 33370, 42266, 1989, 'Chirie');

select*from conturi;
select*from tranzactii;

/*3. Sa se implementeze o procedura stocata care sa implementeze o tranzactie, stiind ca aceasta presupune 2 conturi – unul debitor
(din care pleaca banii) si respectiv unul creditor (in care intra banii). Procedura primeste ca si parametri de intrare numarul tranzactiei,
contul debitor, contul creditor, suma tranzactionata precum si o descriere a tranzactiei:*/
CREATE OR REPLACE PROCEDURE tranzactie (parametru_numar_tranzactie NUMBER, parametru_debitor NUMBER, parametru_creditor NUMBER, parametru_suma NUMBER, parametru_descriere VARCHAR) AS
data_tranzactie DATE;
BEGIN
SELECT sysdate INTO data_tranzactie FROM dual;
INSERT INTO tranzactii VALUES (parametru_numar_tranzactie, data_tranzactie, parametru_debitor, parametru_creditor, parametru_suma, parametru_descriere);
END;
/
EXEC tranzactie (666, 22520, 42266, 8000, 'iPhone13');
select*from tranzactii;
--DELETE FROM tranzactii WHERE numar_tranzactie=666;


/*4. Sa se implementeze un trigger care atunci cand este introdusa o tranzactie, automat sa 
recalculeze soldul conturilor implicate in tranzactie*/
CREATE OR REPLACE TRIGGER update_sold -- nu are parametrii
--deoarece triggerul se declanseaza pe o tabela si actioneaza pe alta, este de tip AFTER
AFTER INSERT ON tranzactii
FOR EACH ROW -- deoarece folosim operatorii NEW si OLD
DECLARE -- declaram variabilele
Ex EXCEPTION;
Var_sold number (38,2);
BEGIN
SELECT sold INTO var_sold FROM Conturi WHERE numar_cont= :NEW.numar_cont_debitor;
If var_sold>= :NEW.suma_tranzactionata THEN
UPDATE conturi SET sold = (sold - :NEW.suma_tranzactionata) WHERE numar_cont =     :NEW.numar_cont_debitor;
Else 
RAISE ex; -- se face rollback
END IF;
UPDATE conturi SET sold = (sold + :NEW.suma_tranzactionata) WHERE numar_cont = :NEW.numar_cont_creditor;
EXCEPTION
WHEN ex THEN
RAISE_APPLICATION_ERROR (-20000, 'Sold insuficient');
WHEN OTHERS THEN
RAISE_APPLICATION_ERROR (-20000, 'EROARE');
END;
/
INSERT INTO tranzactii VALUES (777, '27.05.2022', 18069, 33370, 1000, 'Laptop'); --error, sold insuficient
INSERT INTO tranzactii VALUES (888, '27.05.2022', 33370, 42266, 2100, 'Laptop');


--5. Să se creeze un raport in care sa se afişeze toate tranzacţiile în care este folosit un anumit cont.
SELECT * FROM tranzactii WHERE numar_cont_debitor=22520 OR numar_cont_creditor = 22520;


--6. Să se afişeze toate tranzactiile care au fost introduse in perioada (01.01.2020-01.06.2020)
SELECT * FROM tranzactii WHERE data_tranzactie >= '01.01.2020' AND data_tranzactie <= '01.06.2020'; --error
SELECT * FROM tranzactii WHERE data_tranzactie >= '03.03.2022' AND data_tranzactie <= '27.05.2022';


/*7. Să se calculeze şi să se afişeze suma totala creditoare, respectiv debitoare, pentru toate 
conturile care au fost implicate in tranzactii.*/
--suma totala debitoare
CREATE VIEW total_debit AS
SELECT A.numar_cont, B.numar_cont_debitor, SUM(B.suma_tranzactionata) AS suma_debitoare
FROM conturi A, tranzactii B
WHERE A.numar_cont=B.numar_cont_debitor(+)
GROUP BY A.numar_cont, B.numar_cont_debitor
ORDER BY numar_cont;
SELECT * FROM total_debit;
SELECT SUM (suma_debitoare) FROM total_debit;

--suma totala creditoare
CREATE VIEW total_credit AS
SELECT A.numar_cont, B.numar_cont_creditor, SUM(B.suma_tranzactionata) AS suma_creditoare
FROM conturi A, tranzactii B
WHERE A.numar_cont=B.numar_cont_creditor(+)
GROUP BY A.numar_cont, B.numar_cont_creditor
ORDER BY numar_cont;
SELECT * FROM total_credit;
SELECT SUM (suma_creditoare) FROM total_credit;


--8. Să se afişeze toate tranzacţiile care implică conturi de un anumit tip (’ca’ –capital, ’pa’-pasiv, sau ’ac’-activ)
--A. pentru tip_cont='ca':
--1. nr_cont_debitor
SELECT A.tip_cont, A.numar_cont, B.numar_tranzactie, B.suma_tranzactionata, B.numar_cont_debitor, B.descriere_tranzactie
FROM conturi A, tranzactii B
WHERE A.numar_cont=B.numar_cont_debitor AND A.tip_cont='ca'
ORDER BY numar_cont;

--2. nr_cont_creditor
SELECT A.tip_cont, A.numar_cont, B.numar_tranzactie, B.suma_tranzactionata, B.numar_cont_creditor, B.descriere_tranzactie
FROM conturi A, tranzactii B
WHERE A.numar_cont=B.numar_cont_creditor AND A.tip_cont='ca'
ORDER BY numar_cont;

--B. pentru tip_cont='pa';
--1. nr_cont_debitor
SELECT A.tip_cont, A.numar_cont, B.numar_tranzactie, B.suma_tranzactionata, B.numar_cont_debitor, B.descriere_tranzactie
FROM conturi A, tranzactii B
WHERE A.numar_cont=B.numar_cont_debitor AND A.tip_cont='pa'
ORDER BY numar_cont;

--2. nr_cont_creditor
SELECT A.tip_cont, A.numar_cont, B.numar_tranzactie, B.suma_tranzactionata, B.numar_cont_creditor, B.descriere_tranzactie
FROM conturi A, tranzactii B
WHERE A.numar_cont=B.numar_cont_creditor AND A.tip_cont='pa';

--C. tip_cont='ac';
--1. nr_cont_debitor
SELECT A.tip_cont, A.numar_cont, B.numar_tranzactie, B.suma_tranzactionata, B.numar_cont_debitor, B.descriere_tranzactie
FROM conturi A, tranzactii B
WHERE A.numar_cont=B.numar_cont_debitor AND A.tip_cont='ac'
ORDER BY numar_cont;

--2. nr_cont_creditor
SELECT A.tip_cont, A.numar_cont, B.numar_tranzactie, B.suma_tranzactionata, B.numar_cont_creditor, B.descriere_tranzactie
FROM conturi A, tranzactii B
WHERE A.numar_cont=B.numar_cont_debitor AND A.tip_cont='ac'
ORDER BY numar_cont;


--9. Să se steargă un cont daca nu exista tranzactii pentru el
DELETE FROM conturi
WHERE NOT EXISTS (
SELECT numar_cont_debitor, numar_cont_creditor FROM tranzactii );
--sau
DELETE FROM conturi WHERE numar_cont NOT IN (
SELECT numar_cont_debitor FROM tranzactii) AND numar_cont NOT IN (
SELECT numar_cont_creditor FROM tranzactii );


/*10. Să se afiseze contul care apare in cele mai multe tranzactii precum si numarul de 
tranzactii in care el apare.*/
CREATE VIEW Debitor AS(
SELECT Numar_cont_debitor, count(Numar_cont_debitor) as nr_aparitii
FROM Tranzactii
GROUP BY numar_cont_debitor);
select*from Debitor;

CREATE VIEW Creditor AS(
SELECT Numar_cont_creditor, count(Numar_cont_creditor) AS nr_aparitii_cred
FROM Tranzactii
GROUP BY numar_cont_creditor);
select*from Creditor;

SELECT numar_cont, sum(nr_aparitii+nr_aparitii_cred) AS maxim_aparitii
FROM Conturi A, Debitor B, Creditor C
WHERE A.numar_cont=B.Numar_cont_debitor(+) AND A.numar_cont=C.numar_cont_creditor(+)
HAVING sum(nr_aparitii+nr_aparitii_cred) = (SELECT max(sum(nr_aparitii+nr_aparitii_cred)) 
FROM Conturi A, Debitor B, Creditor C
WHERE A.numar_cont=B.Numar_cont_debitor(+) AND A.numar_cont=C.numar_cont_creditor(+)
GROUP BY numar_cont)
GROUP BY numar_cont;