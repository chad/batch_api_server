# This application expects JSON.
require 'pp'
require 'json'
run ->env{
  raw_body = env['rack.input'].read
  body = raw_body.nil? || raw_body.empty? ? "" : JSON.parse(raw_body)
  pp body
  [200,
   {'Content-Type' => 'application/json'},
   [body == "" ? "" : JSON.dump(body)]
  ]
}
