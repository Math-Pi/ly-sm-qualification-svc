-- 以下代码会删除所有创建的资格，删除后使用到改资格的业务将找不到该资格
-- delete from ZHXG_ZG_ZGDY ;
-- delete from ZHXG_ZG_ZGGLMD;
-- delete from ZHXG_ZG_ZGLSMD;
-- delete from ZHXG_ZG_ZGX_ZG;
-- 以下语句会清除静态值、基本资格项、过滤规则等配置,请谨慎操作，
-- delete from ZHXG_ZG_ZGXFL_ZGX;
-- delete from ZHXG_ZG_ZGXBQ_GX;
-- delete from ZHXG_ZG_ZGX_BQ;
-- delete from ZHXG_ZG_ZGX_ZG;
-- delete from ZHXG_ZG_ZGXGL_GLGZ;
-- delete from ZHXG_ZG_ZGXGL;

commit;