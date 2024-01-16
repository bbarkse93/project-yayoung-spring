-- 유저 -------------------------------------------------------------------------------------------------------
insert into user_tb(username, password, nickname, role, user_image, is_withdraw, user_created_at)
values ('ssar', '1234', 'ssar', true,'/images/user/user-profile.jpg', false, NOW());
insert into user_tb(username, password, nickname, role, user_image, is_withdraw, user_created_at)
values ('admin', '1234', 'admin', false, '/images/user/user-profile.jpg', false, NOW());

-- 캠프 -------------------------------------------------------------------------------------------------------
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('(주)아웃오브파크', '강원도 춘천시 남면 가옹개길 52-9', '1522-1861', 'http://outofpark.com/main/', '당일 취소 불가, 2일 전 100% 환불', true,
        true, '없음', '14:00', '13:00', '/images/camp_map/camp1.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('파크킹', '경북 김천시 어모면 은림로 62-11', '054-434-6677', 'https://www.instagram.com/amazingpark6211/',
        '당일 취소 불가, 2일 전 100% 환불', true, true, '없음', '14:00', '13:00', '/images/camp_map/camp2.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('디캠고흥', '강원 횡성군 서원면 서원서로102번길 3-18', '033-345-3336', 'https://www.instagram.com/salon.de.bonj/',
        '당일 취소 불가, 2일 전 100% 환불', true, true, '없음', '14:00', '13:00', '/images/camp_map/camp3.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('캠프달링', '경기 가평군 설악면 유명산길 61-75', '010-3148-9970', 'http://healingpia.co.kr', '당일 취소 불가, 2일 전 100% 환불', true,
        true, '없음', '14:00', '13:00', '/images/camp_map/camp4.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('STX 오토캠핑장', '충남 태안군 소원면 산간이길 158-24', '043-1668-3972',
        'https://map.naver.com/v5/entry/place/1791561655?c=14047305.3983138,4408436.9604705,15,0,0,0,dh&amp;placePath=%2Fbooking%3Fentry=plt',
        '당일 취소 불가, 2일 전 100% 환불', true, true, '없음', '14:00', '13:00', '/images/camp_map/camp5.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('청주 옥화포시즌캠핑장', '강원 평창군 평창읍 뇌운계곡로 659-6', '033-332-0009', 'https://pcglamping.modoo.at/',
        '당일 취소 불가, 2일 전 100% 환불', true, true, '없음', '14:00', '13:00', '/images/camp_map/camp6.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('휘게 포레스트', '충북 충주시 앙성면 학교말2길 50', '033-332-0009', 'http://www.binaecamping.co.kr', '당일 취소 불가, 2일 전 100% 환불',
        true, true, '없음', '14:00', '13:00', '/images/camp_map/camp7.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('초이스캠프앤카라반', '충남 태안군 남면 몽산포길 63-23', '033-332-0009', 'https://solbeachcamp.modoo.at/', '당일 취소 불가, 2일 전 100% 환불',
        true, true, '없음', '14:00', '13:00', '/images/camp_map/camp8.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('초록여울캠핑장', '경북 포항시 북구 죽장면 새마을로 3351', '033-332-0119', 'http://www.gaoncamp.com/', '당일 취소 불가, 2일 전 100% 환불',
        true, true, '없음', '14:00', '13:00', '/images/camp_map/camp9.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('소리의섬 캠핑장', '충북 충주시 앙성면 남한강변길 218-2', '033-332-0229', 'http://limsglamping.modoo.at', '당일 취소 불가, 2일 전 100% 환불',
        true, true, '없음', '14:00', '13:00', '/images/camp_map/camp10.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('림스 캠핑장', '전남 강진군 강진읍 해강로 1038-30', '033-332-0229', 'http://limsglamping.modoo.at', '당일 취소 불가, 2일 전 100% 환불',
        true, true, '없음', '14:00', '13:00', '/images/camp_map/camp11.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('단아한', '전북 순창군 팔덕면 산동신흥길 6', '031-581-9977', 'Www.toycamp.kr', '당일 취소 불가, 2일 전 100% 환불', true, true, '없음',
        '14:00', '13:00', '/images/camp_map/camp12.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('원주 두리 캠핑장', '경기 가평군 가평읍 호반로 1700', '031-581-9977', 'Www.toycamp.kr', '당일 취소 불가, 2일 전 100% 환불', true, true,
        '없음', '14:00', '13:00', '/images/camp_map/camp13.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('너른뜰 캠핑장', '충남 부여군 세도면 대흥로 163', '031-581-9917', 'Www.toycamp.kr', '당일 취소 불가, 2일 전 100% 환불', true, true, '없음',
        '14:00', '13:00', '/images/camp_map/camp14.png', false);
insert into CAMP_TB (CAMP_NAME, CAMP_ADDRESS, CAMP_CALL_NUMBER, CAMP_WEBSITE, CAMP_REFUND_POLICY, CAMP_WATER,
                     CAMP_GARBAGE_BAG, HOLIDAY, CAMP_CHECK_IN, CAMP_CHECK_OUT, CAMP_FIELD_IMAGE, IS_DELETE)
values ('개네집 캠핑장', '경상북도 영덕군 병곡면 병곡리 58-5', '031-557-7757', 'Www.toycamp.kr', '당일 취소 불가, 2일 전 100% 환불', true, true,
        '없음', '14:00', '13:00', '/images/camp_map/camp15.png', false);

