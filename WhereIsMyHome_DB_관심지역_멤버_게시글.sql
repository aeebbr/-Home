CREATE SCHEMA IF NOT EXISTS `happyhouse` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `happyhouse` ;

DROP TABLE IF EXISTS `happyhouse`.`favor`;
DROP TABLE IF EXISTS `happyhouse`.`article`;
DROP TABLE IF EXISTS `happyhouse`.`member`;
----------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`member` (
  `userid` VARCHAR(16) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `userpwd` VARCHAR(16) NOT NULL,
  `address` VARCHAR(20) NULL DEFAULT NULL,
  `phonenumber` VARCHAR(20) NULL DEFAULT NULL,
  `joindate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into member
values ('ssafy', '김싸피', 'ssafy', 'ssafy@ssafy.com', now()),
       ('admin', '관리자', 'admin', 'admin@ssafy.com', now());
commit;
----------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `happyhouse`.`article` (
  `articleno` INT NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(16) NOT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT 0,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`articleno`),
  INDEX `article_to_member_fk` (`userid` ASC) VISIBLE,
  CONSTRAINT `article_to_member_fk`
    FOREIGN KEY (`userid`)
    REFERENCES `happyhouse`.`member` (`userid`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into article(userid, subject, content) 
values('ssafy', 'Vue Axios 연동', 'Vue를 이용한 HTTP 통신'), 
      ('ssafy', 'Vue를 배워봅시다', 'Vue와 Spring을 연동하자~'),
      ('admin', '뷰와 스프링부트를 이용한 실전 프로젝트', '프로젝트를 직접만드는 내용.'),
      ('ssafy', '프론트엔드 프레임워크', 'Vue는 프론트엔드의 인기있는 프레임워크 입니다.');
commit;
----------------------------------------------------------------
CREATE TABLE `happyhouse`.`favor` (
  `favorno` int(10) NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(16) NOT NULL,
  `dongcode` VARCHAR(10) NOT NULL,
  `joindate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`favorno`),
  INDEX `favor_to_member_fk` (`userid` ASC) VISIBLE,
  CONSTRAINT `favor_to_member_fk`
    FOREIGN KEY (`userid`)
    REFERENCES `happyhouse`.`member` (`userid`),
  CONSTRAINT `favor_to_dongcode_fk`
	FOREIGN KEY (`dongcode`)
    REFERENCES `dongcode` (`dongCode`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
  
insert into favor(userid, dongcode, joindate)
values('ssafy', '1111010100', now());
commit;