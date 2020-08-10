package rabbitmq.spring;

import java.io.Serializable;

public class MessageObject implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;
	
	private String id;

	private String name;
	
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MessageObject(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public MessageObject() {
		super();
	}

	@Override
	public String toString() {
		return "MessageObject [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
}
