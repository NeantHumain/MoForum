package common

import scala.xml.parsing.NoBindingFactoryAdapter
import scala.xml._
import nu.validator.htmlparser.{sax, common}
import sax.HtmlParser
import common.XmlViolationPolicy.ALLOW

/**
 * See [[http://www.hars.de/2009/01/html-as-xml-in-scala.html]].
 */
class HTML5Parser extends NoBindingFactoryAdapter {
	override def loadXML(source: InputSource, parser: SAXParser) = {
		val reader = new HtmlParser
		reader.setXmlPolicy(ALLOW)
		reader.setContentHandler(this)
		reader.parse(source)
		rootElem
	}
}