-- 캠프 구역 -------------------------------------------------------------------------------------------------------
INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('캠핑사이트-1', '50000', 1),
       ('캠핑사이트-2', '50000', 1),
       ('캠핑사이트-3', '50000', 1),
       ('캠핑사이트-4', '50000', 1),
       ('캠핑사이트-5', '50000', 1),
       ('캠핑사이트-6', '50000', 1),
       ('캠핑카 13-1', '60000', 1),
       ('캠핑카 17-1', '60000', 1),
       ('캠핑카 20-1', '60000', 1),
       ('캠핑카 20-2', '60000', 1),
       ('캠핑카 20-3', '60000', 1),
       ('캠핑카 20-4', '60000', 1),
       ('캠핑카 26-1', '60000', 1),
       ('캠핑카 26-2', '60000', 1);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('노랑-1', '40000', 2),
       ('노랑-2', '40000', 2),
       ('노랑-3', '40000', 2),
       ('노랑-4', '40000', 2),
       ('노랑-5', '40000', 2),
       ('노랑-6', '40000', 2),
       ('노랑-7', '40000', 2),
       ('노랑-8', '40000', 2),
       ('노랑-9', '40000', 2),
       ('노랑-10', '40000', 2),
       ('노랑-11', '40000', 2),
       ('노랑-12', '40000', 2),
       ('노랑-13', '40000', 2),
       ('노랑-14', '40000', 2),
       ('초록-1', '40000', 2),
       ('초록-2', '40000', 2),
       ('초록-3', '40000', 2),
       ('초록-4', '40000', 2),
       ('초록-5', '40000', 2),
       ('초록-6', '40000', 2),
       ('초록-7', '40000', 2),
       ('초록-8', '40000', 2),
       ('초록-9', '40000', 2),
       ('초록-10', '40000', 2),
       ('초록-11', '40000', 2),
       ('초록-12', '40000', 2),
       ('초록-13', '40000', 2),
       ('초록-14', '40000', 2),
       ('파랑-15', '40000', 2),
       ('파랑-16', '40000', 2),
       ('파랑-17', '40000', 2);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('1', '40000', 3),
       ('2', '40000', 3),
       ('3', '40000', 3),
       ('4', '40000', 3),
       ('5', '40000', 3),
       ('6', '40000', 3),
       ('7', '40000', 3),
       ('8', '40000', 3),
       ('9', '40000', 3),
       ('10', '40000', 3),
       ('11', '40000', 3),
       ('12', '40000', 3),
       ('13', '40000', 3),
       ('14', '40000', 3),
       ('15', '40000', 3),
       ('16', '40000', 3),
       ('17', '40000', 3),
       ('18', '40000', 3),
       ('19', '40000', 3),
       ('20', '40000', 3),
       ('21', '40000', 3),
       ('22', '40000', 3),
       ('23', '40000', 3),
       ('24', '40000', 3),
       ('25', '40000', 3),
       ('26', '40000', 3),
       ('27', '40000', 3),
       ('28', '40000', 3),
       ('29', '40000', 3),
       ('30', '40000', 3),
       ('31', '40000', 3),
       ('32', '40000', 3),
       ('33', '40000', 3),
       ('34', '40000', 3),
       ('35', '40000', 3),
       ('36', '40000', 3),
       ('37', '40000', 3),
       ('38', '40000', 3),
       ('39', '40000', 3),
       ('40', '40000', 3),
       ('41', '40000', 3),
       ('42', '40000', 3),
       ('43', '40000', 3),
       ('44', '40000', 3),
       ('45', '40000', 3),
       ('46', '40000', 3);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('글램핑1', '80000', 4),
       ('글램핑2', '80000', 4),
       ('글램핑3', '80000', 4),
       ('글램핑4', '80000', 4),
       ('글램핑5', '80000', 4),
       ('글램핑6', '80000', 4),
       ('글램핑7', '80000', 4),
       ('글램핑8', '80000', 4),
       ('글램핑9', '80000', 4),
       ('글램핑10', '80000', 4),
       ('프리미엄캠핑1', '100000', 4),
       ('프리미엄캠핑2', '100000', 4),
       ('프리미엄캠핑3', '100000', 4),
       ('프리미엄캠핑4', '100000', 4),
       ('프리미엄캠핑5', '100000', 4),
       ('프리미엄캠핑6', '100000', 4),
       ('프리미엄캠핑7', '100000', 4),
       ('프리미엄캠핑8', '100000', 4),
       ('프리미엄캠핑9', '100000', 4),
       ('프리미엄캠핑10', '100000', 4),
       ('잔디사이트1', '40000', 4),
       ('잔디사이트2', '40000', 4),
       ('잔디사이트3', '40000', 4),
       ('잔디사이트4', '40000', 4),
       ('잔디사이트5', '40000', 4),
       ('잔디사이트6', '40000', 4),
       ('잔디사이트7', '40000', 4),
       ('잔디사이트8', '40000', 4),
       ('대형사이트1', '50000', 4),
       ('대형사이트2', '50000', 4),
       ('대형사이트3', '50000', 4);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('1', '50000', 5),
       ('2', '50000', 5),
       ('3', '50000', 5),
       ('4', '50000', 5),
       ('5', '50000', 5),
       ('6', '50000', 5),
       ('7', '50000', 5),
       ('8', '50000', 5),
       ('9', '50000', 5),
       ('10', '50000', 5),
       ('11', '50000', 5),
       ('12', '50000', 5),
       ('13', '50000', 5),
       ('14', '50000', 5),
       ('15', '50000', 5),
       ('16', '50000', 5),
       ('17', '50000', 5),
       ('18', '50000', 5),
       ('19', '50000', 5);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('옥화-1', '40000', 6),
       ('옥화-2', '40000', 6),
       ('옥화-3', '40000', 6),
       ('옥화-4', '40000', 6),
       ('옥화-5', '40000', 6),
       ('옥화-6', '40000', 6),
       ('옥화-7', '40000', 6),
       ('옥화-8', '40000', 6),
       ('옥화-9', '40000', 6),
       ('봄-1', '50000', 6),
       ('봄-2', '50000', 6),
       ('봄-3', '50000', 6),
       ('봄-4', '50000', 6),
       ('봄-5', '50000', 6),
       ('봄-6', '50000', 6),
       ('봄-7', '50000', 6),
       ('봄-8', '50000', 6),
       ('봄-9', '50000', 6),
       ('여름-1', '50000', 6),
       ('여름-2', '50000', 6),
       ('여름-3', '50000', 6),
       ('여름-4', '50000', 6),
       ('여름-5', '50000', 6),
       ('여름-6', '50000', 6),
       ('여름-7', '50000', 6),
       ('여름-8', '50000', 6),
       ('여름-9', '50000', 6),
       ('가을-1', '70000', 6),
       ('가을-2', '70000', 6),
       ('가을-3', '70000', 6),
       ('가을-4', '70000', 6),
       ('가을-5', '70000', 6),
       ('가을-6', '70000', 6),
       ('가을-7', '70000', 6),
       ('가을-8', '70000', 6),
       ('가을-9', '70000', 6),
       ('겨울-1', '60000', 6),
       ('겨울-2', '60000', 6),
       ('겨울-3', '60000', 6),
       ('겨울-4', '60000', 6),
       ('겨울-5', '60000', 6),
       ('겨울-6', '70000', 6),
       ('겨울-7', '70000', 6),
       ('차박-1', '70000', 6),
       ('차박-2', '70000', 6),
       ('차박-3', '70000', 6),
       ('차박-4', '70000', 6);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('A-1', '60000', 7),
       ('A-2', '60000', 7),
       ('A-3', '60000', 7),
       ('A-4', '60000', 7),
       ('A-5', '60000', 7),
       ('A-6', '60000', 7),
       ('A-7', '60000', 7),
       ('A-8', '60000', 7),
       ('A-9', '60000', 7),
       ('A-10', '60000', 7),
       ('A-11', '60000', 7),
       ('A-12', '60000', 7),
       ('A-13', '60000', 7),
       ('A-14', '60000', 7),
       ('A-15', '60000', 7),
       ('A-16', '60000', 7),
       ('A-17', '60000', 7),
       ('A-18', '60000', 7),
       ('A-19', '60000', 7),
       ('B-1', '60000', 7),
       ('B-2', '60000', 7),
       ('B-3', '60000', 7),
       ('B-4', '60000', 7),
       ('B-5', '60000', 7),
       ('B-6', '60000', 7),
       ('B-7', '60000', 7),
       ('B-8', '60000', 7),
       ('B-9', '60000', 7),
       ('B-10', '60000', 7),
       ('B-11', '60000', 7),
       ('C-1', '60000', 7),
       ('C-2', '60000', 7),
       ('C-3', '60000', 7),
       ('C-4', '60000', 7),
       ('C-5', '60000', 7),
       ('C-6', '60000', 7),
       ('C-7', '60000', 7),
       ('C-8', '60000', 7),
       ('C-9', '60000', 7),
       ('정원사이트-1', '70000', 7),
       ('정원사이트-2', '70000', 7),
       ('루프탑-1', '70000', 7),
       ('루프탑-2', '70000', 7),
       ('스테이-1', '70000', 7),
       ('스테이-2', '70000', 7),
       ('스테이-3', '70000', 7),
       ('스테이-6', '70000', 7),
       ('스테이-7', '70000', 7),
       ('스테이-8', '70000', 7),
       ('잔디사이트-1', '70000', 7),
       ('잔디사이트-2', '70000', 7),
       ('잔디사이트-3', '70000', 7),
       ('잔디사이트-4', '70000', 7),
       ('잔디사이트-5', '70000', 7),
       ('잔디사이트-6', '70000', 7),
       ('잔디사이트-7', '70000', 7),
       ('잔디사이트-8', '70000', 7),
       ('잔디사이트-9', '70000', 7),
       ('잔디사이트-10', '70000', 7),
       ('잔디사이트-11', '70000', 7);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('A-1', '60000', 8),
       ('A-2', '60000', 8),
       ('A-3', '60000', 8),
       ('A-4', '60000', 8),
       ('A-5', '60000', 8),
       ('A-6', '60000', 8),
       ('A-7', '60000', 8),
       ('A-8', '60000', 8),
       ('A-9', '60000', 8),
       ('A-10', '60000', 8),
       ('A-11', '60000', 8),
       ('A-12', '60000', 8),
       ('C-1', '100000', 8),
       ('C-2', '100000', 8),
       ('C-3', '100000', 8),
       ('C-4', '100000', 8),
       ('C-5', '100000', 8),
       ('C-6', '100000', 8);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('A1', '60000', 9),
       ('A2', '60000', 9),
       ('A3', '60000', 9),
       ('A4', '60000', 9),
       ('A5', '60000', 9),
       ('A6', '60000', 9),
       ('A7', '60000', 9),
       ('A8', '60000', 9),
       ('B1', '50000', 9),
       ('B2', '50000', 9),
       ('B3', '50000', 9),
       ('B4', '50000', 9),
       ('B5', '50000', 9),
       ('B6', '50000', 9),
       ('B7', '50000', 9),
       ('B8', '50000', 9),
       ('B9', '50000', 9),
       ('B10', '50000', 9),
       ('B11', '50000', 9),
       ('B12', '50000', 9),
       ('B13', '50000', 9),
       ('B14', '50000', 9),
       ('B15', '50000', 9),
       ('B16', '50000', 9),
       ('B17', '50000', 9),
       ('B18', '50000', 9),
       ('B19', '50000', 9),
       ('B20', '50000', 9),
       ('C1', '60000', 9),
       ('C2', '60000', 9),
       ('C3', '60000', 9),
       ('C4', '60000', 9),
       ('C5', '60000', 9),
       ('C6', '60000', 9),
       ('C7', '60000', 9),
       ('C8', '60000', 9),
       ('C9', '60000', 9);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('A1', '50000', 10),
       ('A2', '50000', 10),
       ('A3', '50000', 10),
       ('A4', '50000', 10),
       ('A5', '50000', 10),
       ('B1', '50000', 10),
       ('B2', '50000', 10),
       ('B3', '50000', 10),
       ('B4', '50000', 10),
       ('B5', '50000', 10),
       ('B6', '50000', 10),
       ('B7', '50000', 10),
       ('B8', '50000', 10),
       ('B9', '50000', 10),
       ('B10', '50000', 10),
       ('C1', '50000', 10),
       ('C2', '50000', 10),
       ('C3', '50000', 10),
       ('C4', '50000', 10),
       ('C5', '50000', 10),
       ('D1', '50000', 10),
       ('D2', '50000', 10),
       ('D3', '50000', 10),
       ('D4', '50000', 10),
       ('D5', '50000', 10),
       ('D6', '50000', 10),
       ('D7', '50000', 10),
       ('D8', '50000', 10),
       ('D9', '50000', 10),
       ('D10', '50000', 10),
       ('E1', '50000', 10),
       ('E2', '50000', 10),
       ('E3', '50000', 10),
       ('E4', '50000', 10),
       ('E5', '50000', 10);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('A1', '50000', 11),
       ('A2', '50000', 11),
       ('A3', '50000', 11),
       ('A4', '50000', 11),
       ('A5', '50000', 11),
       ('A6', '50000', 11),
       ('A7', '50000', 11),
       ('A8', '50000', 11),
       ('A9', '50000', 11),
       ('A10', '50000', 11),
       ('A11', '50000', 11),
       ('B-1', '50000', 11),
       ('B-2', '50000', 11),
       ('B-3', '50000', 11),
       ('B-4', '50000', 11),
       ('B-5', '50000', 11),
       ('B-6', '50000', 11),
       ('B-7', '50000', 11),
       ('B-8', '50000', 11),
       ('B-9', '50000', 11),
       ('C-1', '60000', 11),
       ('C-2', '60000', 11),
       ('C-3', '60000', 11),
       ('C-4', '60000', 11),
       ('C-5', '60000', 11),
       ('C-6', '60000', 11),
       ('C-7', '60000', 11),
       ('C-8', '60000', 11),
       ('C-9', '60000', 11),
       ('방갈로-1', '80000', 11),
       ('방갈로-2', '80000', 11),
       ('방갈로-3', '80000', 11),
       ('방갈로-4', '80000', 11),
       ('방갈로-5', '80000', 11);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('A1', '50000', 12),
       ('A2', '50000', 12),
       ('A3', '50000', 12),
       ('A4', '50000', 12),
       ('A5', '50000', 12),
       ('C1', '50000', 12),
       ('C2', '50000', 12),
       ('C3', '50000', 12),
       ('두가족데크', '90000', 12),
       ('데크1', '50000', 12),
       ('데크2', '50000', 12),
       ('데크3', '50000', 12);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('물가1', '50000', 13),
       ('물가2', '50000', 13),
       ('물가3', '50000', 13),
       ('물가4', '50000', 13),
       ('물가5', '50000', 13),
       ('물가6', '50000', 13),
       ('물가7', '50000', 13),
       ('물가8', '50000', 13),
       ('물가9', '50000', 13),
       ('물가10', '50000', 13),
       ('물가11', '50000', 13),
       ('물가12', '50000', 13),
       ('물가13', '50000', 13),
       ('물가A', '50000', 13),
       ('물가B', '50000', 13),
       ('물가C', '50000', 13),
       ('물가D', '50000', 13),
       ('물가E', '50000', 13),
       ('물가F', '50000', 13),
       ('두가족-라', '100000', 13),
       ('두가족-마', '100000', 13),
       ('산책타프1', '60000', 13),
       ('산책타프2', '60000', 13),
       ('산책타프3', '60000', 13),
       ('산책타프4', '60000', 13),
       ('산책타프5', '60000', 13),
       ('산책타프6', '60000', 13),
       ('산책타프7', '60000', 13),
       ('산책타프8', '60000', 13),
       ('산책타프9', '60000', 13),
       ('산책타프10', '60000', 13),
       ('산책타프11', '60000', 13),
       ('산책타프12', '60000', 13),
       ('산책타프13', '60000', 13),
       ('산책타프14', '60000', 13),
       ('산책타프15', '60000', 13),
       ('산책타프16', '60000', 13),
       ('산책타프17', '60000', 13),
       ('산책타프18', '60000', 13),
       ('산책타프19', '60000', 13),
       ('산책타프-가', '60000', 13),
       ('산책타프-나', '60000', 13),
       ('산책타프-다', '60000', 13),
       ('산책파쇄석1', '50000', 13),
       ('산책파쇄석2', '50000', 13),
       ('산책파쇄석3', '50000', 13),
       ('산책파쇄석4', '50000', 13),
       ('산책파쇄석5', '50000', 13),
       ('산책파쇄석6', '50000', 13),
       ('산책파쇄석7', '50000', 13),
       ('산책파쇄석8', '50000', 13),
       ('산책파쇄석9', '50000', 13),
       ('놀이타프1', '60000', 13),
       ('놀이타프2', '60000', 13),
       ('놀이타프3', '60000', 13),
       ('놀이타프4', '60000', 13),
       ('놀이타프5', '60000', 13),
       ('놀이타프6', '60000', 13),
       ('놀이타프7', '60000', 13),
       ('놀이타프8', '60000', 13),
       ('놀이타프9', '60000', 13),
       ('놀이타프10', '60000', 13),
       ('놀이타프11', '60000', 13),
       ('놀이타프12', '60000', 13),
       ('놀이타프13', '60000', 13),
       ('놀이타프14', '60000', 13),
       ('놀이타프15', '60000', 13),
       ('놀이타프16', '60000', 13),
       ('놀이타프17', '60000', 13),
       ('놀이타프18', '60000', 13),
       ('놀이타프19', '60000', 13),
       ('놀이타프20', '60000', 13),
       ('놀이타프21', '60000', 13),
       ('놀이타프22', '60000', 13),
       ('놀이타프23', '60000', 13),
       ('놀이타프24', '60000', 13),
       ('놀이타프25', '60000', 13);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('D1', '100000', 14),
       ('D2', '100000', 14),
       ('D3', '100000', 14),
       ('D4', '100000', 14),
       ('D5', '100000', 14),
       ('G1', '40000', 14),
       ('G2', '40000', 14),
       ('G3', '40000', 14),
       ('G4', '40000', 14),
       ('G5', '40000', 14),
       ('C1', '50000', 14),
       ('C2', '50000', 14),
       ('C3', '50000', 14),
       ('C4', '50000', 14),
       ('C5', '50000', 14),
       ('C6', '50000', 14);

