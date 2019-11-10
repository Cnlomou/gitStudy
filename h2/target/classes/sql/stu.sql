create table tb_stu
(
	sid int,
	sname varchar(20),
	constraint tb_stu_pk
		primary key (sid)
);

insert into tb_stu values(1,'sjh');
insert into tb_stu values(2,'txy');
insert into tb_stu values(3,'zl');