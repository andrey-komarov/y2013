---
title: Дерево отрезков
mathjax: on
---

Большую часть этого занятия будет решаться довольно скучная задача:
Дан массив из $n$ чисел и много запросов одного из двух видов:

* Записать в $i$-ю ячейку число $a_i$;
* посчитать сумму чисел на отрезке с $l_i$-го по $r_i$-е.

[Корневая оптимизация](http://neerc.ifmo.ru/wiki/index.php?title=%D0%A1%D1%82%D0%B0%D1%82%D0%B8%D1%81%D1%82%D0%B8%D0%BA%D0%B8_%D0%BD%D0%B0_%D0%BE%D1%82%D1%80%D0%B5%D0%B7%D0%BA%D0%B0%D1%85._%D0%9A%D0%BE%D1%80%D0%BD%D0%B5%D0%B2%D0%B0%D1%8F_%D1%8D%D0%B2%D1%80%D0%B8%D1%81%D1%82%D0%B8%D0%BA%D0%B0)
======================

Имеет также названия _sqrt-декомпозиция_, _корневая эвристика_.

Пусть данный массив $a$ имеет длину $n$. Выберем некое число $k$. Что за число
следует взять в качестве $k$ станет понятно потом.

Разобъём массив на _блоки_ размера $k$. В последнем блоке может оказаться меньше,
чем $k$ элементов. Это нас устраивает. В каждом из блоков посчитаем сумму и
где-нибудь её сохраним.

Нужно научиться обрабатывать обозначенные выше два запроса. Начнём с простого.

* Чтобы обновить значение в ячейке массива, можно:
     1. Обновить его в массиве.
     2. Пересчитать сумму в блоке, содержащем обновлённый элемент.

Итого, эту операцию можно делать за $O(k)$ (пересчёт суммы).

Вторая операция несколько сложнее. Пусть пришёл запрос суммы на интервале
$[left; right)$. Есть несколько случаев:

* Весь интервал $[left; right)$ полностью содержится в каком-то из блоков.
В этом случае можно просто просуммировать все эти элементы, потратив $O(k)$
времени.
* Интервал не содержится целиком ни в одном из блоков.
В этом случае есть два _граничных блока_, не содержащихся в запросе полностью
и сколько-то блоков, полностью содержащихся в интервале запроса.
Тогда для нахождения суммы на интервале $[left; right)$:
    * найдём суммы элементов интервала в граничных блоках;
    * найдём сумму в блоков, попавших в запрос полностью. Таких не более $n / k$.

Итого, обработка одного запроса суммы выполняется за $O(\frac{n}{k} + k)$.
Пришло время выбрать $k$. Мы хотим, чтобы время работы было как можно меньшим.

Продифференцируем время работы по $k$ и приравняем производную к нулю:
$\frac{d(n/k+k)}{dk} = -\frac{n}{k^2} + 1 = 0$, $n = \sqrt{k}$.