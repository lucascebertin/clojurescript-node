(ns server.modules.hello-world.routes
  (:require [server.modules.swagger.logics :as swagger.logics]
            [server.modules.hello-world.controller :as controller]))

(def rota ["/v1/hello" {:summary "Hello World"
                        :get {:title "Say hello world"
                              :handler controller/hello
                              :responses {200 :string}}}])

(def hello-world-swagger
  (swagger.logics/criar-configuracao-para-swagger rota))
