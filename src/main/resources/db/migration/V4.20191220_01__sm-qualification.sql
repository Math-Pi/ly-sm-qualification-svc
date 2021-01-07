#checksql-start#
	select decode(a.cou,0,'1','0') as is_exist from (
	  select t.TABLE_NAME,count(1) as cou from user_tables t group by t.TABLE_NAME
	) a where a.table_name='ZHXG_ZG_JTZGL'
#checksql-end#
#skipErrorRow#
create table ZHXG_ZG_JTZGL
(
  jtzid   VARCHAR2(50) not null,
  yszmc   VARCHAR2(300) not null,
  zlxdm   VARCHAR2(10) not null,
  zhqfsdm VARCHAR2(10) not null,
  csz     VARCHAR2(500) not null,
  ms      VARCHAR2(500)
);
comment on table ZHXG_ZG_JTZGL is '资格鉴定器-静态值管理';
comment on column ZHXG_ZG_JTZGL.jtzid is '静态值ID';
comment on column ZHXG_ZG_JTZGL.yszmc is '预设值名称';
comment on column ZHXG_ZG_JTZGL.zlxdm is '值类型代码，代码库ZGJD_ZLXDM，01:文本';
comment on column ZHXG_ZG_JTZGL.zhqfsdm is '值获取方式代码，代码库ZGJD_ZHQFSDM,01:sql语句查询';
comment on column ZHXG_ZG_JTZGL.csz is '参数值';
comment on column ZHXG_ZG_JTZGL.ms is '描述';
alter table ZHXG_ZG_JTZGL add constraint PK_ZHXG_ZG_JTZGL primary key (JTZID);

create table ZHXG_ZG_ZGDY
(
  zgid     VARCHAR2(50) not null,
  zgmc     VARCHAR2(100) not null,
  sszgmbdm VARCHAR2(10) not null,
  zhxgsj   DATE
);
comment on table ZHXG_ZG_ZGDY is '资格鉴定器-资格定义';
comment on column ZHXG_ZG_ZGDY.zgid is '资格ID';
comment on column ZHXG_ZG_ZGDY.zgmc is '资格名称';
comment on column ZHXG_ZG_ZGDY.sszgmbdm is '所属资格目标，代码库ZGJD_SSZGMBDM,stu:学生，tea:教师，cla:班级，room:宿舍，other:其他';
comment on column ZHXG_ZG_ZGDY.zhxgsj is '最后修改时间';
alter table ZHXG_ZG_ZGDY add constraint PK_ZHXG_ZG_ZGDY primary key (ZGID);

create table ZHXG_ZG_ZGGLMD
(
  id    VARCHAR2(50) not null,
  pcid  VARCHAR2(50) not null,
  bfhtj VARCHAR2(2000),
  zt    VARCHAR2(2),
  cjsj  DATE,
  fhtj  VARCHAR2(2000)
);
comment on table ZHXG_ZG_ZGGLMD is '资格过滤名单，用于在过滤的时候的中间表';
comment on column ZHXG_ZG_ZGGLMD.id is '对象ID';
comment on column ZHXG_ZG_ZGGLMD.pcid is '批次ID';
comment on column ZHXG_ZG_ZGGLMD.bfhtj is '不符合条件';
comment on column ZHXG_ZG_ZGGLMD.zt is '状态';
comment on column ZHXG_ZG_ZGGLMD.cjsj is '创建时间';
comment on column ZHXG_ZG_ZGGLMD.fhtj is '符合条件';
alter table ZHXG_ZG_ZGGLMD add constraint PK_TMP_QUAL_STUDENT primary key (ID, PCID);

create table ZHXG_ZG_ZGLSMD
(
  pkid  VARCHAR2(50) not null,
  dxid  VARCHAR2(50) not null,
  cjsj  DATE not null,
  pcid  VARCHAR2(50) not null,
  bfhtj VARCHAR2(2000),
  zt    VARCHAR2(2),
  fhtj  VARCHAR2(2000)
);
comment on table ZHXG_ZG_ZGLSMD is '资格鉴定器-资格临时名单';
comment on column ZHXG_ZG_ZGLSMD.pkid is '主键';
comment on column ZHXG_ZG_ZGLSMD.dxid is '对象ID,根据资格目标不同，此处存储的信息也不一样';
comment on column ZHXG_ZG_ZGLSMD.cjsj is '创建时间';
comment on column ZHXG_ZG_ZGLSMD.pcid is '批次ID,表示唯一生成的id数据，使用UUID生成';
comment on column ZHXG_ZG_ZGLSMD.bfhtj is '不符合的条件';
comment on column ZHXG_ZG_ZGLSMD.zt is '状态';
comment on column ZHXG_ZG_ZGLSMD.fhtj is '符合条件';
alter table ZHXG_ZG_ZGLSMD add constraint PK_ZHXG_ZG_ZGLSMD primary key (PKID);

