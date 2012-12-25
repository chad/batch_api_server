import models.Operation
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.JsString

class OperationSpec extends Specification {
  val samplePost = """
			{
			"ops": [
			{"method": "get",    "url": "/patrons"},
			{"method": "post",   "url": "/orders/new",  "params": {"dish_id": 123}},
			{"method": "get",    "url": "/oh/no/error", "headers": {"break": "fast"}},
			{"method": "delete", "url": "/patrons/456"},
			{"method": "post",   "url": "/orders/new",  "params": {"dish_id": 123, "kancho": 1}},
			{"method": "post",   "url": "/orders/new",  "params": {"dish_id": 123, "a_string":"o hello"}}

			],
			"sequential": true
			}
			"""

  "An Operation" should {

    "be parsable from JSON" in {
      val rawBody = play.api.libs.json.Json.parse(samplePost)
      val operations = Operation.fromJson(rawBody) getOrElse List()

      operations(0).url must equalTo("/patrons")
      operations(3).url must equalTo("/patrons/456")

    }
    "be writable as JSON" in {
      val o = Operation("get", "/foo/bar", Some(Map()))
      o.asJson().toString must contain("""{"method":""")
    }

    "be able to parse params" in {
      val rawBody = play.api.libs.json.Json.parse(samplePost)
      val operations = Operation.fromJson(rawBody) getOrElse List()
      operations(5).params.get("a_string") must equalTo(JsString("o hello"))
      //			(Some(Map("dish_id" -> 123, "a_string" -> "1")))
    }

  }
}
