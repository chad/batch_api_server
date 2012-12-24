import models.Operation
import org.specs2.mutable._


import play.api.test._
import play.api.test.Helpers._

class OperationSpec extends Specification {
  val samplePost = """
    {
  "ops": [
    {"method": "get",    "url": "/patrons"},
    {"method": "post",   "url": "/orders/new",  "params": {"dish_id": 123}},
    {"method": "get",    "url": "/oh/no/error", "headers": {"break": "fast"}},
    {"method": "delete", "url": "/patrons/456"}
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
		  println(operations(1))
		  operations(1).url must equalTo("/orders/new") // make sure it's the right one
          operations(1).params must equalTo(Some(Map("dish_id" -> 123)))
		}
	}
}
