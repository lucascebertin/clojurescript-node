(ns server.app
  (:require ["express" :as express]
            ["cors" :as cors]
            ["body-parser" :as body-parser]
            [server.infra.router :as router]
            [server.modules.hello-world.routes :as hw.routes]
            [server.modules.irrf.routes :as irrf.routes]
            [server.modules.swagger.middlewares :as swagger.mw]))

(defn add-middlewares [^js app]
  (-> app
      (.use (cors))
      (.use (body-parser/json))
      (swagger.mw/add-middleware [irrf.routes/irrf-swagger
                                  hw.routes/hello-world-swagger])))

(defn start-server []
  (println "Starting server")
  (-> (express)
      add-middlewares
      (router/registrar-rotas irrf.routes/rota)
      (router/registrar-rotas hw.routes/rota)
      (.listen 3000 #(println "Example app listening on port 3000!"))))

