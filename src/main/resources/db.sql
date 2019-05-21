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

CREATE TABLE public."PLANS" (
    "OWNER_ID" bigint,
    "ID" bigint NOT NULL,
    "RESULT" double precision,
    "INFO" text
);

ALTER TABLE public."PLANS" OWNER TO sister;

COMMENT ON TABLE public."PLANS" IS 'stores plans of users';

CREATE SEQUENCE public."PLANS_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public."PLANS_ID_seq" OWNER TO sister;

ALTER SEQUENCE public."PLANS_ID_seq" OWNED BY public."PLANS"."ID";

CREATE TABLE public."STEPS" (
    "PLAN_ID" bigint,
    "NAME" text,
    "DEADLINE" timestamp with time zone,
    "COMPLETE" boolean,
    "COST" double precision,
    "ID" bigint NOT NULL
);

ALTER TABLE public."STEPS" OWNER TO sister;

COMMENT ON TABLE public."STEPS" IS 'stores steps of plans';

CREATE SEQUENCE public."STEPS_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public."STEPS_ID_seq" OWNER TO sister;

ALTER SEQUENCE public."STEPS_ID_seq" OWNED BY public."STEPS"."ID";

CREATE TABLE public."USERS" (
    "ID" bigint NOT NULL,
    "NAME" text,
    "MAIL" text,
    "PASSWORD" text
);

ALTER TABLE public."USERS" OWNER TO sister;

COMMENT ON TABLE public."USERS" IS 'stores users data';

CREATE SEQUENCE public."USERS_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public."USERS_id_seq" OWNER TO sister;

ALTER SEQUENCE public."USERS_id_seq" OWNED BY public."USERS"."ID";

ALTER TABLE ONLY public."PLANS" ALTER COLUMN "ID" SET DEFAULT nextval('public."PLANS_ID_seq"'::regclass);

ALTER TABLE ONLY public."STEPS" ALTER COLUMN "ID" SET DEFAULT nextval('public."STEPS_ID_seq"'::regclass);

ALTER TABLE ONLY public."USERS" ALTER COLUMN "ID" SET DEFAULT nextval('public."USERS_id_seq"'::regclass);

SELECT pg_catalog.setval('public."PLANS_ID_seq"', 3, true);

SELECT pg_catalog.setval('public."STEPS_ID_seq"', 1, true);

SELECT pg_catalog.setval('public."USERS_id_seq"', 7, true);

ALTER TABLE ONLY public."PLANS"
    ADD CONSTRAINT "PLANS_PK" PRIMARY KEY ("ID");

ALTER TABLE ONLY public."STEPS"
    ADD CONSTRAINT "STEPS_PK" PRIMARY KEY ("ID");

ALTER TABLE ONLY public."USERS"
    ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("ID");

ALTER TABLE ONLY public."PLANS"
    ADD CONSTRAINT "OWNER_ID_FK" FOREIGN KEY ("OWNER_ID") REFERENCES public."USERS"("ID") ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY public."STEPS"
    ADD CONSTRAINT "PLAN_ID" FOREIGN KEY ("PLAN_ID") REFERENCES public."PLANS"("ID") ON UPDATE CASCADE ON DELETE CASCADE;
