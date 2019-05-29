SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE public."plans" (
    "owner_id" bigint,
    "id" bigint NOT NULL,
    "result" double precision,
    "info" text
);

ALTER TABLE public."plans" OWNER TO postgres;

COMMENT ON TABLE public."plans" IS 'stores plans of users';

CREATE SEQUENCE public."plans_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public."plans_id_seq" OWNER TO postgres;

ALTER SEQUENCE public."plans_id_seq" OWNED BY public."plans"."id";

CREATE TABLE public."steps" (
    "plan_id" bigint,
    "name" text,
    "deadline" timestamp with time zone,
    "complete" boolean,
    "cost" double precision,
    "id" bigint NOT NULL
);

ALTER TABLE public."steps" OWNER TO postgres;

COMMENT ON TABLE public."steps" IS 'stores steps of plans';

CREATE SEQUENCE public."steps_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public."steps_id_seq" OWNER TO postgres;

ALTER SEQUENCE public."steps_id_seq" OWNED BY public."steps"."id";

CREATE TABLE public."users" (
    "id" bigint NOT NULL,
    "name" text,
    "mail" text,
    "password" text
);

ALTER TABLE public."users" OWNER TO postgres;

COMMENT ON TABLE public."users" IS 'stores users data';

CREATE SEQUENCE public."users_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public."users_id_seq" OWNER TO postgres;

ALTER SEQUENCE public."users_id_seq" OWNED BY public."users"."id";

ALTER TABLE ONLY public."plans" ALTER COLUMN "id" SET DEFAULT nextval('public."plans_id_seq"'::regclass);

ALTER TABLE ONLY public."steps" ALTER COLUMN "id" SET DEFAULT nextval('public."steps_id_seq"'::regclass);

ALTER TABLE ONLY public."users" ALTER COLUMN "id" SET DEFAULT nextval('public."users_id_seq"'::regclass);

SELECT pg_catalog.setval('public."plans_id_seq"', 3, true);

SELECT pg_catalog.setval('public."steps_id_seq"', 1, true);

SELECT pg_catalog.setval('public."users_id_seq"', 7, true);

ALTER TABLE ONLY public."plans"
    ADD CONSTRAINT "plans_pk" PRIMARY KEY ("id");

ALTER TABLE ONLY public."steps"
    ADD CONSTRAINT "steps_pk" PRIMARY KEY ("id");

ALTER TABLE ONLY public."users"
    ADD CONSTRAINT "users_pk" PRIMARY KEY ("id");

ALTER TABLE ONLY public."plans"
    ADD CONSTRAINT "owner_id_fk" FOREIGN KEY ("owner_id") REFERENCES public."users"("id") ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public."steps"
    ADD CONSTRAINT "plan_id" FOREIGN KEY ("plan_id") REFERENCES public."plans"("id") ON UPDATE CASCADE ON DELETE CASCADE;
