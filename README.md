# Java-MiniProject : Dear.Diary

Dear.Diary는 
매일 자신의 감정과 연관된 상태를 간단히 기록하고, 
다른 사람의 기록을 살펴보는 과정을 통해
나와 주변의 마음건강을 유지하도록 돕는
셀프 멘탈 헬스케어 체크 SNS입니다.


## Why we select this Topic?

### topic 

<div>
<img width="800" src=https://user-images.githubusercontent.com/73386460/101110209-dcb81300-361b-11eb-8e5a-75cf0469f02a.jpg><br>
	<center> <출처> http://dongascience.donga.com/news.php?idx=41968 </center>

#### 정신줄 꽉 잡아!
사회적 거리두기의 일상화로 우울감이 심해지고 있는 
포스트 코로나 시대,

사람들은 다양한 방식으로 이를 해결하려 노력하고 있습니다. 

정신건강 전문의들은 
사소한 것이라도 자신의 감정을 글로 적어보면 
그 감정에서 떨어져서 바라볼 수 있기 때문에 마음을 다스리는데 도움이 된다고 추천합니다. 
또 이럴 때일수록 서로를 살피고 공감하는 것이 필요하다고 권합니다.


#### 바쁘다 바빠 현대사회
하지만... 유튜브, TV, 스마트폰 등 넘쳐나는 재미있는 

넘쳐나는 
마지막으로 일기를 써본 적이 언제인지도 모르겠어요. 


#### 지피지기


#### 이심전심

사람들은 당시 어떤일이 있었는지 같은 구체적 상황보다 그 때 자신이 느꼈던 감정으로 더 많은 것을 기억합니다.   



### Programing 

1. 
2.
3.


## STRUCTURE 
<div>
<img width="1000" src=https://lh4.googleusercontent.com/dVuPisLaZvbbO7v7B0Yf3T4AXV2zYE-7L7lMroRW51R2pD9GyZVJn1DlOquyORKzETP0_A3eH8OQpMqBZKCMJF7LS0YUBBU4Uyrf3-e02mpv3uciUoatxd3H92q2aEB7xiKuKZtzZA>
	

## MODELING 	
	



## Service 

### 1. USER 측면
	1) 내 아이디로 로그인을 하고 들어간다
	2) 오늘의 일기장을 작성한다
	3) 내 커플ID 이용자의 오늘의 일기도 확인한다 
	4) 나의 지난주랑 이번주 기분점수를 총합하여 비교해서 출력 
		>> 점수 (지난주 점수 - 이번주 점수) , 
		   코멘트 (지난주보다 좋으면 상태 좋아짐, 안 좋으면 안 좋아짐)
	+ 기분점수가 지난주보다 낮다면 ?? 
		>> 이번주 잠잔시간 평균- 내 평균 수면시간과 비교 -권장시간(8시간) 비교
		>>(-)면 주말에 푹 쉬세요 ! 
	  		(+)면 푹 쉬셨네요 ! 주말엔 00해보는게 어떨까요 ~ !
	+ 검색하는 감정으로 일기를 쓴 사람을 익명으로 보여주기

### 2. ADMIN 측면 

+ 모든 다이어리 정보 가져오기
+ 감정 테이블에 감정 추가/삭제
+ 날씨 테이블에 날씨 추가/삭제


                                          
## SQL TABLE 

+ USER INFORMAITON 

```SQL
drop table USERINFO;

create table USERINFO(
   id varchar2(50) constraint userinfo_id_pk primary key,
   pw number(7) not null,
   matchingid varchar2(50)
); 

insert into USERINFO values('ace123', '123456', 'coral2');
insert into USERINFO values('bestyou1', '1111111', 'doglover3');
insert into USERINFO values('coral2', '222222', 'ace123');
insert into USERINFO values('doglover3', '333333', 'bestyou1');
insert into USERINFO values('enough4', '444444', null);
insert into USERINFO values('forever5', '555555', null); 
insert into USERINFO values('admin', '1234', null); 

COMMIT;         
```

+ EMOTIONS

```SQL
drop table EMOTIONS;

create table EMOTIONS(
   emotionno number(1) constraint emotions_emotionno_pk primary key,
   emotionstats varchar2(20) not null   
); 

		
		
insert into EMOTIONS values(1, '화가나');
insert into EMOTIONS values(2, '짜증나');
insert into EMOTIONS values(3, '걱정돼');
insert into EMOTIONS values(4, '그럭저럭'); 
insert into EMOTIONS values(5, '평온해');
insert into EMOTIONS values(6,' 최고');

COMMIT;         
```

