
Insert into T_SYSTEM (ID,CODE,NAME,ORGANIZATION,MEMO,FLAG,VERSION,CREATED_BY,CREATED_DT,UPDATED_BY,UPDATED_DT) values (SEQ_SYSTEM.NEXTVAL,'projects-creepers','量富征信网络爬虫管理系统','量富征信管理有限公司',null,'0',4,'admin',to_date('15-4月 -16','DD-MON-RR'),'admin',to_date('15-4月 -16','DD-MON-RR'));


Insert into T_ROLE (ID,CODE,NAME,SYSTEM_CODE,MEMO,FLAG,VERSION,CREATED_BY,CREATED_DT,UPDATED_BY,UPDATED_DT) values (SEQ_ROLE.NEXTVAL,'R_CREEPERS_ADMIN','网络爬虫系统管理员','projects-creepers','量富征信网络爬虫管理系统','0',1,'admin',to_date('18-2月 -16','DD-MON-RR'),'admin',to_date('18-2月 -16','DD-MON-RR'));

Insert into T_USER (ID,USER_NAME,REAL_NAME,PASSWORD,SALT,DEPARTMENT_CODE,GENDER,MOBILE,MAIL,POSTCODE,ADDRESS,TITLE,STATUS,MEMO,FLAG,VERSION,CREATED_BY,CREATED_DT,UPDATED_BY,UPDATED_DT) values (SEQ_USER.NEXTVAL,'creepers','网络爬虫系统管理员','f269a3dc0065158b7139381821d6d9ec0eb2e234','436ca4d7980d4a17','FORTUNECREDIT','M','13046642283','creepers@fosun.com',null,null,null,'0',null,'0',1,'admin',to_date('08-4月 -16','DD-MON-RR'),'admin',to_date('08-4月 -16','DD-MON-RR'));

Insert into T_USER_ROLE (ID,USER_NAME,ROLE_CODE,FLAG,VERSION,CREATED_BY,CREATED_DT,UPDATED_DT,UPDATED_BY) values (SEQ_USER_ROLE.NEXTVAL,'creepers','R_CREEPERS_ADMIN','0',1,'admin',to_date('08-4月 -16','DD-MON-RR'),to_date('08-4月 -16','DD-MON-RR'),'admin');
