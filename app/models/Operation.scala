package models
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.libs.json._

case class Operation(method: String, url: String, params: Option[Map[String, Any]], headers: Option[Map[String,Any]]) {
	implicit object OperationWrites extends Writes[Operation] {
		def writes(op: Operation) = toJson(Map(
				"method" -> toJson(op.method),
				"url" -> toJson(op.url)
				))
	}
	def asJson() = {
		toJson(this)
	}
}

object Operation {
	implicit object OperationReads extends Reads[Operation] {
		def reads(json: JsValue) = {
			Operation(
					(json \ "method").as[String],
					(json \ "url").as[String],
					(json \ "params").asOpt[Map[String,JsValue]],
					(json \ "headers").asOpt[Map[String,JsValue]]
					)
		}
	}
	def fromJson(body:JsValue) = {
		(body \ "ops").asOpt[List[Operation]]
	}
}