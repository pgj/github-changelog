(ns github-changelog.formatters.markdown-test
  (:require
    [github-changelog.formatters.markdown :as f-markdown]
    [github-changelog.markdown :as markdown]
    [github-changelog.schema :refer [Semver Tag Change Pull Issue]]
    [github-changelog.schema-generators :refer [generators]]
    [github-changelog.schema-complete :refer [complete]]
    [clojure.test :refer :all]))

(def v-major (complete {:major 1 :minor 0, :patch 0, :pre-release nil :build nil} Semver))
(def v-minor (complete {:minor 1 :patch 0, :pre-release nil :build nil} Semver))
(def v-patch (complete {:patch 1 :pre-release nil :build nil} Semver))
(def v-pre-release (complete {:pre-release "pre" :build nil} Semver))
(def v-build (complete {:pre-release "pre" :build "42"} Semver))

(deftest highlight-fn
         (are [function version] (= (f-markdown/highlight-fn version) function)
              markdown/h1 v-major
              markdown/h2 v-minor
              markdown/h3 v-patch
              markdown/h4 v-pre-release
              markdown/h5 v-build))

(deftest format-tag
  (are [content tag] (= content (f-markdown/format-tag tag))
                     (markdown/h1 "v1.0.0") (complete {:name "v1.0.0" :version v-major} Tag)
                     (markdown/h2 "v1.1.0") (complete {:name "v1.1.0" :version v-minor} Tag)))

(def pull (complete {:number 1 :html_url "http://example.com/"} Pull))
(def change (complete {:type "feat" :scope "scope" :subject "new something" :pull-request pull :issues []} Change))

(def expected-change (str "new something " (markdown/link "#1" "http://example.com/")))

(deftest format-change
  (is (= expected-change (f-markdown/format-change change))))

(def grouped (group-by :scope [change change]))
(def expected-scope (markdown/emphasis "scope:"))
(def expected-changes (markdown/ul [expected-change expected-change]))

(deftest format-grouped-changes
  (is (= (str expected-scope " " expected-change) (f-markdown/format-grouped-changes ["scope" [change]])))
  (is (= (str expected-scope expected-changes) (f-markdown/format-grouped-changes (first grouped)))))