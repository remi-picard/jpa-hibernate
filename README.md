# Techlunch JPA / Hibernate

## Installation

### AsciiDoctor
```
bundle config --local github.https true
bundle --path=.bundle/gems --binstubs=.bundle/.bin
```

### RevealJS
```
git clone -b 3.3.0 --depth 1 https://github.com/hakimel/reveal.js.git
```

## Génération Slides
```
bundle exec asciidoctor-revealjs .\presentation.adoc
```