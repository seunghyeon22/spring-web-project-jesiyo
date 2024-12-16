
-- user_tb 더미데이터
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('ssar', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Ssar', '12345', '주소1', '상세주소1', '생일1', 'ROLE_ADMIN', '01011111111');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('cos', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Cos', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '01022222222');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('love', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Love', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '01033333333');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('cos2', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Cos2', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '01022222222');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('love2', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Love2', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '01033333333');

insert into useraccount_tb(user_id, score, has_price, account) values (1, 0, 0,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (2, 0, 15000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (3, 0, 15000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (4, 0, 15000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (5, 0, 15000,'등록계좌없음');




-- category_tb 더미데이터
insert into category_tb(name, img_url) values ('디지털기기', '/upload/category1.png');
insert into category_tb(name, img_url) values ('생활가전', '/upload/category2.png');
insert into category_tb(name, img_url) values ('가구/인테리어', '/upload/category3.png');
insert into category_tb(name, img_url) values ('생활/주방', '/upload/category4.png');
insert into category_tb(name, img_url) values ('유아동', '/upload/category5.png');
insert into category_tb(name, img_url) values ('유아도서', '/upload/category6.png');
insert into category_tb(name, img_url) values ('여성의류', '/upload/category7.png');
insert into category_tb(name, img_url) values ('남성패션', '/upload/category8.png');


-- goods_tb 더미데이터
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '4', '4', '강아지 산책줄 나눔 중입니다.', '없습니다.', 1000, '2024-12-10 12:20:00','2024-12-13 16:15:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('책상 팝니다', '7', '1', '2년 썼고 생활기스 있어요.', '없습니다', 20000, now(),'2024-12-12 22:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '4', '2', '강아지 산책줄 나눔 중입니다.', '/upload/category1.png', 1000, '2024-12-10 12:20:00','2024-12-13 12:55:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('책상 팝니다', '7', '2', '2년 썼고 생활기스 있어요.', '없습니다', 20000, now(),'2024-12-16 22:00:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('냄비 팔아요', '8', '2', '산지 얼마 안돼서 새것 같아요.', '없습니다', 5000, now(),'2024-12-16 21:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('작년에 산 노트북 입니다.', '5', '2', '작년에 사서 몇 번 안썼어요.', '없습니다', 100000, now(),'2024-12-16 19:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 ', '1', '1', '이거만.', '/upload/mainlogo.jpg', 100, now(),'2024-12-16 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('고양이', '2', '2', '강아지 산책줄 나눔 중입니다.', '/upload/category1.png', 200, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('고양이2.', '3', '1', '강아지 산책줄 나눔 중입니다.', '/upload/category1.png', 300, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('1고양이즈', '1', '2', '강아지 산책줄 나눔 중입니다.', '/upload/category1.png', 400, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아', '2', '1', '니다.', '/upload/category1.png', 500, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('소.', '3', '2', '강아지 .', '/upload/category1.png', 600, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('양', '1', '1', '강아지 산책다.', '/upload/category1.png', 700, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('사람', '2', '1', '강아지 산책줄 .', '/upload/category1.png', 800, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산.', '3', '2', '강아지 산책줄 나눔 중입니다.', '/upload/category1.png', 900, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '1', '2', '강아지 산책줄 나눔 중입니다.', '/upload/category1.png', 1000, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다2.', '1', '2', '강아지 산책줄 나눔 중입니다2.', '/upload/category1.png', 1000, now(),'2024-12-10 23:00:00', 1);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 판매.', '1', '1', '강아지 산책줄.', '/upload/category1.png', 1000, now(),'2024-12-15 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '1', '2', '강아지 산책줄 나눔 중입니다.', '/upload/category1.png', 1000, now(),'2024-12-10 23:00:00', 0);


-- bid_tb 더미데이터
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,2,21000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,3,5500,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (3,3,6000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (2,4,130000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (2,1,3000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,4000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,3000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,4000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,5000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (2,2,21000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,2,22000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (5,2,23000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (4,2,24000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (3,1,5000,now());
-- insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (4,1,10000,now());

-- transaction_tb 더미데이터
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (1, 3, 4, 0, 0, 0,86000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (2, 3, 1, 1, 1, 0,100000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (3, 4, 3, 1, 1, 0,9200, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (4, 4, 2, 0, 1, 0,45000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (5, 4, 2, 1, 1, 0,7000, now());

--report_tb 더미데이터
-- insert into report_tb(reporter_id, reported_id, reason, transaction_id, status) values (3, 2, '아 자바스크립트', 1, 0);
-- insert into report_tb(reporter_id, reported_id, reason, transaction_id, status) values (3, 2, '개화난다', 1, 1);



--recode_tb 더미데이터
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 1, 2000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 1, 3000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 1, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 1, 5000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 1, 6000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 2, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 2, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 2, 9000, 1, now());


insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 3, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 3, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 3, 9000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 4, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 4, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 4, 9000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 5, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 5, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 5, 9000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 6, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 6, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 6, 9000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 7, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 7, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 7, 9000, 1, now());


insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 8, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 8, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 8, 9000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 9, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 9, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 9, 9000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 10, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 10, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 10, 9000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 11, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 11, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 11, 9000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 7, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 7, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 7, 9000, 1, now());