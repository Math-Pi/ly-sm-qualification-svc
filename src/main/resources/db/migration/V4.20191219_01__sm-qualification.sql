#skipErrorRow#
create or replace force view wfwzgjd.vc_zhxg_xgdw_jsjbxx as
select "JSID","GH","XM","XBM","XB","SFZJLXM","SFZJLX","SFZJH","BMID","BMDM","BMMC","BMLB","BMLBM","ZZMMM","ZZMM","LXDH","YX","QQ","WX","CSRQ","RZSJ","PXH","ZT","ZTMC","ZHXGSJ" from wfwzhxg.VC_ZHXG_XGDW_JSJBXX;

create or replace force view wfwzgjd.vc_zhxg_xgdw_zwgl_zwry as
select "PKID","ZWID","YHID","ZZJGID","RZKSSJ","RZJSSJ","RZZT","FPZID","FPSJ","ZZJGMC","ZZJGDM","YHXM","ZWMC","ZWLBMC","ZWTX","ZWLB" from wfwzhxg.VC_ZHXG_XGDW_ZWGL_ZWRY;

create or replace force view wfwzgjd.vc_zhxg_xsxx_xsjbxx as
select "XSID","XH","XM","XBM","XB","RXNF","SFZJLXM","SFZJLX","SFZJH","XQID","XQDM","XQMC","BMID","BMDM","BMMC","ZYID","ZYDM","ZYMC","ZYJC","XZ","CCDM","CCDMMC","BJID","BJDM","BJJC","SSNJ","BJMC","BYNF","CSRQ","SYDM","SYD","JGM","JG","MZM","MZ","ZZMMM","ZZMM","XSDQZTM","XSDQZT","XSLBM","XSLB","ZHXGSJ" from wfwzhxg.VC_ZHXG_XSXX_XSJBXX;

create or replace force view wfwzgjd.vc_zhxg_xsxx_xsjbxx_kz as
select "XSID","XH","XM","XBM","XB","RXNF","SFZJLXM","SFZJLX","SFZJH","XQID","XQDM","XQMC","BMID","BMDM","BMMC","ZYID","ZYDM","ZYMC","ZYJC","XZ","CCDM","CCDMMC","BJID","BJDM","BJJC","SSNJ","BJMC","BYNF","CSRQ","SYDM","SYD","JGM","JG","MZM","MZ","ZZMMM","ZZMM","XSDQZTM","XSDQZT","XSLBM","XSLB","SJHM","JTDZ","ZHXGSJ" from wfwzhxg.VC_ZHXG_XSXX_XSJBXX_KZ;

create or replace force view wfwzgjd.vc_zhxg_xsxx_xszp as
select "PKID","XSID","XSZP","ZPLXM" from wfwzhxg.VC_ZHXG_XSXX_XSZP;

create or replace force view wfwzgjd.vc_zhxg_xtgl_cssz as
select "PKID","CSBZ","CSMC","KJCS","CSZ","SSFW","PXH","ZT","ZHXGSJ" from wfwzhxg.VC_ZHXG_XTGL_CSSZ;

create or replace force view wfwzgjd.vc_zhxg_xtgl_dmk as
select "PKID","FLID","DMBZ","DMMC","DMMS","DM","MC","JC","PXH","FDM","ZT" from wfwzhxg.VC_ZHXG_XTGL_DMK;

create or replace force view wfwzgjd.vc_zhxg_xtgl_dmk_ssq as
select "DMBZ","DM","MC","SSQMC","PXH","FDM","ZT" from wfwzhxg.VC_ZHXG_XTGL_DMK_SSQ;

create or replace force view wfwzgjd.vc_zhxg_xtgl_jcsj_bjxx as
select "BJID","BJDM","BJMC","BJJC","SSNJ","XQID","XQDM","XQMC","BMID","BMDM","BMMC","ZYID","ZYDM","ZYMC","JBNY","ZT","ZTMC","ZHXGSJ" from wfwzhxg.VC_ZHXG_XTGL_JCSJ_BJXX;

create or replace force view wfwzgjd.vc_zhxg_xtgl_jcsj_bmxx as
select "BMID","BMDM","BMMC","BMJC","SJBMID","BMLB","BMLBMC","PXH","ZT","ZTMC","ZHXGSJ" from wfwzhxg.VC_ZHXG_XTGL_JCSJ_BMXX;

create or replace force view wfwzgjd.vc_zhxg_xtgl_jcsj_xqxx as
select "XQID","XQDM","XQMC","XQDZ","PXH","ZT","ZTMC","ZHXGSJ" from wfwzhxg.VC_ZHXG_XTGL_JCSJ_XQXX;

create or replace force view wfwzgjd.vc_zhxg_xtgl_jcsj_xxxx as
select "XXID","XXDM","XXMC","XXYWMC","XXDZ","XXYZBM","XZQHM","ICO","LOGO" from wfwzhxg.VC_ZHXG_XTGL_JCSJ_XXXX;

create or replace force view wfwzgjd.vc_zhxg_xtgl_jcsj_yhxx as
select "YHID","YHBH","YHXM","XBM","SFZJLXM","SFZJH","YHMM","YHLX","ZT" from wfwzhxg.VC_ZHXG_XTGL_JCSJ_YHXX;

