package models

//case class Operation(method: String, url: String)
case class Operation(method: String, url: String, params: Option[Map[String,String]])

