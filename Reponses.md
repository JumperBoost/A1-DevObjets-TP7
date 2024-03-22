# Réponse aux questions du TP 7

## Exercice 1
1. ✅
2. ✅
3. ✅
4. ✅

## Exercice 2
1. ✅
2. Pour `dejaVus` et `frontiere`, on utilise une `ArrayList`, ce qui peut demander beaucoup de ressources mémoires avec de grandes listes lors d'intéraction avec des fonctions gourmandes telles que `add`, `size` ou encore `get`. Une meilleure solution serait d'utiliser `Queue` au lieu de `ArrayList` pour `frontiere`, puisqu'on souhaite seulement ajouter et supprimer selon le modèle FIFO. Pour `dejaVus`, il serait préférable d'utiliser un `HashSet` qui est plus performant dans la recherche d'éléments.
3. Il est préférable d'utiliser `HashSet` au lieu de `ArrayList` car il est plus performant pour rechercher un élément dans une liste.
4. ✅ (67s -> 9s)

## Exercice 3
1. ✅
2. ✅
3. ✅
4. ✅
5. ✅
6. ✅
7. ✅
8. ✅
