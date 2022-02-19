(ns server.modules.swagger.logics
  (:require [malli.swagger :as swagger]))

(defn assoc-some
  [m k v]
  (if (some? v)
    (assoc m k v)
    m))

(defn conj-some
  [coll v]
  (if (some? v)
    (conj coll v)
    coll))

(defn criar-body [opts]
  (when opts
    {:name (:name opts)
     :in "body"
     :required true
     :schema (swagger/transform (:schema opts))}))

(defn criar-query [opts]
  (when opts
    (->> opts
         :schema
         swagger/transform
         :properties
         (into [])
         (map (fn [[k v]] (merge {:name (name k)} v)))
         (into {})
         (map #(merge % {:name (:name opts)
                         :in "query"
                         :required true})))))

(defn criar-path [opts]
  (when opts
    (->> opts
         :schema
         swagger/transform
         :properties
         (into [])
         (map (fn [[k v]] (merge {:name (name k)} v)))
         (into {})
         (merge {:name (:name opts)
                 :in "path"
                 :required true}))))

(defn criar-parametros [{:keys [body path query]}]
  (-> []
      (conj-some (criar-body body))
      (conj-some (criar-path path))
      (conj-some (criar-query query))))

(defn criar-respostas
  [opts]
  (map (fn [[k v]] {(str k) {:description ""
                             :schema (swagger/transform v)}}) opts))

(defn criar-verbo [opts]
  (when opts
    {:operationId (:title opts)
     :consumes ["application/json"]
     :produces ["application/json"]
     :parameters (->> opts :parameters criar-parametros)
     :responses (->> opts :responses criar-respostas (into {}))}))

(defn criar-rotas [{:keys [get post put patch delete]}]
  (-> {}
      (assoc-some :post (criar-verbo post))
      (assoc-some :get (criar-verbo get))
      (assoc-some :put (criar-verbo put))
      (assoc-some :delete (criar-verbo delete))
      (assoc-some :patch (criar-verbo patch))))

(defn criar-configuracao-para-swagger [[path v]]
  {:paths {path (criar-rotas v)}})

