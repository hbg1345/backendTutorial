insert into article(title, content) values ('ㄴㅇㄹㄷ', 'ㄹㄷㅈㄷㅈㄷ');
insert into article(title, content) values ('ㄴㅇㄹㄷee', 'efewㄹㄷㅈㄷㅈㄷ');
insert into comment(article_id, nickname, body) values(1, '박씨', '굿');
insert into comment(article_id, nickname, body) values(1, '박씨2', '굿2');
insert into comment(article_id, nickname, body) values(2, '김씨', '배드');
insert into comment(article_id, nickname, body) values(2, '김씨2', '배드2');


insert into coffee(name, price) values ('아메리카노', 4500);
insert into coffee(name, price) values ('아이스티', 450);

insert into review(coffee_id, name, body, score) values (1, '유저1', '아아', 5);
insert into review(coffee_id, name, body, score) values (2, '유저2', '싸다', 5);
