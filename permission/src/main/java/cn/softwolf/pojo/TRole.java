package cn.softwolf.pojo;

public class TRole {
    private Integer id;

    private String rolename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

	@Override
	public String toString() {
		return "TRole [id=" + id + ", rolename=" + rolename + "]";
	}
    
    
}