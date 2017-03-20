package com.atul;

import java.io.StringWriter;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

public class TestXMLBuilder {

	/**
	 * 
		<?xml version="1.0" encoding="UTF-8" standalone="no"?>
		<Root param1="val1" param2="val2">
		    <ListElement>
		        <ListParam name="name1" value="val1" />
		        <ListParam name="name2" value="val2" />
		    </ListElement>
		    <Load>
		        <SubLoad>
		            <Task name="name2" value="val2" />
		        </SubLoad>
		    </Load>
		</Root>	 	 
	 * @throws Exception
	 */
	@Test
	public void testSimpleXML() throws Exception{
		Document doc = new XMLBuilder()
				.addElement("Root")
				.addAttribute("param1", "val1")
				.addAttribute("param2", "val2")		
					.addElement("ListElement")		
						.addElement("ListParam")
						.addAttribute("name", "name1")
						.addAttribute("value", "val1")
					.parent()
						.addElement("ListParam")
						.addAttribute("name", "name2")
						.addAttribute("value", "val2")
					.parent()		
				.parent()
					.addElement("Load")
						.addElement("SubLoad")
							.addElement("Task")
							.addAttribute("name", "name2")
							.addAttribute("value", "val2")
				.build();

		String retXml = getXML(doc);
		System.out.println(retXml);
		
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Root param1=\"val1\" param2=\"val2\"><ListElement><ListParam name=\"name1\" value=\"val1\"/><ListParam name=\"name2\" value=\"val2\"/></ListElement><Load><SubLoad><Task name=\"name2\" value=\"val2\"/></SubLoad></Load></Root>";
		Assert.assertEquals(retXml, expected);
		
	}

	public String getXML(Document doc) {
		StringWriter writer = new StringWriter();
		try {
			DOMSource source = new DOMSource(doc);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer xform = factory.newTransformer();
			StreamResult result = new StreamResult(writer);
			xform.transform(source, result);
			return writer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
