package models
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.libs.json._


case class BatchRequest(ops: List[Operation], sequential: Boolean)

object BatchRequest {
  def fromJson(body: JsValue) = {
    BatchRequest((body \ "ops").as[List[Operation]],
      isSequential(body \ "sequential"))
  }

  def isSequential(value: play.api.libs.json.JsValue) = {
    value match {
      case JsString(_) => value.as[String].toBoolean
      case JsBoolean(_) => value.as[Boolean]
      case _ => throw new Exception("Failed to parse sequential attribute from JSON")
    }
  }
}
