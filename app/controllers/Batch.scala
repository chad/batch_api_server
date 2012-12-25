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
      val url = WS.url("http://localhost:9292" + op.url)
      op.method match {
        case "post" => url.post(op.asJson)
        case "get" => url.get() // FIXME: params
        case "put" => url.put(op.asJson)
        case "delete" => url.delete
      }
    }
    Ok(responses.map(_.value.get.body).mkString("\n"))
  }
  

}