INSERT INTO CAMP_FIELD_TB (FIELD_NAME, PRICE, CAMP_ID)
VALUES ('1', '50000', 15),
       ('2', '50000', 15),
       ('3', '50000', 15),
       ('4', '50000', 15),
       ('5', '50000', 15),
       ('6', '50000', 15),
       ('7', '50000', 15),
       ('8', '50000', 15),
       ('9', '140000', 15),
       ('10', '50000', 15),
       ('11', '90000', 15),
       ('12', '90000', 15);

-- 캠프 사진 LIST -------------------------------------------------------------------------------------------------------
insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp1-1.jpg', 1),
       ('/images/camp_image/camp1-2.jpg', 1),
       ('/images/camp_image/camp1-3.jpg', 1),
       ('/images/camp_image/camp1-4.jpg', 1),
       ('/images/camp_image/camp1-5.jpg', 1),
       ('/images/camp_image/camp1-6.jpg', 1),
       ('/images/camp_image/camp1-7.jpg', 1),
       ('/images/camp_image/camp1-8.jpg', 1);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp2-1.jpg', 2),
       ('/images/camp_image/camp2-2.jpg', 2),
       ('/images/camp_image/camp2-3.jpg', 2),
       ('/images/camp_image/camp2-4.jpg', 2),
       ('/images/camp_image/camp2-5.jpg', 2);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp3-1.jpg', 3),
       ('/images/camp_image/camp3-2.jpg', 3),
       ('/images/camp_image/camp3-3.jpg', 3),
       ('/images/camp_image/camp3-4.jpg', 3),
       ('/images/camp_image/camp3-5.jpg', 3);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp4-1.jpg', 4),
       ('/images/camp_image/camp4-2.jpg', 4),
       ('/images/camp_image/camp4-3.jpg', 4),
       ('/images/camp_image/camp4-4.jpg', 4),
       ('/images/camp_image/camp4-5.jpg', 4);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp5-1.jpg', 5),
       ('/images/camp_image/camp5-2.jpg', 5),
       ('/images/camp_image/camp5-3.jpg', 5),
       ('/images/camp_image/camp5-4.jpg', 5),
       ('/images/camp_image/camp5-5.jpg', 5);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp6-1.jpg', 6),
       ('/images/camp_image/camp6-2.jpg', 6),
       ('/images/camp_image/camp6-3.jpg', 6),
       ('/images/camp_image/camp6-4.jpg', 6),
       ('/images/camp_image/camp6-5.jpg', 6);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp7-1.jpg', 7),
       ('/images/camp_image/camp7-2.jpg', 7),
       ('/images/camp_image/camp7-3.jpg', 7);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp8-1.jpg', 8),
       ('/images/camp_image/camp8-2.jpg', 8),
       ('/images/camp_image/camp8-3.jpg', 8),
       ('/images/camp_image/camp8-4.jpg', 8),
       ('/images/camp_image/camp8-5.jpg', 8);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp9-1.jpg', 9),
       ('/images/camp_image/camp9-2.jpg', 9),
       ('/images/camp_image/camp9-3.jpg', 9),
       ('/images/camp_image/camp9-4.jpg', 9),
       ('/images/camp_image/camp9-5.jpg', 9),
       ('/images/camp_image/camp9-6.jpg', 9),
       ('/images/camp_image/camp9-7.jpg', 9);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp10-1.jpg', 10),
       ('/images/camp_image/camp10-2.jpg', 10),
       ('/images/camp_image/camp10-3.jpg', 10),
       ('/images/camp_image/camp10-4.jpg', 10),
       ('/images/camp_image/camp10-5.jpg', 10);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp11-1.jpg', 11),
       ('/images/camp_image/camp11-2.jpg', 11),
       ('/images/camp_image/camp11-3.jpg', 11),
       ('/images/camp_image/camp11-4.jpg', 11),
       ('/images/camp_image/camp11-5.jpg', 11);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp12-1.jpg', 12),
       ('/images/camp_image/camp12-2.jpg', 12),
       ('/images/camp_image/camp12-3.jpg', 12),
       ('/images/camp_image/camp12-4.jpg', 12);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp13-1.jpg', 13),
       ('/images/camp_image/camp13-2.jpg', 13),
       ('/images/camp_image/camp13-3.jpg', 13),
       ('/images/camp_image/camp13-4.jpg', 13),
       ('/images/camp_image/camp13-5.jpg', 13);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp14-1.jpg', 14),
       ('/images/camp_image/camp14-2.jpg', 14),
       ('/images/camp_image/camp14-3.jpg', 14),
       ('/images/camp_image/camp14-4.jpg', 14),
       ('/images/camp_image/camp14-5.jpg', 14);

