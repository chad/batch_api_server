package models
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.libs.json._

case class BatchResult(status:Int, body: String, headers: Map[String,String]) 

object BatchResult {
	implicit object BatchResultWriter extends Writes[BatchResult] {
		def writes(batchResult: BatchResult)  = toJson(
				Map("status" -> toJson(batchResult.status), 
						"body" -> toJson(batchResult.body), 
						"headers" -> toJson(batchResult.headers))
				)
	}
}

case class BatchResponse(results: List[BatchResult]) {
	implicit object BatchResponseWriter extends Writes[BatchResponse] {
		def writes(batchResponse: BatchResponse) = toJson(Map(
				"results" -> batchResponse.results.map(toJson(_)).mkString(" ") 
				))
	}
	def asJson = {
			toJson(this)
	}
}


object BatchResponse {
	def fromJson(body: JsValue) = {
		BatchRequest((body \ "ops").as[List[Operation]],
				(body \ "sequential").as[Boolean])
	}
}