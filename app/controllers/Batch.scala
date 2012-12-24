package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.libs.json._
import models.Operation

object Batch extends Controller {
  implicit object OperationWrites extends Writes[Operation] {
    def writes(op: Operation) = toJson(Map(
      "method" -> toJson(toJson(op.method)),
      "url" -> toJson(toJson(op.url))
    ))
  }

  implicit object OperationReads extends Reads[Operation] {
    def reads(json: JsValue) = Operation(
      (json \ "method").as[String],
      (json \ "url").as[String],
      (json \ "params").asOpt[Map[String,String]]
    )
  }

  def process = Action(parse.json) { request =>
    val ops = (request.body \ "ops").asOpt[List[Operation]].map{ o =>
     Logger.info("found an op" + o)
     o
    }
    Ok(toJson(ops))
  }

}
