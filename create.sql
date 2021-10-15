create table user(
    cid char(30) primary key,
    pwd char(30) not null,
    cname char(30) not null,
    gender char(2),
    age int,
    flag int not null,
    check(gender in('男','女')),
    check(flag=1 OR flag=2)
);

create table vip(
    vid char(30) primary key,
    vname char(30),
    telephone char(11) not null,
    etime datetime not null,
    amount double not null
);

create table income(
    lid char(30) primary key,
    cid char(30),
    s_pay double not null,
    r_pay double not null,
    vid char(10),
    ptime datetime not null,
    foreign key(cid) references user(cid),
    foreign key(vid) references vip(vid)
);

create table goods(
    gid char(30) primary key,
    gname char(30) not null,
    price double not null,
    stock int not null
);

create table sells(
    gid char(30) not null,
    lid char(30) not null,
    buy_num int not null,
    primary key(gid,lid),
    foreign key(gid) references goods(gid),
    foreign key(lid) references income(lid)
);



/*结账时往income表中插入记录，若使用了会员卡，则自动更新会员卡的累计金额*/

delimiter ;;
create trigger t1
after insert on income
for each row
begin
    update vip set amount = amount + new.r_pay
    where vip.vid = new.vid;
end;;
delimiter ;



/*视图流水：订单编号，收银员编号，商品货号，购买数目，单价*/
create view bill(lid,cid,gid,buy_num,price,ptime) 
as
select income.lid,income.cid,sells.gid,sells.buy_num,goods.price,income.ptime
from income,goods,sells
where income.lid = sells.lid and sells.gid = goods.gid;