create table ZHXG_ZG_ZGXGL
(
  zgxid      VARCHAR2(50) not null,
  zgxmc      VARCHAR2(100) not null,
  sszgmbdm   VARCHAR2(10) not null,
  hdfwid     VARCHAR2(50),
  zgst       VARCHAR2(50),
  zgtjzd     VARCHAR2(50),
  zgtjzdlxdm VARCHAR2(10),
  ms         VARCHAR2(300),
  xlkbz      VARCHAR2(100),
  yhtsxx     VARCHAR2(300),
  yhtszd     VARCHAR2(100)
);
comment on table ZHXG_ZG_ZGXGL is '资格鉴定器-资格项管理';
comment on column ZHXG_ZG_ZGXGL.zgxid is '资格项ID';
comment on column ZHXG_ZG_ZGXGL.zgxmc is '资格项名称';
comment on column ZHXG_ZG_ZGXGL.sszgmbdm is '所属资格目标，代码库ZGJD_SSZGMBDM,stu:学生，tea:教师，cla:班级，room:宿舍，other:其他';
comment on column ZHXG_ZG_ZGXGL.hdfwid is '后端服务ID,关联表';
comment on column ZHXG_ZG_ZGXGL.zgst is '资格视图';
comment on column ZHXG_ZG_ZGXGL.zgtjzd is '资格条件字段';
comment on column ZHXG_ZG_ZGXGL.zgtjzdlxdm is '资格条件字段类型代码，代码库ZGJD_ZGTJZDLXDM,01:文本，02：数字，03：下拉框，11：Boolean类型(是否)，12：Boolean类型(满足不满足)';
comment on column ZHXG_ZG_ZGXGL.ms is '描述';
comment on column ZHXG_ZG_ZGXGL.xlkbz is '下拉框标志,该下拉框标志属于HDFWID所关联的后端服务';
comment on column ZHXG_ZG_ZGXGL.yhtsxx is '用户提示信息，例如：性别是#{yhtszd}';
comment on column ZHXG_ZG_ZGXGL.yhtszd is '用户提示字段';
alter table ZHXG_ZG_ZGXGL add constraint PK_ZHXG_ZG_ZGXGL primary key (ZGXID);

create table ZHXG_ZG_ZGXBQ_GX
(
  pkid  VARCHAR2(50) not null,
  zgxid VARCHAR2(50) not null,
  bqbz  VARCHAR2(50) not null
);
comment on table ZHXG_ZG_ZGXBQ_GX is '资格鉴定器-资格项标签关系';
comment on column ZHXG_ZG_ZGXBQ_GX.pkid is '主键';
comment on column ZHXG_ZG_ZGXBQ_GX.zgxid is '资格项id';
comment on column ZHXG_ZG_ZGXBQ_GX.bqbz is '标签标志';
alter table ZHXG_ZG_ZGXBQ_GX add constraint ZHXG_ZG_ZGXBQ_GX_PK primary key (PKID);

create table ZHXG_ZG_ZGXFL_ZGX
(
  pkid  VARCHAR2(50) not null,
  zgxid VARCHAR2(50) not null,
  flid  VARCHAR2(50) not null
);
comment on table ZHXG_ZG_ZGXFL_ZGX is '资格鉴定器-资格项分类-资格项';
comment on column ZHXG_ZG_ZGXFL_ZGX.pkid is '主键';
comment on column ZHXG_ZG_ZGXFL_ZGX.zgxid is '资格项ID';
comment on column ZHXG_ZG_ZGXFL_ZGX.flid is '分类ID';
alter table ZHXG_ZG_ZGXFL_ZGX add constraint PK_ZHXG_ZG_ZGXFL_ZGX primary key (PKID);


