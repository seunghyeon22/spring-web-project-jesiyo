insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role) values('ssar', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Ssar', '12345', '주소1', '상세주소1', '생일1', 'ROLE_ADMIN');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role) values('cos', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Cos', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER');
insert into user_tb(username, password, name, post_num, addr, addr_detail, birth, role) values('love', '$2a$10$vUQrTY7IN0lGJdlv/E.6n.I09krJTYX3tQsRiSZ4S6PDCQws9Kq5m', 'Love', '12345', '주소2', '상세주소1', '생일2', 'ROLE_USER');



insert into category_tb(name, img_url) values ('반려동물 용품', '없습니다.');
insert into category_tb(name, img_url) values ('디지털기기', '없습니다.');
insert into category_tb(name, img_url) values ('생활가전', '없습니다.');
insert into category_tb(name, img_url) values ('가구/인테리어', '없습니다.');
insert into category_tb(name, img_url) values ('생활/주방', '없습니다.');

insert into goods_tb(title, category_id, seller_id, content, img_url, starting_price, created_at, end_at, status) values ('강아지 산책줄 나눔합니다.', '1', '2', '강아지 산책줄 나눔 중입니다.', '없습니다.', 1000, now(),'2024-12-10 23:00:00', 0);

insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (1,1,2000,now());
insert into bid_tb(buyer_id,goods_id,try_price,created_at) values (3,1,3000,now());



insert into category_tb(name, img_url) values ('가구', '/upload/category1.png');
insert into category_tb(name, img_url) values ('디지털기기2', '/upload/category2.png');
insert into category_tb(name, img_url) values ('생활가전2', '/upload/category3.png');




