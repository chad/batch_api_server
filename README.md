Batch API server in Scala/Play.

<pre>
# POST /batch
# Content-Type: application/json

{
  ops: [
    {method: "get",    url: "/patrons"},
    {method: "post",   url: "/orders/new",  params: {dish_id: 123}},
    {method: "get",    url: "/oh/no/error", headers: {break: "fast"}},
    {method: "delete", url: "/patrons/456"}
  ],
  sequential: true
}
</pre>


Here's my terrible workflow for playing with it so far:

$ play run

# another window

$ rackup echo.ru

# another window

$ bash r.sh
