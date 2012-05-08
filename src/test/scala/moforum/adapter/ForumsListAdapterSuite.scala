package moforum.adapter

import scala.xml._
import common.HTML5Parser
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ForumsListAdapterSuite extends FunSuite with BeforeAndAfter {
	var adapter: ForumsListAdapter = _

	before {
		adapter = new ForumsListAdapter
	}

	test("adapt") {
		val parser = new HTML5Parser
		val html = parser.loadFile("forums.html")
		assert(html != null)
		val result = adapter.adapt(html)
		assert(result != null)
		println(result)
	}
}