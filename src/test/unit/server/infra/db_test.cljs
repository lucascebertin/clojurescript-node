(ns test.unit.server.infra.db-test
  (:require [cljs.test :refer [deftest testing is]]
            [server.infra.db :as db]))

(defn tabela-possui-valores-crescentes? [tabela chave]
  (->> tabela
       (map chave)
       (partition 2 1)
       (map (fn [[n1 n2]] (> n2 n1)))
       (every? true?)))

(deftest deducao-por-dependente
  (testing "valor deve estar definido e ser maior que zero"
    (is (> db/deducao-por-dependente 0))))

(deftest tabela-inss
  (testing "tabela deve estar preenchida"
    (is (> (count db/tabela-inss) 0))
    (is (= (count db/tabela-inss) 4))
    (is (zero? (-> db/tabela-inss first :deducao)))
    (is (not (zero? (-> db/tabela-inss last :deducao))))
    (is (tabela-possui-valores-crescentes? db/tabela-inss :limite))
    (is (tabela-possui-valores-crescentes? db/tabela-inss :deducao))
    (is (tabela-possui-valores-crescentes? db/tabela-inss :aliquota))))

(deftest tabela-irrf
  (testing "tabela deve estar preenchida"
    (is (> (count db/tabela-irrf) 0))
    (is (= (count db/tabela-irrf) 5))
    (is (zero? (-> db/tabela-irrf first :deducao)))
    (is (not (zero? (-> db/tabela-irrf last :deducao))))
    (is (infinite? (-> db/tabela-irrf last :limite)))
    (is (tabela-possui-valores-crescentes? db/tabela-irrf :limite))
    (is (tabela-possui-valores-crescentes? db/tabela-irrf :deducao))
    (is (tabela-possui-valores-crescentes? db/tabela-irrf :aliquota))))

