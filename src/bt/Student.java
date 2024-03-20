package bt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Hung", 20, 3.28));
        studentList.add(new Student("Phuong", 19, 3.9));
        studentList.add(new Student("Quynh", 18, 2.6));

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("students");
            doc.appendChild(rootElement);

            for (Student student : studentList) {
                Element studentElement = doc.createElement("student");
                rootElement.appendChild(studentElement);

                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(student.getName()));
                studentElement.appendChild(nameElement);

                Element ageElement = doc.createElement("age");
                ageElement.appendChild(doc.createTextNode(String.valueOf(student.getAge())));
                studentElement.appendChild(ageElement);

                Element gpaElement = doc.createElement("gpa");
                gpaElement.appendChild(doc.createTextNode(String.valueOf(student.getGpa())));
                studentElement.appendChild(gpaElement);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("student_list.xml"));

            transformer.transform(source, result);

            System.out.println("File XML đã được tạo thành công.");

        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
