(ns hu.ssh.github-changelog
  (:require
    [environ.core :refer [env]]
    [tentacles.core :as core]
    [tentacles.repos :as repos]
    [tentacles.pulls :as pulls]
    [clj-semver.core :as semver]))

(defn- repo
  "Gets the repository from its name"
  ([name] (repo "pro" name))
  ([org repo] [org repo]))

(defn- parse-semver
  "Parse semantic versions with or without 'v' predicate from the tags"
  [tag]
  (let [version (:name tag)
        parse #(try (semver/parse %)
                    (catch java.lang.AssertionError _e nil))]
    (if (= \v (first version))
      (parse (apply str (rest version)))
      (parse version))))

(defn- map-semver
  "Map semver to the tag"
  [tags]
  (map #(assoc % :version (parse-semver %)) tags))

(defn- fetch-version-tags
  "Fetch the version tags in the correct order"
  [user repo]
  (let [sort-fn #(semver/older? (:version %1) (:version %2))]
    (sort sort-fn (filter :version (map-semver (repos/tags user repo))))))

(defn changelog
  "Fetches the changelog"
  [user repo]
  (let [tags (delay (fetch-version-tags user repo))
        pulls (delay (pulls/pulls user repo {:state "closed"}))
        commits (delay (repos/commits user repo))]
    (println (first @tags))))

(core/with-defaults {:oauth-token (env :github-token) :all_pages true}
                    (changelog "raszi" "node-tmp"))