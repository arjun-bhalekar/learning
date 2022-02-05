insert into USER values(10001,sysdate(),'Janet');
insert into USER values(10002,sysdate(),'Jam');
insert into USER values(10003,sysdate(),'Jill');

--- now insert into the post table
insert into post values(20001, 'My First POST', 10001);
insert into post values(20002, 'My Second POST', 10001);
