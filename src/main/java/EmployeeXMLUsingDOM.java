
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EmployeeXMLUsingDOM {
	public static void main(String[] args) {

		Employee employee1 = new Employee("E00001", "Vinod Vukkalam", "Software");
		Employee employee2 = new Employee("E00002", "Nevaan Skanda", "Master chef");

		generateXMLUsingDOM(employee1, employee2);
	}

	private static void generateXMLUsingDOM(Employee... employees) {
		try {
			DocumentBuilderFactory doc_factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder doc_builder = doc_factory.newDocumentBuilder();

			// Create root element
			Document doc = doc_builder.newDocument();
			Element root_element = doc.createElement("Employees");
			doc.appendChild(root_element);

			for (Employee employee : employees) {
				// Create employee element
				Element employeeElement = doc.createElement("Employee");
				root_element.appendChild(employeeElement);

				// Create child elements and add them to the employee element
				Element id = doc.createElement("Employee_id");
				id.appendChild(doc.createTextNode(employee.getId()));
				employeeElement.appendChild(id);

				Element name = doc.createElement("Employee_name");
				name.appendChild(doc.createTextNode(employee.getName()));
				employeeElement.appendChild(name);

				Element department = doc.createElement("Employee_department");
				department.appendChild(doc.createTextNode(employee.getDepartment()));
				employeeElement.appendChild(department);
			}

			// Transform the DOM object to XML file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("employees.xml"));
			transformer.transform(source, result);

			System.out.println("XML file generated successfully.");

		} catch (ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

}
