CREATE TABLE BOARD (
	BOARD_NO bigint auto_increment
,	BOARD_TITLE varchar(200) not null
,	BOARD_CONTENTS varchar(max) not null
,	BOARD_GUBUN varchar(10) not null
,	REG_DT datetime not null
,	REG_CUST_NO bigint not null
,	CHG_DT datetime
,	CHG_CUST_NO bigint
,	IP varchar(30)
,	USE_YN varchar(2)
);