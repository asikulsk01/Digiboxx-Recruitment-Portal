
```SQL
create user reservation identified by manager;

grant dba to reservation;

commit;

connect reservation/manager;

create table admin6(uname varchar2(40) primary key,name varchar2(40),
	pword varchar2(50),mail_id varchar2(60),phone_no varchar2(12));
	


create table register(uname varchar2(40) primary key,pword varchar2(50),
	fname varchar2(40),lname varchar2(40),
	addr varchar2(100), phno varchar2(12), mailid varchar2(60));

insert into admin6 values('admin','admin','admin','admin@tiu.edu','8909878908');

commit;
```
