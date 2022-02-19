(ns server.infra.db)

(def LinhaTabela
  [:map
   [:limite :double]
   [:deducao :double]
   [:aliquota :double]])

(def deducao-por-dependente 189.59)

(def tabela-inss
  [{:limite 1212.00 :deducao 0      :aliquota 7.5}
   {:limite 2427.35 :deducao 18.18  :aliquota 9.0}
   {:limite 3641.03 :deducao 91.00  :aliquota 12.0}
   {:limite 7087.22 :deducao 163.82 :aliquota 14.0}])

(def tabela-irrf
  [{:limite 1903.98 :deducao 0.00   :aliquota 0.00}
   {:limite 2826.65 :deducao 142.80 :aliquota 7.50}
   {:limite 3751.05 :deducao 354.80 :aliquota 15.00}
   {:limite 4664.68 :deducao 636.13 :aliquota 22.50}
   {:limite ##Inf   :deducao 869.36 :aliquota 27.50}])

