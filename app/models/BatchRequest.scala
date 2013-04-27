package models
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.libs.json._


case class BatchRequest(ops: List[Operation], sequential: Boolean)

object BatchRequest {
  def fromJson(body: JsValue) = {
    BatchRequest((body \ "ops").as[List[Operation]],
        (body \ "sequential").as[String].toBoolean)
  }
}
