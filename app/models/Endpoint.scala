package models

import com.typesafe.config.ConfigFactory

object Endpoint {
  def load() = {
    ConfigFactory.load("endpoints.properties")
  }
}