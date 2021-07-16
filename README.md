# M223: Punchclock
Dies ist eine Prüfungsabgabe für das Modul M223.

In der Applikation wurden bereits ein paar Objekte erstelt.
Anmelden kann man sich mit einem der folgenden Accounts:
Username:admin
Password:pw1

Username:user
Password:pw1

Es wurden auch schon UserGroups und Entrys erstellt.
Auf dem dashboard.html findet man alle Dashboards. (index.html, manageUsergroup.html, manageuser.html)

Was tut die Applikation:
Die Applikation kann verwendet werden um seine Arbeitszeiten einzutragen. Dafür
registriert man sich, loggt sich ein und trägt die Zeiten auf index.html ein. Man kann
auch Usergruppen verwalten und User verwalten. Im verwalten kann man immer welche erstellen, welche
updaten und Eintröge löschen.

Folgende Schritte befolgen um loszulegen:

    Sicherstellen, dass JDK 12 installiert und in der Umgebungsvariable path definiert ist.
    Ins Verzeichnis der Applikation wechseln und über die Kommandozeile mit ./gradlew bootRun oder ./gradlew.bat bootRun starten
    Unittest mit ./gradlew test oder ./gradlew.bat test ausführen.
    Ein ausführbares JAR kann mit ./gradlew bootJar oder ./gradlew.bat bootJar erstellt werden.

Folgende Dienste stehen während der Ausführung im Profil dev zur Verfügung:

    REST-Schnittstelle der Applikation: http://localhost:8081
    Dashboard der H2 Datenbank: http://localhost:8081/h2-console
