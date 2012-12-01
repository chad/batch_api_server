package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json._

object Batch extends Controller {

  def process = Action(parse.json) { request =>
    (request.body \ "ops").asOpt[Map[String, String]].map { ops =>
    Ok("ug")
      }.getOrElse {
        BadRequest("Missing parameter [ops]" + request.body)
      }
  }

}
