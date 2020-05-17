drop trigger odd_project_PK_seq_tri ;
drop  sequence odd_project_PK_seq;
drop table odd_project;
drop table project;
drop table orders;
drop table patient;
drop table account;

--创建患者表
create table patient(
    pat_id varchar(20) primary key,   --患者编号
    pat_name varchar(30),       --患者姓名
    pat_sex varchar(3),         --性别
    pat_age number(3),          --年龄
    pat_phone varchar(11),        --电话号码
    pat_address varchar(50),      --地址
    pat_id_card varchar(20),      --身份证
    pat_nation varchar(10),       --民族
    pat_brith date,           --出生日期
    pat_job varchar(20),        --职业
    pat_height number(4,1),       --身高
    pat_weight number(5,2),       --体重
    pat_blood_pressure number(5,2),   --血压
    pat_temperature number(3,1),    --体温
    pat_remarks varchar(50)       --备注
);


--订单表
create table orders(
    ord_id varchar(20) primary key, --订单编号
    ord_price number(7,2) not null, --订单总价格
    disease_type varchar(30),   --病种类型
    ord_oper varchar(20),     --操作人员
    advice varchar(30),       --医嘱
    ord_date date,         --订单日期
    pat_id varchar(20),     --患者编号
    foreign key(pat_id) references patient(pat_id) ON DELETE CASCADE
    
);
select ord_id, to_char(ord_date,'yyyy-mm-dd hh24:mi:ss') ord_date, ord_price, disease_type, ord_oper,advice  from orders where pat_id='a001';

commit;

--创建 项目清单表格
create table project(
    pro_id   varchar2(20) primary key,  --项目编号
    pro_name   varchar2(30),      --项目名称
    units varchar2(10),         --单位
    pro_size  varchar2(20),       --规格
    price  number(5,2),         --项目价格      
    pro_type  varchar(30),        --项目类型
    prostock   number(5)        --库存量

);
insert into project values('p0001','打针',null,null,24.99,'服务',null);
insert into project values('p0002','输液',null,null,59.99,'服务',null);
insert into project values('p0003','针灸',null,null,59.99,'服务',null);
insert into project values('p1002','阿莫西林','盒',null,9.99,'西药',5); 
insert into project values('p1003','板蓝根','盒',null,19.99,'中药',99);
insert into project values('p1004','头孢克肟颗粒','盒',null,21.00,'西药',99);
insert into project values('p1005','罗红霉素颗粒','盒',null,13.99,'西药',99);
insert into project values('p1006','六位地黄丸','瓶',null,69.99,'中药',199);
insert into project values('p1007','人参','斤',null,88.99,'西药',99);
commit;


select * from project;




------
select p.pro_id, p.pro_name, p.pro_type, p.units, p.price, 
       op.pro_amount , op.all_price, p.pro_size , op.odd_id
from  project p ,odd_project op
where p.pro_id = op.pro_id and op.odd_id ='z000002';

--创建序列
create sequence odd_project_PK_seq start with 1 increment by 1 ;

--创建单号_项目记录表
create table odd_project  (
    id       number(10) primary key,
    pro_id    varchar2(20), --项目编号
    odd_id    varchar2(20), --订单号
    pro_amount   number(2),       --数量
    all_price   number(7,2),         --项目总价格
    foreign key(odd_id) references orders(ord_id) ON DELETE CASCADE,
    foreign key(pro_id) references project(pro_id)
);

select * from account;
commit;
select * from odd_project;
--delete from odd_project where odd_id = 'z000002';
--rollback;

--创建触发器
create or replace trigger odd_project_PK_seq_tri      
before insert on odd_project     
 for each row     
begin      
select odd_project_PK_seq.nextval into :new.id from dual;
end; 





commit;


select * from odd_project;



--账户表
create table account(
   account_username varchar(20) primary key,        --账号
   account_password varchar(20)         --密码
);

commit;
