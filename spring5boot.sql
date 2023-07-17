-- member
create table member2 (
    mno     int             auto_increment,
    userid  varchar(18)     unique,
    passwd  varchar(18)     not null,
    name    varchar(18)     not null,
    email   varchar(50)     unique,
    zipcode char(7)         not null,
    addr1   varchar(100)    not null,
    addr2   varchar(100)    not null,
    phone   char(13)        not null,
    regdate datetime        default current_timestamp,
    primary key (mno)
);

insert into member2
    (userid, passwd, name, email, zipcode, addr1, addr2, phone)
values ('abc123','987xyz','tt','tt@abc123.co.kr',
        '123456','서울 관악구','블라블라','123-4567-8912');

select  * from member2;


-- board
create table board2 (
    bno         int      auto_increment,
    title       varchar(100)      not null,
    userid      varchar(18)       not null,
    regdate     datetime          default current_timestamp,
    thumbs      int               default 0,
    views       int               default 0,
    contents    text              not null,
    ipaddr      varchar(15)       not null,
    primary key (bno)
    -- foreign key (userid) references member2(userid)
);

alter table board2
    add constraint fkuid
        foreign key (userid) references member2 (userid);


insert into board2
    (userid, title,contents, ipaddr)
values ('abc123', ' 구글, 사람처럼 진찰하는','뇨뇨뇽','127.0.0.1');

insert into board2
(userid, title,contents, ipaddr)
values ('abc123a', '파리 생제르망','뇨뇨뇽','127.0.0.1');

insert into board2
(userid, title,contents, ipaddr)
values ('ubub0322' , '데이터센터 구축','뇨뇨뇽','127.0.0.1');



select
    count(userid) cnt, ceil(count(userid) /25) pages
from board2;


select * from board2
where title like '%데이터%';


-- pds
create table pds (
    pno          int               auto_increment,
     title       varchar(100)      not null,
     userid      varchar(18)       not null,
     regdate     datetime          default current_timestamp,
     thumbs      int               default 0,
     views       int               default 0,
     contents    text              not null,
     ipaddr      varchar(15)       not null,
     primary key (pno)
);


create table pdsattach  (
    pano        int             auto_increment,
    pno         int             not null,      -- 게시글 번호
    fname       varchar(200)    not null,      -- uuid 포함 (다른 사람이 똑같은 파일 올리면 충돌, uuid:식별코드)
    ftype       varchar(3)      not null,      -- 파일의 종류를 아이콘화(압축형식)
    fsize       float           not null ,     -- 파일 용량
    fdown       int             default 0,      -- 몇 번 다운받았는지
    primary key (pano)
);

alter table pds
    add constraint fkpuid
    foreign key (userid) references member2 (userid);

alter table pdsattach
    add constraint fkpno
        foreign key (pno) references pds (pno);


-- join
select * from pds p join pdsattach pa
                         using(pno) where p.pno = '5';

--  view (가상테이블)
create view ppa
as
select * from pds p join pdsattach pa
                         using(pno);

select * from ppa where pno = '5';











