# cljx-start-lib

cljx-start-lib is a Leiningen template to create a lib for use by clojure and clojurescript.  It can be considered a companion to the  cljx-start which is a webapp skeleton generator.

[![Clojars Project](http://clojars.org/cljx-start-lib/lein-template/latest-version.svg)](http://clojars.org/cljx-start-lib/lein-template)
  
## Usage

tl;dr lein once, then lein dev and code away.
lein dev will start a figwheel process and the embedded webapp.

## Aliases

    lein clean ; clean generated and build targets (does not delete figwheel output)
    lein once ; preprocess cljx into clj and cljs and build once
	lein dev ; start headless, start cljx auto, and run figwheel (browse to localhost:8080) 
     
## TODO

An autotest feature for both clojure and clojurescript is the top priority here.  Second would be a non-browser centric option maybe targeting phantomjs.     
     
## License

Copyright &copy; 2015 ilazarte Released under the Eclipse Public License, the same as Clojure.