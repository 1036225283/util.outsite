package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * name是这个类的名称
 * map里面装的是字段和字段值
 * key里面放的是主键，主键有可能不只一个
 * @author 1036225283
 * 数据描述系统，描述数据版本更新 version
 * 
 * 建立数据同步服务
 * 首先，验证身份，验证通过
 * 然后，验证权限，验证通过
 * 然后，验证数据，验证通过
 * 数据验证有3个字段，表名，唯一主键，版本号
 * 如果该资源版本号与内存中存在的数据版本号一致，则直接返回版本号
 * 然后，获取数据，返回数据
 *
 *
 *接收数据的时候，要查看ip地址和请求频率
 *建立强制刷新数据服务
 *先看内存中有没有该数据，数据凭证为表名-唯一主键-版本号
 *
 *内存列式数据库
 *内存行式数据库
 *
 */
public class Entity {

	private String name;
	
	private Map<String, Object> key = new HashMap<String, Object>();
	
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/**
	 * 内存池
	 */
	private List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
	

	public List<Map<String, Object>> getList() {
		return list;
	}

	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	
}