insert into CAMP_IMAGE_TB (CAMP_IMAGE, CAMP_ID)
values ('/images/camp_image/camp15-1.jpg', 15),
       ('/images/camp_image/camp15-2.jpg', 15),
       ('/images/camp_image/camp15-3.jpg', 15),
       ('/images/camp_image/camp15-4.jpg', 15),
       ('/images/camp_image/camp15-5.jpg', 15);

-- 옵션 카테고리 -------------------------------------------------------------------------------------------------------
insert into OPTION_CATEGORY_TB (CATEGORY_NAME)
values ('environment');
insert into OPTION_CATEGORY_TB (CATEGORY_NAME)
values ('type');
insert into OPTION_CATEGORY_TB (CATEGORY_NAME)
values ('site');
insert into OPTION_CATEGORY_TB (CATEGORY_NAME)
values ('main_facility');
insert into OPTION_CATEGORY_TB (CATEGORY_NAME)
values ('have_facility');
insert into OPTION_CATEGORY_TB (CATEGORY_NAME)
values ('exercise_facility');
insert into OPTION_CATEGORY_TB (CATEGORY_NAME)
values ('program');
insert into OPTION_CATEGORY_TB (CATEGORY_NAME)
values ('rental');
insert into OPTION_CATEGORY_TB (CATEGORY_NAME)
values ('sell');