+ WEATHER 

```SQL
drop table WEATHER;

create table WEATHER(
   weatherno number(1) constraint weather_weatherno_pk primary key,
   weatherstats varchar2(20) not null   
); 


insert into WEATHER values(1, '따뜻해');
insert into WEATHER values(2, '더워');
insert into WEATHER values(3, '비가와');
insert into WEATHER values(4, '추워');
insert into WEATHER values(5, '구름둥둥');
insert into WEATHER values(6, '눈온다'); 

COMMIT;         
```
+ DIARY 

```SQL
drop table DIARY;

create table DIARY(
   diaryno number(2) constraint diary_diaryno_pk primary key, 
   id varchar2(50) constraint diary_id_fk references USERINFO(id),
   emotionno number(1) constraint diary_emotionno_fk references EMOTIONS(emotionno),
   weatherno number(1) constraint diary_weatherno_fk references WEATHER(weatherno),
   reportingdate DATE,
   sleepingtime number(2) not null, 
   diarycomment varchar2(200) not null, 
   isPublic number(1) not null 
); 


insert into DIARY values(1, 'ace123', 6, 1, to_date('2020-12-01'), 6, '돈 주웠다 최고!', 2);
insert into DIARY values(2, 'bestyou1', 5, 1, to_date('2020-12-01'), 7, '오늘 하루도 끝', 2);
insert into DIARY values(3, 'coral2', 6, 2, to_date('2020-12-01'), 8, '도자기가 잘 구워졌다', 2);
insert into DIARY values(4, 'doglover3', 6, 1, to_date('2020-12-01'), 8, '예약해둔 맛집에서 저녁 먹었다', 2);
insert into DIARY values(5, 'enough4', 5, 1, to_date('2020-12-01'), 7, '저녁이 맛있었다.', 1);
insert into DIARY values(6, 'forever5', 3, 1, to_date('2020-12-01'), 5, '12월 첫날이라니 벌써...', 1);
insert into DIARY values(7, 'ace123', 5, 3, to_date('2020-12-02'), 6, '무난무난한 하루였다.', 2);
insert into DIARY values(8, 'bestyou1', 6, 3, to_date('2020-12-02'), 7, '보고싶었던 공연을 봤다', 2);
insert into DIARY values(9, 'coral2', 4, 3, to_date('2020-12-02'), 8, '배고픈데 간식을 선물받았다', 2);
insert into DIARY values(10, 'doglover3', 2, 3, to_date('2020-12-02'), 6, '아이스크림 바닥에 흘렸다', 2);
insert into DIARY values(11, 'enough4', 5, 3, to_date('2020-12-02'), 6, '택배가 왔다', 1);
insert into DIARY values(12, 'forever5', 2, 3, to_date('2020-12-02'), 7, '지갑을 놓고 나왔다', 1);
insert into DIARY values(13, 'ace123', 4, 4, to_date('2020-12-03'), 5, '내일만 지나면 주말!!', 2);
insert into DIARY values(14, 'bestyou1', 2, 2, to_date('2020-12-03'), 6, '왜 다 나한테 시키지', 1);
insert into DIARY values(15, 'coral2', 5, 1, to_date('2020-12-03'), 5, '알고리즘 문제 4개 풀었다 그리고 드디어 하나 맞았다', 2);
insert into DIARY values(16, 'doglover3', 5, 5, to_date('2020-12-03'), 7, '지하철에서 앉아서 왔다', 2);
insert into DIARY values(17, 'enough4', 5, 5, to_date('2020-12-03'), 3, '길가다 친구를 만났다', 1);
insert into DIARY values(18, 'forever5', 3, 5, to_date('2020-12-03'), 5, '내일 기말고사 마지막 시험', 0);
insert into DIARY values(19, 'ace123', 2, 2, to_date('2020-12-04'), 6, '친구가 아프다고 한다.', 2);
insert into DIARY values(20, 'bestyou1', 5, 2, to_date('2020-12-04'), 7, '떡볶이 먹었다', 2);
insert into DIARY values(21, 'coral2', 2, 2, to_date('2020-12-04'), 5, '되는일이 없었다', 1);
insert into DIARY values(22, 'doglover3', 6, 1, to_date('2020-12-04'), 6, '쇼핑 최고다...진짜', 2);
insert into DIARY values(23, 'enough4', 5, 2, to_date('2020-12-04'), 6, '부장님이 월차를 썼다', 1);
insert into DIARY values(24, 'forever5', 4, 2, to_date('2020-12-04'), 7, '점심 메뉴가 나쁘지 않았다', 0);
insert into DIARY values(25, 'ace123', 6, 3, to_date('2020-12-05'), 7, '월급이 들어왔다!', 2);
insert into DIARY values(26, 'bestyou1', 6, 3, to_date('2020-12-05'), 6, '오랜만에 친구들 만났다', 2);
insert into DIARY values(27, 'coral2', 4, 3, to_date('2020-12-05'), 7, '돈까스를 먹었다', 2);
insert into DIARY values(28, 'doglover3', 1, 3, to_date('2020-12-05'), 5, '엄마가 아프다.', 0);
insert into DIARY values(29, 'enough4', 5, 3, to_date('2020-12-05'), 7, '오늘 요리가 잘됐다', 1);
insert into DIARY values(30, 'forever5', 3, 3, to_date('2020-12-05'), 6, '알바하는데 손님 너무 없어서 지루했다', 1);
insert into DIARY values(31, 'ace123', 4, 4, to_date('2020-12-06'), 6, '평온한 주말이다', 2);
insert into DIARY values(32, 'bestyou1', 6, 4, to_date('2020-12-06'), 8, '강릉에서 킹크랩을 먹었다', 2);
insert into DIARY values(33, 'coral2', 4, 4, to_date('2020-12-06'), 6, '집에서 운동했다', 2);
insert into DIARY values(34, 'doglover3', 3, 4, to_date('2020-12-06'), 5, '지우개 잊어버렸다', 2);
insert into DIARY values(35, 'enough4', 5, 4, to_date('2020-12-06'), 6, '배민 쿠폰 1만원 당첨', 1);
insert into DIARY values(36, 'forever5', 3, 4, to_date('2020-12-06'), 6, '은행업무 보러가기 귀찮다', 1);
insert into DIARY values(37, 'ace123', 4, 1, to_date('2020-12-07'), 7, '일요일이다!', 2);
insert into DIARY values(38, 'bestyou1', 5, 1, to_date('2020-12-07'), 6, '오늘은 버스가 시간에 맞춰왔다', 1);
insert into DIARY values(39, 'coral2', 2, 1, to_date('2020-12-07'), 6, '안경이 깨졌다', 1);
insert into DIARY values(40, 'doglover3', 4, 1, to_date('2020-12-07'), 7, '부장님 없는 틈타 퇴근했다', 1);
insert into DIARY values(41, 'enough4', 6, 1, to_date('2020-12-07'), 7, '생일선물로 조던 받았따', 1);
insert into DIARY values(42, 'forever5', 6, 2, to_date('2020-12-07'), 7, '밀린 드라마 다 몰아봄. 눈이부시게 최고., ', 0);
insert into DIARY values(43, 'ace123', 2, 4, to_date('2020-12-08'), 7, '상사한테 깨졌다', 2);
insert into DIARY values(44, 'bestyou1', 2, 4, to_date('2020-12-08'), 5, '내일은 출장이다', 2);
insert into DIARY values(45, 'coral2', 3, 4, to_date('2020-12-08'), 7, '배고프다', 0);
insert into DIARY values(46, 'doglover3', 4, 4, to_date('2020-12-08'), 6, '새우튀김을 먹었다', 2);
insert into DIARY values(47, 'enough4', 6, 4, to_date('2020-12-08'), 7, '일이 꼬인 줄 알았는데 해결됐다', 0);
insert into DIARY values(48, 'forever5', 5, 4, to_date('2020-12-08'), 6, '오랜만에 친구들 만나서 수다 떨었다.', 0);
insert into DIARY values(49, 'ace123', 3, 5, to_date('2020-12-09'), 4, '실수했다. 어떡하지', 2);
insert into DIARY values(50, 'bestyou1', 3, 5, to_date('2020-12-09'), 3, '출장인지 짐셔틀인지...', 2);
insert into DIARY values(51, 'coral2', 1, 5, to_date('2020-12-09'), 3, '교통사고가 났다.', 2);
insert into DIARY values(52, 'doglover3', 5, 5, to_date('2020-12-09'), 7, '꼬북칩 초코맛 편의점에서 발견해서 3봉지 삼', 1);
insert into DIARY values(53, 'enough4', 1, 5, to_date('2020-12-09'), 0, '친구가 죽었다.....', 0);
insert into DIARY values(54, 'forever5', 3, 5, to_date('2020-12-09'), 7, '길에서 죽은 새를 보았다.', 0);
insert into DIARY values(55, 'ace123', 5, 6, to_date('2020-12-10'), 7, '점심으로 제육볶음을 먹었다.', 2);
insert into DIARY values(56, 'bestyou1', 3, 6, to_date('2020-12-10'), 3, '에고 피곤해', 0);
insert into DIARY values(57, 'coral2', 5, 6, to_date('2020-12-10'), 6, '첫눈 내림', 1);
insert into DIARY values(58, 'doglover3', 6, 6, to_date('2020-12-10'), 8, '맛집에 웨이팅이 없었다', 2);
insert into DIARY values(59, 'enough4', 4, 6, to_date('2020-12-10'), 7, '오랜만에 드라마를 봤다', 1);
insert into DIARY values(60, 'forever5', 3, 6, to_date('2020-12-10'), 2, '아 테스형! 세상이 왜 이래!', 1);
insert into DIARY values(61, 'ace123', 4, 1, to_date('2020-12-11'), 8, '무난무난', 2);
insert into DIARY values(62, 'bestyou1', 3, 1, to_date('2020-12-11'), 8, '길냥이가 불쌍해', 1);
insert into DIARY values(63, 'coral2', 6, 1, to_date('2020-12-11'), 7, '티켓팅, 성공했다!!!!, ', 2);
insert into DIARY values(64, 'doglover3', 5, 1, to_date('2020-12-11'), 8, '오랜만에 중학교 동창한테 연락이 왔다!! 반가웠다!!!!', 1);
insert into DIARY values(65, 'enough4', 4, 1, to_date('2020-12-11'), 6, '쇼미더머니 결승전 ㄷㄷㄷㅈ', 1);
insert into DIARY values(66, 'forever5', 2, 1, to_date('2020-12-11'), 7, '컨디션 너무 안 좋다. 코로나면 어뜨카지...', 0);
insert into DIARY values(67, 'ace123', 3, 5, to_date('2020-12-12'), 4, '주말인데 걱정된다.', 2);
insert into DIARY values(68, 'bestyou1', 4, 5, to_date('2020-12-12'), 10, '토!요!일!!!!!!!', 2);
insert into DIARY values(69, 'coral2', 1, 5, to_date('2020-12-12'), 4, '사이드미러가 깨졌다', 2);
insert into DIARY values(70, 'doglover3', 4, 5, to_date('2020-12-12'), 5, '간단하게 회식했다', 2);
insert into DIARY values(71, 'enough4', 3, 5, to_date('2020-12-12'), 4, '병원에 가야된다', 0);
insert into DIARY values(72, 'forever5', 2, 5, to_date('2020-12-12'), 9, '친구 결혼식 다녀왔다. 나빼고 다 결혼해', 1);
insert into DIARY values(73, 'ace123', 2, 5, to_date('2020-12-13'), 5, '짜증난다.', 0);
insert into DIARY values(74, 'bestyou1', 5, 5, to_date('2020-12-13'), 7, '이승우 작가님 신작 읽었다, 크', 0);
insert into DIARY values(75, 'coral2', 2, 5, to_date('2020-12-13'), 7, '밥먹다가 머리카락 나왔다', 2);
insert into DIARY values(76, 'doglover3', 3, 5, to_date('2020-12-13'), 4, '운동하다가 다쳤다', 2);
insert into DIARY values(77, 'enough4', 2, 5, to_date('2020-12-13'), 4, '이사짐 싸느라 매우 힘듦...', 0);
insert into DIARY values(78, 'forever5', 4, 5, to_date('2020-12-13'), 5, '오늘치 목표량 공부 다 했다.', 1);
insert into DIARY values(79, 'ace123', 6, 1, to_date('2020-12-14'), 6, '최고다!', 2);
insert into DIARY values(80, 'bestyou1', 4, 1, to_date('2020-12-14'), 6, '월요일인데 안피곤하다', 1);
insert into DIARY values(81, 'coral2', 6, 1, to_date('2020-12-14'), 6, '이제 카페갈 수 있다', 1);
insert into DIARY values(82, 'doglover3', 5, 1, to_date('2020-12-14'), 6, '점심이 맛있었다', 0);
insert into DIARY values(83, 'enough4', 6, 1, to_date('2020-12-14'), 6, '이사 첫 날, 새 집에는 햇살이 잘 들어서 좋다.', 1);
insert into DIARY values(84, 'forever5', 6, 1, to_date('2020-12-14'), 6, '일년만에 오프라인 공연 보러 다녀옴! 매우 신남!!', 1);

COMMIT;         
```


## JAVA CODE 


```JAVA

```



## INSPRATION 

1. GITHUB 사용했을때
연동에서 문제점 

2. 
