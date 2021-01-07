package com.ly.cloud.controller.Interface.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ly.zhxg.utils.NHCollectionUtils;
import com.ly.zhxg.utils.NHStringUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 资格项定义的信息
 * @author yizhiqiang
 *
 */
public class QualConfigItem {
	private String zgxdymc;//资格项定义名称
	private List<String> selectSql=new ArrayList<>();//每个资格项拼凑出来的查询语句
	private List<String> yhtszdList=new ArrayList<>();//用户提示字段集合
	private List<String> yhtsSqlList=new ArrayList<>();//用户提示Sql集合
	private String loginUserId;
	private String pcid;
	private List<String> matchSqlList;
	private String yhts;
	/**
	 * 添加资格项
	 * @param map
	 */
	public void addItem(Map<String, Object> map,String anotherName) {  
		setZgxdymc((String)map.get("ZGXDYMC"),(String)map.get("YHTSXX"),(String)map.get("YHTSZD"));
		addSelectSql(buildSql(map,anotherName));
		addYhtsSql(map);
		addMatchSql(map);
	}

	public QualConfigItem() {
		this.matchSqlList = new ArrayList<>();
	}

	private void addMatchSql(Map<String, Object> map) {
		String zgtjzd = MapUtils.getString(map, "ZGTJZD");
		String sjkyh = MapUtils.getString(map, "SJKYH");
		String zgst = MapUtils.getString(map, "ZGST");
		String yhtsxx = MapUtils.getString(map, "YHTSXX","");
		String yhtszd = MapUtils.getString(map, "YHTSZD","");
		String whereSql=bulidWhereSql(map,true);
		//select t.id, replace('是当前学年认定为困难生(${sfknsmc})','${sfknsmc}',t.SFKNSMC) yhts from wfwkns.VZ_ZHXG_KNS_RDK_XNTJ t where 1=1  and SFKNS = '1' and XN=(select t.CSZ from VC_ZHXG_XTGL_CSSZ t where t.CSBZ='XTGL_DQXN')
		matchSqlList.add(new StringBuilder().append("select t.id, replace('").append(this.yhts).append("','${")
                .append(yhtszd.toLowerCase()).append("}',t.").append(yhtszd).append(") fhtj from ")
				.append(sjkyh).append(".").append(zgst).append(" t ").append(whereSql).toString());
	}

	/**
	 * 添加字段
	 * @param map
	 */
	private void addYhtsSql(Map<String,Object> map) {
		String yhtsxx=(String)map.get("YHTSXX");
		String yhtszd=(String)map.get("YHTSZD");
		if(NHStringUtils.isNotEmpty(yhtsxx) && NHStringUtils.isNotEmpty(yhtszd)){
			yhtszdList.add(yhtszd.toLowerCase());
			String jskyh=(String) map.get("SJKYH");//数据库用户名
			String zgst=(String) map.get("ZGST");//资格视图
			String whereSql=bulidWhereSql(map,true);
			if(NHStringUtils.isEmpty(whereSql)) {
				whereSql = " where t1.id=t.id ";
			}else{
				whereSql+= " and t1.id=t.id ";
			}
			String sql="select nvl(t1."+yhtszd+",'') from "+jskyh+"."+zgst+" t1 "+whereSql;
			yhtsSqlList.add(sql);
		}
	}

	/**
	 * 设置资格项定义的名称
	 * @param zgxdymc
	 */
	@SuppressWarnings("unused")
	private void setZgxdymc(String zgxdymc,String yhtsxx,String yhtszd) {
		if(NHStringUtils.isNotEmpty(yhtsxx) && NHStringUtils.isNotEmpty(yhtszd)){
			zgxdymc+="("+yhtsxx+")";
		}
		this.yhts = zgxdymc;
		if(this.zgxdymc==null) {
			this.zgxdymc=zgxdymc;
		}else {
			this.zgxdymc = this.zgxdymc +" 或  "+zgxdymc;
		}
	}
	
	/**
	 * 增加过滤的sql
	 * @param sql
	 */
	private void addSelectSql(String sql) {
		if(selectSql==null) {
			selectSql = new ArrayList<>();
		}
		selectSql.add(sql);
	}
	
	/**
	 * 拼凑当前资格项的查询语句
	 * @param map
	 * @param anotherName 
	 * @return
	 */
	private String buildSql(Map<String, Object> map, String anotherName) {
		String jskyh=(String) map.get("SJKYH");//数据库用户名
		String zgst=(String) map.get("ZGST");//资格视图
		String whereSql=bulidWhereSql(map,true);
		String sql = "select id from "+jskyh+"."+zgst+"  "+anotherName+" "+whereSql;
		return sql;
	}

