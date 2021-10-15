package hust.mysql.bean;

public class User {
    private String cid;    //主码，员工id
    private String userpwd;
    private String cname;  //用户真实姓名
    private Integer age;
    private String gender;
    private Integer flag;  //2:manager 1:cashier

    public String getCid(){return cid;}
    public void setCid(String cid){this.cid = cid;}
    public String getUserpwd() {
        return userpwd;
    }
    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
    public Integer getFlag(){return flag;}
    public void setFlag(int flag){this.flag = flag;}
    public String getCname(){return this.cname;}
    public void setCname(String cname){this.cname = cname;}
    public Integer getAge(){return this.age;}
    public void setAge(int age){this.age = age;}
    public String getGender(){return this.gender;}
    public void setGender(String gender){this.gender = gender;}

    public User(String cid,String userpwd,String cname,String gender,Integer age,Integer flag){
        this.cid = cid;
        this.userpwd = userpwd;
        this.cname = cname;
        this.gender = gender;
        this.age = age;
        this.flag = flag;
    }
    public User(){

    }

    @Override
    public String toString() {
        return "User: cid="+cid+",pwd="+userpwd+",cname="+cname+",age="+age+",gender="+gender+",flag="+flag;
    }
}