create table ZHXG_ZG_ZGXGL_GLGZ
(
  glgzid   VARCHAR2(50) not null,
  glgzmc   VARCHAR2(300) not null,
  zgxid    VARCHAR2(50) not null,
  zd       VARCHAR2(50) not null,
  ysfdm    VARCHAR2(10) not null,
  zhqgzdm  VARCHAR2(10) not null,
  csz      VARCHAR2(100) not null,
  hcglgzid VARCHAR2(50),
  zdlxdm   VARCHAR2(10) not null
);
comment on table ZHXG_ZG_ZGXGL_GLGZ is '资格鉴定器-资格项管理-过滤规则';
comment on column ZHXG_ZG_ZGXGL_GLGZ.glgzid is '过滤规则ID';
comment on column ZHXG_ZG_ZGXGL_GLGZ.glgzmc is '过滤规则名称';
comment on column ZHXG_ZG_ZGXGL_GLGZ.zgxid is '资格项ID，关联表ZHXG_ZG_ZGXGL的ZGXID字段';
comment on column ZHXG_ZG_ZGXGL_GLGZ.zd is '字段';
comment on column ZHXG_ZG_ZGXGL_GLGZ.ysfdm is '运算符代码，代码库ZGJD_YSFDM,>：大于、>=：大于小于、=：等于、<=:小于等于、<：小于、like：包含,!=:不等于、not like:不包含';
comment on column ZHXG_ZG_ZGXGL_GLGZ.zhqgzdm is '值获取规则代码，代码库ZGJD_ZHQGZDM，01：固定值，02：静态值、03：平台参数';
comment on column ZHXG_ZG_ZGXGL_GLGZ.csz is '参数值';
comment on column ZHXG_ZG_ZGXGL_GLGZ.hcglgzid is '互斥过滤规则ID';
comment on column ZHXG_ZG_ZGXGL_GLGZ.zdlxdm is '字段类型代码,代码库ZGJD_ZDLX,01:文本、02：数字、03：日期';
alter table ZHXG_ZG_ZGXGL_GLGZ add constraint PK_ZHXG_ZG_ZGXGL_GLGZ primary key (GLGZID);

create table ZHXG_ZG_ZGX_BQ
(
  pkid VARCHAR2(50) not null,
  bqbz VARCHAR2(50) not null,
  bqmc VARCHAR2(100) not null
);
comment on table ZHXG_ZG_ZGX_BQ is '资格鉴定器-资格项-标签';
comment on column ZHXG_ZG_ZGX_BQ.pkid is '主键';
comment on column ZHXG_ZG_ZGX_BQ.bqbz is '标签标志';
comment on column ZHXG_ZG_ZGX_BQ.bqmc is '标签名称';
alter table ZHXG_ZG_ZGX_BQ add constraint ZHXG_ZG_ZGX_BQ_PK primary key (PKID);
alter table ZHXG_ZG_ZGX_BQ add constraint ZHXG_ZG_ZGX_BQ_UK unique (BQBZ);

create table ZHXG_ZG_ZGX_ZG
(
  zgxdyid   VARCHAR2(50) not null,
  zgid      VARCHAR2(50) not null,
  zgxid     VARCHAR2(50) not null,
  zgxdymc   VARCHAR2(300),
  ysfdm     VARCHAR2(10),
  csz       VARCHAR2(300),
  hczgxdyid VARCHAR2(50),
  pxh       NUMBER
);
comment on table ZHXG_ZG_ZGX_ZG is '资格鉴定器-资格项-资格';
comment on column ZHXG_ZG_ZGX_ZG.zgxdyid is '资格项定义ID';
comment on column ZHXG_ZG_ZGX_ZG.zgid is '资格ID';
comment on column ZHXG_ZG_ZGX_ZG.zgxid is '资格项ID';
comment on column ZHXG_ZG_ZGX_ZG.zgxdymc is '资格项定义名称';
comment on column ZHXG_ZG_ZGX_ZG.ysfdm is '运算符代码，代码库ZGJD_YSFDM,01：大于、02：大于小于、03：等于、04:小于等于、05：小于、07：包含';
comment on column ZHXG_ZG_ZGX_ZG.csz is '参数值';
comment on column ZHXG_ZG_ZGX_ZG.hczgxdyid is '互斥资格项定义ID';
comment on column ZHXG_ZG_ZGX_ZG.pxh is '排序号';
alter table ZHXG_ZG_ZGX_ZG add constraint PK_ZHXG_ZG_ZGX_ZG primary key (ZGXDYID);


