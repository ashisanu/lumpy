Sorry for all the english people, my english isn't good enough to write this article in understandable english.

# Wieso Lumpy? #
Als ich mich vor einiger Zeit nach einer für mich "perfekten" Programmiersprache umgesehen habe, musste ich ernüchternd feststellen, dass es kaum welche gibt, die meinen Ansprüchen recht werden.

Doch was erwarte ich von einer Sprache?
  * Objektorientierte Programmierung: Große Projekte mit möglichst wenig Overhead koordinieren
  * Schnelle Ausführung: Echtzeitanwendungen einfach erstellen
  * Leistungsstarke Sprachelemente (Generatoren, Templates/Generics, Properties,...): Ohne viel drum herum programmieren zu müssen.
  * Keine C Syntax: Für Menschen einfacher zu lesen
  * Statische Typisierung: Fehler bereits zur Kompilierzeit feststellen
  * Platformunabhängig: Keine Einschränkungen was die Platform anbelangt
  * Stabilität: Zuverlässiges Exceptionsystem.

So. Welche Sprachen bleiben nun übrig? Mir fällt nun eigentlich keine Sprache ein, die wirklich all diese Elemente implementiert. Die Idee für eine für mich "perfekte" Programmiersprache war geboren.

# Das _wirklich_ generische "loop". #
Die Schleifen in Lumpy werden alle mit loop eingeleitet. Dadurch gibt es keine Unterscheidung mehr zwischen "for", "while" und "foreach".

Doch wo liegt der Vorteil hierbei?
Hauptsächlich ist hierbei der Hintergrundgedanke, dass jede Schleife gleich behandelt werden soll. Wieso sollte der Programmierer zwischen einer Zählschleife und einer Bedingungsschleife unterscheiden? Beide erledigen das wiederholte Ausführen von Kommandos.

Außerdem ist es für meinen Geschmack um einiges übersichtlicher. Da hierdurch Codezeilen um einiges homogener aussehen. Zugegeben, dies ist wiederum ein sehr subjektives Argument, allerdings hoffe ich, dass auch andere so denken wie ich.

Nebenbei, gibt es bereits so viele Programmiersprachen, welche "for" und "while" verwenden, wieso nicht mal etwas Neues ausprobieren. Nun werden einige sicher denken "Wieso braucht eine erzwungene "Verschlechtbesserung"". Dies ist einfach erklärt: Wenn man nichts Neues ausprobiert, dann stagniert man immer auf demselben Niveau.

# Die _wirklich_ üppigen Syntaxelemente #
Lumpy bietet von Generatoren, Anonyme Funktionen bis hin zu Datenflussbasierte Programmierung und Templates alles an, was das Programmiererherz begehrt. Jedoch wo liegt der Sinn dahinter? Java hat doch auch ein vergleichbar kleines Sprachpaket.

Dies ist einfach erklärt. Mich hat es immer gestört, dass all diese "reduzierten" Sprachen absichtlich den Sprachumfang reduzieren (eigentlich sollte es "oxidieren" heißen wenn man chemisch denkt... :>). Wieso unterstützt Java keine Operatorüberladung? Wieso unterstützt es keine Generatoren? Operatorüberladung jedenfalls sollte rein technisch recht einfach möglich sein, da der "+" Operator bereits für Strings überladen wurde. Generatoren wollten auch einfach möglich sein, da die gesamte JavaVM eine stackbasierte VM ist.

Doch wieso implementieren die Entwickler dies nicht in Java ein? Sind sie faul, oder sehen sie andere Probleme?
Ich denke, sie wollen die Sprache künstlich einfach halten, indem sie auf solch leistungsstarken Sprachelementen verzichten. Stattdessen versuchen sie wild darum herum zu programmieren.

C# hingegen schlägt hier den richtigen Weg ein. Die Sprache ist einfach, kann allerdings all die nützlichen Dinge wie Generatoren, Operatorüberladung und Properties. Dadurch ist C# eine sehr leistungsstarke Sprache (Stattdessen hat sie andere Mankos...).

Ich versuche in Lumpy weitgehend alle sinnvollen (gut darüber lässt sich nun auch streiten...) Sprachelemente zu implementieren. Jedoch sollte dies nicht zu sehr ausarten, wie in Perl.

# Die _wirklich_ schnelle Ausführung #
Lumpy wird primär in die Programmiersprache C übersetzt. Hierdurch, wird einerseits eine hohe Platformunabhängigkeit erreicht (C Compiler gibt es auf _jeder_ Platform) andererseits auch eine sehr schnelle Ausführungsgeschwindigkeit. C ist mitunter eine der schnellsten Hochsprachen, da wirklich viele sehr gut optimierende Compiler existieren.

Doch wieso wird überhaupt in eine Hochsprache wie  C kompiliert? Lumpy könnte doch genauso in eine VM kompiliert werden, wie zum Beispiel JVM oder CIL Code?
Grundsätzlich sind Virtuelle Maschinen _immer_ langsamer als direkt kompilierter Code. Dies liegt einfach an der Natur von VMs. Sie "interpretieren" den Bytecode quasi. Nun werden einige meinen, es gäbe Just In Time Compiler. Jedoch sind diese erstens nicht immer ganz so "just in time" außerdem haben sie trotzdem noch einen graviernden Nachteil: Jeder der Lumpy Programme ausführen will, braucht diese VM installiert. Hierdurch wird die Benützung durch User stark reduziert, da kaum einer bereit ist eine VM zu installieren.

CIL ist wiederum Microsoft abhängig (und Mono ist auch nicht so das gelbe vom Ei) und die JavaVM ist vergleichsweise langsam.

# KICK - Keep It Consequent, Kid #
KICK ist ein eigenes Konzept. KICK bedeutet, dass in Lumppy alles gleich funktionieren soll. JEDE Schleife wird mit "loop" eingeleitet. JEDE Funktion (egal ob Methode oder Funktion) wird mit "function" eingeleitet. JEDE Variable (Egal ob Attribut, Lokal oder Global) wird mit "var" eingeleitet.
Außerdem gibt es kaum optionale Sprachelemente. Das einzig Optionale Sprachkonstrukt ist der "Step" Parameter beim for - loop.

Dies lässt sich noch weiter auslegen, aber ich glaube es wird damit klar was ich hiermit versuche zu erklären.
Frei nach dem Motto "There is only one way to program".

# Was bedeutet das nun für Sie? #

Die oben angeführten Kriterien für eine Programmiersprache sind natürlich stark subjektiv. Jedoch glaube ich, dass ich nicht der Einzige bin, der so denkt.
