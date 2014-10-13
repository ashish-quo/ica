CREATE TABLE `tadignetwork` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `network_id` int(11) DEFAULT NULL,
  `tadig` varchar(10) DEFAULT NULL,
  `mcc` int(11) DEFAULT NULL,
  `mnc` int(11) DEFAULT NULL,
  `network_name` varchar(100) DEFAULT NULL,
  `network_group` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1875 DEFAULT CHARSET=latin1;


CREATE TABLE country2countrymap
(
  id int(11) NOT NULL AUTO_INCREMENT,
  homeid smallint,
  homecountry character(100),
  visitorid smallint,
  visitedcountry character (100),
  isbordering boolean,
  issamecontinent boolean,
  CONSTRAINT country2countrymap_pkey PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE country_ib
(
  id int(11) NOT NULL AUTO_INCREMENT,
  homeid smallint,
  homecountry character(100),
  visitedcountry character(100),
  visitorid smallint,
  bordering character(20),
  samecontinent smallint,
  CONSTRAINT country_ib_pkey PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `opcoid` varchar(20) DEFAULT NULL,
  `roamtype` varchar(5) DEFAULT NULL,
  `homemcc` int(11) DEFAULT NULL,
  `homemnc` int(11) DEFAULT NULL,
  `visitedmcc` int(11) DEFAULT NULL,
  `visitedmnc` int(11) DEFAULT NULL,
  `imsi` bigint(20) DEFAULT NULL,
  `msisdn` bigint(20) DEFAULT NULL,
  `starttime` bigint(20) DEFAULT NULL,
  `endtime` bigint(20) DEFAULT NULL,
  `imei` varchar(20) DEFAULT NULL,
  `tac` varchar(8) DEFAULT NULL,
  `chargingplan` smallint(6) DEFAULT NULL,
  `priceplan` varchar(20) DEFAULT NULL,
  `mocallcount` bigint(20) DEFAULT NULL,
  `mocallminutes` bigint(20) DEFAULT NULL,
  `mtcallcount` bigint(20) DEFAULT NULL,
  `mtcallminutes` bigint(20) DEFAULT NULL,
  `mosmscount` bigint(20) DEFAULT NULL,
  `mtsmscount` bigint(20) DEFAULT NULL,
  `uplink` bigint(20) DEFAULT NULL,
  `downlink` bigint(20) DEFAULT NULL,
  `mocallcountlocal` bigint(20) DEFAULT NULL,
  `mocallcounthome` bigint(20) DEFAULT NULL,
  `mocallcountothers` bigint(20) DEFAULT NULL,
  `mocallminuteslocal` bigint(20) DEFAULT NULL,
  `mocallminuteshome` bigint(20) DEFAULT NULL,
  `mocallminutesothers` bigint(20) DEFAULT NULL,
  `mtcallcountlocal` bigint(20) DEFAULT NULL,
  `mtcallcounthome` bigint(20) DEFAULT NULL,
  `mtcallcountothers` bigint(20) DEFAULT NULL,
  `mtcallminuteslocal` bigint(20) DEFAULT NULL,
  `mtcallminuteshome` bigint(20) DEFAULT NULL,
  `mtcallminutesothers` bigint(20) DEFAULT NULL,
  `mosmscountlocal` bigint(20) DEFAULT NULL,
  `mosmscounthome` bigint(20) DEFAULT NULL,
  `mosmscountothers` bigint(20) DEFAULT NULL,
  `mtsmscountlocal` bigint(20) DEFAULT NULL,
  `mtsmscounthome` bigint(20) DEFAULT NULL,
  `mtsmscountothers` bigint(20) DEFAULT NULL,
  `mocallcountquadroam` smallint(6) DEFAULT NULL,
  `mocallminutesquadroam` smallint(6) DEFAULT NULL,
  `mtcallcountquadroam` smallint(6) DEFAULT NULL,
  `mtcallminutesquadroam` smallint(6) DEFAULT NULL,
  `mosmscountquadroam` smallint(6) DEFAULT NULL,
  `mtsmscountquadroam` smallint(6) DEFAULT NULL,
  `uplinkquadroam` smallint(6) DEFAULT NULL,
  `downlinkquadroam` smallint(6) DEFAULT NULL,
  `tonnagequadroam` smallint(6) DEFAULT NULL,
  `overalltripcategory` smallint(6) DEFAULT NULL,
  `mocallcountquaddomestic` smallint(6) DEFAULT NULL,
  `mocallminutesquaddomestic` smallint(6) DEFAULT NULL,
  `mtcallcountquaddomestic` smallint(6) DEFAULT NULL,
  `mtcallminutesquaddomestic` smallint(6) DEFAULT NULL,
  `mosmscountquaddomestic` smallint(6) DEFAULT NULL,
  `mtsmscountquaddomestic` smallint(6) DEFAULT NULL,
  `uplinkquaddomestic` smallint(6) DEFAULT NULL,
  `downlinkquaddomestic` smallint(6) DEFAULT NULL,
  `tonnagequaddomestic` smallint(6) DEFAULT NULL,
  `overalldomesticcategory` smallint(6) DEFAULT NULL,
  `msisdntimestamp` bigint(20) DEFAULT NULL,
  `imeitimestamp` bigint(20) DEFAULT NULL,
  `chargingplanapproximity` smallint(6) DEFAULT NULL,
  `starttimestring` varchar(20) DEFAULT NULL,
  `endtimestring` varchar(20) DEFAULT NULL,
  `visitedcountryname` varchar(100) DEFAULT NULL,
  `visitednetworkname` varchar(100) DEFAULT NULL,
  `homecountryname` varchar(100) DEFAULT NULL,
  `homenetworkname` varchar(100) DEFAULT NULL,
  `devicename` varchar(1000) DEFAULT NULL,
  `devicemanufacturer` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3437 DEFAULT CHARSET=latin1;

CREATE TABLE `triptime` (
  `opcoid` varchar(20) DEFAULT NULL,
  `roamtype` varchar(5) DEFAULT NULL,
  `homemcc` int(11) DEFAULT NULL,
  `homemnc` int(11) DEFAULT NULL,
  `visitedmcc` int(11) DEFAULT NULL,
  `visitedmnc` int(11) DEFAULT NULL,
  `imsi` bigint(20) DEFAULT NULL,
  `tripstarttime` bigint(20) DEFAULT NULL,
  `tripendtime` bigint(20) DEFAULT NULL,
  `usagebintime` bigint(20) DEFAULT NULL,
  `mocallcount` bigint(20) DEFAULT NULL,
  `mocallminutes` bigint(20) DEFAULT NULL,
  `mtcallcount` bigint(20) DEFAULT NULL,
  `mtcallminutes` bigint(20) DEFAULT NULL,
  `mosmscount` bigint(20) DEFAULT NULL,
  `mtsmscount` bigint(20) DEFAULT NULL,
  `uplink` bigint(20) DEFAULT NULL,
  `downlink` bigint(20) DEFAULT NULL,
  `mocallcountlocal` bigint(20) DEFAULT NULL,
  `mocallcounthome` bigint(20) DEFAULT NULL,
  `mocallcountothers` bigint(20) DEFAULT NULL,
  `mocallminuteslocal` bigint(20) DEFAULT NULL,
  `mocallminuteshome` bigint(20) DEFAULT NULL,
  `mocallminutesothers` bigint(20) DEFAULT NULL,
  `mtcallcountlocal` bigint(20) DEFAULT NULL,
  `mtcallcounthome` bigint(20) DEFAULT NULL,
  `mtcallcountothers` bigint(20) DEFAULT NULL,
  `mtcallminuteslocal` bigint(20) DEFAULT NULL,
  `mtcallminuteshome` bigint(20) DEFAULT NULL,
  `mtcallminutesothers` bigint(20) DEFAULT NULL,
  `mosmscountlocal` bigint(20) DEFAULT NULL,
  `mosmscounthome` bigint(20) DEFAULT NULL,
  `mosmscountothers` bigint(20) DEFAULT NULL,
  `mtsmscountlocal` bigint(20) DEFAULT NULL,
  `mtsmscounthome` bigint(20) DEFAULT NULL,
  `mtsmscountothers` bigint(20) DEFAULT NULL,
  `tripstarttimestring` varchar(20) DEFAULT NULL,
  `tripendtimestring` varchar(20) DEFAULT NULL,
  `visitedcountryname` varchar(100) DEFAULT NULL,
  `visitednetworkname` varchar(100) DEFAULT NULL,
  `homecountryname` varchar(100) DEFAULT NULL,
  `homenetworkname` varchar(100) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE INDEX trip_visitedcountryname ON trip (visitedcountryname);
CREATE INDEX trip_starttime ON trip (starttime);
CREATE INDEX trip_endtime ON trip (endtime);
CREATE INDEX trip_roamtype ON trip (roamtype);
CREATE INDEX trip_homecountryname ON trip (homecountryname);
CREATE INDEX trip_overalltripcategory ON trip (overalltripcategory);

CREATE INDEX triptime_starttime ON triptime (usagebintime);
CREATE INDEX triptime_imsi ON triptime (imsi);

