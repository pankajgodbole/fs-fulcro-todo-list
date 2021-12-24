;;
;; src/main/app/client.cljs
;;

(ns app.client
  (:require
    [com.fulcrologic.fulcro.application :as app]
    [com.fulcrologic.fulcro.components  :as comp :refer [defsc]]
    [com.fulcrologic.fulcro.dom         :as dom  :refer [div h1 ol li]])

  (:require-macros [app.app-macros :as app-macros]))

(def pg-root
  "The DOM root element"
  (.getElementById js/document "app"))

(defn pg-render!
  "Renders the required DOM element"
  [e]
  (js/ReactDOM.render e pg-root))

(defn pg-make-div
  "Returns an HTML div from the given properties [ps] and child tag(s) [cs]"
  [ps & cs]
  (js/React.createElement "div"
                          (clj->js ps)
                          (clj->js (vec cs))))

;; Measure raw React speed
(time
  (dotimes [n 100000]
    (js/React.createElement "div"
                            #js {:className "red"}
                            #js ["Hi"])))
;; Measure raw React speed
(time
  (dotimes [n 100000]
    (pg-make-div {:className "red"} "Hi")))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(comment
  (pg-render! (js/React.createElement "div"
                                      #js {:className "red"}
                                      #js ["Hi"]))
  (macroexpand '(app-macros/pg-!no-op
                  (println "Testing macro-expanding ...")))

  (macroexpand '(div nil a))

  (app-macros/pg-!no-op
    (println "app.client/comment: Hi")
    (throw (js/Error. )))

  (println "In comment block"))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

  (comment
    (defonce app
             (app/fulcro-app))

    (defsc Root
      "The Root stateful UI component"
      [this props]
      (dom/div "TODO"))

    (defn ^:export init
      "Our entry-point function setup by shadow-cljs"
      []
      (app/mount! app Root "app")
      (js/console.log "client/init: Loaded"))

    (defn ^:export refresh
      "During development shadow-cljs will call this on every hot reload of the source"
      []
      ;; re-mounting will cause forced UI refresh, update internals, etc.
      (app/mount! app Root "app")
      ;; As of Fulcro 3.3.0, this addition will help with stale queries when using dynamic routing:
      (comp/refresh-dynamic-queries! app)
      (js/console.log "client/init: Hot reload")))