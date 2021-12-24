(ns app.app-macros)

(defmacro pg-!no-op
  [& body]
  `(try
     ~@body
     (catch :default ~'_)))

(comment
  (macroexpand '(pg-!no-op
                  (println "Expanding a macro")))

  (println "In comment block"))