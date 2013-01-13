Batch API server in Scala/Play.

It's a service implementation of Alex Koppel's  Batch API:

https://github.com/arsduo/batch_api

It's not done yet. It does, however, support changing the "sequential" flag, which is kinda cool.

The response output isn't correct, and it doesn't pass through all info the backend service correctly.  Ideally, you could also configure endpoints to hit for various services. For now it assumes they're all on the same host and port (localhost:9292) for convenience during development.



```

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
```



## Running

Here's my terrible workflow for playing with it so far:

```
$ play run
```
### another window
```
$ rackup echo.ru
```
### another window

```
$ bash r.sh
```
