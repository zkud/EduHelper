--
-- PostgreSQL database dump
--

-- Dumped from database version 11.2
-- Dumped by pg_dump version 11.2

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

--
-- Name: PLANS; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."PLANS" (
    "OWNER_ID" bigint,
    "ID" bigint NOT NULL,
    "RESULT" double precision,
    "INFO" text
);


ALTER TABLE public."PLANS" OWNER TO postgres;

--
-- Name: TABLE "PLANS"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."PLANS" IS 'stores plans of users';


--
-- Name: PLANS_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."PLANS_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."PLANS_ID_seq" OWNER TO postgres;

--
-- Name: PLANS_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."PLANS_ID_seq" OWNED BY public."PLANS"."ID";


--
-- Name: STEPS; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."STEPS" (
    "PLAN_ID" bigint,
    "NAME" text,
    "DEADLINE" timestamp with time zone,
    "COMPLETE" boolean,
    "COST" double precision,
    "ID" bigint NOT NULL
);


ALTER TABLE public."STEPS" OWNER TO postgres;

--
-- Name: TABLE "STEPS"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."STEPS" IS 'stores steps of plans';


--
-- Name: STEPS_ID_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."STEPS_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."STEPS_ID_seq" OWNER TO postgres;

--
-- Name: STEPS_ID_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."STEPS_ID_seq" OWNED BY public."STEPS"."ID";


--
-- Name: USERS; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."USERS" (
    "ID" bigint NOT NULL,
    "NAME" text,
    "MAIL" text,
    "PASSWORD" text
);


ALTER TABLE public."USERS" OWNER TO postgres;

--
-- Name: TABLE "USERS"; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON TABLE public."USERS" IS 'stores users data';


--
-- Name: USERS_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."USERS_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."USERS_id_seq" OWNER TO postgres;

--
-- Name: USERS_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."USERS_id_seq" OWNED BY public."USERS"."ID";


--
-- Name: PLANS ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."PLANS" ALTER COLUMN "ID" SET DEFAULT nextval('public."PLANS_ID_seq"'::regclass);


--
-- Name: STEPS ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."STEPS" ALTER COLUMN "ID" SET DEFAULT nextval('public."STEPS_ID_seq"'::regclass);


--
-- Name: USERS ID; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."USERS" ALTER COLUMN "ID" SET DEFAULT nextval('public."USERS_id_seq"'::regclass);


--
-- Data for Name: PLANS; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."PLANS" ("OWNER_ID", "ID", "RESULT", "INFO") FROM stdin;
\.


--
-- Data for Name: STEPS; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."STEPS" ("PLAN_ID", "NAME", "DEADLINE", "COMPLETE", "COST", "ID") FROM stdin;
\.


--
-- Data for Name: USERS; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."USERS" ("ID", "NAME", "MAIL", "PASSWORD") FROM stdin;
\.


--
-- Name: PLANS_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."PLANS_ID_seq"', 3, true);


--
-- Name: STEPS_ID_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."STEPS_ID_seq"', 1, true);


--
-- Name: USERS_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."USERS_id_seq"', 7, true);


--
-- Name: PLANS PLANS_PK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."PLANS"
    ADD CONSTRAINT "PLANS_PK" PRIMARY KEY ("ID");


--
-- Name: STEPS STEPS_PK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."STEPS"
    ADD CONSTRAINT "STEPS_PK" PRIMARY KEY ("ID") INCLUDE ("ID");


--
-- Name: USERS USERS_PK; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."USERS"
    ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("ID") INCLUDE ("ID");


--
-- Name: PLANS OWNER_ID_FK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."PLANS"
    ADD CONSTRAINT "OWNER_ID_FK" FOREIGN KEY ("OWNER_ID") REFERENCES public."USERS"("ID") ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: STEPS PLAN_ID; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."STEPS"
    ADD CONSTRAINT "PLAN_ID" FOREIGN KEY ("PLAN_ID") REFERENCES public."PLANS"("ID") ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

