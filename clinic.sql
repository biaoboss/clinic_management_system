drop trigger odd_project_PK_seq_tri ;
drop  sequence odd_project_PK_seq;
drop table odd_project;
drop table project;
drop table orders;
drop table patient;
drop table account;

--�������߱�
create table patient(
    pat_id varchar(20) primary key,   --���߱��
    pat_name varchar(30),       --��������
    pat_sex varchar(3),         --�Ա�
    pat_age number(3),          --����
    pat_phone varchar(11),        --�绰����
    pat_address varchar(50),      --��ַ
    pat_id_card varchar(20),      --���֤
    pat_nation varchar(10),       --����
    pat_brith date,           --��������
    pat_job varchar(20),        --ְҵ
    pat_height number(4,1),       --���
    pat_weight number(5,2),       --����
    pat_blood_pressure number(5,2),   --Ѫѹ
    pat_temperature number(3,1),    --����
    pat_remarks varchar(50)       --��ע
);


--������
create table orders(
    ord_id varchar(20) primary key, --�������
    ord_price number(7,2) not null, --�����ܼ۸�
    disease_type varchar(30),   --��������
    ord_oper varchar(20),     --������Ա
    advice varchar(30),       --ҽ��
    ord_date date,         --��������
    pat_id varchar(20),     --���߱��
    foreign key(pat_id) references patient(pat_id) ON DELETE CASCADE
    
);
select ord_id, to_char(ord_date,'yyyy-mm-dd hh24:mi:ss') ord_date, ord_price, disease_type, ord_oper,advice  from orders where pat_id='a001';

commit;

--���� ��Ŀ�嵥���
create table project(
    pro_id   varchar2(20) primary key,  --��Ŀ���
    pro_name   varchar2(30),      --��Ŀ����
    units varchar2(10),         --��λ
    pro_size  varchar2(20),       --���
    price  number(5,2),         --��Ŀ�۸�      
    pro_type  varchar(30),        --��Ŀ����
    prostock   number(5)        --�����

);
insert into project values('p0001','����',null,null,24.99,'����',null);
insert into project values('p0002','��Һ',null,null,59.99,'����',null);
insert into project values('p0003','���',null,null,59.99,'����',null);
insert into project values('p1002','��Ī����','��',null,9.99,'��ҩ',5); 
insert into project values('p1003','������','��',null,19.99,'��ҩ',99);
insert into project values('p1004','ͷ�߿�뿿���','��',null,21.00,'��ҩ',99);
insert into project values('p1005','�޺�ù�ؿ���','��',null,13.99,'��ҩ',99);
insert into project values('p1006','��λ�ػ���','ƿ',null,69.99,'��ҩ',199);
insert into project values('p1007','�˲�','��',null,88.99,'��ҩ',99);
commit;


select * from project;




------
select p.pro_id, p.pro_name, p.pro_type, p.units, p.price, 
       op.pro_amount , op.all_price, p.pro_size , op.odd_id
from  project p ,odd_project op
where p.pro_id = op.pro_id and op.odd_id ='z000002';

--��������
create sequence odd_project_PK_seq start with 1 increment by 1 ;

--��������_��Ŀ��¼��
create table odd_project  (
    id       number(10) primary key,
    pro_id    varchar2(20), --��Ŀ���
    odd_id    varchar2(20), --������
    pro_amount   number(2),       --����
    all_price   number(7,2),         --��Ŀ�ܼ۸�
    foreign key(odd_id) references orders(ord_id) ON DELETE CASCADE,
    foreign key(pro_id) references project(pro_id)
);

select * from account;
commit;
select * from odd_project;
--delete from odd_project where odd_id = 'z000002';
--rollback;

--����������
create or replace trigger odd_project_PK_seq_tri      
before insert on odd_project     
 for each row     
begin      
select odd_project_PK_seq.nextval into :new.id from dual;
end; 





commit;


select * from odd_project;



--�˻���
create table account(
   account_username varchar(20) primary key,        --�˺�
   account_password varchar(20)         --����
);

commit;
