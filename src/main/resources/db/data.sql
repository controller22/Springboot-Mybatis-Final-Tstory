INSERT INTO user_tb(username, password, email, nickname, profile_img , role, updated_at,created_at) VALUES('ssar','1234','ssar@nate.com', 'ssar',null,'user',NOW(), NOW());
INSERT INTO user_tb(username, password, email, nickname, profile_img , role, updated_at,created_at) VALUES('cos','1234','cos@nate.com','cos','프로필이미지1.jpg','admin',NOW(), NOW());
INSERT INTO user_tb(username, password, email, nickname, profile_img , role, updated_at,created_at) VALUES('tan','1234','tan@nate.com','tan','프로필이미지2.jpg','user',NOW(), NOW());


INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) 
VALUES('스즈메의 문단속, 솔직 감상 후기 ','어제(10일) 메가박스 대구신세계에서 스즈메의 문단속을 보고 왔습니다.<br>
신카이 마코토 감독의 최신작입니다. 일본에서는 더 퍼스트 슬램덩크(2022년 12월)보다 한 달 먼저 개봉됐는데<br>
 우리나라는 슬램덩크를 올 1월로 앞당기고 스즈메의 문단속을 세계 여성의 날인 3월 8일로 미뤘더군요.<br>
 전작 날씨의 아이(2019)가 대중성에서 벗어난 서사로 흥행을 이끌지 못했으니까 국내 배급사 및 영화사들이 파급력 좋은 슬램덩크부터 스크린에 올리기로 결정한 게 아닐까 싶더군요.<br>
  스즈메의 문단속은 볼 만했을까요?
  <img src="/img/후기1.jpg"></br>
  작품은 꿈속에서 엄마를 찾던 주인공 이와토 스즈메의 회상 씬에서 시작됩니다.</br>
   사고로 엄마를 잃고 규슈 미야자키 현 바닷가 마을에서 이모(이와토 타마키)와 단둘이 살던 2학년 여고생 스즈메는 통학길에 한 남자를 마주칩니다.</br>
자동차나 자전거로 올라갈 법한 오르막을 혼자 걷던 모습이 눈에 아른거렸는지 자전거를 세웁니다. 지나가던 남자는 스즈메에게 문이 있는 폐허 위치를 묻습니다.</br>
 대강 일러주고 철길 건널목까지 내려간 스즈메는 신원 미상의 남자가 계속 신경 쓰여서 자신이 일러준 폐허로 찾아갑니다.</br>
  주위를 둘러보며 남자를 찾던 그때 수중에 오롯이 서 있는 문을 발견합니다.</br>
   <img src="/img/후기2.jpg"></br>
   발목 깊이의 수중을 걸어가 문을 연 스즈메는 눈앞에 보이는 다른 세상을 보고 소스라치게 놀랍니다.</br>
    발을 내딛고 넘어간 순간 스즈메가 본 세상은 사라지고 원래 세계가 다시 나타납니다.</br>
    이질감을 느끼며 몇 차례 건너뛰던 스즈메는 문 근처에 박힌 길쭉한 돌을 들어 올립니다.</br>
    유심히 살피던 돌이 갑자기 털북숭이 동물로 바뀌더니 폴짝폴짝 어딘가로 사라집니다. 스즈메는 무서워하며 자리를 벗어납니다.
  ','스즈메.jpeg', '1','1',NOW(), NOW());
INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) VALUES('영화 리뷰 : 나는 내일 어제의 너와 만난다','히히히','나는내일.jpg', '1','1',NOW(), NOW());
INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) VALUES('영화 리뷰 : 올빼미','히히히','올빼미.jpg', '1','1',NOW(), NOW());
INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) VALUES('영화 리뷰 : 길복순','히히히','길복순.jpg', '1','1',NOW(), NOW());
INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) VALUES('영화 리뷰 : 소울메이트','히히히','소울메이트.jpg', '1','1',NOW(), NOW());
INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) VALUES('영화 리뷰 : 스마트폰을 떨어뜨렸을 뿐인데','히히히','스마트폰을.jpg', '1','1',NOW(), NOW());
INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) VALUES('영화 리뷰 : 더포스트','히히히','더포스트.jpg', '1','1',NOW(), NOW());
INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) VALUES('영화 리뷰 : 범죄와의 전쟁','히히히','범죄.jpg', '1','1',NOW(), NOW());
INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) VALUES('영화 리뷰 : 존윅4','히히히','존윅.jpg', '1','1',NOW(), NOW());
INSERT INTO post_tb(post_title, post_content, post_thumnail, user_id, category_id, updated_at, created_at) VALUES('영화 리뷰 : 헌트','히히히','헌트.jpg', '1','1',NOW(), NOW());



  
INSERT INTO category_tb(category_title, user_id, updated_at, created_at) VALUES('애니메이션','1', NOW(), NOW());
INSERT INTO category_tb(category_title, user_id, updated_at, created_at) VALUES('액션','1', NOW(), NOW());
INSERT INTO category_tb(category_title, user_id, updated_at, created_at) VALUES('코미디','1', NOW(), NOW());