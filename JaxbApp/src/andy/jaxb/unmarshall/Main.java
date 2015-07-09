package andy.jaxb.unmarshall;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			JAXBContext context= JAXBContext.newInstance(Person.class);
			Unmarshaller u=context.createUnmarshaller();
			Person person=(Person)u.unmarshal(new File("c:\\xmldir\\person.xml"));
			System.out.println(person.toString());
			
			context = JAXBContext.newInstance(Main.class);
			JAXBElement<Person> root=u.unmarshal(new StreamSource(new File("c:\\xmldir\\person.xml")), Person.class);
			Person p=root.getValue();
			System.out.println(p.toString());
			
			/** Marshalling **/
			context= JAXBContext.newInstance(Person.class);
			Marshaller m = context.createMarshaller();
			m.marshal(new Person(), System.out);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
