--
-- PostgreSQL database dump
--

-- Dumped from database version 10.6 (Ubuntu 10.6-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.6 (Ubuntu 10.6-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: licio
--

CREATE TABLE public.clients (
    id integer NOT NULL,
    clientname character varying,
    clientphone character varying,
    clientemail character varying,
    stylistid integer
);


ALTER TABLE public.clients OWNER TO licio;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: licio
--

CREATE SEQUENCE public.clients_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO licio;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: licio
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: licio
--

CREATE TABLE public.stylists (
    id integer NOT NULL,
    stylistname character varying,
    stylistphone character varying,
    stylistemail character varying
);


ALTER TABLE public.stylists OWNER TO licio;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: licio
--

CREATE SEQUENCE public.stylists_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stylists_id_seq OWNER TO licio;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: licio
--

ALTER SEQUENCE public.stylists_id_seq OWNED BY public.stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: licio
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner: licio
--

ALTER TABLE ONLY public.stylists ALTER COLUMN id SET DEFAULT nextval('public.stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: licio
--

COPY public.clients (id, clientname, clientphone, clientemail, stylistid) FROM stdin;
4	client	234567	rfghj@dg	1
\.


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: licio
--

COPY public.stylists (id, stylistname, stylistphone, stylistemail) FROM stdin;
1	stylist	345	test@test.com
2	style2	345	style@test.com
3	style3	3456	he@sdgb
4	style 4	890990	style@style4.com
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: licio
--

SELECT pg_catalog.setval('public.clients_id_seq', 4, true);


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: licio
--

SELECT pg_catalog.setval('public.stylists_id_seq', 4, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: licio
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: licio
--

ALTER TABLE ONLY public.stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

