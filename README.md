# 📚 Progetto Biblioteca Avanzata

Sistema di gestione di una biblioteca, progettato per essere realistico, ben strutturato e adatto a simulazioni accademiche, analisi architetturali e sperimentazioni con strumenti come JDepend.

---

## 🎯 Obiettivi del Progetto

- Simulare un contesto realistico di gestione di una biblioteca.
- Implementare e analizzare **dipendenze tra package** (anche cicliche) con strumenti come **JDepend**.
- Fornire un progetto **modulare, estendibile e testabile**, basato su una buona architettura.
- Servire come base per attività didattiche, refactoring o esercitazioni su design software.

---

## 🏗️ Architettura Generale

Il progetto segue una **struttura a livelli**, con separazione chiara delle responsabilità:

| Package       | Descrizione                                                                 |
|---------------|-----------------------------------------------------------------------------|
| `model`       | Entità del dominio (`User`, `Book`, `Loan`)                                 |
| `controller`  | Coordinamento delle operazioni principali (es. `LoanController`)             |
| `service`     | Logica applicativa (`LoanService`, `UserService`)                            |
| `repository`  | Persistenza simulata in memoria (`BookRepository`, `UserRepository`)         |
| `util`        | Funzioni ausiliarie (`Formatter`, `Logger`)                                  |
| `exception`   | Gestione degli errori (`ServiceException`, ecc.)                             |

---

## 🔧 Funzionalità

- 👤 Gestione utenti (creazione, autenticazione con `username` e `password`)
- 📚 Gestione libri (titolo, disponibilità, prenotazioni con coda FIFO)
- 🔄 Gestione prestiti (inizio prestito, scadenza automatica o personalizzata)
- 📅 Calcolo giorni rimanenti e verifica prestiti scaduti
- 📦 Repository in memoria per simulare un sistema senza database
- 🧪 Testing completo con **JUnit 5**
- 📈 Integrazione con **JaCoCo** per la copertura dei test
- 🧮 Progettato per supportare l’analisi delle dipendenze con **JDepend**

---

## 🧱 Design Pattern Utilizzati

- **Service Layer**: per separare la logica di dominio dal coordinamento delle operazioni.
- **Repository Pattern**: per astrarre la persistenza (simulata).
- **Factory Pattern** *(potenzialmente estendibile)*: utile per creare oggetti complessi in scenari più estesi.

---

## 🗺️ Mappa delle Dipendenze tra Package

```mermaid
graph TD
    model --> service
    model --> controller
    service --> controller
    controller --> repository
    util --> service
    exception --> service
