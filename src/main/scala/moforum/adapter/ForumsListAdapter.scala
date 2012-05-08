package moforum.adapter

import scala.xml._
import scala.collection.mutable.ListBuffer
import net.liftweb.json._
import JsonDSL._

/**
 * Converts an HTML listing of forums into JSON.
 */
class ForumsListAdapter extends Adapter[Node, String] {
	case class Forum(id: Int, name: String, description: String)
	case class Category(id: Int, name: String, forums: ListBuffer[Forum])

	val FORUM_CATEGORY_ID_PATTERN = """forumsindex\-c\-(\d+)\.html""".r
	val FORUM_ID_PATTERN = """forum(\d+)\.html""".r

	def extractId(href: String) = {
		href match {
			case FORUM_CATEGORY_ID_PATTERN(id) => id.toInt
			case FORUM_ID_PATTERN(id) => id.toInt
			case _ => -1
		}
	}

	def getDescription(tableDatum: Node) = {
		val spans = tableDatum \\ "span" filter {span => (span \ "@class").text == "genmed"}
		if (spans.length > 0)
			spans(0).text.trim()
		else ""
	}

	def adapt(html: Node) = {
		val tableData = (html \\ "td") filter {td => (td \ "@class").text.matches("catLeft|row1")}
		val forums = new ListBuffer[Category]
		val miscellany = Category(-1, "Miscellany", new ListBuffer[Forum])
		var currentCategory: Option[Category] = None
		for (tableDatum <- tableData) {
			val anchors = (tableDatum \\ "a") filter {a => (a \ "@class").text.matches("cattitle|forumlink")}
			for (anchor <- anchors) {
				val href = (anchor \ "@href").text
				val name = anchor.text
				val id = extractId(href)
				(anchor \ "@class").text match {
					case "cattitle" => {
						currentCategory = Some(Category(id, name, new ListBuffer[Forum]))
						forums += currentCategory.get
					}
					case "forumlink" => {
						currentCategory.getOrElse(miscellany).forums += Forum(id, name, getDescription(tableDatum))
					}
				}
			}
		}
		if (!miscellany.forums.isEmpty)
			forums += miscellany
		val json = ("forums" ->
			("categories" -> forums.map { category =>
				(("name" -> category.name) ~
				("id" -> category.id) ~
				("forums" -> category.forums.map { forum =>
					(("name" -> forum.name) ~
					("description" -> forum.description) ~
					("id" -> forum.id)
				)})
			)})
		)
  		compact(render(json))
  		//pretty(render(json))
	}
}