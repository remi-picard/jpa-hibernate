# Techlunch JPA / Hibernate

## Projet Java démo

### Installation
- Postgres 9+
- Ajouter base de données nommée "jpa-hibernate"
- Java 8+
- Maven 3+
- Eclipse

### Lancement
```
mvn install
java -jar jpa-hibernate-0.0.1-SNAPSHOT.jar
```
Sinon depuis Eclipse :
- Ouvrir com.github.remipicard.main.Demo
- Run As Java Application

## Slides / Deck

### Installation

#### AsciiDoctor
```
bundle config --local github.https true
bundle --path=.bundle/gems --binstubs=.bundle/.bin
```

#### RevealJS
```
git clone -b 3.3.0 --depth 1 https://github.com/hakimel/reveal.js.git
```

### Génération
```
bundle exec asciidoctor-revealjs .\presentation.adoc
```