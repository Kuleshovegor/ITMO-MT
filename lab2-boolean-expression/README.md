# Лабораторная работа №2. Ручное построение нисходящих синтаксических анализаторов
## Язык
### Вариант 4. Логические формулы с множествами в стиле Python 
Логические формулы. Используются операции and, or, xor, not, in.
Приоритет операций стандартный. Скобки могут использоваться для изменения приоритета. Предусмотреть возможность оператора not in.
В качестве операндов выступают переменные с именем из одной буквы. Используйте один терминал для всех переменных. Для каждой логической операции должен быть заведен один терминал (не три ‘a’, ‘n’,
‘d’ для and).
Пример: (a in b) or (c not in b)
## 1 Разработка грамматики
Построим грамматику.

S → E

S → S xor E 

E → T

E → E or T

T → P

T → T and P

P → (S)

P → not P

P → c in c

P → c not in c

P → c

Избавимся от левой рекурсии.

S → ES'

S' → eps

S' → xor ES'

E → TE'

E' → eps

E' → or TE'

T → PT'

T' → and PT'

T' → eps

P → (S)

P → not P

P → c in c

P → c not in c

P → c

Описание нетерминалов.

S - выражение состоящее из суммы xor.

S' - продолжение суммы xor.

E - выражение состоящее из суммы or.

E' - продолжение суммы or.

T - выражение состоящее из and.

T' - продожение выражения состоящего из and.

P - aтом, выражение в скобах, переменная, отрицание P, выражение a in b или a not in b
## 2 Построение лексического анализатора
Токены описаны в файле [Token.kt](src/main/kotlin/Token.kt).
Лексический анализатор описан в [LexicalAnalyzer](src/main/kotlin/LexicalAnalyzer.kt).
## 3 Построение синтаксического анализатора
Построим функции FIRST и FOLLOW.

Нетерминал  | First     | Follow
------------| ----------|--------
S           | ( not var | $ )
S'          | eps xor   | $ )
E           | ( not var | xor $ )
E'          | eps or    | xor $ )
T           | ( not var | xor or $ )
T'          | eps and   | xor or $ )
P           | ( not var | xor and or $ )

Синтаксический анализатор описан в классе [Parser](src/main/kotlin/Parser.kt).
## 4 Визуализация дерева разбора
Для визуализации дерева использовалась библеотека GraphViz. Построить изображение дерева можно с помощью метода ```writeGraphviz``` в классе [Tree](src/main/kotlin/Tree.kt).
## 5 Подготовка набора тестов
Автоматические Junit тесты на ходятся в папке [test](src/test).