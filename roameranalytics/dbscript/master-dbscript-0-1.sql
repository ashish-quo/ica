-- Table: attribute

-- DROP TABLE attribute;

CREATE TABLE attribute
(
  id SERIAL NOT NULL,
  attribute_name character varying(50),
  module_id character varying(15),
  type integer, -- type of attributr. dropdown/checkbox/radio
  icon character varying(20),
  view_type character varying(10),
  display_order integer,
  attr_ind integer, -- attribute indicator
  CONSTRAINT attribute_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE attribute
  OWNER TO postgres;
COMMENT ON COLUMN attribute.type IS 'type of attributr. dropdown/checkbox/radio';


-- Table: attribute_category

-- DROP TABLE attribute_category;

CREATE TABLE attribute_category
(
  id SERIAL NOT NULL,
  category_name character varying(50), -- stores the attribute details of particular attribute id
  attribute_id integer,
  icon character varying(20),
  display_order integer,
  cat_ind integer, -- category indicator
  CONSTRAINT attribute_category_pkey PRIMARY KEY (id),
  CONSTRAINT attribute_category_Fkey FOREIGN KEY (attribute_id) REFERENCES ATTRIBUTE(ID)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE attribute_category
  OWNER TO postgres;
COMMENT ON TABLE attribute_category
  IS 'stores attribute details as per attribute id';
COMMENT ON COLUMN attribute_category.category_name IS 'stores the attribute details of particular attribute id';


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

-- Table: country

-- DROP TABLE country;

CREATE TABLE country
(
  id integer NOT NULL,
  country_code character varying(3),
  country_name character varying(30),
  dial_code integer,
  CONSTRAINT country_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE country
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

-- Table: "user"

-- DROP TABLE "user";

CREATE TABLE "user"
(
  id integer NOT NULL,
  username character varying(50),
  password character varying(100),
  lastname character varying(30),
  firstname character varying(30),
  mobile character varying(15),
  group_id integer,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "user"
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
  visitedcountryname character varying(30),
  visitednetworkname character varying(30),
  homecountryname character varying(30),
  homenetworkname character varying(30),
  devicename character varying(30),
  devicemanufacturer character varying(20),
  CONSTRAINT trip_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE trip
  OWNER TO postgres;

 -- Table triptime added by smruti on 22-08-2014
  
  -- Table: triptime

-- DROP TABLE triptime;

CREATE TABLE triptime
(
  id bigserial NOT NULL,
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
  tripstarttimestring character varying(20),
  tripendtimestring character varying(20),
  visitedcountryname character varying(30),
  visitednetworkname character varying(30),
  homecountryname character varying(30),
  homenetworkname character varying(30),
  CONSTRAINT triptime_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE triptime
  OWNER TO postgres;

