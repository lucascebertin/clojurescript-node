(ns server.modules.irrf.controller
  (:require [malli.core :as m]
            [malli.error :as me]
            [server.modules.irrf.models :as models]
            [server.infra.http :as http]
            [server.modules.irrf.logics :as irrf.logics]))

(defn calcular-irrf [^js req ^js res]
  (let [{:keys [salario dependentes]} (http/obter-body req)]
    (http/ok res {:valor (irrf.logics/calcular-irrf salario dependentes)})))

(defn validar-chamada [^js req ^js res ^js next]
  (if-let [erro (->> req
                     http/obter-body
                     (m/explain models/IrrfRequestSchema)
                     (me/humanize))]
    (http/bad-request res erro)
    (next)))

