INSERT INTO communities (name, code) VALUES ('Aragón', 'AR');
INSERT INTO communities (name, code) VALUES ('Cataluña', 'CA');
INSERT INTO communities (name, code) VALUES ('Madrid', 'MD');

INSERT INTO federations (name, website, community_id) VALUES ('Federación Aragonesa de Natación', 'www.fanatacion.es', 1);
INSERT INTO federations (name, website, community_id) VALUES ('Federació Catalana de Natació', 'www.natacio.cat', 2);
INSERT INTO federations (name, website, community_id) VALUES ('Federación de Natación de Madrid', 'www.fnmadrid.com', 3);

INSERT INTO leagues (name, province, federation_id) VALUES ('Liga Zaragoza', 'Zaragoza', 1);
INSERT INTO leagues (name, province, federation_id) VALUES ('Liga Huesca', 'Huesca', 1);
INSERT INTO leagues (name, province, federation_id) VALUES ('Liga Barcelona', 'Barcelona', 2);

INSERT INTO clubs (name, city, email, league_id) VALUES ('CN Zaragoza', 'Zaragoza', 'cnzaragoza@email.com', 1);
INSERT INTO clubs (name, city, email, league_id) VALUES ('CN Huesca', 'Huesca', 'cnhuesca@email.com', 2);
INSERT INTO clubs (name, city, email, league_id) VALUES ('CN Barcelona', 'Barcelona', 'cnbarcelona@email.com', 3);

INSERT INTO categories (name, min_age, max_age) VALUES ('Benjamín', 6, 8);
INSERT INTO categories (name, min_age, max_age) VALUES ('Alevín', 9, 11);
INSERT INTO categories (name, min_age, max_age) VALUES ('Infantil', 12, 13);
INSERT INTO categories (name, min_age, max_age) VALUES ('Júnior', 14, 17);
INSERT INTO categories (name, min_age, max_age) VALUES ('Absoluto', 18, 99);

INSERT INTO swimmers (name, surname, birth_date, gender, license_number, club_id, category_id) VALUES ('Carlos', 'García', '2005-03-15', 'M', 'AR-001', 1, 4);
INSERT INTO swimmers (name, surname, birth_date, gender, license_number, club_id, category_id) VALUES ('Laura', 'Martínez', '2007-07-22', 'F', 'AR-002', 1, 3);
INSERT INTO swimmers (name, surname, birth_date, gender, license_number, club_id, category_id) VALUES ('Pablo', 'López', '2003-11-10', 'M', 'CA-001', 3, 5);

INSERT INTO races (style, distance, pool_type) VALUES ('Libre', 50, 'Corta');
INSERT INTO races (style, distance, pool_type) VALUES ('Libre', 100, 'Corta');
INSERT INTO races (style, distance, pool_type) VALUES ('Mariposa', 100, 'Larga');
INSERT INTO races (style, distance, pool_type) VALUES ('Espalda', 200, 'Larga');

INSERT INTO events (name, date, location, pool_type, federation_id) VALUES ('Campeonato Aragonés Infantil 2025', '2025-11-15', 'Zaragoza', 'Corta', 1);
INSERT INTO events (name, date, location, pool_type, federation_id) VALUES ('Trofeo Ciudad de Barcelona 2025', '2025-12-10', 'Barcelona', 'Larga', 2);

INSERT INTO time_records (time, date, status, swimmer_id, race_id, event_id) VALUES (28.45, '2025-11-15', 'oficial', 1, 1, 1);
INSERT INTO time_records (time, date, status, swimmer_id, race_id, event_id) VALUES (62.30, '2025-11-15', 'oficial', 2, 2, 1);
INSERT INTO time_records (time, date, status, swimmer_id, race_id, event_id) VALUES (55.10, '2025-12-10', 'oficial', 3, 3, 2);

INSERT INTO `records` (time, date, gender, race_id, category_id, federation_id, swimmer_id) VALUES (27.90, '2024-03-10', 'M', 1, 4, 1, 1);
INSERT INTO `records` (time, date, gender, race_id, category_id, federation_id, swimmer_id) VALUES (61.50, '2024-05-20', 'F', 2, 3, 1, 2);