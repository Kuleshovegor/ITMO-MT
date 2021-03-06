# Генератор парсеров
Генератор поддерживает LL(1) грамматики. <code>Generator</code> принимает два файла: путь к файлу с описанием грамматики и путь к директории, в которую будет сгенерирован пакет с парсером.

Файл с описанием грамматики должен начинаться с <code>@grammar <имя грамматики></code>

Правила грамматики описываются таким шаблоном <code><имя правила с маленькой буквы> = <последовательность терминалов и нетерминалов через пробел>;</code>

Для выбора нескольких последовательностей они разделяются символом <code>|</code>

Генератор поддерживает наследуемые и генерируемые аттрибуты. Для наследуемых аттрибутов необходимо после имени перечислить типы и названия аттрибутов в квадратных скобках, разделяя запятыми. Для синтезируемых аттрибутов необходимо перечислить их после <code>@returns</code> в квадратных скобках через <code>;</code>. Для обращения к ним используйте объект <code>result</code>

Генератор поддерживает вставки кода по шаблону <code>{code}</code>. Для обращения к терминалам и нетерминала используйте <code>arg<номер терминала или нетерминала в правиле с 0></code>

В репозитории также есть примеры со сгенерированными прасерами для [булевых](src/main/java/BooleanExpressionGrammarDescription) и [арифметических выражений](src/main/java/SimpleCalculatorGrammarDescription).