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

  def process = Action(parse.json) { request =>
    val ops = Operation.fromJson(request.body)
    
    Ok(toJson(ops))
  }

}
