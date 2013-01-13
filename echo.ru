# This application expects JSON.
require 'pp'
require 'json'
run ->env{
  raw_body = env['rack.input'].read
  body = raw_body.nil? || raw_body.empty? ? "" : JSON.parse(raw_body)
  puts "*" * 25
  pp body
  puts "*" * 25
  [200,
   {'Content-Type' => 'application/json'},
   [body == "" ? "" : JSON.dump(body)]
  ]
}