create or replace force view wfwzgjd.vc_zhxg_xtgl_jcsj_zyxx as
select "PKID","ZYID","ZYDM","ZYMC","ZYJC","ZYYWMC","ZYFX","BMID","BMDM","BMMC","XZ","CCDM","CCDMMC","JLNY","PXH","ZT","ZTMC","ZHXGSJ" from wfwzhxg.VC_ZHXG_XTGL_JCSJ_ZYXX;

create or replace force view wfwzgjd.vc_zhxg_xtgl_jcsj_zzjg as
select "ZZJGID","ZZJGDM","ZZJGMC","FID","BMLB","PXH","ZT","ZZJGLX" from wfwzhxg.VC_ZHXG_XTGL_JCSJ_ZZJG;

create or replace force view wfwzgjd.vc_zhxg_xtgl_sjgl_dqnd as
select "SJDM","SJMC" from wfwzhxg.VC_ZHXG_XTGL_SJGL_DQND;

create or replace force view wfwzgjd.vc_zhxg_xtgl_sjgl_dqxn as
select "SJDM","SJMC" from wfwzhxg.VC_ZHXG_XTGL_SJGL_DQXN;

create or replace force view wfwzgjd.vc_zhxg_xtgl_sjgl_dqxq as
select "SJDM","SJMC","FDM" from wfwzhxg.VC_ZHXG_XTGL_SJGL_DQXQ;

create or replace force view wfwzgjd.vc_zhxg_xtgl_sjgl_nd as
select "SJDM","SJMC","ZT","ZTMC" from wfwzhxg.VC_ZHXG_XTGL_SJGL_ND;

create or replace force view wfwzgjd.vc_zhxg_xtgl_sjgl_xn as
select "SJDM","SJMC","ZT","ZTMC" from wfwzhxg.VC_ZHXG_XTGL_SJGL_XN;

create or replace force view wfwzgjd.vc_zhxg_xtgl_sjgl_xq as
select "SJDM","SJMC","FDM","ZT","ZTMC" from wfwzhxg.VC_ZHXG_XTGL_SJGL_XQ;

create or replace force view wfwzgjd.zhxg_fwgl_hdfwlb as
select "PKID","WFWBZ","WFWMC","WFWFL","SJKYH","WFWZT","WFWMS","PXH","JKQZ" from wfwzhxg.zhxg_fwgl_hdfwlb;

create or replace force view wfwzgjd.zhxg_xgdw_jsjbxx as
select "PKID","GH","XM","XBM","SFZJLXM","SFZJH","BMID","ZZMMM","CSRQ","RZSJ","LXDH","YX","QQ","WX","ZT","PXH","ZHXGSJ" from wfwzhxg.zhxg_xgdw_jsjbxx;

create or replace force view wfwzgjd.zhxg_xsxx_xsjbxx as
select "PKID","XH","XM","XBM","RXNF","SFZJLXM","SFZJH","XQID","BMID","ZYID","BJID","BYNF","CSRQ","SYDM","JGM","MZM","ZZMMM","XSDQZTM","XSLBM","ZHXGSJ" from wfwzhxg.ZHXG_XSXX_XSJBXX;

create or replace force view wfwzgjd.zhxg_xtgl_dmk_cl as
select "PKID","FLID","DM","MC","JC","FDM","PXH","ZT","DMBZ" from wfwzhxg.zhxg_xtgl_dmk_cl;

create or replace force view wfwzgjd.zhxg_xtgl_dmk_fl as
select "PKID","DMBZ","DMMC","DMMS","ZT","PXH","SSFW","ZHXGSJ" from wfwzhxg.zhxg_xtgl_dmk_fl;

create or replace force view wfwzgjd.zhxg_xtgl_dmk_fwfl as
select "PKID","FLDM","FLMC","ZT","FDM","PXH" from wfwzhxg.zhxg_xtgl_dmk_fwfl;

create or replace force view wfwzgjd.zhxg_xtgl_jcsj_bjxx as
select "PKID","BJDM","BJMC","BJJC","SSNJ","XQID","BMID","ZYID","JBNY","ZT","ZHXGSJ" from wfwzhxg.zhxg_xtgl_jcsj_bjxx;

create or replace force view wfwzgjd.zhxg_xtgl_jcsj_bmxx as
select "PKID","BMDM","BMMC","BMJC","SJBMID","BMLB","PXH","ZT","ZHXGSJ" from wfwzhxg.zhxg_xtgl_jcsj_bmxx;

create or replace force view wfwzgjd.zhxg_xtgl_jcsj_xxxx as
select "PKID","XXDM","XXMC","XXYWMC","XXDZ","XXYZBM","XZQHM","ICO","LOGO","DLYLOGO" from wfwzhxg.zhxg_xtgl_jcsj_xxxx;

create or replace force view wfwzgjd.zhxg_xtgl_jcsj_zyxx as
select "PKID","ZYDM","ZYMC","ZYJC","ZYYWMC","ZYFX","BMID","XZ","CCDM","JLNY","PXH","ZT","ZHXGSJ" from wfwzhxg.zhxg_xtgl_jcsj_zyxx;
