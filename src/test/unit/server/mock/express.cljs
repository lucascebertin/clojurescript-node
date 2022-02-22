(ns test.unit.server.mock.express)

(def express-calls (atom {}))

(def express-mock
  (js-obj

   "status"
   (fn [status]
     (swap! express-calls assoc :status status)
     express-mock)

   "send"
   (fn [data]
     (swap! express-calls assoc :data data)
     express-mock)))


