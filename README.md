# Estudo de clojurescript com nodejs

Eu queria rodar nodejs mas com clojurescript para entender como a dinâmica funciona.

Antes de mais nada, o hot reload funciona muito bem, porém, é necessário rodar a aplicação node ao mesmo tempo para que aplique o efeito de reload.

Então:

## Como iniciar

```bash
npx shadow-cljs watch app

ou

npm run watch
```

```bash
node target/main

ou

npm start
```

Com isso já é possível desenvolver e ter o server atualizando a cada modificação de arquivo.


## Integrando com bibliotecas nodejs

É como se fosse trabalhar com node mesmo, basta adicionar com o `npm`.
O require da biblioteca só precisa ser feito com o nome entre aspas

```cljs
(ns xablau
  (:require ["express" :as express]))
```
