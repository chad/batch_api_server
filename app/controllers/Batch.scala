package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.libs.json._
import models.Operation

object Batch extends Controller {

  def process = Action(parse.json) { request =>
    val ops = Operation.fromJson(request.body) getOrElse List()
    val json = ops.map { op => op.asJson }
    val jsonResponse = "[" + json.mkString(",") + "]"
    Ok(jsonResponse)
  }
  

}
