-- Table: attribute

-- DROP TABLE attribute;

CREATE TABLE attribute
(
  id integer NOT NULL,
  attribute_name character varying(50),
  module_id character varying(15) DEFAULT NULL::character varying,
  display_order integer,
  db_column character varying(50),
  column_type character varying(50),
  chart_type smallint, -- Column for Chart type in microsegment
  db_column_in character varying(50),
  CONSTRAINT attribute_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE attribute
  OWNER TO postgres;
COMMENT ON COLUMN attribute.chart_type IS 'Column for Chart type in microsegment';




-- Table: attribute_category

-- DROP TABLE attribute_category;

CREATE TABLE attribute_category
(
  id integer NOT NULL,
  categ_name character varying(100), -- stores the attribute details of particular attribute id
  display_order integer,
  attr_id integer,
  categ_value character varying(5000) NOT NULL,
  CONSTRAINT attribute_category_pkey PRIMARY KEY (id),
  CONSTRAINT attribute_category_fkey FOREIGN KEY (attr_id)
      REFERENCES attribute (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE attribute_category
  OWNER TO postgres;
COMMENT ON TABLE attribute_category
  IS 'stores attribute details as per attribute id';
COMMENT ON COLUMN attribute_category.categ_name IS 'stores the attribute details of particular attribute id';


-- Table: bookmark

-- DROP TABLE bookmark;

CREATE TABLE bookmark
(
  id integer NOT NULL,
  search_filter character varying(1000),
  bookmark_name character varying(50),
  created_by integer,
  created_on timestamp without time zone,
  updated_on timestamp without time zone,
  CONSTRAINT bookmark_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE bookmark
  OWNER TO postgres;
  
-- Table: bookmark_tray

-- DROP TABLE bookmark_tray;

CREATE TABLE bookmark_tray
(
  id integer NOT NULL,
  tray_id integer,
  bookmark_id integer,
  CONSTRAINT bookmark_tray_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE bookmark_tray
  OWNER TO postgres;
  
  
  -- Table: campaign

-- DROP TABLE campaign;

CREATE TABLE campaign
(
  id integer NOT NULL,
  search_filter character varying(1000),
  tag_name character varying(50),
  created_by integer,
  created_on timestamp without time zone,
  updated_on timestamp without time zone,
  country character varying(3),
  subscriber_count integer,
  projected_mt integer,
  projected_mo integer,
  projected_data integer,
  projected_sms integer,
  upsell_mt integer,
  upsell_mo integer,
  upsell_data integer,
  upsell_sms integer,
  expected_mt integer,
  expected_mo integer,
  expected_data integer,
  expected_sms integer,
  CONSTRAINT campaign_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE campaign
  OWNER TO postgres;

  
  -- Table: comment_details

-- DROP TABLE comment_details;

CREATE TABLE comment_details
(
  id integer NOT NULL,
  created_by integer,
  reply_to integer,
  comment_text character varying(500),
  comment_id integer,
  created_on timestamp without time zone,
  CONSTRAINT comment_details_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE comment_details
  OWNER TO postgres;

  -- Table: comment_list

-- DROP TABLE comment_list;

CREATE TABLE comment_list
(
  id integer NOT NULL,
  search_filter character varying(1000),
  created_by integer,
  created_on timestamp without time zone,
  CONSTRAINT comment_list_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE comment_list
  OWNER TO postgres;

-- Table: country_ib

-- DROP TABLE country_ib;

CREATE TABLE country_ib
(
  id serial NOT NULL,
  homeid smallint,
  homecountry character varying(100),
  visitedcountry character varying(100),
  visitorid smallint,
  bordering character varying(20),
  samecontinent smallint,
  CONSTRAINT country_ib_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE country_ib
  OWNER TO postgres;

  
  -- Table: country2countrymap

-- DROP TABLE country2countrymap;

CREATE TABLE country2countrymap
(
  id serial NOT NULL,
  homeid smallint,
  homecountry character varying(100),
  visitorid smallint,
  visitedcountry character varying(100),
  isbordering boolean,
  issamecontinent boolean,
  CONSTRAINT country2countrymap_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE country2countrymap
  OWNER TO postgres;


-- Table: "group"

-- DROP TABLE "group";

CREATE TABLE "group"
(
  id integer NOT NULL,
  name character varying(15),
  role_id integer,
  CONSTRAINT group_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "group"
  OWNER TO postgres;

  
  -- Table: role

-- DROP TABLE role;

CREATE TABLE role
(
  id integer NOT NULL,
  name character varying(15),
  CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE role
  OWNER TO postgres;
  
  
  
  -- Table: tag

-- DROP TABLE tag;

CREATE TABLE tag
(
  id integer NOT NULL,
  search_filter character varying(1000),
  tag_name character varying(50),
  created_by integer,
  created_on timestamp without time zone,
  updated_on timestamp without time zone,
  CONSTRAINT tag_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tag
  OWNER TO postgres;
  
  -- Table: tray

-- DROP TABLE tray;

CREATE TABLE tray
(
  id integer NOT NULL,
  tray_name character varying(50),
  created_by integer,
  created_on timestamp without time zone,
  updated_on timestamp without time zone,
  CONSTRAINT tray_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tray
  OWNER TO postgres;

-- Table userinfo added by smruti on 29-08-2014

-- DROP TABLE userinfo;

CREATE TABLE userinfo
(
  id serial NOT NULL,
  username character varying(50),
  password character varying(100),
  enabled boolean,
  CONSTRAINT userinfo_pkey PRIMARY KEY (id),
  CONSTRAINT userinfo_username_key UNIQUE (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE userinfo
  OWNER TO postgres;

  
 -- Table trip added by smruti on 27-08-2014
 
  -- Table: user_roles

-- DROP TABLE user_roles;

CREATE TABLE user_roles
(
  user_role_id serial NOT NULL,
  user_id integer,
  role_name character varying(10),
  CONSTRAINT user_roles_pkey PRIMARY KEY (user_role_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_roles
  OWNER TO postgres;
  
 -- Table trip added by smruti on 22-08-2014
 
-- Table: trip

-- DROP TABLE trip;

CREATE TABLE trip
(
  id bigserial NOT NULL,
  opcoid character varying(20),
  roamtype character varying(5),
  homemcc integer,
  homemnc integer,
  visitedmcc integer,
  visitedmnc integer,
  imsi bigint,
  msisdn bigint,
  starttime bigint,
  endtime bigint,
  imei character varying(20),
  tac character varying(8),
  chargingplan smallint,
  priceplan character varying(20),
  mocallcount bigint,
  mocallminutes bigint,
  mtcallcount bigint,
  mtcallminutes bigint,
  mosmscount bigint,
  mtsmscount bigint,
  uplink bigint,
  downlink bigint,
  mocallcountlocal bigint,
  mocallcounthome bigint,
  mocallcountothers bigint,
  mocallminuteslocal bigint,
  mocallminuteshome bigint,
  mocallminutesothers bigint,
  mtcallcountlocal bigint,
  mtcallcounthome bigint,
  mtcallcountothers bigint,
  mtcallminuteslocal bigint,
  mtcallminuteshome bigint,
  mtcallminutesothers bigint,
  mosmscountlocal bigint,
  mosmscounthome bigint,
  mosmscountothers bigint,
  mtsmscountlocal bigint,
  mtsmscounthome bigint,
  mtsmscountothers bigint,
  mocallcountquadroam smallint,
  mocallminutesquadroam smallint,
  mtcallcountquadroam smallint,
  mtcallminutesquadroam smallint,
  mosmscountquadroam smallint,
  mtsmscountquadroam smallint,
  uplinkquadroam smallint,
  downlinkquadroam smallint,
  tonnagequadroam smallint,
  overalltripcategory smallint,
  mocallcountquaddomestic smallint,
  mocallminutesquaddomestic smallint,
  mtcallcountquaddomestic smallint,
  mtcallminutesquaddomestic smallint,
  mosmscountquaddomestic smallint,
  mtsmscountquaddomestic smallint,
  uplinkquaddomestic smallint,
  downlinkquaddomestic smallint,
  tonnagequaddomestic smallint,
  overalldomesticcategory smallint,
  msisdntimestamp bigint,
  imeitimestamp bigint,
  chargingplanapproximity smallint,
  starttimestring character varying(20),
  endtimestring character varying(20),
  visitedcountryname character varying(100),
  visitednetworkname character varying(100),
  homecountryname character varying(100),
  homenetworkname character varying(100),
  devicename character varying(1000),
  devicemanufacturer character varying(1000),
  CONSTRAINT trip_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE trip
  OWNER TO postgres;

-- Index: trip_endtime

-- DROP INDEX trip_endtime;

CREATE INDEX trip_endtime
  ON trip
  USING btree
  (endtime);

-- Index: trip_starttime

-- DROP INDEX trip_starttime;

CREATE INDEX trip_starttime
  ON trip
  USING btree
  (starttime);

-- Index: trip_time_range

-- DROP INDEX trip_time_range;

CREATE INDEX trip_time_range
  ON trip
  USING btree
  (starttime, endtime);


 --- Table: triptime

-- DROP TABLE triptime;

CREATE TABLE triptime
(
  opcoid character varying(20),
  roamtype character varying(5),
  homemcc integer,
  homemnc integer,
  visitedmcc integer,
  visitedmnc integer,
  imsi bigint,
  tripstarttime bigint,
  tripendtime bigint,
  usagebintime bigint,
  mocallcount bigint,
  mocallminutes bigint,
  mtcallcount bigint,
  mtcallminutes bigint,
  mosmscount bigint,
  mtsmscount bigint,
  uplink bigint,
  downlink bigint,
  mocallcountlocal bigint,
  mocallcounthome bigint,
  mocallcountothers bigint,
  mocallminuteslocal bigint,
  mocallminuteshome bigint,
  mocallminutesothers bigint,
  mtcallcountlocal bigint,
  mtcallcounthome bigint,
  mtcallcountothers bigint,
  mtcallminuteslocal bigint,
  mtcallminuteshome bigint,
  mtcallminutesothers bigint,
  mosmscountlocal bigint,
  mosmscounthome bigint,
  mosmscountothers bigint,
  mtsmscountlocal bigint,
  mtsmscounthome bigint,
  mtsmscountothers bigint,
  tripstarttimestring character varying(20),
  tripendtimestring character varying(20),
  visitedcountryname character varying(100),
  visitednetworkname character varying(100),
  homecountryname character varying(100),
  homenetworkname character varying(100),
  id bigserial NOT NULL,
  CONSTRAINT triptime_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE triptime
  OWNER TO postgres;

-- Index: triptime_imsi

-- DROP INDEX triptime_imsi;

CREATE INDEX triptime_imsi
  ON triptime
  USING btree
  (imsi);

-- Index: triptime_tripstarttime

-- DROP INDEX triptime_tripstarttime;

CREATE INDEX triptime_tripstarttime
  ON triptime
  USING btree
  (tripstarttime);

-- Index: triptime_usagebintime

-- DROP INDEX triptime_usagebintime;

CREATE INDEX triptime_usagebintime
  ON triptime
  USING btree
  (usagebintime);



  -- Table: tadignetwork

-- DROP TABLE tadignetwork;

CREATE TABLE tadignetwork
(
  id serial NOT NULL,
  network_id integer,
  tadig character varying(10),
  mcc integer,
  mnc integer,
  network_name character varying(100),
  network_group character varying(50),
  CONSTRAINT tadignetwork_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tadignetwork
  OWNER TO postgres;
