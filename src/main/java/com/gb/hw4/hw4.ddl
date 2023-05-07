CREATE SCHEMA "cinema";

CREATE TABLE cinema.movies (
	id serial NOT NULL PRIMARY KEY,
	"name" varchar NOT NULL,
	duration int NOT NULL
);

INSERT INTO cinema.movies ("name", duration) VALUES('Film One', 30);
INSERT INTO cinema.movies ("name", duration) VALUES('Film Two', 30);
INSERT INTO cinema.movies ("name", duration) VALUES('Film Three', 120);
INSERT INTO cinema.movies ("name", duration) VALUES('Film Four', 60);
INSERT INTO cinema.movies ("name", duration) VALUES('Film Five', 60);

CREATE TABLE cinema.shows (
	id serial NOT NULL PRIMARY KEY,
	"date" date NOT NULL,
	"time" time NOT NULL,
	price decimal NOT NULL,
	movie_id int NOT NULL
);

CREATE TABLE cinema.tickets (
	id serial NOT NULL PRIMARY KEY,
	show_id int NOT NULL
);

ALTER TABLE cinema.shows ADD CONSTRAINT shows_fk FOREIGN KEY (movie_id) REFERENCES cinema.movies(id);

ALTER TABLE cinema.tickets ADD CONSTRAINT tickets_fk FOREIGN KEY (show_id) REFERENCES cinema.shows(id);

INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '09:00', 100.00, 3);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '10:00', 100.00, 1);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '10:15', 100.00, 2);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '11:00', 110.00, 1);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '11:15', 110.00, 2);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '11:30', 110.00, 5);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '14:00', 130.00, 4);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '15:00', 130.00, 3);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '17:00', 150.00, 4);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '17:45', 150.00, 2);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '20:00', 150.00, 3);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '20:30', 150.00, 1);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '20:45', 200.00, 4);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '21:00', 200.00, 5);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '21:15', 200.00, 5);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '22:00', 150.00, 3);
INSERT INTO cinema.shows ("date", "time", price, movie_id) VALUES('2023-05-08', '22:30', 150.00, 4);

INSERT INTO cinema.tickets (show_id)
VALUES
    (1),
    (1),
    (1),
    (3),
    (3),
    (3),
    (4),
    (5),
    (5),
    (6),
    (8),
    (9),
    (9),
    (9),
    (3),
    (1),
    (10),
    (11),
    (12),
    (12),
    (12),
    (17),
    (17),
    (14),
    (16),
    (15),
    (15);

SELECT m1.name as "Film A name", s1.time as "Film A time", m1.duration as "Film A duration",
       m2.name as "Film B name", s2.time as "Film B time", m2.duration as "Film B duration"
FROM cinema.shows s1
         INNER JOIN cinema.movies m1 ON s1.movie_id = m1.id
         INNER JOIN cinema.shows s2 ON s1.time < s2.time AND s1.time + INTERVAL '1 minute' * m1.duration > s2.time
         INNER JOIN cinema.movies m2 ON s2.movie_id = m2.id
ORDER BY s1.time;

SELECT m1.name AS "Film A name",
       s1.time AS "Film A time",
       m1.duration AS "Film A duration",
       s2.time AS "Film B time",
       s2.time - (s1.time + INTERVAL '1 minute' * m1.duration) AS "Break duration"
FROM cinema.shows s1
         JOIN cinema.movies m1 ON s1.movie_id = m1.id
         LEFT JOIN cinema.shows s2 ON s2.time = (
    SELECT MIN(time)
    FROM cinema.shows
    WHERE time > s1.time + INTERVAL '1 minute' * m1.duration
)
WHERE s2.time - (s1.time + INTERVAL '1 minute' * m1.duration) >= INTERVAL '30 minutes'
ORDER BY s2.time - (s1.time + INTERVAL '1 minute' * m1.duration) DESC;

SELECT
    COALESCE(m.name, 'Total') AS "Film name",
    COUNT(t.id) AS "Tickets purchased",
    ROUND(AVG((SELECT COUNT(*) FROM cinema.tickets WHERE show_id = s.id))::NUMERIC, 2) AS "Average tickets purchased",
    SUM(s.price * (SELECT COUNT(*) FROM cinema.tickets WHERE show_id = s.id)) AS "Gross sales"
FROM cinema.shows s
         LEFT JOIN cinema.movies m ON s.movie_id = m.id
         LEFT JOIN cinema.tickets t ON s.id = t.show_id
GROUP BY ROLLUP (m.name)
ORDER BY SUM(s.price * (SELECT COUNT(*) FROM cinema.tickets WHERE show_id = s.id)) DESC;

SELECT
    CASE
        WHEN time BETWEEN '09:00:00' AND '15:00:00' THEN '09:00 - 15:00'
        WHEN time BETWEEN '15:00:00' AND '18:00:00' THEN '15:00 - 18:00'
        WHEN time BETWEEN '18:00:00' AND '21:00:00' THEN '18:00 - 21:00'
        ELSE '21:00 - 09:00'
        END AS "Day part",
    COUNT(*) AS "Purchased tickets",
    SUM(s.price) AS "Gross sales"
FROM cinema.tickets
         JOIN cinema.shows s ON tickets.show_id = s.id
GROUP BY 1
ORDER BY "Day part";