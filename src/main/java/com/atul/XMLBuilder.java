package com.atul;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * This is a builder class used for building dynamic XML's on the fly
 * @author SAtul
*/
public class XMLBuilder {
	private Document doc = null;
	private Element currentElement = null;

	/**
	 * Initialize xml builder
	 * @throws ParserConfigurationException
	 */
	public XMLBuilder() throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbf.newDocumentBuilder();
		this.doc = docBuilder.newDocument();
	}

	/**
	 * Once you are done with all changes, use it to build final doc
	 * @return
	 */
	public Document build() {
		return doc;
	}

	/**
	 * Go back one level up.
	 * @return
	 */
	public XMLBuilder parent() {
		currentElement = (Element) currentElement.getParentNode();
		if(currentElement == null){
			throw new RuntimeException("This is the top level element");
		}
		
		return this;
	}

	/**
	 * Add new element with a given name
	 * 
	 * @param name
	 * @return
	 */
	public XMLBuilder addElement(String name) {
		Element current = doc.createElement(name);
		if (currentElement == null) {
			// first element
			doc.appendChild(current);
		} else {
			currentElement.appendChild(current);
		}

		currentElement = current;
		return this;
	}

	/**
	 * Add attribute to the current element
	 * @param attribute
	 * @param value
	 * @return
	 */
	public XMLBuilder addAttribute(String attribute, String value) {
		if (currentElement == null) {
			throw new RuntimeException("New element is not created");
		}

		currentElement.setAttribute(attribute, value);
		return this;
	}
}
