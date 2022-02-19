(ns server.infra.router)

(defn obter-interceptor [{:keys [interceptor]}]
  (or interceptor
      (fn [^js _req ^js _res ^js next] (next))))

(defn ^:private registrar-padrao [configs f]
  (when configs
    (f (obter-interceptor configs) (:handler configs))))

(defn registrar-post [^js app path configs]
  (registrar-padrao configs #(.post app path %1 %2)))

(defn registrar-get [^js app path configs]
  (registrar-padrao configs #(.get app path %1 %2)))

(defn registrar-put [^js app path configs]
  (registrar-padrao configs #(.put app path %1 %2)))

(defn registrar-patch [^js app path configs]
  (registrar-padrao configs #(.patch app path %1 %2)))

(defn registrar-delete [^js app path configs]
  (registrar-padrao configs #(.delete app path %1 %2)))

(defn registrar-rotas
  [^js app
   [path {:keys [get post put patch delete]}]]
  (registrar-get app path get)
  (registrar-post app path post)
  (registrar-put app path put)
  (registrar-patch app path patch)
  (registrar-delete app path delete)
  app)


