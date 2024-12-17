
-- user_tb 더미데이터
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('ssar', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Ssar', '12345', '주소1', '상세주소1', '생일1', 'ROLE_ADMIN', '010-1111-1111');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('cos', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Cos', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '010-2222-2222');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('love', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Love', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '010-3333-3333');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('cos2', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Cos2', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '010-4444-4444');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel) values('love2', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Love2', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER', '010-5555-5555');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel)
values ('hgd', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', '홍길동', '12345', '주소6', '상세주소6', '생일6', 'ROLE_USER', '010-6666-6666');

insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel)
values ('cug', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', '최엄지', '12345', '주소7', '상세주소7', '생일7', 'ROLE_USER', '010-7777-7777');

insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel)
values ('ijm', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', '일지매', '12345', '주소8', '상세주소8', '생일8', 'ROLE_USER', '010-8888-8888');

insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel)
values ('lss', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', '이순신', '12345', '주소9', '상세주소9', '생일9', 'ROLE_USER', '010-9999-9999');

insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role, tel)
values ('klc', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', '김링컨', '12345', '주소10', '상세주소10', '생일10', 'ROLE_USER', '010-1010-1010');


insert into useraccount_tb(user_id, score, has_price, account) values (1, 0, 0,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (2, 0, 15000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (3, 0, 15000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (4, 0, 15000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (5, 0, 15000,'등록계좌없음');

insert into useraccount_tb(user_id, score, has_price, account) values (6, 0, 100000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (7, 0, 200000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (8, 0, 400000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (9, 0, 450000,'등록계좌없음');
insert into useraccount_tb(user_id, score, has_price, account) values (10, 0, 500000,'등록계좌없음');




-- category_tb 더미데이터
insert into category_tb(name, img_url) values ('디지털기기', '/upload/category1.png');
insert into category_tb(name, img_url) values ('생활가전', '/upload/category2.png');
insert into category_tb(name, img_url) values ('가구/인테리어', '/upload/category3.png');
insert into category_tb(name, img_url) values ('생활/주방', '/upload/category4.png');
insert into category_tb(name, img_url) values ('유아동', '/upload/category5.png');
insert into category_tb(name, img_url) values ('유아도서', '/upload/category6.png');
insert into category_tb(name, img_url) values ('여성의류', '/upload/category7.png');
insert into category_tb(name, img_url) values ('남성패션', '/upload/category8.png');


-- goods_tb 더미데이터 8,9,10 유저만 물품 하나씩등록 / 나머지 2개씩 등록한 상태
insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('강아지 산책줄 나눔합니다.', '4', '4', '강아지 산책줄 나눔 중입니다.', '/upload/dog_line.png.', 1000, '2024-12-10 12:20:00','2024-12-13 16:15:00', 1);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('아이패드 9세대 판매합니다.', '1', '2', '거의 새 제품 같은 아이패드 9세대입니다. 액정 보호필름 부착, 케이스 포함.', '/upload/ipad9.png', 250000, '2024-12-12 10:00:00', '2024-12-20 18:00:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('삼성 스마트 TV 55인치 중고', '2', '2', '화질 좋고 잘 작동하는 삼성 스마트 TV 55인치입니다.', '/upload/tv_samsung.png', 150000, '2024-12-01 15:00:00', '2024-12-08 20:00:00', 1);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('원목 식탁 4인용 저렴하게 판매', '3', '3', '사용감은 있지만 튼튼한 원목 4인용 식탁입니다.', '/upload/wood_table.png', 50000, '2024-12-05 09:00:00', '2024-12-10 21:00:00', 1);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('아기 유모차 깨끗한 상태입니다.', '5', '5', '아기 유모차 깨끗하게 관리되어 있습니다. 부드럽게 잘 굴러갑니다.', '/upload/stroller.png', 80000, '2024-12-07 11:00:00', '2024-12-12 19:00:00', 1);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('아기 그림책 세트 (10권)', '6', '6', '유아용 그림책 10권 세트로 판매합니다. 상태 좋음.', '/upload/kids_books.png', 30000, '2024-12-08 14:00:00', '2024-12-15 17:00:00', 1);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('여성 겨울 코트', '7', '7', '따뜻하고 멋스러운 겨울 코트입니다. 사이즈 55.', '/upload/women_coat.png', 40000, '2024-12-09 10:00:00', '2024-12-16 20:00:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('남성용 가죽 자켓', '8', '8', '고급스러운 가죽 자켓, 사이즈 100.', '/upload/mens_jacket.png', 60000, '2024-12-10 13:00:00', '2024-12-18 22:00:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('갤럭시 워치 4 판매합니다.', '1', '9', '갤럭시 워치 4 블랙 컬러, 44mm. 박스 포함.', '/upload/galaxy_watch4.png', 80000, '2024-12-11 09:30:00', '2024-12-14 23:59:00', 1);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('다이슨 무선 청소기 V10', '2', '10', '다이슨 무선 청소기 V10, 필터 교체 완료.', '/upload/dyson_v10.png', 200000, '2024-12-12 11:00:00', '2024-12-19 19:30:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('원목 의자 2개 세트', '3', '2', '원목 의자 2개 세트로 판매. 약간의 생활 기스.', '/upload/wood_chairs.png', 30000, '2024-12-13 14:00:00', '2024-12-18 20:00:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('아기 침대, 매트리스 포함', '5', '3', '아기 침대와 매트리스 포함, 안전 가드 포함.', '/upload/baby_bed.png', 120000, '2024-12-14 08:00:00', '2024-12-20 22:00:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('여성 가죽 백 (브라운)', '7', '2', '고급스러운 가죽 백. 상태 A급.', '/upload/women_bag.png', 70000, '2024-12-15 10:30:00', '2024-12-22 23:59:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('남성 정장 신발 (260mm)', '8', '3', '사용감 적은 남성 정장 신발입니다.', '/upload/mens_shoes.png', 50000, '2024-12-16 12:00:00', '2024-12-23 21:00:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('로봇 청소기 중고', '2', '10', '로봇 청소기 사용감 있지만 성능 좋음.', '/upload/robot_vacuum.png', 90000, '2024-12-17 14:00:00', '2024-12-24 22:00:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('스탠드 조명', '3', '4', '독서용으로 적합한 스탠드 조명.', '/upload/stand_light.png', 20000, '2024-12-18 13:00:00', '2024-12-25 20:00:00', 3);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('유아 장난감 세트 (5종)', '5', '5', '유아용 장난감 5종 세트. 깨끗이 세척.', '/upload/toy_set.png', 15000, '2024-12-19 09:00:00', '2024-12-26 22:00:00', 3);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('여성 겨울 부츠', '7', '6', '방수 기능 있는 여성 겨울 부츠. 사이즈 240.', '/upload/women_boot.png', 40000, '2024-12-20 10:00:00', '2024-12-27 23:59:00', 0);

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status)
values ('남성 스니커즈', '8', '7', '깔끔한 디자인의 남성 스니커즈, 사이즈 270.', '/upload/mens_sneakers.png', 45000, '2024-12-21 11:00:00', '2024-12-28 20:00:00', 3);


insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (2,2,260000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,2,280000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (5,2,300000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (4,2,340000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (8,7,55000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (10,7,50000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (10,8,60000,now());




-- transaction_tb 더미데이터
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (1, 10, 4, 0, 0, 0,86000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (6, 10, 1, 1, 1, 0,100000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (3, 4, 10, 1, 0, 0,500000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (4, 2, 10, 0, 1, 0,200000, now());
insert into transaction_tb(goods_id, buyer_id, seller_id, buyer_status, seller_status, transaction_status, success_price, updated_at) values (5, 4, 10, 1, 1, 0,150000, now());


--recode_tb 더미데이터
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 1, 2000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 1, 3000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 1, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 1, 5000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 1, 6000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 3, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 3, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (4, 3, 500000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 4, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 4, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (2, 4, 200000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 5, 4000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (1, 5, 8000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (4, 5, 150000, 1, now());

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
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (10, 1, 86000, 1, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (10, 6, 100000, 1, now());

insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (10, 3, 160000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (10, 4, 60000, 0, now());
insert into recode_tb(buyer_id, goods_id, try_price, success_status, created_at) values (10, 5, 90000, 0, now());