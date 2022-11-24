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
  `useraddress` VARCHAR(20) NULL DEFAULT NULL,
  `userphonenumber` VARCHAR(20) NULL DEFAULT NULL,
  `joindate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into member
values ('ssafy', '김싸피', 'ssafy', '서울특별시', '010-0000-0000', now()),
       ('admin', '관리자', 'admin', '대전광역시', '010-0000-0000', now());
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
values('ssafy', '스무살 첫 자취방 후기', '관악구 서울대 근처에서 자취했었습니다.'), 
      ('ssafy', '대전 유성구 충대 근처 단기 매물 추천', '충남대 학사 마을 추천합니다~!'),
      ('admin', '쾌적했던 32평 아파트 후기입니다', '용산구 근처 아파트였고요, 정말 좋았어요!'),
      ('admin', '자취 5년차의 좋은 방 구하는 법', '진짜 좋은 방을 구하려면요~!'), 
      ('admin', '단기 매물은 원래 잘 없나요?', '대전 유성구 근처 단기 매물 구하고 있는데요'),
      ('ssafy', '네 가족 살만한 집', '23평 아파트'),
      ('ssafy', '청주 분평동 근처 네 가족 아파트 후기', '현대 대우 아파트 추천 드립니다'), 
      ('ssafy', '단독 주택 비추 후기', '경기도 단독 주택에 2년 살았었는데요, 비추합니다'),
       ('ssafy', '대전 유성구 충대 근처 단기 매물 추천', '충남대 학사 마을 추천합니다~!'),
      ('admin', '쾌적했던 32평 아파트 후기입니다', '용산구 근처 아파트였고요, 정말 좋았어요!'),
      ('admin', '자취 5년차의 좋은 방 구하는 법', '진짜 좋은 방을 구하려면요~!'), 
     ('ssafy', '스무살 첫 자취방 후기', '관악구 서울대 근처에서 자취했었습니다.'), 
      ('admin', '네 가족 살만한 집', '23평 아파트'),
      ('ssafy', '대전 충남대 학사 마을 후기', '2년 살았고요, 좋았어요! 추천!'),
      ('admin', '쾌적했던 32평 아파트 후기입니다', '용산구 근처 아파트였고요, 정말 좋았어요!');
      
commit;
----------------------------------------------------------------
CREATE TABLE `happyhouse`.`favor` (
  `favorno` int(10) NOT NULL AUTO_INCREMENT,
  `userid` VARCHAR(16) NOT NULL,
  `dongcode` VARCHAR(10) NOT NULL,
  `sidoname` VARCHAR(20) NULL DEFAULT NULL,
  `gugunname` VARCHAR(20) NULL DEFAULT NULL,
  `dongname` VARCHAR(20) NULL DEFAULT NULL,
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
  
insert into favor(userid, dongcode, sidoname, gugunname, dongname, joindate)
values('ssafy', '1111010100', '서울특별시', '종로구', '청운동', now());
commit;
