package cn.softwolf.pojo;

import java.util.List;

public class TUser {
    private Integer id;

    private String username;

    private String password;
    
    private List<TRole> roleList;//一个用户具有多个角色

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public List<TRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<TRole> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "TUser [id=" + id + ", username=" + username + ", password=" + password + ", roleList=" + roleList + "]";
	}
    
    
}