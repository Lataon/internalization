DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS countries;
DROP TABLE IF EXISTS languages;

CREATE TABLE languages (
    id INT NOT NULL AUTO_INCREMENT,
    lang_name VARCHAR(50) NOT NULL,
    locale VARCHAR(2)
);

CREATE TABLE countries (
    id INT NOT NULL AUTO_INCREMENT,
    locale VARCHAR(50) NOT NULL,
    code VARCHAR(2) NOT NULL,
    country_name VARCHAR(50) NOT NULL
);

CREATE TABLE cities (
    id INT NOT NULL AUTO_INCREMENT,
    locale VARCHAR(2) NOT NULL,
    country_code VARCHAR(2) NOT NULL,
    city_name VARCHAR(50) NOT NULL
);

INSERT INTO languages(lang_name, locale) VALUES
('germany', 'de'),
('english', 'en');

INSERT INTO countries (locale, code, country_name) VALUES
('de', 'de', 'Deutschland'),
('de', 'en', 'England'),

('en', 'de', 'Germany'),
('en', 'en', 'England');

INSERT INTO cities (locale, country_code, city_name) VALUES
('de', 'de', 'Hamburg'),
('de', 'en', 'London'),

('en', 'de', 'Hamburg'),
('en', 'en', 'London');