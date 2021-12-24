;;
;; raw.cljs
;;

(ns app.raw)

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
(defn pg-make-ol
  "Returns an HTML ordered list from the given properties [ps] and child tag(s) [cs]"
  [ps & cs]
  (js/React.createElement "ol"
                          (clj->js ps)
                          (clj->js (vec cs))))
(defn pg-make-li
  "Returns an HTML list item from the given properties [ps] and child tag(s) [cs]"
  [ps & cs]
  (js/React.createElement "li"
                          (clj->js ps)
                          (clj->js (vec cs))))

(comment
  (pg-render! (js/React.createElement "div"
                                      #js {:className "red"}
                                      #js ["Hi"]))
  (pg-render! (pg-make-div {:className "red"} "Hi 2"))
  (pg-render! (pg-make-div {:className "red"} "Hi 3"))
  (pg-render! (pg-make-div {:className "red"}
                           (pg-make-div {}
                                        (pg-make-ol {}
                                                    (pg-make-li {} "Hello A"))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn return-arg
  [x]
  x)

(defn increment-arg
  [x]
  (inc x))