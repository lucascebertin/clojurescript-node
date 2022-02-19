(ns test.server.infra.router-test
  (:require [server.infra.http :as http]
            [server.infra.router :as router]
            [cljs.test :refer [deftest testing is]]))

(def schema-in
  [:map
   [:x :int]])

(def schema-out
  schema-in)

(def rota ["/path1/path2"
           {:summary "text"
            :post {:title "swagger title"
                   :interceptor #(%3)
                   :handler #(-> %2 http/ok)
                   :parameters {:body {:name "x in"
                                       :schema schema-in}}
                   :responses {200 schema-out}}}])

(deftest obter-interceptor
  (testing "deve retornar a function do interceptor"
    (is (fn? (router/obter-interceptor rota))))

  (testing "deve retornar interceptor padrão não fornecido"
    (is (fn? (router/obter-interceptor
              [(first rota)
               (-> (second rota)
                   (update :post dissoc :interceptor))])))))


