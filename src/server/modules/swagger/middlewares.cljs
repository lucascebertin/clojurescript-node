(ns server.modules.swagger.middlewares
  (:require [medley.core :as m]
            ["swagger-ui-express" :as swagger-ui-express]))

(def default-swagger
  {:swagger "2.0"
   :host "localhost:3000"
   :basePath "/"
   :schemes ["http"]
   :info {:title "Swagger"
          :description "This is a simple API documentation"
          :license {:name "Apache 2.0"
                    :url "http://www.apache.org/licenses/LICENSE-2.0.html"}
          :version "1.0.0"}})

(defn criar-swagger-json [swaggers]
  (->> swaggers
       (reduce #(m/deep-merge %1 %2))
       (merge default-swagger)
       clj->js))

(defn add-middleware [^js app swaggers]
  (-> app (.use "/api-docs"
                (. swagger-ui-express -serve)
                (.setup swagger-ui-express (criar-swagger-json swaggers)))))

