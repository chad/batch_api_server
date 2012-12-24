package models
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.libs.json._

case class Operation(method: String, url: String, params: Option[Map[String,String]]) {
	implicit object OperationWrites extends Writes[Operation] {
		def writes(op: Operation) = toJson(Map(
				"method" -> toJson(toJson(op.method)),
				"url" -> toJson(toJson(op.url))
				))
	}
	def asJson() = {
		toJson(this)
	}
}

object Operation {
	implicit object OperationReads extends Reads[Operation] {
		def reads(json: JsValue) = Operation(
				(json \ "method").as[String],
				(json \ "url").as[String],
				(json \ "params").asOpt[Map[String,String]]
				)
	}
	def fromJson(body:JsValue) = {
		(body \ "ops").asOpt[List[Operation]]
	}
}