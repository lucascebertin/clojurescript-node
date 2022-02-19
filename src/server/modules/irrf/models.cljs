(ns server.modules.irrf.models)

(def IrrfRequestSchema
  [:map
   [:salario :double]
   [:dependentes :int]])

(def IrrfResponseSchema
  [:map
   [:valor :double]])

