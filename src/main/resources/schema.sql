-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


DROP TABLE IF EXISTS public.movies;

CREATE TABLE IF NOT EXISTS public.movies
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    nm character varying NOT NULL,
    prod_year smallint NOT NULL,
    director character varying NOT NULL,
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS public.tickets;

CREATE TABLE IF NOT EXISTS public.tickets
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    "number" bigint NOT NULL,
    seat integer NOT NULL,
    movie bigint NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.tickets
    ADD FOREIGN KEY (movie)
    REFERENCES public.movies (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE RESTRICT
    NOT VALID;

END;