# This application expects JSON.
require 'pp'
require 'json'
run ->env{
  pp body = JSON.parse(env['rack.input'].read)
  [200, {'Content-Type' => 'application/json'}, [JSON.dump(body)]]
}
