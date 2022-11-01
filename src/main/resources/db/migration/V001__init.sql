CREATE TABLE FEE(
    ID  int not null,
    TYPE varchar(2) unique not null,
    FEE decimal(20, 2),
    primary key (id)
);

insert into FEE(ID,TYPE,FEE) values('1','A',10);
insert into FEE(ID,TYPE,FEE) values('2','B',20);
insert into FEE(ID,TYPE,FEE) values('3','C',30);