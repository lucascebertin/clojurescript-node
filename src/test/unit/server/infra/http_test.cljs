(ns test.unit.server.infra.http-test
  (:require [cljs.test :refer [deftest testing is]]
            [test.unit.server.mock.express :as express]
            [server.infra.http :as http]))

(deftest ok
  (testing "deve retornar status 200 com body"
    (let [_resposta (js->clj (http/ok express/express-mock))]
      (is (= 200 (:status @express/express-calls)))
      (is (= {} (-> @express/express-calls :data  js->clj))))))

