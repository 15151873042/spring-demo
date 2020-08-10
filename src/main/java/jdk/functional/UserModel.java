 package jdk.functional;

 /**
 * @author 胡鹏
 * @date 2020/07/06
 */
public class UserModel {

    private Integer age;
    
    private String sexKey;
    
    private String sexValue;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSexKey() {
        return sexKey;
    }

    public void setSexKey(String sexKey) {
        this.sexKey = sexKey;
    }

    public String getSexValue() {
        return sexValue;
    }

    public void setSexValue(String sexValue) {
        this.sexValue = sexValue;
    }

    public UserModel(Integer age, String sexKey) {
        this.age = age;
        this.sexKey = sexKey;
    }

    @Override
    public String toString() {
        return "UserModel [age=" + age + ", sexKey=" + sexKey + ", sexValue=" + sexValue + "]";
    }
}
