(ns server.infra.http)

(defn obter-body [^js req]
  (-> req
      (. -body)
      (js->clj :keywordize-keys true)))

(defn bad-request [res erro]
  (-> res (.status 400) (.send (clj->js erro))))

(defn ok
  ([res] (ok res {}))
  ([res dados] (-> res
                   (.status 200)
                   (.send (clj->js dados)))))
