PGDMP         (                w            DevDB    11.2    11.2                 0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    16385    DevDB    DATABASE        CREATE DATABASE "DevDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Russian_Russia.1251' LC_CTYPE = 'Russian_Russia.1251';
    DROP DATABASE "DevDB";
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    3            Η            1259    16443    PLANS    TABLE        CREATE TABLE public."PLANS" (
    "OWNER_ID" bigint,
    "ID" bigint NOT NULL,
    "RESULT" double precision,
    "INFO" text
);
    DROP TABLE public."PLANS";
       public         postgres    false    3                       0    0    TABLE "PLANS"    COMMENT     <   COMMENT ON TABLE public."PLANS" IS 'stores plans of users';
            public       postgres    false    199            Ζ            1259    16441    PLANS_ID_seq    SEQUENCE     w   CREATE SEQUENCE public."PLANS_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."PLANS_ID_seq";
       public       postgres    false    199    3                       0    0    PLANS_ID_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."PLANS_ID_seq" OWNED BY public."PLANS"."ID";
            public       postgres    false    198            Ι            1259    16459    STEPS    TABLE     Ώ   CREATE TABLE public."STEPS" (
    "PLAN_ID" bigint,
    "NAME" text,
    "DEADLINE" timestamp with time zone,
    "COMPLETE" boolean,
    "COST" double precision,
    "ID" bigint NOT NULL
);
    DROP TABLE public."STEPS";
       public         postgres    false    3                       0    0    TABLE "STEPS"    COMMENT     <   COMMENT ON TABLE public."STEPS" IS 'stores steps of plans';
            public       postgres    false    201            Θ            1259    16457    STEPS_ID_seq    SEQUENCE     w   CREATE SEQUENCE public."STEPS_ID_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."STEPS_ID_seq";
       public       postgres    false    201    3                       0    0    STEPS_ID_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."STEPS_ID_seq" OWNED BY public."STEPS"."ID";
            public       postgres    false    200            Ε            1259    16432    USERS    TABLE     q   CREATE TABLE public."USERS" (
    "ID" bigint NOT NULL,
    "NAME" text,
    "MAIL" text,
    "PASSWORD" text
);
    DROP TABLE public."USERS";
       public         postgres    false    3                       0    0    TABLE "USERS"    COMMENT     8   COMMENT ON TABLE public."USERS" IS 'stores users data';
            public       postgres    false    197            Δ            1259    16430    USERS_id_seq    SEQUENCE     w   CREATE SEQUENCE public."USERS_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public."USERS_id_seq";
       public       postgres    false    197    3                       0    0    USERS_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public."USERS_id_seq" OWNED BY public."USERS"."ID";
            public       postgres    false    196                       2604    16446    PLANS ID    DEFAULT     j   ALTER TABLE ONLY public."PLANS" ALTER COLUMN "ID" SET DEFAULT nextval('public."PLANS_ID_seq"'::regclass);
 ;   ALTER TABLE public."PLANS" ALTER COLUMN "ID" DROP DEFAULT;
       public       postgres    false    199    198    199                       2604    16462    STEPS ID    DEFAULT     j   ALTER TABLE ONLY public."STEPS" ALTER COLUMN "ID" SET DEFAULT nextval('public."STEPS_ID_seq"'::regclass);
 ;   ALTER TABLE public."STEPS" ALTER COLUMN "ID" DROP DEFAULT;
       public       postgres    false    200    201    201                       2604    16435    USERS ID    DEFAULT     j   ALTER TABLE ONLY public."USERS" ALTER COLUMN "ID" SET DEFAULT nextval('public."USERS_id_seq"'::regclass);
 ;   ALTER TABLE public."USERS" ALTER COLUMN "ID" DROP DEFAULT;
       public       postgres    false    196    197    197                      0    16443    PLANS 
   TABLE DATA               E   COPY public."PLANS" ("OWNER_ID", "ID", "RESULT", "INFO") FROM stdin;
    public       postgres    false    199   8                 0    16459    STEPS 
   TABLE DATA               Z   COPY public."STEPS" ("PLAN_ID", "NAME", "DEADLINE", "COMPLETE", "COST", "ID") FROM stdin;
    public       postgres    false    201   U                 0    16432    USERS 
   TABLE DATA               C   COPY public."USERS" ("ID", "NAME", "MAIL", "PASSWORD") FROM stdin;
    public       postgres    false    197   r                  0    0    PLANS_ID_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."PLANS_ID_seq"', 3, true);
            public       postgres    false    198                       0    0    STEPS_ID_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."STEPS_ID_seq"', 1, true);
            public       postgres    false    200                       0    0    USERS_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."USERS_id_seq"', 7, true);
            public       postgres    false    196                       2606    16451    PLANS PLANS_PK 
   CONSTRAINT     R   ALTER TABLE ONLY public."PLANS"
    ADD CONSTRAINT "PLANS_PK" PRIMARY KEY ("ID");
 <   ALTER TABLE ONLY public."PLANS" DROP CONSTRAINT "PLANS_PK";
       public         postgres    false    199                       2606    16467    STEPS STEPS_PK 
   CONSTRAINT     a   ALTER TABLE ONLY public."STEPS"
    ADD CONSTRAINT "STEPS_PK" PRIMARY KEY ("ID") INCLUDE ("ID");
 <   ALTER TABLE ONLY public."STEPS" DROP CONSTRAINT "STEPS_PK";
       public         postgres    false    201    201            
           2606    16440    USERS USERS_PK 
   CONSTRAINT     a   ALTER TABLE ONLY public."USERS"
    ADD CONSTRAINT "USERS_PK" PRIMARY KEY ("ID") INCLUDE ("ID");
 <   ALTER TABLE ONLY public."USERS" DROP CONSTRAINT "USERS_PK";
       public         postgres    false    197    197                       2606    16452    PLANS OWNER_ID_FK    FK CONSTRAINT        ALTER TABLE ONLY public."PLANS"
    ADD CONSTRAINT "OWNER_ID_FK" FOREIGN KEY ("OWNER_ID") REFERENCES public."USERS"("ID") ON UPDATE CASCADE ON DELETE CASCADE;
 ?   ALTER TABLE ONLY public."PLANS" DROP CONSTRAINT "OWNER_ID_FK";
       public       postgres    false    197    2058    199                       2606    16468    STEPS PLAN_ID    FK CONSTRAINT        ALTER TABLE ONLY public."STEPS"
    ADD CONSTRAINT "PLAN_ID" FOREIGN KEY ("PLAN_ID") REFERENCES public."PLANS"("ID") ON UPDATE CASCADE ON DELETE CASCADE;
 ;   ALTER TABLE ONLY public."STEPS" DROP CONSTRAINT "PLAN_ID";
       public       postgres    false    2060    199    201                  xΡγββ Ε ©            xΡγββ Ε ©            xΡγββ Ε ©     