-- 옵션 메뉴 -------------------------------------------------------------------------------------------------------
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (1, '산');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (1, '계곡');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (1, '바다');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (1, '섬');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (1, '도시');

insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (2, '트레일러');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (2, '모터홈');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (2, '카라반');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (2, '오토캠핑');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (2, '차박');

insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (3, '데크');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (3, '파쇄석');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (3, '잔디');

insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (4, '전기');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (4, 'Wi-Fi');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (4, '화로대');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (4, '반려동물');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (4, '키즈');

insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (5, '샤워장');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (5, '온수');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (5, '매점');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (5, '물놀이장');

insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (6, '산책로');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (6, '운동장');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (6, '농구대');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (6, '축구장');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (6, '족구장');

insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (7, '계곡·물놀이');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (7, '해수욕');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (7, '갯벌체험');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (7, '낚시');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (7, '등산(트래킹)');

insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (8, '릴선');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (8, '화로대');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (8, '난로');

insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (9, '숯');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (9, '장작');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (9, '얼음');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (9, '술');
insert into OPTION_TB (OPTION_CATEGORY_ID, OPTION_NAME)
values (9, '등유');

-- 옵션 management -------------------------------------------------------------------------------------------------------
insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (1, 1),
       (1, 8),
       (1, 9),
       (1, 11),
       (1, 12),
       (1, 14),
       (1, 15),
       (1, 16),
       (1, 19),
       (1, 20),
       (1, 21),
       (1, 22),
       (1, 27),
       (1, 33),
       (1, 36),
       (1, 37),
       (1, 38),
       (1, 39),
       (1, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (2, 1),
       (2, 6),
       (2, 8),
       (2, 9),
       (2, 11),
       (2, 13),
       (2, 14),
       (2, 15),
       (2, 16),
       (2, 17),
       (2, 18),
       (2, 19),
       (2, 20),
       (2, 21),
       (2, 22),
       (2, 32),
       (2, 33),
       (2, 36),
       (2, 37),
       (2, 38),
       (2, 39),
       (2, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (3, 1),
       (3, 2),
       (3, 6),
       (3, 9),
       (3, 12),
       (3, 14),
       (3, 15),
       (3, 16),
       (3, 17),
       (3, 18),
       (3, 19),
       (3, 20),
       (3, 21),
       (3, 22),
       (3, 23),
       (3, 28),
       (3, 32),
       (3, 33),
       (3, 36),
       (3, 37),
       (3, 38),
       (3, 39),
       (3, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (4, 1),
       (4, 6),
       (4, 7),
       (4, 8),
       (4, 9),
       (4, 11),
       (4, 13),
       (4, 14),
       (4, 15),
       (4, 16),
       (4, 18),
       (4, 19),
       (4, 20),
       (4, 21),
       (4, 22),
       (4, 23),
       (4, 33),
       (4, 36),
       (4, 37),
       (4, 38),
       (4, 39),
       (4, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (5, 1),
       (5, 4),
       (5, 9),
       (5, 11),
       (5, 12),
       (5, 13),
       (5, 14),
       (5, 15),
       (5, 16),
       (5, 17),
       (5, 18),
       (5, 19),
       (5, 20),
       (5, 21),
       (5, 23),
       (5, 24),
       (5, 28),
       (5, 33),
       (5, 36),
       (5, 37),
       (5, 38),
       (5, 39),
       (5, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (6, 1),
       (6, 5),
       (6, 6),
       (6, 8),
       (6, 9),
       (6, 10),
       (6, 11),
       (6, 12),
       (6, 13),
       (6, 14),
       (6, 15),
       (6, 16),
       (6, 18),
       (6, 19),
       (6, 20),
       (6, 21),
       (6, 33),
       (6, 36),
       (6, 37),
       (6, 38),
       (6, 39),
       (6, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (7, 1),
       (7, 4),
       (7, 9),
       (7, 11),
       (7, 12),
       (7, 13),
       (7, 14),
       (7, 15),
       (7, 17),
       (7, 18),
       (7, 19),
       (7, 20),
       (7, 21),
       (7, 28),
       (7, 33),
       (7, 36),
       (7, 37),
       (7, 38),
       (7, 39),
       (7, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (8, 1),
       (8, 8),
       (8, 9),
       (8, 12),
       (8, 14),
       (8, 15),
       (8, 19),
       (8, 20),
       (8, 21),
       (8, 33),
       (8, 36),
       (8, 37),
       (8, 38),
       (8, 39),
       (8, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (9, 1),
       (9, 4),
       (9, 9),
       (9, 11),
       (9, 12),
       (9, 14),
       (9, 15),
       (9, 16),
       (9, 17),
       (9, 18),
       (9, 19),
       (9, 20),
       (9, 21),
       (9, 23),
       (9, 33),
       (9, 36),
       (9, 37),
       (9, 38),
       (9, 39),
       (9, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (10, 2),
       (10, 4),
       (10, 6),
       (10, 8),
       (10, 9),
       (10, 11),
       (10, 14),
       (10, 15),
       (10, 17),
       (10, 18),
       (10, 19),
       (10, 20),
       (10, 21),
       (10, 23),
       (10, 33),
       (10, 36),
       (10, 37),
       (10, 38),
       (10, 39),
       (10, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (11, 1),
       (11, 5),
       (11, 6),
       (11, 8),
       (11, 9),
       (11, 11),
       (11, 12),
       (11, 13),
       (11, 14),
       (11, 15),
       (11, 16),
       (11, 17),
       (11, 18),
       (11, 19),
       (11, 20),
       (11, 21),
       (11, 23),
       (11, 25),
       (11, 26),
       (11, 32),
       (11, 33),
       (11, 36),
       (11, 37),
       (11, 38),
       (11, 39),
       (11, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (12, 1),
       (12, 2),
       (12, 9),
       (12, 11),
       (12, 12),
       (12, 14),
       (12, 15),
       (12, 17),
       (12, 18),
       (12, 19),
       (12, 20),
       (12, 21),
       (12, 22),
       (12, 28),
       (12, 32),
       (12, 33),
       (12, 36),
       (12, 37),
       (12, 38),
       (12, 39),
       (12, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (13, 5),
       (13, 8),
       (13, 9),
       (13, 11),
       (13, 12),
       (13, 14),
       (13, 15),
       (13, 17),
       (13, 18),
       (13, 19),
       (13, 20),
       (13, 21),
       (13, 22),
       (13, 28),
       (13, 32),
       (13, 33),
       (13, 36),
       (13, 37),
       (13, 38),
       (13, 39),
       (13, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (14, 1),
       (14, 3),
       (14, 4),
       (14, 8),
       (14, 9),
       (14, 11),
       (14, 13),
       (14, 14),
       (14, 15),
       (14, 17),
       (14, 18),
       (14, 19),
       (14, 20),
       (14, 21),
       (14, 22),
       (14, 33),
       (14, 36),
       (14, 37),
       (14, 38),
       (14, 39),
       (14, 40);

insert into OPTION_MANAGEMENT_TB (CAMP_ID, OPTION_ID)
values (15, 1),
       (15, 2),
       (15, 5),
       (15, 6),
       (15, 8),
       (15, 9),
       (15, 11),
       (15, 12),
       (15, 14),
       (15, 15),
       (15, 16),
       (15, 18),
       (15, 19),
       (15, 20),
       (15, 21),
       (15, 33),
       (15, 36),
       (15, 37),
       (15, 38),
       (15, 39),
       (15, 40);

-- 북마크 -------------------------------------------------------------------------------------------------------
insert into CAMP_BOOKMARK_TB (CAMP_ID, USER_ID) values(1, 1);
insert into CAMP_BOOKMARK_TB (CAMP_ID, USER_ID) values(2, 1);
insert into CAMP_BOOKMARK_TB (CAMP_ID, USER_ID) values(3, 1);

-- 결제 -------------------------------------------------------------------------------------------------------
insert into order_tb (check_in_date, check_out_date, user_id, camp_field_id, created_at)
values ('2022-01-10', '2022-01-15', 1, 1, now());
insert into order_tb (check_in_date, check_out_date, user_id, camp_field_id, created_at)
values ('2024-01-11', '2024-01-15', 1, 3, now());
insert into order_tb (check_in_date, check_out_date, user_id, camp_field_id, created_at)
values ('2024-01-17', '2024-01-18', 1, 5, now());
insert into order_tb (check_in_date, check_out_date, user_id, camp_field_id, created_at)
values ('2024-01-20', '2024-01-25', 1, 7, now());
insert into order_tb (check_in_date, check_out_date, user_id, camp_field_id, created_at)
values ('2024-02-03', '2024-02-10', 1, 9, now());

-- 평점 ------------------------------------------------------------------------------------------------------------
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 1, 4.0, 5.0, 4.0, 1 );
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 1, 5.0, 5.0, 4.0, 1 );
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 2, 3.0, 2.0, 3.0, 1 );
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 3, 4.0, 3.0, 4.0, 1 );
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 4, 5.0, 4.0, 5.0, 1 );
values ( 2, 1.0, 5.0, 5.0, 1 );
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 2, 2.0, 5.0, 5.0, 1 );
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 2, 2.0, 4.0, 5.0, 1 );
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 2, 4.0, 1.0, 5.0, 1 );
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 2, 2.0, 1.0, 5.0, 1 );
insert into camp_rating_tb ( camp_id, cleanliness, friendliness, managementness, user_id)
values ( 2, 3.0, 1.0, 5.0, 1 );

-- 리뷰 -----------------------------------------------------------------------------------------------------------
insert into camp_review_tb (order_id, camp_rating_id, camp_id, user_id, content, review_image, created_at )
values (1, 1, 1, 1, '아름답고 좋은 장소였습니다. 다음에도 또 오고 싶네요.', '/images/camp_image/camp1-1.jpg', now()  );
insert into camp_review_tb (order_id, camp_rating_id, camp_id, user_id, content, review_image, created_at )
values (2, 2, 1, 1, '전보다 더 아름답고 좋은 장소였습니다. 다음에도 또 오고 싶네요.', '/images/camp_image/camp1-1.jpg', now()  );
insert into camp_review_tb (order_id, camp_rating_id, camp_id, user_id, content, review_image, created_at )
values (3, 3, 2, 1, '전보다 조금 관리가 안된 장소였습니다. 다음에는 개선되었으면 싶네요.', '/images/camp_image/camp1-1.jpg', now()  );
insert into camp_review_tb (order_id, camp_rating_id, camp_id, user_id, content, review_image, created_at )
values (4, 4, 3, 1, '전보다 더 아름답고 좋은 장소였습니다. 다음에도 또 오고 싶네요.', '/images/camp_image/camp1-1.jpg', now()  );
insert into camp_review_tb (order_id, camp_rating_id, camp_id, user_id, content, review_image, created_at )
values (5, 5, 4, 1, '전보다 더 아름답고 좋은 장소였습니다. 다음에도 또 오고 싶네요.', '/images/camp_image/camp1-1.jpg', now()  );

-- FAQ 카테고리 -------------------------------------------------------------------------------------------------------
insert into BOARD_CATEGORY_TB (TITLE)
values ('결제/환불');
insert into BOARD_CATEGORY_TB (TITLE)
values ('회원/로그인');

-- FAQ -------------------------------------------------------------------------------------------------------
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('예약을 취소하고 싶어요',
        '예약자 본인이 직접 예약확인/취소 가능합니다.
예약/결제 당시 안내된 취소/환불 규정에 따라 처리되며, 취소수수료 발생 시 취소수수료를 차감한 금액으로 환불 처리됩니다.', 1, now(), 1);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('예약 완료후 바로 취소했는데 수수료가 나왔어요',
        '취소 수수료는 예약 시점과는 무관합니다.
예약 후 바로 취소하더라도 입실일/이용일로부터 남은 날짜 기준으로 수수료가 부과됩니다. 특히, 이용일 10일 이내 예약 건은 더욱 신중한 예약 바랍니다.', 1, now(), 1);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('입실일을 변경하고 싶어요',
        '예약 이용일 변경은 불가능합니다.
취소 수수료를 확인하시고 기존 예약건을 취소하신 다음 재예약하셔야 합니다.', 1, now(), 1);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('천재지변으로 인한 예약취소는 어떻게 하나요?',
        '일반적인 우천에 의한 예약 취소 및 변경 불가합니다.
태풍 등 기상 특보 시, 입실 당일 현장 날씨 및 캠핑장/펜션의 정책에 따라 연기 또는 취소 결정됩니다.
입실 당일 오전에 캠핑장/펜션에 연락하셔서 안내 받으시기 바랍니다.', 1, now(), 1);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('객실 타입을 변경하고 싶어요',
        'YAYoung 고객센터(070-1111-1111)로 문의하시기 바랍니다.', 1, now(), 1);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('취소 수수료 산정은 어떻게 되나요?',
        '당일 취소 불가, 2일 전 환불 100%입니다.', 1, now(), 1);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('예약 취소 했는데 언제 환불이 되나요?',
        '예약 취소 후 간편결제 사업자, 은행 또는 신용카드사에 따라 환불 절차에 일정 시간이 소요될 수 있습니다.
영업일 기준(토/일/공휴일 제외) 실시간 계좌이체는 2~3일, 신용카드는 3~5일 소요됩니다.', 1, now(), 1);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('회원탈퇴 후 제 SNS계정에 아직 연결되어 있다고 뜨네요',
        '회원탈퇴를 하셨더라도, 연결된 SNS 계정은 해당 계정 설정에서 연결 해제를 해주셔야 합니다.', 1, now(), 2);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('탈퇴하고 싶어요.',
        '아래 경로를 통해 탈퇴 할 수 있습니다.

▶ ME > 설정 > 회원탈퇴', 1, now(), 2);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('회원 정보 수정은 어떻게 하나요?',
        '아래 경로를 통해 회원 정보를 수정할 수 있습니다.

▶ ME > 프로필 클릭', 1, now(), 2);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('제가 설정한 적 없는 닉네임으로 되어있어요.',
        '고객님의 개인 정보가 유출된 것이 아니니 안심하세요.
아래의 경로를 통해 닉네임을 변경할 수 있습니다.', 1, now(), 2);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('제 개인정보를 안전하게 보호하려면 어떻게 해야 하죠?',
        'YAYoung는 고객님의 소중한 개인정보를 더욱 안전하게 보호하도록 노력하겠습니다.
', 1, now(), 2);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('로그인이 안 돼요.',
        '이메일로 가입한 계정이 있는지 확인해주세요.', 1, now(), 2);
insert into BOARD_TB (TITLE, CONTENT, USER_ID, CREATED_AT, BOARD_CATEGORY_ID)
values ('비회원도 예약을 하거나 예약 조회를 할 수 있나요?', '아니요, 가입을 하셔야 가능합니다.', 1, now(), 2);

-- 공지사항 -------------------------------------------------------------------------------------------------------
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('YAYoung 임시 점검 안내',
        'YAYoung 사이트 서비스 개선을 위한 작업으로 인하여
아래와 같이 일시적으로 YAYoung 홈페이지 접속이 불가능할 예정입니다.

- 작업일시 : 2023년 10월 13일 (금) 00:00 ~ 06:00
- 작업내용 : DB 작업

YAYoung 서비스 이용에 불편을 드리게 되어 죄송합니다.
더 나은 서비스를 제공할 수 있도록 노력하는 YAYoung이 되겠습니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('YAYoung 리뉴얼 오픈',
        'Don`t Worry 캠핑!
Be Happy YAYoung!!
YAYoung 리뉴얼 오픈

YAYoung 앱이 리뉴얼 오픈합니다.
사용은 편리해졌고, 혜택은 다양해졌습니다.

강력해진 Q-Point(적립금) 제도

- YAYoung 예약, 쇼핑 이용 시 현금처럼 사용이 가능한 포인트 입니다.
- 다양한 활동을 통해서 Q-Point를 적립하실 수 있습니다.
- 예약, 쇼핑, 방문(출석), 게시글 작성, 홍보 참여 등 포인트 적립 기회가 다양해졌습니다.

회원등급제도

- YAYoung을 많이 이용할수록 혜택도 더욱 커집니다.
- 1년 동안의 누적 활동 지수를 기준으로 내년 등급이 결정되며, 등급별로 다양한 혜택을 받으실 수 있습니다.
- 등급은 Basic, Bronze, Silver, Gold, Platinum, VIP 총 6등급으로 나눠집니다.

사용성 개선
좀 더 쉽게 이동하세요.
메뉴 버튼을 적용하여 원하는 페이지로 쉽게 이동이 가능해졌습니다.

고객만족을 위해 더욱 노력하는 YAYoung이 되겠습니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('서버 점검으로 인한 서비스 일시 중지 안내',
        '※ 서버 일시 중지 안내

서비스 향상을 위한 서버 점검 작업으로 인해 작업 시간 동안  서비스가 중지됨을 알려드립니다.

▶ 일시 : 28일(화) 01시 - 07시
▶ 내용 : 서버 점검으로 인한 작업

위 시간 동안 예약에 대한 모든 서비스가 중지 되오니 참고하시기 바랍니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('카카오 소셜로그인 장애 안내',
        '현재 어플 및 PC로그인 중 카카오 소셜로그인이 정상적으로 작동되지 않음을 확인하였습니다.

최근 카카오API 정책변경으로 인해 기능 보완이 필요해보입니다.
조치 될때까지 카카오 로그인 서비스는 잠시 중단하겠습니다.

기존에 카카오로그인으로 활동하신분은 죄송하지만 다른 로그인 방법을 이용해주시기 바랍니다.

불편 드려 죄송합니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('캠핑장 내 공용 와이파이 사용 안내',
        '연곡해변 솔향기 캠핑장을 찾는 고객분들에게 알려드립니다 .

캠핑장 내 강릉시에서 운영하는 공용 와이파이가 설치되어 있지만 지형적인 여건으로 인하여 와이파이 신호가 매우 약하여 노트북을 이용한 사무업무 혹은 동영상 강의 등을 재생할 경우 인터넷 연결이 원활하지 않은 경우가 많습니다.

카라반 내부를 포함하여 캠핑장 내 전 구역에 캠핑장에서 개별적으로 운영하는 와이파이가 없으므로 이용에 참고하시기 바랍니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('YAYoung 개인정보처리방침 개정 안내',
        '보다 나은 서비스 제공을 위해 YAYoung의 개인정보처리방침이 다음과 같이 변경됨을 알려 드립니다.

▶ 개인정보처리방침 변경 및 시행일 : 2023년 12월 26일
▶ 개인정보처리방침 주요 변경 내용

YAYoung는 더 나은 서비스 제공을 위해 항상 최선을 다하겠습니다.

앞으로도 지속적인 서비스 이용과 관심 부탁드립니다.
감사합니다', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('자주 묻는 질문 FAQ 안내',
        '2022년 9월 27일자로 YAYoung 전자금융 선불 서비스가 신규 런칭함에 따라, 전자금융거래 이용약관이 추가되어 안내드립니다.
상세 내용은 아래 약관 전문을 통하여 확인 가능하오니 이용에 참고 부탁드립니다.

- 적용일자: 2022년 9월 27일
- 전문: 전자금융거래 이용약관

이용약관 동의 후 기존과 동일하게 야놀자 포인트 서비스 이용이 가능하오니, 고객님께서는 새롭게 추가된 이용약관을 확인하시어 이후 서비스 이용에 불편 없으시길 바랍니다.
감사합니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('전자금융거래 이용약관 안내',
        '2022년 9월 27일자로 YAYoung 전자금융 선불 서비스가 신규 런칭함에 따라, 전자금융거래 이용약관이 추가되어 안내드립니다.
상세 내용은 아래 약관 전문을 통하여 확인 가능하오니 이용에 참고 부탁드립니다.

- 적용일자: 2022년 9월 27일
- 전문: 전자금융거래 이용약관

이용약관 동의 후 기존과 동일하게 야놀자 포인트 서비스 이용이 가능하오니, 고객님께서는 새롭게 추가된 이용약관을 확인하시어 이후 서비스 이용에 불편 없으시길 바랍니다.
감사합니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('개인정보 처리방침 개정 안내',
        '2022년 9월 27일자로 YAYoung 전자금융 선불 서비스가 신규 런칭함에 따라, 전자금융거래 이용약관이 추가되어 안내드립니다.
상세 내용은 아래 약관 전문을 통하여 확인 가능하오니 이용에 참고 부탁드립니다.

- 적용일자: 2022년 9월 27일
- 전문: 전자금융거래 이용약관

이용약관 동의 후 기존과 동일하게 야놀자 포인트 서비스 이용이 가능하오니, 고객님께서는 새롭게 추가된 이용약관을 확인하시어 이후 서비스 이용에 불편 없으시길 바랍니다.
감사합니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('카카오톡 로그인 안내',
        '2022년 9월 27일자로 YAYoung 전자금융 선불 서비스가 신규 런칭함에 따라, 전자금융거래 이용약관이 추가되어 안내드립니다.
상세 내용은 아래 약관 전문을 통하여 확인 가능하오니 이용에 참고 부탁드립니다.

- 적용일자: 2022년 9월 27일
- 전문: 전자금융거래 이용약관

이용약관 동의 후 기존과 동일하게 야놀자 포인트 서비스 이용이 가능하오니, 고객님께서는 새롭게 추가된 이용약관을 확인하시어 이후 서비스 이용에 불편 없으시길 바랍니다.
감사합니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('전자금융거래 이용약관 안내',
        '2022년 9월 27일자로 YAYoung 전자금융 선불 서비스가 신규 런칭함에 따라, 전자금융거래 이용약관이 추가되어 안내드립니다.
상세 내용은 아래 약관 전문을 통하여 확인 가능하오니 이용에 참고 부탁드립니다.

- 적용일자: 2022년 9월 27일
- 전문: 전자금융거래 이용약관

이용약관 동의 후 기존과 동일하게 야놀자 포인트 서비스 이용이 가능하오니, 고객님께서는 새롭게 추가된 이용약관을 확인하시어 이후 서비스 이용에 불편 없으시길 바랍니다.
감사합니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('YAYoung 서비스 점검 안내',
        '2022년 9월 27일자로 YAYoung 전자금융 선불 서비스가 신규 런칭함에 따라, 전자금융거래 이용약관이 추가되어 안내드립니다.
상세 내용은 아래 약관 전문을 통하여 확인 가능하오니 이용에 참고 부탁드립니다.

- 적용일자: 2022년 9월 27일
- 전문: 전자금융거래 이용약관

이용약관 동의 후 기존과 동일하게 야놀자 포인트 서비스 이용이 가능하오니, 고객님께서는 새롭게 추가된 이용약관을 확인하시어 이후 서비스 이용에 불편 없으시길 바랍니다.
감사합니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('개인정보 방침 처리 안내',
        '2022년 9월 27일자로 YAYoung 전자금융 선불 서비스가 신규 런칭함에 따라, 전자금융거래 이용약관이 추가되어 안내드립니다.
상세 내용은 아래 약관 전문을 통하여 확인 가능하오니 이용에 참고 부탁드립니다.

- 적용일자: 2022년 9월 27일
- 전문: 전자금융거래 이용약관

이용약관 동의 후 기존과 동일하게 야놀자 포인트 서비스 이용이 가능하오니, 고객님께서는 새롭게 추가된 이용약관을 확인하시어 이후 서비스 이용에 불편 없으시길 바랍니다.
감사합니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('전자금융거래 이용약관 안내',
        '2022년 9월 27일자로 YAYoung 전자금융 선불 서비스가 신규 런칭함에 따라, 전자금융거래 이용약관이 추가되어 안내드립니다.
상세 내용은 아래 약관 전문을 통하여 확인 가능하오니 이용에 참고 부탁드립니다.

- 적용일자: 2022년 9월 27일
- 전문: 전자금융거래 이용약관

이용약관 동의 후 기존과 동일하게 야놀자 포인트 서비스 이용이 가능하오니, 고객님께서는 새롭게 추가된 이용약관을 확인하시어 이후 서비스 이용에 불편 없으시길 바랍니다.
감사합니다.', 1, now());
insert into NOTICE_TB (TITLE, CONTENT, USER_ID, CREATED_AT)
values ('1월 프로모션 종료 안내',
        '2022년 9월 27일자로 YAYoung 전자금융 선불 서비스가 신규 런칭함에 따라, 전자금융거래 이용약관이 추가되어 안내드립니다.
상세 내용은 아래 약관 전문을 통하여 확인 가능하오니 이용에 참고 부탁드립니다.

- 적용일자: 2022년 9월 27일
- 전문: 전자금융거래 이용약관

이용약관 동의 후 기존과 동일하게 야놀자 포인트 서비스 이용이 가능하오니, 고객님께서는 새롭게 추가된 이용약관을 확인하시어 이후 서비스 이용에 불편 없으시길 바랍니다.
감사합니다.', 1, now());









