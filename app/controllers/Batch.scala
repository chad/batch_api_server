package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json._
import play.api.libs.json.JsValue
import play.api.libs.json._
import play.api.libs.ws._
import models.Operation
import models.BatchRequest

object Batch extends Controller {
  trait WSResult
  case class SyncResult(response: play.api.libs.ws.Response) extends WSResult
  case class AsyncResult(response: play.api.libs.concurrent.Promise[play.api.libs.ws.Response]) extends WSResult

  /* Crappy hack */
  def externalServiceUrl = {
//    val r = new scala.util.Random
//    val ports = List(9292, 9293)
//    "http://localhost:" + ports(r.nextInt(2))
    "http://localhost:9292"
  }

  def process = Action(parse.json) { request =>
    val batchRequest = BatchRequest.fromJson(request.body)
    val ops = batchRequest.ops
    val responses = ops.map { op =>
      val url = WS.url(externalServiceUrl + op.url)
      val response = op.method match {
        case "post" => url.post(op.asJson)
        case "get" => url.get() // FIXME: params
        case "put" => url.put(op.asJson)
        case "delete" => url.delete
      }
      
      if( batchRequest.sequential ) new SyncResult(response.value.get) else new AsyncResult(response)
    }
   val body = responses.map { 
     case SyncResult(result) => result.body
     case AsyncResult(result) => result.value.get.body
     }.mkString("\n")
    Ok(body)
  }


}
