-- acm_basic.acm_member definition

CREATE TABLE `acm_member` (
  `USER_ID` varchar(30) NOT NULL COMMENT '회원 ID',
  `USER_NM` varchar(50) DEFAULT NULL COMMENT '성명',
  `USER_PWD` varchar(100) DEFAULT NULL COMMENT '비밀번호',
  `SEX` varchar(1) DEFAULT NULL COMMENT '성별',
  `USER_ROLE` varchar(20) DEFAULT NULL COMMENT '사용자 권한(사용자, 관리자, 강사)',
  `ADMIN_ROLE` varchar(20) DEFAULT NULL COMMENT '관리자 권한(관리자에 사용)',
  `BIRTH_DAY` varchar(8) DEFAULT NULL COMMENT '생일(생년월일)',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '이메일',
  `ZIP_CODE` varchar(10) DEFAULT NULL COMMENT '우편번호',
  `ADDRESS1` varchar(200) DEFAULT NULL COMMENT '자택주소',
  `ADDRESS2` varchar(200) DEFAULT NULL COMMENT '자택주소 상세',
  `USER_POINT` varchar(50) DEFAULT NULL COMMENT '포인트',
  `MEMO` varchar(2000) DEFAULT NULL COMMENT '메모(관리자에 사용)',
  `PIC` varchar(100) DEFAULT NULL COMMENT '사진(소)(강사에 사용)',
  `IS_USE` varchar(1) DEFAULT NULL COMMENT '상태',
  `REG_DT` datetime DEFAULT NULL COMMENT '등록일',
  `REG_ID` varchar(30) DEFAULT NULL COMMENT '등록자 아이디',
  `UPD_DT` datetime DEFAULT NULL COMMENT '수정일',
  `UPD_ID` varchar(30) DEFAULT NULL COMMENT '수정자 아이디',
  `ISOK_SMS` varchar(1) DEFAULT NULL COMMENT '문자수신여부',
  `ISOK_EMAIL` varchar(1) DEFAULT NULL COMMENT '이메일수신여부',
  `TOKEN` varchar(256) DEFAULT NULL COMMENT '로그인 token',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;