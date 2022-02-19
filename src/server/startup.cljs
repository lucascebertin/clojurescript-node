(ns server.startup
  (:require [server.app :as app]))

(set! *warn-on-infer* true)

(defonce server (atom nil))

(defn start! []
  (reset! server (app/start-server))
  (.log js/console (. ^js @server -router)))

(defn express-stop-callback [done err]
  (when err (println err))
  (reset! server nil)
  (done))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn stop! [done]
  (.close @server (partial express-stop-callback done)))

#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn main! []
  (start!))

