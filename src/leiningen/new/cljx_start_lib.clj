(ns leiningen.new.cljx-start-lib
  (:require [leiningen.new.templates :refer [renderer     
                                             multi-segment 
                                             sanitize-ns
                                             project-name
                                             year
                                             sanitize     
                                             ->files]]
            [leiningen.core.main :as main]))

; TODO, review multi-segment vs namespace vars candidates for removal
(defn cljx-start
  "cljx based clojure/script lib"
  [name]
  (let [render    (renderer "cljx-start-lib")
        main-ns   (multi-segment (sanitize-ns name))
        sanitized (sanitize (project-name name))
        data      {:name        (project-name name)
                   :namespace   main-ns
                   :sanitized   sanitized
                   :year        (year)}]
    
    (main/info "Generating" name "using cljx-start-lib")
    (main/info "Creating project files...")
    
    (->files 
      data
      [".gitignore"                         (render ".gitignore" data)]
      ["README.md"                          (render "README.md" data)]
      ["LICENSE"                            (render "LICENSE" data)]
      ["project.clj"                        (render "project.clj" data)]
      ["src/cljx/{{sanitized}}/core.cljx"   (render "core.cljx" data)]
      ["dev/cljx/{{sanitized}}/main.cljx"   (render "main.cljx" data)]
      ["dev/cljx/{{sanitized}}/routes.cljx" (render "routes.cljx" data)]
      ["dev/cljx/{{sanitized}}/view.cljx"   (render "view.cljx" data)])
    
    (main/info "Done!")))