--外键关联创建语句
alter table ZHXG_ZG_ZGXGL_GLGZ add constraint FK_ZHXG_ZG_ZGXGL_GLGZ foreign key (ZGXID) references ZHXG_ZG_ZGXGL (ZGXID);
alter table ZHXG_ZG_ZGXBQ_GX add constraint ZHXG_ZG_ZGXBQ_GX_FK foreign key (ZGXID) references ZHXG_ZG_ZGXGL (ZGXID);
alter table ZHXG_ZG_ZGX_ZG add constraint FK_ZHXG_ZG_ZGX_ZG1 foreign key (ZGXID) references ZHXG_ZG_ZGXGL (ZGXID);
alter table ZHXG_ZG_ZGX_ZG add constraint FK_ZHXG_ZG_ZGX_ZG2 foreign key (ZGID) references ZHXG_ZG_ZGDY (ZGID);
alter table ZHXG_ZG_ZGXFL_ZGX add constraint FK_ZHXG_ZG_ZGXFL_ZGX foreign key (ZGXID) references ZHXG_ZG_ZGXGL (ZGXID);

--初始化数据插入语句
insert into ZHXG_ZG_JTZGL (JTZID, YSZMC, ZLXDM, ZHQFSDM, CSZ, MS)
values ('128914e3-2567-405f-95b1-8f58f2c075b9', '上一学期', '01', '01', 'select  case when regexp_substr(t.CSZ,''[^-]+'',5,2,''i'')=1 then concat(concat(concat(regexp_substr(regexp_substr(t.CSZ,''[^-]+'',1,1,''i''),''[^-]+'',1,1,''i'')-1,''-'') ,regexp_substr(regexp_substr(t.CSZ,''[^_]+'',1,1,''i''),''[^-]+'',1,2,''i'')-1),''-2'') when  regexp_substr(t.CSZ,''[^-]+'',5,2,''i'')=2 then concat(substr(t.CSZ,1,9),''-1'') else ''0'' end as time_code from VC_ZHXG_XTGL_CSSZ t where t.CSBZ=''XTGL_DQXQ''', '获取上一学期的信息');

insert into ZHXG_ZG_JTZGL (JTZID, YSZMC, ZLXDM, ZHQFSDM, CSZ, MS)
values ('8c672c60-38ce-44ff-8c44-f3dedbc0285e', '上一学年', '01', '01', 'select concat(concat(regexp_substr(t.CSZ,''[^-]+'',1,1,''i'')-1,''-''),regexp_substr(t.CSZ,''[^-]+'',1,2,''i'')-1) as time_code from VC_ZHXG_XTGL_CSSZ t where t.CSBZ=''XTGL_DQXN''', '获取上一学年的信息');

insert into ZHXG_ZG_JTZGL (JTZID, YSZMC, ZLXDM, ZHQFSDM, CSZ, MS)
values ('b44d9317-c679-49b5-a422-21c38197342d', '当前学期', '01', '01', 'select t.CSZ from VC_ZHXG_XTGL_CSSZ t where t.CSBZ=''XTGL_DQXQ''', '获取当前学期的信息');

insert into ZHXG_ZG_JTZGL (JTZID, YSZMC, ZLXDM, ZHQFSDM, CSZ, MS)
values ('774aef79-1775-4230-bc87-456b7249d456', '当前年度', '01', '01', 'select t.CSZ from VC_ZHXG_XTGL_CSSZ t where t.CSBZ=''XTGL_DQND''', '获取当前年度的信息');

insert into ZHXG_ZG_JTZGL (JTZID, YSZMC, ZLXDM, ZHQFSDM, CSZ, MS)
values ('c0a86cf4-c8ee-4e59-a987-65b3dc7b8baa', '当前学年', '01', '01', 'select t.CSZ from VC_ZHXG_XTGL_CSSZ t where t.CSBZ=''XTGL_DQXN''', '获得当前学年的信息');

