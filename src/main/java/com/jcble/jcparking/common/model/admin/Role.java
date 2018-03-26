package com.jcble.jcparking.common.model.admin;

import baseproj.common.mybatis.mapper.annotation.TableMapperAnnotation;
import baseproj.common.mybatis.mapper.annotation.UniqueKeyType;
/**
 * 角色表
 * @author Jingdong Wang
 *
 */
@TableMapperAnnotation(tableName = "t_role", uniqueKeyType = UniqueKeyType.Single, uniqueKey = "id")
public class Role {
	// 主键ID
	private Integer id;
	// 创建者
	private Integer creator;
	// 创建时间
	private String createtime;
	// 修改者
	private Integer modifier;
	// 修改时间
	private String modifytime;
	// 角色编号
	private String roleno;
	// 角色名称
	private String rolename;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public Integer getModifier() {
		return modifier;
	}

	public void setModifier(Integer modifier) {
		this.modifier = modifier;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getRoleno() {
		return roleno;
	}

	public void setRoleno(String roleno) {
		this.roleno = roleno;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
