import models.BatchRequest
import scala.io
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.JsString

class BatchRequestSpec extends Specification {
  "A BatchRequest" should {
    "be parsable from JSON" in {
      val source = scala.io.Source.fromFile("test/fixtures/batch.txt").mkString
			val rawBody = play.api.libs.json.Json.parse(source)
      BatchRequest.fromJson(rawBody)
      1 must equalTo(1)
    }
  }

}
