
-- user_tb 더미데이터
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role) values('ssar', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Ssar', '12345', '주소1', '상세주소1', '생일1', 'ROLE_ADMIN');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role) values('cos', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Cos', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role) values('love', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Love', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER');




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

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '1', '2', '강아지 산책줄 나눔 중입니다.', '없습니다.', 1000, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다2.', '1', '2', '강아지 산책줄 나눔 중입니다2.', '없습니다.', 1000, now(),'2024-12-10 23:00:00', 1);

-- goods_tb 더미데이터
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '4', '2', '강아지 산책줄 나눔 중입니다.', '없습니다.', 1000, now(),'2024-12-10 23:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('책상 팝니다', '7', '1', '2년 썼고 생활기스 있어요.', '없습니다', 20000, now(),'2024-12-12 22:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('냄비 팔아요', '8', '2', '산지 얼마 안돼서 새것 같아요.', '없습니다', 5000, now(),'2024-12-11 21:00:00', 0);
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('작년에 산 노트북 입니다.', '5', '2', '작년에 사서 몇 번 안썼어요.', '없습니다', 100000, now(),'2024-12-15 19:00:00', 0);

-- bid_tb 더미데이터
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,2000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (3,1,3000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,2,21000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (2,3,5500,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (2,4,130000,now());

insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, success_price, updated_at) values (2, 2, 3, 0, 1, 50000, now());









