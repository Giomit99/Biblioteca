
# 📚 Progetto Biblioteca Avanzata

Sistema di gestione biblioteca completo, progettato per essere realistico e adatto a simulazioni in contesti accademici e professionali.

---

## 🎯 Obiettivi del Progetto

- Simulare una gestione realistica di una biblioteca.
- Implementare cicli tra package **intenzionali e significativi**.
- Fornire un'architettura modulare, estendibile e testabile.
- Consentire analisi di dipendenze tra package (es: test JDepend).

---

## 🏛️ Architettura Generale

**Principali package:**

| Package | Ruolo |
|:--------|:------|
| `model` | Contiene le entità di dominio: `User`, `Book`, `Loan`. |
| `controller` | Coordina le azioni dell'utente: `LoanController`, `UserController`. |
| `service` | Gestisce la logica di business: `LoanService`, `UserService`. |
| `repository` | Simula persistenza dati: `BookRepository`, `UserRepository`. |
| `util` | Funzionalità di supporto: `Formatter`, `Logger`. |
| `exception` | Gestione degli errori applicativi: `ServiceException`, `DatabaseException`. |

**Cicli tra package:**

- `controller ⇆ service`
- `model ⇆ repository`
- `service ⇆ util`

Cicli creati **intenzionalmente** per studiare il comportamento delle dipendenze.

---

## 🔥 Flussi principali operativi

- L'utente si registra con **nome, username, password**.
- Effettua il **login**.
- Visualizza i **libri disponibili**.
- Può **prenotare** o **prendere in prestito** un libro.
- Riceve **notifiche** se un prestito è prossimo alla scadenza.
- Può **restituire** un libro.
- Il sistema aggiorna le **statistiche**.
- ♻️ Pulizia automatica dei prestiti scaduti oltre 30 giorni.

---

## 🛠️ Tecnologie Utilizzate

- **Java 8** (compatibilità garantita)
- **Maven** per build e dipendenze

---

## 📈 Diagramma UML (semplificato)

```plaintext
+----------------+
|    User        |
|----------------|
| + name         |
| + username     |
| + password     |
+----------------+
       |
       v
+----------------+
|    Loan        |
|----------------|
| + startDate    |
| + dueDate      |
| + book         |
| + user         |
+----------------+
       ^
       |
+----------------+
|    Book        |
|----------------|
| + title        |
| + loaned       |
| + reservationQ |
+----------------+

UserController ↔ UserService ↔ UserRepository
LoanController ↔ LoanService ↔ BookRepository
Logger ↔ Formatter
```

---

## ⚙️ Gestione delle Eccezioni

- `ServiceException`: problemi logici nei servizi (es: utente non trovato).
- `DatabaseException`: simulazione errori di persistenza.

Tutte le eccezioni vengono intercettate e gestite con messaggi chiari.

---

## 📊 Statistiche e Pulizia

- Numero totale di prestiti eseguiti.
- Libro più prestato.
- Utente più attivo.
- Pulizia dei prestiti scaduti da più di 30 giorni.

---

## 🧠 Scelte di Design

- **Cicli tra pacchetti** creati volontariamente per analisi dinamiche.
- **Lista prenotazioni** gestita tramite `Queue` (FIFO).
- **Gestione date** moderna con `LocalDate`.
- **Accesso dati** simulato con Repository pattern semplificato.

---

## 🚀 Come eseguire il progetto

### Prerequisiti

- JDK 8+
- Maven

### Compilazione

```bash
mvn clean install
```

### Esecuzione

```bash
mvn exec:java -Dexec.mainClass="com.biblioteca.Main"
```

oppure avvia `Main.java` da un IDE (IntelliJ, Eclipse).

---

## 🔥 Idee di Estensione Futura

- Multi-lingua (supporto Italiano/Inglese)
- Integrazione database reale (es: MySQL)
- Interfaccia grafica JavaFX
- API RESTful
- Ricerca avanzata per autore o categoria

---

## 👍 Conclusione

Questo progetto rappresenta un **sistema di biblioteca** maturo, progettato per:
- Studiare architetture realistiche.
- Esercitarsi su **design modulare**.
- Simulare **dipendenze controllate**.
- Prepararsi a progetti veri o tesi di laurea.

Sistema **completo, testabile e pronto all'estensione**.
