
-- user_tb 더미데이터
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('ssar', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Ssar', '12345', '주소1', '상세주소1', '생일1', 'ROLE_ADMIN', '010-1111-1111');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('cos', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Cos', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '010-2222-2222');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('love', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Love', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '010-3333-3333');

insert into useraccount_tb(user_id, score, has_price) values (1, 0, 0);
insert into useraccount_tb(user_id, score, has_price) values (2, 0, 3000);
insert into useraccount_tb(user_id, score, has_price) values (3, 0, 3000);



-- category_tb 더미데이터
insert into category_tb(name, img_url) values ('카테고리1', '/upload/category1.png');
insert into category_tb(name, img_url) values ('카테고리2', '/upload/category2.png');
insert into category_tb(name, img_url) values ('카테고리3', '/upload/category3.png');

insert into category_tb(name, img_url) values ('반려동물 용품', '없습니다.');
insert into category_tb(name, img_url) values ('디지털기기', '없습니다.');
insert into category_tb(name, img_url) values ('생활가전', '없습니다.');
insert into category_tb(name, img_url) values ('가구/인테리어', '없습니다.');
insert into category_tb(name, img_url) values ('생활/주방', '없습니다.');
insert into category_tb(name, img_url) values ('가구', '/upload/category1.png');
insert into category_tb(name, img_url) values ('디지털기기2', '/upload/category2.png');
insert into category_tb(name, img_url) values ('생활가전2', '/upload/category3.png');

insert into category_tb(name, img_url) values ('카테고리4', '/upload/category1.png');
insert into category_tb(name, img_url) values ('카테고리5', '/upload/category2.png');
insert into category_tb(name, img_url) values ('카테고리6', '/upload/category3.png');

-- goods_tb 더미데이터

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '4', '2', '강아지 산책줄 나눔 중입니다.', '없습니다.', 1000, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('책상 팝니다', '7', '1', '2년 썼고 생활기스 있어요.', '없습니다', 20000, now(),'2024-12-12 22:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('냄비 팔아요', '8', '2', '산지 얼마 안돼서 새것 같아요.', '없습니다', 5000, now(),'2024-12-11 21:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('작년에 산 노트북 입니다.', '5', '2', '작년에 사서 몇 번 안썼어요.', '없습니다', 100000, now(),'2024-12-15 19:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 ', '1', '1', '이거만.', '/upload/mainlogo.jpg', 100, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('고양이', '2', '2', '강아지 산책줄 나눔 중입니다.', '/upload/mainlogo.jpg', 200, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('고양이2.', '3', '1', '강아지 산책줄 나눔 중입니다.', '/upload/mainlogo.jpg', 300, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('1고양이즈', '1', '2', '강아지 산책줄 나눔 중입니다.', '/upload/mainlogo.jpg', 400, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아', '2', '1', '니다.', '/upload/mainlogo.jpg', 500, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('소.', '3', '2', '강아지 .', '/upload/mainlogo.jpg', 600, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('양', '1', '1', '강아지 산책다.', '/upload/mainlogo.jpg', 700, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('사람', '2', '1', '강아지 산책줄 .', '/upload/mainlogo.jpg', 800, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산.', '3', '2', '강아지 산책줄 나눔 중입니다.', '/upload/mainlogo.jpg', 900, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '1', '2', '강아지 산책줄 나눔 중입니다.', '없습니다.', 1000, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다2.', '1', '2', '강아지 산책줄 나눔 중입니다2.', '없습니다.', 1000, now(),'2024-12-10 23:00:00', 1);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 판매.', '1', '1', '강아지 산책줄.', '없습니다.', 1000, now(),'2024-12-15 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '1', '2', '강아지 산책줄 나눔 중입니다.', '없습니다.', 1000, now(),'2024-12-10 23:00:00', 0);

insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,2000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (3,1,3000,now());

insert into useraccount_tb(user_id,account,has_price,score) values(1,'등록계좌없음',0,0);
insert into useraccount_tb(user_id,account,has_price,score) values(2,'등록계좌없음',0,0);
insert into useraccount_tb(user_id,account,has_price,score) values(3,'등록계좌없음',0,0);





-- bid_tb 더미데이터
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (3,1,3000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,2,21000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (2,3,5500,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (2,4,130000,now());

insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (2, 2, 3, 0, 1, 50000, now());


insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (2,1,3000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,4000,now());

insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,3000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,4000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,5000,now());

-- transaction_tb 더미데이터
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (2, 2, 3, 0, 1, 50000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (1, 3, 1, 0, 0, 86000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (4, 2, 1, 0, 0, 3000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (2, 2, 1, 0, 1, 100000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (3, 1, 2, 0, 1, 50000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (7, 1, 3, 0, 1, 9200, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (4, 1, 2, 0, 1, 45000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (5, 1, 3, 1, 1, 7000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (2, 1, 2, 1, 1, 6500, now());

--report_tb 더미데이터
insert into report_tb(reporter_id, reported_id, reason, transaction_id, status) values (3, 2, '아 자바스크립트', 1, 0);
insert into report_tb(reporter_id, reported_id, reason, transaction_id, status) values (3, 2, '개화난다', 1, 1);
