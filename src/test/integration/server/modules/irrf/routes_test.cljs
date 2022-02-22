(ns test.integration.server.modules.irrf.routes-test
  (:require [cljs.test :refer [deftest testing is]]
            [server.app :as app]
            [cljs.core.async :refer [go]]
            [cljs.core.async.interop :refer-macros [<p!]]
            ["supertest" :as supertest]))

(def app (app/start-server))

(defn post [url data]
  (-> app
      (supertest)
      (.post url)
      (.set "accept" "application/json")
      (.send (clj->js data))))

(deftest routes
  (testing "calcular irrf"
    (testing "quando salário for R$1000.00, deve ser isento, retornando valor igual a zero"
      (go (let [response (<p! (post "/v1/irrf" {:salario 1000.00
                                                :dependentes 0}))]
            (is (= 200
                   (-> response .-status)))

            (is (= {:valor 0}
                   (-> response
                       .-body
                       (js->clj :keywordize-keys true)))))))))