	/**
	 * 拼凑资格项的where后面的查询条件
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String bulidWhereSql(Map<String, Object> map,boolean flag) {
		String zgtjzd=(String) map.get("ZGTJZD");//资格条件字段
		String ysfdm=(String) map.get("YSFDM");//运算符代码
		String csz=(String) map.get("CSZ");//参数值
		String zdlx=(String) map.get("ZGTJZDLXDM");//字段类型
		List<List<Map<String,Object>>> glgzList=(List<List<Map<String, Object>>>) map.get("GLGZLIST");
		
		StringBuilder whereSql=new StringBuilder("where 1=1 ");
		//1、处理教师设置的参数,flag为true表示需要拼接，老师设置的参数需要拼凑上去
		if(flag && NHStringUtils.isNotEmpty(zgtjzd) && NHStringUtils.isNotEmpty(ysfdm) && NHStringUtils.isNotEmpty(csz)) {
			whereSql.append(" and "+buildWhereCondition(zgtjzd, ysfdm, csz, zdlx, "01", null, null));
		}
		if(glgzList!=null) {
		for (List<Map<String, Object>> list : glgzList) {
			if(list.size()==1) {//只有一个的时候
				whereSql.append(" and "+buildWhereCondition(
						(String) list.get(0).get("ZD"), 
						(String) list.get(0).get("YSFDM"), 
						(String) list.get(0).get("CSZ"), 
						(String) list.get(0).get("ZDLXDM"), 
						(String) list.get(0).get("ZHQGZDM"), 
						(String) list.get(0).get("JTZ"), loginUserId));
			}else if(list.size()>1) {//存在多个的时候
				String tempSql="";
				for (Map<String, Object> map2 : list) {
					tempSql+=" or "+buildWhereCondition(
							(String) map2.get("ZD"), 
							(String) map2.get("YSFDM"), 
							(String) map2.get("CSZ"), 
							(String) map2.get("ZDLXDM"), 
							(String) map2.get("ZHQGZDM"), 
							(String) map2.get("JTZ"), loginUserId);
				}
				whereSql.append(" and ("+tempSql.substring(3)+")");
			}
		}
		}
		return whereSql.toString();
	}
	
	/**
	 * 拼凑一个查询条件
	 * @param fieldName 字段名
	 * @param ysfdm 运算符代码
	 * @param csz 固定参数值
	 * @param fieldType 字段类型，01：文本，02：数字，03：日期，11：Boolean类型(是否)，12：Boolean类型(满足不满足)
	 * @param zhqfs 值获取方式，01：固定值，02：静态值、03：平台参数
	 * @param jtz 静态值
	 * @param loginUserId 登录账号
	 * @return
	 */
	@SuppressWarnings("unused")
	private String buildWhereCondition(String fieldName,String ysfdm,String csz,String fieldType,String zhqfs
			,String jtz,String loginUserId) {
		String value="";
		//1、是固定值
		if("01".equals(zhqfs)) {
			if("like".equals(ysfdm) || "not like".equals(ysfdm)) {
				return fieldName+" "+ysfdm+" "+"'%"+csz+"%'";
			}else if("in".equals(ysfdm)){
				String[] cszList=NHStringUtils.isEmpty(csz)?new String[]{UUID.randomUUID().toString()}:csz.split(",");
				return fieldName+" in ("+NHCollectionUtils.buildSqlInStr(cszList)+")";
			}else if("02".equals(fieldType)){
				return fieldName+" "+ysfdm+" "+csz;
			}else if("03".equals(fieldType)) {
				return "to_date(to_char("+fieldName+",'yyyy-MM-dd'),'yyyy-MM-dd') "+ysfdm+" to_date('"+csz+"','yyyy-MM-dd')";
			}else {
				return fieldName+" "+ysfdm+" "+"'"+csz+"'";
			}
		}
		//2、获取静态参数
		else if("02".equals(zhqfs)) {
			return fieldName+"=("+jtz+")";
		}
		//3、获取平台登录信息
		else if("03".equals(zhqfs)) {
			return fieldName+"='"+loginUserId+"'";
		}
		return null;
	}
	
	public String getMatchSql() {
		return StringUtils.join(this.matchSqlList," union ");
	}
	
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public void setPcid(String pcid) {
		this.pcid = pcid;
	}

	/**
	 * 获取修改语句
	 * @param flag 数据，true:忽略掉那些不满足条件的学生
	 * @return
	 */
	public String getUpdateSql(Boolean flag) {
		String sql="update ZHXG_ZG_ZGGLMD t set t.zt='0',t.BFHTJ="+getNoPassCondition("'"+zgxdymc+"'",0)+" where pcid='"+pcid+"'  ";
		for (int i = 0; i < selectSql.size(); i++) {
			sql+=" and not exists ("+selectSql.get(i)+" and ttt"+i+".id=t.id) ";
		}
		if(flag) {
			sql+=" and zt='1'";
		}
		return sql;
	}

	/**
	 * 拼凑不通过的理由的sql语句
	 * @param sql
	 * @param index
	 * @return
	 */
	public String getNoPassCondition(String sql,Integer index){
		String sqlTemp = sql.substring(sql.lastIndexOf('('),sql.lastIndexOf(')')+1);
		
		String regEx = "[ '']|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(sqlTemp);
        //如果有特殊字符''
        if(m.find()){
        	String newStr = sqlTemp.replaceAll("'","''''");
    		sqlTemp = newStr;
        }
        System.out.println("sqlTemp: "+sqlTemp);
     
		//说明没有用户提示字段
		if(yhtszdList.size()<=0){
			return "nvl2(t.BFHTJ,t.BFHTJ||';','')||"+sql;
		}
		//说明已经全部拼凑完成了
		if(index==yhtszdList.size()){
			return "nvl2(t.BFHTJ,t.BFHTJ||';','')||"+sql;
		}
		String sql1="replace("+sql+",'"+sqlTemp+"',("+yhtsSqlList.get(index)+"))";
		return getNoPassCondition(sql1,index+1);
	}

	public String getZgxName(){
		return this.zgxdymc;
	}

	public List<String> getYhtszdList(){
		return this.yhtszdList;
	}

	public List<String> getYhtsSqlList(){
		return this.yhtsSqlList;
	}
}
