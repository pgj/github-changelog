# GitHub changelog

[![Continuous Integration status](https://api.travis-ci.org/raszi/github-changelog.svg?branch=master)](http://travis-ci.org/raszi/github-changelog)
[![Coverage Status](https://coveralls.io/repos/github/raszi/github-changelog/badge.svg?branch=master)](https://coveralls.io/github/raszi/github-changelog?branch=master)

Pull-request based conventional changelog generator for GitHub projects which follow the AngularJS [Commit Message Format].


## Releases and Dependency Information

* Releases are published to TODO_LINK

* Latest stable release is TODO_LINK

* All released versions TODO_LINK

[Leiningen] dependency information:

    [github-changelog "0.1.0-SNAPSHOT"]

[Maven] dependency information:

    <dependency>
      <groupId>github-changelog</groupId>
      <artifactId>github-changelog</artifactId>
      <version>0.1.0-SNAPSHOT</version>
    </dependency>



## Usage

### Command Line Interface

It should be started with a [edn] config file
with the following content:

```edn
{:user       "user"
 :repo       "repo"
 :token      "0123456789abcdef0123456789abcdef01234567"
 :git        "https://github.com/"
 :github-api "https://api.github.com/"
 :jira       "https://jira.atlassian.com/"
 :dir        "/tmp/destination-dir/"
 :update?    true}
```

| key | description | required |
|-----|-------------|----------|
| `:user`       | Username for the repo | ✓ |
| `:repo`       | Repository name | ✓ |
| `:token`      | GitHub [access token] to reach the API | ✓ |
| `:git`        | Git URL for cloning | ✓ |
| `:github-api` | URL for [GitHub Enterprise API] | ✗ |
| `:jira`       | JIRA URL if you're using that for issue tracking | ✗ |
| `:dir`        | The destination directory for the repo | ✗ |
| `:update?`    | Enables or disables repo updating | ✗ |

## Change Log

* Version 0.1.0-SNAPSHOT


## Copyright and License

Copyright © 2015-2016 István Karaszi

Distributed under [MIT license](http://choosealicense.com/licenses/mit/).

[Leiningen]: http://leiningen.org/
[Maven]: http://maven.apache.org/
[Commit Message Format]: https://github.com/angular/angular.js/blob/master/CONTRIBUTING.md#commit-message-format
[edn]: https://github.com/edn-format/edn
[access token]: https://help.github.com/articles/creating-an-access-token-for-command-line-use/
[GitHub Enterprise API]: https://developer.github.com/v3/enterprise/
