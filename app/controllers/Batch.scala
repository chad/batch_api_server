package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.libs.json._
import play.api.libs.ws._
import models.Operation

object Batch extends Controller {

  def process = Action(parse.json) { request =>
    val ops = Operation.fromJson(request.body) getOrElse List()
    val responses = ops.map { op =>
      val response = WS.url("http://localhost:9292" + op.url).post(op.asJson)
    }.mkString("\n")
    Ok(responses)
  }
  

}
