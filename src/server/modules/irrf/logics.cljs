(ns server.modules.irrf.logics
  (:require [server.infra.db :as db]))

(defn truncar-valor [valor]
  (-> valor
      (.toFixed 2)
      (js/parseFloat)))

(defn ^:private aplicar-calculo
  [linha-tabela salario-bruto]
  {:schema [:=> [:cat db/LinhaTabela :double] :double]}
  (-> (:aliquota linha-tabela)
      (/ 100)
      (* salario-bruto)
      (- (:deducao linha-tabela))))

(defn ^:private calcular-base
  [tabela salario-bruto]
  {:schema [:=> [:cat :vector db/LinhaTabela :double] :double]}
  (->> tabela
       (filter #(<= salario-bruto (:limite %)))
       (map #(aplicar-calculo % salario-bruto))
       first))

(defn calcular-inss
  [salario-bruto]
  {:schema [:=> [:cat :double] :double]}
  (truncar-valor
   (or (calcular-base db/tabela-inss salario-bruto)
       (calcular-base db/tabela-inss (->> db/tabela-inss last :limite)))))

(defn calcular-irrf
  [salario-bruto qtde-de-dependentes]
  {:schema [:=> [:cat :double :int] :double]}
  (let [valor-de-inss (calcular-inss salario-bruto)
        desconto-por-dependentes (* qtde-de-dependentes db/deducao-por-dependente)
        salario-com-descontos (-> salario-bruto
                                  (- valor-de-inss)
                                  (- desconto-por-dependentes))]
    (truncar-valor (calcular-base db/tabela-irrf salario-com-descontos))))

