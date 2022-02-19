(ns server.modules.irrf.routes
  (:require [server.modules.swagger.logics :as swagger.logics]
            [server.modules.irrf.controller :as controller]
            [server.modules.irrf.models :as models]))

(def rota ["/v1/irrf" {:summary "Calcular IRRF."
                       :post {:title "Calcular IRRF."
                              :interceptor controller/validar-chamada
                              :handler controller/calcular-irrf
                              :parameters {:body {:name "dados de irrf"
                                                  :schema models/IrrfRequestSchema}}
                              :responses {200 models/IrrfResponseSchema}}}])

(def irrf-swagger
  (swagger.logics/criar-configuracao-para-swagger rota))

