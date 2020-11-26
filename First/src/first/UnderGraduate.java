package first;

public class UnderGraduate {

    private String id = null;
    private String name = null;
    private String tel = null;
    private String qq = null;
    private String mail = null;

    public UnderGraduate() {}

    public UnderGraduate(String u_id, String u_name, String u_tel, String u_qq, String u_mail) {
        id = u_id;
        name = u_name;
        tel = u_tel;
        qq = u_qq;
        mail = u_mail;
    }

    public void setId(String u_id) {
        id = u_id;
    }

    public void setName(String u_name) {
        name = u_name;
    }

    public void seu_tel(String u_tel) {
        tel = u_tel;
    }

    public void setQq(String u_qq) {
        qq = u_qq;
    }

    public void setMail(String u_mail) {
        mail = u_mail;
    }

    public String printUnderGraduateInfo() {

        return ("id: " + id + "  name: " + name + "  tel: " + tel + "  qq: " + qq + "  mail: " + mail);

    }

    public boolean isMatch(String query) {	//根据query的值进行查询，id精确，其余模糊
        if(id.equals(query))
            return true;
        else if(name.contains(query))
            return true;
        else if(tel.contains(query))
            return true;
        else if(qq.contains(query))
            return true;
        else if(mail.contains(query))
            return true;
        else
            return false;
    }

    public boolean isEquals(String u_id) {	//根据id来判断是否是同一个人
        if(u_id.equals(id))
            return true;
        return false;
    }
}
