package andy.jaxb.unmarshall;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
	String firstName="";
	String lastName="";
	int age;
	String city="";
	String state="";
	String phone="";
	
	
	public Person(String firstName, String lastName, int age, String city,
			String state, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.city = city;
		this.state = state;
		this.phone = phone;
	}
	
	public Person(){}
	
	@XmlElement
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@XmlElement
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XmlElement
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@XmlElement
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@XmlElement
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@XmlElement
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ ", age=" + age + ", city=" + city + ", state=" + state
				+ ", phone=" + phone + "]";
	}
	
	
}
