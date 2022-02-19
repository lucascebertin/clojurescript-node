(ns server.modules.hello-world.controller
  (:require [server.infra.http :as http]))

(defn hello [^js _req ^js res]
  (http/ok res "Hello, world!"))


